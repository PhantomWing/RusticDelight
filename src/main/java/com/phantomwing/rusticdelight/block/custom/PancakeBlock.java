package com.phantomwing.rusticdelight.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class PancakeBlock extends Block {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    /** Pancakes on a freshly crafted plate. Also what the block item is worth in the recipes. */
    public static final Integer MAX_SERVINGS = 6;

    /** The tallest stack that still fits inside a single block. */
    public static final int MAX_TOTAL_SERVINGS = 12;

    /**
     * Stack height, stored in two halves so existing worlds keep working. Values 0-5 are the
     * original "servings eaten off a plate of {@link #MAX_SERVINGS}" and are left untouched, so a
     * saved block still means exactly what it did. Values 6-11 continue past a full plate and hold
     * 7-12 pancakes. Use {@link #getPancakesPresent} rather than reading this directly.
     */
    public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, MAX_TOTAL_SERVINGS - 1);

    public final Supplier<Item> servingItem;

    protected static final VoxelShape PLATE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
    /** Indexed by pancakes present; each one is 1px tall, sitting on the 2px plate. */
    protected static final VoxelShape[] PANCAKES_SHAPES = buildShapes();

    private static VoxelShape[] buildShapes() {
        VoxelShape[] shapes = new VoxelShape[MAX_TOTAL_SERVINGS + 1];
        for (int present = 0; present < shapes.length; present++) {
            double top = 2.0D + Math.max(present, 1);
            shapes[present] = Shapes.joinUnoptimized(PLATE_SHAPE,
                    Block.box(3.0D, 2.0D, 3.0D, 13.0D, top, 13.0D), BooleanOp.OR);
        }
        return shapes;
    }

    public PancakeBlock(Supplier<Item> servingItem, Properties properties) {
        super(properties);

        this.servingItem = servingItem;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(SERVINGS, 0));
    }

    /** How many pancakes the block is currently showing. */
    public static int getPancakesPresent(BlockState state) {
        return pancakesPresentFor(state.getValue(SERVINGS));
    }

    /** Decodes the {@link #SERVINGS} value: eaten-from-a-plate below {@link #MAX_SERVINGS}, stacked above it. */
    public static int pancakesPresentFor(int servings) {
        return servings < MAX_SERVINGS ? MAX_SERVINGS - servings : servings + 1;
    }

    private static int servingsFor(int pancakesPresent) {
        return pancakesPresent <= MAX_SERVINGS ? MAX_SERVINGS - pancakesPresent : pancakesPresent - 1;
    }

    @Override
    public @NotNull InteractionResult useItemOn(ItemStack heldStack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        // Sneaking with a matching pancake puts one back onto the stack. Vanilla normally skips the
        // block interaction when sneaking with a full hand, so ModEvents forces it through.
        if (player.isSecondaryUseActive() && heldStack.is(this.servingItem.get())) {
            return addServing(level, pos, state, heldStack, player);
        }

        // Everything else takes a pancake, the same way Farmer's Delight feasts hand out servings.
        return takeServing(level, pos, state, player);
    }

    protected InteractionResult takeServing(Level level, BlockPos pos, BlockState state, Player player) {
        // Straight into the inventory, dropping only what doesn't fit — same as FD's FeastBlock,
        // which Rice Roll Royale and the Bell Pepper Medleys already inherit.
        if (!level.isClientSide()) {
            ItemStack serving = this.getServingItem();
            if (!player.getInventory().add(serving)) {
                player.drop(serving, false);
            }
        }

        // Spawn crumb particles using the pancake's texture — matches FD's PieBlock/FeastBlock.
        spawnServingParticles(level, pos, state);

        // Remove a serving from the block.
        this.removeServing(level, pos, state);

        // Play a sound, for taking the serving.
        level.playSound(null, pos, SoundEvents.WOOL_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);

        return InteractionResult.SUCCESS;
    }

    /** Puts a pancake back on, up to the height the block can show. */
    protected InteractionResult addServing(Level level, BlockPos pos, BlockState state, ItemStack heldStack, Player player) {
        int present = getPancakesPresent(state);
        if (present >= MAX_TOTAL_SERVINGS) {
            // Stacked as high as the block allows - consume so the held pancake isn't eaten instead.
            return InteractionResult.CONSUME;
        }

        level.setBlock(pos, state.setValue(SERVINGS, servingsFor(present + 1)), Block.UPDATE_ALL);

        if (!player.getAbilities().instabuild) {
            heldStack.shrink(1);
        }

        level.playSound(null, pos, SoundEvents.WOOL_PLACE, SoundSource.PLAYERS, 0.8F, 0.8F);

        return InteractionResult.SUCCESS;
    }

    /**
     * Server-side: emit 3 small block-texture particles above the pancake plate, matching the
     * crumb effect FD's {@code PieBlock} / {@code FeastBlock} spawn when a serving is taken.
     * Same magic numbers as FD (count 3, spread 0.1, speed 0.001, y offset +0.3).
     */
    private void spawnServingParticles(Level level, BlockPos pos, BlockState state) {
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK, state),
                    pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5,
                    3,
                    0.1, 0.1, 0.1,
                    0.001);
        }
    }

    /** Takes the topmost pancake off, destroying the block once the plate is empty. */
    private void removeServing(Level level, BlockPos pos, BlockState state) {
        int present = getPancakesPresent(state);
        if (present > 1) {
            level.setBlock(pos, state.setValue(SERVINGS, servingsFor(present - 1)), Block.UPDATE_ALL);
        } else {
            // No loot: takeServing already handed the player this last pancake, and the loot table
            // would drop the block's remaining serving a second time.
            level.destroyBlock(pos, false);
        }
    }

    public ItemStack getServingItem() {
        return new ItemStack(this.servingItem.get());
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return PANCAKES_SHAPES[getPancakesPresent(state)];
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected @NotNull BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        return direction == Direction.DOWN && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        BlockPos floorPos = pos.below();
        return canSupportRigidBlock(level, floorPos) || canSupportCenter(level, floorPos, Direction.UP);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SERVINGS);
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
        return state.getValue(SERVINGS);
    }

    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState state) {
        return true;
    }

    @Override
    public boolean isPathfindable(@NotNull BlockState state, @NotNull PathComputationType type) {
        return false;
    }
}

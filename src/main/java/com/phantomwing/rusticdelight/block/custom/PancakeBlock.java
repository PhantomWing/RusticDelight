package com.phantomwing.rusticdelight.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class PancakeBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
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
    public static final IntProperty SERVINGS = IntProperty.of("servings", 0, MAX_TOTAL_SERVINGS - 1);

    public final Supplier<Item> servingItem;

    protected static final VoxelShape PLATE_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
    /** Indexed by pancakes present; each one is 1px tall, sitting on the 2px plate. */
    protected static final VoxelShape[] PANCAKES_SHAPES = buildShapes();

    private static VoxelShape[] buildShapes() {
        VoxelShape[] shapes = new VoxelShape[MAX_TOTAL_SERVINGS + 1];
        for (int present = 0; present < shapes.length; present++) {
            double top = 2.0D + Math.max(present, 1);
            shapes[present] = VoxelShapes.combine(PLATE_SHAPE,
                    Block.createCuboidShape(3.0D, 2.0D, 3.0D, 13.0D, top, 13.0D), BooleanBiFunction.OR);
        }
        return shapes;
    }

    public PancakeBlock(Supplier<Item> servingItem, Settings settings) {
        super(settings);

        this.servingItem = servingItem;
        this.setDefaultState(getStateManager().getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(SERVINGS, 0));
    }

    /** How many pancakes the block is currently showing. */
    public static int getPancakesPresent(BlockState state) {
        return pancakesPresentFor(state.get(SERVINGS));
    }

    /** Decodes the {@link #SERVINGS} value: eaten-from-a-plate below {@link #MAX_SERVINGS}, stacked above it. */
    public static int pancakesPresentFor(int servings) {
        return servings < MAX_SERVINGS ? MAX_SERVINGS - servings : servings + 1;
    }

    private static int servingsFor(int pancakesPresent) {
        return pancakesPresent <= MAX_SERVINGS ? MAX_SERVINGS - pancakesPresent : pancakesPresent - 1;
    }

    @Override
    public @NotNull ActionResult onUse(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        ItemStack heldStack = player.getStackInHand(hand);

        // Sneaking with a matching pancake puts one back onto the stack. Vanilla normally skips the
        // block interaction when sneaking with a full hand, so ModEvents forces it through.
        if (player.shouldCancelInteraction() && heldStack.isOf(this.servingItem.get())) {
            return addServing(level, pos, state, heldStack, player);
        }

        // Everything else takes a pancake, the same way Farmer's Delight feasts hand out servings.
        return takeServing(level, pos, state, player);
    }

    protected ActionResult takeServing(World level, BlockPos pos, BlockState state, PlayerEntity player) {
        // Straight into the inventory, dropping only what doesn't fit, same as FD's FeastBlock,
        // which Rice Roll Royale and the Bell Pepper Medleys already inherit.
        if (!level.isClient) {
            ItemStack serving = this.getServingItem();
            if (!player.getInventory().insertStack(serving)) {
                player.dropItem(serving, false);
            }
        }

        // Spawn crumb particles using the pancake's texture, matching FD's PieBlock/FeastBlock.
        spawnServingParticles(level, pos, state);

        // Remove a serving from the block.
        this.removeServing(level, pos, state);

        // Play a sound, for taking the serving.
        level.playSound(null, pos, SoundEvents.BLOCK_WOOL_BREAK, SoundCategory.PLAYERS, 0.8F, 0.8F);

        return ActionResult.SUCCESS;
    }

    /** Puts a pancake back on, up to the height the block can show. */
    protected ActionResult addServing(World level, BlockPos pos, BlockState state, ItemStack heldStack, PlayerEntity player) {
        int present = getPancakesPresent(state);
        if (present >= MAX_TOTAL_SERVINGS) {
            // Stacked as high as the block allows - consume so the held pancake isn't eaten instead.
            return ActionResult.CONSUME;
        }

        level.setBlockState(pos, state.with(SERVINGS, servingsFor(present + 1)), Block.NOTIFY_ALL);

        if (!player.getAbilities().creativeMode) {
            heldStack.decrement(1);
        }

        level.playSound(null, pos, SoundEvents.BLOCK_WOOL_PLACE, SoundCategory.PLAYERS, 0.8F, 0.8F);

        return ActionResult.SUCCESS;
    }

    /**
     * Server-side: emit 3 small block-texture particles above the pancake plate, matching the
     * crumb effect FD's {@code PieBlock} / {@code FeastBlock} spawn when a serving is taken.
     * Same magic numbers as FD (count 3, spread 0.1, speed 0.001, y offset +0.3).
     */
    private void spawnServingParticles(World level, BlockPos pos, BlockState state) {
        if (level instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    new BlockStateParticleEffect(ParticleTypes.BLOCK, state),
                    pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5,
                    3,
                    0.1, 0.1, 0.1,
                    0.001);
        }
    }

    /** Takes the topmost pancake off, destroying the block once the plate is empty. */
    private void removeServing(World level, BlockPos pos, BlockState state) {
        int present = getPancakesPresent(state);
        if (present > 1) {
            level.setBlockState(pos, state.with(SERVINGS, servingsFor(present - 1)), Block.NOTIFY_ALL);
        } else {
            // No loot: takeServing already handed the player this last pancake, and the loot table
            // would drop the block's remaining serving a second time.
            level.breakBlock(pos, false);
        }
    }

    public ItemStack getServingItem() {
        return new ItemStack(this.servingItem.get());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
        return PANCAKES_SHAPES[getPancakesPresent(state)];
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(FACING, context.getHorizontalPlayerFacing());
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSolidBlock(world, pos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, SERVINGS);
    }

    @Override
    public int getComparatorOutput(BlockState blockState, World level, BlockPos pos) {
        return blockState.get(SERVINGS);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView level, BlockPos pos, NavigationType type) {
        return false;
    }
}

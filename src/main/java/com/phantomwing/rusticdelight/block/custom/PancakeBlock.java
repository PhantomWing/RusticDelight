package com.phantomwing.rusticdelight.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.ItemUtils;

import java.util.function.Supplier;

public class PancakeBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final Integer MAX_SERVINGS = 6;
    public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, MAX_SERVINGS - 1);

    public final Supplier<Item> servingItem;

    protected static final VoxelShape PLATE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
    protected static final VoxelShape[] PANCAKES_SHAPES =  new VoxelShape[]{
            Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(3.0D, 2.0D, 3.0D, 13.0D, 8.0D, 13.0D), BooleanOp.OR),
            Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(3.0D, 2.0D, 3.0D, 13.0D, 7.0D, 13.0D), BooleanOp.OR),
            Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(3.0D, 2.0D, 3.0D, 13.0D, 6.0D, 13.0D), BooleanOp.OR),
            Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(3.0D, 2.0D, 3.0D, 13.0D, 5.0D, 13.0D), BooleanOp.OR),
            Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(3.0D, 2.0D, 3.0D, 13.0D, 4.0D, 13.0D), BooleanOp.OR),
            Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(3.0D, 2.0D, 3.0D, 13.0D, 3.0D, 13.0D), BooleanOp.OR)
    };

    public PancakeBlock(Supplier<Item> servingItem, Properties properties) {
        super(properties);

        this.servingItem = servingItem;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(SERVINGS, 0));
    }

    @Override
    public @NotNull ItemInteractionResult useItemOn(ItemStack heldStack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (heldStack.is(ModTags.Items.KNIVES)) {
            return takeServing(level, pos, state, player);
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        if (level.isClientSide) {
            if (consumeServing(level, pos, state, player).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        return consumeServing(level, pos, state, player);
    }

    protected ItemInteractionResult takeServing(Level level, BlockPos pos, BlockState state, Player player) {
        // Drop the serving item.
        Direction direction = player.getDirection().getOpposite();
        ItemUtils.spawnItemEntity(level, this.getServingItem(), pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5,
                direction.getStepX() * 0.15, 0.05, direction.getStepZ() * 0.15);

        // Spawn crumb particles using the pancake's texture — matches FD's PieBlock/FeastBlock.
        spawnEatingParticles(level, pos, state);

        // Remove a serving from the block.
        this.removeServing(level, pos, state);

        // Play a sound, for taking the serving.
        level.playSound(null, pos, SoundEvents.WOOL_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);

        return ItemInteractionResult.SUCCESS;
    }

    /**
     * Eats a pancake from the stack, feeding the player.
     */
    protected InteractionResult consumeServing(Level level, BlockPos pos, BlockState state, Player playerIn) {
        if (!playerIn.canEat(false)) {
            // If the player is full, no interaction is possible.
            return InteractionResult.PASS;
        } else {
            ItemStack servingStack = this.getServingItem();
            FoodProperties foodProperties = servingStack.getFoodProperties(playerIn);

            // Apply food effect to the player
            if (foodProperties != null) {
                playerIn.getFoodData().eat(foodProperties);
                for (FoodProperties.PossibleEffect effect : foodProperties.effects()) {
                    if (!level.isClientSide && effect != null && level.random.nextFloat() < effect.probability()) {
                        playerIn.addEffect(effect.effect());
                    }
                }
            }

            // Spawn crumb particles using the pancake's texture — matches FD's PieBlock/FeastBlock.
            spawnEatingParticles(level, pos, state);

            // Remove a serving from the block.
            this.removeServing(level, pos, state);

            // Play a sound.
            level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.8F, 0.8F);

            return InteractionResult.SUCCESS;
        }
    }

    /**
     * Server-side: emit 3 small block-texture particles above the pancake plate, matching the
     * crumb effect FD's {@code PieBlock} / {@code FeastBlock} spawn when a bite is consumed.
     * Same magic numbers as FD (count 3, spread 0.1, speed 0.001, y offset +0.3).
     */
    private void spawnEatingParticles(Level level, BlockPos pos, BlockState state) {
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK, state),
                    pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5,
                    3,
                    0.1, 0.1, 0.1,
                    0.001);
        }
    }

    /** Update the block model. If there are no more servings left, destroy the block. */
    private void removeServing(Level level, BlockPos pos, BlockState state) {
        int servingsTaken = state.getValue(SERVINGS);
        if (servingsTaken < MAX_SERVINGS - 1) {
            level.setBlock(pos, state.setValue(SERVINGS, servingsTaken + 1), MAX_SERVINGS - 1);
        } else {
            level.destroyBlock(pos, true);
        }
    }

    public ItemStack getServingItem() {
        return new ItemStack(this.servingItem.get());
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return PANCAKES_SHAPES[state.getValue(SERVINGS)];
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState stateIn, @NotNull Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor level, @NotNull BlockPos currentPos, @NotNull BlockPos facingPos) {
        return facing == Direction.DOWN && !stateIn.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SERVINGS);
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, @NotNull Level level, @NotNull BlockPos pos) {
        return blockState.getValue(SERVINGS);
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

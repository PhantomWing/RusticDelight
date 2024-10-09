package com.phantomwing.rusticdelight.block.custom;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
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

public class PancakeBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final Integer MAX_SERVINGS = 6;
    public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, MAX_SERVINGS - 1);

    public final FoodProperties foodProperties;

    protected static final VoxelShape PLATE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
    protected static final VoxelShape[] PANCAKES_SHAPES =  new VoxelShape[]{
            Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(3.0D, 2.0D, 3.0D, 13.0D, 8.0D, 13.0D), BooleanOp.OR),
            Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(3.0D, 2.0D, 3.0D, 13.0D, 7.0D, 13.0D), BooleanOp.OR),
            Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(3.0D, 2.0D, 3.0D, 13.0D, 6.0D, 13.0D), BooleanOp.OR),
            Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(3.0D, 2.0D, 3.0D, 13.0D, 5.0D, 13.0D), BooleanOp.OR),
            Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(3.0D, 2.0D, 3.0D, 13.0D, 4.0D, 13.0D), BooleanOp.OR),
            Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(3.0D, 2.0D, 3.0D, 13.0D, 3.0D, 13.0D), BooleanOp.OR)
    };

    public PancakeBlock(FoodProperties foodProperties, Properties properties) {
        super(properties);

        this.foodProperties = foodProperties;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(SERVINGS, 0));
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
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

    /**
     * Eats a pancake from the stack, feeding the player.
     */
    protected InteractionResult consumeServing(Level level, BlockPos pos, BlockState state, Player playerIn) {
        if (!playerIn.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            // Apply food effect to the player
            if (foodProperties != null) {
                playerIn.getFoodData().eat(foodProperties.getNutrition(), foodProperties.getSaturationModifier());
                for (Pair<MobEffectInstance, Float> pair: foodProperties.getEffects()) {
                    if (!level.isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                        playerIn.addEffect(new MobEffectInstance(pair.getFirst()));
                    }
                }
            }

            // Update the block model. If there are no more servings left, destroy the block.
            int servingsTaken = state.getValue(SERVINGS);
            if (servingsTaken < MAX_SERVINGS - 1) {
                level.setBlock(pos, state.setValue(SERVINGS, servingsTaken + 1), MAX_SERVINGS - 1);
            } else {
                level.removeBlock(pos, false);
            }

            // Play a sound.
            level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.8F, 0.8F);

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return PANCAKES_SHAPES[state.getValue(SERVINGS)];
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return facing == Direction.DOWN && !stateIn.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SERVINGS);
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        return blockState.getValue(SERVINGS);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }
}
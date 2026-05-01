package com.phantomwing.rusticdelight.block.custom;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
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
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.ItemUtils;

import java.util.function.Supplier;

public class PancakeBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final Integer MAX_SERVINGS = 6;
    public static final IntProperty SERVINGS = IntProperty.of("servings", 0, MAX_SERVINGS - 1);

    public final Supplier<Item> servingItem;

    protected static final VoxelShape PLATE_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
    protected static final VoxelShape[] PANCAKES_SHAPES =  new VoxelShape[]{
            VoxelShapes.combine(PLATE_SHAPE, Block.createCuboidShape(3.0D, 2.0D, 3.0D, 13.0D, 8.0D, 13.0D), BooleanBiFunction.OR),
            VoxelShapes.combine(PLATE_SHAPE, Block.createCuboidShape(3.0D, 2.0D, 3.0D, 13.0D, 7.0D, 13.0D), BooleanBiFunction.OR),
            VoxelShapes.combine(PLATE_SHAPE, Block.createCuboidShape(3.0D, 2.0D, 3.0D, 13.0D, 6.0D, 13.0D), BooleanBiFunction.OR),
            VoxelShapes.combine(PLATE_SHAPE, Block.createCuboidShape(3.0D, 2.0D, 3.0D, 13.0D, 5.0D, 13.0D), BooleanBiFunction.OR),
            VoxelShapes.combine(PLATE_SHAPE, Block.createCuboidShape(3.0D, 2.0D, 3.0D, 13.0D, 4.0D, 13.0D), BooleanBiFunction.OR),
            VoxelShapes.combine(PLATE_SHAPE, Block.createCuboidShape(3.0D, 2.0D, 3.0D, 13.0D, 3.0D, 13.0D), BooleanBiFunction.OR)
    };

    public PancakeBlock(Supplier<Item> servingItem, Settings settings) {
        super(settings);

        this.servingItem = servingItem;
        this.setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(SERVINGS, 0));
    }

    @Override
    public @NotNull ActionResult onUse(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        ItemStack heldStack = player.getStackInHand(hand);
        if (heldStack.isIn(ModTags.KNIVES)) {
            return takeServing(level, pos, state, player);
        }

        if (level.isClient) {
            if (this.consumeServing(level, pos, state, player).isAccepted()) {
                return ActionResult.SUCCESS;
            }
            if (player.getStackInHand(hand).isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        return consumeServing(level, pos, state, player);
    }

    /**
     * Drops a single pancake serving item without feeding the player.
     */
    protected ActionResult takeServing(World level, BlockPos pos, BlockState state, PlayerEntity player) {
        Direction direction = player.getHorizontalFacing().getOpposite();
        ItemUtils.spawnItemEntity(level, this.getServingItem(),
                pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5,
                direction.getOffsetX() * 0.15, 0.05, direction.getOffsetZ() * 0.15);

        this.removeServing(level, pos, state);

        level.playSound(null, pos, SoundEvents.BLOCK_WOOL_BREAK, SoundCategory.PLAYERS, 0.8F, 0.8F);

        return ActionResult.SUCCESS;
    }

    /**
     * Eats a pancake from the stack, feeding the player.
     */
    protected ActionResult consumeServing(World level, BlockPos pos, BlockState state, PlayerEntity playerIn) {
        if (!playerIn.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            ItemStack servingStack = this.getServingItem();
            FoodComponent foodComp = servingStack.getItem().getFoodComponent();

            if (foodComp != null) {
                playerIn.getHungerManager().add(foodComp.getHunger(), foodComp.getSaturationModifier());
                for (Pair<StatusEffectInstance, Float> effect : foodComp.getStatusEffects()) {
                    if (!level.isClient && effect != null && level.random.nextFloat() < effect.getSecond()) {
                        playerIn.addStatusEffect(effect.getFirst());
                    }
                }
            }

            this.removeServing(level, pos, state);

            level.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, 0.8F, 0.8F);

            return ActionResult.SUCCESS;
        }
    }

    /** Update the block model. If there are no more servings left, destroy the block. */
    private void removeServing(World level, BlockPos pos, BlockState state) {
        int servingsTaken = state.get(SERVINGS);
        if (servingsTaken < MAX_SERVINGS - 1) {
            level.setBlockState(pos, state.with(SERVINGS, servingsTaken + 1), MAX_SERVINGS - 1);
        } else {
            level.breakBlock(pos, true);
        }
    }

    public ItemStack getServingItem() {
        return new ItemStack(this.servingItem.get());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
        return PANCAKES_SHAPES[state.get(SERVINGS)];
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(FACING, context.getHorizontalPlayerFacing().getOpposite());
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

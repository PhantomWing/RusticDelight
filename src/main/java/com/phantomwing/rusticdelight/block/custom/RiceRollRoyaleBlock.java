package com.phantomwing.rusticdelight.block.custom;

import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.NotNull;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class RiceRollRoyaleBlock extends FeastBlock
{
    public static final IntProperty ROLL_SERVINGS = IntProperty.of("servings", 0, 8);
    public static final int MAX_SERVINGS = 8;

    protected static final VoxelShape PLATE_SHAPE = FeastBlock.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
    protected static final VoxelShape FOOD_SHAPE = VoxelShapes.combineAndSimplify(PLATE_SHAPE, FeastBlock.createCuboidShape(2.0D, 2.0D, 2.0D, 14.0D, 4.0D, 14.0D), BooleanBiFunction.OR);

    public final List<Supplier<Item>> riceRollServings = Arrays.asList(
            () -> ModItems.CHERRY_BLOSSOM_ROLL,
            () -> ModItems.CALAMARI_ROLL,
            () -> ModItems.BELL_PEPPER_ROLL_YELLOW,
            () -> ModItems.BELL_PEPPER_ROLL_RED,
            () -> ModItems.BELL_PEPPER_ROLL_GREEN,
            vectorwing.farmersdelight.common.registry.ModItems.KELP_ROLL_SLICE,
            vectorwing.farmersdelight.common.registry.ModItems.KELP_ROLL_SLICE,
            vectorwing.farmersdelight.common.registry.ModItems.KELP_ROLL_SLICE
    );

    public RiceRollRoyaleBlock(Settings settings) {
        super(settings, () -> ModItems.CHERRY_BLOSSOM_ROLL, true);
    }

    @Override
    public @NotNull IntProperty getServingsProperty() {
        return ROLL_SERVINGS;
    }

    @Override
    public int getMaxServings() {
        return MAX_SERVINGS;
    }

    @Override
    public @NotNull ItemStack getServingItem(BlockState state) {
        return new ItemStack(riceRollServings.get(state.get(getServingsProperty()) - 1).get());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
        return state.get(getServingsProperty()) == 0 ? PLATE_SHAPE : FOOD_SHAPE;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(FACING, context.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, ROLL_SERVINGS);
    }
}
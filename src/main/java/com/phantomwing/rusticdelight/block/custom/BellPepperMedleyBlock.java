package com.phantomwing.rusticdelight.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A platter holding three stuffed bell peppers - two at the back, one in front. Knife/serving
 * behaviour comes straight from {@link FeastBlock}, same as Rice Roll Royale.
 */
public class BellPepperMedleyBlock extends FeastBlock {
    // Named apart from FeastBlock.SERVINGS so we can use our own 0-3 range.
    public static final IntegerProperty MEDLEY_SERVINGS = IntegerProperty.create("servings", 0, 3);
    public static final int MAX_SERVINGS = 3;

    // Shapes per facing, indexed by servings, so collision follows the peppers still on the
    // platter. Only the 3-pepper arrangement is symmetric under a half turn, so north and south
    // genuinely differ once one has been taken.
    private static final Map<Direction, VoxelShape[]> SHAPES = buildShapes();

    /** Ordered [back-left, back-right, front] to match the stage models. */
    private final List<Supplier<Item>> peppers;

    public BellPepperMedleyBlock(Properties properties, List<Supplier<Item>> peppers) {
        super(properties, peppers.get(0), true);
        this.peppers = peppers;
    }

    @Override
    public @NotNull IntegerProperty getServingsProperty() {
        return MEDLEY_SERVINGS;
    }

    @Override
    public int getMaxServings() {
        return MAX_SERVINGS;
    }

    @Override
    public @NotNull ItemStack getServingItem(BlockState state) {
        // Serves front first, then back-right, then back-left - the order the models empty in.
        return new ItemStack(peppers.get(state.getValue(getServingsProperty()) - 1).get());
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPES.get(state.getValue(FACING))[state.getValue(getServingsProperty())];
    }

    /**
     * Builds the north-facing shapes (matching the un-rotated model), then derives the other
     * three facings by repeatedly rotating a quarter turn.
     */
    private static Map<Direction, VoxelShape[]> buildShapes() {
        VoxelShape platter = Block.box(1.0D, 0.0D, 2.0D, 15.0D, 3.0D, 14.0D);
        VoxelShape backLeft = pepper(2.0D, 8.0D);
        VoxelShape backRight = pepper(9.0D, 8.0D);
        VoxelShape front = pepper(5.0D, 3.0D);

        // Servings are taken front -> back-right -> back-left, so that is the order they vanish.
        VoxelShape[] shapes = new VoxelShape[MAX_SERVINGS + 1];
        shapes[0] = platter;
        shapes[1] = Shapes.or(platter, backLeft);
        shapes[2] = Shapes.or(platter, backLeft, backRight);
        shapes[3] = Shapes.or(platter, backLeft, backRight, front);

        Map<Direction, VoxelShape[]> byFacing = new EnumMap<>(Direction.class);
        byFacing.put(Direction.NORTH, shapes);
        for (Direction facing : new Direction[]{Direction.EAST, Direction.SOUTH, Direction.WEST}) {
            VoxelShape[] rotated = new VoxelShape[shapes.length];
            for (int i = 0; i < shapes.length; i++) {
                rotated[i] = rotateY(shapes[i]);
            }
            byFacing.put(facing, rotated);
            shapes = rotated;
        }
        return byFacing;
    }

    /**
     * One pepper, given the minimum corner of its 5x5 footprint: the body sitting on the platter
     * plus the narrower stuffing cap on top, so collision follows the model instead of squaring
     * off the last pixel.
     */
    private static VoxelShape pepper(double x, double z) {
        return Shapes.or(
                Block.box(x, 3.0D, z, x + 5.0D, 8.0D, z + 5.0D),
                Block.box(x + 1.0D, 8.0D, z + 1.0D, x + 4.0D, 9.0D, z + 4.0D));
    }

    /** Rotates a shape a quarter turn clockwise about Y, matching the blockstate's y rotation. */
    private static VoxelShape rotateY(VoxelShape shape) {
        VoxelShape[] result = {Shapes.empty()};
        shape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) ->
                result[0] = Shapes.joinUnoptimized(result[0],
                        Shapes.box(1.0D - maxZ, minY, minX, 1.0D - minZ, maxY, maxX), BooleanOp.OR));
        return result[0].optimize();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, MEDLEY_SERVINGS);
    }
}

package com.phantomwing.rusticdelight.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;

import java.util.function.Supplier;

public class BellPepperCropBlock extends CropBlock {
    public static final int MAX_AGE = 7;
    public static final IntProperty AGE = Properties.AGE_7;

    private final Supplier<? extends ItemConvertible> seed;

    public BellPepperCropBlock(Settings settings, Supplier<? extends ItemConvertible> seed) {
        super(settings);
        this.seed = seed;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return seed.get();
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}

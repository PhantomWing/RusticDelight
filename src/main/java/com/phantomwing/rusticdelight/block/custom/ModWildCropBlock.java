package com.phantomwing.rusticdelight.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.block.WildCropBlock;

public class ModWildCropBlock extends WildCropBlock {
    public ModWildCropBlock(Holder<MobEffect> suspiciousStewEffect, int effectDuration, Properties properties) {
        super(suspiciousStewEffect, effectDuration, properties);
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        // 26.1: BlockTags.SUBSTRATE_OVERWORLD aggregates dirt, mud, moss, and grass-like blocks
        // (the natural overworld ground tag). Also accept sand so wild crops can grow on beach.
        return state.is(BlockTags.SUBSTRATE_OVERWORLD) || state.is(BlockTags.SAND);
    }
}

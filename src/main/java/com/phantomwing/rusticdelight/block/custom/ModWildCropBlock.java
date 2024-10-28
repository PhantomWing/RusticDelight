package com.phantomwing.rusticdelight.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import vectorwing.farmersdelight.common.block.WildCropBlock;

public class ModWildCropBlock extends WildCropBlock {
    public ModWildCropBlock(RegistryEntry<StatusEffect> suspiciousStewEffect, int effectDuration, Settings properties) {
        super(suspiciousStewEffect, effectDuration, properties);
    }

    public boolean canPlantOnTop(BlockState state, BlockView level, BlockPos pos) {
        return state.isIn(BlockTags.DIRT);
    }
}

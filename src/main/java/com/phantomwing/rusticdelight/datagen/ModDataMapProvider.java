package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.block.BlockManager;
import com.phantomwing.rusticdelight.item.ItemManager;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        builder(NeoForgeDataMaps.COMPOSTABLES)
                // 30% chance
                .add(ItemManager.COTTON_SEEDS.getId(), new Compostable(0.3f, true), false)
                // 50% chance
                .add(ItemManager.COTTON_BOLL.getId(), new Compostable(0.5f, true), false)
                // 65% chance
                .add(BlockManager.WILD_COTTON.getId(), new Compostable(0.65f, true), false)
        ;
    }
}

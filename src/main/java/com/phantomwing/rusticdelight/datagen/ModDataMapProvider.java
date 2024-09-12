package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.item.ModItems;
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
                .add(ModItems.COTTON_SEEDS.getId(), new Compostable(0.3f, true), false)

                // 50% chance
                .add(ModItems.COTTON_BOLL.getId(), new Compostable(0.5f, true), false)
                .add(ModItems.POTATO_SLICES.getId(), new Compostable(0.5f, true), false)

                // 65% chance
                .add(ModItems.WILD_COTTON.getId(), new Compostable(0.65f, true), false)
        ;
    }
}

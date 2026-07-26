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
    protected void gather(HolderLookup.Provider provider) {
        builder(NeoForgeDataMaps.COMPOSTABLES)
                // 30% chance
                .add(ModItems.COTTON_SEEDS.getId(), new Compostable(0.3f, true), false)
                .add(ModItems.BELL_PEPPER_SEEDS.getId(), new Compostable(0.3f, true), false)
                .add(ModItems.PALE_BELL_PEPPER_SEEDS.getId(), new Compostable(0.3f, true), false)
                .add(ModItems.DARK_BELL_PEPPER_SEEDS.getId(), new Compostable(0.3f, true), false)

                // 50% chance
                .add(ModItems.COTTON_BOLL.getId(), new Compostable(0.5f, true), false)
                .add(ModItems.POTATO_SLICES.getId(), new Compostable(0.5f, true), false)
                .add(ModItems.COFFEE_BEANS.getId(), new Compostable(0.5f, true), false)
                .add(ModItems.BELL_PEPPER_SLICE_GREEN.getId(), new Compostable(0.5f, true), false)
                .add(ModItems.BELL_PEPPER_SLICE_YELLOW.getId(), new Compostable(0.5f, true), false)
                .add(ModItems.BELL_PEPPER_SLICE_RED.getId(), new Compostable(0.5f, true), false)
                .add(ModItems.BELL_PEPPER_SLICE_ORANGE.getId(), new Compostable(0.5f, true), false)
                .add(ModItems.BELL_PEPPER_SLICE_WHITE.getId(), new Compostable(0.5f, true), false)
                .add(ModItems.BELL_PEPPER_SLICE_PINK.getId(), new Compostable(0.5f, true), false)
                .add(ModItems.BELL_PEPPER_SLICE_BLUE.getId(), new Compostable(0.5f, true), false)
                .add(ModItems.BELL_PEPPER_SLICE_PURPLE.getId(), new Compostable(0.5f, true), false)
                .add(ModItems.BELL_PEPPER_SLICE_BLACK.getId(), new Compostable(0.5f, true), false)

                // 65% chance
                .add(ModItems.BELL_PEPPER_GREEN.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_YELLOW.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_RED.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_ORANGE.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_WHITE.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_PINK.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_BLUE.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_PURPLE.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_BLACK.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.WILD_COFFEE.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.WILD_COTTON.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.WILD_BELL_PEPPERS.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.WILD_PALE_BELL_PEPPERS.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.WILD_DARK_BELL_PEPPERS.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.ROASTED_COFFEE_BEANS.getId(), new Compostable(0.65f, true), false)

                // Bell pepper blocks (65%)
                .add(ModItems.BELL_PEPPER_GREEN_BLOCK.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_YELLOW_BLOCK.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_RED_BLOCK.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_ORANGE_BLOCK.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_WHITE_BLOCK.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_PINK_BLOCK.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_BLUE_BLOCK.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_PURPLE_BLOCK.getId(), new Compostable(0.65f, true), false)
                .add(ModItems.BELL_PEPPER_BLACK_BLOCK.getId(), new Compostable(0.65f, true), false)

                // 85% chance
                .add(ModItems.COFFEE_COOKIE.getId(), new Compostable(0.85f, true), false)
                .add(ModItems.SYRUP_COOKIE.getId(), new Compostable(0.85f, true), false)
                .add(ModItems.CHERRY_BLOSSOM_COOKIE.getId(), new Compostable(0.85f, true), false)
                .add(ModItems.SYRUP_CHEESECAKE_SLICE.getId(), new Compostable(0.85f, true), false)
                .add(ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE.getId(), new Compostable(0.85f, true), false)
                .add(ModItems.COFFEE_CHEESECAKE_SLICE.getId(), new Compostable(0.85f, true), false)

                // 100% chance
                .add(ModItems.SYRUP_CHEESECAKE.getId(), new Compostable(1.0f, true), false)
                .add(ModItems.CHERRY_BLOSSOM_CHEESECAKE.getId(), new Compostable(1.0f, true), false)
                .add(ModItems.COFFEE_CHEESECAKE.getId(), new Compostable(1.0f, true), false)
        ;
    }
}

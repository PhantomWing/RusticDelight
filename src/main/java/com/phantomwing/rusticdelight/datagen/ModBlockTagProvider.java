package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.tag.CommonTags;
import com.phantomwing.rusticdelight.tag.CompatibilityTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        addModTags();
        addMinecraftTags();
        addFabricTags();
        addCommonTags();
        addCompatibilityTags();
    }

    private void addModTags() {
    }

    private void addMinecraftTags() {
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(
                        ModBlocks.COTTON_BOLL_CRATE,
                        ModBlocks.BELL_PEPPER_GREEN_CRATE,
                        ModBlocks.BELL_PEPPER_YELLOW_CRATE,
                        ModBlocks.BELL_PEPPER_RED_CRATE
                );

        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(
                ModBlocks.WILD_COTTON,
                ModBlocks.WILD_BELL_PEPPERS,
                ModBlocks.WILD_COFFEE
        );

        getOrCreateTagBuilder(BlockTags.CROPS)
                .add(
                        ModBlocks.COTTON_CROP,
                        ModBlocks.BELL_PEPPER_CROP,
                        ModBlocks.COFFEE_CROP
                );
    }

    private void addFabricTags() {

    }

    private void addCommonTags() {
        getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS).add(
                ModBlocks.COTTON_SEEDS_BAG,
                ModBlocks.COTTON_BOLL_CRATE,
                ModBlocks.BELL_PEPPER_GREEN_CRATE,
                ModBlocks.BELL_PEPPER_YELLOW_CRATE,
                ModBlocks.BELL_PEPPER_RED_CRATE,
                ModBlocks.COFFEE_BEANS_BAG
        );
    }

    private void addCompatibilityTags() {
        // Farmers Delight
        getOrCreateTagBuilder(ModTags.STRAW_BLOCKS).add(
                ModBlocks.COTTON_SEEDS_BAG,
                ModBlocks.BELL_PEPPER_SEEDS_BAG,
                ModBlocks.COFFEE_BEANS_BAG
        );
        getOrCreateTagBuilder(ModTags.WILD_CROPS).add(
                ModBlocks.WILD_COTTON,
                ModBlocks.WILD_BELL_PEPPERS,
                ModBlocks.WILD_COFFEE
        );

        // Serene Seasons
        getOrCreateTagBuilder(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS_BLOCK).add(
                ModBlocks.COTTON_CROP
        );
        getOrCreateTagBuilder(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS_BLOCK).add(
                ModBlocks.COTTON_CROP,
                ModBlocks.BELL_PEPPER_CROP,
                ModBlocks.COFFEE_CROP
        );
        getOrCreateTagBuilder(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS_BLOCK).add(
                ModBlocks.BELL_PEPPER_CROP,
                ModBlocks.COFFEE_CROP
        );
    }
}

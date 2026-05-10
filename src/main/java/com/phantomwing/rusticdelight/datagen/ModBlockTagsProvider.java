package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.tags.CommonTags;
import com.phantomwing.rusticdelight.tags.CompatibilityTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        addMinecraftTags();
        addCommonTags();
        addCompatibilityTags();
        addModTags();
    }

    private void addModTags() {
    }

    private void addMinecraftTags() {
        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(
                        ModBlocks.COTTON_BOLL_CRATE,
                        ModBlocks.BELL_PEPPER_GREEN_CRATE,
                        ModBlocks.BELL_PEPPER_YELLOW_CRATE,
                        ModBlocks.BELL_PEPPER_RED_CRATE
                );

        this.valueLookupBuilder(BlockTags.SMALL_FLOWERS).add(
            ModBlocks.WILD_COTTON,
            ModBlocks.WILD_BELL_PEPPERS,
            ModBlocks.WILD_COFFEE
        );

        this.valueLookupBuilder(BlockTags.CROPS)
                .add(
                        ModBlocks.COTTON_CROP,
                        ModBlocks.BELL_PEPPER_CROP,
                        ModBlocks.COFFEE_CROP
                );
    }

    private void addCommonTags() {
        // Storage blocks
        this.valueLookupBuilder(CommonTags.STORAGE_BLOCKS_COTTON_SEEDS).add(
                ModBlocks.COTTON_SEEDS_BAG
        );
        this.valueLookupBuilder(CommonTags.STORAGE_BLOCKS_COTTON).add(
                ModBlocks.COTTON_BOLL_CRATE
        );
        this.valueLookupBuilder(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_SEEDS).add(
                ModBlocks.BELL_PEPPER_SEEDS_BAG
        );
        this.valueLookupBuilder(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_GREEN).add(
                ModBlocks.BELL_PEPPER_GREEN_CRATE
        );
        this.valueLookupBuilder(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_YELLOW).add(
                ModBlocks.BELL_PEPPER_YELLOW_CRATE
        );
        this.valueLookupBuilder(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_RED).add(
                ModBlocks.BELL_PEPPER_RED_CRATE
        );
        this.valueLookupBuilder(CommonTags.STORAGE_BLOCKS_COFFEE_BEANS).add(
                ModBlocks.COFFEE_BEANS_BAG
        );
        this.valueLookupBuilder(CommonTags.STORAGE_BLOCKS_ROASTED_COFFEE_BEANS).add(
                ModBlocks.ROASTED_COFFEE_BEANS_BAG
        );

        // Duplicate tags
        this.valueLookupBuilder(CommonTags.STORAGE_BLOCKS_COFFEE).add(
                ModBlocks.COFFEE_BEANS_BAG
        );

        // Main storage block tag
        this.valueLookupBuilder(ConventionalBlockTags.STORAGE_BLOCKS)
                .addTag(CommonTags.STORAGE_BLOCKS_COTTON_SEEDS)
                .addTag(CommonTags.STORAGE_BLOCKS_COTTON)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_SEEDS)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_GREEN)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_YELLOW)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_RED)
                .addTag(CommonTags.STORAGE_BLOCKS_COFFEE_BEANS)
                .addTag(CommonTags.STORAGE_BLOCKS_COFFEE)
                .addTag(CommonTags.STORAGE_BLOCKS_ROASTED_COFFEE_BEANS);
    }

    private void addCompatibilityTags() {
        // Farmers Delight
        this.valueLookupBuilder(ModTags.Blocks.STRAW_BLOCKS).add(
                ModBlocks.COTTON_SEEDS_BAG,
                ModBlocks.BELL_PEPPER_SEEDS_BAG,
                ModBlocks.COFFEE_BEANS_BAG,
                ModBlocks.ROASTED_COFFEE_BEANS_BAG
        );
        this.valueLookupBuilder(ModTags.Blocks.WILD_CROPS).add(
                ModBlocks.WILD_COTTON,
                ModBlocks.WILD_BELL_PEPPERS,
                ModBlocks.WILD_COFFEE
        );

        // Serene Seasons
        this.valueLookupBuilder(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS_BLOCK).add(
                ModBlocks.COTTON_CROP
        );
        this.valueLookupBuilder(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS_BLOCK).add(
                ModBlocks.COTTON_CROP,
                ModBlocks.BELL_PEPPER_CROP,
                ModBlocks.COFFEE_CROP
        );
        this.valueLookupBuilder(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS_BLOCK).add(
                ModBlocks.BELL_PEPPER_CROP,
                ModBlocks.COFFEE_CROP
        );
    }
}

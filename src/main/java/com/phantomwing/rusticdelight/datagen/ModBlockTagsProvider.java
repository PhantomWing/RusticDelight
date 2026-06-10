package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.tags.CommonTags;
import com.phantomwing.rusticdelight.tags.CompatibilityTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, RusticDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addModTags();
        addMinecraftTags();
        addCommonTags();
        addCompatibilityTags();
    }

    private void addModTags() {
    }

    private void addMinecraftTags() {
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(
                        ModBlocks.COTTON_BOLL_CRATE.get(),
                        ModBlocks.BELL_PEPPER_GREEN_CRATE.get(),
                        ModBlocks.BELL_PEPPER_YELLOW_CRATE.get(),
                        ModBlocks.BELL_PEPPER_RED_CRATE.get(),
                        ModBlocks.CALAMARI_CRATE.get(),
                        ModBlocks.BELL_PEPPER_GREEN_BLOCK.get(),
                        ModBlocks.BELL_PEPPER_YELLOW_BLOCK.get(),
                        ModBlocks.BELL_PEPPER_RED_BLOCK.get()
                );

        this.tag(BlockTags.SMALL_FLOWERS).add(
                ModBlocks.WILD_COTTON.get(),
                ModBlocks.WILD_BELL_PEPPERS.get(),
                ModBlocks.WILD_COFFEE.get()
        );

        this.tag(BlockTags.CROPS)
                .add(
                        ModBlocks.COTTON_CROP.get(),
                        ModBlocks.BELL_PEPPER_CROP.get(),
                        ModBlocks.COFFEE_CROP.get()
                );
    }

    private void addCommonTags() {
        // Storage blocks
        this.tag(CommonTags.STORAGE_BLOCKS_COTTON_SEEDS).add(
                ModBlocks.COTTON_SEEDS_BAG.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_COTTON).add(
                ModBlocks.COTTON_BOLL_CRATE.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_SEEDS).add(
                ModBlocks.BELL_PEPPER_SEEDS_BAG.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_GREEN).add(
                ModBlocks.BELL_PEPPER_GREEN_CRATE.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_YELLOW).add(
                ModBlocks.BELL_PEPPER_YELLOW_CRATE.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_RED).add(
                ModBlocks.BELL_PEPPER_RED_CRATE.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_CALAMARI).add(
                ModBlocks.CALAMARI_CRATE.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_COFFEE_BEANS).add(
                ModBlocks.COFFEE_BEANS_BAG.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_ROASTED_COFFEE_BEANS).add(
                ModBlocks.ROASTED_COFFEE_BEANS_BAG.get()
        );

        // Duplicate tags
        this.tag(CommonTags.STORAGE_BLOCKS_COFFEE).add(
                ModBlocks.COFFEE_BEANS_BAG.get()
        );

        // Main storage block tag
        this.tag(Tags.Blocks.STORAGE_BLOCKS)
                .addTag(CommonTags.STORAGE_BLOCKS_COTTON_SEEDS)
                .addTag(CommonTags.STORAGE_BLOCKS_COTTON)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_SEEDS)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_GREEN)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_YELLOW)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_RED)
                .addTag(CommonTags.STORAGE_BLOCKS_CALAMARI)
                .addTag(CommonTags.STORAGE_BLOCKS_COFFEE_BEANS)
                .addTag(CommonTags.STORAGE_BLOCKS_COFFEE)
                .addTag(CommonTags.STORAGE_BLOCKS_ROASTED_COFFEE_BEANS);
    }

    private void addCompatibilityTags() {
        // Farmers Delight
        this.tag(ModTags.Blocks.STRAW_BLOCKS).add(
                ModBlocks.COTTON_SEEDS_BAG.get(),
                ModBlocks.BELL_PEPPER_SEEDS_BAG.get(),
                ModBlocks.COFFEE_BEANS_BAG.get(),
                ModBlocks.ROASTED_COFFEE_BEANS_BAG.get()
        );
        this.tag(ModTags.Blocks.WILD_CROPS).add(
                ModBlocks.WILD_COTTON.get(),
                ModBlocks.WILD_BELL_PEPPERS.get(),
                ModBlocks.WILD_COFFEE.get()
        );

        // Serene Seasons
        tag(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS_BLOCK).add(
                ModBlocks.COTTON_CROP.get()
        );
        tag(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS_BLOCK).add(
                ModBlocks.COTTON_CROP.get(),
                ModBlocks.BELL_PEPPER_CROP.get(),
                ModBlocks.COFFEE_CROP.get()
        );
        tag(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS_BLOCK).add(
                ModBlocks.BELL_PEPPER_CROP.get(),
                ModBlocks.COFFEE_CROP.get()
        );
    }
}

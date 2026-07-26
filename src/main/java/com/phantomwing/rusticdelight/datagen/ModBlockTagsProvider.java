package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.tags.CommonTags;
import com.phantomwing.rusticdelight.tags.CompatibilityTags;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

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
        this.tag(ModTags.Blocks.PANCAKES).add(
                ModBlocks.PANCAKES.get(),
                ModBlocks.HONEY_PANCAKES.get(),
                ModBlocks.CHOCOLATE_PANCAKES.get(),
                ModBlocks.CHERRY_BLOSSOM_PANCAKES.get(),
                ModBlocks.VEGETABLE_PANCAKES.get(),
                ModBlocks.PUMPKIN_PANCAKES.get(),
                ModBlocks.COFFEE_PANCAKES.get()
        );
    }

    private void addMinecraftTags() {
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(
                        ModBlocks.COTTON_BOLL_CRATE.get(),
                        ModBlocks.BELL_PEPPER_GREEN_CRATE.get(),
                        ModBlocks.BELL_PEPPER_YELLOW_CRATE.get(),
                        ModBlocks.BELL_PEPPER_RED_CRATE.get(),
                        ModBlocks.BELL_PEPPER_ORANGE_CRATE.get(),
                        ModBlocks.BELL_PEPPER_WHITE_CRATE.get(),
                        ModBlocks.BELL_PEPPER_PINK_CRATE.get(),
                        ModBlocks.BELL_PEPPER_BLUE_CRATE.get(),
                        ModBlocks.BELL_PEPPER_PURPLE_CRATE.get(),
                        ModBlocks.BELL_PEPPER_BLACK_CRATE.get(),
                        ModBlocks.CALAMARI_CRATE.get(),
                        ModBlocks.BELL_PEPPER_GREEN_BLOCK.get(),
                        ModBlocks.BELL_PEPPER_YELLOW_BLOCK.get(),
                        ModBlocks.BELL_PEPPER_RED_BLOCK.get(),
                        ModBlocks.BELL_PEPPER_ORANGE_BLOCK.get(),
                        ModBlocks.BELL_PEPPER_WHITE_BLOCK.get(),
                        ModBlocks.BELL_PEPPER_PINK_BLOCK.get(),
                        ModBlocks.BELL_PEPPER_BLUE_BLOCK.get(),
                        ModBlocks.BELL_PEPPER_PURPLE_BLOCK.get(),
                        ModBlocks.BELL_PEPPER_BLACK_BLOCK.get()
                );

        this.tag(BlockTags.SMALL_FLOWERS).add(
                ModBlocks.WILD_COTTON.get(),
                ModBlocks.WILD_BELL_PEPPERS.get(),
                ModBlocks.WILD_PALE_BELL_PEPPERS.get(),
                ModBlocks.WILD_DARK_BELL_PEPPERS.get(),
                ModBlocks.WILD_COFFEE.get()
        );

        this.tag(BlockTags.CROPS)
                .add(
                        ModBlocks.COTTON_CROP.get(),
                        ModBlocks.BELL_PEPPER_CROP.get(),
                        ModBlocks.PALE_BELL_PEPPER_CROP.get(),
                        ModBlocks.DARK_BELL_PEPPER_CROP.get(),
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
        this.tag(CommonTags.STORAGE_BLOCKS_PALE_BELL_PEPPER_SEEDS).add(
                ModBlocks.PALE_BELL_PEPPER_SEEDS_BAG.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_DARK_BELL_PEPPER_SEEDS).add(
                ModBlocks.DARK_BELL_PEPPER_SEEDS_BAG.get()
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
        this.tag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_ORANGE).add(
                ModBlocks.BELL_PEPPER_ORANGE_CRATE.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_WHITE).add(
                ModBlocks.BELL_PEPPER_WHITE_CRATE.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_PINK).add(
                ModBlocks.BELL_PEPPER_PINK_CRATE.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_BLUE).add(
                ModBlocks.BELL_PEPPER_BLUE_CRATE.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_PURPLE).add(
                ModBlocks.BELL_PEPPER_PURPLE_CRATE.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_BLACK).add(
                ModBlocks.BELL_PEPPER_BLACK_CRATE.get()
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
                .addTag(CommonTags.STORAGE_BLOCKS_PALE_BELL_PEPPER_SEEDS)
                .addTag(CommonTags.STORAGE_BLOCKS_DARK_BELL_PEPPER_SEEDS)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_GREEN)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_YELLOW)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_RED)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_ORANGE)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_WHITE)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_PINK)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_BLUE)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_PURPLE)
                .addTag(CommonTags.STORAGE_BLOCKS_BELL_PEPPER_BLACK)
                .addTag(CommonTags.STORAGE_BLOCKS_CALAMARI)
                .addTag(CommonTags.STORAGE_BLOCKS_COFFEE_BEANS)
                .addTag(CommonTags.STORAGE_BLOCKS_COFFEE)
                .addTag(CommonTags.STORAGE_BLOCKS_ROASTED_COFFEE_BEANS);
    }

    private void addCompatibilityTags() {
        // Farmers Delight
        this.tag(vectorwing.farmersdelight.common.tag.ModTags.Blocks.STRAW_BLOCKS).add(
                ModBlocks.COTTON_SEEDS_BAG.get(),
                ModBlocks.BELL_PEPPER_SEEDS_BAG.get(),
                ModBlocks.PALE_BELL_PEPPER_SEEDS_BAG.get(),
                ModBlocks.DARK_BELL_PEPPER_SEEDS_BAG.get(),
                ModBlocks.COFFEE_BEANS_BAG.get(),
                ModBlocks.ROASTED_COFFEE_BEANS_BAG.get()
        );
        this.tag(vectorwing.farmersdelight.common.tag.ModTags.Blocks.WILD_CROPS).add(
                ModBlocks.WILD_COTTON.get(),
                ModBlocks.WILD_BELL_PEPPERS.get(),
                ModBlocks.WILD_PALE_BELL_PEPPERS.get(),
                ModBlocks.WILD_DARK_BELL_PEPPERS.get(),
                ModBlocks.WILD_COFFEE.get()
        );

        // Serene Seasons
        tag(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS_BLOCK).add(
                ModBlocks.COTTON_CROP.get()
        );
        tag(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS_BLOCK).add(
                ModBlocks.COTTON_CROP.get(),
                ModBlocks.BELL_PEPPER_CROP.get(),
                ModBlocks.PALE_BELL_PEPPER_CROP.get(),
                ModBlocks.DARK_BELL_PEPPER_CROP.get(),
                ModBlocks.COFFEE_CROP.get()
        );
        tag(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS_BLOCK).add(
                ModBlocks.BELL_PEPPER_CROP.get(),
                ModBlocks.PALE_BELL_PEPPER_CROP.get(),
                ModBlocks.DARK_BELL_PEPPER_CROP.get(),
                ModBlocks.COFFEE_CROP.get()
        );
    }
}

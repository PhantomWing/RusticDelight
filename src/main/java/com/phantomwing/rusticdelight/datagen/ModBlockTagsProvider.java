package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
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
        addNeoForgeTags();
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
                        ModBlocks.BELL_PEPPER_RED_CRATE.get()
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

    private void addNeoForgeTags() {
        this.tag(Tags.Blocks.STORAGE_BLOCKS).add(
                ModBlocks.COTTON_SEEDS_BAG.get(),
                ModBlocks.COTTON_BOLL_CRATE.get(),
                ModBlocks.BELL_PEPPER_GREEN_CRATE.get(),
                ModBlocks.BELL_PEPPER_YELLOW_CRATE.get(),
                ModBlocks.BELL_PEPPER_RED_CRATE.get(),
                ModBlocks.COFFEE_BEANS_BAG.get()
        );
    }

    private void addCommonTags() {
    }

    private void addCompatibilityTags() {
        // Farmers Delight
        this.tag(ModTags.STRAW_BLOCKS).add(
                ModBlocks.COTTON_SEEDS_BAG.get(),
                ModBlocks.BELL_PEPPER_SEEDS_BAG.get(),
                ModBlocks.COFFEE_BEANS_BAG.get()
        );
        this.tag(ModTags.WILD_CROPS).add(
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

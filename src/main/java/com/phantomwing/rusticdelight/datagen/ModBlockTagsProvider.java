package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.BlockManager;
import com.phantomwing.rusticdelight.tags.CompatibilityTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
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
                        BlockManager.COTTON_BOLL_CRATE.get()
                );

        this.tag(BlockTags.SMALL_FLOWERS).add(
                BlockManager.WILD_COTTON.get()
        );

        this.tag(BlockTags.CROPS)
                .add(
                        BlockManager.COTTON_CROP.get()
                );
    }

    private void addCommonTags() {
    }

    private void addCompatibilityTags() {
        // Farmers Delight
        this.tag(ModTags.STRAW_BLOCKS).add(
                BlockManager.COTTON_SEEDS_BAG.get()
        );
        this.tag(ModTags.WILD_CROPS).add(
                BlockManager.WILD_COTTON.get()
        );

        // Serene Seasons
        tag(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS_BLOCK).add(
                BlockManager.COTTON_CROP.get()
        );
        tag(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS_BLOCK).add(
                BlockManager.COTTON_CROP.get()
        );
    }
}

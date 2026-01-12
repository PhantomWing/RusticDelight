package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends BiomeTagsProvider {
    public ModBiomeTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addModTags();
    }

    private void addModTags() {
        this.tag(ModTags.Biomes.HAS_WILD_COTTON)
            .addOptionalTag(BiomeTags.IS_FOREST)
            .addOptionalTag(ConventionalBiomeTags.IS_FOREST);

        this.tag(ModTags.Biomes.HAS_WILD_COFFEE)
            .addOptionalTag(BiomeTags.IS_JUNGLE)
            .addOptionalTag(ConventionalBiomeTags.IS_JUNGLE);

        this.tag(ModTags.Biomes.HAS_WILD_BELL_PEPPERS)
            .addOptionalTag(BiomeTags.IS_JUNGLE)
            .addOptionalTag(ConventionalBiomeTags.IS_JUNGLE);
    }
}

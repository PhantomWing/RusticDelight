package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends BiomeTagsProvider {
    public ModBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, RusticDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addModTags();
    }

    private void addModTags() {
        this.tag(ModTags.Biomes.HAS_WILD_COTTON)
                .addTag(BiomeTags.IS_FOREST);

        this.tag(ModTags.Biomes.HAS_WILD_COFFEE)
                .addTag(BiomeTags.IS_JUNGLE);

        this.tag(ModTags.Biomes.HAS_WILD_BELL_PEPPERS)
                .addTag(BiomeTags.IS_JUNGLE);
    }
}
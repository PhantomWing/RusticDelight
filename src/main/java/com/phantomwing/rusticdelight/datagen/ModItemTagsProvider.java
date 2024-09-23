package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tags.ForgeTags;
import com.phantomwing.rusticdelight.tags.CompatibilityTags;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, RusticDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        copy(vectorwing.farmersdelight.common.tag.ModTags.WILD_CROPS, vectorwing.farmersdelight.common.tag.ModTags.WILD_CROPS_ITEM);
        copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);

        addModTags();
        addMinecraftTags();
        addForgeTags();
        addCompatibilityTags();
    }

    private void addModTags() {
        this.tag(ModTags.Items.COOKING_OIL_INGREDIENTS).add(
                ModItems.COTTON_SEEDS.get()
        );
    }

    private void addMinecraftTags() {
        this.tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(
                ModItems.COTTON_SEEDS.get(),
                ModItems.BELL_PEPPER_SEEDS.get()
        );

        this.tag(ItemTags.FISHES).add(
                ModItems.CALAMARI.get(),
                ModItems.COOKED_CALAMARI.get()
        );
    }

    private void addForgeTags() {
        // Crops
        this.tag(ForgeTags.CROPS)
                .addTag(ForgeTags.CROPS_COTTON)
                .addTag(ForgeTags.CROPS_BELL_PEPPER);
        this.tag(ForgeTags.CROPS_COTTON).add(
                ModItems.COTTON_BOLL.get()
        );
        this.tag(ForgeTags.CROPS_BELL_PEPPER).add(
                ModItems.BELL_PEPPER_GREEN.get(),
                ModItems.BELL_PEPPER_YELLOW.get(),
                ModItems.BELL_PEPPER_RED.get()
        );

        // Fish
        this.tag(ForgeTags.RAW_FISHES).addTag(
                ForgeTags.RAW_FISHES_CALAMARI
        );
        this.tag(ForgeTags.COOKED_FISHES).addTag(
                ForgeTags.COOKED_FISHES_CALAMARI
        );
        this.tag(ForgeTags.RAW_FISHES_CALAMARI).add(
                ModItems.CALAMARI.get(),
                ModItems.CALAMARI_SLICE.get()
        );
        this.tag(ForgeTags.COOKED_FISHES_CALAMARI).add(
                ModItems.COOKED_CALAMARI.get(),
                ModItems.COOKED_CALAMARI_SLICE.get()
        );

        this.tag(Tags.Items.SEEDS).add(
                ModItems.COTTON_SEEDS.get(),
                ModItems.BELL_PEPPER_SEEDS.get()
        );

        // Vegetables
        this.tag(ForgeTags.VEGETABLES)
                .addTag(ForgeTags.VEGETABLES_POTATO)
                .addTag(ForgeTags.VEGETABLES_BELL_PEPPER);
        this.tag(ForgeTags.VEGETABLES_POTATO).add(
                Items.POTATO,
                ModItems.POTATO_SLICES.get()
        );
        this.tag(ForgeTags.VEGETABLES_BELL_PEPPER).addTag(
                ForgeTags.CROPS_BELL_PEPPER
        );
    }

    private void addCompatibilityTags() {
        this.tag(CompatibilityTags.CREATE_UPRIGHT_ON_BELT).add(
                ModItems.COOKING_OIL.get()
        );

        this.tag(vectorwing.farmersdelight.common.tag.ModTags.CABBAGE_ROLL_INGREDIENTS).add(
                ModItems.POTATO_SLICES.get()
        );
    }
}
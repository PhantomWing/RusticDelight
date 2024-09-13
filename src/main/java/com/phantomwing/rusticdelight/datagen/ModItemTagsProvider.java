package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tags.CommonTags;
import com.phantomwing.rusticdelight.tags.CompatibilityTags;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

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
        addNeoForgeTags();
        addCommonTags();
        addCompatibilityTags();
    }

    private void addModTags() {
        this.tag(ModTags.Items.COOKING_OIL_INGREDIENTS).add(
                ModItems.COTTON_SEEDS.get()
        );
    }

    private void addMinecraftTags() {
        this.tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(
                ModItems.COTTON_SEEDS.get()
        );
        this.tag(ItemTags.CHICKEN_FOOD)
                .add(ModItems.COTTON_SEEDS.get()
                );
        this.tag(ItemTags.PARROT_FOOD)
                .add(ModItems.COTTON_SEEDS.get()
                );
        this.tag(ItemTags.CAT_FOOD).add(
                ModItems.CALAMARI.get()
        );
        this.tag(ItemTags.OCELOT_FOOD).add(
                ModItems.CALAMARI.get()
        );
        this.tag(ItemTags.FISHES).add(
                ModItems.CALAMARI.get(),
                ModItems.COOKED_CALAMARI.get()
        );
    }

    private void addNeoForgeTags() {
        this.tag(Tags.Items.FOODS_RAW_FISH).addTag(
                CommonTags.FOODS_RAW_CALAMARI
        );
        this.tag(Tags.Items.FOODS_COOKED_FISH).addTag(
                CommonTags.FOODS_COOKED_CALAMARI
        );
        this.tag(Tags.Items.SEEDS).add(
                ModItems.COTTON_SEEDS.get()
        );
        this.tag(Tags.Items.FOODS_FRUIT).add(
                Items.MELON_SLICE
        ).addTag(Tags.Items.FOODS_BERRY);
    }

    private void addCommonTags() {
        this.tag(CommonTags.FOODS_RAW_CALAMARI).add(
                ModItems.CALAMARI.get(),
                ModItems.CALAMARI_SLICE.get()
        );
        this.tag(CommonTags.FOODS_COOKED_CALAMARI).add(
                ModItems.COOKED_CALAMARI.get(),
                ModItems.COOKED_CALAMARI_SLICE.get()
        );
        this.tag(CommonTags.FOODS_POTATO).add(
                Items.POTATO,
                ModItems.POTATO_SLICES.get()
        );
    }

    private void addCompatibilityTags() {
        this.tag(CompatibilityTags.CREATE_UPRIGHT_ON_BELT).add(
                ModItems.COOKING_OIL.get()
        );
    }
}

package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.item.ItemManager;
import com.phantomwing.rusticdelight.tags.CommonTags;
import com.phantomwing.rusticdelight.tags.CompatibilityTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, RusticDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        copy(ModTags.WILD_CROPS, ModTags.WILD_CROPS_ITEM);
        copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);

        addModTags();
        addMinecraftTags();
        addNeoForgeTags();
        addCommonTags();
        addCompatibilityTags();
    }

    private void addModTags() {
    }

    private void addMinecraftTags() {
        this.tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(
                ItemManager.COTTON_SEEDS.get()
        );
        this.tag(ItemTags.CHICKEN_FOOD)
                .add(ItemManager.COTTON_SEEDS.get()
                );
        this.tag(ItemTags.PARROT_FOOD)
                .add(ItemManager.COTTON_SEEDS.get()
                );
        this.tag(ItemTags.CAT_FOOD).add(
                ItemManager.CALAMARI.get()
        );
        this.tag(ItemTags.OCELOT_FOOD).add(
                ItemManager.CALAMARI.get()
        );
        this.tag(ItemTags.FISHES).add(
                ItemManager.CALAMARI.get(),
                ItemManager.COOKED_CALAMARI.get()
        );
    }

    private void addNeoForgeTags() {
        this.tag(Tags.Items.FOODS_RAW_FISH).addTags(
                CommonTags.FOODS_RAW_CALAMARI
        );
        this.tag(Tags.Items.FOODS_COOKED_FISH).addTags(
                CommonTags.FOODS_COOKED_CALAMARI
        );

        this.tag(Tags.Items.SEEDS).add(
                ItemManager.COTTON_SEEDS.get()
        );
    }

    private void addCommonTags() {
        // Cotton
        this.tag(CommonTags.COOKING_OIL_INGREDIENTS).add(
                ItemManager.COTTON_SEEDS.get()
        );

        this.tag(CommonTags.FOODS_RAW_CALAMARI).add(
                ItemManager.CALAMARI.get(),
                ItemManager.CALAMARI_SLICE.get()
        );

        this.tag(CommonTags.FOODS_COOKED_CALAMARI).add(
                ItemManager.COOKED_CALAMARI.get(),
                ItemManager.COOKED_CALAMARI_SLICE.get()
        );
    }

    private void addCompatibilityTags() {
        this.tag(CompatibilityTags.CREATE_UPRIGHT_ON_BELT).add(
                ItemManager.COOKING_OIL.get()
        );

    }

}

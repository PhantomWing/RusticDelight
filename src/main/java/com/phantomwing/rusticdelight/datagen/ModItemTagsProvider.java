package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tags.CommonTags;
import com.phantomwing.rusticdelight.tags.CompatibilityTags;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
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
        ).addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.FRYCOOKS_DELIGHT, "canola_seeds"));

        this.tag(ModTags.Items.COOKING_OIL).add(
                ModItems.COOKING_OIL.get()
        ).addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.FRYCOOKS_DELIGHT, "canola_oil"));

        this.tag(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS).add(
                Items.PINK_PETALS,
                Items.CHERRY_LEAVES,
                Items.CHERRY_SAPLING
        );

        this.tag(ModTags.Items.CALAMARI_ROLL_INGREDIENTS).add(
                ModItems.CALAMARI_SLICE.get()
        ).addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.MINERS_DELIGHT, "tentacles"));
    }

    private void addMinecraftTags() {
        this.tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(
                ModItems.COTTON_SEEDS.get(),
                ModItems.BELL_PEPPER_SEEDS.get(),
                ModItems.COFFEE_BEANS.get()
        );
        this.tag(ItemTags.CHICKEN_FOOD).add(
                ModItems.COTTON_SEEDS.get(),
                ModItems.BELL_PEPPER_SEEDS.get()
        );
        this.tag(ItemTags.PARROT_FOOD).add(
                ModItems.COTTON_SEEDS.get(),
                ModItems.BELL_PEPPER_SEEDS.get()
        );
        this.tag(ItemTags.CAT_FOOD).add(
                ModItems.CALAMARI.get()
        );
        this.tag(ItemTags.OCELOT_FOOD).add(
                ModItems.CALAMARI.get()
        );
        this.tag(ItemTags.PIG_FOOD).addTag(
                CommonTags.CROPS_BELL_PEPPER
        );
        this.tag(ItemTags.FISHES).add(
                ModItems.CALAMARI.get(),
                ModItems.COOKED_CALAMARI.get()
        );
    }

    private void addNeoForgeTags() {
        this.tag(Tags.Items.CROPS)
                .addTag(CommonTags.CROPS_COTTON)
                .addTag(CommonTags.CROPS_BELL_PEPPER)
                .addTag(CommonTags.CROPS_COFFEE);

        this.tag(Tags.Items.FOODS_RAW_FISH).addTag(
                CommonTags.FOODS_RAW_CALAMARI
        );
        this.tag(Tags.Items.FOODS_COOKED_FISH).addTag(
                CommonTags.FOODS_COOKED_CALAMARI
        );
        this.tag(Tags.Items.SEEDS).add(
                ModItems.COTTON_SEEDS.get(),
                ModItems.BELL_PEPPER_SEEDS.get(),
                ModItems.COFFEE_BEANS.get()
        );
        this.tag(Tags.Items.FOODS_FRUIT).add(
                Items.MELON_SLICE
        ).addTag(Tags.Items.FOODS_BERRY);
        this.tag(Tags.Items.FOODS_VEGETABLE).add(
                ModItems.POTATO_SLICES.get()
        ).addTag(CommonTags.FOODS_BELL_PEPPER);
    }

    private void addCommonTags() {
        // Crops
        this.tag(CommonTags.CROPS_COTTON).add(
                ModItems.COTTON_BOLL.get()
        );
        this.tag(CommonTags.CROPS_BELL_PEPPER).add(
                ModItems.BELL_PEPPER_GREEN.get(),
                ModItems.BELL_PEPPER_YELLOW.get(),
                ModItems.BELL_PEPPER_RED.get()
        );
        this.tag(CommonTags.CROPS_COFFEE).add(
                ModItems.COFFEE_BEANS.get()
        );

        // Foods
        this.tag(CommonTags.FOODS_BELL_PEPPER).addTag(
                CommonTags.CROPS_BELL_PEPPER
        );
        this.tag(CommonTags.FOODS_RAW_CALAMARI)
                .add(
                    ModItems.CALAMARI.get(),
                    ModItems.CALAMARI_SLICE.get()
                )
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.CULTURAL_DELIGHTS,"squid"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.CULTURAL_DELIGHTS,"glow_squid"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.CULTURAL_DELIGHTS,"raw_calamari"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.MINERS_DELIGHT,"squid"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.MINERS_DELIGHT,"glow_squid"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.MINERS_DELIGHT,"tentacles"));

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
        // Create
        this.tag(CompatibilityTags.CREATE_UPRIGHT_ON_BELT).add(
                ModItems.COOKING_OIL.get(),
                ModItems.BLACK_COFFEE.get(),
                ModItems.CHERRY_BLOSSOM_CHEESECAKE.get()
        );

        // Farmer's Delight
        this.tag(vectorwing.farmersdelight.common.tag.ModTags.CABBAGE_ROLL_INGREDIENTS).add(
                ModItems.POTATO_SLICES.get()
        );

        // Serene Seasons
        tag(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS).add(
                ModItems.COTTON_SEEDS.get()
        );
        tag(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS).add(
                ModItems.COTTON_SEEDS.get(),
                ModItems.BELL_PEPPER_SEEDS.get(),
                ModItems.COFFEE_BEANS.get()
        );
        tag(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS).add(
                ModItems.BELL_PEPPER_SEEDS.get(),
                ModItems.COFFEE_BEANS.get()
        );

        // Miner's Delight
        this.tag(CompatibilityTags.MINERS_DELIGHT_TENTACLES).add(
                ModItems.CALAMARI.get(),
                ModItems.CALAMARI_SLICE.get(),
                ModItems.COOKED_CALAMARI.get(),
                ModItems.COOKED_CALAMARI_SLICE.get()
        );

        // Frycook's Delight
        this.tag(CompatibilityTags.FISH_SLICES).add(
                ModItems.CALAMARI_SLICE.get()
        );
        this.tag(CompatibilityTags.HAS_FISH_SLICE).add(
                ModItems.CALAMARI.get()
        );
    }
}

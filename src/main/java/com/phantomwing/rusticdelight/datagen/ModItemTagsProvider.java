package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tags.ForgeTags;
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
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, RusticDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        copy(vectorwing.farmersdelight.common.tag.ModTags.Blocks.WILD_CROPS, vectorwing.farmersdelight.common.tag.ModTags.Items.WILD_CROPS);
        copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);

        addModTags();
        addMinecraftTags();
        addForgeTags();
        addCompatibilityTags();
    }

    private void addModTags() {
        // Cooking oil
        this.tag(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .add(ModItems.COTTON_SEEDS.get(), Items.PUMPKIN_SEEDS)
                .addOptionalTag(ForgeTags.SEEDS_CANOLA)
                .addOptionalTag(ForgeTags.SEEDS_SUNFLOWER)
                .addOptional(new ResourceLocation(CompatibilityTags.FRYCOOKS_DELIGHT, "canola_seeds"));;

        this.tag(ModTags.Items.COOKING_OIL).add(
                ModItems.COOKING_OIL.get()
        ).addOptional(new ResourceLocation(CompatibilityTags.FRYCOOKS_DELIGHT, "canola_oil"));

        // Cherry blossom foods
        this.tag(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS).add(
                Items.PINK_PETALS,
                Items.CHERRY_LEAVES,
                Items.CHERRY_SAPLING
        );

        // Calamari
        this.tag(ModTags.Items.CALAMARI_ROLL_INGREDIENTS)
                .addTag(ForgeTags.RAW_FISHES_CALAMARI)
                .remove(ModItems.CALAMARI.get())
                .addOptional(new ResourceLocation(CompatibilityTags.MINERS_DELIGHT,"tentacles"));

        // Coffee
        this.tag(ModTags.Items.COFFEE_INGREDIENTS)
                .addTag(ForgeTags.CROPS_COFFEE)
                .addTag(ForgeTags.CROPS_COFFEE_BEANS)
                .remove(ModItems.COFFEE_BEANS.get()) // We actually don't want to use our coffee beans here, because we require to roast them first.
                .add(ModItems.ROASTED_COFFEE_BEANS.get())
                .addOptional(new ResourceLocation(CompatibilityTags.FARMERS_RESPITE, "coffee_beans"));

        this.tag(ModTags.Items.COFFEE_FOOD_INGREDIENTS).add(
                ModItems.COFFEE.get(),
                ModItems.DARK_COFFEE.get()
        ).addOptional(new ResourceLocation(CompatibilityTags.FARMERS_RESPITE, "coffee"));

        // Syrup
        this.tag(ModTags.Items.SYRUP_INGREDIENTS).add(
                Items.APPLE
        ).addTag(Tags.Items.CROPS_BEETROOT);

        this.tag(ModTags.Items.SYRUP).add(
                ModItems.SYRUP.get()
        );

        this.tag(ModTags.Items.SWEET_LIQUIDS).add(
                Items.HONEY_BOTTLE
        ).addTag(ModTags.Items.SYRUP);

        // Eggs
        this.tag(ModTags.Items.RAW_AND_COOKED_EGGS)
                .addTag(Tags.Items.EGGS)
                .addOptionalTag(ForgeTags.COOKED_EGGS);

        // Spring rolls
        this.tag(ModTags.Items.SPRING_ROLL_INGREDIENTS)
                .addTag(ForgeTags.RAW_FISHES)
                .addOptionalTag(ForgeTags.RAW_PORK)
                .addOptionalTag(ForgeTags.RAW_CHICKEN)
                .addOptionalTag(ForgeTags.RAW_BEEF)
                .addOptionalTag(ForgeTags.RAW_MUTTON)
                .addTag(Tags.Items.EGGS)
                .addTag(Tags.Items.MUSHROOMS)
                .addOptionalTag(ForgeTags.VEGETABLES_CARROT)
                .addTag(ForgeTags.VEGETABLES_POTATO)
                .addOptionalTag(ForgeTags.VEGETABLES_BEETROOT)
                .addTag(ForgeTags.VEGETABLES_BELL_PEPPER);

        // Stuffed bell peppers
        this.tag(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .addTag(ForgeTags.RAW_FISHES)
                .addOptionalTag(ForgeTags.RAW_PORK)
                .addOptionalTag(ForgeTags.RAW_CHICKEN)
                .addOptionalTag(ForgeTags.RAW_BEEF)
                .addOptionalTag(ForgeTags.RAW_MUTTON)
                .addTag(Tags.Items.EGGS)
                .addTag(Tags.Items.MUSHROOMS)
                .addOptionalTag(ForgeTags.VEGETABLES_CARROT)
                .addOptionalTag(ForgeTags.VEGETABLES_POTATO)
                .addOptionalTag(ForgeTags.VEGETABLES_BEETROOT)
                .addOptionalTag(ForgeTags.VEGETABLES_TOMATO)
                .addOptionalTag(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
                .addOptionalTag(ForgeTags.VEGETABLES_ONION)
                .addOptionalTag(CompatibilityTags.BREWIN_AND_CHEWIN_CHEESE_WEDGE);
    }

    private void addMinecraftTags() {
        // Villagers
        this.tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(
                ModItems.COTTON_SEEDS.get(),
                ModItems.BELL_PEPPER_SEEDS.get(),
                ModItems.COFFEE_BEANS.get()
        );

        // Fish
        this.tag(ItemTags.FISHES).add(
                ModItems.CALAMARI.get(),
                ModItems.COOKED_CALAMARI.get()
        );

        // Piglins
        this.tag(ItemTags.PIGLIN_LOVED).add(
                ModItems.GOLDEN_COFFEE_BEANS.get()
        );

    }

    private void addForgeTags() {
        addStorageBlockTags();
        addSeedTags();
        addCropTags();
        addFoodTags();
    }

    private void addCompatibilityTags() {
        // Create
        this.tag(CompatibilityTags.CREATE_UPRIGHT_ON_BELT).add(
                ModItems.COOKING_OIL.get(),
                ModItems.SYRUP.get(),
                ModItems.COFFEE.get(),
                ModItems.MILK_COFFEE.get(),
                ModItems.HONEY_COFFEE.get(),
                ModItems.CHOCOLATE_COFFEE.get(),
                ModItems.SYRUP_COFFEE.get(),
                ModItems.DARK_COFFEE.get(),
                ModItems.CHERRY_BLOSSOM_CHEESECAKE.get(),
                ModItems.SYRUP_CHEESECAKE.get()
        );

        // Serene Seasons
        this.tag(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS).add(
                ModItems.COTTON_SEEDS.get()
        );
        this.tag(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS).add(
                ModItems.COTTON_SEEDS.get(),
                ModItems.BELL_PEPPER_SEEDS.get(),
                ModItems.COFFEE_BEANS.get()
        );
        this.tag(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS).add(
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

        // Brewin' and Chewin
        this.tag(CompatibilityTags.BREWIN_AND_CHEWIN_PIZZA_TOPPING).addTag(ForgeTags.VEGETABLES_BELL_PEPPER);

        // Supplementaries
        this.tag(CompatibilityTags.COOKIES).addTag(
                ForgeTags.COOKIES
        );
    }

    private void addStorageBlockTags() {
        // Storage blocks
        this.tag(ForgeTags.STORAGE_BLOCKS_ITEM_COTTON_SEEDS).add(
                ModItems.COTTON_SEEDS_BAG.get()
        );
        this.tag(ForgeTags.STORAGE_BLOCKS_ITEM_COTTON).add(
                ModItems.COTTON_BOLL_CRATE.get()
        );
        this.tag(ForgeTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_SEEDS).add(
                ModItems.BELL_PEPPER_SEEDS_BAG.get()
        );
        this.tag(ForgeTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_GREEN).add(
                ModItems.BELL_PEPPER_GREEN_CRATE.get()
        );
        this.tag(ForgeTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_YELLOW).add(
                ModItems.BELL_PEPPER_YELLOW_CRATE.get()
        );
        this.tag(ForgeTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_RED).add(
                ModItems.BELL_PEPPER_RED_CRATE.get()
        );
        this.tag(ForgeTags.STORAGE_BLOCKS_ITEM_COFFEE_BEANS).add(
                ModItems.COFFEE_BEANS_BAG.get()
        );
        this.tag(ForgeTags.STORAGE_BLOCKS_ITEM_ROASTED_COFFEE_BEANS).add(
                ModItems.ROASTED_COFFEE_BEANS_BAG.get()
        );

        // Duplicate tags
        this.tag(ForgeTags.STORAGE_BLOCKS_ITEM_COFFEE).add(
                ModItems.COFFEE_BEANS_BAG.get()
        );

        // Main storage block tag
        this.tag(Tags.Items.STORAGE_BLOCKS)
                .addTag(ForgeTags.STORAGE_BLOCKS_ITEM_COTTON_SEEDS)
                .addTag(ForgeTags.STORAGE_BLOCKS_ITEM_COTTON)
                .addTag(ForgeTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_SEEDS)
                .addTag(ForgeTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_GREEN)
                .addTag(ForgeTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_YELLOW)
                .addTag(ForgeTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_RED)
                .addTag(ForgeTags.STORAGE_BLOCKS_ITEM_COFFEE_BEANS)
                .addTag(ForgeTags.STORAGE_BLOCKS_ITEM_COFFEE)
                .addTag(ForgeTags.STORAGE_BLOCKS_ITEM_ROASTED_COFFEE_BEANS);
    }

    private void addSeedTags() {
        this.tag(ForgeTags.SEEDS_COTTON).add(
                ModItems.COTTON_SEEDS.get()
        );
        this.tag(ForgeTags.SEEDS_BELL_PEPPER).add(
                ModItems.BELL_PEPPER_SEEDS.get()
        );
        this.tag(ForgeTags.SEEDS_COFFEE_BEANS).add(
                ModItems.COFFEE_BEANS.get()
        );
        this.tag(ForgeTags.SEEDS_COFFEE).add(
                ModItems.COFFEE_BEANS.get()
        );

        // Main seeds tag
        this.tag(Tags.Items.SEEDS)
                .addTag(ForgeTags.SEEDS_COTTON)
                .addTag(ForgeTags.SEEDS_BELL_PEPPER)
                .addTag(ForgeTags.SEEDS_COFFEE_BEANS)
                .addTag(ForgeTags.SEEDS_COFFEE);
    }

    private void addCropTags() {
        this.tag(ForgeTags.CROPS_COTTON).add(
                ModItems.COTTON_BOLL.get()
        );

        this.tag(ForgeTags.CROPS_BELL_PEPPER).add(
                ModItems.BELL_PEPPER_GREEN.get(),
                ModItems.BELL_PEPPER_YELLOW.get(),
                ModItems.BELL_PEPPER_RED.get()
        );

        // Coffee
        this.tag(ForgeTags.CROPS_COFFEE_BEANS).add(
                ModItems.COFFEE_BEANS.get()
        );
        this.tag(ForgeTags.CROPS_COFFEE).add(
                ModItems.COFFEE_BEANS.get()
        );

        // Main crops tag
        this.tag(ForgeTags.CROPS)
                .addTag(ForgeTags.CROPS_COTTON)
                .addTag(ForgeTags.CROPS_BELL_PEPPER)
                .addTag(ForgeTags.CROPS_COFFEE);
    }

    private void addFoodTags() {
        // Fish
        this.tag(ForgeTags.RAW_FISHES).addTag(
                ForgeTags.RAW_FISHES_CALAMARI
        );
        this.tag(ForgeTags.COOKED_FISHES).addTag(
                ForgeTags.COOKED_FISHES_CALAMARI
        );
        this.tag(ForgeTags.RAW_FISHES_CALAMARI)
                .add(
                        ModItems.CALAMARI.get(),
                        ModItems.CALAMARI_SLICE.get()
                )
                .addOptional(new ResourceLocation(CompatibilityTags.CULTURAL_DELIGHTS,"squid"))
                .addOptional(new ResourceLocation(CompatibilityTags.CULTURAL_DELIGHTS,"glow_squid"))
                .addOptional(new ResourceLocation(CompatibilityTags.CULTURAL_DELIGHTS,"raw_calamari"))
                .addOptional(new ResourceLocation(CompatibilityTags.MINERS_DELIGHT,"squid"))
                .addOptional(new ResourceLocation(CompatibilityTags.MINERS_DELIGHT,"glow_squid"))
                .addOptional(new ResourceLocation(CompatibilityTags.MINERS_DELIGHT,"tentacles"));

        this.tag(ForgeTags.COOKED_FISHES_CALAMARI).add(
                ModItems.COOKED_CALAMARI.get(),
                ModItems.COOKED_CALAMARI_SLICE.get()
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
        ).add(
                ModItems.BELL_PEPPER_SLICE_GREEN.get(),
                ModItems.BELL_PEPPER_SLICE_YELLOW.get(),
                ModItems.BELL_PEPPER_SLICE_RED.get()
        );

        // Cookies
        this.tag(ForgeTags.COOKIES).add(
                ModItems.CHERRY_BLOSSOM_COOKIE.get(),
                ModItems.COFFEE_COOKIE.get(),
                ModItems.SYRUP_COOKIE.get()
        );
    }
}
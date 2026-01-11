package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tags.CommonTags;
import com.phantomwing.rusticdelight.tags.CompatibilityTags;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.tag.ConventionalTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        addMinecraftTags();
        addCommonTags();
        addCompatibilityTags();
        addModTags();
    }

    private void addModTags() {
        // Cooking oil
        getOrCreateTagBuilder(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .add(ModItems.COTTON_SEEDS, Items.PUMPKIN_SEEDS)
                .addOptionalTag(CommonTags.SEEDS_CANOLA.location())
                .addOptionalTag(CommonTags.SEEDS_SUNFLOWER.location())
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.FRYCOOKS_DELIGHT, "canola_seeds"));

        getOrCreateTagBuilder(ModTags.Items.COOKING_OIL)
                .add(ModItems.COOKING_OIL)
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.FRYCOOKS_DELIGHT, "canola_oil"));

        // Cherry blossom foods
        getOrCreateTagBuilder(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS).add(
                Items.PINK_PETALS,
                Items.CHERRY_LEAVES,
                Items.CHERRY_SAPLING
        );

        // Calamari
        getOrCreateTagBuilder(ModTags.Items.CALAMARI_ROLL_INGREDIENTS)
                .add(ModItems.CALAMARI_SLICE)
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.MINERS_DELIGHT, "tentacles"));

        // Coffee
        getOrCreateTagBuilder(ModTags.Items.COFFEE_INGREDIENTS)
                .add(ModItems.ROASTED_COFFEE_BEANS)
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.FARMERS_RESPITE, "coffee_beans"));

        getOrCreateTagBuilder(ModTags.Items.COFFEE_FOOD_INGREDIENTS)
                .add(ModItems.COFFEE, ModItems.DARK_COFFEE)
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.FARMERS_RESPITE, "coffee"));
        // Syrup
        getOrCreateTagBuilder(ModTags.Items.SYRUP_INGREDIENTS).add(
                Items.APPLE
        ).addOptionalTag(ConventionalItemTags.BEETROOT_CROPS);

        getOrCreateTagBuilder(ModTags.Items.SYRUP).add(
                ModItems.SYRUP
        );

        getOrCreateTagBuilder(ModTags.Items.SWEET_LIQUIDS).add(
                Items.HONEY_BOTTLE
        ).addTag(ModTags.Items.SYRUP);

        // Eggs
        getOrCreateTagBuilder(ModTags.Items.RAW_AND_COOKED_EGGS)
            .addOptionalTag(CommonTags.EGGS)
            .addOptionalTag(CommonTags.FOODS_COOKED_EGG);

        // Spring rolls
        getOrCreateTagBuilder(ModTags.Items.SPRING_ROLL_INGREDIENTS)
            .addOptionalTag(CommonTags.FOODS_SAFE_RAW_FISH)
            .addOptionalTag(CommonTags.FOODS_RAW_PORK)
            .addOptionalTag(CommonTags.FOODS_RAW_CHICKEN)
            .addOptionalTag(CommonTags.FOODS_RAW_BEEF)
            .addOptionalTag(CommonTags.FOODS_RAW_MUTTON)
            .addTag(CommonTags.FOODS_CARROT)
            .addTag(CommonTags.FOODS_POTATO)
            .addTag(CommonTags.FOODS_BEETROOT)
            .addTag(CommonTags.FOODS_BELL_PEPPER)
            .addOptionalTag(CommonTags.EGGS)
            .addOptionalTag(CommonTags.MUSHROOMS);

        // Stuffed bell peppers
        getOrCreateTagBuilder(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
            .addOptionalTag(CommonTags.FOODS_SAFE_RAW_FISH)
            .addOptionalTag(CommonTags.FOODS_RAW_PORK)
            .addOptionalTag(CommonTags.FOODS_RAW_CHICKEN)
            .addOptionalTag(CommonTags.FOODS_RAW_BEEF)
            .addOptionalTag(CommonTags.FOODS_RAW_MUTTON)
            .addTag(CommonTags.FOODS_CARROT)
            .addTag(CommonTags.FOODS_POTATO)
            .addTag(CommonTags.FOODS_BEETROOT)
            .addOptionalTag(CommonTags.FOODS_TOMATO)
            .addOptionalTag(CommonTags.FOODS_CABBAGE)
            .addOptionalTag(CommonTags.FOODS_ONION)
            .addOptionalTag(CommonTags.EGGS)
            .addOptionalTag(CommonTags.MUSHROOMS)
            .addOptionalTag(CompatibilityTags.BREWIN_AND_CHEWIN_CHEESE_WEDGE);

        // Fried rice
        getOrCreateTagBuilder(ModTags.Items.FRIED_RICE_INGREDIENTS)
            .addOptionalTag(CommonTags.EGGS)
            .addTag(ModTags.Items.COOKING_OIL);

        // Mushroom rice
        getOrCreateTagBuilder(ModTags.Items.MUSHROOM_RICE_INGREDIENTS)
            .addTag(CommonTags.FOODS_CARROT)
            .addTag(CommonTags.FOODS_POTATO);
    }

    private void addMinecraftTags() {
        // Villagers
        getOrCreateTagBuilder(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(
            ModItems.COTTON_SEEDS,
            ModItems.BELL_PEPPER_SEEDS,
            ModItems.COFFEE_BEANS
        );
        getOrCreateTagBuilder(ItemTags.VILLAGER_PICKS_UP).add(
                ModItems.BELL_PEPPER_GREEN,
                ModItems.BELL_PEPPER_YELLOW,
                ModItems.BELL_PEPPER_RED,
                ModItems.COTTON_BOLL,
                ModItems.BELL_PEPPER_SEEDS,
                ModItems.COTTON_SEEDS,
                ModItems.COFFEE_BEANS
        );

        this.getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS).add(
            ModItems.WILD_COTTON,
            ModItems.WILD_BELL_PEPPERS,
            ModItems.WILD_COFFEE
        );

        // Animal food
        getOrCreateTagBuilder(ItemTags.CHICKEN_FOOD).add(
            ModItems.COTTON_SEEDS,
            ModItems.BELL_PEPPER_SEEDS
        );
        getOrCreateTagBuilder(ItemTags.PARROT_FOOD).add(
            ModItems.COTTON_SEEDS,
            ModItems.BELL_PEPPER_SEEDS
        );
        getOrCreateTagBuilder(ItemTags.CAT_FOOD).add(
            ModItems.CALAMARI
        );
        getOrCreateTagBuilder(ItemTags.OCELOT_FOOD).add(
            ModItems.CALAMARI
        );
        getOrCreateTagBuilder(ItemTags.PIG_FOOD).addTag(
            CommonTags.CROPS_BELL_PEPPER
        );

        // Fish
        getOrCreateTagBuilder(ItemTags.FISHES).add(
            ModItems.CALAMARI,
            ModItems.COOKED_CALAMARI
        );

        // Piglins
        getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED).add(
            ModItems.GOLDEN_COFFEE_BEANS
        );
    }

    private void addCommonTags() {
        addStorageBlockTags();
        addSeedTags();
        addCropTags();
        addFoodTags();
    }

    private void addCompatibilityTags() {
        // Create
        getOrCreateTagBuilder(CompatibilityTags.CREATE_UPRIGHT_ON_BELT).add(
            ModItems.COOKING_OIL,
            ModItems.SYRUP,
            ModItems.COFFEE,
            ModItems.MILK_COFFEE,
            ModItems.CHOCOLATE_COFFEE,
            ModItems.HONEY_COFFEE,
            ModItems.SYRUP_COFFEE,
            ModItems.DARK_COFFEE,
            ModItems.CHERRY_BLOSSOM_CHEESECAKE,
            ModItems.SYRUP_CHEESECAKE
        );

        // Farmer's Delight
        getOrCreateTagBuilder(vectorwing.farmersdelight.common.tag.ModTags.CABBAGE_ROLL_INGREDIENTS)
            .add(ModItems.POTATO_SLICES)
            .addTag(CommonTags.FOODS_BELL_PEPPER);
        getOrCreateTagBuilder(vectorwing.farmersdelight.common.tag.ModTags.WILD_CROPS_ITEM)
            .add(ModItems.WILD_COTTON)
            .add(ModItems.WILD_BELL_PEPPERS)
            .add(ModItems.WILD_COFFEE);

        // Serene Seasons
        getOrCreateTagBuilder(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS).add(
            ModItems.COTTON_SEEDS
        );
        getOrCreateTagBuilder(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS).add(
            ModItems.COTTON_SEEDS,
            ModItems.BELL_PEPPER_SEEDS,
            ModItems.COFFEE_BEANS
        );
        getOrCreateTagBuilder(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS).add(
                ModItems.BELL_PEPPER_SEEDS,
                ModItems.COFFEE_BEANS
        );

        // Miner's Delight
        getOrCreateTagBuilder(CompatibilityTags.MINERS_DELIGHT_TENTACLES).add(
                ModItems.CALAMARI,
                ModItems.CALAMARI_SLICE,
                ModItems.COOKED_CALAMARI,
                ModItems.COOKED_CALAMARI_SLICE
        );

        // Frycook's Delight
        getOrCreateTagBuilder(CompatibilityTags.FISH_SLICES).add(
                ModItems.CALAMARI_SLICE
        );
        getOrCreateTagBuilder(CompatibilityTags.HAS_FISH_SLICE).add(
                ModItems.CALAMARI
        );

        // Brewin' and Chewin
        getOrCreateTagBuilder(CompatibilityTags.BREWIN_AND_CHEWIN_PIZZA_TOPPING).addTag(CommonTags.FOODS_BELL_PEPPER);
    }

    private void addStorageBlockTags() {
        // Storage blocks
        getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_COTTON_SEEDS).add(
                ModItems.COTTON_SEEDS_BAG
        );
        getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_COTTON).add(
                ModItems.COTTON_BOLL_CRATE
        );
        getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_SEEDS).add(
                ModItems.BELL_PEPPER_SEEDS_BAG
        );
        getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_GREEN).add(
                ModItems.BELL_PEPPER_GREEN_CRATE
        );
        getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_YELLOW).add(
                ModItems.BELL_PEPPER_YELLOW_CRATE
        );
        getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_RED).add(
                ModItems.BELL_PEPPER_RED_CRATE
        );
        getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_COFFEE_BEANS).add(
                ModItems.COFFEE_BEANS_BAG
        );
        getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_ROASTED_COFFEE_BEANS).add(
                ModItems.ROASTED_COFFEE_BEANS_BAG
        );

        // Duplicate tags
        getOrCreateTagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_COFFEE).add(
                ModItems.COFFEE_BEANS_BAG
        );

        // Main storage block tag
        getOrCreateTagBuilder(ConventionalItemTags.STORAGE_BLOCKS)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_COTTON_SEEDS)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_COTTON)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_SEEDS)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_GREEN)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_YELLOW)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_RED)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_COFFEE_BEANS)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_COFFEE)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_ROASTED_COFFEE_BEANS);
    }

    private void addSeedTags() {
        getOrCreateTagBuilder(CommonTags.SEEDS_COTTON).add(
                ModItems.COTTON_SEEDS
        );
        getOrCreateTagBuilder(CommonTags.SEEDS_BELL_PEPPER).add(
                ModItems.BELL_PEPPER_SEEDS
        );
        getOrCreateTagBuilder(CommonTags.SEEDS_COFFEE_BEANS).add(
                ModItems.COFFEE_BEANS
        );
        getOrCreateTagBuilder(CommonTags.SEEDS_COFFEE).add(
                ModItems.COFFEE_BEANS
        );

        // Main seeds tag
        getOrCreateTagBuilder(ConventionalTags.SEEDS)
                .addTag(CommonTags.SEEDS_COTTON)
                .addTag(CommonTags.SEEDS_BELL_PEPPER)
                .addTag(CommonTags.SEEDS_COFFEE_BEANS)
                .addTag(CommonTags.SEEDS_COFFEE);
    }

    private void addCropTags() {
        // Crops
        getOrCreateTagBuilder(CommonTags.CROPS_COTTON).add(
                ModItems.COTTON_BOLL
        );
        getOrCreateTagBuilder(CommonTags.CROPS_BELL_PEPPER).add(
                ModItems.BELL_PEPPER_GREEN,
                ModItems.BELL_PEPPER_YELLOW,
                ModItems.BELL_PEPPER_RED
        );

        // Coffee
        getOrCreateTagBuilder(CommonTags.CROPS_COFFEE_BEANS).add(
                ModItems.COFFEE_BEANS
        );
        getOrCreateTagBuilder(CommonTags.CROPS_COFFEE).add(
                ModItems.COFFEE_BEANS
        );

        // Potato
        getOrCreateTagBuilder(CommonTags.CROPS_POTATO).add(
                Items.POTATO,
                ModItems.POTATO_SLICES
        );

        // Main crops tag
        getOrCreateTagBuilder(ConventionalItemTags.CROPS)
                .addTag(CommonTags.CROPS_COTTON)
                .addTag(CommonTags.CROPS_BELL_PEPPER)
                .addTag(CommonTags.CROPS_COFFEE_BEANS)
                .addTag(CommonTags.CROPS_COFFEE);
    }

    private void addFoodTags() {
        // Fruits
        getOrCreateTagBuilder(ModTags.Items.FRUITS_AND_BERRIES)
            .addOptionalTag(ConventionalItemTags.FRUIT_FOODS)
            .addOptionalTag(ConventionalItemTags.BERRY_FOODS);

        // Veggies
        getOrCreateTagBuilder(CommonTags.FOODS_BELL_PEPPER).addTag(
                CommonTags.CROPS_BELL_PEPPER
        ).add(
                ModItems.BELL_PEPPER_SLICE_GREEN,
                ModItems.BELL_PEPPER_SLICE_YELLOW,
                ModItems.BELL_PEPPER_SLICE_RED
        );

        getOrCreateTagBuilder(CommonTags.FOODS_POTATO).addTag(
            CommonTags.CROPS_POTATO
        );

        getOrCreateTagBuilder(CommonTags.FOODS_CARROT).add(
            Items.CARROT
        );

        getOrCreateTagBuilder(CommonTags.FOODS_BEETROOT).add(
            Items.BEETROOT
        );

        getOrCreateTagBuilder(ConventionalItemTags.VEGETABLE_FOODS)
            .add(ModItems.POTATO_SLICES)
            .addTag(CommonTags.FOODS_BELL_PEPPER);

        // Fish
        getOrCreateTagBuilder(CommonTags.FOODS_RAW_CALAMARI)
                .add(
                        ModItems.CALAMARI,
                        ModItems.CALAMARI_SLICE
                )
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.CULTURAL_DELIGHTS,"squid"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.CULTURAL_DELIGHTS,"glow_squid"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.CULTURAL_DELIGHTS,"raw_calamari"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.MINERS_DELIGHT,"squid"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.MINERS_DELIGHT,"glow_squid"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.MINERS_DELIGHT,"tentacles"));
        getOrCreateTagBuilder(CommonTags.FOODS_RAW_SQUID).add(
                ModItems.CALAMARI,
                ModItems.CALAMARI_SLICE
        );

        getOrCreateTagBuilder(CommonTags.FOODS_COOKED_CALAMARI).add(
                ModItems.COOKED_CALAMARI,
                ModItems.COOKED_CALAMARI_SLICE
        );
        getOrCreateTagBuilder(CommonTags.FOODS_COOKED_SQUID).add(
                ModItems.COOKED_CALAMARI,
                ModItems.COOKED_CALAMARI_SLICE
        );

        getOrCreateTagBuilder(ConventionalItemTags.RAW_FISH_FOODS).addTag(
                CommonTags.FOODS_RAW_CALAMARI
        );
        getOrCreateTagBuilder(ConventionalItemTags.COOKED_FISH_FOODS).addTag(
                CommonTags.FOODS_COOKED_CALAMARI
        );

        // Soups
        getOrCreateTagBuilder(ConventionalItemTags.SOUP_FOODS)
                .add(ModItems.BELL_PEPPER_SOUP);

        // Cookies
        getOrCreateTagBuilder(ConventionalItemTags.COOKIE_FOODS).add(
                ModItems.CHERRY_BLOSSOM_COOKIE,
                ModItems.COFFEE_COOKIE,
                ModItems.SYRUP_COOKIE
        );
    }
}

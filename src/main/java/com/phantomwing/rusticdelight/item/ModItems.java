package com.phantomwing.rusticdelight.item;

import com.google.common.collect.Sets;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.item.custom.ChocolateCoffeeItem;
import com.phantomwing.rusticdelight.item.custom.MilkCoffeeItem;
import com.phantomwing.rusticdelight.food.FoodValues;
import net.minecraft.block.Block;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.LinkedHashSet;

public class ModItems {
    public static final int BOWL_STACK_SIZE = 16;
    public static final int BOTTLE_STACK_SIZE = 16;

    public static LinkedHashSet<Item> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

    // Seed bags
    public static final Item COTTON_SEEDS_BAG = registerBlockWithTab(ModBlocks.COTTON_SEEDS_BAG);
    public static final Item BELL_PEPPER_SEEDS_BAG = registerBlockWithTab(ModBlocks.BELL_PEPPER_SEEDS_BAG);
    public static final Item COFFEE_BEANS_BAG = registerBlockWithTab(ModBlocks.COFFEE_BEANS_BAG);

    // Crop Crates
    public static final Item COTTON_BOLL_CRATE = registerBlockWithTab(ModBlocks.COTTON_BOLL_CRATE);
    public static final Item BELL_PEPPER_GREEN_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_GREEN_CRATE);
    public static final Item BELL_PEPPER_YELLOW_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_YELLOW_CRATE);
    public static final Item BELL_PEPPER_RED_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_RED_CRATE);

    // Wild crops
    public static final Item WILD_COTTON = registerBlockWithTab(ModBlocks.WILD_COTTON);
    public static final Item WILD_BELL_PEPPERS = registerBlockWithTab(ModBlocks.WILD_BELL_PEPPERS);
    public static final Item WILD_COFFEE = registerBlockWithTab(ModBlocks.WILD_COFFEE);

    // Crop products
    public static final Item COTTON_BOLL = registerWithTab("cotton_boll", new Item(baseItem()));
    public static final Item BELL_PEPPER_GREEN = registerWithTab("bell_pepper_green", new Item(
            baseItem().food(FoodValues.BELL_PEPPER)));
    public static final Item BELL_PEPPER_YELLOW = registerWithTab("bell_pepper_yellow", new Item(
            baseItem().food(FoodValues.BELL_PEPPER)));
    public static final Item BELL_PEPPER_RED = registerWithTab("bell_pepper_red", new Item(
            baseItem().food(FoodValues.BELL_PEPPER)));

    // Crop seeds
    public static final Item COTTON_SEEDS = registerWithTab("cotton_seeds", new AliasedBlockItem(
            ModBlocks.COTTON_CROP,
            baseItem()));
    public static final Item BELL_PEPPER_SEEDS = registerWithTab("bell_pepper_seeds", new AliasedBlockItem(
            ModBlocks.BELL_PEPPER_CROP,
            baseItem()));
    public static final Item COFFEE_BEANS = registerWithTab("coffee_beans", new AliasedBlockItem(
            ModBlocks.COFFEE_CROP,
            baseItem()));

    // Coffee Beans
    public static final Item ROASTED_COFFEE_BEANS = registerWithTab("roasted_coffee_beans", new Item(
            baseItem().food(FoodValues.ROASTED_COFFEE_BEANS)));
    public static final Item GOLDEN_COFFEE_BEANS = registerWithTab("golden_coffee_beans", new Item(
            baseItem().food(FoodValues.GOLDEN_COFFEE_BEANS)));

    // Calamari
    public static final Item CALAMARI = registerWithTab("calamari", new Item(
            baseItem().food(FoodValues.CALAMARI)));
    public static final Item COOKED_CALAMARI = registerWithTab("cooked_calamari", new Item(
            baseItem().food(FoodValues.COOKED_CALAMARI)));

    // Roasted bell peppers
    public static final Item ROASTED_BELL_PEPPER_GREEN = registerWithTab("roasted_bell_pepper_green", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)));
    public static final Item ROASTED_BELL_PEPPER_YELLOW = registerWithTab("roasted_bell_pepper_yellow", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)));
    public static final Item ROASTED_BELL_PEPPER_RED = registerWithTab("roasted_bell_pepper_red", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)));

    // Coffee
    public static final Item COFFEE = registerWithTab("coffee", new DrinkableItem(
            bottleItem().food(FoodValues.COFFEE), true));
    public static final Item MILK_COFFEE = registerWithTab("milk_coffee", new MilkCoffeeItem(
            bottleItem().food(FoodValues.MILK_COFFEE)));
    public static final Item CHOCOLATE_COFFEE = registerWithTab("chocolate_coffee", new ChocolateCoffeeItem(
            bottleItem().food(FoodValues.CHOCOLATE_COFFEE)));
    public static final Item HONEY_COFFEE = registerWithTab("honey_coffee", new DrinkableItem(
            bottleItem().food(FoodValues.HONEY_COFFEE), true));
    public static final Item DARK_COFFEE = registerWithTab("dark_coffee", new DrinkableItem(
            bottleItem().food(FoodValues.DARK_COFFEE), true));

    // Cooking products
    public static final Item COOKING_OIL = registerWithTab("cooking_oil", new DrinkableItem(
            bottleItem().food(FoodValues.COOKING_OIL)));
    public static final Item BATTER = registerWithTab("batter", new Item(
            bowlItem().food(FoodValues.BATTER)));

    // Sliced foods
    public static final Item POTATO_SLICES = registerWithTab("potato_slices", new Item(
            baseItem().food(FoodValues.POTATO_SLICES)));
    public static final Item BAKED_POTATO_SLICES = registerWithTab("baked_potato_slices", new Item(
            baseItem().food(FoodValues.BAKED_POTATO_SLICES)));
    public static final Item CALAMARI_SLICE = registerWithTab("calamari_slice", new Item(
            baseItem().food(FoodValues.CALAMARI_SLICE)));
    public static final Item COOKED_CALAMARI_SLICE = registerWithTab("cooked_calamari_slice", new Item(
            baseItem().food(FoodValues.COOKED_CALAMARI_SLICE)));

    // Sweets
    public static final Item FRUIT_BEIGNET = registerWithTab("fruit_beignet", new ConsumableItem(
            baseItem().food(FoodValues.FRUIT_BEIGNET), true));
    public static final Item CHERRY_BLOSSOM_COOKIE = registerWithTab("cherry_blossom_cookie", new Item(
            baseItem().food(vectorwing.farmersdelight.common.FoodValues.COOKIES)));
    public static final Item CHERRY_BLOSSOM_CHEESECAKE = registerBlockWithTab(ModBlocks.CHERRY_BLOSSOM_CHEESECAKE, baseItem());
    public static final Item CHERRY_BLOSSOM_CHEESECAKE_SLICE = registerWithTab("cherry_blossom_cheesecake_slice", new Item(
            baseItem().food(vectorwing.farmersdelight.common.FoodValues.PIE_SLICE)));
    public static final Item HONEY_PANCAKES = registerBlockWithTab(ModBlocks.HONEY_PANCAKES, bowlItem());
    public static final Item CHOCOLATE_PANCAKES = registerBlockWithTab(ModBlocks.CHOCOLATE_PANCAKES, bowlItem());
    public static final Item CHERRY_BLOSSOM_PANCAKES = registerBlockWithTab(ModBlocks.CHERRY_BLOSSOM_PANCAKES, bowlItem());
    public static final Item VEGETABLE_PANCAKES = registerBlockWithTab(ModBlocks.VEGETABLE_PANCAKES, bowlItem());

    // Basic meals
    public static final Item CALAMARI_ROLL = registerWithTab("calamari_roll", new Item(
            baseItem().food(FoodValues.CALAMARI_ROLL)));
    public static final Item CHERRY_BLOSSOM_ROLL = registerWithTab("cherry_blossom_roll", new Item(
            baseItem().food(FoodValues.CHERRY_BLOSSOM_ROLL)));
    public static final Item POTATO_SALAD = registerWithTab("potato_salad", new ConsumableItem(
            bowlItem().food(FoodValues.POTATO_SALAD), true));
    public static final Item SPRING_ROLLS = registerWithTab("spring_rolls", new Item(
            baseItem().food(FoodValues.SPRING_ROLLS)));
    public static final Item STUFFED_BELL_PEPPER_GREEN = registerWithTab("stuffed_bell_pepper_green", new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)));
    public static final Item STUFFED_BELL_PEPPER_YELLOW = registerWithTab("stuffed_bell_pepper_yellow", new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)));
    public static final Item STUFFED_BELL_PEPPER_RED = registerWithTab("stuffed_bell_pepper_red", new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)));

    // Soups and stews
    public static final Item BELL_PEPPER_SOUP = registerWithTab("bell_pepper_soup", new ConsumableItem(
            bowlItem().food(FoodValues.BELL_PEPPER_SOUP), true));

    // Plated meals
    public static final Item BELL_PEPPER_PASTA = registerWithTab("bell_pepper_pasta", new ConsumableItem(
            bowlItem().food(FoodValues.BELL_PEPPER_PASTA), true));
    public static final Item FRIED_CALAMARI = registerWithTab("fried_calamari", new ConsumableItem(
            bowlItem().food(FoodValues.FRIED_CALAMARI), true));
    public static final Item FRIED_CHICKEN = registerWithTab("fried_chicken", new ConsumableItem(
            bowlItem().food(FoodValues.FRIED_CHICKEN), true));
    public static final Item FRIED_MUSHROOMS = registerWithTab("fried_mushrooms", new ConsumableItem(
            bowlItem().food(FoodValues.FRIED_MUSHROOMS), true));
    public static final Item COFFEE_BRAISED_BEEF = registerWithTab("coffee_braised_beef", new ConsumableItem(
            bowlItem().food(FoodValues.COFFEE_BRAISED_BEEF), true));

    // Helper functions
    public static Item.Settings baseItem() {
        return new Item.Settings();
    }

    public static Item.Settings bottleItem() {
        return baseItem().recipeRemainder(Items.GLASS_BOTTLE).maxCount(BOTTLE_STACK_SIZE);
    }

    public static Item.Settings bowlItem() {
        return baseItem().recipeRemainder(Items.BOWL).maxCount(BOWL_STACK_SIZE);
    }

    private static Item registerWithTab(String name, Item item) {
        Item registeredItem = Registry.register(Registries.ITEM, Identifier.of(RusticDelight.MOD_ID, name), item);

        CREATIVE_TAB_ITEMS.add(registeredItem);

        return registeredItem;
    }

    private static Item registerBlockWithTab(Block block) {
        String name = Registries.BLOCK.getId(block).getPath();
        Item item = Registry.register(Registries.ITEM, Identifier.of(RusticDelight.MOD_ID, name),
                new BlockItem(block, baseItem()));

        CREATIVE_TAB_ITEMS.add(item);

        return item;
    }

    private static Item registerBlockWithTab(Block block, Item.Settings settings) {
        String name = Registries.BLOCK.getId(block).getPath();
        Item item = Registry.register(Registries.ITEM, Identifier.of(RusticDelight.MOD_ID, name),
                new BlockItem(block, settings));

        CREATIVE_TAB_ITEMS.add(item);

        return item;
    }

    public static void registerModItems() {
        RusticDelight.LOGGER.info("Registering items for " + RusticDelight.MOD_ID);
    }
}

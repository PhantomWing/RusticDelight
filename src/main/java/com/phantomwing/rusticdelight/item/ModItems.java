package com.phantomwing.rusticdelight.item;

import com.google.common.collect.Maps;
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
import vectorwing.farmersdelight.common.item.PlaceableItem;

import java.util.LinkedHashMap;

public class ModItems {
    public static final int BOWL_STACK_SIZE = 16;
    public static final int BOTTLE_STACK_SIZE = 16;

    // Creative-tab items in insertion order, each mapped to the families it belongs to.
    // An item is shown only when all of its families are enabled (empty = no family, always shown).
    public static LinkedHashMap<Item, ItemFamily[]> CREATIVE_TAB_ITEMS = Maps.newLinkedHashMap();

    // Seed bags
    public static final Item COTTON_SEEDS_BAG = registerBlockWithTab(ModBlocks.COTTON_SEEDS_BAG, ItemFamily.COTTON);
    public static final Item BELL_PEPPER_SEEDS_BAG = registerBlockWithTab(ModBlocks.BELL_PEPPER_SEEDS_BAG, ItemFamily.BELL_PEPPER);
    public static final Item PALE_BELL_PEPPER_SEEDS_BAG = registerBlockWithTab(ModBlocks.PALE_BELL_PEPPER_SEEDS_BAG, ItemFamily.BELL_PEPPER);
    public static final Item DARK_BELL_PEPPER_SEEDS_BAG = registerBlockWithTab(ModBlocks.DARK_BELL_PEPPER_SEEDS_BAG, ItemFamily.BELL_PEPPER);
    public static final Item COFFEE_BEANS_BAG = registerBlockWithTab(ModBlocks.COFFEE_BEANS_BAG, ItemFamily.COFFEE);
    public static final Item ROASTED_COFFEE_BEANS_BAG = registerBlockWithTab(ModBlocks.ROASTED_COFFEE_BEANS_BAG, ItemFamily.COFFEE);

    // Crop Crates
    public static final Item COTTON_BOLL_CRATE = registerBlockWithTab(ModBlocks.COTTON_BOLL_CRATE, ItemFamily.COTTON);
    public static final Item BELL_PEPPER_GREEN_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_GREEN_CRATE, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_YELLOW_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_YELLOW_CRATE, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_RED_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_RED_CRATE, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ORANGE_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_ORANGE_CRATE, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_WHITE_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_WHITE_CRATE, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_PINK_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_PINK_CRATE, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_BLUE_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_BLUE_CRATE, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_PURPLE_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_PURPLE_CRATE, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_BLACK_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_BLACK_CRATE, ItemFamily.BELL_PEPPER);
    public static final Item CALAMARI_CRATE = registerBlockWithTab(ModBlocks.CALAMARI_CRATE, ItemFamily.CALAMARI);

    // Bell pepper blocks
    public static final Item BELL_PEPPER_GREEN_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_GREEN_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_YELLOW_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_YELLOW_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_RED_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_RED_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ORANGE_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_ORANGE_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_WHITE_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_WHITE_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_PINK_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_PINK_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_BLUE_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_BLUE_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_PURPLE_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_PURPLE_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_BLACK_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_BLACK_BLOCK, ItemFamily.BELL_PEPPER);

    // Wild crops
    public static final Item WILD_COTTON = registerBlockWithTab(ModBlocks.WILD_COTTON, ItemFamily.COTTON);
    public static final Item WILD_BELL_PEPPERS = registerBlockWithTab(ModBlocks.WILD_BELL_PEPPERS, ItemFamily.BELL_PEPPER);
    public static final Item WILD_PALE_BELL_PEPPERS = registerBlockWithTab(ModBlocks.WILD_PALE_BELL_PEPPERS, ItemFamily.BELL_PEPPER);
    public static final Item WILD_DARK_BELL_PEPPERS = registerBlockWithTab(ModBlocks.WILD_DARK_BELL_PEPPERS, ItemFamily.BELL_PEPPER);
    public static final Item WILD_COFFEE = registerBlockWithTab(ModBlocks.WILD_COFFEE, ItemFamily.COFFEE);

    // Crop seeds
    public static final Item COTTON_SEEDS = registerWithTab("cotton_seeds", new AliasedBlockItem(
            ModBlocks.COTTON_CROP,
            baseItem()), ItemFamily.COTTON);
    public static final Item BELL_PEPPER_SEEDS = registerWithTab("bell_pepper_seeds", new AliasedBlockItem(
            ModBlocks.BELL_PEPPER_CROP,
            baseItem()), ItemFamily.BELL_PEPPER);
    public static final Item PALE_BELL_PEPPER_SEEDS = registerWithTab("pale_bell_pepper_seeds", new AliasedBlockItem(
            ModBlocks.PALE_BELL_PEPPER_CROP,
            baseItem()), ItemFamily.BELL_PEPPER);
    public static final Item DARK_BELL_PEPPER_SEEDS = registerWithTab("dark_bell_pepper_seeds", new AliasedBlockItem(
            ModBlocks.DARK_BELL_PEPPER_CROP,
            baseItem()), ItemFamily.BELL_PEPPER);

    // Crop products
    public static final Item COTTON_BOLL = registerWithTab("cotton_boll", new Item(baseItem()), ItemFamily.COTTON);
    public static final Item BELL_PEPPER_GREEN = registerWithTab("bell_pepper_green", new Item(
            baseItem().food(FoodValues.BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_YELLOW = registerWithTab("bell_pepper_yellow", new Item(
            baseItem().food(FoodValues.BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_RED = registerWithTab("bell_pepper_red", new Item(
            baseItem().food(FoodValues.BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ORANGE = registerWithTab("bell_pepper_orange", new Item(
            baseItem().food(FoodValues.BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_WHITE = registerWithTab("bell_pepper_white", new Item(
            baseItem().food(FoodValues.BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_PINK = registerWithTab("bell_pepper_pink", new Item(
            baseItem().food(FoodValues.BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_BLUE = registerWithTab("bell_pepper_blue", new Item(
            baseItem().food(FoodValues.BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_PURPLE = registerWithTab("bell_pepper_purple", new Item(
            baseItem().food(FoodValues.BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_BLACK = registerWithTab("bell_pepper_black", new Item(
            baseItem().food(FoodValues.BELL_PEPPER)), ItemFamily.BELL_PEPPER);

    // Roasted bell peppers
    public static final Item ROASTED_BELL_PEPPER_GREEN = registerWithTab("roasted_bell_pepper_green", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_YELLOW = registerWithTab("roasted_bell_pepper_yellow", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_RED = registerWithTab("roasted_bell_pepper_red", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_ORANGE = registerWithTab("roasted_bell_pepper_orange", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_WHITE = registerWithTab("roasted_bell_pepper_white", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_PINK = registerWithTab("roasted_bell_pepper_pink", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_BLUE = registerWithTab("roasted_bell_pepper_blue", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_PURPLE = registerWithTab("roasted_bell_pepper_purple", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_BLACK = registerWithTab("roasted_bell_pepper_black", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);

    // Calamari
    public static final Item CALAMARI = registerWithTab("calamari", new Item(
            baseItem().food(FoodValues.CALAMARI)), ItemFamily.CALAMARI);
    public static final Item COOKED_CALAMARI = registerWithTab("cooked_calamari", new Item(
            baseItem().food(FoodValues.COOKED_CALAMARI)), ItemFamily.CALAMARI);

    // Coffee Beans
    public static final Item COFFEE_BEANS = registerWithTab("coffee_beans", new AliasedBlockItem(
            ModBlocks.COFFEE_CROP, baseItem()), ItemFamily.COFFEE);
    public static final Item ROASTED_COFFEE_BEANS = registerWithTab("roasted_coffee_beans", new Item(
            baseItem().food(FoodValues.ROASTED_COFFEE_BEANS)), ItemFamily.COFFEE);
    public static final Item GOLDEN_COFFEE_BEANS = registerWithTab("golden_coffee_beans", new Item(
            baseItem().food(FoodValues.GOLDEN_COFFEE_BEANS)), ItemFamily.COFFEE);

    // Coffee
    public static final Item COFFEE = registerWithTab("coffee", new DrinkableItem(
            bottleItem().food(FoodValues.COFFEE), true), ItemFamily.COFFEE);
    public static final Item DARK_COFFEE = registerWithTab("dark_coffee", new DrinkableItem(
            bottleItem().food(FoodValues.DARK_COFFEE), true), ItemFamily.COFFEE);
    public static final Item MILK_COFFEE = registerWithTab("milk_coffee", new MilkCoffeeItem(
            bottleItem().food(FoodValues.MILK_COFFEE)), ItemFamily.COFFEE);

    public static final Item CHOCOLATE_COFFEE = registerWithTab("chocolate_coffee", new ChocolateCoffeeItem(
            bottleItem().food(FoodValues.CHOCOLATE_COFFEE)), ItemFamily.COFFEE);
    public static final Item HONEY_COFFEE = registerWithTab("honey_coffee", new MilkCoffeeItem(
            bottleItem().food(FoodValues.HONEY_COFFEE)), ItemFamily.COFFEE);
    public static final Item SYRUP_COFFEE = registerWithTab("syrup_coffee", new MilkCoffeeItem(
            bottleItem().food(FoodValues.SYRUP_COFFEE)), ItemFamily.COFFEE, ItemFamily.SYRUP_FOODS);
    public static final Item PUMPKIN_COFFEE = registerWithTab("pumpkin_coffee", new MilkCoffeeItem(
            bottleItem().food(FoodValues.PUMPKIN_COFFEE)), ItemFamily.COFFEE);
    public static final Item CHERRY_BLOSSOM_COFFEE = registerWithTab("cherry_blossom_coffee", new MilkCoffeeItem(
            bottleItem().food(FoodValues.CHERRY_BLOSSOM_COFFEE)), ItemFamily.COFFEE, ItemFamily.CHERRY_BLOSSOM_FOODS);

    // Cooking products
    public static final Item COOKING_OIL = registerWithTab("cooking_oil", new DrinkableItem(
            bottleItem().food(FoodValues.COOKING_OIL)), ItemFamily.FRIED_FOODS);
    public static final Item SYRUP = registerWithTab("syrup", new DrinkableItem(
            bottleItem().food(FoodValues.SYRUP), true), ItemFamily.SYRUP_FOODS);
    public static final Item BATTER = registerWithTab("batter", new Item(
            bowlItem().food(FoodValues.BATTER)), ItemFamily.BATTER);

    // Sliced foods
    public static final Item POTATO_SLICES = registerWithTab("potato_slices", new Item(
            baseItem().food(FoodValues.POTATO_SLICES)), ItemFamily.POTATO_SLICES);
    public static final Item BAKED_POTATO_SLICES = registerWithTab("baked_potato_slices", new Item(
            baseItem().food(FoodValues.BAKED_POTATO_SLICES)), ItemFamily.POTATO_SLICES);

    public static final Item BELL_PEPPER_SLICE_GREEN = registerWithTab("bell_pepper_slice_green", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_YELLOW = registerWithTab("bell_pepper_slice_yellow", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_RED = registerWithTab("bell_pepper_slice_red", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_ORANGE = registerWithTab("bell_pepper_slice_orange", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_WHITE = registerWithTab("bell_pepper_slice_white", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_PINK = registerWithTab("bell_pepper_slice_pink", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_BLUE = registerWithTab("bell_pepper_slice_blue", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_PURPLE = registerWithTab("bell_pepper_slice_purple", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_BLACK = registerWithTab("bell_pepper_slice_black", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);

    public static final Item ROASTED_BELL_PEPPER_SLICE_GREEN = registerWithTab("roasted_bell_pepper_slice_green", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_YELLOW = registerWithTab("roasted_bell_pepper_slice_yellow", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_RED = registerWithTab("roasted_bell_pepper_slice_red", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_ORANGE = registerWithTab("roasted_bell_pepper_slice_orange", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_WHITE = registerWithTab("roasted_bell_pepper_slice_white", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_PINK = registerWithTab("roasted_bell_pepper_slice_pink", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_BLUE = registerWithTab("roasted_bell_pepper_slice_blue", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_PURPLE = registerWithTab("roasted_bell_pepper_slice_purple", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_BLACK = registerWithTab("roasted_bell_pepper_slice_black", new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER_SLICE)), ItemFamily.BELL_PEPPER);

    public static final Item CALAMARI_SLICE = registerWithTab("calamari_slice", new Item(
            baseItem().food(FoodValues.CALAMARI_SLICE)), ItemFamily.CALAMARI);
    public static final Item COOKED_CALAMARI_SLICE = registerWithTab("cooked_calamari_slice", new Item(
            baseItem().food(FoodValues.COOKED_CALAMARI_SLICE)), ItemFamily.CALAMARI);

    // Pies & cakes
    public static final Item SYRUP_CHEESECAKE = registerPlaceableBlockWithTab(ModBlocks.SYRUP_CHEESECAKE, baseItem(), ItemFamily.SYRUP_FOODS);
    public static final Item SYRUP_CHEESECAKE_SLICE = registerWithTab("syrup_cheesecake_slice", new ConsumableItem(
            baseItem().food(vectorwing.farmersdelight.common.FoodValues.PIE_SLICE)), ItemFamily.SYRUP_FOODS);

    public static final Item CHERRY_BLOSSOM_CHEESECAKE = registerPlaceableBlockWithTab(ModBlocks.CHERRY_BLOSSOM_CHEESECAKE, baseItem(), ItemFamily.CHERRY_BLOSSOM_FOODS);
    public static final Item CHERRY_BLOSSOM_CHEESECAKE_SLICE = registerWithTab("cherry_blossom_cheesecake_slice", new ConsumableItem(
            baseItem().food(vectorwing.farmersdelight.common.FoodValues.PIE_SLICE)), ItemFamily.CHERRY_BLOSSOM_FOODS);

    public static final Item COFFEE_CHEESECAKE = registerPlaceableBlockWithTab(ModBlocks.COFFEE_CHEESECAKE, baseItem(), ItemFamily.COFFEE);
    public static final Item COFFEE_CHEESECAKE_SLICE = registerWithTab("coffee_cheesecake_slice", new ConsumableItem(
            baseItem().food(FoodValues.COFFEE_CHEESECAKE_SLICE)), ItemFamily.COFFEE);

    // Cookies
    public static final Item SYRUP_COOKIE = registerWithTab("syrup_cookie", new Item(
            baseItem().food(vectorwing.farmersdelight.common.FoodValues.COOKIES)), ItemFamily.SYRUP_FOODS);
    public static final Item CHERRY_BLOSSOM_COOKIE = registerWithTab("cherry_blossom_cookie", new Item(
            baseItem().food(vectorwing.farmersdelight.common.FoodValues.COOKIES)), ItemFamily.CHERRY_BLOSSOM_FOODS);
    public static final Item COFFEE_COOKIE = registerWithTab("coffee_cookie", new Item(
            baseItem().food(vectorwing.farmersdelight.common.FoodValues.COOKIES)), ItemFamily.COFFEE);

    // Sweets
    public static final Item SYRUP_SANDWICH = registerWithTab("syrup_sandwich", new Item(
            baseItem().food(FoodValues.SYRUP_SANDWICH)), ItemFamily.SYRUP_FOODS);
    public static final Item FRUIT_BEIGNET = registerWithTab("fruit_beignet", new ConsumableItem(
            baseItem().food(FoodValues.FRUIT_BEIGNET), true), ItemFamily.FRIED_FOODS);

    // Pancakes
    public static final Item PANCAKES = registerPlaceableBlockWithTab(ModBlocks.PANCAKES, bowlItem(), ItemFamily.PANCAKES, ItemFamily.SYRUP_FOODS);
    public static final Item PANCAKE = registerWithTab("pancake", new Item(
            baseItem().food(FoodValues.PANCAKE)), ItemFamily.PANCAKES, ItemFamily.SYRUP_FOODS);
    public static final Item HONEY_PANCAKES = registerPlaceableBlockWithTab(ModBlocks.HONEY_PANCAKES, bowlItem(), ItemFamily.PANCAKES);
    public static final Item HONEY_PANCAKE = registerWithTab("honey_pancake", new ConsumableItem(
            baseItem().food(FoodValues.HONEY_PANCAKE), true), ItemFamily.PANCAKES);
    public static final Item CHOCOLATE_PANCAKES = registerPlaceableBlockWithTab(ModBlocks.CHOCOLATE_PANCAKES, bowlItem(), ItemFamily.PANCAKES);
    public static final Item CHOCOLATE_PANCAKE = registerWithTab("chocolate_pancake", new ConsumableItem(
            baseItem().food(FoodValues.CHOCOLATE_PANCAKE), true), ItemFamily.PANCAKES);
    public static final Item CHERRY_BLOSSOM_PANCAKES = registerPlaceableBlockWithTab(ModBlocks.CHERRY_BLOSSOM_PANCAKES, bowlItem(), ItemFamily.CHERRY_BLOSSOM_FOODS, ItemFamily.PANCAKES);
    public static final Item CHERRY_BLOSSOM_PANCAKE = registerWithTab("cherry_blossom_pancake", new ConsumableItem(
            baseItem().food(FoodValues.CHERRY_BLOSSOM_PANCAKE), true), ItemFamily.CHERRY_BLOSSOM_FOODS, ItemFamily.PANCAKES);
    public static final Item VEGETABLE_PANCAKES = registerPlaceableBlockWithTab(ModBlocks.VEGETABLE_PANCAKES, bowlItem(), ItemFamily.PANCAKES);
    public static final Item VEGETABLE_PANCAKE = registerWithTab("vegetable_pancake", new ConsumableItem(
            baseItem().food(FoodValues.VEGETABLE_PANCAKE), true), ItemFamily.PANCAKES);
    public static final Item PUMPKIN_PANCAKES = registerPlaceableBlockWithTab(ModBlocks.PUMPKIN_PANCAKES, bowlItem(), ItemFamily.PANCAKES, ItemFamily.SYRUP_FOODS);
    public static final Item PUMPKIN_PANCAKE = registerWithTab("pumpkin_pancake", new ConsumableItem(
            baseItem().food(FoodValues.PUMPKIN_PANCAKE), true), ItemFamily.PANCAKES, ItemFamily.SYRUP_FOODS);
    public static final Item COFFEE_PANCAKES = registerPlaceableBlockWithTab(ModBlocks.COFFEE_PANCAKES, bowlItem(), ItemFamily.PANCAKES, ItemFamily.COFFEE);
    public static final Item COFFEE_PANCAKE = registerWithTab("coffee_pancake", new ConsumableItem(
            baseItem().food(FoodValues.COFFEE_PANCAKE), true), ItemFamily.PANCAKES, ItemFamily.COFFEE);

    // Salads
    public static final Item POTATO_SALAD = registerWithTab("potato_salad", new ConsumableItem(
            bowlItem().food(FoodValues.POTATO_SALAD), true));
    public static final Item SWEET_SALAD = registerWithTab("sweet_salad", new ConsumableItem(
            bowlItem().food(FoodValues.SWEET_SALAD), true));

    // Basic meals
    public static final Item STUFFED_BELL_PEPPER_GREEN = registerWithTab("stuffed_bell_pepper_green", new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_YELLOW = registerWithTab("stuffed_bell_pepper_yellow", new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_RED = registerWithTab("stuffed_bell_pepper_red", new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_ORANGE = registerWithTab("stuffed_bell_pepper_orange", new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_WHITE = registerWithTab("stuffed_bell_pepper_white", new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_PINK = registerWithTab("stuffed_bell_pepper_pink", new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_BLUE = registerWithTab("stuffed_bell_pepper_blue", new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_PURPLE = registerWithTab("stuffed_bell_pepper_purple", new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_BLACK = registerWithTab("stuffed_bell_pepper_black", new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)), ItemFamily.BELL_PEPPER);

    // Fried foods
    public static final Item FRIED_DOUGH = registerWithTab("fried_dough", new Item(
            baseItem().food(FoodValues.FRIED_DOUGH)), ItemFamily.FRIED_FOODS);
    public static final Item FRIED_DUMPLINGS = registerWithTab("fried_dumplings", new Item(
            baseItem().food(FoodValues.FRIED_DUMPLINGS)), ItemFamily.FRIED_FOODS);
    public static final Item SPRING_ROLLS = registerWithTab("spring_rolls", new Item(
            baseItem().food(FoodValues.SPRING_ROLLS)), ItemFamily.FRIED_FOODS);
    public static final Item FRIED_FISH = registerWithTab("fried_fish", new Item(
            baseItem().food(FoodValues.FRIED_FISH)), ItemFamily.FRIED_FOODS);

    // Sushi rolls
    public static final Item BELL_PEPPER_ROLL_GREEN = registerWithTab("bell_pepper_roll_green", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_ROLL)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_YELLOW = registerWithTab("bell_pepper_roll_yellow", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_ROLL)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_RED = registerWithTab("bell_pepper_roll_red", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_ROLL)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_ORANGE = registerWithTab("bell_pepper_roll_orange", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_ROLL)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_WHITE = registerWithTab("bell_pepper_roll_white", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_ROLL)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_PINK = registerWithTab("bell_pepper_roll_pink", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_ROLL)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_BLUE = registerWithTab("bell_pepper_roll_blue", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_ROLL)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_PURPLE = registerWithTab("bell_pepper_roll_purple", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_ROLL)), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_BLACK = registerWithTab("bell_pepper_roll_black", new Item(
            baseItem().food(FoodValues.BELL_PEPPER_ROLL)), ItemFamily.BELL_PEPPER);
    public static final Item CALAMARI_ROLL = registerWithTab("calamari_roll", new Item(
            baseItem().food(FoodValues.CALAMARI_ROLL)), ItemFamily.CALAMARI);
    public static final Item CHERRY_BLOSSOM_ROLL = registerWithTab("cherry_blossom_roll", new Item(
            baseItem().food(FoodValues.CHERRY_BLOSSOM_ROLL)), ItemFamily.CHERRY_BLOSSOM_FOODS);

    // Soups and stews
    public static final Item BELL_PEPPER_SOUP = registerWithTab("bell_pepper_soup", new ConsumableItem(
            bowlItem().food(FoodValues.BELL_PEPPER_SOUP), true), ItemFamily.BELL_PEPPER);
    public static final Item CALAMARI_SOUP = registerWithTab("calamari_soup", new ConsumableItem(
            bowlItem().food(FoodValues.CALAMARI_SOUP), true), ItemFamily.CALAMARI);

    // Plated meals
    public static final Item BELL_PEPPER_PASTA = registerWithTab("bell_pepper_pasta", new ConsumableItem(
            bowlItem().food(FoodValues.BELL_PEPPER_PASTA), true), ItemFamily.BELL_PEPPER);
    public static final Item FRIED_CALAMARI = registerWithTab("fried_calamari", new ConsumableItem(
            bowlItem().food(FoodValues.FRIED_CALAMARI), true), ItemFamily.CALAMARI, ItemFamily.FRIED_FOODS);
    public static final Item FRIED_CHICKEN = registerWithTab("fried_chicken", new ConsumableItem(
            bowlItem().food(FoodValues.FRIED_CHICKEN), true), ItemFamily.FRIED_FOODS);
    public static final Item FRIED_MUSHROOMS = registerWithTab("fried_mushrooms", new ConsumableItem(
            bowlItem().food(FoodValues.FRIED_MUSHROOMS), true), ItemFamily.FRIED_FOODS);
    public static final Item COFFEE_BRAISED_BEEF = registerWithTab("coffee_braised_beef", new ConsumableItem(
            bowlItem().food(FoodValues.COFFEE_BRAISED_BEEF), true), ItemFamily.COFFEE);

    // Feasts
    // Also needs a Cherry Blossom Roll, so it follows that family too.
    public static final Item RICE_ROLL_ROYALE = registerPlaceableBlockWithTab(ModBlocks.RICE_ROLL_ROYALE, feastItem(), ItemFamily.BELL_PEPPER, ItemFamily.CALAMARI, ItemFamily.CHERRY_BLOSSOM_FOODS);
    public static final Item BELL_PEPPER_MEDLEY = registerPlaceableBlockWithTab(ModBlocks.BELL_PEPPER_MEDLEY, feastItem(), ItemFamily.BELL_PEPPER);
    public static final Item PALE_BELL_PEPPER_MEDLEY = registerPlaceableBlockWithTab(ModBlocks.PALE_BELL_PEPPER_MEDLEY, feastItem(), ItemFamily.BELL_PEPPER);
    public static final Item DARK_BELL_PEPPER_MEDLEY = registerPlaceableBlockWithTab(ModBlocks.DARK_BELL_PEPPER_MEDLEY, feastItem(), ItemFamily.BELL_PEPPER);

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

    public static Item.Settings feastItem() {
        return baseItem().recipeRemainder(Items.BOWL).maxCount(1);
    }

    // Registry functions
    private static Item registerWithTab(final String name, final Item item, final ItemFamily... families) {
        Item registered = Registry.register(Registries.ITEM, Identifier.of(RusticDelight.MOD_ID, name), item);
        CREATIVE_TAB_ITEMS.put(registered, families);
        return registered;
    }

    private static Item registerBlockWithTab(Block block, ItemFamily... families) {
        return registerWithTab(Registries.BLOCK.getId(block).getPath(), new BlockItem(block, baseItem()), families);
    }

    private static Item registerPlaceableBlockWithTab(Block block, Item.Settings settings, ItemFamily... families) {
        return registerWithTab(Registries.BLOCK.getId(block).getPath(), new PlaceableItem(block, settings), families);
    }

    public static void registerModItems() {
        RusticDelight.LOGGER.info("Registering items for " + RusticDelight.MOD_ID);
    }
}

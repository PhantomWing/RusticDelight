package com.phantomwing.rusticdelight.item;

import com.google.common.collect.Maps;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.food.ConsumableValues;
import com.phantomwing.rusticdelight.food.FoodValues;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.PlaceableItem;

import java.util.LinkedHashMap;
import java.util.function.Function;

public class ModItems {
    public static final int BOWL_STACK_SIZE = 16;
    public static final int BOTTLE_STACK_SIZE = 16;

    public static LinkedHashMap<Item, ItemFamily[]> CREATIVE_TAB_ITEMS = Maps.newLinkedHashMap();

    public static final Item COTTON_SEEDS_BAG = registerBlockWithTab(ModBlocks.COTTON_SEEDS_BAG, ItemFamily.COTTON);
    public static final Item BELL_PEPPER_SEEDS_BAG = registerBlockWithTab(ModBlocks.BELL_PEPPER_SEEDS_BAG, ItemFamily.BELL_PEPPER);
    public static final Item PALE_BELL_PEPPER_SEEDS_BAG = registerBlockWithTab(ModBlocks.PALE_BELL_PEPPER_SEEDS_BAG, ItemFamily.BELL_PEPPER);
    public static final Item DARK_BELL_PEPPER_SEEDS_BAG = registerBlockWithTab(ModBlocks.DARK_BELL_PEPPER_SEEDS_BAG, ItemFamily.BELL_PEPPER);
    public static final Item COFFEE_BEANS_BAG = registerBlockWithTab(ModBlocks.COFFEE_BEANS_BAG, ItemFamily.COFFEE);
    public static final Item ROASTED_COFFEE_BEANS_BAG = registerBlockWithTab(ModBlocks.ROASTED_COFFEE_BEANS_BAG, ItemFamily.COFFEE);
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
    public static final Item BELL_PEPPER_GREEN_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_GREEN_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_YELLOW_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_YELLOW_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_RED_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_RED_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ORANGE_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_ORANGE_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_WHITE_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_WHITE_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_PINK_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_PINK_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_BLUE_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_BLUE_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_PURPLE_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_PURPLE_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_BLACK_BLOCK = registerBlockWithTab(ModBlocks.BELL_PEPPER_BLACK_BLOCK, ItemFamily.BELL_PEPPER);
    public static final Item WILD_COTTON = registerBlockWithTab(ModBlocks.WILD_COTTON, ItemFamily.COTTON);
    public static final Item WILD_BELL_PEPPERS = registerBlockWithTab(ModBlocks.WILD_BELL_PEPPERS, ItemFamily.BELL_PEPPER);
    public static final Item WILD_PALE_BELL_PEPPERS = registerBlockWithTab(ModBlocks.WILD_PALE_BELL_PEPPERS, ItemFamily.BELL_PEPPER);
    public static final Item WILD_DARK_BELL_PEPPERS = registerBlockWithTab(ModBlocks.WILD_DARK_BELL_PEPPERS, ItemFamily.BELL_PEPPER);
    public static final Item WILD_COFFEE = registerBlockWithTab(ModBlocks.WILD_COFFEE, ItemFamily.COFFEE);
    public static final Item COTTON_SEEDS = registerItemNameBlockWithTab("cotton_seeds", ModBlocks.COTTON_CROP, ItemFamily.COTTON);
    public static final Item BELL_PEPPER_SEEDS = registerItemNameBlockWithTab("bell_pepper_seeds", ModBlocks.BELL_PEPPER_CROP, ItemFamily.BELL_PEPPER);
    public static final Item PALE_BELL_PEPPER_SEEDS = registerItemNameBlockWithTab("pale_bell_pepper_seeds", ModBlocks.PALE_BELL_PEPPER_CROP, ItemFamily.BELL_PEPPER);
    public static final Item DARK_BELL_PEPPER_SEEDS = registerItemNameBlockWithTab("dark_bell_pepper_seeds", ModBlocks.DARK_BELL_PEPPER_CROP, ItemFamily.BELL_PEPPER);
    public static final Item COTTON_BOLL = registerWithTab("cotton_boll", baseItem(), ItemFamily.COTTON);
    public static final Item BELL_PEPPER_GREEN = registerWithTab("bell_pepper_green", foodItem(FoodValues.BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_YELLOW = registerWithTab("bell_pepper_yellow", foodItem(FoodValues.BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_RED = registerWithTab("bell_pepper_red", foodItem(FoodValues.BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ORANGE = registerWithTab("bell_pepper_orange", foodItem(FoodValues.BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_WHITE = registerWithTab("bell_pepper_white", foodItem(FoodValues.BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_PINK = registerWithTab("bell_pepper_pink", foodItem(FoodValues.BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_BLUE = registerWithTab("bell_pepper_blue", foodItem(FoodValues.BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_PURPLE = registerWithTab("bell_pepper_purple", foodItem(FoodValues.BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_BLACK = registerWithTab("bell_pepper_black", foodItem(FoodValues.BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_GREEN = registerWithTab("roasted_bell_pepper_green", foodItem(FoodValues.ROASTED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_YELLOW = registerWithTab("roasted_bell_pepper_yellow", foodItem(FoodValues.ROASTED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_RED = registerWithTab("roasted_bell_pepper_red", foodItem(FoodValues.ROASTED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_ORANGE = registerWithTab("roasted_bell_pepper_orange", foodItem(FoodValues.ROASTED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_WHITE = registerWithTab("roasted_bell_pepper_white", foodItem(FoodValues.ROASTED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_PINK = registerWithTab("roasted_bell_pepper_pink", foodItem(FoodValues.ROASTED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_BLUE = registerWithTab("roasted_bell_pepper_blue", foodItem(FoodValues.ROASTED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_PURPLE = registerWithTab("roasted_bell_pepper_purple", foodItem(FoodValues.ROASTED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_BLACK = registerWithTab("roasted_bell_pepper_black", foodItem(FoodValues.ROASTED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item CALAMARI = registerWithTab("calamari", foodItem(FoodValues.CALAMARI), ItemFamily.CALAMARI);
    public static final Item COOKED_CALAMARI = registerWithTab("cooked_calamari", foodItem(FoodValues.COOKED_CALAMARI), ItemFamily.CALAMARI);
    public static final Item COFFEE_BEANS = registerItemNameBlockWithTab("coffee_beans", ModBlocks.COFFEE_CROP, ItemFamily.COFFEE);
    public static final Item ROASTED_COFFEE_BEANS = registerWithTab("roasted_coffee_beans", foodItem(FoodValues.ROASTED_COFFEE_BEANS), ItemFamily.COFFEE);
    public static final Item GOLDEN_COFFEE_BEANS = registerWithTab("golden_coffee_beans", foodItem(FoodValues.GOLDEN_COFFEE_BEANS), ItemFamily.COFFEE);
    public static final Item COFFEE = registerWithTab("coffee", props -> new ConsumableItem(props, true), bottleFoodItem(FoodValues.COFFEE, ConsumableValues.COFFEE), ItemFamily.COFFEE);
    public static final Item DARK_COFFEE = registerWithTab("dark_coffee", props -> new ConsumableItem(props, true), bottleFoodItem(FoodValues.DARK_COFFEE, ConsumableValues.DARK_COFFEE), ItemFamily.COFFEE);
    public static final Item MILK_COFFEE = registerWithTab("milk_coffee", props -> new ConsumableItem(props, true, true), bottleFoodItem(FoodValues.MILK_COFFEE, ConsumableValues.MILK_COFFEE), ItemFamily.COFFEE);
    public static final Item CHOCOLATE_COFFEE = registerWithTab("chocolate_coffee", props -> new ConsumableItem(props, true, true), bottleFoodItem(FoodValues.CHOCOLATE_COFFEE, ConsumableValues.CHOCOLATE_COFFEE), ItemFamily.COFFEE);
    public static final Item HONEY_COFFEE = registerWithTab("honey_coffee", props -> new ConsumableItem(props, true, true), bottleFoodItem(FoodValues.HONEY_COFFEE, ConsumableValues.HONEY_COFFEE), ItemFamily.COFFEE);
    public static final Item SYRUP_COFFEE = registerWithTab("syrup_coffee", props -> new ConsumableItem(props, true, true), bottleFoodItem(FoodValues.SYRUP_COFFEE, ConsumableValues.SYRUP_COFFEE), ItemFamily.COFFEE, ItemFamily.SYRUP_FOODS);
    public static final Item PUMPKIN_COFFEE = registerWithTab("pumpkin_coffee", props -> new ConsumableItem(props, true, true), bottleFoodItem(FoodValues.PUMPKIN_COFFEE, ConsumableValues.PUMPKIN_COFFEE), ItemFamily.COFFEE);
    public static final Item CHERRY_BLOSSOM_COFFEE = registerWithTab("cherry_blossom_coffee", props -> new ConsumableItem(props, true, true), bottleFoodItem(FoodValues.CHERRY_BLOSSOM_COFFEE, ConsumableValues.CHERRY_BLOSSOM_COFFEE), ItemFamily.COFFEE, ItemFamily.CHERRY_BLOSSOM_FOODS);
    public static final Item COOKING_OIL = registerWithTab("cooking_oil", ConsumableItem::new, bottleFoodItem(FoodValues.COOKING_OIL, ConsumableValues.COOKING_OIL), ItemFamily.FRIED_FOODS);
    public static final Item SYRUP = registerWithTab("syrup", props -> new ConsumableItem(props, true), bottleFoodItem(FoodValues.SYRUP, ConsumableValues.SYRUP), ItemFamily.SYRUP_FOODS);
    public static final Item BATTER = registerWithTab("batter", ConsumableItem::new, bowlFoodItem(FoodValues.BATTER, ConsumableValues.BATTER), ItemFamily.BATTER);
    public static final Item POTATO_SLICES = registerWithTab("potato_slices", foodItem(FoodValues.POTATO_SLICES, ConsumableValues.FAST_FOOD), ItemFamily.POTATO_SLICES);
    public static final Item BAKED_POTATO_SLICES = registerWithTab("baked_potato_slices", foodItem(FoodValues.BAKED_POTATO_SLICES, ConsumableValues.FAST_FOOD), ItemFamily.POTATO_SLICES);
    public static final Item BELL_PEPPER_SLICE_GREEN = registerWithTab("bell_pepper_slice_green", foodItem(FoodValues.BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_YELLOW = registerWithTab("bell_pepper_slice_yellow", foodItem(FoodValues.BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_RED = registerWithTab("bell_pepper_slice_red", foodItem(FoodValues.BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_ORANGE = registerWithTab("bell_pepper_slice_orange", foodItem(FoodValues.BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_WHITE = registerWithTab("bell_pepper_slice_white", foodItem(FoodValues.BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_PINK = registerWithTab("bell_pepper_slice_pink", foodItem(FoodValues.BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_BLUE = registerWithTab("bell_pepper_slice_blue", foodItem(FoodValues.BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_PURPLE = registerWithTab("bell_pepper_slice_purple", foodItem(FoodValues.BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_SLICE_BLACK = registerWithTab("bell_pepper_slice_black", foodItem(FoodValues.BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_GREEN = registerWithTab("roasted_bell_pepper_slice_green", foodItem(FoodValues.ROASTED_BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_YELLOW = registerWithTab("roasted_bell_pepper_slice_yellow", foodItem(FoodValues.ROASTED_BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_RED = registerWithTab("roasted_bell_pepper_slice_red", foodItem(FoodValues.ROASTED_BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_ORANGE = registerWithTab("roasted_bell_pepper_slice_orange", foodItem(FoodValues.ROASTED_BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_WHITE = registerWithTab("roasted_bell_pepper_slice_white", foodItem(FoodValues.ROASTED_BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_PINK = registerWithTab("roasted_bell_pepper_slice_pink", foodItem(FoodValues.ROASTED_BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_BLUE = registerWithTab("roasted_bell_pepper_slice_blue", foodItem(FoodValues.ROASTED_BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_PURPLE = registerWithTab("roasted_bell_pepper_slice_purple", foodItem(FoodValues.ROASTED_BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item ROASTED_BELL_PEPPER_SLICE_BLACK = registerWithTab("roasted_bell_pepper_slice_black", foodItem(FoodValues.ROASTED_BELL_PEPPER_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.BELL_PEPPER);
    public static final Item CALAMARI_SLICE = registerWithTab("calamari_slice", foodItem(FoodValues.CALAMARI_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.CALAMARI);
    public static final Item COOKED_CALAMARI_SLICE = registerWithTab("cooked_calamari_slice", foodItem(FoodValues.COOKED_CALAMARI_SLICE, ConsumableValues.FAST_FOOD), ItemFamily.CALAMARI);
    public static final Item SYRUP_CHEESECAKE = registerPlaceableBlockWithTab(ModBlocks.SYRUP_CHEESECAKE, baseItem(), ItemFamily.SYRUP_FOODS);
    public static final Item SYRUP_CHEESECAKE_SLICE = registerWithTab("syrup_cheesecake_slice", foodItem(
            vectorwing.farmersdelight.common.FoodValues.PIE_SLICE,
            vectorwing.farmersdelight.common.FoodValues.ConsumableValues.PIE_SLICE
    ), ItemFamily.SYRUP_FOODS);
    public static final Item CHERRY_BLOSSOM_CHEESECAKE = registerPlaceableBlockWithTab(ModBlocks.CHERRY_BLOSSOM_CHEESECAKE, baseItem(), ItemFamily.CHERRY_BLOSSOM_FOODS);
    public static final Item CHERRY_BLOSSOM_CHEESECAKE_SLICE = registerWithTab("cherry_blossom_cheesecake_slice", foodItem(
            vectorwing.farmersdelight.common.FoodValues.PIE_SLICE,
            vectorwing.farmersdelight.common.FoodValues.ConsumableValues.PIE_SLICE
    ), ItemFamily.CHERRY_BLOSSOM_FOODS);
    public static final Item COFFEE_CHEESECAKE = registerPlaceableBlockWithTab(ModBlocks.COFFEE_CHEESECAKE, baseItem(), ItemFamily.COFFEE);
    public static final Item COFFEE_CHEESECAKE_SLICE = registerWithTab("coffee_cheesecake_slice", foodItem(
            vectorwing.farmersdelight.common.FoodValues.PIE_SLICE,
            ConsumableValues.COFFEE_CHEESECAKE_SLICE
    ), ItemFamily.COFFEE);
    public static final Item SYRUP_COOKIE = registerWithTab("syrup_cookie", foodItem(
            vectorwing.farmersdelight.common.FoodValues.COOKIES, 
            vectorwing.farmersdelight.common.FoodValues.ConsumableValues.FAST_FOOD
    ), ItemFamily.SYRUP_FOODS);
    public static final Item CHERRY_BLOSSOM_COOKIE = registerWithTab("cherry_blossom_cookie", foodItem(
            vectorwing.farmersdelight.common.FoodValues.COOKIES,
            vectorwing.farmersdelight.common.FoodValues.ConsumableValues.FAST_FOOD
    ), ItemFamily.CHERRY_BLOSSOM_FOODS);
    public static final Item COFFEE_COOKIE = registerWithTab("coffee_cookie", foodItem(
            vectorwing.farmersdelight.common.FoodValues.COOKIES,
            vectorwing.farmersdelight.common.FoodValues.ConsumableValues.FAST_FOOD
    ), ItemFamily.COFFEE);
    public static final Item SYRUP_SANDWICH = registerWithTab("syrup_sandwich", foodItem(FoodValues.SYRUP_SANDWICH), ItemFamily.SYRUP_FOODS);
    public static final Item FRUIT_BEIGNET = registerWithTab("fruit_beignet", props -> new ConsumableItem(props, true), foodItem(FoodValues.FRUIT_BEIGNET, ConsumableValues.FRUIT_BEIGNET), ItemFamily.FRIED_FOODS);
    public static final Item PANCAKES = registerPlaceableBlockWithTab(ModBlocks.PANCAKES, bowlItem(), ItemFamily.PANCAKES, ItemFamily.SYRUP_FOODS);
    public static final Item PANCAKE = registerWithTab("pancake", foodItem(FoodValues.PANCAKE), ItemFamily.PANCAKES, ItemFamily.SYRUP_FOODS);
    public static final Item HONEY_PANCAKES = registerPlaceableBlockWithTab(ModBlocks.HONEY_PANCAKES, bowlItem(), ItemFamily.PANCAKES);
    public static final Item HONEY_PANCAKE = registerWithTab("honey_pancake", props -> new ConsumableItem(props, true), foodItem(FoodValues.HONEY_PANCAKE, ConsumableValues.HONEY_PANCAKE), ItemFamily.PANCAKES);
    public static final Item CHOCOLATE_PANCAKES = registerPlaceableBlockWithTab(ModBlocks.CHOCOLATE_PANCAKES, bowlItem(), ItemFamily.PANCAKES);
    public static final Item CHOCOLATE_PANCAKE = registerWithTab("chocolate_pancake", props -> new ConsumableItem(props, true), foodItem(FoodValues.CHOCOLATE_PANCAKE, ConsumableValues.CHOCOLATE_PANCAKE), ItemFamily.PANCAKES);
    public static final Item CHERRY_BLOSSOM_PANCAKES = registerPlaceableBlockWithTab(ModBlocks.CHERRY_BLOSSOM_PANCAKES, bowlItem(), ItemFamily.CHERRY_BLOSSOM_FOODS, ItemFamily.PANCAKES);
    public static final Item CHERRY_BLOSSOM_PANCAKE = registerWithTab("cherry_blossom_pancake", props -> new ConsumableItem(props, true), foodItem(FoodValues.CHERRY_BLOSSOM_PANCAKE, ConsumableValues.CHERRY_BLOSSOM_PANCAKE), ItemFamily.CHERRY_BLOSSOM_FOODS, ItemFamily.PANCAKES);
    public static final Item VEGETABLE_PANCAKES = registerPlaceableBlockWithTab(ModBlocks.VEGETABLE_PANCAKES, bowlItem(), ItemFamily.PANCAKES);
    public static final Item VEGETABLE_PANCAKE = registerWithTab("vegetable_pancake", props -> new ConsumableItem(props, true), foodItem(FoodValues.VEGETABLE_PANCAKE, ConsumableValues.VEGETABLE_PANCAKE), ItemFamily.PANCAKES);
    public static final Item PUMPKIN_PANCAKES = registerPlaceableBlockWithTab(ModBlocks.PUMPKIN_PANCAKES, bowlItem(), ItemFamily.PANCAKES, ItemFamily.SYRUP_FOODS);
    public static final Item PUMPKIN_PANCAKE = registerWithTab("pumpkin_pancake", props -> new ConsumableItem(props, true), foodItem(FoodValues.PUMPKIN_PANCAKE, ConsumableValues.PUMPKIN_PANCAKE), ItemFamily.PANCAKES, ItemFamily.SYRUP_FOODS);
    public static final Item COFFEE_PANCAKES = registerPlaceableBlockWithTab(ModBlocks.COFFEE_PANCAKES, bowlItem(), ItemFamily.PANCAKES, ItemFamily.COFFEE);
    public static final Item COFFEE_PANCAKE = registerWithTab("coffee_pancake", props -> new ConsumableItem(props, true), foodItem(FoodValues.COFFEE_PANCAKE, ConsumableValues.COFFEE_PANCAKE), ItemFamily.PANCAKES, ItemFamily.COFFEE);
    public static final Item POTATO_SALAD = registerWithTab("potato_salad", props -> new ConsumableItem(props, true), bowlFoodItem(FoodValues.POTATO_SALAD, ConsumableValues.POTATO_SALAD));
    public static final Item SWEET_SALAD = registerWithTab("sweet_salad", props -> new ConsumableItem(props, true), bowlFoodItem(FoodValues.SWEET_SALAD, ConsumableValues.SWEET_SALAD));
    public static final Item STUFFED_BELL_PEPPER_GREEN = registerWithTab("stuffed_bell_pepper_green", foodItem(FoodValues.STUFFED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_YELLOW = registerWithTab("stuffed_bell_pepper_yellow", foodItem(FoodValues.STUFFED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_RED = registerWithTab("stuffed_bell_pepper_red", foodItem(FoodValues.STUFFED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_ORANGE = registerWithTab("stuffed_bell_pepper_orange", foodItem(FoodValues.STUFFED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_WHITE = registerWithTab("stuffed_bell_pepper_white", foodItem(FoodValues.STUFFED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_PINK = registerWithTab("stuffed_bell_pepper_pink", foodItem(FoodValues.STUFFED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_BLUE = registerWithTab("stuffed_bell_pepper_blue", foodItem(FoodValues.STUFFED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_PURPLE = registerWithTab("stuffed_bell_pepper_purple", foodItem(FoodValues.STUFFED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item STUFFED_BELL_PEPPER_BLACK = registerWithTab("stuffed_bell_pepper_black", foodItem(FoodValues.STUFFED_BELL_PEPPER), ItemFamily.BELL_PEPPER);
    public static final Item FRIED_DOUGH = registerWithTab("fried_dough", foodItem(FoodValues.FRIED_DOUGH), ItemFamily.FRIED_FOODS);
    public static final Item FRIED_DUMPLINGS = registerWithTab("fried_dumplings", foodItem(FoodValues.FRIED_DUMPLINGS), ItemFamily.FRIED_FOODS);
    public static final Item SPRING_ROLLS = registerWithTab("spring_rolls", foodItem(FoodValues.SPRING_ROLLS), ItemFamily.FRIED_FOODS);
    public static final Item FRIED_FISH = registerWithTab("fried_fish", foodItem(FoodValues.FRIED_FISH), ItemFamily.FRIED_FOODS);
    public static final Item BELL_PEPPER_ROLL_GREEN = registerWithTab("bell_pepper_roll_green", foodItem(FoodValues.BELL_PEPPER_ROLL), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_YELLOW = registerWithTab("bell_pepper_roll_yellow", foodItem(FoodValues.BELL_PEPPER_ROLL), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_RED = registerWithTab("bell_pepper_roll_red", foodItem(FoodValues.BELL_PEPPER_ROLL), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_ORANGE = registerWithTab("bell_pepper_roll_orange", foodItem(FoodValues.BELL_PEPPER_ROLL), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_WHITE = registerWithTab("bell_pepper_roll_white", foodItem(FoodValues.BELL_PEPPER_ROLL), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_PINK = registerWithTab("bell_pepper_roll_pink", foodItem(FoodValues.BELL_PEPPER_ROLL), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_BLUE = registerWithTab("bell_pepper_roll_blue", foodItem(FoodValues.BELL_PEPPER_ROLL), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_PURPLE = registerWithTab("bell_pepper_roll_purple", foodItem(FoodValues.BELL_PEPPER_ROLL), ItemFamily.BELL_PEPPER);
    public static final Item BELL_PEPPER_ROLL_BLACK = registerWithTab("bell_pepper_roll_black", foodItem(FoodValues.BELL_PEPPER_ROLL), ItemFamily.BELL_PEPPER);
    public static final Item CALAMARI_ROLL = registerWithTab("calamari_roll", foodItem(FoodValues.CALAMARI_ROLL), ItemFamily.CALAMARI);
    public static final Item CHERRY_BLOSSOM_ROLL = registerWithTab("cherry_blossom_roll", foodItem(FoodValues.CHERRY_BLOSSOM_ROLL), ItemFamily.CHERRY_BLOSSOM_FOODS);
    public static final Item BELL_PEPPER_SOUP = registerWithTab("bell_pepper_soup", props -> new ConsumableItem(props, true),
            bowlFoodItem(FoodValues.BELL_PEPPER_SOUP, ConsumableValues.BELL_PEPPER_SOUP), ItemFamily.BELL_PEPPER);
    public static final Item CALAMARI_SOUP = registerWithTab("calamari_soup", props -> new ConsumableItem(props, true),
            bowlFoodItem(FoodValues.CALAMARI_SOUP, ConsumableValues.CALAMARI_SOUP), ItemFamily.CALAMARI);
    public static final Item BELL_PEPPER_PASTA = registerWithTab("bell_pepper_pasta", props -> new ConsumableItem(props, true),
            bowlFoodItem(FoodValues.BELL_PEPPER_PASTA, ConsumableValues.BELL_PEPPER_PASTA), ItemFamily.BELL_PEPPER);
    public static final Item FRIED_CALAMARI = registerWithTab("fried_calamari", props -> new ConsumableItem(props, true),
            bowlFoodItem(FoodValues.FRIED_CALAMARI, ConsumableValues.FRIED_CALAMARI), ItemFamily.CALAMARI, ItemFamily.FRIED_FOODS);
    public static final Item FRIED_CHICKEN = registerWithTab("fried_chicken", props -> new ConsumableItem(props, true),
            bowlFoodItem(FoodValues.FRIED_CHICKEN, ConsumableValues.FRIED_CHICKEN), ItemFamily.FRIED_FOODS);
    public static final Item FRIED_MUSHROOMS = registerWithTab("fried_mushrooms", props -> new ConsumableItem(props, true),
            bowlFoodItem(FoodValues.FRIED_MUSHROOMS, ConsumableValues.FRIED_MUSHROOMS), ItemFamily.FRIED_FOODS);
    public static final Item COFFEE_BRAISED_BEEF = registerWithTab("coffee_braised_beef", props -> new ConsumableItem(props, true),
            bowlFoodItem(FoodValues.COFFEE_BRAISED_BEEF, ConsumableValues.COFFEE_BRAISED_BEEF), ItemFamily.COFFEE);
    public static final Item RICE_ROLL_ROYALE = registerPlaceableBlockWithTab(ModBlocks.RICE_ROLL_ROYALE, feastItem(), ItemFamily.BELL_PEPPER, ItemFamily.CALAMARI, ItemFamily.CHERRY_BLOSSOM_FOODS);
    public static final Item BELL_PEPPER_MEDLEY = registerPlaceableBlockWithTab(ModBlocks.BELL_PEPPER_MEDLEY, feastItem(), ItemFamily.BELL_PEPPER);
    public static final Item PALE_BELL_PEPPER_MEDLEY = registerPlaceableBlockWithTab(ModBlocks.PALE_BELL_PEPPER_MEDLEY, feastItem(), ItemFamily.BELL_PEPPER);
    public static final Item DARK_BELL_PEPPER_MEDLEY = registerPlaceableBlockWithTab(ModBlocks.DARK_BELL_PEPPER_MEDLEY, feastItem(), ItemFamily.BELL_PEPPER);

    // Helper functions
    public static Item.Properties baseItem() {
        return new Item.Properties();
    }

    public static Item.Properties foodItem(FoodProperties food) {
        return foodItem(food, Consumables.DEFAULT_FOOD);
    }

    public static Item.Properties foodItem(FoodProperties food, Consumable consumable) {
        return baseItem().food(food).component(DataComponents.CONSUMABLE, consumable);
    }

    public static Item.Properties bottleFoodItem(FoodProperties food) {
        return bottleFoodItem(food, Consumables.DEFAULT_DRINK);
    }

    public static Item.Properties bottleFoodItem(FoodProperties food, Consumable consumable) {
        return foodItem(food, consumable).craftRemainder(Items.GLASS_BOTTLE).stacksTo(BOTTLE_STACK_SIZE);
    }

    public static Item.Properties bowlFoodItem(FoodProperties food, @Nullable Consumable consumable) {
        return foodItem(food, consumable).craftRemainder(Items.BOWL).stacksTo(BOWL_STACK_SIZE);
    }

    public static Item.Properties bowlItem() {
        return baseItem().craftRemainder(Items.BOWL).stacksTo(BOWL_STACK_SIZE);
    }

    public static Item.Properties feastItem() {
        return baseItem().craftRemainder(Items.BOWL).stacksTo(1);
    }

    // Registry functions
    private static Item registerWithTab(String name, Item.Properties props, ItemFamily... families) {
        return registerWithTab(name, Item::new, props, families);
    }

    private static Item registerWithTab(String name, Function<Item.Properties, Item> function, Item.Properties props, ItemFamily... families) {
        Identifier loc = Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, name);
        props.setId(ResourceKey.create(Registries.ITEM, loc));

        Item item = function.apply(props);
        CREATIVE_TAB_ITEMS.put(item, families);

        return Registry.register(BuiltInRegistries.ITEM, loc, item);
    }

    private static Item registerBlockWithTab(Block block, ItemFamily... families) {
        return registerBlockWithTab(block, baseItem(), families);
    }

    private static Item registerBlockWithTab(Block block, Item.Properties props, ItemFamily... families) {
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
        Identifier loc = Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, name);

        props.useBlockDescriptionPrefix();
        props.setId(ResourceKey.create(Registries.ITEM, loc));

        BlockItem item = new BlockItem(block, props);
        CREATIVE_TAB_ITEMS.put(item, families);

        return Registry.register(BuiltInRegistries.ITEM, loc, item);
    }

    /** Like registerBlockWithTab, but FDR's PlaceableItem — it adds the "Placeable" tooltip. */
    private static Item registerPlaceableBlockWithTab(Block block, Item.Properties props, ItemFamily... families) {
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
        Identifier loc = Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, name);

        props.useBlockDescriptionPrefix();
        props.setId(ResourceKey.create(Registries.ITEM, loc));

        PlaceableItem item = new PlaceableItem(block, props);
        CREATIVE_TAB_ITEMS.put(item, families);

        return Registry.register(BuiltInRegistries.ITEM, loc, item);
    }

    private static Item registerItemNameBlockWithTab(String name, Block block, ItemFamily... families) {
        return registerItemNameBlockWithTab(name, block, baseItem(), families);
    }

    private static Item registerItemNameBlockWithTab(String name, Block block, Item.Properties props, ItemFamily... families) {
        Identifier loc = Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, name);

        props.setId(ResourceKey.create(Registries.ITEM, loc));

        BlockItem item = new BlockItem(block, props);
        CREATIVE_TAB_ITEMS.put(item, families);

        return Registry.register(BuiltInRegistries.ITEM, loc, item);
    }

    public static void registerModItems() {
        RusticDelight.LOGGER.info("Registering items for " + RusticDelight.MOD_ID);
    }
}

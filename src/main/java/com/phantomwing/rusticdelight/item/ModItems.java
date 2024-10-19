package com.phantomwing.rusticdelight.item;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.food.FoodValues;
import com.google.common.collect.Sets;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class ModItems {
    public static final int BOWL_STACK_SIZE = 16;
    public static final int BOTTLE_STACK_SIZE = 16;

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RusticDelight.MOD_ID);
    public static LinkedHashSet<Supplier<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

    // Seed bags
    public static final DeferredItem<Item> COTTON_SEEDS_BAG = registerBlockWithTab(ModBlocks.COTTON_SEEDS_BAG);
    public static final DeferredItem<Item> BELL_PEPPER_SEEDS_BAG = registerBlockWithTab(ModBlocks.BELL_PEPPER_SEEDS_BAG);
    public static final DeferredItem<Item> COFFEE_BEANS_BAG = registerBlockWithTab(ModBlocks.COFFEE_BEANS_BAG);

    // Crop Crates
    public static final DeferredItem<Item> COTTON_BOLL_CRATE = registerBlockWithTab(ModBlocks.COTTON_BOLL_CRATE);
    public static final DeferredItem<Item> BELL_PEPPER_GREEN_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_GREEN_CRATE);
    public static final DeferredItem<Item> BELL_PEPPER_YELLOW_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_YELLOW_CRATE);
    public static final DeferredItem<Item> BELL_PEPPER_RED_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_RED_CRATE);

    // Wild crops
    public static final DeferredItem<Item> WILD_COTTON = registerBlockWithTab(ModBlocks.WILD_COTTON);
    public static final DeferredItem<Item> WILD_BELL_PEPPERS = registerBlockWithTab(ModBlocks.WILD_BELL_PEPPERS);
    public static final DeferredItem<Item> WILD_COFFEE = registerBlockWithTab(ModBlocks.WILD_COFFEE);

    // Crop products
    public static final DeferredItem<Item> COTTON_BOLL = registerWithTab("cotton_boll", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BELL_PEPPER_GREEN = registerWithTab("bell_pepper_green", () -> new Item(
            new Item.Properties().food(FoodValues.BELL_PEPPER)));
    public static final DeferredItem<Item> BELL_PEPPER_YELLOW = registerWithTab("bell_pepper_yellow", () -> new Item(
            new Item.Properties().food(FoodValues.BELL_PEPPER)));
    public static final DeferredItem<Item> BELL_PEPPER_RED = registerWithTab("bell_pepper_red", () -> new Item(
            new Item.Properties().food(FoodValues.BELL_PEPPER)));
    public static final DeferredItem<Item> COFFEE_BEANS = registerWithTab("coffee_beans", () -> new ItemNameBlockItem(
            ModBlocks.COFFEE_CROP.get(),
            new Item.Properties().food(FoodValues.COFFEE_BEANS)));

    // Crop seeds
    public static final DeferredItem<Item> COTTON_SEEDS = registerWithTab("cotton_seeds", () -> new ItemNameBlockItem(
            ModBlocks.COTTON_CROP.get(),
            new Item.Properties()));
    public static final DeferredItem<Item> BELL_PEPPER_SEEDS = registerWithTab("bell_pepper_seeds", () -> new ItemNameBlockItem(
            ModBlocks.BELL_PEPPER_CROP.get(),
            new Item.Properties()));


    // Basic food
    public static final DeferredItem<Item> CALAMARI = registerWithTab("calamari", () -> new Item(
            new Item.Properties().food(FoodValues.CALAMARI)));
    public static final DeferredItem<Item> COOKED_CALAMARI = registerWithTab("cooked_calamari", () -> new Item(
            new Item.Properties().food(FoodValues.COOKED_CALAMARI)));
    public static final DeferredItem<Item> ROASTED_BELL_PEPPER_GREEN = registerWithTab("roasted_bell_pepper_green", () -> new Item(
            new Item.Properties().food(FoodValues.ROASTED_BELL_PEPPER)));
    public static final DeferredItem<Item> ROASTED_BELL_PEPPER_YELLOW = registerWithTab("roasted_bell_pepper_yellow", () -> new Item(
            new Item.Properties().food(FoodValues.ROASTED_BELL_PEPPER)));
    public static final DeferredItem<Item> ROASTED_BELL_PEPPER_RED = registerWithTab("roasted_bell_pepper_red", () -> new Item(
            new Item.Properties().food(FoodValues.ROASTED_BELL_PEPPER)));

    // Cooking products
    public static final DeferredItem<Item> COOKING_OIL = registerWithTab("cooking_oil", () -> new DrinkableItem(
            new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(FoodValues.COOKING_OIL).stacksTo(BOTTLE_STACK_SIZE)));
    public static final DeferredItem<Item> BATTER = registerWithTab("batter", () -> new DrinkableItem(
            new Item.Properties().craftRemainder(Items.BOWL).food(FoodValues.BATTER).stacksTo(BOWL_STACK_SIZE)));

    // Sliced food
    public static final DeferredItem<Item> POTATO_SLICES = registerWithTab("potato_slices", () -> new Item(
            new Item.Properties().food(FoodValues.POTATO_SLICES)));
    public static final DeferredItem<Item> BAKED_POTATO_SLICES = registerWithTab("baked_potato_slices", () -> new Item(
            new Item.Properties().food(FoodValues.BAKED_POTATO_SLICES)));
    public static final DeferredItem<Item> CALAMARI_SLICE = registerWithTab("calamari_slice", () -> new Item(
            new Item.Properties().food(FoodValues.CALAMARI_SLICE)));
    public static final DeferredItem<Item> COOKED_CALAMARI_SLICE = registerWithTab("cooked_calamari_slice", () -> new Item(
            new Item.Properties().food(FoodValues.COOKED_CALAMARI_SLICE)));

    // Sweets
    public static final DeferredItem<Item> FRUIT_BEIGNET = registerWithTab("fruit_beignet", () -> new Item(
            new Item.Properties().food(FoodValues.FRUIT_BEIGNET)));
    public static final DeferredItem<Item> CHERRY_BLOSSOM_COOKIE = registerWithTab("cherry_blossom_cookie", () -> new Item(
            new Item.Properties().food(FoodValues.CHERRY_BLOSSOM_COOKIE)));
    public static final DeferredItem<Item> CHERRY_BLOSSOM_CHEESECAKE = registerBlockWithTab(ModBlocks.CHERRY_BLOSSOM_CHEESECAKE, new Item.Properties());
    public static final DeferredItem<Item> CHERRY_BLOSSOM_CHEESECAKE_SLICE = registerWithTab("cherry_blossom_cheesecake_slice", () -> new Item(
            new Item.Properties().food(vectorwing.farmersdelight.common.FoodValues.PIE_SLICE)));
    public static final DeferredItem<Item> HONEY_PANCAKES = registerBlockWithTab(ModBlocks.HONEY_PANCAKES, new Item.Properties().stacksTo(BOWL_STACK_SIZE));
    public static final DeferredItem<Item> CHOCOLATE_PANCAKES = registerBlockWithTab(ModBlocks.CHOCOLATE_PANCAKES, new Item.Properties().stacksTo(BOWL_STACK_SIZE));
    public static final DeferredItem<Item> CHERRY_BLOSSOM_PANCAKES = registerBlockWithTab(ModBlocks.CHERRY_BLOSSOM_PANCAKES, new Item.Properties().stacksTo(BOWL_STACK_SIZE));
    public static final DeferredItem<Item> VEGETABLE_PANCAKES = registerBlockWithTab(ModBlocks.VEGETABLE_PANCAKES, new Item.Properties().stacksTo(BOWL_STACK_SIZE));

    // Basic meals
    public static final DeferredItem<Item> CALAMARI_ROLL = registerWithTab("calamari_roll", () -> new Item(
            new Item.Properties().food(FoodValues.CALAMARI_ROLL)));
    public static final DeferredItem<Item> CHERRY_BLOSSOM_ROLL = registerWithTab("cherry_blossom_roll", () -> new Item(
            new Item.Properties().food(FoodValues.CHERRY_BLOSSOM_ROLL)));
    public static final DeferredItem<Item> POTATO_SALAD = registerWithTab("potato_salad", () -> new Item(
            new Item.Properties().craftRemainder(Items.BOWL).food(FoodValues.POTATO_SALAD).stacksTo(BOWL_STACK_SIZE)));
    public static final DeferredItem<Item> SPRING_ROLLS = registerWithTab("spring_rolls", () -> new Item(
            new Item.Properties().food(FoodValues.SPRING_ROLLS)));
    public static final DeferredItem<Item> STUFFED_BELL_PEPPER_GREEN = registerWithTab("stuffed_bell_pepper_green", () -> new Item(
            new Item.Properties().food(FoodValues.STUFFED_BELL_PEPPER)));
    public static final DeferredItem<Item> STUFFED_BELL_PEPPER_YELLOW = registerWithTab("stuffed_bell_pepper_yellow", () -> new Item(
            new Item.Properties().food(FoodValues.STUFFED_BELL_PEPPER)));
    public static final DeferredItem<Item> STUFFED_BELL_PEPPER_RED = registerWithTab("stuffed_bell_pepper_red", () -> new Item(
            new Item.Properties().food(FoodValues.STUFFED_BELL_PEPPER)));

    // Soups and stews
    public static final DeferredItem<Item> BELL_PEPPER_SOUP = registerWithTab("bell_pepper_soup", () -> new DrinkableItem(
            new Item.Properties().craftRemainder(Items.BOWL).food(FoodValues.BELL_PEPPER_SOUP).stacksTo(BOWL_STACK_SIZE)));

    // Plated meals
    public static final DeferredItem<Item> BELL_PEPPER_PASTA = registerWithTab("bell_pepper_pasta", () -> new Item(
            new Item.Properties().craftRemainder(Items.BOWL).food(FoodValues.BELL_PEPPER_PASTA).stacksTo(BOWL_STACK_SIZE)));
    public static final DeferredItem<Item> FRIED_CALAMARI = registerWithTab("fried_calamari", () -> new Item(
            new Item.Properties().craftRemainder(Items.BOWL).food(FoodValues.FRIED_CALAMARI).stacksTo(BOWL_STACK_SIZE)));
    public static final DeferredItem<Item> FRIED_CHICKEN = registerWithTab("fried_chicken", () -> new Item(
            new Item.Properties().craftRemainder(Items.BOWL).food(FoodValues.FRIED_CHICKEN).stacksTo(BOWL_STACK_SIZE)));
    public static final DeferredItem<Item> FRIED_MUSHROOMS = registerWithTab("fried_mushrooms", () -> new Item(
            new Item.Properties().craftRemainder(Items.BOWL).food(FoodValues.FRIED_MUSHROOMS).stacksTo(BOWL_STACK_SIZE)));

    // Feasts

    public static DeferredItem<Item> registerWithTab(final String name, final Supplier<Item> supplier) {
        DeferredItem<Item> item = ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(item);
        return item;
    }

    public static DeferredItem<Item> registerBlockWithTab(DeferredBlock<Block> block) {
        return registerWithTab(block.getRegisteredName().replaceFirst(RusticDelight.MOD_ID + ":", ""), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static DeferredItem<Item> registerBlockWithTab(DeferredBlock<Block> block, Item.Properties properties) {
        return registerWithTab(block.getRegisteredName().replaceFirst(RusticDelight.MOD_ID + ":", ""), () -> new BlockItem(block.get(), properties));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

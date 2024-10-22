package com.phantomwing.rusticdelight.item;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.block.custom.ChocolateCoffeeItem;
import com.phantomwing.rusticdelight.block.custom.MilkCoffeeItem;
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
import vectorwing.farmersdelight.common.item.ConsumableItem;
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
    public static final DeferredItem<Item> COTTON_BOLL = registerWithTab("cotton_boll", () -> new Item(baseItem()));
    public static final DeferredItem<Item> BELL_PEPPER_GREEN = registerWithTab("bell_pepper_green", () -> new Item(
           baseItem().food(FoodValues.BELL_PEPPER)));
    public static final DeferredItem<Item> BELL_PEPPER_YELLOW = registerWithTab("bell_pepper_yellow", () -> new Item(
            baseItem().food(FoodValues.BELL_PEPPER)));
    public static final DeferredItem<Item> BELL_PEPPER_RED = registerWithTab("bell_pepper_red", () -> new Item(
            baseItem().food(FoodValues.BELL_PEPPER)));

    // Crop seeds
    public static final DeferredItem<Item> COTTON_SEEDS = registerWithTab("cotton_seeds", () -> new ItemNameBlockItem(
            ModBlocks.COTTON_CROP.get(),
            baseItem()));
    public static final DeferredItem<Item> BELL_PEPPER_SEEDS = registerWithTab("bell_pepper_seeds", () -> new ItemNameBlockItem(
            ModBlocks.BELL_PEPPER_CROP.get(),
            baseItem()));

    // Coffee Beans
    public static final DeferredItem<Item> COFFEE_BEANS = registerWithTab("coffee_beans", () -> new ItemNameBlockItem(
            ModBlocks.COFFEE_CROP.get(), baseItem()));
    public static final DeferredItem<Item> ROASTED_COFFEE_BEANS = registerWithTab("roasted_coffee_beans", () -> new Item(
            baseItem().food(FoodValues.ROASTED_COFFEE_BEANS)));
    public static final DeferredItem<Item> GOLDEN_COFFEE_BEANS = registerWithTab("golden_coffee_beans", () -> new Item(
            baseItem().food(FoodValues.GOLDEN_COFFEE_BEANS)));

    // Calamari
    public static final DeferredItem<Item> CALAMARI = registerWithTab("calamari", () -> new Item(
            baseItem().food(FoodValues.CALAMARI)));
    public static final DeferredItem<Item> COOKED_CALAMARI = registerWithTab("cooked_calamari", () -> new Item(
            baseItem().food(FoodValues.COOKED_CALAMARI)));

    // Roasted bell peppers
    public static final DeferredItem<Item> ROASTED_BELL_PEPPER_GREEN = registerWithTab("roasted_bell_pepper_green", () -> new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)));
    public static final DeferredItem<Item> ROASTED_BELL_PEPPER_YELLOW = registerWithTab("roasted_bell_pepper_yellow", () -> new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)));
    public static final DeferredItem<Item> ROASTED_BELL_PEPPER_RED = registerWithTab("roasted_bell_pepper_red", () -> new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)));

    // Coffee
    public static final DeferredItem<Item> COFFEE = registerWithTab("coffee", () -> new DrinkableItem(
            bottleItem().food(FoodValues.COFFEE), true));
    public static final DeferredItem<Item> MILK_COFFEE = registerWithTab("milk_coffee", () -> new MilkCoffeeItem(
            bottleItem().food(FoodValues.MILK_COFFEE)));
    public static final DeferredItem<Item> CHOCOLATE_COFFEE = registerWithTab("chocolate_coffee", () -> new ChocolateCoffeeItem(
            bottleItem().food(FoodValues.CHOCOLATE_COFFEE)));
    public static final DeferredItem<Item> HONEY_COFFEE = registerWithTab("honey_coffee", () -> new DrinkableItem(
            bottleItem().food(FoodValues.HONEY_COFFEE), true));
    public static final DeferredItem<Item> DARK_COFFEE = registerWithTab("dark_coffee", () -> new DrinkableItem(
            bottleItem().food(FoodValues.DARK_COFFEE), true));

    // Cooking products
    public static final DeferredItem<Item> COOKING_OIL = registerWithTab("cooking_oil", () -> new DrinkableItem(
            bottleItem().food(FoodValues.COOKING_OIL)));
    public static final DeferredItem<Item> BATTER = registerWithTab("batter", () -> new DrinkableItem(
            bowlItem().food(FoodValues.BATTER)));

    // Sliced foods
    public static final DeferredItem<Item> POTATO_SLICES = registerWithTab("potato_slices", () -> new Item(
            baseItem().food(FoodValues.POTATO_SLICES)));
    public static final DeferredItem<Item> BAKED_POTATO_SLICES = registerWithTab("baked_potato_slices", () -> new Item(
            baseItem().food(FoodValues.BAKED_POTATO_SLICES)));
    public static final DeferredItem<Item> CALAMARI_SLICE = registerWithTab("calamari_slice", () -> new Item(
            baseItem().food(FoodValues.CALAMARI_SLICE)));
    public static final DeferredItem<Item> COOKED_CALAMARI_SLICE = registerWithTab("cooked_calamari_slice", () -> new Item(
            baseItem().food(FoodValues.COOKED_CALAMARI_SLICE)));

    // Sweets
    public static final DeferredItem<Item> FRUIT_BEIGNET = registerWithTab("fruit_beignet", () -> new ConsumableItem(
            baseItem().food(FoodValues.FRUIT_BEIGNET), true));
    public static final DeferredItem<Item> CHERRY_BLOSSOM_COOKIE = registerWithTab("cherry_blossom_cookie", () -> new Item(
            baseItem().food(vectorwing.farmersdelight.common.FoodValues.COOKIES)));
    public static final DeferredItem<Item> CHERRY_BLOSSOM_CHEESECAKE = registerBlockWithTab(ModBlocks.CHERRY_BLOSSOM_CHEESECAKE, baseItem());
    public static final DeferredItem<Item> CHERRY_BLOSSOM_CHEESECAKE_SLICE = registerWithTab("cherry_blossom_cheesecake_slice", () -> new Item(
            baseItem().food(vectorwing.farmersdelight.common.FoodValues.PIE_SLICE)));
    public static final DeferredItem<Item> HONEY_PANCAKES = registerBlockWithTab(ModBlocks.HONEY_PANCAKES, bowlItem());
    public static final DeferredItem<Item> CHOCOLATE_PANCAKES = registerBlockWithTab(ModBlocks.CHOCOLATE_PANCAKES, bowlItem());
    public static final DeferredItem<Item> CHERRY_BLOSSOM_PANCAKES = registerBlockWithTab(ModBlocks.CHERRY_BLOSSOM_PANCAKES, bowlItem());
    public static final DeferredItem<Item> VEGETABLE_PANCAKES = registerBlockWithTab(ModBlocks.VEGETABLE_PANCAKES, bowlItem());

    // Basic meals
    public static final DeferredItem<Item> CALAMARI_ROLL = registerWithTab("calamari_roll", () -> new Item(
            baseItem().food(FoodValues.CALAMARI_ROLL)));
    public static final DeferredItem<Item> CHERRY_BLOSSOM_ROLL = registerWithTab("cherry_blossom_roll", () -> new Item(
            baseItem().food(FoodValues.CHERRY_BLOSSOM_ROLL)));
    public static final DeferredItem<Item> POTATO_SALAD = registerWithTab("potato_salad", () -> new ConsumableItem(
            bowlItem().food(FoodValues.POTATO_SALAD), true));
    public static final DeferredItem<Item> SPRING_ROLLS = registerWithTab("spring_rolls", () -> new Item(
            baseItem().food(FoodValues.SPRING_ROLLS)));
    public static final DeferredItem<Item> STUFFED_BELL_PEPPER_GREEN = registerWithTab("stuffed_bell_pepper_green", () -> new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)));
    public static final DeferredItem<Item> STUFFED_BELL_PEPPER_YELLOW = registerWithTab("stuffed_bell_pepper_yellow", () -> new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)));
    public static final DeferredItem<Item> STUFFED_BELL_PEPPER_RED = registerWithTab("stuffed_bell_pepper_red", () -> new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)));

    // Soups and stews
    public static final DeferredItem<Item> BELL_PEPPER_SOUP = registerWithTab("bell_pepper_soup", () -> new DrinkableItem(
            bowlItem().food(FoodValues.BELL_PEPPER_SOUP), true));

    // Plated meals
    public static final DeferredItem<Item> BELL_PEPPER_PASTA = registerWithTab("bell_pepper_pasta", () -> new ConsumableItem(
            bowlItem().food(FoodValues.BELL_PEPPER_PASTA), true));
    public static final DeferredItem<Item> FRIED_CALAMARI = registerWithTab("fried_calamari", () -> new ConsumableItem(
            bowlItem().food(FoodValues.FRIED_CALAMARI), true));
    public static final DeferredItem<Item> FRIED_CHICKEN = registerWithTab("fried_chicken", () -> new ConsumableItem(
            bowlItem().food(FoodValues.FRIED_CHICKEN), true));
    public static final DeferredItem<Item> FRIED_MUSHROOMS = registerWithTab("fried_mushrooms", () -> new ConsumableItem(
           bowlItem().food(FoodValues.FRIED_MUSHROOMS), true));
    public static final DeferredItem<Item> COFFEE_BRAISED_BEEF = registerWithTab("coffee_braised_beef", () -> new ConsumableItem(
            bowlItem().food(FoodValues.COFFEE_BRAISED_BEEF), true));

    // Feasts

    // Helper functions
    public static Item.Properties baseItem() {
        return new Item.Properties();
    }

    public static Item.Properties bottleItem() {
        return baseItem().craftRemainder(Items.GLASS_BOTTLE).stacksTo(BOTTLE_STACK_SIZE);
    }

    public static Item.Properties bowlItem() {
        return baseItem().craftRemainder(Items.BOWL).stacksTo(BOWL_STACK_SIZE);
    }

    // Registry functions
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

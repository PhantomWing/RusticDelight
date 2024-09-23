package com.phantomwing.rusticdelight.item;

import com.google.common.collect.Sets;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.food.FoodValues;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class ModItems {
    public static final int BOWL_STACK_SIZE = 16;
    public static final int BOTTLE_STACK_SIZE = 16;

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RusticDelight.MOD_ID);

    public static LinkedHashSet<Supplier<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

    // Seed bags
    public static final RegistryObject<Item> COTTON_SEEDS_BAG = registerBlockWithTab(ModBlocks.COTTON_SEEDS_BAG);
    public static final RegistryObject<Item> BELL_PEPPER_SEEDS_BAG = registerBlockWithTab(ModBlocks.BELL_PEPPER_SEEDS_BAG);

    // Crop Crates
    public static final RegistryObject<Item> COTTON_BOLL_CRATE = registerBlockWithTab(ModBlocks.COTTON_BOLL_CRATE);
    public static final RegistryObject<Item> BELL_PEPPER_GREEN_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_GREEN_CRATE);
    public static final RegistryObject<Item> BELL_PEPPER_YELLOW_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_YELLOW_CRATE);
    public static final RegistryObject<Item> BELL_PEPPER_RED_CRATE = registerBlockWithTab(ModBlocks.BELL_PEPPER_RED_CRATE);

    // Crops
    public static final RegistryObject<Item> WILD_COTTON = registerBlockWithTab(ModBlocks.WILD_COTTON);
    public static final RegistryObject<Item> WILD_BELL_PEPPERS = registerBlockWithTab(ModBlocks.WILD_BELL_PEPPERS);

    // Crop products
    public static final RegistryObject<Item> COTTON_BOLL = registerWithTab("cotton_boll", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BELL_PEPPER_GREEN = registerWithTab("bell_pepper_green", () -> new Item(
            new Item.Properties().food(FoodValues.BELL_PEPPER)));
    public static final RegistryObject<Item> BELL_PEPPER_YELLOW = registerWithTab("bell_pepper_yellow", () -> new Item(
            new Item.Properties().food(FoodValues.BELL_PEPPER)));
    public static final RegistryObject<Item> BELL_PEPPER_RED = registerWithTab("bell_pepper_red", () -> new Item(
            new Item.Properties().food(FoodValues.BELL_PEPPER)));

    // Crop seeds
    public static final RegistryObject<Item> COTTON_SEEDS = registerWithTab("cotton_seeds", () -> new ItemNameBlockItem(
            ModBlocks.COTTON_CROP.get(),
            new Item.Properties()));
    public static final RegistryObject<Item> BELL_PEPPER_SEEDS = registerWithTab("bell_pepper_seeds", () -> new ItemNameBlockItem(
            ModBlocks.BELL_PEPPER_CROP.get(),
            new Item.Properties()));

    // Basic food
    public static final RegistryObject<Item> CALAMARI = registerWithTab("calamari", () -> new Item(
            new Item.Properties().food(FoodValues.CALAMARI)));
    public static final RegistryObject<Item> COOKED_CALAMARI = registerWithTab("cooked_calamari", () -> new Item(
            new Item.Properties().food(FoodValues.COOKED_CALAMARI)));
    public static final RegistryObject<Item> ROASTED_BELL_PEPPER_GREEN = registerWithTab("roasted_bell_pepper_green", () -> new Item(
            new Item.Properties().food(FoodValues.ROASTED_BELL_PEPPER)));
    public static final RegistryObject<Item> ROASTED_BELL_PEPPER_YELLOW = registerWithTab("roasted_bell_pepper_yellow", () -> new Item(
            new Item.Properties().food(FoodValues.ROASTED_BELL_PEPPER)));
    public static final RegistryObject<Item> ROASTED_BELL_PEPPER_RED = registerWithTab("roasted_bell_pepper_red", () -> new Item(
            new Item.Properties().food(FoodValues.ROASTED_BELL_PEPPER)));

    // Cooking products
    public static final RegistryObject<Item> COOKING_OIL = registerWithTab("cooking_oil", () -> new DrinkableItem(
            new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(FoodValues.COOKING_OIL).stacksTo(BOTTLE_STACK_SIZE)));
    public static final RegistryObject<Item> BATTER = registerWithTab("batter", () -> new DrinkableItem(
            new Item.Properties().craftRemainder(Items.BOWL).food(FoodValues.BATTER).stacksTo(BOWL_STACK_SIZE)));

    // Sliced food
    public static final RegistryObject<Item> POTATO_SLICES = registerWithTab("potato_slices", () -> new Item(
            new Item.Properties().food(FoodValues.POTATO_SLICES)));
    public static final RegistryObject<Item> BAKED_POTATO_SLICES = registerWithTab("baked_potato_slices", () -> new Item(
            new Item.Properties().food(FoodValues.BAKED_POTATO_SLICES)));
    public static final RegistryObject<Item> CALAMARI_SLICE = registerWithTab("calamari_slice", () -> new Item(
            new Item.Properties().food(FoodValues.CALAMARI_SLICE)));
    public static final RegistryObject<Item> COOKED_CALAMARI_SLICE = registerWithTab("cooked_calamari_slice", () -> new Item(
            new Item.Properties().food(FoodValues.COOKED_CALAMARI_SLICE)));

    // Sweets
    public static final RegistryObject<Item> FRUIT_BEIGNET = registerWithTab("fruit_beignet", () -> new Item(
            new Item.Properties().food(FoodValues.FRUIT_BEIGNET)));
    public static final RegistryObject<Item> HONEY_PANCAKES = registerBlockWithTab(ModBlocks.HONEY_PANCAKES, new Item.Properties().stacksTo(BOWL_STACK_SIZE));
    public static final RegistryObject<Item> CHOCOLATE_PANCAKES = registerBlockWithTab(ModBlocks.CHOCOLATE_PANCAKES, new Item.Properties().stacksTo(BOWL_STACK_SIZE));
    public static final RegistryObject<Item> VEGETABLE_PANCAKES = registerBlockWithTab(ModBlocks.VEGETABLE_PANCAKES, new Item.Properties().stacksTo(BOWL_STACK_SIZE));

    // Basic meals
    public static final RegistryObject<Item> CALAMARI_ROLL = registerWithTab("calamari_roll", () -> new Item(
            new Item.Properties().food(FoodValues.CALAMARI_ROLL)));
    public static final RegistryObject<Item> POTATO_SALAD = registerWithTab("potato_salad", () -> new Item(
            new Item.Properties().craftRemainder(Items.BOWL).food(FoodValues.POTATO_SALAD)));
    public static final RegistryObject<Item> SPRING_ROLLS = registerWithTab("spring_rolls", () -> new Item(
            new Item.Properties().food(FoodValues.SPRING_ROLLS)));
    public static final RegistryObject<Item> STUFFED_BELL_PEPPER_GREEN = registerWithTab("stuffed_bell_pepper_green", () -> new Item(
            new Item.Properties().food(FoodValues.STUFFED_BELL_PEPPER)));
    public static final RegistryObject<Item> STUFFED_BELL_PEPPER_YELLOW = registerWithTab("stuffed_bell_pepper_yellow", () -> new Item(
            new Item.Properties().food(FoodValues.STUFFED_BELL_PEPPER)));
    public static final RegistryObject<Item> STUFFED_BELL_PEPPER_RED = registerWithTab("stuffed_bell_pepper_red", () -> new Item(
            new Item.Properties().food(FoodValues.STUFFED_BELL_PEPPER)));

    // Soups and stews
    public static final RegistryObject<Item> BELL_PEPPER_SOUP = registerWithTab("bell_pepper_soup", () -> new DrinkableItem(
            new Item.Properties().craftRemainder(Items.BOWL).food(FoodValues.BELL_PEPPER_SOUP).stacksTo(BOWL_STACK_SIZE)));

    // Plated meals
    public static final RegistryObject<Item> BELL_PEPPER_PASTA = registerWithTab("bell_pepper_pasta", () -> new Item(
            new Item.Properties().craftRemainder(Items.BOWL).food(FoodValues.BELL_PEPPER_PASTA).stacksTo(BOWL_STACK_SIZE)));
    public static final RegistryObject<Item> FRIED_CALAMARI = registerWithTab("fried_calamari", () -> new Item(
            new Item.Properties().craftRemainder(Items.BOWL).food(FoodValues.FRIED_CALAMARI).stacksTo(BOWL_STACK_SIZE)));
    public static final RegistryObject<Item> FRIED_CHICKEN = registerWithTab("fried_chicken", () -> new Item(
            new Item.Properties().craftRemainder(Items.BOWL).food(FoodValues.FRIED_CHICKEN).stacksTo(BOWL_STACK_SIZE)));
    public static final RegistryObject<Item> FRIED_MUSHROOMS = registerWithTab("fried_mushrooms", () -> new Item(
            new Item.Properties().craftRemainder(Items.BOWL).food(FoodValues.FRIED_MUSHROOMS).stacksTo(BOWL_STACK_SIZE)));

    // Feasts

    public static RegistryObject<Item> registerWithTab(final String name, final Supplier<Item> supplier) {
        RegistryObject<Item> item = ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(item);
        return item;
    }

    public static RegistryObject<Item> registerBlockWithTab(RegistryObject<Block> block) {
        return registerWithTab(block.getId().getPath().replaceFirst(RusticDelight.MOD_ID + ":", ""), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static RegistryObject<Item> registerBlockWithTab(RegistryObject<Block> block,  net.minecraft.world.item.Item.Properties properties) {
        return registerWithTab(block.getId().getPath().replaceFirst(RusticDelight.MOD_ID + ":", ""), () -> new BlockItem(block.get(), properties));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
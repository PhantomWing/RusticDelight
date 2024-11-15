package com.phantomwing.rusticdelight.item;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.food.FoodValues;
import com.phantomwing.rusticdelight.item.custom.ChocolateCoffeeItem;
import com.phantomwing.rusticdelight.item.custom.MilkCoffeeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.function.Supplier;

public class ModItems {
    public static final int BOWL_STACK_SIZE = 16;
    public static final int BOTTLE_STACK_SIZE = 16;

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RusticDelight.MOD_ID);

    // Seed bags
    public static final RegistryObject<Item> COTTON_SEEDS_BAG = registerBlock(ModBlocks.COTTON_SEEDS_BAG);
    public static final RegistryObject<Item> BELL_PEPPER_SEEDS_BAG = registerBlock(ModBlocks.BELL_PEPPER_SEEDS_BAG);
    public static final RegistryObject<Item> COFFEE_BEANS_BAG = registerBlock(ModBlocks.COFFEE_BEANS_BAG);

    // Crop Crates
    public static final RegistryObject<Item> COTTON_BOLL_CRATE = registerBlock(ModBlocks.COTTON_BOLL_CRATE);
    public static final RegistryObject<Item> BELL_PEPPER_GREEN_CRATE = registerBlock(ModBlocks.BELL_PEPPER_GREEN_CRATE);
    public static final RegistryObject<Item> BELL_PEPPER_YELLOW_CRATE = registerBlock(ModBlocks.BELL_PEPPER_YELLOW_CRATE);
    public static final RegistryObject<Item> BELL_PEPPER_RED_CRATE = registerBlock(ModBlocks.BELL_PEPPER_RED_CRATE);

    // Crops
    public static final RegistryObject<Item> WILD_COTTON = registerBlock(ModBlocks.WILD_COTTON);
    public static final RegistryObject<Item> WILD_BELL_PEPPERS = registerBlock(ModBlocks.WILD_BELL_PEPPERS);
    public static final RegistryObject<Item> WILD_COFFEE = registerBlock(ModBlocks.WILD_COFFEE);

    // Crop products
    public static final RegistryObject<Item> COTTON_BOLL = registerItem("cotton_boll", () -> new Item(baseItem()));
    public static final RegistryObject<Item> BELL_PEPPER_GREEN = registerItem("bell_pepper_green", () -> new Item(
            baseItem().food(FoodValues.BELL_PEPPER)));
    public static final RegistryObject<Item> BELL_PEPPER_YELLOW = registerItem("bell_pepper_yellow", () -> new Item(
            baseItem().food(FoodValues.BELL_PEPPER)));
    public static final RegistryObject<Item> BELL_PEPPER_RED = registerItem("bell_pepper_red", () -> new Item(
            baseItem().food(FoodValues.BELL_PEPPER)));

    // Crop seeds
    public static final RegistryObject<Item> COTTON_SEEDS = registerItem("cotton_seeds", () -> new ItemNameBlockItem(
            ModBlocks.COTTON_CROP.get(),
            baseItem()));
    public static final RegistryObject<Item> BELL_PEPPER_SEEDS = registerItem("bell_pepper_seeds", () -> new ItemNameBlockItem(
            ModBlocks.BELL_PEPPER_CROP.get(),
            baseItem()));

    // Coffee Beans
    public static final RegistryObject<Item> COFFEE_BEANS = registerItem("coffee_beans", () -> new ItemNameBlockItem(
            ModBlocks.COFFEE_CROP.get(), baseItem()));
    public static final RegistryObject<Item> ROASTED_COFFEE_BEANS = registerItem("roasted_coffee_beans", () -> new Item(
            baseItem().food(FoodValues.ROASTED_COFFEE_BEANS)));
    public static final RegistryObject<Item> GOLDEN_COFFEE_BEANS = registerItem("golden_coffee_beans", () -> new Item(
            baseItem().food(FoodValues.GOLDEN_COFFEE_BEANS)));

    // Calamari
    public static final RegistryObject<Item> CALAMARI = registerItem("calamari", () -> new Item(
            baseItem().food(FoodValues.CALAMARI)));
    public static final RegistryObject<Item> COOKED_CALAMARI = registerItem("cooked_calamari", () -> new Item(
            baseItem().food(FoodValues.COOKED_CALAMARI)));

    // Roasted bell peppers
    public static final RegistryObject<Item> ROASTED_BELL_PEPPER_GREEN = registerItem("roasted_bell_pepper_green", () -> new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)));
    public static final RegistryObject<Item> ROASTED_BELL_PEPPER_YELLOW = registerItem("roasted_bell_pepper_yellow", () -> new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)));
    public static final RegistryObject<Item> ROASTED_BELL_PEPPER_RED = registerItem("roasted_bell_pepper_red", () -> new Item(
            baseItem().food(FoodValues.ROASTED_BELL_PEPPER)));

    // Coffee
    public static final RegistryObject<Item> COFFEE = registerItem("coffee", () -> new DrinkableItem(
            bottleItem().food(FoodValues.COFFEE), true));
    public static final RegistryObject<Item> MILK_COFFEE = registerItem("milk_coffee", () -> new MilkCoffeeItem(
            bottleItem().food(FoodValues.MILK_COFFEE)));
    public static final RegistryObject<Item> CHOCOLATE_COFFEE = registerItem("chocolate_coffee", () -> new ChocolateCoffeeItem(
            bottleItem().food(FoodValues.CHOCOLATE_COFFEE)));
    public static final RegistryObject<Item> HONEY_COFFEE = registerItem("honey_coffee", () -> new DrinkableItem(
            bottleItem().food(FoodValues.HONEY_COFFEE), true));
    public static final RegistryObject<Item> DARK_COFFEE = registerItem("dark_coffee", () -> new DrinkableItem(
            bottleItem().food(FoodValues.DARK_COFFEE), true));

    // Cooking products
    public static final RegistryObject<Item> COOKING_OIL = registerItem("cooking_oil", () -> new DrinkableItem(
            bottleItem().food(FoodValues.COOKING_OIL)));
    public static final RegistryObject<Item> BATTER = registerItem("batter", () -> new Item(
            bowlItem().food(FoodValues.BATTER)));

    // Sliced food
    public static final RegistryObject<Item> POTATO_SLICES = registerItem("potato_slices", () -> new Item(
            baseItem().food(FoodValues.POTATO_SLICES)));
    public static final RegistryObject<Item> BAKED_POTATO_SLICES = registerItem("baked_potato_slices", () -> new Item(
            baseItem().food(FoodValues.BAKED_POTATO_SLICES)));
    public static final RegistryObject<Item> CALAMARI_SLICE = registerItem("calamari_slice", () -> new Item(
            baseItem().food(FoodValues.CALAMARI_SLICE)));
    public static final RegistryObject<Item> COOKED_CALAMARI_SLICE = registerItem("cooked_calamari_slice", () -> new Item(
            baseItem().food(FoodValues.COOKED_CALAMARI_SLICE)));

    // Sweets
    public static final RegistryObject<Item> FRUIT_BEIGNET = registerItem("fruit_beignet", () -> new ConsumableItem(
            baseItem().food(FoodValues.FRUIT_BEIGNET), true));
    public static final RegistryObject<Item> HONEY_PANCAKES = registerBlock(ModBlocks.HONEY_PANCAKES, bowlItem());
    public static final RegistryObject<Item> CHOCOLATE_PANCAKES = registerBlock(ModBlocks.CHOCOLATE_PANCAKES, bowlItem());
    public static final RegistryObject<Item> VEGETABLE_PANCAKES = registerBlock(ModBlocks.VEGETABLE_PANCAKES, bowlItem());

    // Basic meals
    public static final RegistryObject<Item> CALAMARI_ROLL = registerItem("calamari_roll", () -> new Item(
            baseItem().food(FoodValues.CALAMARI_ROLL)));
    public static final RegistryObject<Item> POTATO_SALAD = registerItem("potato_salad", () -> new ConsumableItem(
            bowlItem().food(FoodValues.POTATO_SALAD), true));
    public static final RegistryObject<Item> SPRING_ROLLS = registerItem("spring_rolls", () -> new Item(
            baseItem().food(FoodValues.SPRING_ROLLS)));
    public static final RegistryObject<Item> STUFFED_BELL_PEPPER_GREEN = registerItem("stuffed_bell_pepper_green", () -> new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)));
    public static final RegistryObject<Item> STUFFED_BELL_PEPPER_YELLOW = registerItem("stuffed_bell_pepper_yellow", () -> new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)));
    public static final RegistryObject<Item> STUFFED_BELL_PEPPER_RED = registerItem("stuffed_bell_pepper_red", () -> new Item(
            baseItem().food(FoodValues.STUFFED_BELL_PEPPER)));

    // Soups and stews
    public static final RegistryObject<Item> BELL_PEPPER_SOUP = registerItem("bell_pepper_soup", () -> new ConsumableItem(
            bowlItem().food(FoodValues.BELL_PEPPER_SOUP), true));

    // Plated meals
    public static final RegistryObject<Item> BELL_PEPPER_PASTA = registerItem("bell_pepper_pasta", () -> new ConsumableItem(
            bowlItem().food(FoodValues.BELL_PEPPER_PASTA), true));
    public static final RegistryObject<Item> FRIED_CALAMARI = registerItem("fried_calamari", () -> new ConsumableItem(
            bowlItem().food(FoodValues.FRIED_CALAMARI), true));
    public static final RegistryObject<Item> FRIED_CHICKEN = registerItem("fried_chicken", () -> new ConsumableItem(
            bowlItem().food(FoodValues.FRIED_CHICKEN), true));
    public static final RegistryObject<Item> FRIED_MUSHROOMS = registerItem("fried_mushrooms", () -> new ConsumableItem(
            bowlItem().food(FoodValues.FRIED_MUSHROOMS), true));
    public static final RegistryObject<Item> COFFEE_BRAISED_BEEF = registerItem("coffee_braised_beef", () -> new ConsumableItem(
            bowlItem().food(FoodValues.COFFEE_BRAISED_BEEF), true));

    // Feasts
    public static Item.Properties baseItem() {
        return new Item.Properties().tab(RusticDelight.CREATIVE_TAB);
    }

    public static Item.Properties bottleItem() {
        return baseItem().craftRemainder(Items.GLASS_BOTTLE).stacksTo(BOTTLE_STACK_SIZE);
    }

    public static Item.Properties bowlItem() {
        return baseItem().craftRemainder(Items.BOWL).stacksTo(BOWL_STACK_SIZE);
    }

    public static RegistryObject<Item> registerItem(final String name, final Supplier<Item> supplier) {
        return ITEMS.register(name, supplier);
    }

    public static RegistryObject<Item> registerBlock(RegistryObject<Block> block) {
        return registerItem(block.getId().getPath().replaceFirst(RusticDelight.MOD_ID + ":", ""), () -> new BlockItem(block.get(), baseItem()));
    }

    public static RegistryObject<Item> registerBlock(RegistryObject<Block> block, net.minecraft.world.item.Item.Properties properties) {
        return registerItem(block.getId().getPath().replaceFirst(RusticDelight.MOD_ID + ":", ""), () -> new BlockItem(block.get(), properties));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
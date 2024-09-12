package com.phantomwing.rusticdelight.item;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.food.FoodValues;
import com.phantomwing.rusticdelight.item.custom.CookingOilItem;
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

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RusticDelight.MOD_ID);
    public static LinkedHashSet<Supplier<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

    // Storage blocks
    public static final DeferredItem<Item> COTTON_SEEDS_BAG = registerBlockWithTab(ModBlocks.COTTON_SEEDS_BAG);
    public static final DeferredItem<Item> COTTON_BOLL_CRATE = registerBlockWithTab(ModBlocks.COTTON_BOLL_CRATE);

    // Crops
    public static final DeferredItem<Item> WILD_COTTON = registerBlockWithTab(ModBlocks.WILD_COTTON);

    // Crop products
    public static final DeferredItem<Item> COTTON_BOLL = registerWithTab("cotton_boll", () -> new Item(new Item.Properties()));

    // Crop seeds
    public static final DeferredItem<Item> COTTON_SEEDS = registerWithTab("cotton_seeds", () -> new ItemNameBlockItem(
            ModBlocks.COTTON_CROP.get(),
            new Item.Properties()));

    // Basic food
    public static final DeferredItem<Item> CALAMARI = registerWithTab("calamari", () -> new Item(
            new Item.Properties().food(FoodValues.CALAMARI)));
    public static final DeferredItem<Item> COOKED_CALAMARI = registerWithTab("cooked_calamari", () -> new Item(
            new Item.Properties().food(FoodValues.COOKED_CALAMARI)));

    // Cooking products
    public static final DeferredItem<Item> COOKING_OIL = registerWithTab("cooking_oil", () -> new CookingOilItem(
            new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(FoodValues.COOKING_OIL).stacksTo(16)));

    // Sliced food
    public static final DeferredItem<Item> POTATO_SLICES = registerWithTab("potato_slices", () -> new Item(
            new Item.Properties().food(FoodValues.POTATO_SLICES)));
    public static final DeferredItem<Item> BAKED_POTATO_SLICES = registerWithTab("baked_potato_slices", () -> new Item(
            new Item.Properties().food(FoodValues.BAKED_POTATO_SLICES)));
    public static final DeferredItem<Item> CALAMARI_SLICE = registerWithTab("calamari_slice", () -> new Item(
            new Item.Properties().food(FoodValues.CALAMARI_SLICE)));
    public static final DeferredItem<Item> COOKED_CALAMARI_SLICE = registerWithTab("cooked_calamari_slice", () -> new Item(
            new Item.Properties().food(FoodValues.COOKED_CALAMARI_SLICE)));

    // Cooked food
    public static final DeferredItem<Item> CALAMARI_ROLL = registerWithTab("calamari_roll", () -> new Item(
            new Item.Properties().food(FoodValues.CALAMARI_ROLL)));

    public static DeferredItem<Item> registerWithTab(final String name, final Supplier<Item> supplier) {
        DeferredItem<Item> item = ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(item);
        return item;
    }

    public static DeferredItem<Item> registerBlockWithTab(DeferredBlock<Block> block) {
        return registerWithTab(block.getRegisteredName().replaceFirst(RusticDelight.MOD_ID + ":", ""), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

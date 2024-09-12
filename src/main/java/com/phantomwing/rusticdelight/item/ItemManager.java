package com.phantomwing.rusticdelight.item;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.BlockManager;
import com.phantomwing.rusticdelight.item.custom.CookingOilItem;
import com.google.common.collect.Sets;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class ItemManager {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RusticDelight.MOD_ID);
    public static LinkedHashSet<Supplier<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

    public static DeferredItem<Item> registerWithTab(final String name, final Supplier<Item> supplier) {
        DeferredItem<Item> item = ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(item);
        return item;
    }

    // Crops
    public static final DeferredItem<Item> COTTON_SEEDS = registerWithTab("cotton_seeds", () -> new ItemNameBlockItem(
            BlockManager.COTTON_CROP.get(),
            new Item.Properties()));
    public static final DeferredItem<Item> COTTON_BOLL = registerWithTab("cotton_boll", () -> new Item(new Item.Properties()));

    // Foods
    public static final DeferredItem<Item> POTATO_SLICES = registerWithTab("potato_slices", () -> new Item(
            new Item.Properties().food(FoodManager.POTATO_SLICES)));
    public static final DeferredItem<Item> BAKED_POTATO_SLICES = registerWithTab("baked_potato_slices", () -> new Item(
            new Item.Properties().food(FoodManager.BAKED_POTATO_SLICES)));
    public static final DeferredItem<Item> CALAMARI = registerWithTab("calamari", () -> new Item(
            new Item.Properties().food(FoodManager.CALAMARI)));
    public static final DeferredItem<Item> COOKED_CALAMARI = registerWithTab("cooked_calamari", () -> new Item(
            new Item.Properties().food(FoodManager.COOKED_CALAMARI)));
    public static final DeferredItem<Item> CALAMARI_SLICE = registerWithTab("calamari_slice", () -> new Item(
            new Item.Properties().food(FoodManager.CALAMARI_SLICE)));
    public static final DeferredItem<Item> COOKED_CALAMARI_SLICE = registerWithTab("cooked_calamari_slice", () -> new Item(
            new Item.Properties().food(FoodManager.COOKED_CALAMARI_SLICE)));
    public static final DeferredItem<Item> CALAMARI_ROLL = registerWithTab("calamari_roll", () -> new Item(
            new Item.Properties().food(FoodManager.CALAMARI_ROLL)));

    // Products
    public static final DeferredItem<Item> COOKING_OIL = registerWithTab("cooking_oil", () -> new CookingOilItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(FoodManager.COOKING_OIL).stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

        BlockItems.register(eventBus);
    }

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        BlockItems.addCreative(event);
    }
}

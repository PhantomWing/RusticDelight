package com.phantomwing.rusticdelight.util;

import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.item.ItemFamily;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Seeds our crops into vanilla structure chests.
 *
 * <p>The NeoForge build does this with {@code farmersdelight:replace_item} loot modifiers, which
 * swap a vanilla item out for ours. Fabric's loot events can only append pools, never remove an
 * existing entry, so each entry here adds its item on a chance roll instead of replacing one. The
 * chances below reproduce NeoForge's where it set one; the rest use {@link #DEFAULT_CHANCE}, which
 * stands in for "however often the item it replaced would have rolled".
 */
public class ChestLootHelper {
    /** Used where the NeoForge modifier replaced an entry outright rather than rolling for it. */
    private static final float DEFAULT_CHANCE = 0.5f;

    private static final List<Entry> ENTRIES = new ArrayList<>();

    static {
        // Cotton turns up wherever villagers and sailors kept their seeds.
        cotton("chests/abandoned_mineshaft", ModItems.COTTON_SEEDS, 1, DEFAULT_CHANCE);
        cotton("chests/shipwreck_supply", ModItems.COTTON_SEEDS, 1, DEFAULT_CHANCE);
        cotton("chests/village/village_savanna_house", ModItems.COTTON_SEEDS, 1, DEFAULT_CHANCE);
        cotton("chests/village/village_snowy_house", ModItems.COTTON_SEEDS, 1, DEFAULT_CHANCE);
        cotton("chests/village/village_taiga_house", ModItems.COTTON_SEEDS, 1, DEFAULT_CHANCE);

        // The jungle is where coffee and the rarer bell peppers come from.
        coffee("chests/jungle_temple", ModItems.COFFEE_BEANS, 2, 0.5f);
        bellPepper("chests/jungle_temple", ModItems.PALE_BELL_PEPPER_SEEDS, 1, 0.25f);
        bellPepper("chests/jungle_temple", ModItems.DARK_BELL_PEPPER_SEEDS, 1, 0.25f);
    }

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            // Only touch the tables the game ships with; a datapack that overrode one meant to.
            if (!source.isBuiltin() || !RusticDelightConfig.get().generate_random_loot) {
                return;
            }

            for (Entry entry : ENTRIES) {
                if (entry.table.equals(key) && entry.family.isEnabled()) {
                    tableBuilder.pool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(entry.chance))
                            .add(LootItem.lootTableItem(entry.item)
                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(entry.count))))
                            .build());
                }
            }
        });
    }

    private static void cotton(String path, Item item, int count, float chance) {
        ENTRIES.add(new Entry(table(path), item, count, chance, ItemFamily.COTTON));
    }

    private static void coffee(String path, Item item, int count, float chance) {
        ENTRIES.add(new Entry(table(path), item, count, chance, ItemFamily.COFFEE));
    }

    private static void bellPepper(String path, Item item, int count, float chance) {
        ENTRIES.add(new Entry(table(path), item, count, chance, ItemFamily.BELL_PEPPER));
    }

    private static ResourceKey<LootTable> table(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, Identifier.withDefaultNamespace(path));
    }

    private record Entry(ResourceKey<LootTable> table, Item item, int count, float chance, ItemFamily family) {}
}

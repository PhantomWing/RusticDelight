package com.phantomwing.rusticdelight.util;

import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.item.ItemFamily;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Seeds our crops into vanilla structure chests.
 *
 * <p>The NeoForge build does this with {@code farmersdelight:replace_item} loot modifiers, which
 * swap a vanilla item out for ours and apply unconditionally unless given a chance. This port
 * appends a pool per entry instead, using the same chances (1.0 where the modifier had none), so
 * item frequencies match; the one remaining divergence is that the vanilla items the modifiers
 * would have removed still drop alongside ours.
 */
public class ChestLootHelper {
    /** Matches the unconditional replace_item modifiers, which fire in every generated chest. */
    private static final float DEFAULT_CHANCE = 1.0f;

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
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            // Only touch the tables the game ships with; a datapack that overrode one meant to.
            if (!source.isBuiltin() || !RusticDelightConfig.get().generate_random_loot) {
                return;
            }

            for (Entry entry : ENTRIES) {
                if (entry.table.equals(id) && entry.family.isEnabled()) {
                    tableBuilder.pool(LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(entry.chance))
                            .with(ItemEntry.builder(entry.item)
                                    .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(entry.count))))
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

    private static Identifier table(String path) {
        return new Identifier("minecraft", path);
    }

    private record Entry(Identifier table, Item item, int count, float chance, ItemFamily family) {}
}

package com.phantomwing.rusticdelight.util;

import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class EntityLootHelper {
    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            // Only touch the built-in tables: a datapack that deliberately replaces squid drops
            // should win rather than have calamari appended on top.
            boolean isSquid = hasDefaultLootTable(EntityType.SQUID, key)
                    || hasDefaultLootTable(EntityType.GLOW_SQUID, key);

            if (RusticDelightConfig.get().squids_drop_calamari && source.isBuiltin() && isSquid) {
                LootPool.Builder poolBuilder = LootPool.lootPool().add(LootItem.lootTableItem(ModItems.CALAMARI)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)))
                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F)))
                );
                tableBuilder.withPool(poolBuilder);
            }
        });
    }

    /**
     * {@code EntityType.getDefaultLootTable()} returns an {@code Optional}, so comparing it to the
     * key directly compiles fine but is ALWAYS false — which silently stopped calamari from
     * dropping. Unwrap it before comparing.
     */
    private static boolean hasDefaultLootTable(EntityType<?> type, ResourceKey<LootTable> key) {
        return type.getDefaultLootTable().filter(key::equals).isPresent();
    }
}
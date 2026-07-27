package com.phantomwing.rusticdelight.util;

import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class EntityLootHelper {
    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            // Only touch the built-in tables: a datapack that deliberately replaces squid drops
            // should win rather than have calamari appended on top.
            boolean isSquid = EntityType.SQUID.getDefaultLootTable().equals(key)
                    || EntityType.GLOW_SQUID.getDefaultLootTable().equals(key);

            if (RusticDelightConfig.get().squids_drop_calamari && source.isBuiltin() && isSquid) {
                LootPool.Builder poolBuilder = LootPool.lootPool().add(LootItem.lootTableItem(ModItems.CALAMARI)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)))
                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F)))
                );
                tableBuilder.withPool(poolBuilder);
            }
        });
    }
}
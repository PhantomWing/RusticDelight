package com.phantomwing.rusticdelight.util;

import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class EntityLootHelper {
    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            RusticDelightConfig config = RusticDelightConfig.get();

            if (config.squids_drop_calamari && source.isBuiltin() && (
                    (EntityType.SQUID.getDefaultLootTable().isPresent() && EntityType.SQUID.getDefaultLootTable().get().equals(key)) ||
                            (EntityType.GLOW_SQUID.getDefaultLootTable().isPresent() && EntityType.GLOW_SQUID.getDefaultLootTable().get().equals(key)))) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(1.0f)) // 100% chance
                        .add(LootItem.lootTableItem(ModItems.CALAMARI))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
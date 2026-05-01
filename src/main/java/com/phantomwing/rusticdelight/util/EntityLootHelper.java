package com.phantomwing.rusticdelight.util;

import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class EntityLootHelper {
    private static final Identifier SQUID_ID = new Identifier("minecraft", "entities/squid");
    private static final Identifier GLOW_SQUID_ID =new Identifier("minecraft", "entities/glow_squid");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (!RusticDelightConfig.getBooleanConfigurationValue(RusticDelightConfig.SQUIDS_DROP_CALAMARI_ID)) {
                return;
            }

            // Allow Squids and glow squids to drop Calamari
            if (SQUID_ID.equals(id) || GLOW_SQUID_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1.0f)) // 100% chance
                        .with(ItemEntry.builder(ModItems.CALAMARI))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}

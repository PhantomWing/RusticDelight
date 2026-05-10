package com.phantomwing.rusticdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.trading.VillagerTrade;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * Wires our data-driven {@link VillagerTrade} entries into the vanilla profession/level tags
 * that the game looks up when offering trades. Without this, the JSON files in
 * {@code data/rusticdelight/villager_trade/} exist but are never offered to any villager.
 *
 * <p>Trade IDs are added with {@code required: false} so that when a trade's runtime
 * {@code config_boolean} condition fails the corresponding tag entry is silently skipped.
 */
public class ModVillagerTradeTagsProvider extends FabricTagsProvider<VillagerTrade> {
    public ModVillagerTradeTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.VILLAGER_TRADE, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        builder(VillagerTradeTags.FARMER_LEVEL_1)
                .addOptional(ModVillagerTrades.FARMER_1_COTTON_EMERALD)
                .addOptional(ModVillagerTrades.FARMER_1_BELL_PEPPER_EMERALD)
                .addOptional(ModVillagerTrades.FARMER_1_COFFEE_EMERALD);

        builder(VillagerTradeTags.FARMER_LEVEL_5)
                .addOptional(ModVillagerTrades.FARMER_5_GOLDEN_COFFEE_BEANS);

        builder(VillagerTradeTags.FISHERMAN_LEVEL_1)
                .addOptional(ModVillagerTrades.FISHERMAN_1_COOKED_CALAMARI);

        builder(VillagerTradeTags.FISHERMAN_LEVEL_2)
                .addOptional(ModVillagerTrades.FISHERMAN_2_CALAMARI_EMERALD);

        builder(VillagerTradeTags.WANDERING_TRADER_COMMON)
                .addOptional(ModVillagerTrades.WANDERING_COTTON_SEEDS)
                .addOptional(ModVillagerTrades.WANDERING_BELL_PEPPER_SEEDS)
                .addOptional(ModVillagerTrades.WANDERING_COFFEE_BEANS);
    }
}

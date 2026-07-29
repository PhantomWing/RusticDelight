package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.condition.ConfigBooleanCondition;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Data-driven villager trades — the 26.1 replacement for fabric-api's removed TradeOfferHelper.
 * Each trade is gated on the user's {@link RusticDelightConfig} via {@link ConfigBooleanCondition}
 * so the existing config switches (master enable, per-feature chance > 0, calamari toggle) keep
 * working at runtime.
 */
public class ModVillagerTrades extends FabricDynamicRegistryProvider {
    public static final float PRICE_MULTIPLIER = 0.05f;

    private static final ResourceCondition VILLAGER_TRADES_ENABLED = new ConfigBooleanCondition(RusticDelightConfig.ENABLE_VILLAGER_TRADES_ID);
    private static final ResourceCondition WANDERING_TRADES_ENABLED = new ConfigBooleanCondition(RusticDelightConfig.ENABLE_WANDERING_TRADER_TRADES_ID);

    public ModVillagerTrades(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    // Farmer level 1: crop -> emerald
    public static final ResourceKey<VillagerTrade> FARMER_1_COTTON_EMERALD = key("farmer/1/cotton_emerald");
    public static final ResourceKey<VillagerTrade> FARMER_1_BELL_PEPPER_EMERALD = key("farmer/1/bell_pepper_emerald");
    public static final ResourceKey<VillagerTrade> FARMER_1_COFFEE_EMERALD = key("farmer/1/coffee_emerald");

    // Farmer level 5: emerald -> golden coffee beans
    public static final ResourceKey<VillagerTrade> FARMER_5_GOLDEN_COFFEE_BEANS = key("farmer/5/golden_coffee_beans_for_emerald");

    // Fisherman level 1: emerald + raw calamari -> cooked calamari (compound trade)
    public static final ResourceKey<VillagerTrade> FISHERMAN_1_COOKED_CALAMARI = key("fisherman/1/cooked_calamari_for_emerald_calamari");

    // Fisherman level 2: calamari -> emerald
    public static final ResourceKey<VillagerTrade> FISHERMAN_2_CALAMARI_EMERALD = key("fisherman/2/calamari_emerald");

    // Wandering trader: emerald -> seeds
    public static final ResourceKey<VillagerTrade> WANDERING_COTTON_SEEDS = key("wandering_trader/cotton_seeds_for_emerald");
    public static final ResourceKey<VillagerTrade> WANDERING_BELL_PEPPER_SEEDS = key("wandering_trader/bell_pepper_seeds_for_emerald");
    public static final ResourceKey<VillagerTrade> WANDERING_COFFEE_BEANS = key("wandering_trader/coffee_beans_for_emerald");

    // Wandering trader, uncommon tier: the exotic mutated seeds
    public static final ResourceKey<VillagerTrade> WANDERING_PALE_BELL_PEPPER_SEEDS = key("wandering_trader/pale_bell_pepper_seeds_for_emerald");
    public static final ResourceKey<VillagerTrade> WANDERING_DARK_BELL_PEPPER_SEEDS = key("wandering_trader/dark_bell_pepper_seeds_for_emerald");

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        // A family's trades need both its master toggle AND a non-zero wild-gen chance: setting the
        // chance to zero has always disabled the whole feature, trades included.
        ResourceCondition[] cottonEnabled = {
                featureEnabled(RusticDelightConfig.ENABLE_COTTON_ID),
                featureEnabled(RusticDelightConfig.CHANCE_WILD_COTTON_ID)};
        ResourceCondition[] bellPepperEnabled = {
                featureEnabled(RusticDelightConfig.ENABLE_BELL_PEPPERS_ID),
                featureEnabled(RusticDelightConfig.CHANCE_WILD_BELL_PEPPERS_ID)};
        ResourceCondition[] coffeeEnabled = {
                featureEnabled(RusticDelightConfig.ENABLE_COFFEE_ID),
                featureEnabled(RusticDelightConfig.CHANCE_WILD_COFFEE_ID)};
        ResourceCondition[] calamariEnabled = {featureEnabled(RusticDelightConfig.SQUIDS_DROP_CALAMARI_ID)};

        // Farmer level 1: 24 cotton -> 1 emerald, 24 bell peppers -> 1 emerald, 26 coffee -> 1 emerald
        addVillagerTrade(entries, FARMER_1_COTTON_EMERALD, emeraldsFor(ModItems.COTTON_BOLL, 24, 16, 2), cottonEnabled);
        addVillagerTrade(entries, FARMER_1_BELL_PEPPER_EMERALD, emeraldsFor(ModItems.BELL_PEPPER_RED, 24, 16, 2), bellPepperEnabled);
        addVillagerTrade(entries, FARMER_1_COFFEE_EMERALD, emeraldsFor(ModItems.COFFEE_BEANS, 26, 16, 2), coffeeEnabled);

        // Farmer level 5: 3 emeralds -> 3 golden coffee beans
        addVillagerTrade(entries, FARMER_5_GOLDEN_COFFEE_BEANS, itemsForEmeralds(ModItems.GOLDEN_COFFEE_BEANS, 3, 3, 12, 30), coffeeEnabled);

        // Fisherman level 1: 6 raw calamari + 1 emerald -> 6 cooked calamari.
        // Order matters: vanilla's cooked-fish trades put the fish in `wants` and the emerald in
        // `additional_wants` (see minecraft:fisherman/1/raw_cod_and_emerald_cooked_cod), so this
        // reads the same way round as every other fish trade in the villager UI.
        addVillagerTrade(entries, FISHERMAN_1_COOKED_CALAMARI, compoundTrade(ModItems.CALAMARI, 6, Items.EMERALD, 1, ModItems.COOKED_CALAMARI, 6, 16, 1), calamariEnabled);

        // Fisherman level 2: 15 raw calamari -> 1 emerald
        addVillagerTrade(entries, FISHERMAN_2_CALAMARI_EMERALD, emeraldsFor(ModItems.CALAMARI, 15, 16, 10), calamariEnabled);

        // Wandering trader: 1 emerald -> 1 seeds
        addWanderingTrade(entries, WANDERING_COTTON_SEEDS, itemsForEmeralds(ModItems.COTTON_SEEDS, 1, 1, 12, 2), cottonEnabled);
        addWanderingTrade(entries, WANDERING_BELL_PEPPER_SEEDS, itemsForEmeralds(ModItems.BELL_PEPPER_SEEDS, 1, 1, 12, 2), bellPepperEnabled);
        addWanderingTrade(entries, WANDERING_COFFEE_BEANS, itemsForEmeralds(ModItems.COFFEE_BEANS, 1, 1, 12, 2), coffeeEnabled);

        // Pale and Dark bell pepper seeds are exotic - 5 emeralds, and only from the uncommon pool.
        addWanderingTrade(entries, WANDERING_PALE_BELL_PEPPER_SEEDS, itemsForEmeralds(ModItems.PALE_BELL_PEPPER_SEEDS, 1, 5, 3, 1), bellPepperEnabled);
        addWanderingTrade(entries, WANDERING_DARK_BELL_PEPPER_SEEDS, itemsForEmeralds(ModItems.DARK_BELL_PEPPER_SEEDS, 1, 5, 3, 1), bellPepperEnabled);
    }

    @Override
    public String getName() {
        return "Rustic Delight Villager Trades";
    }

    /** Adds a villager-profession trade gated on `enable_villager_trades` plus any extra conditions. */
    private static void addVillagerTrade(Entries entries, ResourceKey<VillagerTrade> key, VillagerTrade trade, ResourceCondition... extra) {
        entries.add(key, trade, prepend(VILLAGER_TRADES_ENABLED, extra));
    }

    /** Adds a wandering-trader trade gated on `enable_wandering_trader_trades` plus any extra conditions. */
    private static void addWanderingTrade(Entries entries, ResourceKey<VillagerTrade> key, VillagerTrade trade, ResourceCondition... extra) {
        entries.add(key, trade, prepend(WANDERING_TRADES_ENABLED, extra));
    }

    private static ResourceCondition featureEnabled(String configId) {
        return new ConfigBooleanCondition(configId);
    }

    private static ResourceCondition[] prepend(ResourceCondition first, ResourceCondition[] rest) {
        ResourceCondition[] combined = new ResourceCondition[rest.length + 1];
        combined[0] = first;
        System.arraycopy(rest, 0, combined, 1, rest.length);
        return combined;
    }

    /** Sells {@code count} of {@code item} for 1 emerald. */
    private static VillagerTrade emeraldsFor(ItemLike item, int count, int maxUses, int xp) {
        return new VillagerTrade(
                new TradeCost(item.asItem(), count),
                new ItemStackTemplate(Items.EMERALD),
                maxUses, xp, PRICE_MULTIPLIER,
                Optional.empty(), List.of()
        );
    }

    /** Buys {@code outputCount} of {@code item} for {@code emeraldCost} emeralds. */
    private static VillagerTrade itemsForEmeralds(ItemLike item, int outputCount, int emeraldCost, int maxUses, int xp) {
        return new VillagerTrade(
                new TradeCost(Items.EMERALD, emeraldCost),
                new ItemStackTemplate(item.asItem(), outputCount),
                maxUses, xp, PRICE_MULTIPLIER,
                Optional.empty(), List.of()
        );
    }

    /** Compound trade: {@code count1} of {@code cost1} + {@code count2} of {@code cost2} -> {@code outputCount} of {@code output}. */
    private static VillagerTrade compoundTrade(ItemLike cost1, int count1, ItemLike cost2, int count2, ItemLike output, int outputCount, int maxUses, int xp) {
        return new VillagerTrade(
                new TradeCost(cost1.asItem(), count1),
                Optional.of(new TradeCost(cost2.asItem(), count2)),
                new ItemStackTemplate(output.asItem(), outputCount),
                maxUses, xp, PRICE_MULTIPLIER,
                Optional.empty(), List.of()
        );
    }

    private static ResourceKey<VillagerTrade> key(String path) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, path));
    }
}

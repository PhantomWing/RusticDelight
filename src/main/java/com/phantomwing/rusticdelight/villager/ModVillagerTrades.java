package com.phantomwing.rusticdelight.villager;

import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class ModVillagerTrades {
    public static float PRICE_MULTIPLIER = 0.05f;

    public static void registerVillagerTrades() {
        if (!RusticDelightConfig.getBooleanConfigurationValue(RusticDelightConfig.ENABLE_VILLAGER_TRADES_ID)) {
            return;
        }

        // Farmer level 1
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
                factories -> {
                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.COTTON_BOLL, 24),
                            new ItemStack(Items.EMERALD, 1),
                            16, 2, PRICE_MULTIPLIER
                    )));

                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.BELL_PEPPER_RED, 24),
                            new ItemStack(Items.EMERALD, 1),
                            16, 2, PRICE_MULTIPLIER
                    )));

                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.COFFEE_BEANS, 26),
                            new ItemStack(Items.EMERALD, 1),
                            16, 2, PRICE_MULTIPLIER
                    )));
                }
        );

        // Farmer level 5 (Master)
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 5,
                factories -> {
                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.GOLDEN_COFFEE_BEANS, 3),
                            12, 30, PRICE_MULTIPLIER
                    )));
                }
        );

        // Fisherman level 1
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 1,
                factories -> {
                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(ModItems.CALAMARI, 6),
                            new ItemStack(ModItems.COOKED_CALAMARI, 6),
                            16, 1, PRICE_MULTIPLIER
                    )));
                }
        );

        // Fisherman level 2
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 2,
                factories -> {
                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.CALAMARI, 15),
                            new ItemStack(Items.EMERALD, 1),
                            16, 10, PRICE_MULTIPLIER
                    )));
                }
        );
    }

    public static void registerWanderingTraderTrades() {
        if (!RusticDelightConfig.getBooleanConfigurationValue(RusticDelightConfig.ENABLE_WANDERING_TRADER_TRADES_ID)) {
            return;
        }

        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add(((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.COTTON_SEEDS, 1),
                    12, 2, PRICE_MULTIPLIER
            )));

            factories.add(((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.BELL_PEPPER_SEEDS, 1),
                    12, 2, PRICE_MULTIPLIER
            )));

            factories.add(((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.COFFEE_BEANS, 1),
                    12, 2, PRICE_MULTIPLIER
            )));
        });
    }

}

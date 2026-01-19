package com.phantomwing.rusticdelight.villager;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.HashMap;
import java.util.Optional;

public class ModVillagers {
    public static float PRICE_MULTIPLIER = 0.05f;

    public static void registerFoodsAndTrades() {
        registerFoodPoints();
        registerVillagerTrades();
        registerWanderingTraderTrades();
    }

    private static void registerFoodPoints() {
        HashMap<Item, Integer> foodPoints = new HashMap<>(Villager.FOOD_POINTS);
        foodPoints.put(ModItems.COTTON_BOLL, 1);
        foodPoints.put(ModItems.BELL_PEPPER_GREEN, 1);
        foodPoints.put(ModItems.BELL_PEPPER_YELLOW, 1);
        foodPoints.put(ModItems.BELL_PEPPER_RED, 1);
        foodPoints.put(ModItems.COFFEE_BEANS, 1);
        Villager.FOOD_POINTS = foodPoints;
    }

    private static void registerVillagerTrades() {
        RusticDelightConfig config = RusticDelightConfig.get();
        if (!config.enable_villager_trades) {
            return;
        }

        // Farmer level 1
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
                factories -> {
                    if (config.wild_cotton_chance > 0) {
                        factories.add(((world, entity, random) -> new MerchantOffer(
                                new ItemCost(ModItems.COTTON_BOLL, 24),
                                new ItemStack(Items.EMERALD, 1),
                                16, 2, PRICE_MULTIPLIER
                        )));
                    }

                    if (config.wild_bell_peppers_chance > 0) {
                        factories.add(((world, entity, random) -> new MerchantOffer(
                                new ItemCost(ModItems.BELL_PEPPER_RED, 24),
                                new ItemStack(Items.EMERALD, 1),
                                16, 2, PRICE_MULTIPLIER
                        )));
                    }

                    if (config.wild_coffee_chance > 0) {
                        factories.add(((world, entity, random) -> new MerchantOffer(
                                new ItemCost(ModItems.COFFEE_BEANS, 26),
                                new ItemStack(Items.EMERALD, 1),
                                16, 2, PRICE_MULTIPLIER
                        )));
                    }
                }
        );

        // Farmer level 5 (Master)
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 5,
                factories -> {
                    if (config.wild_coffee_chance > 0) {
                        factories.add(((world, entity, random) -> new MerchantOffer(
                                new ItemCost(Items.EMERALD, 3),
                                new ItemStack(ModItems.GOLDEN_COFFEE_BEANS, 3),
                                12, 30, PRICE_MULTIPLIER
                        )));
                    }
                }
        );

        // Fisherman level 1
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 1,
                factories -> {
                    if (config.squids_drop_calamari) {
                        factories.add(((world, entity, random) -> new MerchantOffer(
                                new ItemCost(Items.EMERALD, 1),
                                Optional.of(new ItemCost(ModItems.CALAMARI, 6)),
                                new ItemStack(ModItems.COOKED_CALAMARI, 6),
                                16, 1, PRICE_MULTIPLIER
                        )));
                    }
                }
        );

        // Fisherman level 2
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 2,
                factories -> {
                    if (config.squids_drop_calamari) {
                        factories.add(((world, entity, random) -> new MerchantOffer(
                                new ItemCost(ModItems.CALAMARI, 15),
                                new ItemStack(Items.EMERALD, 1),
                                16, 10, PRICE_MULTIPLIER
                        )));
                    }
                }
        );
    }

    private static void registerWanderingTraderTrades() {
        RusticDelightConfig config = RusticDelightConfig.get();
        if (!config.enable_wandering_trader_trades) {
            return;
        }

        TradeOfferHelper.registerWanderingTraderOffers(factories -> {
            if (config.wild_cotton_chance > 0) {
                Identifier cottonPool = Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, "emerald_for_cotton_seeds");
                factories.addAll(cottonPool, (world, entity, random) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 1),
                        new ItemStack(ModItems.COTTON_SEEDS, 1),
                        12, 2, PRICE_MULTIPLIER
                ));
            }

            if (config.wild_bell_peppers_chance > 0) {
                Identifier bellPepperPool = Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, "emerald_for_bell_pepper_seeds");
                factories.addAll(bellPepperPool, (world, entity, random) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 1),
                        new ItemStack(ModItems.BELL_PEPPER_SEEDS, 1),
                        12, 2, PRICE_MULTIPLIER
                ));
            }

            if (config.wild_coffee_chance > 0) {
                Identifier coffeePool = Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, "emerald_for_coffee_beans");
                factories.addAll(coffeePool, (world, entity, random) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 1),
                        new ItemStack(ModItems.COFFEE_BEANS, 1),
                        12, 2, PRICE_MULTIPLIER
                ));
            }
        });
    }

}

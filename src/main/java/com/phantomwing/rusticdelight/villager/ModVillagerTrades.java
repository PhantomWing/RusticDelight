package com.phantomwing.rusticdelight.villager;

import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.Optional;

public class ModVillagerTrades {
    public static float PRICE_MULTIPLIER = 0.05f;

    public static void registerVillagerTrades() {
        // Farmer level 1
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
                factories -> {
                    factories.add(((entity, random) -> new MerchantOffer(
                            new ItemCost(ModItems.COTTON_BOLL, 24),
                            new ItemStack(Items.EMERALD, 1),
                            16, 2, PRICE_MULTIPLIER
                    )));

                    factories.add(((entity, random) -> new MerchantOffer(
                            new ItemCost(ModItems.BELL_PEPPER_RED, 24),
                            new ItemStack(Items.EMERALD, 1),
                            16, 2, PRICE_MULTIPLIER
                    )));

                    factories.add(((entity, random) -> new MerchantOffer(
                            new ItemCost(ModItems.COFFEE_BEANS, 26),
                            new ItemStack(Items.EMERALD, 1),
                            16, 2, PRICE_MULTIPLIER
                    )));
                }
        );

        // Farmer level 5 (Master)
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 5,
                factories -> {
                    factories.add(((entity, random) -> new MerchantOffer(
                            new ItemCost(Items.EMERALD, 3),
                            new ItemStack(ModItems.GOLDEN_COFFEE_BEANS, 3),
                            12, 30, PRICE_MULTIPLIER
                    )));
                }
        );

        // Fisherman level 1
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 1,
                factories -> {
                    factories.add(((entity, random) -> new MerchantOffer(
                            new ItemCost(Items.EMERALD, 1),
                            Optional.of(new ItemCost(ModItems.CALAMARI, 6)),
                            new ItemStack(ModItems.COOKED_CALAMARI, 6),
                            16, 1, PRICE_MULTIPLIER
                    )));
                }
        );

        // Fisherman level 2
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 2,
                factories -> {
                    factories.add(((entity, random) -> new MerchantOffer(
                            new ItemCost(ModItems.CALAMARI, 15),
                            new ItemStack(Items.EMERALD, 1),
                            16, 10, PRICE_MULTIPLIER
                    )));
                }
        );
    }

    public static void registerWanderingTraderTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add(((entity, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.COTTON_SEEDS, 1),
                    12, 2, PRICE_MULTIPLIER
            )));

            factories.add(((entity, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.BELL_PEPPER_SEEDS, 1),
                    12, 2, PRICE_MULTIPLIER
            )));

            factories.add(((entity, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.COFFEE_BEANS, 1),
                    12, 2, PRICE_MULTIPLIER
            )));
        });
    }

}

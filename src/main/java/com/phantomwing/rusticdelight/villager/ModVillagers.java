package com.phantomwing.rusticdelight.villager;

import com.google.common.collect.ImmutableMap;
import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.item.ItemFamily;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
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

    /**
     * Adds Rustic Delight's edible crops to the villager food map so farmer villagers count, share and
     * breed on them like vanilla crops ({@code Villager.FOOD_POINTS} is otherwise hardcoded to
     * bread/potato/carrot/beetroot). Copies the current map first so additions from other mods are
     * preserved instead of clobbered.
     */
    private static void registerFoodPoints() {
        HashMap<Item, Integer> foodPoints = new HashMap<>(Villager.FOOD_POINTS);
        foodPoints.put(ModItems.BELL_PEPPER_GREEN, 1);
        foodPoints.put(ModItems.BELL_PEPPER_YELLOW, 1);
        foodPoints.put(ModItems.BELL_PEPPER_RED, 1);
        foodPoints.put(ModItems.BELL_PEPPER_ORANGE, 1);
        foodPoints.put(ModItems.BELL_PEPPER_WHITE, 1);
        foodPoints.put(ModItems.BELL_PEPPER_PINK, 1);
        foodPoints.put(ModItems.BELL_PEPPER_BLUE, 1);
        foodPoints.put(ModItems.BELL_PEPPER_PURPLE, 1);
        foodPoints.put(ModItems.BELL_PEPPER_BLACK, 1);
        // Cotton and coffee aren't truly food, but counting them (value 1) lets farmer villagers reliably
        // offload them to a partner so they work in automatic farms. The minor realism cost (villagers eating /
        // breeding on them) is unnoticeable in normal play.
        foodPoints.put(ModItems.COTTON_BOLL, 1);
        foodPoints.put(ModItems.COFFEE_BEANS, 1);
        Villager.FOOD_POINTS = ImmutableMap.copyOf(foodPoints);
    }

    private static void registerVillagerTrades() {
        RusticDelightConfig config = RusticDelightConfig.get();
        if (!config.enable_villager_trades) {
            return;
        }

        // Farmer level 1
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
                factories -> {
                    if (ItemFamily.COTTON.isEnabled()) {
                        factories.add(((world, entity, random) -> new MerchantOffer(
                                new ItemCost(ModItems.COTTON_BOLL, 24),
                                new ItemStack(Items.EMERALD, 1),
                                16, 2, PRICE_MULTIPLIER
                        )));
                    }

                    if (ItemFamily.BELL_PEPPER.isEnabled()) {
                        factories.add(((world, entity, random) -> new MerchantOffer(
                                new ItemCost(ModItems.BELL_PEPPER_RED, 24),
                                new ItemStack(Items.EMERALD, 1),
                                16, 2, PRICE_MULTIPLIER
                        )));
                    }

                    if (ItemFamily.COFFEE.isEnabled()) {
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
                    if (ItemFamily.COFFEE.isEnabled()) {
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
                    if (ItemFamily.CALAMARI.isEnabled()) {
                        // Fish first, emerald second, matching vanilla's cooked fish trades. Costs
                        // are discounted on the primary slot only, so the order is not cosmetic.
                        factories.add(((world, entity, random) -> new MerchantOffer(
                                new ItemCost(ModItems.CALAMARI, 6),
                                Optional.of(new ItemCost(Items.EMERALD, 1)),
                                new ItemStack(ModItems.COOKED_CALAMARI, 6),
                                16, 1, PRICE_MULTIPLIER
                        )));
                    }
                }
        );

        // Fisherman level 2
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 2,
                factories -> {
                    if (ItemFamily.CALAMARI.isEnabled()) {
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

        // Seeds go in the vanilla common pool (5 of its entries are picked per trader) rather than a
        // pool of our own, so they compete for a slot like every other wandering trader offer.
        TradeOfferHelper.registerWanderingTraderOffers(factories -> {
            if (ItemFamily.COTTON.isEnabled()) {
                factories.addOffersToPool(
                        TradeOfferHelper.WanderingTraderOffersBuilder.SELL_COMMON_ITEMS_POOL,
                        (world, entity, random) -> new MerchantOffer(
                                new ItemCost(Items.EMERALD, 1),
                                new ItemStack(ModItems.COTTON_SEEDS, 1),
                                12, 2, PRICE_MULTIPLIER
                        ));
            }

            if (ItemFamily.BELL_PEPPER.isEnabled()) {
                factories.addOffersToPool(
                        TradeOfferHelper.WanderingTraderOffersBuilder.SELL_COMMON_ITEMS_POOL,
                        (world, entity, random) -> new MerchantOffer(
                                new ItemCost(Items.EMERALD, 1),
                                new ItemStack(ModItems.BELL_PEPPER_SEEDS, 1),
                                12, 2, PRICE_MULTIPLIER
                        ));
            }

            if (ItemFamily.COFFEE.isEnabled()) {
                factories.addOffersToPool(
                        TradeOfferHelper.WanderingTraderOffersBuilder.SELL_COMMON_ITEMS_POOL,
                        (world, entity, random) -> new MerchantOffer(
                                new ItemCost(Items.EMERALD, 1),
                                new ItemStack(ModItems.COFFEE_BEANS, 1),
                                12, 2, PRICE_MULTIPLIER
                        ));
            }

            // Pale and Dark bell pepper seeds are exotic - the special pool picks only 2 of its
            // entries, so these show up far more rarely than the seed trades above.
            if (ItemFamily.BELL_PEPPER.isEnabled()) {
                factories.addOffersToPool(
                        TradeOfferHelper.WanderingTraderOffersBuilder.SELL_SPECIAL_ITEMS_POOL,
                        (world, entity, random) -> new MerchantOffer(
                                new ItemCost(Items.EMERALD, 5),
                                new ItemStack(ModItems.PALE_BELL_PEPPER_SEEDS, 1),
                                3, 1, PRICE_MULTIPLIER
                        ),
                        (world, entity, random) -> new MerchantOffer(
                                new ItemCost(Items.EMERALD, 5),
                                new ItemStack(ModItems.DARK_BELL_PEPPER_SEEDS, 1),
                                3, 1, PRICE_MULTIPLIER
                        ));
            }
        });
    }

}

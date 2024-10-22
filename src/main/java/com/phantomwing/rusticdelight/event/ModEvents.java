package com.phantomwing.rusticdelight.event;

import com.phantomwing.rusticdelight.Configuration;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.potions.ModPotions;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = RusticDelight.MOD_ID)
public class ModEvents {
    public static float PRICE_MULTIPLIER = 0.05f;

    @SubscribeEvent
    public static void addVillagerTrades(VillagerTradesEvent event) {
        // Check if trades are enabled.
        if (!Configuration.ENABLE_VILLAGER_TRADES.get()) {
            return;
        }

        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

        if (event.getType() == VillagerProfession.FARMER) {
            if (Configuration.CHANCE_WILD_COTTON.get() > 0) {
                trades.get(1).add((trader, random) -> new MerchantOffer(
                        new ItemCost(ModItems.COTTON_BOLL.get(), 24),
                        new ItemStack(Items.EMERALD, 1),
                        16,
                        2,
                        PRICE_MULTIPLIER
                ));
            }

            if (Configuration.CHANCE_WILD_BELL_PEPPERS.get() > 0) {
                trades.get(1).add((trader, random) -> new MerchantOffer(
                        new ItemCost(ModItems.BELL_PEPPER_RED.get(), 24),
                        new ItemStack(Items.EMERALD, 1),
                        16,
                        2,
                        PRICE_MULTIPLIER
                ));
            }

            if (Configuration.CHANCE_WILD_COFFEE.get() > 0) {
                trades.get(1).add((trader, random) -> new MerchantOffer(
                        new ItemCost(ModItems.COFFEE_BEANS.get(), 26),
                        new ItemStack(Items.EMERALD, 1),
                        16,
                        2,
                        PRICE_MULTIPLIER
                ));

                // Master
                trades.get(5).add((trader, random) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 3),
                        new ItemStack(ModItems.GOLDEN_COFFEE_BEANS.get(), 3),
                        12,
                        30,
                        PRICE_MULTIPLIER
                ));
            }
        } else if (event.getType() == VillagerProfession.FISHERMAN) {
            if (Configuration.SQUIDS_DROP_CALAMARI.get()) {
                // Level 1 trades
                trades.get(1).add((trader, random) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 1),
                        Optional.of(new ItemCost(ModItems.CALAMARI.get(), 6)),
                        new ItemStack(ModItems.COOKED_CALAMARI.get(), 6),
                        16,
                        1,
                        PRICE_MULTIPLIER
                ));

                // Level 2 trades
                trades.get(2).add((trader, random) -> new MerchantOffer(
                        new ItemCost(ModItems.CALAMARI.get(), 15),
                        new ItemStack(Items.EMERALD, 1),
                        16,
                        10,
                        PRICE_MULTIPLIER
                ));
            }
        }
    }

    @SubscribeEvent
    public static void addWanderingTraderTrades(WandererTradesEvent event) {
        // Check if trades are enabled.
        if (!Configuration.ENABLE_WANDERING_TRADER_TRADES.get()) {
            return;
        }

        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();

        if (Configuration.CHANCE_WILD_COTTON.get() > 0) {
            genericTrades.add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.COTTON_SEEDS.get(), 1),
                    12,
                    2,
                    PRICE_MULTIPLIER
            ));
        }

        if (Configuration.CHANCE_WILD_BELL_PEPPERS.get() > 0) {
            genericTrades.add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.BELL_PEPPER_SEEDS.get(), 1),
                    12,
                    2,
                    PRICE_MULTIPLIER
            ));
        }

        if (Configuration.CHANCE_WILD_COFFEE.get() > 0) {
            genericTrades.add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.COFFEE_BEANS.get(), 1),
                    12,
                    2,
                    PRICE_MULTIPLIER
            ));
        }
    }

    @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        // Disable custom potions entirely.
        if (!Configuration.ENABLE_POTIONS.get()) {
            return;
        }

        PotionBrewing.Builder builder = event.getBuilder();

        // Add Potion of Haste recipes. (Only if Coffee feature is enabled)
        if (Configuration.CHANCE_WILD_COFFEE.get() > 0) {
            // Use addMix to add brewing recipes for each potion container type (potion, splash potion, lingering potion, tipped arrow)
            builder.addMix(Potions.AWKWARD, ModItems.GOLDEN_COFFEE_BEANS.get(), ModPotions.HASTE_POTION
            );
            builder.addMix(ModPotions.HASTE_POTION, Items.REDSTONE, ModPotions.LONG_HASTE_POTION
            );
            builder.addMix(ModPotions.HASTE_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_HASTE_POTION
            );
        }
    }
}

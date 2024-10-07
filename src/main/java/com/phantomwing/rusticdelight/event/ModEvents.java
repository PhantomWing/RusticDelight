package com.phantomwing.rusticdelight.event;

import com.phantomwing.rusticdelight.Configuration;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.item.ModItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = RusticDelight.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void addVillagerTrades(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

        if (event.getType() == VillagerProfession.FARMER) {
            // Level 1 trades
            if (Configuration.CHANCE_WILD_COTTON.get() > 0) {
                trades.get(1).add((trader, random) -> new MerchantOffer(
                        new ItemCost(ModItems.COTTON_BOLL.get(), 24),
                        new ItemStack(Items.EMERALD, 1),
                        16,
                        2,
                        0.05f
                ));
            }

            if (Configuration.CHANCE_WILD_BELL_PEPPERS.get() > 0) {
                trades.get(1).add((trader, random) -> new MerchantOffer(
                        new ItemCost(ModItems.BELL_PEPPER_RED.get(), 24),
                        new ItemStack(Items.EMERALD, 1),
                        16,
                        2,
                        0.05f
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
                        0.05f
                ));

                // Level 2 trades
                trades.get(2).add((trader, random) -> new MerchantOffer(
                        new ItemCost(ModItems.CALAMARI.get(), 15),
                        new ItemStack(Items.EMERALD, 1),
                        16,
                        10,
                        0.05f
                ));
            }
        }
    }

    @SubscribeEvent
    public static void addWanderingTraderTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        // List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        if (Configuration.CHANCE_WILD_COTTON.get() > 0) {
            genericTrades.add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.COTTON_SEEDS.get(), 1),
                    12,
                    2,
                    0.05f
            ));
        }

        if (Configuration.CHANCE_WILD_BELL_PEPPERS.get() > 0) {
            genericTrades.add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.BELL_PEPPER_SEEDS.get(), 1),
                    12,
                    2,
                    0.05f
            ));
        }
    }
}

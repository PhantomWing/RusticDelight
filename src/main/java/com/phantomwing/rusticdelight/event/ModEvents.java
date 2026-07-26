package com.phantomwing.rusticdelight.event;

import com.phantomwing.rusticdelight.Configuration;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.custom.PancakeBlock;
import com.phantomwing.rusticdelight.item.ItemFamily;
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
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
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
            if (ItemFamily.COTTON.isEnabled()) {
                trades.get(1).add((trader, random) -> new MerchantOffer(
                        new ItemCost(ModItems.COTTON_BOLL.get(), 24),
                        new ItemStack(Items.EMERALD, 1),
                        16,
                        2,
                        PRICE_MULTIPLIER
                ));
            }

            if (ItemFamily.BELL_PEPPER.isEnabled()) {
                trades.get(1).add((trader, random) -> new MerchantOffer(
                        new ItemCost(ModItems.BELL_PEPPER_RED.get(), 24),
                        new ItemStack(Items.EMERALD, 1),
                        16,
                        2,
                        PRICE_MULTIPLIER
                ));
            }

            if (ItemFamily.COFFEE.isEnabled()) {
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

        if (ItemFamily.COTTON.isEnabled()) {
            genericTrades.add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.COTTON_SEEDS.get(), 1),
                    12,
                    2,
                    PRICE_MULTIPLIER
            ));
        }

        if (ItemFamily.BELL_PEPPER.isEnabled()) {
            genericTrades.add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.BELL_PEPPER_SEEDS.get(), 1),
                    12,
                    2,
                    PRICE_MULTIPLIER
            ));
        }

        if (ItemFamily.COFFEE.isEnabled()) {
            genericTrades.add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.COFFEE_BEANS.get(), 1),
                    12,
                    2,
                    PRICE_MULTIPLIER
            ));
        }

        // Pale and Dark bell pepper seeds are exotic - offered as rare wandering trader trades.
        if (ItemFamily.BELL_PEPPER.isEnabled()) {
            List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

            rareTrades.add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    new ItemStack(ModItems.PALE_BELL_PEPPER_SEEDS.get(), 1),
                    3,
                    1,
                    PRICE_MULTIPLIER
            ));

            rareTrades.add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    new ItemStack(ModItems.DARK_BELL_PEPPER_SEEDS.get(), 1),
                    3,
                    1,
                    PRICE_MULTIPLIER
            ));
        }
    }

    /**
     * Vanilla skips the block interaction entirely when a player sneaks with something in hand, so
     * putting a pancake back would never reach {@link PancakeBlock#useItemOn}. Force the block
     * through for that one case: sneaking with the pancake that belongs on the targeted stack.
     */
    @SubscribeEvent
    public static void allowPuttingPancakesBack(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getEntity().isSecondaryUseActive()) {
            return;
        }

        if (!(event.getLevel().getBlockState(event.getPos()).getBlock() instanceof PancakeBlock pancake)) {
            return;
        }

        if (event.getItemStack().is(pancake.servingItem.get())) {
            event.setUseBlock(TriState.TRUE);
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
        if (ItemFamily.COFFEE.isEnabled()) {
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

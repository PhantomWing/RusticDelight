package com.phantomwing.rusticdelight;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.compat.ThirstCompat;
import com.phantomwing.rusticdelight.condition.ModConditions;
import com.phantomwing.rusticdelight.loot.LootModifierManager;
import com.phantomwing.rusticdelight.potions.ModPotions;
import com.phantomwing.rusticdelight.ui.ModCreativeModTab;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.world.ModPlacementModifiers;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.fml.ModList;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Mod(RusticDelight.MOD_ID)
public class RusticDelight {
    public static final String MOD_ID = "rusticdelight";
    private static final Logger LOGGER = LogUtils.getLogger();

    public RusticDelight(IEventBus eventBus, ModContainer modContainer) {
        eventBus.addListener(this::commonSetup);

        modContainer.registerConfig(ModConfig.Type.COMMON, Configuration.COMMON_CONFIG);

        // This will use NeoForge's ConfigurationScreen to display this mod's configs (Client only)
        if (FMLEnvironment.dist.isClient()) {
            modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        }

        NeoForge.EVENT_BUS.register(this);

        if (ModList.get().isLoaded("thirst")) {
            NeoForge.EVENT_BUS.register(ThirstCompat.class);
        }

        registerManagers(eventBus);
    }

    // Register all managers to the event bus.
    private void registerManagers(IEventBus eventBus) {
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModPotions.register(eventBus);
        LootModifierManager.register(eventBus);
        ModCreativeModTab.register(eventBus);
        ModPlacementModifiers.register(eventBus);
        ModConditions.register(eventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            addFlowerPots();
            registerItemSetAdditions();
            registerVillagerFood();
        });
    }

    private void addFlowerPots() {
        FlowerPotBlock flowerPotBlock = ((FlowerPotBlock) Blocks.FLOWER_POT);
        flowerPotBlock.addPlant(ModBlocks.WILD_COTTON.getId(), ModBlocks.POTTED_WILD_COTTON);
        flowerPotBlock.addPlant(ModBlocks.WILD_BELL_PEPPERS.getId(), ModBlocks.POTTED_WILD_BELL_PEPPERS);
        flowerPotBlock.addPlant(ModBlocks.WILD_PALE_BELL_PEPPERS.getId(), ModBlocks.POTTED_WILD_PALE_BELL_PEPPERS);
        flowerPotBlock.addPlant(ModBlocks.WILD_DARK_BELL_PEPPERS.getId(), ModBlocks.POTTED_WILD_DARK_BELL_PEPPERS);
        flowerPotBlock.addPlant(ModBlocks.WILD_COFFEE.getId(), ModBlocks.POTTED_WILD_COFFEE);
    }

    public static void registerItemSetAdditions() {
        Set<Item> newWantedItems = Sets.newHashSet(
                ModItems.BELL_PEPPER_GREEN.get(),
                ModItems.BELL_PEPPER_YELLOW.get(),
                ModItems.BELL_PEPPER_RED.get(),
                ModItems.BELL_PEPPER_ORANGE.get(),
                ModItems.BELL_PEPPER_WHITE.get(),
                ModItems.BELL_PEPPER_PINK.get(),
                ModItems.BELL_PEPPER_BLUE.get(),
                ModItems.BELL_PEPPER_PURPLE.get(),
                ModItems.BELL_PEPPER_BLACK.get(),
                ModItems.COTTON_BOLL.get(),
                ModItems.BELL_PEPPER_SEEDS.get(),
                ModItems.PALE_BELL_PEPPER_SEEDS.get(),
                ModItems.DARK_BELL_PEPPER_SEEDS.get(),
                ModItems.COTTON_SEEDS.get(),
                ModItems.COFFEE_BEANS.get()
        );

        newWantedItems.addAll(Villager.WANTED_ITEMS);
        Villager.WANTED_ITEMS = ImmutableSet.copyOf(newWantedItems);
    }

    /**
     * Adds Rustic Delight's edible crops to the villager food map so farmer villagers count, share and
     * breed on them like vanilla crops (Villager.FOOD_POINTS is otherwise hardcoded to bread/potato/carrot/beetroot).
     * Copies the current map first so additions from other mods are preserved instead of clobbered; runs inside
     * commonSetup's enqueueWork, which is serialized on the main thread.
     */
    public static void registerVillagerFood() {
        Map<Item, Integer> newFoodPoints = new HashMap<>(Villager.FOOD_POINTS);
        newFoodPoints.put(ModItems.BELL_PEPPER_GREEN.get(), 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_YELLOW.get(), 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_RED.get(), 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_ORANGE.get(), 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_WHITE.get(), 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_PINK.get(), 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_BLUE.get(), 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_PURPLE.get(), 1);
        newFoodPoints.put(ModItems.BELL_PEPPER_BLACK.get(), 1);
        // Cotton and coffee aren't truly food, but counting them (value 1) lets farmer villagers reliably
        // offload them to a partner so they work in automatic farms. The minor realism cost (villagers eating /
        // breeding on them) is unnoticeable in normal play.
        newFoodPoints.put(ModItems.COTTON_BOLL.get(), 1);
        newFoodPoints.put(ModItems.COFFEE_BEANS.get(), 1);
        Villager.FOOD_POINTS = ImmutableMap.copyOf(newFoodPoints);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}

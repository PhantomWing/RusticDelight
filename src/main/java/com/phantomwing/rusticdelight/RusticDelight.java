package com.phantomwing.rusticdelight;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.loot.LootModifierManager;
import com.phantomwing.rusticdelight.ui.ModCreativeModTab;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.world.ModPlacementModifiers;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
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

        registerManagers(eventBus);
    }

    // Register all managers to the event bus.
    private void registerManagers(IEventBus eventBus) {
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        LootModifierManager.register(eventBus);
        ModCreativeModTab.register(eventBus);
        ModPlacementModifiers.register(eventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            addFlowerPots();
            registerItemSetAdditions();
        });
    }

    private void addFlowerPots() {
        FlowerPotBlock flowerPotBlock = ((FlowerPotBlock) Blocks.FLOWER_POT);
        flowerPotBlock.addPlant(ModBlocks.WILD_COTTON.getId(), ModBlocks.POTTED_WILD_COTTON);
        flowerPotBlock.addPlant(ModBlocks.WILD_BELL_PEPPERS.getId(), ModBlocks.POTTED_WILD_BELL_PEPPERS);
    }

    public static void registerItemSetAdditions() {
        Set<Item> newWantedItems = Sets.newHashSet(
                ModItems.BELL_PEPPER_GREEN.get(),
                ModItems.BELL_PEPPER_YELLOW.get(),
                ModItems.BELL_PEPPER_RED.get(),
                ModItems.COTTON_BOLL.get(),
                ModItems.BELL_PEPPER_SEEDS.get(),
                ModItems.COTTON_SEEDS.get());
        newWantedItems.addAll(Villager.WANTED_ITEMS);
        Villager.WANTED_ITEMS = ImmutableSet.copyOf(newWantedItems);
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}

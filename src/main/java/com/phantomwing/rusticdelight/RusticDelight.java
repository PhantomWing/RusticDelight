package com.phantomwing.rusticdelight;

import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.condition.ModConditions;
import com.phantomwing.rusticdelight.event.ModEvents;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.itemGroup.ModItemGroups;
import com.phantomwing.rusticdelight.potion.ModPotions;
import com.phantomwing.rusticdelight.util.ChestLootHelper;
import com.phantomwing.rusticdelight.util.ComposterHelper;
import com.phantomwing.rusticdelight.util.EntityLootHelper;
import com.phantomwing.rusticdelight.util.VillagerHelper;
import com.phantomwing.rusticdelight.villager.ModVillagerTrades;
import com.phantomwing.rusticdelight.world.ModPlacementModifiers;
import com.phantomwing.rusticdelight.world.ModVillageStructures;
import com.phantomwing.rusticdelight.world.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RusticDelight implements ModInitializer {
	public static final String MOD_ID = "rusticdelight";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Register the config file.
		RusticDelightConfig.register();

		// Datapack conditions, so config-gated recipes/loot/advancements can load conditionally.
		ModConditions.registerModConditions();

		// Items
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModPotions.registerModPotions();

		// World
		ModWorldGeneration.registerModWorldGeneration();
		ModPlacementModifiers.registerPlacementModfiiers();
		ModVillageStructures.registerVillageCrops();

		// Interaction
		ModEvents.registerModEvents();

		// Trades
		ModVillagerTrades.registerVillagerTrades();
		ModVillagerTrades.registerWanderingTraderTrades();

		// UI
		ModItemGroups.registerModItemGroups();

		ComposterHelper.registerCompostableItems();
		EntityLootHelper.modifyLootTables();
		ChestLootHelper.modifyLootTables();
		VillagerHelper.registerGatherableItems();

		// Fuel
		FuelRegistry.INSTANCE.add(ModItems.COTTON_BOLL, 100);
	}
}
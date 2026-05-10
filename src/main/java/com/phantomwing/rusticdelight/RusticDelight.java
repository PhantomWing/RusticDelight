package com.phantomwing.rusticdelight;

import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.condition.ConfigBooleanCondition;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.itemGroup.ModItemGroups;
import com.phantomwing.rusticdelight.potion.ModPotions;
import com.phantomwing.rusticdelight.util.ComposterHelper;
import com.phantomwing.rusticdelight.util.EntityLootHelper;
import com.phantomwing.rusticdelight.util.FuelHelper;
import com.phantomwing.rusticdelight.villager.ModVillagers;
import com.phantomwing.rusticdelight.world.ModPlacementModifiers;
import com.phantomwing.rusticdelight.world.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RusticDelight implements ModInitializer {
	public static final String MOD_ID = "rusticdelight";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Register the config file.
		RusticDelightConfig.register();

		// Register custom resource conditions used by data-driven entries (e.g. villager trades).
		ResourceConditions.register(ConfigBooleanCondition.TYPE);

		// Items
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModPotions.registerModPotions();

		// World
		ModWorldGeneration.registerModWorldGeneration();
		ModPlacementModifiers.registerPlacementModfiiers();

		// Villagers
		ModVillagers.registerFoodsAndTrades();

		// UI
		ModItemGroups.registerModItemGroups();

		ComposterHelper.registerCompostableItems();
		EntityLootHelper.modifyLootTables();
		FuelHelper.registerFuelItems();
	}
}
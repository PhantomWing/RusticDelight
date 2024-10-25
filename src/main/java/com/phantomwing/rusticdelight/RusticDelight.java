package com.phantomwing.rusticdelight;

import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.itemGroup.ModItemGroups;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RusticDelight implements ModInitializer {
	public static final String MOD_ID = "rusticdelight";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerModItemGroups();
	}
}
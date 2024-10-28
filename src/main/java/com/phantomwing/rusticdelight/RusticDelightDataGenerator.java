package com.phantomwing.rusticdelight;

import com.phantomwing.rusticdelight.datagen.ModBlockLootTableProvider;
import com.phantomwing.rusticdelight.datagen.ModBlockTagProvider;
import com.phantomwing.rusticdelight.datagen.ModItemTagProvider;
import com.phantomwing.rusticdelight.datagen.ModModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class RusticDelightDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
	}
}

package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.Configuration;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.world.modifiers.ConfigurableRarityFilter;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, RusticDelight.MOD_ID);

    public static final RegistryObject<PlacedFeature> WILD_COTTON = PLACED_FEATURES.register("wild_cotton_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.WILD_COTTON.getHolder().get(), List.of(ConfigurableRarityFilter.withConfigurableChance(Configuration.CHANCE_WILD_COTTON_ID),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> WILD_BELL_PEPPERS = PLACED_FEATURES.register("wild_bell_peppers_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.WILD_BELL_PEPPERS.getHolder().get(), List.of(ConfigurableRarityFilter.withConfigurableChance(Configuration.CHANCE_WILD_BELL_PEPPERS_ID),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}

package com.phantomwing.rusticdelight.world;


import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.world.modifiers.ConfigurableRarityFilter;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> WILD_BELL_PEPPERS_PLACED_KEY = registerKey("wild_bell_peppers_placed");
    public static final ResourceKey<PlacedFeature> WILD_COTTON_PLACED_KEY = registerKey("wild_cotton_placed");
    public static final ResourceKey<PlacedFeature> WILD_COFFEE_PLACED_KEY = registerKey("wild_coffee_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // 26.1: tries values match the original RandomPatchConfiguration (32 cotton, 48 peppers/coffee).
        registerWildCrop(context, configuredFeatures, WILD_COTTON_PLACED_KEY, ModConfiguredFeatures.WILD_COTTON_KEY, RusticDelightConfig.CHANCE_WILD_COTTON_ID, 32);
        registerWildCrop(context, configuredFeatures, WILD_BELL_PEPPERS_PLACED_KEY, ModConfiguredFeatures.WILD_BELL_PEPPERS_KEY, RusticDelightConfig.CHANCE_WILD_BELL_PEPPERS_ID, 48);
        registerWildCrop(context, configuredFeatures, WILD_COFFEE_PLACED_KEY, ModConfiguredFeatures.WILD_COFFEE_KEY, RusticDelightConfig.CHANCE_WILD_COFFEE_ID, 48);
    }

    private static void registerWildCrop(BootstrapContext<PlacedFeature> context, HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures, ResourceKey<PlacedFeature> placedFeatureKey, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, String configuredChanceId, int tries) {
        register(context, placedFeatureKey, configuredFeatures.getOrThrow(configuredFeatureKey), List.of(
                ConfigurableRarityFilter.withConfigurableChance(configuredChanceId),
                CountPlacement.of(tries),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome())
        );
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}

package com.phantomwing.rusticdelight.world;


import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.world.modifiers.ConfigurableRarityFilter;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.util.valueproviders.TrapezoidInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> WILD_BELL_PEPPERS_PLACED_KEY = registerKey("wild_bell_peppers_placed");
    public static final ResourceKey<PlacedFeature> WILD_COTTON_PLACED_KEY = registerKey("wild_cotton_placed");
    public static final ResourceKey<PlacedFeature> WILD_COFFEE_PLACED_KEY = registerKey("wild_coffee_placed");
    public static final ResourceKey<PlacedFeature> BELL_PEPPER_BLOCK_PATCH_PLACED_KEY = registerKey("bell_pepper_block_patch_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // Scattering for the wild crops lives in the IN_ORDER sub-features, so their placement
        // chain only has to pick one anchor point per chunk.
        registerWildCrop(context, configuredFeatures, WILD_COTTON_PLACED_KEY, ModConfiguredFeatures.WILD_COTTON_KEY, RusticDelightConfig.CHANCE_WILD_COTTON_ID);
        registerWildCrop(context, configuredFeatures, WILD_BELL_PEPPERS_PLACED_KEY, ModConfiguredFeatures.WILD_BELL_PEPPERS_KEY, RusticDelightConfig.CHANCE_WILD_BELL_PEPPERS_ID);
        registerWildCrop(context, configuredFeatures, WILD_COFFEE_PLACED_KEY, ModConfiguredFeatures.WILD_COFFEE_KEY, RusticDelightConfig.CHANCE_WILD_COFFEE_ID);
        registerBellPepperBlockPatch(context, configuredFeatures);
    }

    /**
     * Giant bell peppers, modelled on vanilla's jungle melons ({@code patch_melon}) — 26.1 has no
     * RANDOM_PATCH feature, so the scattering lives in the placement chain. This mirrors
     * {@code patch_melon} step for step, including the biome filter sitting *before* the count so
     * the biome is tested once at the patch anchor rather than per scattered block (checking it
     * last would cull peppers off the edge of a patch that straddles a biome border).
     *
     * <p>Two deliberate departures: the rarity filter is config-driven, and the count stays at 48
     * (melons use 64) to match the density the other version branches ship.
     * noFluid() matters because water is replaceable — without it these spawn submerged.
     */
    private static void registerBellPepperBlockPatch(BootstrapContext<PlacedFeature> context,
                                                     HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures) {
        register(context, BELL_PEPPER_BLOCK_PATCH_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.BELL_PEPPER_BLOCK_PATCH_KEY),
                List.of(
                        ConfigurableRarityFilter.withConfigurableChance(RusticDelightConfig.CHANCE_BELL_PEPPER_BLOCK_PATCH_ID),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome(),
                        CountPlacement.of(48),
                        RandomOffsetPlacement.of(TrapezoidInt.of(-7, 7, 0), TrapezoidInt.of(-3, 3, 0)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.replaceable(),
                                BlockPredicate.noFluid(),
                                BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), Blocks.GRASS_BLOCK)
                        ))
                ));
    }

    private static void registerWildCrop(BootstrapContext<PlacedFeature> context, HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures, ResourceKey<PlacedFeature> placedFeatureKey, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, String configuredChanceId) {
        register(context, placedFeatureKey, configuredFeatures.getOrThrow(configuredFeatureKey), List.of(ConfigurableRarityFilter.withConfigurableChance(configuredChanceId),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())
        );
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
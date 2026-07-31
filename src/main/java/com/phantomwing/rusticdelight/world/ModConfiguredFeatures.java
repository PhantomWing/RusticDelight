package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.TrapezoidInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import vectorwing.farmersdelight.common.registry.ModBiomeFeatures;
import vectorwing.farmersdelight.common.world.configuration.InOrderFeatureConfiguration;

import java.util.List;

/**
 * Wild crop patch generation — matches FDR's {@code in_order} pattern (see
 * {@code farmersdelight:wild_carrots}). Each patch lays down coarse dirt where the surface is a
 * natural overworld substrate, then the wild crop on top, then an ambiance block (short grass
 * for forest crops, bush for jungle crops) to soften the silhouette.
 *
 * <p>We target {@code minecraft:substrate_overworld} (added in 26.1) which aggregates
 * {@code dirt}, {@code mud}, {@code moss_blocks}, and {@code grass_blocks} — the right semantic
 * for "any natural ground in the overworld". FDR's original JSONs use {@code minecraft:dirt},
 * which on 26.1 was narrowed to just {@code dirt/coarse_dirt/rooted_dirt} (no grass), leaving
 * FDR's own wild crops sparse in grass biomes.
 */
public class ModConfiguredFeatures {
    public static ResourceKey<ConfiguredFeature<?, ?>> WILD_COTTON_KEY = registerKey("wild_cotton");
    public static ResourceKey<ConfiguredFeature<?, ?>> WILD_BELL_PEPPERS_KEY = registerKey("wild_bell_peppers");
    public static ResourceKey<ConfiguredFeature<?, ?>> WILD_COFFEE_KEY = registerKey("wild_coffee");
    public static ResourceKey<ConfiguredFeature<?, ?>> BELL_PEPPER_BLOCK_PATCH_KEY = registerKey("bell_pepper_block_patch");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        // Cotton: short dry grass evokes the wispy, parched look of a cotton field.
        // Bell peppers and coffee live in jungles — bushes match the dense undergrowth.
        registerWildCropPatch(context, WILD_COTTON_KEY, BlockStateProvider.simple(ModBlocks.WILD_COTTON), Blocks.SHORT_DRY_GRASS);
        // Each block in a wild bell pepper patch has a 5% chance to roll the pale or the dark variant.
        registerWildCropPatch(context, WILD_BELL_PEPPERS_KEY, new WeightedStateProvider(
                WeightedList.<BlockState>builder()
                        .add(ModBlocks.WILD_BELL_PEPPERS.defaultBlockState(), 90)
                        .add(ModBlocks.WILD_PALE_BELL_PEPPERS.defaultBlockState(), 5)
                        .add(ModBlocks.WILD_DARK_BELL_PEPPERS.defaultBlockState(), 5)
                        .build()
        ), Blocks.BUSH);
        registerWildCropPatch(context, WILD_COFFEE_KEY, BlockStateProvider.simple(ModBlocks.WILD_COFFEE), Blocks.BUSH);

        registerBellPepperBlockPatch(context, BELL_PEPPER_BLOCK_PATCH_KEY);
    }

    private static void registerWildCropPatch(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                              ResourceKey<ConfiguredFeature<?, ?>> key, BlockStateProvider cropProvider, Block ambianceBlock) {
        HolderSet<PlacedFeature> subFeatures = HolderSet.direct(
                coarseDirtSubFeature(),
                cropSubFeature(cropProvider),
                ambianceSubFeature(ambianceBlock)
        );
        context.register(key, new ConfiguredFeature<>(
                ModBiomeFeatures.IN_ORDER.get(),
                new InOrderFeatureConfiguration(subFeatures)
        ));
    }

    /** Sprinkles coarse dirt onto an exposed dirt-tagged surface. */
    private static Holder<PlacedFeature> coarseDirtSubFeature() {
        return Holder.direct(new PlacedFeature(
                Holder.direct(simpleBlock(Blocks.COARSE_DIRT)),
                List.of(
                        RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(1)),
                        RandomOffsetPlacement.of(TrapezoidInt.of(-6, 6, 0), TrapezoidInt.of(-3, 3, 0)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.replaceable(new BlockPos(0, 1, 0)),
                                BlockPredicate.matchesTag(BlockTags.SUBSTRATE_OVERWORLD)
                        ))
                )
        ));
    }

    /** Places the wild crop in air above a dirt-tagged block. */
    private static Holder<PlacedFeature> cropSubFeature(BlockStateProvider cropProvider) {
        return Holder.direct(new PlacedFeature(
                Holder.direct(simpleBlock(cropProvider)),
                List.of(
                        RandomOffsetPlacement.of(TrapezoidInt.of(-4, 4, 0), TrapezoidInt.of(-3, 3, 0)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesBlocks(Blocks.AIR),
                                BlockPredicate.matchesTag(new BlockPos(0, -1, 0), BlockTags.SUBSTRATE_OVERWORLD)
                        ))
                )
        ));
    }

    /** Scatters the configured ambiance block (short grass, bush, …) around the patch. */
    private static Holder<PlacedFeature> ambianceSubFeature(Block ambianceBlock) {
        return Holder.direct(new PlacedFeature(
                Holder.direct(simpleBlock(ambianceBlock)),
                List.of(
                        RandomOffsetPlacement.of(TrapezoidInt.of(-6, 6, 0), TrapezoidInt.of(-3, 3, 0)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesBlocks(Blocks.AIR),
                                BlockPredicate.matchesTag(new BlockPos(0, -1, 0), BlockTags.SUBSTRATE_OVERWORLD)
                        ))
                )
        ));
    }

    /**
     * A patch of giant bell peppers. The pale and dark colours are far rarer than the three common
     * ones.
     *
     * <p>26.1 removed {@code Feature.RANDOM_PATCH} / {@code RandomPatchConfiguration}, so the
     * scattering that used to live in the configuration (tries / xzSpread / ySpread) now lives in
     * the placement chain — see {@code ModPlacedFeatures.registerBellPepperBlockPatch}. This is the
     * same shape vanilla's own {@code patch_pumpkin} uses on 26.1.
     */
    private static void registerBellPepperBlockPatch(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                     ResourceKey<ConfiguredFeature<?, ?>> key) {
        WeightedStateProvider provider = new WeightedStateProvider(
                WeightedList.<BlockState>builder()
                        .add(ModBlocks.BELL_PEPPER_RED_BLOCK.defaultBlockState(), 90)
                        .add(ModBlocks.BELL_PEPPER_YELLOW_BLOCK.defaultBlockState(), 90)
                        .add(ModBlocks.BELL_PEPPER_GREEN_BLOCK.defaultBlockState(), 90)
                        .add(ModBlocks.BELL_PEPPER_ORANGE_BLOCK.defaultBlockState(), 5)
                        .add(ModBlocks.BELL_PEPPER_PINK_BLOCK.defaultBlockState(), 5)
                        .add(ModBlocks.BELL_PEPPER_WHITE_BLOCK.defaultBlockState(), 5)
                        .add(ModBlocks.BELL_PEPPER_BLUE_BLOCK.defaultBlockState(), 5)
                        .add(ModBlocks.BELL_PEPPER_PURPLE_BLOCK.defaultBlockState(), 5)
                        .add(ModBlocks.BELL_PEPPER_BLACK_BLOCK.defaultBlockState(), 5)
                        .build()
        );
        context.register(key, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(provider)));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ConfiguredFeature<?, ?> simpleBlock(Block block) {
        return simpleBlock(BlockStateProvider.simple(block));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ConfiguredFeature<?, ?> simpleBlock(BlockStateProvider provider) {
        return new ConfiguredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(provider));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, name));
    }
}

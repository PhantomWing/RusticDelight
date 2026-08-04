package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import vectorwing.farmersdelight.common.registry.ModBiomeFeatures;
import vectorwing.farmersdelight.common.world.configuration.WildCropConfiguration;

import java.util.List;

public class ModConfiguredFeatures {
    // Matches Farmer's Delight's own wild crop patches (see WildCropGeneration).
    private static final int WILD_CROP_TRIES = 64;
    private static final int WILD_CROP_XZ_SPREAD = 6;
    private static final int WILD_CROP_Y_SPREAD = 3;

    public static ResourceKey<ConfiguredFeature<?, ?>> WILD_COTTON_KEY = registerKey("wild_cotton");
    public static ResourceKey<ConfiguredFeature<?, ?>> WILD_BELL_PEPPERS_KEY = registerKey("wild_bell_peppers");
    public static ResourceKey<ConfiguredFeature<?, ?>> WILD_COFFEE_KEY = registerKey("wild_coffee");
    public static ResourceKey<ConfiguredFeature<?, ?>> BELL_PEPPER_BLOCK_PATCH_KEY = registerKey("bell_pepper_block_patch");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
        registerWildCrops(context);
        registerBellPepperBlockPatch(context, BELL_PEPPER_BLOCK_PATCH_KEY, 48, 7, 3);
    }

    private static void registerWildCrops(BootstapContext<ConfiguredFeature<?, ?>> context) {
        // Short grass suits the dry, wispy look of a cotton field; ferns match jungle undergrowth.
        registerWildCropPatch(context, WILD_COTTON_KEY, BlockStateProvider.simple(ModBlocks.WILD_COTTON.get()), Blocks.GRASS);
        // Each block in a wild bell pepper patch has a 5% chance to roll the pale or the dark variant.
        registerWildCropPatch(context, WILD_BELL_PEPPERS_KEY, new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder()
                        .add(ModBlocks.WILD_BELL_PEPPERS.get().defaultBlockState(), 90)
                        .add(ModBlocks.WILD_PALE_BELL_PEPPERS.get().defaultBlockState(), 5)
                        .add(ModBlocks.WILD_DARK_BELL_PEPPERS.get().defaultBlockState(), 5)
                        .build()
        ), Blocks.FERN);
        registerWildCropPatch(context, WILD_COFFEE_KEY, BlockStateProvider.simple(ModBlocks.WILD_COFFEE.get()), Blocks.FERN);
    }

    /**
     * A wild crop patch built on Farmer's Delight's own {@code WILD_CROP} feature: it lays coarse
     * dirt over the surface, plants the crop on top, and scatters an ambiance block around it, so
     * our patches read the same way as FD's wild carrots instead of a lone crop sitting in grass.
     * Scattering (tries/spread) lives in the configuration, so the placed feature stays untouched.
     */
    private static void registerWildCropPatch(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, BlockStateProvider cropProvider, Block ambianceBlock) {
        context.register(key, new ConfiguredFeature<>(
                ModBiomeFeatures.WILD_CROP.get(),
                new WildCropConfiguration(
                        WILD_CROP_TRIES, WILD_CROP_XZ_SPREAD, WILD_CROP_Y_SPREAD,
                        plantSubFeature(cropProvider),
                        plantSubFeature(BlockStateProvider.simple(ambianceBlock)),
                        floorSubFeature(Blocks.COARSE_DIRT)
                )
        ));
    }

    /** Places a block in air that sits directly on top of a dirt-tagged block. */
    private static Holder<PlacedFeature> plantSubFeature(BlockStateProvider provider) {
        return Holder.direct(new PlacedFeature(
                Holder.direct(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(provider))),
                List.of(BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.matchesBlocks(Blocks.AIR),
                        BlockPredicate.matchesTag(new Vec3i(0, -1, 0), BlockTags.DIRT)
                )))
        ));
    }

    /** Replaces an exposed dirt-tagged surface block (grass, dirt, podzol, ...) with the floor block. */
    private static Holder<PlacedFeature> floorSubFeature(Block floorBlock) {
        return Holder.direct(new PlacedFeature(
                Holder.direct(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(floorBlock)))),
                List.of(BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.replaceable(new Vec3i(0, 1, 0)),
                        BlockPredicate.matchesTag(BlockTags.DIRT)
                )))
        ));
    }

    // A melon-like patch of randomly colored bell pepper blocks. Weights total 300, so the
    // pale group (orange/pink/white) and the dark group (blue/purple/black) are 5% each.
    private static void registerBellPepperBlockPatch(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, int tries, int xzSpread, int ySpread) {
        WeightedStateProvider provider = new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder()
                        .add(ModBlocks.BELL_PEPPER_RED_BLOCK.get().defaultBlockState(), 90)
                        .add(ModBlocks.BELL_PEPPER_YELLOW_BLOCK.get().defaultBlockState(), 90)
                        .add(ModBlocks.BELL_PEPPER_GREEN_BLOCK.get().defaultBlockState(), 90)
                        .add(ModBlocks.BELL_PEPPER_ORANGE_BLOCK.get().defaultBlockState(), 5)
                        .add(ModBlocks.BELL_PEPPER_PINK_BLOCK.get().defaultBlockState(), 5)
                        .add(ModBlocks.BELL_PEPPER_WHITE_BLOCK.get().defaultBlockState(), 5)
                        .add(ModBlocks.BELL_PEPPER_BLUE_BLOCK.get().defaultBlockState(), 5)
                        .add(ModBlocks.BELL_PEPPER_PURPLE_BLOCK.get().defaultBlockState(), 5)
                        .add(ModBlocks.BELL_PEPPER_BLACK_BLOCK.get().defaultBlockState(), 5)
                        .build()
        );
        register(context, key, Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        tries,
                        xzSpread,
                        ySpread,
                        // Melon-style: only place on a replaceable, fluid-free spot with grass below.
                        // noFluid() matters because water is replaceable - without it these spawn submerged.
                        PlacementUtils.filtered(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(provider),
                                BlockPredicate.allOf(
                                        BlockPredicate.replaceable(),
                                        BlockPredicate.noFluid(),
                                        BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.GRASS_BLOCK)
                                )
                        )
                )
        );
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(RusticDelight.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}

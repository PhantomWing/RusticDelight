package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.util.random.WeightedList;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import vectorwing.farmersdelight.common.registry.ModBiomeFeatures;
import vectorwing.farmersdelight.common.world.configuration.WildCropConfiguration;

import java.util.List;

/**
 * Wild crop patches built on Farmer's Delight's own {@code WILD_CROP} feature: each patch lays
 * coarse dirt over the surface, plants the crop on top, and scatters an ambiance block around it,
 * so our patches read the same way as FDR's wild carrots instead of a lone crop sitting in grass.
 *
 * <p>26.1 expresses this with FDR's {@code IN_ORDER} feature, but that isn't registered in the
 * 1.21.11 builds of FDR — only {@code WILD_CROP} is. The visual result is the same; {@code
 * WILD_CROP} additionally carries its own tries/spread, so the placed feature needs no
 * CountPlacement.
 *
 * <p>Targets {@code minecraft:dirt}, which on 1.21.11 still aggregates dirt, grass, podzol, moss
 * and mud. (26.1 narrowed that tag and had to switch to {@code substrate_overworld}.)
 */
public class ModConfiguredFeatures {
    // Matches FDR's own wild crop patches (see WildCropGeneration).
    private static final int TRIES = 64;
    private static final int XZ_SPREAD = 6;
    private static final int Y_SPREAD = 3;

    public static ResourceKey<ConfiguredFeature<?, ?>> WILD_COTTON_KEY = registerKey("wild_cotton");
    public static ResourceKey<ConfiguredFeature<?, ?>> WILD_BELL_PEPPERS_KEY = registerKey("wild_bell_peppers");
    public static ResourceKey<ConfiguredFeature<?, ?>> WILD_COFFEE_KEY = registerKey("wild_coffee");
    public static ResourceKey<ConfiguredFeature<?, ?>> BELL_PEPPER_BLOCK_PATCH_KEY = registerKey("bell_pepper_block_patch");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        registerWildCrops(context);
        registerBellPepperBlockPatch(context, BELL_PEPPER_BLOCK_PATCH_KEY, 48, 7, 3);
    }

    private static void registerWildCrops(BootstrapContext<ConfiguredFeature<?, ?>> context) {
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
    }

    private static void registerWildCropPatch(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                              ResourceKey<ConfiguredFeature<?, ?>> key, BlockStateProvider cropProvider, Block ambianceBlock) {
        context.register(key, new ConfiguredFeature<>(
                ModBiomeFeatures.WILD_CROP.get(),
                new WildCropConfiguration(
                        TRIES, XZ_SPREAD, Y_SPREAD,
                        plantSubFeature(cropProvider),
                        plantSubFeature(BlockStateProvider.simple(ambianceBlock)),
                        floorSubFeature(Blocks.COARSE_DIRT)
                )
        ));
    }

    /**
     * A patch of giant bell peppers, melon-style: only on a replaceable, fluid-free spot with grass
     * below. The pale and dark colours are far rarer than the three common ones.
     */
    private static void registerBellPepperBlockPatch(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                     ResourceKey<ConfiguredFeature<?, ?>> key, int tries, int xzSpread, int ySpread) {
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
        context.register(key, new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        tries, xzSpread, ySpread,
                        // noFluid() matters because water is replaceable - without it these spawn submerged.
                        PlacementUtils.filtered(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(provider),
                                BlockPredicate.allOf(
                                        BlockPredicate.replaceable(),
                                        BlockPredicate.noFluid(),
                                        BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), Blocks.GRASS_BLOCK)
                                )
                        )
                )
        ));
    }

    /** Places a block in air that sits directly on top of a dirt-tagged block. */
    private static Holder<PlacedFeature> plantSubFeature(BlockStateProvider provider) {
        return Holder.direct(new PlacedFeature(
                Holder.direct(simpleBlock(provider)),
                List.of(BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.matchesBlocks(Blocks.AIR),
                        BlockPredicate.matchesTag(new Vec3i(0, -1, 0), BlockTags.DIRT)
                )))
        ));
    }

    /** Replaces an exposed dirt-tagged surface block (grass, dirt, podzol, …) with the floor block. */
    private static Holder<PlacedFeature> floorSubFeature(Block floorBlock) {
        return Holder.direct(new PlacedFeature(
                Holder.direct(simpleBlock(BlockStateProvider.simple(floorBlock))),
                List.of(BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.replaceable(new Vec3i(0, 1, 0)),
                        BlockPredicate.matchesTag(BlockTags.DIRT)
                )))
        ));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ConfiguredFeature<?, ?> simpleBlock(BlockStateProvider provider) {
        return new ConfiguredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(provider));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, name));
    }
}

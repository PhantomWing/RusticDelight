package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import net.minecraft.core.Holder;
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

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        registerWildCrops(context);
    }

    private static void registerWildCrops(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        // Cotton: short dry grass evokes the wispy, parched look of a cotton field.
        // Bell peppers and coffee live in jungles — bushes match the dense undergrowth.
        registerWildCropPatch(context, WILD_COTTON_KEY, ModBlocks.WILD_COTTON, Blocks.SHORT_DRY_GRASS);
        registerWildCropPatch(context, WILD_BELL_PEPPERS_KEY, ModBlocks.WILD_BELL_PEPPERS, Blocks.BUSH);
        registerWildCropPatch(context, WILD_COFFEE_KEY, ModBlocks.WILD_COFFEE, Blocks.BUSH);
    }

    private static void registerWildCropPatch(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                              ResourceKey<ConfiguredFeature<?, ?>> key, Block cropBlock, Block ambianceBlock) {
        context.register(key, new ConfiguredFeature<>(
                ModBiomeFeatures.WILD_CROP.get(),
                new WildCropConfiguration(
                        TRIES, XZ_SPREAD, Y_SPREAD,
                        plantSubFeature(cropBlock),
                        plantSubFeature(ambianceBlock),
                        floorSubFeature(Blocks.COARSE_DIRT)
                )
        ));
    }

    /** Places a block in air that sits directly on top of a dirt-tagged block. */
    private static Holder<PlacedFeature> plantSubFeature(Block block) {
        return Holder.direct(new PlacedFeature(
                Holder.direct(simpleBlock(block)),
                List.of(BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.matchesBlocks(Blocks.AIR),
                        BlockPredicate.matchesTag(new Vec3i(0, -1, 0), BlockTags.DIRT)
                )))
        ));
    }

    /** Replaces an exposed dirt-tagged surface block (grass, dirt, podzol, …) with the floor block. */
    private static Holder<PlacedFeature> floorSubFeature(Block floorBlock) {
        return Holder.direct(new PlacedFeature(
                Holder.direct(simpleBlock(floorBlock)),
                List.of(BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.replaceable(new Vec3i(0, 1, 0)),
                        BlockPredicate.matchesTag(BlockTags.DIRT)
                )))
        ));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ConfiguredFeature<?, ?> simpleBlock(Block block) {
        return new ConfiguredFeature(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(block)));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, name));
    }
}

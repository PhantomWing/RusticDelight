package com.phantomwing.rusticdelight.world.modifiers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.phantomwing.rusticdelight.Configuration;
import com.phantomwing.rusticdelight.world.ModPlacementModifiers;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import org.jetbrains.annotations.NotNull;

public class ConfigurableRarityFilter extends PlacementFilter {
    public static final MapCodec<ConfigurableRarityFilter> CODEC = RecordCodecBuilder.mapCodec((builder) ->
            builder.group(
                    ExtraCodecs.NON_EMPTY_STRING.fieldOf("option").forGetter((instance) -> instance.chance)
            ).apply(builder, ConfigurableRarityFilter::new));

    private final String chance;

    private ConfigurableRarityFilter(String chance) {
        this.chance = chance;
    }

    public static ConfigurableRarityFilter withConfigurableChance(String chance) {
        return new ConfigurableRarityFilter(chance);
    }

    protected boolean shouldPlace(@NotNull PlacementContext context, RandomSource random, @NotNull BlockPos pos) {
        int configuredValue = Configuration.getIntConfigurationValue(this.chance);

        // When the user has entered zero chance, nothing should be placed.
        if (configuredValue <= 0)
        {
            return false;
        }

        return random.nextFloat() < 1.0F / configuredValue;
    }

    public @NotNull PlacementModifierType<?> type() {
        return ModPlacementModifiers.CONFIGURABLE_RARITY_FILTER.get();
    }
}
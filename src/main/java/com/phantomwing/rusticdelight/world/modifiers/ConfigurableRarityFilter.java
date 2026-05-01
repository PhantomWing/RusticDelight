package com.phantomwing.rusticdelight.world.modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.world.ModPlacementModifiers;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;
import org.jetbrains.annotations.NotNull;

public class ConfigurableRarityFilter extends AbstractConditionalPlacementModifier {
    public static final Codec<ConfigurableRarityFilter> MODIFIER_CODEC = RecordCodecBuilder.create((builder) ->
            builder.group(
                    Codecs.NON_EMPTY_STRING.fieldOf("option").forGetter((instance) -> instance.chance)
            ).apply(builder, ConfigurableRarityFilter::new));

    private final String chance;

    private ConfigurableRarityFilter(String chance) {
        this.chance = chance;
    }

    public static ConfigurableRarityFilter withConfigurableChance(String chance) {
        return new ConfigurableRarityFilter(chance);
    }

    @Override
    protected boolean shouldPlace(@NotNull FeaturePlacementContext context, Random random, @NotNull BlockPos pos) {
        int configuredValue = RusticDelightConfig.getIntConfigurationValue(this.chance);

        // When the user has entered zero chance, nothing should be placed.
        if (configuredValue <= 0) {
            return false;
        }

        return random.nextFloat() < 1.0F / configuredValue;
    }

    @Override
    public @NotNull PlacementModifierType<?> getType() {
        return ModPlacementModifiers.CONFIGURABLE_RARITY_FILTER;
    }
}

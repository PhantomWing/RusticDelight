package com.phantomwing.rusticdelight.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.phantomwing.rusticdelight.Configuration;
import net.neoforged.neoforge.common.conditions.ICondition;
import org.jetbrains.annotations.NotNull;

/**
 * Recipe/loot condition that resolves to a boolean config option, so recipes can be
 * toggled on or off at runtime (evaluated on datapack load).
 */
public record ConfigBooleanCondition(String settingId) implements ICondition {
    public static final MapCodec<ConfigBooleanCondition> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Codec.STRING.fieldOf("settingId").forGetter(ConfigBooleanCondition::settingId)
    ).apply(inst, ConfigBooleanCondition::new));

    @Override
    public boolean test(ICondition.@NotNull IContext context) {
        return Configuration.getBooleanConfigurationValue(settingId);
    }

    @Override
    public @NotNull MapCodec<? extends ICondition> codec() {
        return CODEC;
    }
}

package com.phantomwing.rusticdelight.condition;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.RusticDelight;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import org.jetbrains.annotations.Nullable;

/**
 * Loads a recipe, loot table or advancement only while the named boolean config option is on.
 * The Fabric counterpart of the NeoForge branch's condition of the same name - same JSON shape
 * ({@code settingId}), so the generated data reads identically across loaders.
 */
public record ConfigBooleanCondition(String settingId) implements ResourceCondition {
    public static final Identifier ID = Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, "config_boolean");

    public static final MapCodec<ConfigBooleanCondition> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    com.mojang.serialization.Codec.STRING.fieldOf("settingId").forGetter(ConfigBooleanCondition::settingId)
            ).apply(instance, ConfigBooleanCondition::new));

    public static final ResourceConditionType<ConfigBooleanCondition> TYPE = ResourceConditionType.create(ID, CODEC);

    @Override
    public ResourceConditionType<?> getType() {
        return TYPE;
    }

    @Override
    public boolean test(@Nullable RegistryOps.RegistryInfoLookup registryLookup) {
        return RusticDelightConfig.getBooleanConfigurationValue(settingId);
    }
}

package com.phantomwing.rusticdelight.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.RusticDelightConfig;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;

/**
 * A Fabric {@link ResourceCondition} that evaluates a named boolean config value at datapack
 * load time. Used to gate data-driven entries (e.g. villager trades) on the user's config —
 * preserves the runtime config gating that was lost when fabric-api dropped TradeOfferHelper.
 *
 * <p>The config id can refer to either a true boolean field or a derived boolean (e.g.
 * {@code wild_cotton_chance > 0}); see {@link RusticDelightConfig#getBooleanConfigurationValue}.
 */
public final class ConfigBooleanCondition implements ResourceCondition {
    public static final MapCodec<ConfigBooleanCondition> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.STRING.fieldOf("config").forGetter(c -> c.configId)
            ).apply(instance, ConfigBooleanCondition::new)
    );

    public static final ResourceConditionType<ConfigBooleanCondition> TYPE = ResourceConditionType.create(
            Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, "config_boolean"),
            CODEC
    );

    private final String configId;

    public ConfigBooleanCondition(String configId) {
        this.configId = configId;
    }

    @Override
    public ResourceConditionType<?> getType() {
        return TYPE;
    }

    @Override
    public boolean test(RegistryOps.RegistryInfoLookup lookup) {
        return RusticDelightConfig.getBooleanConfigurationValue(configId);
    }
}

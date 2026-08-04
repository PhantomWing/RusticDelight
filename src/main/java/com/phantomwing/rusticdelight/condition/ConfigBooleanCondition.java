package com.phantomwing.rusticdelight.condition;

import com.google.gson.JsonObject;
import com.phantomwing.rusticdelight.Configuration;
import com.phantomwing.rusticdelight.RusticDelight;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import org.jetbrains.annotations.NotNull;

/**
 * Recipe/loot condition that resolves to a boolean config option, so recipes can be
 * toggled on or off at runtime (evaluated on datapack load).
 */
public record ConfigBooleanCondition(String settingId) implements ICondition {
    public static final ResourceLocation NAME = new ResourceLocation(RusticDelight.MOD_ID, "config_boolean");

    @Override
    public @NotNull ResourceLocation getID() {
        return NAME;
    }

    @Override
    public boolean test(ICondition.@NotNull IContext context) {
        return Configuration.getBooleanConfigurationValue(settingId);
    }

    /** Forge 1.20.1 serializes conditions through JSON rather than a codec. */
    public static class Serializer implements IConditionSerializer<ConfigBooleanCondition> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, ConfigBooleanCondition value) {
            json.addProperty("settingId", value.settingId());
        }

        @Override
        public @NotNull ConfigBooleanCondition read(JsonObject json) {
            return new ConfigBooleanCondition(json.get("settingId").getAsString());
        }

        @Override
        public @NotNull ResourceLocation getID() {
            return ConfigBooleanCondition.NAME;
        }
    }
}

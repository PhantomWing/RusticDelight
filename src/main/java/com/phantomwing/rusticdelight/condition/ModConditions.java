package com.phantomwing.rusticdelight.condition;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.RusticDelightConfig;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.util.Identifier;

public class ModConditions {
    // Loads a recipe, advancement or loot table only while the named boolean config option is on.
    // Same JSON shape ({"settingId": ...}) as the condition on the Forge/NeoForge branches, so the
    // ported data files read identically across loaders.
    public static final Identifier CONFIG_BOOLEAN_ID = Identifier.of(RusticDelight.MOD_ID, "config_boolean");

    public static void register() {
        ResourceConditions.register(CONFIG_BOOLEAN_ID, json ->
                RusticDelightConfig.getBooleanConfigurationValue(json.get("settingId").getAsString()));
    }
}

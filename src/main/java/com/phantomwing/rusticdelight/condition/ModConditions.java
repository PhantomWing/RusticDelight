package com.phantomwing.rusticdelight.condition;

import net.minecraftforge.common.crafting.CraftingHelper;

public class ModConditions {
    /**
     * Forge 1.20.1 has no condition registry to defer into, so this is a plain static
     * registration. It must run before datapacks load, i.e. from the mod constructor.
     */
    public static void register() {
        CraftingHelper.register(ConfigBooleanCondition.Serializer.INSTANCE);
    }
}

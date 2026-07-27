package com.phantomwing.rusticdelight.condition;

import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;

public class ModConditions {
    public static void registerModConditions() {
        ResourceConditions.register(ConfigBooleanCondition.TYPE);
    }
}

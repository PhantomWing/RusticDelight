package com.phantomwing.rusticdelight.condition;

import com.mojang.serialization.MapCodec;
import com.phantomwing.rusticdelight.RusticDelight;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModConditions {
    public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_CODECS =
            DeferredRegister.create(NeoForgeRegistries.Keys.CONDITION_CODECS, RusticDelight.MOD_ID);

    public static final Supplier<MapCodec<ConfigBooleanCondition>> CONFIG_BOOLEAN =
            CONDITION_CODECS.register("config_boolean", () -> ConfigBooleanCondition.CODEC);

    public static void register(IEventBus eventBus) {
        CONDITION_CODECS.register(eventBus);
    }
}

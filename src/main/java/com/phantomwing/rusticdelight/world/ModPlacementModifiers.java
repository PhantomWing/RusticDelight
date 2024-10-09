package com.phantomwing.rusticdelight.world;

import com.mojang.serialization.MapCodec;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.world.modifiers.ConfigurableRarityFilter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModPlacementModifiers {
    public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIERS = DeferredRegister.create(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE.key(), RusticDelight.MOD_ID);

    public static final Supplier<PlacementModifierType<ConfigurableRarityFilter>> CONFIGURABLE_RARITY_FILTER = PLACEMENT_MODIFIERS.register("configurable_rarity_filter", () -> typeConvert(ConfigurableRarityFilter.CODEC));

    private static <P extends PlacementModifier> PlacementModifierType<P> typeConvert(MapCodec<P> codec) {
        return () -> codec.codec();
    }

    public static void register(IEventBus eventBus) {
        PLACEMENT_MODIFIERS.register(eventBus);
    }
}

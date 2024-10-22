package com.phantomwing.rusticdelight.potions;

import com.phantomwing.rusticdelight.RusticDelight;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, RusticDelight.MOD_ID);

    // Potions
    public static final Holder<Potion> HASTE_POTION = register("haste", MobEffects.DIG_SPEED, 3600);
    public static final Holder<Potion> LONG_HASTE_POTION = register("long_haste", "haste", MobEffects.DIG_SPEED, 9600, 0);
    public static final Holder<Potion> STRONG_HASTE_POTION = register("strong_haste", "haste", MobEffects.DIG_SPEED, 1800, 1);

    private static Holder<Potion> register(String name, Holder<MobEffect> effect, int duration) {
        return POTIONS.register(name, () -> new Potion(name, new MobEffectInstance(effect, duration, 0)));
    }

    private static Holder<Potion> register(String name, String potionName, Holder<MobEffect> effect, int duration, int amplifier) {
        return POTIONS.register(name, () -> new Potion(potionName, new MobEffectInstance(effect, duration, amplifier)));
    }

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
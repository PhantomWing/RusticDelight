package com.phantomwing.rusticdelight.potion;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class ModPotions {
    // Potions
    public static final Potion HASTE_POTION = register("haste", StatusEffects.HASTE, 3600);
    public static final Potion LONG_HASTE_POTION = register("long_haste", "haste", StatusEffects.HASTE, 9600, 0);
    public static final Potion STRONG_HASTE_POTION = register("strong_haste", "haste", StatusEffects.HASTE, 1800, 1);

    private static Potion register(String name, RegistryEntry<StatusEffect> effect, int duration) {
        return Registry.register(Registries.POTION, name, new Potion(name, new StatusEffectInstance(effect, duration, 0)));
    }

    private static Potion  register(String name, String potionName, RegistryEntry<StatusEffect> effect, int duration, int amplifier) {
        return Registry.register(Registries.POTION, name, new Potion(potionName, new StatusEffectInstance(effect, duration, amplifier)));
    }

    public static void registerPotionRecipes() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            // Haste
            builder.registerPotionRecipe(Potions.WATER, ModItems.GOLDEN_COFFEE_BEANS, Registries.POTION.getEntry(HASTE_POTION));
            builder.registerPotionRecipe(Registries.POTION.getEntry(HASTE_POTION), Items.REDSTONE, Registries.POTION.getEntry(LONG_HASTE_POTION));
            builder.registerPotionRecipe(Registries.POTION.getEntry(HASTE_POTION), Items.GLOWSTONE_DUST, Registries.POTION.getEntry(STRONG_HASTE_POTION));
        });
    }

    public static void registerModPotions() {
        RusticDelight.LOGGER.info("Registering potions for " + RusticDelight.MOD_ID);

        registerPotionRecipes();
    }
}

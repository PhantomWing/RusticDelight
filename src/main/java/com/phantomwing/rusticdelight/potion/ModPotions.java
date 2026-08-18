package com.phantomwing.rusticdelight.potion;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.item.ItemFamily;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModPotions {
    // Potions
    public static final Potion HASTE_POTION = register("haste", StatusEffects.HASTE, 3600);
    public static final Potion LONG_HASTE_POTION = register("long_haste", "haste", StatusEffects.HASTE, 9600, 0);
    public static final Potion STRONG_HASTE_POTION = register("strong_haste", "haste", StatusEffects.HASTE, 1800, 1);

    private static Potion register(String name, StatusEffect effect, int duration) {
        return Registry.register(Registries.POTION, name, new Potion(name, new StatusEffectInstance(effect, duration, 0)));
    }

    private static Potion  register(String name, String potionName, StatusEffect effect, int duration, int amplifier) {
        return Registry.register(Registries.POTION, name, new Potion(potionName, new StatusEffectInstance(effect, duration, amplifier)));
    }

    public static void registerPotionRecipes() {
        // Brewed from an Awkward Potion, matching every other potion.
        FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.ofItems(ModItems.GOLDEN_COFFEE_BEANS), Registries.POTION.getEntry(HASTE_POTION).value());
        FabricBrewingRecipeRegistry.registerPotionRecipe(Registries.POTION.getEntry(HASTE_POTION).value(), Ingredient.ofItems(Items.REDSTONE), Registries.POTION.getEntry(LONG_HASTE_POTION).value());
        FabricBrewingRecipeRegistry.registerPotionRecipe(Registries.POTION.getEntry(HASTE_POTION).value(), Ingredient.ofItems(Items.GLOWSTONE_DUST), Registries.POTION.getEntry(STRONG_HASTE_POTION).value());
    }

    public static void registerModPotions() {
        // Golden Coffee Beans are the only brewing ingredient, so the coffee family gates this too.
        if (!RusticDelightConfig.getBooleanConfigurationValue(RusticDelightConfig.ENABLE_POTIONS_ID)
                || !ItemFamily.COFFEE.isEnabled()) {
            return;
        }

        RusticDelight.LOGGER.info("Registering potions for " + RusticDelight.MOD_ID);

        registerPotionRecipes();
    }
}

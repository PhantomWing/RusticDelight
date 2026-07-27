package com.phantomwing.rusticdelight.potion;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.item.ItemFamily;
import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

public class ModPotions {
    // Potions
    public static final Holder<Potion> HASTE_POTION = register("haste", MobEffects.HASTE, 3600);
    public static final Holder<Potion> LONG_HASTE_POTION = register("long_haste", "haste", MobEffects.HASTE, 9600, 0);
    public static final Holder<Potion> STRONG_HASTE_POTION = register("strong_haste", "haste", MobEffects.HASTE, 1800, 1);

    private static Holder<Potion> register(String name, Holder<MobEffect> effect, int duration) {
        return Registry.registerForHolder(
                BuiltInRegistries.POTION,
                Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, name),
                new Potion(name, new MobEffectInstance(effect, duration, 0))
        );
    }

    private static Holder<Potion> register(String name, String potionName, Holder<MobEffect> effect, int duration, int amplifier) {
        return Registry.registerForHolder(
                BuiltInRegistries.POTION,
                Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, name),
                new Potion(potionName, new MobEffectInstance(effect, duration, amplifier))
        );
    }

    private static void registerPotionRecipes() {
        // Golden Coffee Beans are the only brewing ingredient, so the coffee family gates this too.
        if (!RusticDelightConfig.get().enable_potions || !ItemFamily.COFFEE.isEnabled()) {
            return;
        }

        PotionBrewing.Builder.BUILD.register(builder -> {
            // Haste
            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(ModItems.GOLDEN_COFFEE_BEANS), HASTE_POTION);
            builder.registerPotionRecipe(HASTE_POTION, Ingredient.of(Items.REDSTONE), LONG_HASTE_POTION);
            builder.registerPotionRecipe(HASTE_POTION, Ingredient.of(Items.GLOWSTONE_DUST), STRONG_HASTE_POTION);
        });
    }

    public static void registerModPotions() {
        RusticDelight.LOGGER.info("Registering potions for " + RusticDelight.MOD_ID);

        registerPotionRecipes();
    }
}

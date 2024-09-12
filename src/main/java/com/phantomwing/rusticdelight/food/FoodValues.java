package com.phantomwing.rusticdelight.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

import static vectorwing.farmersdelight.common.FoodValues.*;

public class FoodValues {
    // Cooking products
    public static final FoodProperties BATTER = (new FoodProperties.Builder())
            .nutrition(2).saturationModifier(0.2F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).fast().build();
    public static final FoodProperties COOKING_OIL = (new FoodProperties.Builder())
            .nutrition(2).saturationModifier(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 0), 0.5f).fast().build();

    // Basic foods
    public static final FoodProperties POTATO_SLICES = (new FoodProperties.Builder())
            .nutrition(1).saturationModifier(0.6F).build();
    public static final FoodProperties BAKED_POTATO_SLICES = (new FoodProperties.Builder())
            .nutrition(5).saturationModifier(0.8F).build();
    public static final FoodProperties CALAMARI = (new FoodProperties.Builder())
            .nutrition(3).saturationModifier(0.3F).build();
    public static final FoodProperties CALAMARI_SLICE = (new FoodProperties.Builder())
            .nutrition(1).saturationModifier(0.1F).build();
    public static final FoodProperties COOKED_CALAMARI = (new FoodProperties.Builder())
            .nutrition(5).saturationModifier(0.6F).build();
    public static final FoodProperties COOKED_CALAMARI_SLICE = (new FoodProperties.Builder())
            .nutrition(3).saturationModifier(0.6F).build();

    // Handheld foods
    public static final FoodProperties CALAMARI_ROLL = (new FoodProperties.Builder())
            .nutrition(7).saturationModifier(0.6F).build();

    // Bowl foods

    // Plated foods
    public static final FoodProperties FRIED_CHICKEN = (new FoodProperties.Builder())
            .nutrition(12).saturationModifier(0.8F)
            .effect(() -> nourishment(SHORT_DURATION), 1.0f).build();
}

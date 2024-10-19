package com.phantomwing.rusticdelight.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

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
    public static final FoodProperties BELL_PEPPER = (new FoodProperties.Builder())
            .nutrition(1).saturationModifier(0.1F).build();
    public static final FoodProperties COFFEE_BEANS = (new FoodProperties.Builder())
            .nutrition(1).saturationModifier(0.1F).build();
    public static final FoodProperties ROASTED_BELL_PEPPER = (new FoodProperties.Builder())
            .nutrition(5).saturationModifier(0.8F).build();
    public static final FoodProperties POTATO_SLICES = (new FoodProperties.Builder())
            .nutrition(1).saturationModifier(0.3F).build();
    public static final FoodProperties BAKED_POTATO_SLICES = (new FoodProperties.Builder())
            .nutrition(3).saturationModifier(0.6F).build();
    public static final FoodProperties CALAMARI = (new FoodProperties.Builder())
            .nutrition(3).saturationModifier(0.3F).build();
    public static final FoodProperties CALAMARI_SLICE = (new FoodProperties.Builder())
            .nutrition(1).saturationModifier(0.1F).build();
    public static final FoodProperties COOKED_CALAMARI = (new FoodProperties.Builder())
            .nutrition(5).saturationModifier(0.6F).build();
    public static final FoodProperties COOKED_CALAMARI_SLICE = (new FoodProperties.Builder())
            .nutrition(3).saturationModifier(0.6F).build();

    // Sweets
    public static final FoodProperties CHERRY_BLOSSOM_COOKIE = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.1F).build();
    public static final FoodProperties FRUIT_BEIGNET = (new FoodProperties.Builder())
            .nutrition(6).saturationModifier(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0, false, false), 1.0F).build();
    public static final FoodProperties HONEY_PANCAKE = (new FoodProperties.Builder())
            .nutrition(4).saturationModifier(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0, false, false), 1.0F).build();
    public static final FoodProperties CHOCOLATE_PANCAKE = (new FoodProperties.Builder())
            .nutrition(4).saturationModifier(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0, false, false), 1.0F).build();
    public static final FoodProperties CHERRY_BLOSSOM_PANCAKE = (new FoodProperties.Builder())
            .nutrition(4).saturationModifier(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 600, 0, false, false), 1.0F).build();
    public static final FoodProperties VEGETABLE_PANCAKE = (new FoodProperties.Builder())
            .nutrition(4).saturationModifier(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 0, false, false), 1.0F).build();

    // Handheld foods
    public static final FoodProperties CALAMARI_ROLL = (new FoodProperties.Builder())
            .nutrition(7).saturationModifier(0.6F).build();
    public static final FoodProperties CHERRY_BLOSSOM_ROLL = (new FoodProperties.Builder())
            .nutrition(6).saturationModifier(0.5F).build();
    public static final FoodProperties SPRING_ROLLS = (new FoodProperties.Builder())
            .nutrition(6).saturationModifier(0.6F).build();
    public static final FoodProperties STUFFED_BELL_PEPPER = (new FoodProperties.Builder())
            .nutrition(10).saturationModifier(0.7F).build();

    // Bowl foods
    public static final FoodProperties POTATO_SALAD = (new FoodProperties.Builder())
            .nutrition(8).saturationModifier(0.7f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 120, 0), 1.0F).build();
    public static final FoodProperties BELL_PEPPER_SOUP = (new FoodProperties.Builder())
            .nutrition(6).saturationModifier(0.6f)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT, MEDIUM_DURATION, 0), 1.0F).build();

    // Plated foods
    public static final FoodProperties BELL_PEPPER_PASTA = (new FoodProperties.Builder())
            .nutrition(12).saturationModifier(0.8F)
            .effect(() -> nourishment(SHORT_DURATION), 1.0f).build();
    public static final FoodProperties FRIED_CHICKEN = (new FoodProperties.Builder())
            .nutrition(12).saturationModifier(0.8F)
            .effect(() -> nourishment(MEDIUM_DURATION), 1.0f).build();
    public static final FoodProperties FRIED_CALAMARI = (new FoodProperties.Builder())
            .nutrition(12).saturationModifier(0.8F)
            .effect(() -> nourishment(MEDIUM_DURATION), 1.0f).build();
    public static final FoodProperties FRIED_MUSHROOMS = (new FoodProperties.Builder())
            .nutrition(12).saturationModifier(0.8F)
            .effect(() -> nourishment(MEDIUM_DURATION), 1.0f).build();
}

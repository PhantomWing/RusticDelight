package com.phantomwing.rusticdelight.food;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import vectorwing.farmersdelight.common.registry.ModEffects;

import static vectorwing.farmersdelight.common.FoodValues.*;

public class FoodValues {
    // Cooking products
    public static final FoodComponent BATTER = (new FoodComponent.Builder())
            .nutrition(2).saturationModifier(0.2F)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 0.3F).snack().build();
    public static final FoodComponent COOKING_OIL = (new FoodComponent.Builder())
            .nutrition(2).saturationModifier(0.3F)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 400, 0), 0.5f).snack().build();

    // Basic foods
    public static final FoodComponent BELL_PEPPER = (new FoodComponent.Builder())
            .nutrition(1).saturationModifier(0.1F).build();
    public static final FoodComponent ROASTED_COFFEE_BEANS = (new FoodComponent.Builder())
            .nutrition(2).saturationModifier(0.2F).build();
    public static final FoodComponent GOLDEN_COFFEE_BEANS = (new FoodComponent.Builder())
            .nutrition(5).saturationModifier(0.8F).build();
    public static final FoodComponent ROASTED_BELL_PEPPER = (new FoodComponent.Builder())
            .nutrition(5).saturationModifier(0.8F).build();
    public static final FoodComponent POTATO_SLICES = (new FoodComponent.Builder())
            .nutrition(1).saturationModifier(0.3F).build();
    public static final FoodComponent BAKED_POTATO_SLICES = (new FoodComponent.Builder())
            .nutrition(3).saturationModifier(0.6F).build();
    public static final FoodComponent CALAMARI = (new FoodComponent.Builder())
            .nutrition(2).saturationModifier(0.2F).build();
    public static final FoodComponent CALAMARI_SLICE = (new FoodComponent.Builder())
            .nutrition(1).saturationModifier(0.1F).build();
    public static final FoodComponent COOKED_CALAMARI = (new FoodComponent.Builder())
            .nutrition(5).saturationModifier(0.6F).build();
    public static final FoodComponent COOKED_CALAMARI_SLICE = (new FoodComponent.Builder())
            .nutrition(3).saturationModifier(0.6F).build();

    // Drinks
    public static final FoodComponent COFFEE = (new FoodComponent.Builder())
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1800, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1800, 0), 1.0f)
            .alwaysEdible()
            .snack()
            .build();
    public static final FoodComponent MILK_COFFEE = (new FoodComponent.Builder())
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1200, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1200, 0), 1.0f)
            .alwaysEdible()
            .snack()
            .build();
    public static final FoodComponent CHOCOLATE_COFFEE = (new FoodComponent.Builder())
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 900, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 900, 0), 1.0f)
            .alwaysEdible()
            .snack()
            .build();
    public static final FoodComponent HONEY_COFFEE = (new FoodComponent.Builder())
            .nutrition(6).saturationModifier(0.1F)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 900, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 900, 0), 1.0f)
            .alwaysEdible()
            .snack()
            .build();
    public static final FoodComponent DARK_COFFEE = (new FoodComponent.Builder())
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 900, 1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 900, 1), 1.0f)
            .alwaysEdible()
            .snack()
            .build();

    // Sweets
    public static final FoodComponent FRUIT_BEIGNET = (new FoodComponent.Builder())
            .nutrition(6).saturationModifier(0.6F)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0, false, false), 1.0F).build();
    public static final FoodComponent HONEY_PANCAKE = (new FoodComponent.Builder())
            .nutrition(4).saturationModifier(0.6F)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1200, 0, false, false), 1.0F).build();
    public static final FoodComponent CHOCOLATE_PANCAKE = (new FoodComponent.Builder())
            .nutrition(4).saturationModifier(0.6F)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1200, 0, false, false), 1.0F).build();
    public static final FoodComponent CHERRY_BLOSSOM_PANCAKE = (new FoodComponent.Builder())
            .nutrition(4).saturationModifier(0.6F)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 600, 0, false, false), 1.0F).build();
    public static final FoodComponent VEGETABLE_PANCAKE = (new FoodComponent.Builder())
            .nutrition(4).saturationModifier(0.6F)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1200, 0, false, false), 1.0F).build();

    // Handheld foods
    public static final FoodComponent CALAMARI_ROLL = (new FoodComponent.Builder())
            .nutrition(7).saturationModifier(0.6F).build();
    public static final FoodComponent CHERRY_BLOSSOM_ROLL = (new FoodComponent.Builder())
            .nutrition(6).saturationModifier(0.5F).build();
    public static final FoodComponent SPRING_ROLLS = (new FoodComponent.Builder())
            .nutrition(6).saturationModifier(0.6F).build();
    public static final FoodComponent STUFFED_BELL_PEPPER = (new FoodComponent.Builder())
            .nutrition(10).saturationModifier(0.7F).build();

    // Bowl foods
    public static final FoodComponent POTATO_SALAD = (new FoodComponent.Builder())
            .nutrition(8).saturationModifier(0.7f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 120, 0), 1.0F).build();
    public static final FoodComponent BELL_PEPPER_SOUP = (new FoodComponent.Builder())
            .nutrition(6).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(ModEffects.COMFORT, MEDIUM_DURATION, 0), 1.0F).build();

    // Plated foods
    public static final FoodComponent BELL_PEPPER_PASTA = (new FoodComponent.Builder())
            .nutrition(12).saturationModifier(0.8F)
            .statusEffect(nourishment(MEDIUM_DURATION), 1.0f).build();
    public static final FoodComponent FRIED_CHICKEN = (new FoodComponent.Builder())
            .nutrition(12).saturationModifier(0.8F)
            .statusEffect(nourishment(MEDIUM_DURATION), 1.0f).build();
    public static final FoodComponent FRIED_CALAMARI = (new FoodComponent.Builder())
            .nutrition(12).saturationModifier(0.8F)
            .statusEffect(nourishment(MEDIUM_DURATION), 1.0f).build();
    public static final FoodComponent FRIED_MUSHROOMS = (new FoodComponent.Builder())
            .nutrition(12).saturationModifier(0.8F)
            .statusEffect(nourishment(MEDIUM_DURATION), 1.0f).build();
    public static final FoodComponent COFFEE_BRAISED_BEEF = (new FoodComponent.Builder())
            .nutrition(14).saturationModifier(0.75F)
            .statusEffect(nourishment(LONG_DURATION), 1.0f).build();
}

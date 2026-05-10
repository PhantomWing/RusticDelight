package com.phantomwing.rusticdelight.food;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import vectorwing.farmersdelight.common.item.component.consumable.RemoveRandomStatusEffectsConsumeEffect;
import vectorwing.farmersdelight.common.registry.ModEffects;
import vectorwing.farmersdelight.refabricated.FDRefabricatedTags;

import static vectorwing.farmersdelight.common.FoodValues.*;

public class ConsumableValues {
    private static final float CONSUME_FAST = 0.8f;
    private static final float CONSUME_SLOW = 2.0f;

    // Generic
    public static final Consumable FAST_FOOD = Consumables.defaultFood()
            .consumeSeconds(CONSUME_FAST)
            .build();

    // Cooking products
    public static final Consumable BATTER = Consumables.defaultDrink()
            .consumeSeconds(CONSUME_FAST)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 0, false, false), 0.3F))
            .build();

    public static final Consumable COOKING_OIL = Consumables.defaultDrink()
            .consumeSeconds(CONSUME_FAST)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 400, 0, false, false), 0.5F))
            .build();

    public static final Consumable SYRUP = Consumables.defaultDrink()
            .consumeSeconds(CONSUME_FAST)
            .sound(SoundEvents.HONEY_DRINK)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 300, 0, false, false), 1.0F))
            .build();

    // Coffees
    public static final Consumable COFFEE = Consumables.defaultDrink()
            .consumeSeconds(CONSUME_FAST)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 1800, 0, false, false), 1.0F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HASTE, 1800, 0, false, false), 1.0F))
            .build();

    public static final Consumable MILK_COFFEE = Consumables.defaultDrink()
            .consumeSeconds(CONSUME_FAST)
            .onConsume(new RemoveRandomStatusEffectsConsumeEffect(FDRefabricatedTags.MobEffects.MILK_BOTTLE_IGNORED))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 1200, 0, false, false), 1.0F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HASTE, 1200, 0, false, false), 1.0F))
            .build();

    public static final Consumable CHOCOLATE_COFFEE = Consumables.defaultDrink()
            .consumeSeconds(CONSUME_FAST)
            .onConsume(new RemoveRandomStatusEffectsConsumeEffect(FDRefabricatedTags.MobEffects.HOT_COCOA_IGNORED, true))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 900, 0, false, false), 1.0F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HASTE, 900, 0, false, false), 1.0F))
            .build();

    public static final Consumable HONEY_COFFEE = Consumables.defaultDrink()
            .consumeSeconds(CONSUME_FAST)
            .onConsume(new RemoveRandomStatusEffectsConsumeEffect(FDRefabricatedTags.MobEffects.MILK_BOTTLE_IGNORED))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 900, 0, false, false), 1.0F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HASTE, 900, 0, false, false), 1.0F))
            .build();

    public static final Consumable SYRUP_COFFEE = Consumables.defaultDrink()
            .consumeSeconds(CONSUME_FAST)
            .onConsume(new RemoveRandomStatusEffectsConsumeEffect(FDRefabricatedTags.MobEffects.MILK_BOTTLE_IGNORED))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 1800, 0, false, false), 1.0F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HASTE, 1800, 0, false, false), 1.0F))
            .build();

    public static final Consumable DARK_COFFEE = Consumables.defaultDrink()
            .consumeSeconds(CONSUME_FAST)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 900, 1, false, false), 1.0F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HASTE, 900, 1, false, false), 1.0F))
            .build();

    // Sweets
    public static final Consumable FRUIT_BEIGNET = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 600, 0,false, false), 1.0F))
            .build();
    public static final Consumable HONEY_PANCAKE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 1200, 0, false, false), 1.0F))
            .build();
    public static final Consumable CHOCOLATE_PANCAKE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 1200, 0, false, false), 1.0F))
            .build();
    public static final Consumable CHERRY_BLOSSOM_PANCAKE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 600, 0, false, false), 1.0F))
            .build();
    public static final Consumable VEGETABLE_PANCAKE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HASTE, 1200, 0, false, false), 1.0F))
            .build();
    public static final Consumable PUMPKIN_PANCAKE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 600, 0, false, false), 1.0F))
            .build();

    // Bowl foods
    public static final Consumable POTATO_SALAD = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0, false, false), 1.0F))
            .build();
    public static final Consumable SWEET_SALAD = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0, false, false), 1.0F))
            .build();
    public static final Consumable BELL_PEPPER_SOUP = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.COMFORT, MEDIUM_DURATION, 0, false, false), 1.0F))
            .build();

    // Plated foods
    public static final Consumable BELL_PEPPER_PASTA = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(nourishment(MEDIUM_DURATION), 1.0F))
            .build();
    public static final Consumable FRIED_CHICKEN = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(nourishment(MEDIUM_DURATION), 1.0F))
            .build();
    public static final Consumable FRIED_CALAMARI = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(nourishment(MEDIUM_DURATION), 1.0F))
            .build();
    public static final Consumable FRIED_MUSHROOMS = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(nourishment(MEDIUM_DURATION), 1.0F))
            .build();
    public static final Consumable COFFEE_BRAISED_BEEF = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(nourishment(LONG_DURATION), 1.0F))
            .build();
}

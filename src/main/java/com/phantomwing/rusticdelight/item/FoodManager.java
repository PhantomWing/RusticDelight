package com.phantomwing.rusticdelight.item;

import net.minecraft.world.food.FoodProperties;

public class FoodManager {
    // Food
    public static final FoodProperties CALAMARI = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_CALAMARI = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.6F).build();
    public static final FoodProperties POTATO_SLICES = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.6F).build();
    public static final FoodProperties BAKED_POTATO_SLICES = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.8F).build();
    public static final FoodProperties CALAMARI_ROLL = (new FoodProperties.Builder()).nutrition(7).saturationModifier(0.6F).build();
    public static final FoodProperties CALAMARI_SLICE = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.1F).build();
    public static final FoodProperties COOKED_CALAMARI_SLICE = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.6F).build();

    // Drinks
    public static final FoodProperties COOKING_OIL = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.3F).fast().build();
}

package com.phantomwing.rusticdelight.food;

import net.minecraft.world.food.FoodConstants;
import net.minecraft.world.food.FoodProperties;

public class FoodValues {
    // Cooking products
    public static final FoodProperties BATTER = food(2, FoodConstants.FOOD_SATURATION_LOW);
    public static final FoodProperties COOKING_OIL = food(2, FoodConstants.FOOD_SATURATION_LOW);
    public static final FoodProperties SYRUP = food(2, FoodConstants.FOOD_SATURATION_LOW);

    // Basic foods
    public static final FoodProperties BELL_PEPPER = food(2, FoodConstants.FOOD_SATURATION_LOW);
    public static final FoodProperties BELL_PEPPER_SLICE = food(1, FoodConstants.FOOD_SATURATION_POOR);
    public static final FoodProperties ROASTED_COFFEE_BEANS = food(2, FoodConstants.FOOD_SATURATION_LOW);
    public static final FoodProperties GOLDEN_COFFEE_BEANS = food(5, FoodConstants.FOOD_SATURATION_SUPERNATURAL);
    public static final FoodProperties ROASTED_BELL_PEPPER = food(5, FoodConstants.FOOD_SATURATION_GOOD);
    public static final FoodProperties ROASTED_BELL_PEPPER_SLICE = food(3, FoodConstants.FOOD_SATURATION_NORMAL);
    public static final FoodProperties POTATO_SLICES = food(1, FoodConstants.FOOD_SATURATION_POOR);
    public static final FoodProperties BAKED_POTATO_SLICES = food(3, FoodConstants.FOOD_SATURATION_NORMAL);
    public static final FoodProperties CALAMARI = food(2, FoodConstants.FOOD_SATURATION_LOW);
    public static final FoodProperties CALAMARI_SLICE = food(1, FoodConstants.FOOD_SATURATION_POOR);
    public static final FoodProperties COOKED_CALAMARI = food(5, FoodConstants.FOOD_SATURATION_NORMAL);
    public static final FoodProperties COOKED_CALAMARI_SLICE = food(3, FoodConstants.FOOD_SATURATION_NORMAL);

    // Drinks
    public static final FoodProperties COFFEE = alwaysEdibleFood();
    public static final FoodProperties MILK_COFFEE = alwaysEdibleFood();
    public static final FoodProperties CHOCOLATE_COFFEE = alwaysEdibleFood();
    public static final FoodProperties HONEY_COFFEE = alwaysEdibleFood(6, FoodConstants.FOOD_SATURATION_LOW);
    public static final FoodProperties SYRUP_COFFEE = alwaysEdibleFood();
    public static final FoodProperties DARK_COFFEE = alwaysEdibleFood();
    public static final FoodProperties PUMPKIN_COFFEE = alwaysEdibleFood();
    public static final FoodProperties CHERRY_BLOSSOM_COFFEE = alwaysEdibleFood();

    // Sweets
    public static final FoodProperties SYRUP_SANDWICH = food(8, 0.7f);
    public static final FoodProperties FRUIT_BEIGNET = food(6, FoodConstants.FOOD_SATURATION_NORMAL);
    public static final FoodProperties PANCAKE = food(4, FoodConstants.FOOD_SATURATION_NORMAL);
    public static final FoodProperties HONEY_PANCAKE = food(4, FoodConstants.FOOD_SATURATION_NORMAL);
    public static final FoodProperties CHOCOLATE_PANCAKE = food(4, FoodConstants.FOOD_SATURATION_NORMAL);
    public static final FoodProperties CHERRY_BLOSSOM_PANCAKE = food(4, FoodConstants.FOOD_SATURATION_NORMAL);
    public static final FoodProperties VEGETABLE_PANCAKE = food(4, FoodConstants.FOOD_SATURATION_NORMAL);
    public static final FoodProperties PUMPKIN_PANCAKE = food(4, FoodConstants.FOOD_SATURATION_NORMAL);
    public static final FoodProperties COFFEE_PANCAKE = food(4, FoodConstants.FOOD_SATURATION_NORMAL);

    // Handheld foods
    public static final FoodProperties CALAMARI_ROLL = food(7, FoodConstants.FOOD_SATURATION_NORMAL);
    public static final FoodProperties CHERRY_BLOSSOM_ROLL = food(6, 0.5f);
    public static final FoodProperties BELL_PEPPER_ROLL = food(7, FoodConstants.FOOD_SATURATION_GOOD);
    public static final FoodProperties STUFFED_BELL_PEPPER = food(10, 0.7f);

    // Fried foods
    public static final FoodProperties FRIED_DOUGH = food(6, 0.5f);
    public static final FoodProperties SPRING_ROLLS = food(6, FoodConstants.FOOD_SATURATION_NORMAL);
    public static final FoodProperties FRIED_DUMPLINGS = food(10, FoodConstants.FOOD_SATURATION_GOOD);
    public static final FoodProperties FRIED_FISH = food(8, 0.7f);

    // Bowl foods
    public static final FoodProperties POTATO_SALAD = food(8, 0.7f);
    public static final FoodProperties SWEET_SALAD = food(8, 0.7f);
    public static final FoodProperties BELL_PEPPER_SOUP = food(6, FoodConstants.FOOD_SATURATION_NORMAL);
    // Heartier than the vegetable soup, since it carries calamari, potato and milk.
    public static final FoodProperties CALAMARI_SOUP = food(10, FoodConstants.FOOD_SATURATION_GOOD);

    // Plated foods
    public static final FoodProperties BELL_PEPPER_PASTA = food(12, FoodConstants.FOOD_SATURATION_GOOD);
    public static final FoodProperties FRIED_CHICKEN = food(12, FoodConstants.FOOD_SATURATION_GOOD);
    public static final FoodProperties FRIED_CALAMARI = food(12, FoodConstants.FOOD_SATURATION_GOOD);
    public static final FoodProperties FRIED_MUSHROOMS = food(12, FoodConstants.FOOD_SATURATION_GOOD);
    public static final FoodProperties COFFEE_BRAISED_BEEF = food(14, FoodConstants.FOOD_SATURATION_GOOD);

    private static FoodProperties food(int nutrition, float saturation) {
        return (new FoodProperties.Builder())
                .nutrition(nutrition)
                .saturationModifier(saturation)
                .build();
    }

    private static FoodProperties alwaysEdibleFood() {
        return (new FoodProperties.Builder())
                .alwaysEdible()
                .build();
    }

    private static FoodProperties alwaysEdibleFood(int nutrition, float saturation) {
        return (new FoodProperties.Builder())
                .nutrition(nutrition)
                .saturationModifier(saturation)
                .alwaysEdible()
                .build();
    }
}

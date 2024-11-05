package com.phantomwing.rusticdelight.util;

import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.entity.passive.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class AnimalFoodHelper {
    private static void registerChickenFood(ItemConvertible... items) {
        Ingredient newFood = Ingredient.ofItems(items);
        ChickenEntity.BREEDING_INGREDIENT = Ingredient.ofStacks(Stream.concat(Arrays.stream(ChickenEntity.BREEDING_INGREDIENT.getMatchingStacks()), Arrays.stream(newFood.getMatchingStacks())));
    }

    private static void registerPigFood(ItemConvertible... items) {
        Ingredient newFood = Ingredient.ofItems(items);
        PigEntity.BREEDING_INGREDIENT = Ingredient.ofStacks(Stream.concat(Arrays.stream(PigEntity.BREEDING_INGREDIENT.getMatchingStacks()), Arrays.stream(newFood.getMatchingStacks())));
    }

    private static void registerParrotFood(Item... items) {
        Collections.addAll(ParrotEntity.TAMING_INGREDIENTS, items);
    }

    private static void registerCatFood(Item... items) {
        Ingredient newFood = Ingredient.ofItems(items);
        CatEntity.TAMING_INGREDIENT = Ingredient.ofStacks(Stream.concat(Arrays.stream(CatEntity.TAMING_INGREDIENT.getMatchingStacks()), Arrays.stream(newFood.getMatchingStacks())));
        OcelotEntity.TAMING_INGREDIENT = Ingredient.ofStacks(Stream.concat(Arrays.stream(OcelotEntity.TAMING_INGREDIENT.getMatchingStacks()), Arrays.stream(newFood.getMatchingStacks())));
    }


    public static void registerAnimalFood() {
        registerChickenFood(ModItems.COTTON_SEEDS, ModItems.BELL_PEPPER_SEEDS);
        registerPigFood(ModItems.BELL_PEPPER_GREEN, ModItems.BELL_PEPPER_YELLOW, ModItems.BELL_PEPPER_RED);
        registerCatFood(ModItems.CALAMARI);
        registerParrotFood(ModItems.COTTON_SEEDS, ModItems.BELL_PEPPER_SEEDS);
    }
}

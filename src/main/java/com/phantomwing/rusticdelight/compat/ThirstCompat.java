package com.phantomwing.rusticdelight.compat;

import com.phantomwing.rusticdelight.item.ModItems;
import dev.ghen.thirst.foundation.common.event.RegisterThirstValueEvent;
import net.neoforged.bus.api.SubscribeEvent;

public class ThirstCompat {

    @SubscribeEvent
    public static void onRegisterThirstValues(RegisterThirstValueEvent event) {
        // Drinks - Coffee (high hydration, these are liquid beverages)
        event.addDrink(ModItems.COFFEE.get(), 5, 8);
        event.addDrink(ModItems.DARK_COFFEE.get(), 4, 6);
        event.addDrink(ModItems.SYRUP_COFFEE.get(), 8, 11);
        event.addDrink(ModItems.MILK_COFFEE.get(), 6, 10);
        event.addDrink(ModItems.CHOCOLATE_COFFEE.get(), 8, 11);
        event.addDrink(ModItems.HONEY_COFFEE.get(), 8, 11);
        event.addDrink(ModItems.PUMPKIN_COFFEE.get(), 8, 11);
        event.addDrink(ModItems.CHERRY_BLOSSOM_COFFEE.get(), 8, 11);

        // Drinks - Other liquids
        event.addDrink(ModItems.COOKING_OIL.get(), 2, 3);
        event.addDrink(ModItems.SYRUP.get(), 2, 3);

        event.addFood(ModItems.BATTER.get(), 2, 3);

        // Soups and stews (moderate hydration)
        event.addFood(ModItems.BELL_PEPPER_SOUP.get(), 5, 6);
        event.addFood(ModItems.CALAMARI_SOUP.get(), 5, 6);

        // Salads (moderate hydration)
        event.addFood(ModItems.SWEET_SALAD.get(), 4, 5);

        // Juicy foods (low hydration)
        event.addFood(ModItems.BELL_PEPPER_GREEN.get(), 2, 2);
        event.addFood(ModItems.BELL_PEPPER_YELLOW.get(), 2, 2);
        event.addFood(ModItems.BELL_PEPPER_RED.get(), 2, 2);
        event.addFood(ModItems.BELL_PEPPER_SLICE_GREEN.get(), 1, 1);
        event.addFood(ModItems.BELL_PEPPER_SLICE_YELLOW.get(), 1, 1);
        event.addFood(ModItems.BELL_PEPPER_SLICE_RED.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_SLICE_GREEN.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_SLICE_YELLOW.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_SLICE_RED.get(), 1, 1);
        event.addFood(ModItems.BELL_PEPPER_ROLL_GREEN.get(), 1, 1);
        event.addFood(ModItems.BELL_PEPPER_ROLL_YELLOW.get(), 1, 1);
        event.addFood(ModItems.BELL_PEPPER_ROLL_RED.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_GREEN.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_YELLOW.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_RED.get(), 1, 1);
        event.addFood(ModItems.STUFFED_BELL_PEPPER_GREEN.get(), 2, 2);
        event.addFood(ModItems.STUFFED_BELL_PEPPER_YELLOW.get(), 2, 2);
        event.addFood(ModItems.STUFFED_BELL_PEPPER_RED.get(), 2, 2);
        event.addFood(ModItems.BELL_PEPPER_ORANGE.get(), 2, 2);
        event.addFood(ModItems.BELL_PEPPER_WHITE.get(), 2, 2);
        event.addFood(ModItems.BELL_PEPPER_PINK.get(), 2, 2);
        event.addFood(ModItems.BELL_PEPPER_SLICE_ORANGE.get(), 1, 1);
        event.addFood(ModItems.BELL_PEPPER_SLICE_WHITE.get(), 1, 1);
        event.addFood(ModItems.BELL_PEPPER_SLICE_PINK.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_SLICE_ORANGE.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_SLICE_WHITE.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_SLICE_PINK.get(), 1, 1);
        event.addFood(ModItems.BELL_PEPPER_ROLL_ORANGE.get(), 1, 1);
        event.addFood(ModItems.BELL_PEPPER_ROLL_WHITE.get(), 1, 1);
        event.addFood(ModItems.BELL_PEPPER_ROLL_PINK.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_ORANGE.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_WHITE.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_PINK.get(), 1, 1);
        event.addFood(ModItems.STUFFED_BELL_PEPPER_ORANGE.get(), 2, 2);
        event.addFood(ModItems.STUFFED_BELL_PEPPER_WHITE.get(), 2, 2);
        event.addFood(ModItems.STUFFED_BELL_PEPPER_PINK.get(), 2, 2);
        event.addFood(ModItems.BELL_PEPPER_BLUE.get(), 2, 2);
        event.addFood(ModItems.BELL_PEPPER_PURPLE.get(), 2, 2);
        event.addFood(ModItems.BELL_PEPPER_BLACK.get(), 2, 2);
        event.addFood(ModItems.BELL_PEPPER_SLICE_BLUE.get(), 1, 1);
        event.addFood(ModItems.BELL_PEPPER_SLICE_PURPLE.get(), 1, 1);
        event.addFood(ModItems.BELL_PEPPER_SLICE_BLACK.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_SLICE_BLUE.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_SLICE_PURPLE.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_SLICE_BLACK.get(), 1, 1);
        event.addFood(ModItems.BELL_PEPPER_ROLL_BLUE.get(), 1, 1);
        event.addFood(ModItems.BELL_PEPPER_ROLL_PURPLE.get(), 1, 1);
        event.addFood(ModItems.BELL_PEPPER_ROLL_BLACK.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_BLUE.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_PURPLE.get(), 1, 1);
        event.addFood(ModItems.ROASTED_BELL_PEPPER_BLACK.get(), 1, 1);
        event.addFood(ModItems.STUFFED_BELL_PEPPER_BLUE.get(), 2, 2);
        event.addFood(ModItems.STUFFED_BELL_PEPPER_PURPLE.get(), 2, 2);
        event.addFood(ModItems.STUFFED_BELL_PEPPER_BLACK.get(), 2, 2);
    }
}

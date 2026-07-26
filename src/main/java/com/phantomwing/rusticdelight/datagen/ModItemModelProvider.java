package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModItemModelProvider {
    public static void registerModels(ItemModelGenerators g) {
        // Items
        simpleItem(g, ModItems.BAKED_POTATO_SLICES);
        simpleItem(g, ModItems.BATTER);
        simpleItem(g, ModItems.SYRUP);
        simpleItem(g, ModItems.BELL_PEPPER_GREEN);
        simpleItem(g, ModItems.BELL_PEPPER_YELLOW);
        simpleItem(g, ModItems.BELL_PEPPER_RED);
        simpleItem(g, ModItems.BELL_PEPPER_ORANGE);
        simpleItem(g, ModItems.BELL_PEPPER_WHITE);
        simpleItem(g, ModItems.BELL_PEPPER_PINK);
        simpleItem(g, ModItems.BELL_PEPPER_BLUE);
        simpleItem(g, ModItems.BELL_PEPPER_PURPLE);
        simpleItem(g, ModItems.BELL_PEPPER_BLACK);
        simpleItem(g, ModItems.BELL_PEPPER_ROLL_GREEN);
        simpleItem(g, ModItems.BELL_PEPPER_ROLL_YELLOW);
        simpleItem(g, ModItems.BELL_PEPPER_ROLL_RED);
        simpleItem(g, ModItems.BELL_PEPPER_ROLL_ORANGE);
        simpleItem(g, ModItems.BELL_PEPPER_ROLL_WHITE);
        simpleItem(g, ModItems.BELL_PEPPER_ROLL_PINK);
        simpleItem(g, ModItems.BELL_PEPPER_ROLL_BLUE);
        simpleItem(g, ModItems.BELL_PEPPER_ROLL_PURPLE);
        simpleItem(g, ModItems.BELL_PEPPER_ROLL_BLACK);
        simpleItem(g, ModItems.BELL_PEPPER_SLICE_GREEN);
        simpleItem(g, ModItems.BELL_PEPPER_SLICE_YELLOW);
        simpleItem(g, ModItems.BELL_PEPPER_SLICE_RED);
        simpleItem(g, ModItems.BELL_PEPPER_SLICE_ORANGE);
        simpleItem(g, ModItems.BELL_PEPPER_SLICE_WHITE);
        simpleItem(g, ModItems.BELL_PEPPER_SLICE_PINK);
        simpleItem(g, ModItems.BELL_PEPPER_SLICE_BLUE);
        simpleItem(g, ModItems.BELL_PEPPER_SLICE_PURPLE);
        simpleItem(g, ModItems.BELL_PEPPER_SLICE_BLACK);
        simpleItem(g, ModItems.BELL_PEPPER_SOUP);
        simpleItem(g, ModItems.BELL_PEPPER_PASTA);
        simpleItem(g, ModItems.COFFEE);
        simpleItem(g, ModItems.CHOCOLATE_COFFEE);
        simpleItem(g, ModItems.CALAMARI);
        simpleItem(g, ModItems.CALAMARI_ROLL);
        simpleItem(g, ModItems.CALAMARI_SLICE);
        simpleItem(g, ModItems.CALAMARI_SOUP);
        simpleItem(g, ModItems.CHERRY_BLOSSOM_CHEESECAKE);
        simpleItem(g, ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE);
        simpleItem(g, ModItems.CHERRY_BLOSSOM_COOKIE);
        simpleItem(g, ModItems.CHERRY_BLOSSOM_PANCAKE);
        simpleItem(g, ModItems.CHERRY_BLOSSOM_PANCAKES);
        simpleItem(g, ModItems.CHERRY_BLOSSOM_ROLL);
        simpleItem(g, ModItems.CHOCOLATE_PANCAKE);
        simpleItem(g, ModItems.CHOCOLATE_PANCAKES);
        simpleItem(g, ModItems.COFFEE_BRAISED_BEEF);
        simpleItem(g, ModItems.COOKED_CALAMARI);
        simpleItem(g, ModItems.COOKED_CALAMARI_SLICE);
        simpleItem(g, ModItems.COOKING_OIL);
        simpleItem(g, ModItems.COTTON_BOLL);
        simpleItem(g, ModItems.FRIED_CALAMARI);
        simpleItem(g, ModItems.FRIED_CHICKEN);
        simpleItem(g, ModItems.FRIED_MUSHROOMS);
        simpleItem(g, ModItems.FRUIT_BEIGNET);
        simpleItem(g, ModItems.GOLDEN_COFFEE_BEANS);
        simpleItem(g, ModItems.HONEY_PANCAKE);
        simpleItem(g, ModItems.HONEY_PANCAKES);
        simpleItem(g, ModItems.MILK_COFFEE);
        simpleItem(g, ModItems.POTATO_SALAD);
        simpleItem(g, ModItems.POTATO_SLICES);
        simpleItem(g, ModItems.PUMPKIN_PANCAKE);
        simpleItem(g, ModItems.PUMPKIN_PANCAKES);
        simpleItem(g, ModItems.COFFEE_PANCAKE);
        simpleItem(g, ModItems.COFFEE_PANCAKES);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_GREEN);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_YELLOW);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_RED);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_ORANGE);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_WHITE);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_PINK);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_BLUE);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_PURPLE);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_BLACK);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_SLICE_GREEN);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_SLICE_YELLOW);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_SLICE_RED);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_SLICE_ORANGE);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_SLICE_WHITE);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_SLICE_PINK);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_SLICE_BLUE);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_SLICE_PURPLE);
        simpleItem(g, ModItems.ROASTED_BELL_PEPPER_SLICE_BLACK);
        simpleItem(g, ModItems.ROASTED_COFFEE_BEANS);
        simpleItem(g, ModItems.DARK_COFFEE);
        simpleItem(g, ModItems.HONEY_COFFEE);
        simpleItem(g, ModItems.STUFFED_BELL_PEPPER_GREEN);
        simpleItem(g, ModItems.STUFFED_BELL_PEPPER_YELLOW);
        simpleItem(g, ModItems.STUFFED_BELL_PEPPER_RED);
        simpleItem(g, ModItems.STUFFED_BELL_PEPPER_ORANGE);
        simpleItem(g, ModItems.STUFFED_BELL_PEPPER_WHITE);
        simpleItem(g, ModItems.STUFFED_BELL_PEPPER_PINK);
        simpleItem(g, ModItems.STUFFED_BELL_PEPPER_BLUE);
        simpleItem(g, ModItems.STUFFED_BELL_PEPPER_PURPLE);
        simpleItem(g, ModItems.STUFFED_BELL_PEPPER_BLACK);
        simpleItem(g, ModItems.SPRING_ROLLS);
        simpleItem(g, ModItems.VEGETABLE_PANCAKE);
        simpleItem(g, ModItems.VEGETABLE_PANCAKES);
        simpleItem(g, ModItems.PANCAKE);
        simpleItem(g, ModItems.PANCAKES);
        simpleItem(g, ModItems.SYRUP_COFFEE);
        simpleItem(g, ModItems.PUMPKIN_COFFEE);
        simpleItem(g, ModItems.CHERRY_BLOSSOM_COFFEE);
        simpleItem(g, ModItems.SYRUP_COOKIE);
        simpleItem(g, ModItems.COFFEE_COOKIE);
        simpleItem(g, ModItems.SYRUP_SANDWICH);
        simpleItem(g, ModItems.SYRUP_CHEESECAKE);
        simpleItem(g, ModItems.SYRUP_CHEESECAKE_SLICE);
        simpleItem(g, ModItems.COFFEE_CHEESECAKE);
        simpleItem(g, ModItems.COFFEE_CHEESECAKE_SLICE);
        simpleItem(g, ModItems.FRIED_DOUGH);
        simpleItem(g, ModItems.FRIED_DUMPLINGS);
        simpleItem(g, ModItems.FRIED_FISH);
        simpleItem(g, ModItems.SWEET_SALAD);
        simpleItem(g, ModItems.RICE_ROLL_ROYALE);
        simpleItem(g, ModItems.BELL_PEPPER_MEDLEY);
        simpleItem(g, ModItems.PALE_BELL_PEPPER_MEDLEY);
        simpleItem(g, ModItems.DARK_BELL_PEPPER_MEDLEY);
    }

    // A simple item with a model generated from its sprite.
    private static void simpleItem(ItemModelGenerators generator, Item item) {
        generator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }

    // For blocks that appear as a block in-world but as an item in-hand
    private static void simpleBlock2D(ItemModelGenerators generator, Block block) {
        generator.generateFlatItem(block.asItem(), ModelTemplates.FLAT_ITEM);
    }
}

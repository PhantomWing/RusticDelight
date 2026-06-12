package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.Configuration;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.custom.PancakeBlock;
import com.phantomwing.rusticdelight.condition.ConfigBooleanCondition;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tags.CommonTags;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;
import net.neoforged.neoforge.common.crafting.DifferenceIngredient;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;
import vectorwing.farmersdelight.data.recipe.CookingRecipes;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public static final float FOOD_COOKING_EXP = 0.35f;

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        buildCraftingRecipes(output);
        buildCuttingRecipes(output);
        buildCookingRecipes(output);
        buildFarmersDelightOverrideRecipes(output);
    }

    private void buildCraftingRecipes(@NotNull RecipeOutput output) {
        // Per-family conditional outputs: a family's recipes only load while its toggle is enabled.
        RecipeOutput cottonOutput = output.withConditions(new ConfigBooleanCondition(Configuration.ENABLE_COTTON_ID));
        RecipeOutput coffeeOutput = output.withConditions(new ConfigBooleanCondition(Configuration.ENABLE_COFFEE_ID));
        RecipeOutput bellPepperOutput = output.withConditions(new ConfigBooleanCondition(Configuration.ENABLE_BELL_PEPPERS_ID));
        RecipeOutput calamariOutput = output.withConditions(new ConfigBooleanCondition(Configuration.SQUIDS_DROP_CALAMARI_ID));
        RecipeOutput bellPepperAndCalamariOutput = output.withConditions(
                new ConfigBooleanCondition(Configuration.ENABLE_BELL_PEPPERS_ID),
                new ConfigBooleanCondition(Configuration.SQUIDS_DROP_CALAMARI_ID));

        // Bell pepper foods
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_GREEN, ModItems.ROASTED_BELL_PEPPER_GREEN, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_YELLOW, ModItems.ROASTED_BELL_PEPPER_YELLOW, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_RED, ModItems.ROASTED_BELL_PEPPER_RED, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_ORANGE, ModItems.ROASTED_BELL_PEPPER_ORANGE, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_WHITE, ModItems.ROASTED_BELL_PEPPER_WHITE, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_PINK, ModItems.ROASTED_BELL_PEPPER_PINK, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_BLUE, ModItems.ROASTED_BELL_PEPPER_BLUE, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_PURPLE, ModItems.ROASTED_BELL_PEPPER_PURPLE, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_BLACK, ModItems.ROASTED_BELL_PEPPER_BLACK, FOOD_COOKING_EXP);

        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_GREEN, ModItems.ROASTED_BELL_PEPPER_SLICE_GREEN, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_YELLOW, ModItems.ROASTED_BELL_PEPPER_SLICE_YELLOW, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_RED, ModItems.ROASTED_BELL_PEPPER_SLICE_RED, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_ORANGE, ModItems.ROASTED_BELL_PEPPER_SLICE_ORANGE, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_WHITE, ModItems.ROASTED_BELL_PEPPER_SLICE_WHITE, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_PINK, ModItems.ROASTED_BELL_PEPPER_SLICE_PINK, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_BLUE, ModItems.ROASTED_BELL_PEPPER_SLICE_BLUE, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_PURPLE, ModItems.ROASTED_BELL_PEPPER_SLICE_PURPLE, FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_BLACK, ModItems.ROASTED_BELL_PEPPER_SLICE_BLACK, FOOD_COOKING_EXP);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BELL_PEPPER_SOUP, 1)
                .requires(Items.BOWL)
                .requires(CommonTags.FOODS_BELL_PEPPER)
                .requires(CommonTags.FOODS_BELL_PEPPER)
                .requires(CommonTags.FOODS_BELL_PEPPER)
                .requires(CommonTags.FOODS_BELL_PEPPER)
                .requires(CommonTags.FOODS_BELL_PEPPER)
                .requires(CommonTags.FOODS_BELL_PEPPER)
                .unlockedBy(getHasName(ModItems.BELL_PEPPER_RED), has(ModItems.BELL_PEPPER_RED))
                .unlockedBy(getHasName(ModItems.BELL_PEPPER_GREEN), has(ModItems.BELL_PEPPER_GREEN))
                .unlockedBy(getHasName(ModItems.BELL_PEPPER_YELLOW), has(ModItems.BELL_PEPPER_YELLOW))
                .save(bellPepperOutput);

        // Calamari
        foodCookingRecipes(calamariOutput, ModItems.CALAMARI, ModItems.COOKED_CALAMARI, FOOD_COOKING_EXP);
        foodCookingRecipes(calamariOutput, ModItems.CALAMARI_SLICE, ModItems.COOKED_CALAMARI_SLICE, FOOD_COOKING_EXP);

        // Rolls
        simpleSushiRoll(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_GREEN, ModItems.BELL_PEPPER_ROLL_GREEN);
        simpleSushiRoll(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_YELLOW, ModItems.BELL_PEPPER_ROLL_YELLOW);
        simpleSushiRoll(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_RED, ModItems.BELL_PEPPER_ROLL_RED);
        simpleSushiRoll(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_ORANGE, ModItems.BELL_PEPPER_ROLL_ORANGE);
        simpleSushiRoll(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_WHITE, ModItems.BELL_PEPPER_ROLL_WHITE);
        simpleSushiRoll(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_PINK, ModItems.BELL_PEPPER_ROLL_PINK);
        simpleSushiRoll(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_BLUE, ModItems.BELL_PEPPER_ROLL_BLUE);
        simpleSushiRoll(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_PURPLE, ModItems.BELL_PEPPER_ROLL_PURPLE);
        simpleSushiRoll(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_BLACK, ModItems.BELL_PEPPER_ROLL_BLACK);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CALAMARI_ROLL, 2)
                .requires(ModTags.Items.CALAMARI_ROLL_INGREDIENTS)
                .requires(ModTags.Items.CALAMARI_ROLL_INGREDIENTS)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .unlockedBy(getHasName(ModItems.CALAMARI_SLICE), has(ModItems.CALAMARI_SLICE))
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()), has(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()))
                .save(calamariOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_ROLL, 2)
                .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .unlockedBy(getHasName(Items.PINK_PETALS), has(Items.PINK_PETALS))
                .unlockedBy(getHasName(Items.CHERRY_SAPLING), has(Items.CHERRY_SAPLING))
                .unlockedBy(getHasName(Items.CHERRY_LEAVES), has(Items.CHERRY_LEAVES))
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()), has(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()))
                .save(output);

        // Potato (slices gated by the enable_potato_slices toggle)
        RecipeOutput potatoSlicesOutput = output.withConditions(new ConfigBooleanCondition(Configuration.ENABLE_POTATO_SLICES_ID));
        foodCookingRecipes(potatoSlicesOutput, ModItems.POTATO_SLICES, ModItems.BAKED_POTATO_SLICES, FOOD_COOKING_EXP);

        // Salads
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.POTATO_SALAD, 1)
                .requires(Items.BOWL)
                .requires(CommonTags.FOODS_POTATO)
                .requires(CommonTags.FOODS_ONION)
                .requires(CommonTags.DRINKS_MILK)
                .requires(Tags.Items.EGGS)
                .unlockedBy(getHasName(Items.POTATO), has(Items.POTATO))
                .unlockedBy(getHasName(ModItems.POTATO_SLICES), has(ModItems.POTATO_SLICES))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SWEET_SALAD, 1)
                .requires(Items.BOWL)
                .requires(ModTags.Items.SWEET_LIQUIDS)
                .requires(CommonTags.FOODS_LEAFY_GREEN)
                .requires(Tags.Items.FOODS_VEGETABLE)
                .requires(Tags.Items.FOODS_FRUIT)
                .requires(Tags.Items.FOODS_FRUIT)
                .unlockedBy(getHasName(Items.HONEY_BOTTLE), has(Items.HONEY_BOTTLE))
                .unlockedBy(getHasName(ModItems.SYRUP), has(ModItems.SYRUP))
                .save(output);

        // Cookies
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_COOKIE, 8)
                .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(Items.PINK_PETALS), has(Items.PINK_PETALS))
                .unlockedBy(getHasName(Items.CHERRY_SAPLING), has(Items.CHERRY_SAPLING))
                .unlockedBy(getHasName(Items.CHERRY_LEAVES), has(Items.CHERRY_LEAVES))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.COFFEE_COOKIE, 8)
                .requires(ModTags.Items.COFFEE_INGREDIENTS)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(ModItems.ROASTED_COFFEE_BEANS), has(ModItems.ROASTED_COFFEE_BEANS))
                .save(coffeeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SYRUP_COOKIE, 8)
                .requires(ModTags.Items.SYRUP)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(ModItems.SYRUP), has(ModItems.SYRUP))
                .save(output);

        // Pies
        pieRecipes(output, ModItems.SYRUP_CHEESECAKE, ModItems.SYRUP_CHEESECAKE_SLICE, Ingredient.of(ModTags.Items.SYRUP));
        pieRecipes(output, ModItems.CHERRY_BLOSSOM_CHEESECAKE, ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE, Ingredient.of(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS));

        // Pancakes
        pancakeRecipes(output, ModItems.PANCAKES, ModItems.PANCAKE, Ingredient.of(ModTags.Items.SYRUP), Ingredient.of(Items.SUGAR));
        pancakeRecipes(output, ModItems.HONEY_PANCAKES, ModItems.HONEY_PANCAKE, Ingredient.of(Items.HONEY_BOTTLE), Ingredient.of(Items.SWEET_BERRIES), Ingredient.of(Items.SUGAR));
        pancakeRecipes(output, ModItems.CHOCOLATE_PANCAKES, ModItems.CHOCOLATE_PANCAKE, Ingredient.of(CommonTags.DRINKS_MILK), Ingredient.of(Items.COCOA_BEANS));
        pancakeRecipes(output, ModItems.VEGETABLE_PANCAKES, ModItems.VEGETABLE_PANCAKE, Ingredient.of(CommonTags.DRINKS_MILK), vegetablesPatch(), Ingredient.of(CommonTags.FOODS_LEAFY_GREEN));
        pancakeRecipes(output, ModItems.CHERRY_BLOSSOM_PANCAKES, ModItems.CHERRY_BLOSSOM_PANCAKE, Ingredient.of(CommonTags.DRINKS_MILK), Ingredient.of(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS));
        pancakeRecipes(output, ModItems.PUMPKIN_PANCAKES, ModItems.PUMPKIN_PANCAKE, Ingredient.of(ModTags.Items.SYRUP), Ingredient.of(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get()));

        // Cotton
        oneToOne(cottonOutput, RecipeCategory.MISC, ModItems.COTTON_BOLL, Items.STRING, 1);
        horizontalRecipe(cottonOutput, RecipeCategory.MISC, ModItems.COTTON_BOLL, Items.PAPER, 3);
        twoBytwo(cottonOutput, RecipeCategory.MISC, ModItems.COTTON_BOLL, vectorwing.farmersdelight.common.registry.ModItems.CANVAS.get(), 1);
        storageItemRecipes(cottonOutput, RecipeCategory.MISC, ModItems.COTTON_SEEDS, ModItems.COTTON_SEEDS_BAG);
        storageItemRecipes(cottonOutput, RecipeCategory.MISC, ModItems.COTTON_BOLL, ModItems.COTTON_BOLL_CRATE);

        // Bell peppers
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_GREEN, Items.GREEN_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_YELLOW, Items.YELLOW_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_RED, Items.RED_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_ORANGE, Items.ORANGE_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_WHITE, Items.WHITE_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_PINK, Items.PINK_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_BLUE, Items.BLUE_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_PURPLE, Items.PURPLE_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_BLACK, Items.BLACK_DYE, 1);
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SEEDS, ModItems.BELL_PEPPER_SEEDS_BAG);
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.PALE_BELL_PEPPER_SEEDS, ModItems.PALE_BELL_PEPPER_SEEDS_BAG);
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.DARK_BELL_PEPPER_SEEDS, ModItems.DARK_BELL_PEPPER_SEEDS_BAG);
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_GREEN, ModItems.BELL_PEPPER_GREEN_CRATE);
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_YELLOW, ModItems.BELL_PEPPER_YELLOW_CRATE);
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_RED, ModItems.BELL_PEPPER_RED_CRATE);
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_ORANGE, ModItems.BELL_PEPPER_ORANGE_CRATE);
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_WHITE, ModItems.BELL_PEPPER_WHITE_CRATE);
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_PINK, ModItems.BELL_PEPPER_PINK_CRATE);
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_BLUE, ModItems.BELL_PEPPER_BLUE_CRATE);
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_PURPLE, ModItems.BELL_PEPPER_PURPLE_CRATE);
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_BLACK, ModItems.BELL_PEPPER_BLACK_CRATE);
        storageItemRecipes(calamariOutput, RecipeCategory.MISC, ModItems.CALAMARI, ModItems.CALAMARI_CRATE);

        // Bell pepper blocks (3x3 slices <-> block)
        // Bell pepper blocks: only 3x3 slices -> block. The reverse (block -> 9 slices) is cutting-board only.
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_GREEN, ModItems.BELL_PEPPER_GREEN_BLOCK);
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_YELLOW, ModItems.BELL_PEPPER_YELLOW_BLOCK);
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_RED, ModItems.BELL_PEPPER_RED_BLOCK);
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_ORANGE, ModItems.BELL_PEPPER_ORANGE_BLOCK);
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_WHITE, ModItems.BELL_PEPPER_WHITE_BLOCK);
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_PINK, ModItems.BELL_PEPPER_PINK_BLOCK);
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_BLUE, ModItems.BELL_PEPPER_BLUE_BLOCK);
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_PURPLE, ModItems.BELL_PEPPER_PURPLE_BLOCK);
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_BLACK, ModItems.BELL_PEPPER_BLACK_BLOCK);

        // Bell pepper slice -> seeds (1 slice = 1 seed of the matching crop)
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_GREEN, ModItems.BELL_PEPPER_SEEDS, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_YELLOW, ModItems.BELL_PEPPER_SEEDS, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_RED, ModItems.BELL_PEPPER_SEEDS, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_ORANGE, ModItems.PALE_BELL_PEPPER_SEEDS, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_WHITE, ModItems.PALE_BELL_PEPPER_SEEDS, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_PINK, ModItems.PALE_BELL_PEPPER_SEEDS, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_BLUE, ModItems.DARK_BELL_PEPPER_SEEDS, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_PURPLE, ModItems.DARK_BELL_PEPPER_SEEDS, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_BLACK, ModItems.DARK_BELL_PEPPER_SEEDS, 1);

        // Coffee
        storageItemRecipes(coffeeOutput, RecipeCategory.MISC, ModItems.COFFEE_BEANS, ModItems.COFFEE_BEANS_BAG);
        storageItemRecipes(coffeeOutput, RecipeCategory.MISC, ModItems.ROASTED_COFFEE_BEANS, ModItems.ROASTED_COFFEE_BEANS_BAG);

        oneToOne(coffeeOutput, RecipeCategory.MISC, ModItems.COFFEE_BEANS, Items.YELLOW_DYE, 1);
        oneToOne(coffeeOutput, RecipeCategory.MISC, ModItems.ROASTED_COFFEE_BEANS, Items.BROWN_DYE, 1);
        foodCookingRecipes(coffeeOutput, ModItems.COFFEE_BEANS, ModItems.ROASTED_COFFEE_BEANS, FOOD_COOKING_EXP);

        var goldenCoffeeBeansIngredient = Ingredient.of(ModItems.COFFEE_BEANS.get(), ModItems.ROASTED_COFFEE_BEANS.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.GOLDEN_COFFEE_BEANS, 1)
                .pattern("GGG")
                .pattern("GCG")
                .pattern("GGG")
                .define('G', Items.GOLD_NUGGET)
                .define('C', goldenCoffeeBeansIngredient)
                .unlockedBy(getHasName(ModItems.COFFEE_BEANS), has(ModItems.COFFEE_BEANS))
                .unlockedBy(getHasName(ModItems.ROASTED_COFFEE_BEANS), has(ModItems.ROASTED_COFFEE_BEANS))
                .save(coffeeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MILK_COFFEE, 1)
                .requires(ModItems.COFFEE)
                .requires(CommonTags.DRINKS_MILK)
                .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                .save(coffeeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHOCOLATE_COFFEE, 1)
                .requires(ModItems.MILK_COFFEE)
                .requires(Items.COCOA_BEANS)
                .requires(Items.COCOA_BEANS)
                .unlockedBy(getHasName(ModItems.MILK_COFFEE), has(ModItems.MILK_COFFEE))
                .save(coffeeOutput, getRecipeName(ModItems.MILK_COFFEE, ModItems.CHOCOLATE_COFFEE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHOCOLATE_COFFEE, 1)
                .requires(ModItems.COFFEE)
                .requires(CommonTags.DRINKS_MILK)
                .requires(Items.COCOA_BEANS)
                .requires(Items.COCOA_BEANS)
                .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                .save(coffeeOutput, getRecipeName(ModItems.COFFEE, ModItems.CHOCOLATE_COFFEE));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HONEY_COFFEE, 1)
                .requires(ModItems.MILK_COFFEE)
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy(getHasName(ModItems.MILK_COFFEE), has(ModItems.MILK_COFFEE))
                .save(coffeeOutput, getRecipeName(ModItems.MILK_COFFEE, ModItems.HONEY_COFFEE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HONEY_COFFEE, 1)
                .requires(ModItems.COFFEE)
                .requires(CommonTags.DRINKS_MILK)
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                .save(coffeeOutput, getRecipeName(ModItems.COFFEE, ModItems.HONEY_COFFEE));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SYRUP_COFFEE, 1)
                .requires(ModItems.MILK_COFFEE)
                .requires(ModTags.Items.SYRUP)
                .unlockedBy(getHasName(ModItems.MILK_COFFEE), has(ModItems.MILK_COFFEE))
                .save(coffeeOutput, getRecipeName(ModItems.MILK_COFFEE, ModItems.SYRUP_COFFEE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SYRUP_COFFEE, 1)
                .requires(ModItems.COFFEE)
                .requires(CommonTags.DRINKS_MILK)
                .requires(ModTags.Items.SYRUP)
                .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                .save(coffeeOutput, getRecipeName(ModItems.COFFEE, ModItems.SYRUP_COFFEE));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.PUMPKIN_COFFEE, 1)
                .requires(ModItems.MILK_COFFEE)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get())
                .unlockedBy(getHasName(ModItems.MILK_COFFEE), has(ModItems.MILK_COFFEE))
                .save(coffeeOutput, getRecipeName(ModItems.MILK_COFFEE, ModItems.PUMPKIN_COFFEE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.PUMPKIN_COFFEE, 1)
                .requires(ModItems.COFFEE)
                .requires(CommonTags.DRINKS_MILK)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get())
                .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                .save(coffeeOutput, getRecipeName(ModItems.COFFEE, ModItems.PUMPKIN_COFFEE));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_COFFEE, 1)
                .requires(ModItems.MILK_COFFEE)
                .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .unlockedBy(getHasName(ModItems.MILK_COFFEE), has(ModItems.MILK_COFFEE))
                .save(coffeeOutput, getRecipeName(ModItems.MILK_COFFEE, ModItems.CHERRY_BLOSSOM_COFFEE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_COFFEE, 1)
                .requires(ModItems.COFFEE)
                .requires(CommonTags.DRINKS_MILK)
                .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                .save(coffeeOutput, getRecipeName(ModItems.COFFEE, ModItems.CHERRY_BLOSSOM_COFFEE));

        // Syrup-based recipes
        oneToOne(output, RecipeCategory.MISC, ModItems.SYRUP, Items.SUGAR, 3);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SYRUP_SANDWICH, 1)
                .requires(Tags.Items.FOODS_BREAD)
                .requires(ModTags.Items.SYRUP)
                .requires(Items.SUGAR)
                .unlockedBy(getHasName(ModItems.SYRUP), has(ModItems.SYRUP))
                .save(output);

        // Feasts
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RICE_ROLL_ROYALE)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.KELP_ROLL_SLICE.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.KELP_ROLL_SLICE.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.KELP_ROLL_SLICE.get())
                .requires(ModItems.BELL_PEPPER_ROLL_GREEN.get())
                .requires(ModItems.BELL_PEPPER_ROLL_YELLOW.get())
                .requires(ModItems.BELL_PEPPER_ROLL_RED.get())
                .requires(ModItems.CALAMARI_ROLL.get())
                .requires(Items.BOWL)
                .requires(ModItems.CHERRY_BLOSSOM_ROLL.get())
                .unlockedBy("has_rice_roll", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ModItems.BELL_PEPPER_ROLL_GREEN.get(),
                        ModItems.BELL_PEPPER_ROLL_YELLOW.get(),
                        ModItems.BELL_PEPPER_ROLL_RED.get(),
                        ModItems.CALAMARI_ROLL.get(),
                        ModItems.CHERRY_BLOSSOM_ROLL.get(),
                        vectorwing.farmersdelight.common.registry.ModItems.KELP_ROLL_SLICE.get()))
                .save(bellPepperAndCalamariOutput);
    }

    private void buildCuttingRecipes(@NotNull RecipeOutput output) {
        // Per-family conditional outputs: a family's recipes only load while its toggle is enabled.
        RecipeOutput cottonOutput = output.withConditions(new ConfigBooleanCondition(Configuration.ENABLE_COTTON_ID));
        RecipeOutput coffeeOutput = output.withConditions(new ConfigBooleanCondition(Configuration.ENABLE_COFFEE_ID));
        RecipeOutput bellPepperOutput = output.withConditions(new ConfigBooleanCondition(Configuration.ENABLE_BELL_PEPPERS_ID));
        RecipeOutput calamariOutput = output.withConditions(new ConfigBooleanCondition(Configuration.SQUIDS_DROP_CALAMARI_ID));

        // Cotton
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_COTTON), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.COTTON_SEEDS, 1)
                .addResultWithChance(ModItems.COTTON_BOLL, 0.3F)
                .addResultWithChance(Items.WHITE_DYE, 0.1F)
                .build(cottonOutput, cuttingId(ModItems.WILD_COTTON.getId()));

        // Bell pepper
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_BELL_PEPPERS), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BELL_PEPPER_SEEDS, 1)
                .addResultWithChance(ModItems.BELL_PEPPER_RED, 0.3F)
                .addResultWithChance(Items.RED_DYE, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.WILD_BELL_PEPPERS.getId()));

        // Bell pepper slices
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BELL_PEPPER_GREEN), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BELL_PEPPER_SLICE_GREEN, 2)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.BELL_PEPPER_GREEN.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BELL_PEPPER_YELLOW), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BELL_PEPPER_SLICE_YELLOW, 2)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.BELL_PEPPER_YELLOW.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BELL_PEPPER_RED), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BELL_PEPPER_SLICE_RED, 2)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.BELL_PEPPER_RED.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BELL_PEPPER_ORANGE), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BELL_PEPPER_SLICE_ORANGE, 2)
                .addResultWithChance(ModItems.PALE_BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.BELL_PEPPER_ORANGE.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BELL_PEPPER_WHITE), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BELL_PEPPER_SLICE_WHITE, 2)
                .addResultWithChance(ModItems.PALE_BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.BELL_PEPPER_WHITE.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BELL_PEPPER_PINK), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BELL_PEPPER_SLICE_PINK, 2)
                .addResultWithChance(ModItems.PALE_BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.BELL_PEPPER_PINK.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BELL_PEPPER_BLUE), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BELL_PEPPER_SLICE_BLUE, 2)
                .addResultWithChance(ModItems.DARK_BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.BELL_PEPPER_BLUE.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BELL_PEPPER_PURPLE), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BELL_PEPPER_SLICE_PURPLE, 2)
                .addResultWithChance(ModItems.DARK_BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.BELL_PEPPER_PURPLE.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BELL_PEPPER_BLACK), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BELL_PEPPER_SLICE_BLACK, 2)
                .addResultWithChance(ModItems.DARK_BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.BELL_PEPPER_BLACK.getId()));

        // Roasted bell pepper slices
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.ROASTED_BELL_PEPPER_GREEN), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.ROASTED_BELL_PEPPER_SLICE_GREEN, 2)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.ROASTED_BELL_PEPPER_GREEN.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.ROASTED_BELL_PEPPER_YELLOW), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.ROASTED_BELL_PEPPER_SLICE_YELLOW, 2)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.ROASTED_BELL_PEPPER_YELLOW.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.ROASTED_BELL_PEPPER_RED), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.ROASTED_BELL_PEPPER_SLICE_RED, 2)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.ROASTED_BELL_PEPPER_RED.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.ROASTED_BELL_PEPPER_ORANGE), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.ROASTED_BELL_PEPPER_SLICE_ORANGE, 2)
                .addResultWithChance(ModItems.PALE_BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.ROASTED_BELL_PEPPER_ORANGE.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.ROASTED_BELL_PEPPER_WHITE), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.ROASTED_BELL_PEPPER_SLICE_WHITE, 2)
                .addResultWithChance(ModItems.PALE_BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.ROASTED_BELL_PEPPER_WHITE.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.ROASTED_BELL_PEPPER_PINK), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.ROASTED_BELL_PEPPER_SLICE_PINK, 2)
                .addResultWithChance(ModItems.PALE_BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.ROASTED_BELL_PEPPER_PINK.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.ROASTED_BELL_PEPPER_BLUE), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.ROASTED_BELL_PEPPER_SLICE_BLUE, 2)
                .addResultWithChance(ModItems.DARK_BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.ROASTED_BELL_PEPPER_BLUE.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.ROASTED_BELL_PEPPER_PURPLE), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.ROASTED_BELL_PEPPER_SLICE_PURPLE, 2)
                .addResultWithChance(ModItems.DARK_BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.ROASTED_BELL_PEPPER_PURPLE.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.ROASTED_BELL_PEPPER_BLACK), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.ROASTED_BELL_PEPPER_SLICE_BLACK, 2)
                .addResultWithChance(ModItems.DARK_BELL_PEPPER_SEEDS, 0.1F)
                .build(bellPepperOutput, cuttingId(ModItems.ROASTED_BELL_PEPPER_BLACK.getId()));

        // Bell pepper blocks -> 9 slices of the same color
        cuttingBellPepperBlock(bellPepperOutput, ModItems.BELL_PEPPER_GREEN_BLOCK, ModItems.BELL_PEPPER_SLICE_GREEN);
        cuttingBellPepperBlock(bellPepperOutput, ModItems.BELL_PEPPER_YELLOW_BLOCK, ModItems.BELL_PEPPER_SLICE_YELLOW);
        cuttingBellPepperBlock(bellPepperOutput, ModItems.BELL_PEPPER_RED_BLOCK, ModItems.BELL_PEPPER_SLICE_RED);
        cuttingBellPepperBlock(bellPepperOutput, ModItems.BELL_PEPPER_ORANGE_BLOCK, ModItems.BELL_PEPPER_SLICE_ORANGE);
        cuttingBellPepperBlock(bellPepperOutput, ModItems.BELL_PEPPER_WHITE_BLOCK, ModItems.BELL_PEPPER_SLICE_WHITE);
        cuttingBellPepperBlock(bellPepperOutput, ModItems.BELL_PEPPER_PINK_BLOCK, ModItems.BELL_PEPPER_SLICE_PINK);
        cuttingBellPepperBlock(bellPepperOutput, ModItems.BELL_PEPPER_BLUE_BLOCK, ModItems.BELL_PEPPER_SLICE_BLUE);
        cuttingBellPepperBlock(bellPepperOutput, ModItems.BELL_PEPPER_PURPLE_BLOCK, ModItems.BELL_PEPPER_SLICE_PURPLE);
        cuttingBellPepperBlock(bellPepperOutput, ModItems.BELL_PEPPER_BLACK_BLOCK, ModItems.BELL_PEPPER_SLICE_BLACK);

        // Coffee
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_COFFEE), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.COFFEE_BEANS, 1)
                .addResultWithChance(ModItems.COFFEE_BEANS, 0.3F)
                .addResultWithChance(Items.YELLOW_DYE, 0.1F)
                .build(coffeeOutput, cuttingId(ModItems.WILD_COFFEE.getId()));

        // Food (potato slices gated by the enable_potato_slices toggle)
        RecipeOutput potatoSlicesOutput = output.withConditions(new ConfigBooleanCondition(Configuration.ENABLE_POTATO_SLICES_ID));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POTATO), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.POTATO_SLICES, 2)
                .build(potatoSlicesOutput, cuttingId(ModItems.POTATO_SLICES.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BAKED_POTATO), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BAKED_POTATO_SLICES, 2)
                .build(potatoSlicesOutput, cuttingId(ModItems.BAKED_POTATO_SLICES.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CALAMARI), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.CALAMARI_SLICE, 2)
                .addResult(Items.BONE_MEAL)
                .build(calamariOutput, cuttingId(ModItems.CALAMARI_SLICE.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.COOKED_CALAMARI), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.COOKED_CALAMARI_SLICE, 2)
                .addResult(Items.BONE_MEAL)
                .build(calamariOutput, cuttingId(ModItems.COOKED_CALAMARI_SLICE.getId()));

        // Pie
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CHERRY_BLOSSOM_CHEESECAKE), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE, 4)
                .build(output, cuttingId(ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE.getId()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.SYRUP_CHEESECAKE), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.SYRUP_CHEESECAKE_SLICE, 4)
                .build(output, cuttingId(ModItems.SYRUP_CHEESECAKE_SLICE.getId()));

        // Salvaging
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ItemTags.WOOL), Ingredient.of(Tags.Items.TOOLS_SHEAR), Items.STRING, 2)
                .build(output, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "wool"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ItemTags.WOOL_CARPETS), Ingredient.of(Tags.Items.TOOLS_SHEAR), Items.STRING, 1)
                .build(output, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "wool_carpet"));
    }

    private void buildCookingRecipes(@NotNull RecipeOutput output) {
        // Cooking oil and everything fried with it are gated by the enable_fried_foods toggle.
        RecipeOutput friedOutput = output.withConditions(new ConfigBooleanCondition(Configuration.ENABLE_FRIED_FOODS_ID));
        RecipeOutput coffeeOutput = output.withConditions(new ConfigBooleanCondition(Configuration.ENABLE_COFFEE_ID));
        RecipeOutput bellPepperOutput = output.withConditions(new ConfigBooleanCondition(Configuration.ENABLE_BELL_PEPPERS_ID));
        // Fried Calamari needs both Cooking Oil and Calamari.
        RecipeOutput friedAndCalamariOutput = output.withConditions(
                new ConfigBooleanCondition(Configuration.ENABLE_FRIED_FOODS_ID),
                new ConfigBooleanCondition(Configuration.SQUIDS_DROP_CALAMARI_ID));

        // Cooking oil
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COOKING_OIL, 2, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.COTTON_SEEDS, Items.PUMPKIN_SEEDS)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(friedOutput);

        // Batter
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BATTER, 2, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP, Items.BOWL)
                .addIngredient(CommonTags.DRINKS_MILK)
                .addIngredient(Tags.Items.EGGS)
                .addIngredient(Items.WHEAT)
                .addIngredient(Items.WHEAT)
                .unlockedByAnyIngredient(Items.MILK_BUCKET, vectorwing.farmersdelight.common.registry.ModItems.MILK_BOTTLE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output);

        // Syrup
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.SYRUP, 1, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.SYRUP_INGREDIENTS)
                .addIngredient(Items.SUGAR)
                .unlockedByAnyIngredient(Items.APPLE, Items.BEETROOT, Items.SUGAR)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output);

        // Fried Dough
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_DOUGH, 1, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(CommonTags.FOODS_DOUGH)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(friedOutput);

        // Fried Dumplings
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_DUMPLINGS, 2, CookingRecipes.FAST_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.DUMPLINGS.get(), 2)
                .unlockedByAnyIngredient(vectorwing.farmersdelight.common.registry.ModItems.DUMPLINGS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(friedOutput);

        // Spring Rolls
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.SPRING_ROLLS, 2, CookingRecipes.FAST_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(CommonTags.FOODS_DOUGH)
                .addIngredient(CommonTags.FOODS_LEAFY_GREEN)
                .addIngredient(ModTags.Items.SPRING_ROLL_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(friedOutput);

        // Fruit Beignet
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRUIT_BEIGNET, 1, CookingRecipes.FAST_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(CommonTags.FOODS_DOUGH)
                .addIngredient(Tags.Items.FOODS_FRUIT)
                .addIngredient(Items.SUGAR)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(friedOutput);

        // Fried Calamari
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_CALAMARI, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ModItems.BATTER)
                .addIngredient(CommonTags.FOODS_RAW_CALAMARI)
                .addIngredient(CommonTags.FOODS_TOMATO)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(friedAndCalamariOutput);

        // Fried Chicken
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_CHICKEN, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ModItems.BATTER)
                .addIngredient(CommonTags.FOODS_RAW_CHICKEN)
                .addIngredient(CommonTags.FOODS_ONION)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(friedOutput);

        // Fried Mushrooms
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_MUSHROOMS, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(Items.BROWN_MUSHROOM)
                .addIngredient(Items.RED_MUSHROOM)
                .addIngredient(CommonTags.FOODS_ONION)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(friedOutput);

        // Bell Pepper Soup
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BELL_PEPPER_SOUP, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN, ModItems.BELL_PEPPER_YELLOW, ModItems.BELL_PEPPER_RED)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput);

        // Stuffed Bell Peppers
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_GREEN, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_GREEN)
                .addIngredient(CommonTags.CROPS_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_YELLOW, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_YELLOW)
                .addIngredient(CommonTags.CROPS_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_YELLOW)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_RED, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_RED)
                .addIngredient(CommonTags.CROPS_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_RED)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_ORANGE, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_ORANGE)
                .addIngredient(CommonTags.CROPS_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_ORANGE)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_WHITE, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_WHITE)
                .addIngredient(CommonTags.CROPS_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_WHITE)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_PINK, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_PINK)
                .addIngredient(CommonTags.CROPS_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_PINK)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_BLUE, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_BLUE)
                .addIngredient(CommonTags.CROPS_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_BLUE)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_PURPLE, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_PURPLE)
                .addIngredient(CommonTags.CROPS_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_PURPLE)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_BLACK, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_BLACK)
                .addIngredient(CommonTags.CROPS_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_BLACK)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput);

        // Bell Pepper Pasta
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BELL_PEPPER_PASTA, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(CommonTags.FOODS_PASTA)
                .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN, ModItems.BELL_PEPPER_YELLOW, ModItems.BELL_PEPPER_RED)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput);

        // Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COFFEE, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(coffeeOutput);

        // Milk Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.MILK_COFFEE, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(CommonTags.DRINKS_MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(coffeeOutput);

        // Chocolate Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.CHOCOLATE_COFFEE, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(CommonTags.DRINKS_MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(Items.COCOA_BEANS, 2)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(coffeeOutput);

        // Honey Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.HONEY_COFFEE, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(CommonTags.DRINKS_MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(Items.HONEY_BOTTLE, 1)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(coffeeOutput);

        // Syrup Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.SYRUP_COFFEE, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(CommonTags.DRINKS_MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModItems.SYRUP, 1)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(coffeeOutput);

        // Pumpkin Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.PUMPKIN_COFFEE, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(CommonTags.DRINKS_MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get(), 1)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(coffeeOutput);

        // Cherry Blossom Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.CHERRY_BLOSSOM_COFFEE, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(CommonTags.DRINKS_MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(coffeeOutput);

        // Dark Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.DARK_COFFEE, 1, CookingRecipes.SLOW_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(coffeeOutput);

        // Coffee-Braised Beef
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COFFEE_BRAISED_BEEF, 1, CookingRecipes.SLOW_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(CommonTags.FOODS_RAW_BEEF)
                .addIngredient(ModTags.Items.COFFEE_FOOD_INGREDIENTS)
                .addIngredient(CommonTags.FOODS_CARROT)
                .addIngredient(CommonTags.FOODS_POTATO)
                .unlockedByAnyIngredient(ModItems.COFFEE)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(coffeeOutput);
    }

    private void buildFarmersDelightOverrideRecipes(@NotNull RecipeOutput output) {
        // Fried Rice
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.FRIED_RICE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .addIngredient(CompoundIngredient.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(ModTags.Items.COOKING_OIL)))
                .addIngredient(CommonTags.FOODS_CARROT)
                .addIngredient(CommonTags.FOODS_ONION)
                .unlockedByAnyIngredient(vectorwing.farmersdelight.common.registry.ModItems.RICE.get(), Items.EGG, Items.CARROT, vectorwing.farmersdelight.common.registry.ModItems.ONION.get(), ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output);

        // Fried Egg
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.FRIED_EGG.get(), 1, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP)
                .addIngredient(Items.EGG)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .setNamespace(RusticDelight.MOD_ID)
                .save(output, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "cooking/fried_egg_from_cooking_oil"));

        // Baked Cod Stew
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.BAKED_COD_STEW.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(CommonTags.FOODS_RAW_COD)
                .addIngredient(CommonTags.FOODS_POTATO)
                .addIngredient(ModTags.Items.RAW_AND_COOKED_EGGS)
                .addIngredient(CommonTags.FOODS_TOMATO)
                .unlockedByAnyIngredient(Items.COD, Items.POTATO, vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get(), Items.EGG)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output);

        // Beef Stew
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.BEEF_STEW.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(CommonTags.FOODS_RAW_BEEF)
                .addIngredient(CommonTags.FOODS_CARROT)
                .addIngredient(CommonTags.FOODS_POTATO)
                .unlockedByAnyIngredient(Items.BEEF, Items.CARROT, Items.POTATO)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output);

        // Mushroom Rice
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.MUSHROOM_RICE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(Items.BROWN_MUSHROOM)
                .addIngredient(Items.RED_MUSHROOM)
                .addIngredient(CommonTags.CROPS_RICE)
                .addIngredient(CompoundIngredient.of(Ingredient.of(CommonTags.FOODS_CARROT), Ingredient.of(CommonTags.FOODS_POTATO)))
                .unlockedByAnyIngredient(Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM, vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output);

        // Vegetable Soup
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.VEGETABLE_SOUP.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(CommonTags.FOODS_CARROT)
                .addIngredient(CommonTags.FOODS_POTATO)
                .addIngredient(CommonTags.FOODS_BEETROOT)
                .addIngredient(CommonTags.FOODS_LEAFY_GREEN)
                .unlockedByAnyIngredient(Items.CARROT, vectorwing.farmersdelight.common.registry.ModItems.ONION.get(), Items.BEETROOT)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output);
    }

    protected static void oneToOne(RecipeOutput recipeOutput, RecipeCategory category, ItemLike item, ItemLike result, int count) {
        ShapelessRecipeBuilder.shapeless(category, result, count)
                .requires(item)
                .unlockedBy(getHasName(item), has(item))
                .save(recipeOutput, getRecipeName(item, result));
    }

    protected static void horizontalRecipe(RecipeOutput recipeOutput, RecipeCategory category, ItemLike item, ItemLike result, int count) {
        ShapedRecipeBuilder.shaped(category, result, count)
                .pattern("###")
                .define('#', item)
                .unlockedBy(getHasName(item), has(item))
                .save(recipeOutput, getRecipeName(item, result));
    }

    protected static void twoBytwo(RecipeOutput recipeOutput, RecipeCategory category, ItemLike item, ItemLike result, int count) {
        ShapedRecipeBuilder.shaped(category, result, count)
                .pattern("##")
                .pattern("##")
                .define('#', item)
                .unlockedBy(getHasName(item), has(item))
                .save(recipeOutput, getRecipeName(item, result));
    }

    protected static void storageItemRecipes(RecipeOutput recipeOutput, RecipeCategory category, ItemLike item, ItemLike storageItem) {
        // From item to storageItem
        ShapedRecipeBuilder.shaped(category, storageItem)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', item)
                .unlockedBy(getHasName(item), has(item))
                .save(recipeOutput, getRecipeName(item, storageItem));

        // From storageItem to item
        ShapelessRecipeBuilder.shapeless(category, item, 9)
                .requires(storageItem)
                .unlockedBy(getHasName(storageItem), has(storageItem))
                .save(recipeOutput, getRecipeName(storageItem, item));
    }

    // 3x3 of item -> storageItem only (no reverse crafting recipe).
    protected static void compactingRecipe(RecipeOutput recipeOutput, RecipeCategory category, ItemLike item, ItemLike storageItem) {
        ShapedRecipeBuilder.shaped(category, storageItem)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', item)
                .unlockedBy(getHasName(item), has(item))
                .save(recipeOutput, getRecipeName(item, storageItem));
    }

    protected static void foodCookingRecipes(@NotNull RecipeOutput recipeOutput, @NotNull ItemLike material, @NotNull ItemLike result, float experience) {
        foodSmelting(recipeOutput, material, result, experience, 200);
        foodSmoking(recipeOutput, material, result, experience, 100); // Smoking is twice as fast
        foodCampfireCooking(recipeOutput, material, result, experience, 600); // Campfire cooking takes three times longer
    }

    protected static void foodSmelting(@NotNull RecipeOutput recipeOutput, @NotNull ItemLike material, @NotNull ItemLike result, float experience, int cookingTime) {
        SimpleCookingRecipeBuilder
                .generic(Ingredient.of(material), RecipeCategory.FOOD, result, experience, cookingTime, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new)
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    protected static void foodSmoking(@NotNull RecipeOutput recipeOutput, @NotNull ItemLike material, @NotNull ItemLike result, float experience, int cookingTime) {
        SimpleCookingRecipeBuilder
                .generic(Ingredient.of(material), RecipeCategory.FOOD, result, experience, cookingTime, RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new)
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput, RusticDelight.MOD_ID + ":" + getItemName(result) + "_from_smoking");
    }

    protected static void foodCampfireCooking(@NotNull RecipeOutput recipeOutput, @NotNull ItemLike material, @NotNull ItemLike result, float experience, int cookingTime) {
        SimpleCookingRecipeBuilder
                .generic(Ingredient.of(material), RecipeCategory.FOOD, result, experience, cookingTime, RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new)
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput, RusticDelight.MOD_ID + ":" + getItemName(result) + "_from_campfire_cooking");
    }

    protected static void simpleSushiRoll(@NotNull RecipeOutput recipeOutput, @NotNull ItemLike ingredient, @NotNull ItemLike result) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, result, 2)
                .requires(ingredient)
                .requires(ingredient)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()), has(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()))
                .save(recipeOutput);
    }

    protected static void pancakeRecipes(@NotNull RecipeOutput recipeOutput, @NotNull DeferredItem<Item> pancakeBlock, @NotNull DeferredItem<Item> singlePancake, Ingredient topping, Ingredient ingredient) {
        pancakeRecipes(recipeOutput, pancakeBlock, singlePancake, topping, ingredient, ingredient);
    }

    protected static void pancakeRecipes(@NotNull RecipeOutput recipeOutput, @NotNull DeferredItem<Item> pancakeBlock, @NotNull DeferredItem<Item> singlePancake, Ingredient topping, Ingredient ingredient, Ingredient ingredient2) {
        var batter = ModItems.BATTER;
        var servingItem = Items.BOWL;

        // Crafting a pancake block.
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, pancakeBlock, 1)
                .pattern(" T ")
                .pattern("XMX")
                .pattern("YBY")
                .define('T', topping) // Topping
                .define('X', ingredient) // Main ingredient
                .define('Y', ingredient2) // Optional secondary ingredient
                .define('M', batter)
                .define('B', servingItem)
                .unlockedBy(getHasName(batter), has(batter))
                .save(recipeOutput);

        // Cooking a pancake block
        CookingPotRecipeBuilder.cookingPotRecipe(pancakeBlock, 1, CookingRecipes.SLOW_COOKING, CookingRecipes.LARGE_EXP, servingItem)
                .addIngredient(batter)
                .addIngredient(topping)
                .addIngredient(ingredient, 2)
                .addIngredient(ingredient2, 2)
                .unlockedByAnyIngredient(batter)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(recipeOutput);

        // Cutting recipe for pancakes to separate them into single pancakes.
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(pancakeBlock), Ingredient.of(CommonTags.TOOLS_KNIFE), singlePancake, PancakeBlock.MAX_SERVINGS)
                .addResult(servingItem)
                .build(recipeOutput, cuttingId(pancakeBlock.getId()));

        // Split a stack of pancakes into separate pancakes.
        oneToOne(recipeOutput, RecipeCategory.MISC, pancakeBlock, singlePancake.get(), PancakeBlock.MAX_SERVINGS);

        // Combine separate pancakes together into a single stack
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, pancakeBlock)
                .requires(singlePancake, PancakeBlock.MAX_SERVINGS)
                .requires(servingItem) // Pancakes are always placed on a bowl
                .unlockedBy(getHasName(singlePancake), has(singlePancake))
                .save(recipeOutput, getRecipeName(singlePancake, pancakeBlock));
    }

    protected static void pieRecipes(@NotNull RecipeOutput recipeOutput, @NotNull DeferredItem<Item> pieBlock, @NotNull DeferredItem<Item> sliceItem, Ingredient topping) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, pieBlock, 1)
                .pattern("TTT")
                .pattern("MMM")
                .pattern("SCS")
                .define('T', topping)
                .define('M', CommonTags.DRINKS_MILK)
                .define('S', Items.SUGAR)
                .define('C', vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get())
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get()), has(vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, pieBlock, 1)
                .pattern("##")
                .pattern("##")
                .define('#', sliceItem)
                .unlockedBy(getHasName(sliceItem), has(sliceItem))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, getItemName(pieBlock) + "_from_slices"));
    }

    protected static String getRecipeName(ItemLike item, ItemLike result) {
        return RusticDelight.MOD_ID + ":" + getConversionRecipeName(result, item);
    }

    private static ResourceLocation cuttingId(ResourceLocation inputId) {
        return ResourceLocation.fromNamespaceAndPath(inputId.getNamespace(), "cutting/" + inputId.getPath());
    }

    // Cuts a bell pepper block into 9 slices of the same color.
    private static void cuttingBellPepperBlock(RecipeOutput recipeOutput, DeferredItem<Item> block, ItemLike slice) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(block), Ingredient.of(CommonTags.TOOLS_KNIFE), slice, 9)
                .build(recipeOutput, cuttingId(block.getId()));
    }

    private static Ingredient vegetablesPatch() {
        return DifferenceIngredient.of(Ingredient.of(Tags.Items.FOODS_VEGETABLE), Ingredient.of(Items.MELON_SLICE));
    }
}

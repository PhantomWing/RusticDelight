package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tags.ForgeTags;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.common.crafting.DifferenceIngredient;
import net.minecraftforge.common.crafting.PartialNBTIngredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;
import vectorwing.farmersdelight.data.recipe.CookingRecipes;

import java.util.Objects;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public static final float FOOD_COOKING_EXP = 0.35f;

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> output) {
        buildCraftingRecipes(output);
        buildCuttingRecipes(output);
        buildCookingRecipes(output);
    }

    private void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> output) {
        // Bell pepper
        foodCookingRecipes(output, ModItems.BELL_PEPPER_GREEN.get(), ModItems.ROASTED_BELL_PEPPER_GREEN.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(output, ModItems.BELL_PEPPER_YELLOW.get(), ModItems.ROASTED_BELL_PEPPER_YELLOW.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(output, ModItems.BELL_PEPPER_RED.get(), ModItems.ROASTED_BELL_PEPPER_RED.get(), FOOD_COOKING_EXP);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BELL_PEPPER_SOUP.get(), 1)
                .requires(Items.BOWL)
                .requires(ForgeTags.VEGETABLES_BELL_PEPPER)
                .requires(ForgeTags.VEGETABLES_BELL_PEPPER)
                .requires(ForgeTags.VEGETABLES_BELL_PEPPER)
                .requires(ForgeTags.VEGETABLES_BELL_PEPPER)
                .requires(ForgeTags.VEGETABLES_BELL_PEPPER)
                .requires(ForgeTags.VEGETABLES_BELL_PEPPER)
                .unlockedBy(getHasName(ModItems.BELL_PEPPER_RED.get()), has(ModItems.BELL_PEPPER_RED.get()))
                .unlockedBy(getHasName(ModItems.BELL_PEPPER_GREEN.get()), has(ModItems.BELL_PEPPER_GREEN.get()))
                .unlockedBy(getHasName(ModItems.BELL_PEPPER_YELLOW.get()), has(ModItems.BELL_PEPPER_YELLOW.get()))
                .save(output);

        // Calamari
        foodCookingRecipes(output, ModItems.CALAMARI.get(), ModItems.COOKED_CALAMARI.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(output, ModItems.CALAMARI_SLICE.get(), ModItems.COOKED_CALAMARI_SLICE.get(), FOOD_COOKING_EXP);

        // Rolls
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CALAMARI_ROLL.get(), 2)
                .requires(ModTags.Items.CALAMARI_ROLL_INGREDIENTS)
                .requires(ModTags.Items.CALAMARI_ROLL_INGREDIENTS)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .unlockedBy(getHasName(ModItems.CALAMARI_SLICE.get()), has(ModItems.CALAMARI_SLICE.get()))
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()), has(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_ROLL.get(), 2)
                .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .unlockedBy(getHasName(Items.PINK_PETALS), has(Items.PINK_PETALS))
                .unlockedBy(getHasName(Items.CHERRY_SAPLING), has(Items.CHERRY_SAPLING))
                .unlockedBy(getHasName(Items.CHERRY_LEAVES), has(Items.CHERRY_LEAVES))
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()), has(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()))
                .save(output);

        // Potato
        foodCookingRecipes(output, ModItems.POTATO_SLICES.get(), ModItems.BAKED_POTATO_SLICES.get(), FOOD_COOKING_EXP);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.POTATO_SALAD.get(), 1)
                .requires(Items.BOWL)
                .requires(ForgeTags.VEGETABLES_POTATO)
                .requires(ForgeTags.VEGETABLES_ONION)
                .requires(ForgeTags.MILK)
                .requires(Tags.Items.EGGS)
                .unlockedBy(getHasName(Items.POTATO), has(Items.POTATO))
                .unlockedBy(getHasName(ModItems.POTATO_SLICES.get()), has(ModItems.POTATO_SLICES.get()))
                .save(output);

        // Cookies
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_COOKIE.get(), 8)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .unlockedBy(getHasName(Items.PINK_PETALS), has(Items.PINK_PETALS))
                .unlockedBy(getHasName(Items.CHERRY_SAPLING), has(Items.CHERRY_SAPLING))
                .unlockedBy(getHasName(Items.CHERRY_LEAVES), has(Items.CHERRY_LEAVES))
                .save(output);

        // Pies
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_CHEESECAKE.get(), 1)
                .pattern("ccc")
                .pattern("mmm")
                .pattern("sOs")
                .define('c', ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .define('s', Items.SUGAR)
                .define('m', ForgeTags.MILK)
                .define('O', vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get())
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get()), has(vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_CHEESECAKE.get(), 1)
                .pattern("##")
                .pattern("##")
                .define('#', ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE.get())
                .unlockedBy(getHasName(ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE.get()), has(ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE.get()))
                .save(output, new ResourceLocation(RusticDelight.MOD_ID, "cherry_blossom_cheesecake_from_slices"));

        // Pancakes
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.HONEY_PANCAKES.get(), 1)
                .pattern("XHX")
                .pattern("SBS")
                .pattern("XYX")
                .define('S', Items.SWEET_BERRIES)
                .define('H', Items.HONEY_BOTTLE)
                .define('X', Items.SUGAR)
                .define('B', ModItems.BATTER.get())
                .define('Y', Items.BOWL)
                .unlockedBy(getHasName(ModItems.BATTER.get()), has(ModItems.BATTER.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CHOCOLATE_PANCAKES.get(), 1)
                .pattern("XMX")
                .pattern("CBC")
                .pattern("XYX")
                .define('C', Items.COCOA_BEANS)
                .define('M', ForgeTags.MILK)
                .define('X', Items.SUGAR)
                .define('B', ModItems.BATTER.get())
                .define('Y', Items.BOWL)
                .unlockedBy(getHasName(ModItems.BATTER.get()), has(ModItems.BATTER.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_PANCAKES.get(), 1)
                .pattern("XMX")
                .pattern("PBP")
                .pattern("XYX")
                .define('P', ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .define('M', ForgeTags.MILK)
                .define('X', Items.SUGAR)
                .define('B', ModItems.BATTER.get())
                .define('Y', Items.BOWL)
                .unlockedBy(getHasName(ModItems.BATTER.get()), has(ModItems.BATTER.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.VEGETABLE_PANCAKES.get(), 1)
                .pattern("LVL")
                .pattern("VBV")
                .pattern("LYL")
                .define('L', ForgeTags.SALAD_INGREDIENTS)
                .define('V', ForgeTags.VEGETABLES)
                .define('B', ModItems.BATTER.get())
                .define('Y', Items.BOWL)
                .unlockedBy(getHasName(ModItems.BATTER.get()), has(ModItems.BATTER.get()))
                .save(output);

        // Cotton
        oneToOne(output, RecipeCategory.MISC, ModItems.COTTON_BOLL.get(), Items.STRING, 1);
        horizontalRecipe(output, RecipeCategory.MISC, ModItems.COTTON_BOLL.get(), Items.PAPER, 3);
        twoBytwo(output, RecipeCategory.MISC, ModItems.COTTON_BOLL.get(), vectorwing.farmersdelight.common.registry.ModItems.CANVAS.get(), 1);
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.COTTON_SEEDS.get(), ModItems.COTTON_SEEDS_BAG.get());
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.COTTON_BOLL.get(), ModItems.COTTON_BOLL_CRATE.get());

        // Bell peppers
        oneToOne(output, RecipeCategory.MISC, ModItems.BELL_PEPPER_GREEN.get(), Items.GREEN_DYE, 1);
        oneToOne(output, RecipeCategory.MISC, ModItems.BELL_PEPPER_YELLOW.get(), Items.YELLOW_DYE, 1);
        oneToOne(output, RecipeCategory.MISC, ModItems.BELL_PEPPER_RED.get(), Items.RED_DYE, 1);
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.BELL_PEPPER_SEEDS.get(), ModItems.BELL_PEPPER_SEEDS_BAG.get());
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.BELL_PEPPER_GREEN.get(), ModItems.BELL_PEPPER_GREEN_CRATE.get());
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.BELL_PEPPER_YELLOW.get(), ModItems.BELL_PEPPER_YELLOW_CRATE.get());
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.BELL_PEPPER_RED.get(), ModItems.BELL_PEPPER_RED_CRATE.get());

        // Coffee
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.COFFEE_BEANS.get(), ModItems.COFFEE_BEANS_BAG.get());
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.ROASTED_COFFEE_BEANS.get(), ModItems.ROASTED_COFFEE_BEANS_BAG.get());
        oneToOne(output, RecipeCategory.MISC, ModItems.COFFEE_BEANS.get(), Items.YELLOW_DYE, 1);
        oneToOne(output, RecipeCategory.MISC, ModItems.ROASTED_COFFEE_BEANS.get(), Items.BROWN_DYE, 1);
        foodCookingRecipes(output, ModItems.COFFEE_BEANS.get(), ModItems.ROASTED_COFFEE_BEANS.get(), FOOD_COOKING_EXP);

        var goldenCoffeeBeansIngredient = Ingredient.of(ModItems.COFFEE_BEANS.get(), ModItems.ROASTED_COFFEE_BEANS.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.GOLDEN_COFFEE_BEANS.get(), 1)
                .pattern("GGG")
                .pattern("GCG")
                .pattern("GGG")
                .define('G', Items.GOLD_NUGGET)
                .define('C', goldenCoffeeBeansIngredient)
                .unlockedBy(getHasName(ModItems.COFFEE_BEANS.get()), has(ModItems.COFFEE_BEANS.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MILK_COFFEE.get(), 1)
                .requires(ModItems.COFFEE.get())
                .requires(ForgeTags.MILK)
                .unlockedBy(getHasName(ModItems.COFFEE.get()), has(ModItems.COFFEE.get()))
                .save(output, getRecipeName(ModItems.COFFEE.get(), ModItems.MILK_COFFEE.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHOCOLATE_COFFEE.get(), 1)
                .requires(ModItems.MILK_COFFEE.get())
                .requires(Items.COCOA_BEANS)
                .requires(Items.COCOA_BEANS)
                .unlockedBy(getHasName(ModItems.MILK_COFFEE.get()), has(ModItems.MILK_COFFEE.get()))
                .save(output, getRecipeName(ModItems.MILK_COFFEE.get(), ModItems.CHOCOLATE_COFFEE.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHOCOLATE_COFFEE.get(), 1)
                .requires(ModItems.COFFEE.get())
                .requires(ForgeTags.MILK)
                .requires(Items.COCOA_BEANS)
                .requires(Items.COCOA_BEANS)
                .unlockedBy(getHasName(ModItems.COFFEE.get()), has(ModItems.COFFEE.get()))
                .save(output, getRecipeName(ModItems.COFFEE.get(), ModItems.CHOCOLATE_COFFEE.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HONEY_COFFEE.get(), 1)
                .requires(ModItems.MILK_COFFEE.get())
                .requires(Items.HONEY_BOTTLE)
                .requires(Items.SUGAR)
                .unlockedBy(getHasName(ModItems.MILK_COFFEE.get()), has(ModItems.MILK_COFFEE.get()))
                .save(output, getRecipeName(ModItems.MILK_COFFEE.get(), ModItems.HONEY_COFFEE.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HONEY_COFFEE.get(), 1)
                .requires(ModItems.COFFEE.get())
                .requires(ForgeTags.MILK)
                .requires(Items.HONEY_BOTTLE)
                .requires(Items.SUGAR)
                .unlockedBy(getHasName(ModItems.COFFEE.get()), has(ModItems.COFFEE.get()))
                .save(output, getRecipeName(ModItems.COFFEE.get(), ModItems.HONEY_COFFEE.get()));
    }

    private void buildCuttingRecipes(@NotNull Consumer<FinishedRecipe> output) {
        // Cotton
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_COTTON.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.COTTON_SEEDS.get(), 1)
                .addResultWithChance(ModItems.COTTON_BOLL.get(), 0.3F)
                .addResultWithChance(Items.WHITE_DYE, 0.1F)
                .build(output, getCuttingPath(ModItems.WILD_COTTON.get()));

        // Bell pepper
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_BELL_PEPPERS.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.BELL_PEPPER_SEEDS.get(), 1)
                .addResultWithChance(ModItems.BELL_PEPPER_RED.get(), 0.3F)
                .addResultWithChance(Items.RED_DYE, 0.1F)
                .build(output, getCuttingPath(ModItems.WILD_BELL_PEPPERS.get()));

        // Coffee
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_COFFEE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.COFFEE_BEANS.get(), 1)
                .addResultWithChance(ModItems.COFFEE_BEANS.get(), 0.3F)
                .addResultWithChance(Items.YELLOW_DYE, 0.1F)
                .build(output, ModItems.WILD_COFFEE.getId());

        // Food
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POTATO), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.POTATO_SLICES.get(), 2)
                .build(output, getCuttingPath(ModItems.POTATO_SLICES.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BAKED_POTATO), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.BAKED_POTATO_SLICES.get(), 2)
                .build(output, getCuttingPath(ModItems.BAKED_POTATO_SLICES.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CALAMARI.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.CALAMARI_SLICE.get(), 2)
                .addResult(Items.BONE_MEAL)
                .build(output, getCuttingPath(ModItems.CALAMARI_SLICE.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.COOKED_CALAMARI.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.COOKED_CALAMARI_SLICE.get(), 2)
                .addResult(Items.BONE_MEAL)
                .build(output, getCuttingPath(ModItems.COOKED_CALAMARI_SLICE.get()));

        // Pie
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CHERRY_BLOSSOM_CHEESECAKE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE.get(), 4)
                .build(output, ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE.getId());

        // Salvaging
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ItemTags.WOOL), Ingredient.of(Tags.Items.SHEARS), Items.STRING, 2)
                .build(output, getCuttingPath("wool"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ItemTags.WOOL_CARPETS), Ingredient.of(Tags.Items.SHEARS), Items.STRING, 1)
                .build(output, getCuttingPath("wool_carpet"));
    }

    private void buildCookingRecipes(@NotNull Consumer<FinishedRecipe> output) {
        // Cooking oil
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COOKING_OIL.get(), 2, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.COTTON_SEEDS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(output, getCookingPath(ModItems.COOKING_OIL.get()));

        // Batter
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BATTER.get(), 2, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP, Items.BOWL)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(Tags.Items.EGGS)
                .addIngredient(Items.WHEAT)
                .addIngredient(Items.WHEAT)
                .unlockedByAnyIngredient(Items.MILK_BUCKET, vectorwing.farmersdelight.common.registry.ModItems.MILK_BOTTLE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(output, getCookingPath(ModItems.BATTER.get()));

        // Spring Rolls
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.SPRING_ROLLS.get(), 2, CookingRecipes.FAST_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ForgeTags.DOUGH)
                .addIngredient(ForgeTags.SALAD_INGREDIENTS)
                .addIngredient(vectorwing.farmersdelight.common.tag.ModTags.CABBAGE_ROLL_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(output, getCookingPath(ModItems.SPRING_ROLLS.get()));

        // Fruit Beignet
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRUIT_BEIGNET.get(), 1, CookingRecipes.FAST_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ForgeTags.DOUGH)
                .addIngredient(ForgeTags.BERRIES)
                .addIngredient(Items.SUGAR)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(output, getCookingPath(ModItems.FRUIT_BEIGNET.get()));

        // Fried Calamari
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_CALAMARI.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ModItems.BATTER.get())
                .addIngredient(ForgeTags.RAW_FISHES_CALAMARI)
                .addIngredient(ForgeTags.VEGETABLES_TOMATO)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(output, getCookingPath(ModItems.FRIED_CALAMARI.get()));

        // Fried Chicken
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_CHICKEN.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ModItems.BATTER.get())
                .addIngredient(ForgeTags.RAW_CHICKEN)
                .addIngredient(ForgeTags.VEGETABLES_ONION)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(output, getCookingPath(ModItems.FRIED_CHICKEN.get()));

        // Fried Mushrooms
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_MUSHROOMS.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(Items.BROWN_MUSHROOM)
                .addIngredient(Items.RED_MUSHROOM)
                .addIngredient(ForgeTags.VEGETABLES_ONION)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(output, getCookingPath(ModItems.FRIED_MUSHROOMS.get()));

        // Fried Rice (Override)
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.FRIED_RICE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .addIngredient(CompoundIngredient.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(ModTags.Items.COOKING_OIL)))
                .addIngredient(ForgeTags.VEGETABLES_CARROT)
                .addIngredient(ForgeTags.VEGETABLES_ONION)
                .unlockedByAnyIngredient(vectorwing.farmersdelight.common.registry.ModItems.RICE.get(), Items.EGG, Items.CARROT, vectorwing.farmersdelight.common.registry.ModItems.ONION.get(), ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(output);

        // Fried Egg (Alternative)
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.FRIED_EGG.get(), 1, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP)
                .addIngredient(Items.EGG)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(output, getRecipeName(ModItems.COOKING_OIL.get(), vectorwing.farmersdelight.common.registry.ModItems.FRIED_EGG.get()));

        // Bell Pepper Soup
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BELL_PEPPER_SOUP.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN.get(), ModItems.BELL_PEPPER_YELLOW.get(), ModItems.BELL_PEPPER_RED.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(output, getCookingPath(ModItems.BELL_PEPPER_SOUP.get()));

        // Stuffed Bell Peppers
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_GREEN.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_GREEN.get())
                .addIngredient(ForgeTags.GRAIN_RICE)
                .addIngredient(stuffedBellPepperFilling())
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(output, getCookingPath(ModItems.STUFFED_BELL_PEPPER_GREEN.get()));
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_YELLOW.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_YELLOW.get())
                .addIngredient(ForgeTags.GRAIN_RICE)
                .addIngredient(stuffedBellPepperFilling())
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_YELLOW.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(output, getCookingPath(ModItems.STUFFED_BELL_PEPPER_YELLOW.get()));
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_RED.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_RED.get())
                .addIngredient(ForgeTags.GRAIN_RICE)
                .addIngredient(stuffedBellPepperFilling())
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_RED.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(output, getCookingPath(ModItems.STUFFED_BELL_PEPPER_RED.get()));

        // Bell Pepper Pasta
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BELL_PEPPER_PASTA.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ForgeTags.PASTA)
                .addIngredient(ModItems.BELL_PEPPER_GREEN.get())
                .addIngredient(ModItems.BELL_PEPPER_YELLOW.get())
                .addIngredient(ModItems.BELL_PEPPER_RED.get())
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN.get(), ModItems.BELL_PEPPER_YELLOW.get(), ModItems.BELL_PEPPER_RED.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(output, getCookingPath(ModItems.BELL_PEPPER_PASTA.get()));

        // Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(waterIngredient())
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .build(output, ModItems.COFFEE.getId());

        // Milk Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.MILK_COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .build(output, ModItems.MILK_COFFEE.getId());

        // Chocolate Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.CHOCOLATE_COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(Items.COCOA_BEANS, 2)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .build(output, ModItems.CHOCOLATE_COFFEE.getId());

        // Honey Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.HONEY_COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(Items.HONEY_BOTTLE, 1)
                .addIngredient(Items.SUGAR, 1)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .build(output, ModItems.HONEY_COFFEE.getId());

        // Dark Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.DARK_COFFEE.get(), 1, CookingRecipes.SLOW_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(waterIngredient())
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .build(output, ModItems.DARK_COFFEE.getId());

        // Coffee-Braised Beef
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COFFEE_BRAISED_BEEF.get(), 1, CookingRecipes.SLOW_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ForgeTags.RAW_BEEF)
                .addIngredient(ModTags.Items.COFFEE_FOOD_INGREDIENTS)
                .addIngredient(ForgeTags.VEGETABLES_CARROT)
                .addIngredient(ForgeTags.VEGETABLES_POTATO)
                .unlockedByAnyIngredient(ModItems.COFFEE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(output, ModItems.COFFEE_BRAISED_BEEF.getId());
    }

    protected static void oneToOne(Consumer<FinishedRecipe> recipeOutput, RecipeCategory category, ItemLike item, ItemLike result, int count) {
        ShapelessRecipeBuilder.shapeless(category, result, count)
                .requires(item)
                .unlockedBy(getHasName(item), has(item))
                .save(recipeOutput, getRecipeName(item, result));
    }

    protected static void horizontalRecipe(Consumer<FinishedRecipe> recipeOutput, RecipeCategory category, ItemLike item, ItemLike result, int count) {
        ShapedRecipeBuilder.shaped(category, result, count)
                .pattern("###")
                .define('#', item)
                .unlockedBy(getHasName(item), has(item))
                .save(recipeOutput, getRecipeName(item, result));
    }

    protected static void twoBytwo(Consumer<FinishedRecipe> recipeOutput, RecipeCategory category, ItemLike item, ItemLike result, int count) {
        ShapedRecipeBuilder.shaped(category, result, count)
                .pattern("##")
                .pattern("##")
                .define('#', item)
                .unlockedBy(getHasName(item), has(item))
                .save(recipeOutput, getRecipeName(item, result));
    }

    protected static void storageItemRecipes(Consumer<FinishedRecipe> recipeOutput, RecipeCategory category, ItemLike item, ItemLike storageItem) {
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

    protected static void foodCookingRecipes(@NotNull Consumer<FinishedRecipe> recipeOutput, @NotNull ItemLike material, @NotNull ItemLike result, float experience) {
        foodSmelting(recipeOutput, material, result, experience);
        foodSmoking(recipeOutput, material, result, experience); // Smoking is twice as fast
        foodCampfireCooking(recipeOutput, material, result, experience); // Campfire cooking takes three times longer
    }

    protected static void foodSmelting(@NotNull Consumer<FinishedRecipe> recipeOutput, @NotNull ItemLike material, @NotNull ItemLike result, float experience) {
        SimpleCookingRecipeBuilder
                .generic(Ingredient.of(material), RecipeCategory.FOOD, result, experience, 200, RecipeSerializer.SMELTING_RECIPE)
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    protected static void foodSmoking(@NotNull Consumer<FinishedRecipe> recipeOutput, @NotNull ItemLike material, @NotNull ItemLike result, float experience) {
        SimpleCookingRecipeBuilder
                .generic(Ingredient.of(material), RecipeCategory.FOOD, result, experience, 100, RecipeSerializer.SMOKING_RECIPE)
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput, RusticDelight.MOD_ID + ":" + getItemName(result) + "_from_smoking");
    }

    protected static void foodCampfireCooking(@NotNull Consumer<FinishedRecipe> recipeOutput, @NotNull ItemLike material, @NotNull ItemLike result, float experience) {
        SimpleCookingRecipeBuilder
                .generic(Ingredient.of(material), RecipeCategory.FOOD, result, experience, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE)
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput, RusticDelight.MOD_ID + ":" + getItemName(result) + "_from_campfire_cooking");
    }

    protected static String getRecipeName(ItemLike from, ItemLike to) {
        return RusticDelight.MOD_ID + ":" + getConversionRecipeName(to, from);
    }

    protected static String getCookingPath(Item item) {
        return RusticDelight.MOD_ID + ":cooking/" + ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    protected static String getCuttingPath(Item item) {
        return RusticDelight.MOD_ID + ":cutting/" + ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    protected static String getCuttingPath(String itemName) {
        return RusticDelight.MOD_ID + ":cutting/" + itemName;
    }

    private static Ingredient stuffedBellPepperFilling() {
        return DifferenceIngredient.of(Ingredient.of(vectorwing.farmersdelight.common.tag.ModTags.CABBAGE_ROLL_INGREDIENTS), Ingredient.of(ForgeTags.VEGETABLES_BELL_PEPPER));
    }

    private static Ingredient waterIngredient() {
        CompoundTag waterPotionTag = new CompoundTag();
        waterPotionTag.putString(PotionUtils.TAG_POTION, Objects.requireNonNull(ForgeRegistries.POTIONS.getKey(Potions.WATER)).toString());
        Ingredient waterBottleIngredient = PartialNBTIngredient.of(waterPotionTag, Items.POTION);

        return CompoundIngredient.of(waterBottleIngredient, Ingredient.of(Items.WATER_BUCKET));
    }
}
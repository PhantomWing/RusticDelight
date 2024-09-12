package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tags.CommonTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;
import vectorwing.farmersdelight.data.recipe.CookingRecipes;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        buildCraftingRecipes(output);
        buildCuttingRecipes(output);
        buildCookingRecipes(output);
    }

    private void buildCraftingRecipes(@NotNull RecipeOutput output) {
        // Calamari
        foodCookingRecipes(output, ModItems.CALAMARI.get(), ModItems.COOKED_CALAMARI.get(), 0.35f, 200);
        foodCookingRecipes(output, ModItems.CALAMARI_SLICE.get(), ModItems.COOKED_CALAMARI_SLICE.get(), 0.35f, 200);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CALAMARI_ROLL.get(), 2)
                .requires(ModItems.CALAMARI_SLICE.get())
                .requires(ModItems.CALAMARI_SLICE.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .unlockedBy(getHasName(ModItems.CALAMARI_ROLL.get()), has(ModItems.CALAMARI_ROLL.get()))
                .save(output);

        // Potato
        foodCookingRecipes(output, ModItems.POTATO_SLICES.get(), ModItems.BAKED_POTATO_SLICES.get(), 0.35f, 200);

        // Cotton
        oneToOne(output, RecipeCategory.MISC, ModItems.COTTON_BOLL.get(), Items.STRING, 1);
        horizontalRecipe(output, RecipeCategory.MISC, ModItems.COTTON_BOLL.get(), Items.PAPER, 3);
        twoBytwo(output, RecipeCategory.MISC, ModItems.COTTON_BOLL.get(), vectorwing.farmersdelight.common.registry.ModItems.CANVAS.get(), 1);
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.COTTON_SEEDS.get(), ModItems.COTTON_SEEDS_BAG.get());
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.COTTON_BOLL.get(), ModItems.COTTON_BOLL_CRATE.get());
    }

    private void buildCuttingRecipes(@NotNull RecipeOutput output) {
        // Cotton
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_COTTON.get()), Ingredient.of(vectorwing.farmersdelight.common.tag.CommonTags.TOOLS_KNIFE), ModItems.COTTON_SEEDS.get(), 1)
                .addResultWithChance(ModItems.COTTON_BOLL.get(), 0.3F)
                .addResultWithChance(Items.WHITE_DYE, 0.1F)
                .build(output, ModItems.WILD_COTTON.getId());

        // Wool
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ItemTags.WOOL), Ingredient.of(vectorwing.farmersdelight.common.tag.CommonTags.TOOLS_KNIFE), Items.STRING, 2)
                .build(output, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "wool"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ItemTags.WOOL_CARPETS), Ingredient.of(vectorwing.farmersdelight.common.tag.CommonTags.TOOLS_KNIFE), Items.STRING, 1)
                .build(output, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "wool_carpet"));

        // Food
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POTATO), Ingredient.of(vectorwing.farmersdelight.common.tag.CommonTags.TOOLS_KNIFE), ModItems.POTATO_SLICES.get(), 1)
                .build(output, ModItems.POTATO_SLICES.getId());
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CALAMARI.get()), Ingredient.of(vectorwing.farmersdelight.common.tag.CommonTags.TOOLS_KNIFE), ModItems.CALAMARI_SLICE.get(), 2)
                .addResult(Items.BONE_MEAL)
                .build(output, ModItems.CALAMARI_SLICE.getId());
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.COOKED_CALAMARI.get()), Ingredient.of(vectorwing.farmersdelight.common.tag.CommonTags.TOOLS_KNIFE), ModItems.COOKED_CALAMARI_SLICE.get(), 2)
                .addResult(Items.BONE_MEAL)
                .build(output, ModItems.COOKED_CALAMARI_SLICE.getId());
    }

    private void buildCookingRecipes(@NotNull RecipeOutput output) {
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COOKING_OIL.get(), 2, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP, Items.GLASS_BOTTLE)
                .addIngredient(CommonTags.COOKING_OIL_INGREDIENTS)
                .addIngredient(CommonTags.COOKING_OIL_INGREDIENTS)
                .addIngredient(CommonTags.COOKING_OIL_INGREDIENTS)
                .addIngredient(CommonTags.COOKING_OIL_INGREDIENTS)
                .addIngredient(CommonTags.COOKING_OIL_INGREDIENTS)
                .addIngredient(CommonTags.COOKING_OIL_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.COTTON_SEEDS)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, ModItems.COOKING_OIL.getId());
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

    protected static void foodCookingRecipes(@NotNull RecipeOutput recipeOutput, @NotNull ItemLike material, @NotNull ItemLike result, float experience, int cookingTime) {
        foodSmelting(recipeOutput, material, result, experience, cookingTime);
        foodSmoking(recipeOutput, material, result, experience, cookingTime / 2); // Smoking is twice as fast
        foodCampfireCooking(recipeOutput, material, result, experience, cookingTime * 3); // Campfire cooking takes three times longer
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

    protected static String getRecipeName(ItemLike item, ItemLike result) {
        return RusticDelight.MOD_ID + ":" + getConversionRecipeName(result, item);
    }
}

package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.custom.PancakeBlock;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tags.CommonTags;
import com.phantomwing.rusticdelight.tags.ModTags;
import com.phantomwing.rusticdelight.util.CookingPotRecipeBuilder;
import com.phantomwing.rusticdelight.util.CuttingBoardRecipeBuilder;
import com.phantomwing.rusticdelight.util.ItemUtils;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
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
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public static final int FAST_COOKING = 100;
    public static final int NORMAL_COOKING = 200;
    public static final int SLOW_COOKING = 400;
    public static final float SMALL_EXP = 0.35F;
    public static final float MEDIUM_EXP = 1.0F;
    public static final float LARGE_EXP = 2.0F;

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    public void buildRecipes(@NotNull RecipeOutput output) {
        buildCraftingRecipes(output);
        buildCuttingRecipes(output);
        buildCookingRecipes(output);
        buildFarmersDelightOverrideRecipes(output);
    }

    private void buildCraftingRecipes(@NotNull RecipeOutput output) {
        // Bell pepper foods
        foodCookingRecipes(output, ModItems.BELL_PEPPER_GREEN, ModItems.ROASTED_BELL_PEPPER_GREEN, SMALL_EXP);
        foodCookingRecipes(output, ModItems.BELL_PEPPER_YELLOW, ModItems.ROASTED_BELL_PEPPER_YELLOW, SMALL_EXP);
        foodCookingRecipes(output, ModItems.BELL_PEPPER_RED, ModItems.ROASTED_BELL_PEPPER_RED, SMALL_EXP);

        foodCookingRecipes(output, ModItems.BELL_PEPPER_SLICE_GREEN, ModItems.ROASTED_BELL_PEPPER_SLICE_GREEN, SMALL_EXP);
        foodCookingRecipes(output, ModItems.BELL_PEPPER_SLICE_YELLOW, ModItems.ROASTED_BELL_PEPPER_SLICE_YELLOW, SMALL_EXP);
        foodCookingRecipes(output, ModItems.BELL_PEPPER_SLICE_RED, ModItems.ROASTED_BELL_PEPPER_SLICE_RED, SMALL_EXP);

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
                .save(output);

        // Calamari
        foodCookingRecipes(output, ModItems.CALAMARI, ModItems.COOKED_CALAMARI, SMALL_EXP);
        foodCookingRecipes(output, ModItems.CALAMARI_SLICE, ModItems.COOKED_CALAMARI_SLICE, SMALL_EXP);

        // Rolls
        simpleSushiRoll(output, ModItems.BELL_PEPPER_SLICE_GREEN, ModItems.BELL_PEPPER_ROLL_GREEN);
        simpleSushiRoll(output, ModItems.BELL_PEPPER_SLICE_YELLOW, ModItems.BELL_PEPPER_ROLL_YELLOW);
        simpleSushiRoll(output, ModItems.BELL_PEPPER_SLICE_RED, ModItems.BELL_PEPPER_ROLL_RED);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CALAMARI_ROLL, 2)
                .requires(ModTags.Items.CALAMARI_ROLL_INGREDIENTS)
                .requires(ModTags.Items.CALAMARI_ROLL_INGREDIENTS)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .unlockedBy(getHasName(ModItems.CALAMARI_SLICE), has(ModItems.CALAMARI_SLICE))
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()), has(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_ROLL, 2)
                .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .unlockedBy(getHasName(Items.PINK_PETALS), has(Items.PINK_PETALS))
                .unlockedBy(getHasName(Items.CHERRY_SAPLING), has(Items.CHERRY_SAPLING))
                .unlockedBy(getHasName(Items.CHERRY_LEAVES), has(Items.CHERRY_LEAVES))
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()), has(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()))
                .save(output);

        // Potato
        foodCookingRecipes(output, ModItems.POTATO_SLICES, ModItems.BAKED_POTATO_SLICES, SMALL_EXP);

        // Salads
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.POTATO_SALAD, 1)
                .requires(Items.BOWL)
                .requires(CommonTags.FOODS_POTATO)
                .requires(CommonTags.FOODS_ONION)
                .requires(CommonTags.FOODS_MILK)
                .requires(ConventionalItemTags.EGGS)
                .unlockedBy(getHasName(Items.POTATO), has(Items.POTATO))
                .unlockedBy(getHasName(ModItems.POTATO_SLICES), has(ModItems.POTATO_SLICES))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SWEET_SALAD, 1)
                .requires(Items.BOWL)
                .requires(ModTags.Items.SWEET_LIQUIDS)
                .requires(CommonTags.FOODS_LEAFY_GREEN)
                .requires(ConventionalItemTags.VEGETABLE_FOODS)
                .requires(ModTags.Items.FRUITS_AND_BERRIES)
                .requires(ModTags.Items.FRUITS_AND_BERRIES)
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
                .save(output);
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
        pancakeRecipes(output, ModItems.CHOCOLATE_PANCAKES, ModItems.CHOCOLATE_PANCAKE, Ingredient.of(CommonTags.FOODS_MILK), Ingredient.of(Items.COCOA_BEANS));
        pancakeRecipes(output, ModItems.VEGETABLE_PANCAKES, ModItems.VEGETABLE_PANCAKE, Ingredient.of(CommonTags.FOODS_MILK), Ingredient.of(ConventionalItemTags.VEGETABLE_FOODS), Ingredient.of(CommonTags.FOODS_LEAFY_GREEN));
        pancakeRecipes(output, ModItems.CHERRY_BLOSSOM_PANCAKES, ModItems.CHERRY_BLOSSOM_PANCAKE, Ingredient.of(CommonTags.FOODS_MILK), Ingredient.of(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS));
        pancakeRecipes(output, ModItems.PUMPKIN_PANCAKES, ModItems.PUMPKIN_PANCAKE, Ingredient.of(ModTags.Items.SYRUP), Ingredient.of(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get()));

        // Cotton
        oneToOne(output, RecipeCategory.MISC, ModItems.COTTON_BOLL, Items.STRING, 1);
        horizontalRecipe(output, RecipeCategory.MISC, ModItems.COTTON_BOLL, Items.PAPER, 3);
        twoBytwo(output, RecipeCategory.MISC, ModItems.COTTON_BOLL, vectorwing.farmersdelight.common.registry.ModItems.CANVAS.get(), 1);
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.COTTON_SEEDS, ModItems.COTTON_SEEDS_BAG);
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.COTTON_BOLL, ModItems.COTTON_BOLL_CRATE);

        // Bell peppers
        oneToOne(output, RecipeCategory.MISC, ModItems.BELL_PEPPER_GREEN, Items.GREEN_DYE, 1);
        oneToOne(output, RecipeCategory.MISC, ModItems.BELL_PEPPER_YELLOW, Items.YELLOW_DYE, 1);
        oneToOne(output, RecipeCategory.MISC, ModItems.BELL_PEPPER_RED, Items.RED_DYE, 1);
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.BELL_PEPPER_SEEDS, ModItems.BELL_PEPPER_SEEDS_BAG);
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.BELL_PEPPER_GREEN, ModItems.BELL_PEPPER_GREEN_CRATE);
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.BELL_PEPPER_YELLOW, ModItems.BELL_PEPPER_YELLOW_CRATE);
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.BELL_PEPPER_RED, ModItems.BELL_PEPPER_RED_CRATE);

        // Coffee
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.COFFEE_BEANS, ModItems.COFFEE_BEANS_BAG);
        storageItemRecipes(output, RecipeCategory.MISC, ModItems.ROASTED_COFFEE_BEANS, ModItems.ROASTED_COFFEE_BEANS_BAG);

        oneToOne(output, RecipeCategory.MISC, ModItems.COFFEE_BEANS, Items.YELLOW_DYE, 1);
        oneToOne(output, RecipeCategory.MISC, ModItems.ROASTED_COFFEE_BEANS, Items.BROWN_DYE, 1);
        foodCookingRecipes(output, ModItems.COFFEE_BEANS, ModItems.ROASTED_COFFEE_BEANS, SMALL_EXP);

        var goldenCoffeeBeansIngredient = Ingredient.of(ModItems.COFFEE_BEANS, ModItems.ROASTED_COFFEE_BEANS);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.GOLDEN_COFFEE_BEANS, 1)
                .pattern("GGG")
                .pattern("GCG")
                .pattern("GGG")
                .define('G', Items.GOLD_NUGGET)
                .define('C', goldenCoffeeBeansIngredient)
                .unlockedBy(getHasName(ModItems.COFFEE_BEANS), has(ModItems.COFFEE_BEANS))
                .unlockedBy(getHasName(ModItems.ROASTED_COFFEE_BEANS), has(ModItems.ROASTED_COFFEE_BEANS))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MILK_COFFEE, 1)
                .requires(ModItems.COFFEE)
                .requires(CommonTags.FOODS_MILK)
                .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHOCOLATE_COFFEE, 1)
                .requires(ModItems.MILK_COFFEE)
                .requires(Items.COCOA_BEANS)
                .requires(Items.COCOA_BEANS)
                .unlockedBy(getHasName(ModItems.MILK_COFFEE), has(ModItems.MILK_COFFEE))
                .save(output, getRecipeName(ModItems.MILK_COFFEE, ModItems.CHOCOLATE_COFFEE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHOCOLATE_COFFEE, 1)
                .requires(ModItems.COFFEE)
                .requires(CommonTags.FOODS_MILK)
                .requires(Items.COCOA_BEANS)
                .requires(Items.COCOA_BEANS)
                .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                .save(output, getRecipeName(ModItems.COFFEE, ModItems.CHOCOLATE_COFFEE));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HONEY_COFFEE, 1)
                .requires(ModItems.MILK_COFFEE)
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy(getHasName(ModItems.MILK_COFFEE), has(ModItems.MILK_COFFEE))
                .save(output, getRecipeName(ModItems.MILK_COFFEE, ModItems.HONEY_COFFEE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HONEY_COFFEE, 1)
                .requires(ModItems.COFFEE)
                .requires(CommonTags.FOODS_MILK)
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                .save(output, getRecipeName(ModItems.COFFEE, ModItems.HONEY_COFFEE));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SYRUP_COFFEE, 1)
                .requires(ModItems.MILK_COFFEE)
                .requires(ModTags.Items.SYRUP)
                .unlockedBy(getHasName(ModItems.MILK_COFFEE), has(ModItems.MILK_COFFEE))
                .save(output, getRecipeName(ModItems.MILK_COFFEE, ModItems.SYRUP_COFFEE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SYRUP_COFFEE, 1)
                .requires(ModItems.COFFEE)
                .requires(CommonTags.FOODS_MILK)
                .requires(ModTags.Items.SYRUP)
                .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                .save(output, getRecipeName(ModItems.COFFEE, ModItems.SYRUP_COFFEE));

        // Syrup-based recipes
        oneToOne(output, RecipeCategory.MISC, ModItems.SYRUP, Items.SUGAR, 3);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SYRUP_SANDWICH, 1)
                .requires(ConventionalItemTags.BREAD_FOODS)
                .requires(ModTags.Items.SYRUP)
                .requires(Items.SUGAR)
                .unlockedBy(getHasName(ModItems.SYRUP), has(ModItems.SYRUP))
                .save(output);

        // Feasts
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RICE_ROLL_ROYALE)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.KELP_ROLL_SLICE.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.KELP_ROLL_SLICE.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.KELP_ROLL_SLICE.get())
                .requires(ModItems.BELL_PEPPER_ROLL_GREEN)
                .requires(ModItems.BELL_PEPPER_ROLL_YELLOW)
                .requires(ModItems.BELL_PEPPER_ROLL_RED)
                .requires(ModItems.CALAMARI_ROLL)
                .requires(Items.BOWL)
                .requires(ModItems.CHERRY_BLOSSOM_ROLL)
                .unlockedBy("has_rice_roll", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ModItems.BELL_PEPPER_ROLL_GREEN,
                        ModItems.BELL_PEPPER_ROLL_YELLOW,
                        ModItems.BELL_PEPPER_ROLL_RED,
                        ModItems.CALAMARI_ROLL,
                        ModItems.CHERRY_BLOSSOM_ROLL,
                        vectorwing.farmersdelight.common.registry.ModItems.KELP_ROLL_SLICE.get()))
                .save(output);
    }

    private void buildCuttingRecipes(@NotNull RecipeOutput output) {
        // Cotton
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_COTTON), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.COTTON_SEEDS, 1)
                .addResultWithChance(ModItems.COTTON_BOLL, 0.3F)
                .addResultWithChance(Items.WHITE_DYE, 0.1F)
                .build(output, ItemUtils.getResourceLocation(ModItems.WILD_COTTON));

        // Bell pepper
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_BELL_PEPPERS), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BELL_PEPPER_SEEDS, 1)
                .addResultWithChance(ModItems.BELL_PEPPER_RED, 0.3F)
                .addResultWithChance(Items.RED_DYE, 0.1F)
                .build(output, ItemUtils.getResourceLocation(ModItems.WILD_BELL_PEPPERS));

        // Bell pepper slices
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BELL_PEPPER_GREEN), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BELL_PEPPER_SLICE_GREEN, 1)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS, 0.3F)
                .build(output, ItemUtils.getResourceLocation(ModItems.BELL_PEPPER_GREEN));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BELL_PEPPER_YELLOW), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BELL_PEPPER_SLICE_YELLOW, 1)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS, 0.3F)
                .build(output, ItemUtils.getResourceLocation(ModItems.BELL_PEPPER_YELLOW));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BELL_PEPPER_RED), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BELL_PEPPER_SLICE_RED, 1)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS, 0.3F)
                .build(output, ItemUtils.getResourceLocation(ModItems.BELL_PEPPER_RED));

        // Roasted bell pepper slices
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.ROASTED_BELL_PEPPER_GREEN), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.ROASTED_BELL_PEPPER_SLICE_GREEN, 1)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS, 0.3F)
                .build(output, ItemUtils.getResourceLocation(ModItems.ROASTED_BELL_PEPPER_GREEN));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.ROASTED_BELL_PEPPER_YELLOW), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.ROASTED_BELL_PEPPER_SLICE_YELLOW, 1)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS, 0.3F)
                .build(output, ItemUtils.getResourceLocation(ModItems.ROASTED_BELL_PEPPER_YELLOW));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.ROASTED_BELL_PEPPER_RED), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.ROASTED_BELL_PEPPER_SLICE_RED, 1)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS, 0.3F)
                .build(output,ItemUtils.getResourceLocation(ModItems.ROASTED_BELL_PEPPER_RED));

        // Coffee
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_COFFEE), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.COFFEE_BEANS, 1)
                .addResultWithChance(ModItems.COFFEE_BEANS, 0.3F)
                .addResultWithChance(Items.YELLOW_DYE, 0.1F)
                .build(output, ItemUtils.getResourceLocation(ModItems.WILD_COFFEE));

        // Food
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POTATO), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.POTATO_SLICES, 2)
                .build(output, ItemUtils.getResourceLocation(ModItems.POTATO_SLICES));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BAKED_POTATO), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.BAKED_POTATO_SLICES, 2)
                .build(output, ItemUtils.getResourceLocation(ModItems.BAKED_POTATO_SLICES));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CALAMARI), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.CALAMARI_SLICE, 2)
                .addResult(Items.BONE_MEAL)
                .build(output, ItemUtils.getResourceLocation(ModItems.CALAMARI_SLICE));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.COOKED_CALAMARI), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.COOKED_CALAMARI_SLICE, 2)
                .addResult(Items.BONE_MEAL)
                .build(output, ItemUtils.getResourceLocation(ModItems.COOKED_CALAMARI_SLICE));

        // Pie
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CHERRY_BLOSSOM_CHEESECAKE), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE, 4)
                .build(output, ItemUtils.getResourceLocation(ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.SYRUP_CHEESECAKE), Ingredient.of(CommonTags.TOOLS_KNIFE), ModItems.SYRUP_CHEESECAKE_SLICE, 4)
                .build(output, ItemUtils.getResourceLocation(ModItems.SYRUP_CHEESECAKE_SLICE));

        // Salvaging
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ItemTags.WOOL), Ingredient.of(ConventionalItemTags.SHEAR_TOOLS), Items.STRING, 2)
                .build(output, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "wool"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ItemTags.WOOL_CARPETS), Ingredient.of(ConventionalItemTags.SHEAR_TOOLS), Items.STRING, 1)
                .build(output, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "wool_carpet"));
    }

    private void buildCookingRecipes(@NotNull RecipeOutput output) {
        // Cooking oil
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COOKING_OIL, 2, FAST_COOKING, SMALL_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.COTTON_SEEDS, Items.PUMPKIN_SEEDS)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, ItemUtils.getResourceLocation(ModItems.COOKING_OIL));

        // Batter
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BATTER, 2, FAST_COOKING, SMALL_EXP, Items.BOWL)
                .addIngredient(CommonTags.FOODS_MILK)
                .addIngredient(ConventionalItemTags.EGGS)
                .addIngredient(Items.WHEAT)
                .addIngredient(Items.WHEAT)
                .unlockedByAnyIngredient(Items.MILK_BUCKET, vectorwing.farmersdelight.common.registry.ModItems.MILK_BOTTLE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, ItemUtils.getResourceLocation(ModItems.BATTER));

        // Syrup
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.SYRUP, 1, FAST_COOKING, SMALL_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.SYRUP_INGREDIENTS)
                .addIngredient(Items.SUGAR)
                .unlockedByAnyIngredient(Items.APPLE, Items.BEETROOT, Items.SUGAR)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, ItemUtils.getResourceLocation(ModItems.SYRUP));

        // Fried Dough
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_DOUGH, 1, FAST_COOKING, SMALL_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(CommonTags.FOODS_DOUGH)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, ItemUtils.getResourceLocation(ModItems.FRIED_DOUGH));

        // Fried Dumplings
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_DUMPLINGS, 2, FAST_COOKING, MEDIUM_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.DUMPLINGS.get(), 2)
                .unlockedByAnyIngredient(vectorwing.farmersdelight.common.registry.ModItems.DUMPLINGS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, ItemUtils.getResourceLocation(ModItems.FRIED_DUMPLINGS));

        // Spring Rolls
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.SPRING_ROLLS, 2, FAST_COOKING, MEDIUM_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(CommonTags.FOODS_DOUGH)
                .addIngredient(CommonTags.FOODS_LEAFY_GREEN)
                .addIngredient(ModTags.Items.SPRING_ROLL_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, ItemUtils.getResourceLocation(ModItems.SPRING_ROLLS));

        // Fruit Beignet
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRUIT_BEIGNET, 1, FAST_COOKING, MEDIUM_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(CommonTags.FOODS_DOUGH)
                .addIngredient(ModTags.Items.FRUITS_AND_BERRIES)
                .addIngredient(Items.SUGAR)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, ItemUtils.getResourceLocation(ModItems.FRUIT_BEIGNET));

        // Fried Calamari
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_CALAMARI, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ModItems.BATTER)
                .addIngredient(CommonTags.FOODS_RAW_CALAMARI)
                .addIngredient(CommonTags.FOODS_TOMATO)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, ItemUtils.getResourceLocation(ModItems.FRIED_CALAMARI));

        // Fried Chicken
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_CHICKEN, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ModItems.BATTER)
                .addIngredient(CommonTags.FOODS_RAW_CHICKEN)
                .addIngredient(CommonTags.FOODS_ONION)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, ItemUtils.getResourceLocation(ModItems.FRIED_CHICKEN));

        // Fried Mushrooms
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_MUSHROOMS, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(Items.BROWN_MUSHROOM)
                .addIngredient(Items.RED_MUSHROOM)
                .addIngredient(CommonTags.FOODS_ONION)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, ItemUtils.getResourceLocation(ModItems.FRIED_MUSHROOMS));

        // Bell Pepper Soup
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BELL_PEPPER_SOUP, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN, ModItems.BELL_PEPPER_YELLOW, ModItems.BELL_PEPPER_RED)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, ItemUtils.getResourceLocation(ModItems.BELL_PEPPER_SOUP));

        // Stuffed Bell Peppers
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_GREEN, 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_GREEN)
                .addIngredient(CommonTags.CROPS_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, ItemUtils.getResourceLocation(ModItems.STUFFED_BELL_PEPPER_GREEN));
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_YELLOW, 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_YELLOW)
                .addIngredient(CommonTags.CROPS_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_YELLOW)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, ItemUtils.getResourceLocation(ModItems.STUFFED_BELL_PEPPER_YELLOW));
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_RED, 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_RED)
                .addIngredient(CommonTags.CROPS_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_RED)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, ItemUtils.getResourceLocation(ModItems.STUFFED_BELL_PEPPER_RED));

        // Bell Pepper Pasta
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BELL_PEPPER_PASTA, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(CommonTags.FOODS_PASTA)
                .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN, ModItems.BELL_PEPPER_YELLOW, ModItems.BELL_PEPPER_RED)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, ItemUtils.getResourceLocation(ModItems.BELL_PEPPER_PASTA));

        // Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COFFEE, 1, NORMAL_COOKING, MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(output,ItemUtils.getResourceLocation(ModItems.COFFEE));

        // Milk Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.MILK_COFFEE, 1, NORMAL_COOKING, MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(CommonTags.FOODS_MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(output, ItemUtils.getResourceLocation(ModItems.MILK_COFFEE));

        // Chocolate Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.CHOCOLATE_COFFEE, 1, NORMAL_COOKING, MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(CommonTags.FOODS_MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(Items.COCOA_BEANS, 2)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(output, ItemUtils.getResourceLocation(ModItems.CHOCOLATE_COFFEE));

        // Honey Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.HONEY_COFFEE, 1, NORMAL_COOKING, MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(CommonTags.FOODS_MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(Items.HONEY_BOTTLE, 1)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(output, ItemUtils.getResourceLocation(ModItems.HONEY_COFFEE));

        // Syrup Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.SYRUP_COFFEE, 1, NORMAL_COOKING, MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(CommonTags.FOODS_MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModItems.SYRUP, 1)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(output, ItemUtils.getResourceLocation(ModItems.SYRUP_COFFEE));

        // Dark Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.DARK_COFFEE, 1, SLOW_COOKING, MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(output, ItemUtils.getResourceLocation(ModItems.DARK_COFFEE));

        // Coffee-Braised Beef
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COFFEE_BRAISED_BEEF, 1, SLOW_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(CommonTags.FOODS_RAW_BEEF)
                .addIngredient(ModTags.Items.COFFEE_FOOD_INGREDIENTS)
                .addIngredient(CommonTags.FOODS_CARROT)
                .addIngredient(CommonTags.FOODS_POTATO)
                .unlockedByAnyIngredient(ModItems.COFFEE)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, ItemUtils.getResourceLocation(ModItems.COFFEE_BRAISED_BEEF));
    }

    private void buildFarmersDelightOverrideRecipes(@NotNull RecipeOutput output) {
        // Fried Rice
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.FRIED_RICE.get(), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .addIngredient(ModTags.Items.FRIED_RICE_INGREDIENTS)
                .addIngredient(CommonTags.FOODS_CARROT)
                .addIngredient(CommonTags.FOODS_ONION)
                .unlockedByAnyIngredient(vectorwing.farmersdelight.common.registry.ModItems.RICE.get(), Items.EGG, Items.CARROT, vectorwing.farmersdelight.common.registry.ModItems.ONION.get(), ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output);

        // Fried Egg
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.FRIED_EGG.get(), 1, FAST_COOKING, SMALL_EXP)
                .addIngredient(Items.EGG)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, getRecipeName(ModItems.COOKING_OIL, vectorwing.farmersdelight.common.registry.ModItems.FRIED_EGG.get()));

        // Baked Cod Stew
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.BAKED_COD_STEW.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(CommonTags.FOODS_RAW_COD)
                .addIngredient(CommonTags.FOODS_POTATO)
                .addIngredient(ModTags.Items.RAW_AND_COOKED_EGGS)
                .addIngredient(CommonTags.FOODS_TOMATO)
                .unlockedByAnyIngredient(Items.COD, Items.POTATO, vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get(), Items.EGG)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(output);

        // Beef Stew
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.BEEF_STEW.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(CommonTags.FOODS_RAW_BEEF)
                .addIngredient(CommonTags.FOODS_CARROT)
                .addIngredient(CommonTags.FOODS_POTATO)
                .unlockedByAnyIngredient(Items.BEEF, Items.CARROT, Items.POTATO)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(output);

        // Mushroom Rice
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.MUSHROOM_RICE.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(Items.BROWN_MUSHROOM)
                .addIngredient(Items.RED_MUSHROOM)
                .addIngredient(CommonTags.CROPS_RICE)
                .addIngredient(ModTags.Items.MUSHROOM_RICE_INGREDIENTS)
                .unlockedByAnyIngredient(Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM, vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(output);

        // Vegetable Soup
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.VEGETABLE_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(CommonTags.FOODS_CARROT)
                .addIngredient(CommonTags.FOODS_POTATO)
                .addIngredient(CommonTags.FOODS_BEETROOT)
                .addIngredient(CommonTags.FOODS_LEAFY_GREEN)
                .unlockedByAnyIngredient(Items.CARROT, vectorwing.farmersdelight.common.registry.ModItems.ONION.get(), Items.BEETROOT)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(output);
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

    protected static void pancakeRecipes(@NotNull RecipeOutput recipeOutput, @NotNull Item pancakeBlock, @NotNull Item singlePancake, Ingredient topping, Ingredient ingredient) {
        pancakeRecipes(recipeOutput, pancakeBlock, singlePancake, topping, ingredient, ingredient);
    }

    protected static void pancakeRecipes(@NotNull RecipeOutput recipeOutput, @NotNull Item pancakeBlock, @NotNull Item singlePancake, Ingredient topping, Ingredient ingredient, Ingredient ingredient2) {
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
        CookingPotRecipeBuilder.cookingPotRecipe(pancakeBlock, 1, SLOW_COOKING, LARGE_EXP, servingItem)
                .addIngredient(batter)
                .addIngredient(topping)
                .addIngredient(ingredient, 2)
                .addIngredient(ingredient2, 2)
                .unlockedByAnyIngredient(batter)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(recipeOutput, ItemUtils.getResourceLocation(pancakeBlock));

        // Cutting recipe for pancakes to separate them into single pancakes.
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(pancakeBlock), Ingredient.of(CommonTags.TOOLS_KNIFE), singlePancake, PancakeBlock.MAX_SERVINGS)
                .addResult(servingItem)
                .build(recipeOutput, ItemUtils.getResourceLocation(pancakeBlock));

        // Split a stack of pancakes into separate pancakes.
        oneToOne(recipeOutput, RecipeCategory.MISC, pancakeBlock, singlePancake, PancakeBlock.MAX_SERVINGS);

        // Combine separate pancakes together into a single stack
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, pancakeBlock)
                .requires(singlePancake, PancakeBlock.MAX_SERVINGS)
                .requires(servingItem) // Pancakes are always placed on a bowl
                .unlockedBy(getHasName(singlePancake), has(singlePancake))
                .save(recipeOutput, getRecipeName(singlePancake, pancakeBlock));
    }

    protected static void pieRecipes(@NotNull RecipeOutput recipeOutput, @NotNull Item pieBlock, @NotNull Item sliceItem, Ingredient topping) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, pieBlock, 1)
                .pattern("TTT")
                .pattern("MMM")
                .pattern("SCS")
                .define('T', topping)
                .define('M', CommonTags.FOODS_MILK)
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
}

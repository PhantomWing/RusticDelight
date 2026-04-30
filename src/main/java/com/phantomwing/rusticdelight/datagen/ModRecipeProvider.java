package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.custom.PancakeBlock;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tags.ForgeTags;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.common.crafting.DifferenceIngredient;
import net.minecraftforge.common.crafting.PartialNBTIngredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
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
        buildFarmersDelightOverrideRecipes(output);
    }

    private void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> output) {
        // Bell pepper
        foodCookingRecipes(output, ModItems.BELL_PEPPER_GREEN.get(), ModItems.ROASTED_BELL_PEPPER_GREEN.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(output, ModItems.BELL_PEPPER_YELLOW.get(), ModItems.ROASTED_BELL_PEPPER_YELLOW.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(output, ModItems.BELL_PEPPER_RED.get(), ModItems.ROASTED_BELL_PEPPER_RED.get(), FOOD_COOKING_EXP);

        foodCookingRecipes(output, ModItems.BELL_PEPPER_SLICE_GREEN.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_GREEN.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(output, ModItems.BELL_PEPPER_SLICE_YELLOW.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_YELLOW.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(output, ModItems.BELL_PEPPER_SLICE_RED.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_RED.get(), FOOD_COOKING_EXP);

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
        simpleSushiRoll(output, ModItems.BELL_PEPPER_SLICE_GREEN, ModItems.BELL_PEPPER_ROLL_GREEN);
        simpleSushiRoll(output, ModItems.BELL_PEPPER_SLICE_YELLOW, ModItems.BELL_PEPPER_ROLL_YELLOW);
        simpleSushiRoll(output, ModItems.BELL_PEPPER_SLICE_RED, ModItems.BELL_PEPPER_ROLL_RED);

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

        // Salads
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.POTATO_SALAD.get(), 1)
                .requires(Items.BOWL)
                .requires(ForgeTags.VEGETABLES_POTATO)
                .requires(ForgeTags.VEGETABLES_ONION)
                .requires(ForgeTags.MILK)
                .requires(Tags.Items.EGGS)
                .unlockedBy(getHasName(Items.POTATO), has(Items.POTATO))
                .unlockedBy(getHasName(ModItems.POTATO_SLICES.get()), has(ModItems.POTATO_SLICES.get()))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SWEET_SALAD.get(), 1)
                .requires(Items.BOWL)
                .requires(ModTags.Items.SWEET_LIQUIDS)
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(ForgeTags.VEGETABLES)
                .requires(ForgeTags.BERRIES)
                .requires(ForgeTags.BERRIES)
                .unlockedBy(getHasName(Items.HONEY_BOTTLE), has(Items.HONEY_BOTTLE))
                .unlockedBy(getHasName(ModItems.SYRUP.get()), has(ModItems.SYRUP.get()))
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.COFFEE_COOKIE.get(), 8)
                .requires(ModTags.Items.COFFEE_INGREDIENTS)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(ModItems.ROASTED_COFFEE_BEANS.get()), has(ModItems.ROASTED_COFFEE_BEANS.get()))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SYRUP_COOKIE.get(), 8)
                .requires(ModTags.Items.SYRUP)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(ModItems.SYRUP.get()), has(ModItems.SYRUP.get()))
                .save(output);

        // Pies
        pieRecipes(output, ModItems.SYRUP_CHEESECAKE, ModItems.SYRUP_CHEESECAKE_SLICE, Ingredient.of(ModTags.Items.SYRUP));
        pieRecipes(output, ModItems.CHERRY_BLOSSOM_CHEESECAKE, ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE, Ingredient.of(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS));

        // Pancakes
        pancakeRecipes(output, ModItems.PANCAKES, ModItems.PANCAKE, Ingredient.of(ModTags.Items.SYRUP), Ingredient.of(Items.SUGAR));
        pancakeRecipes(output, ModItems.HONEY_PANCAKES, ModItems.HONEY_PANCAKE, Ingredient.of(Items.HONEY_BOTTLE), Ingredient.of(Items.SWEET_BERRIES), Ingredient.of(Items.SUGAR));
        pancakeRecipes(output, ModItems.CHOCOLATE_PANCAKES, ModItems.CHOCOLATE_PANCAKE, Ingredient.of(ForgeTags.MILK), Ingredient.of(Items.COCOA_BEANS));
        pancakeRecipes(output, ModItems.VEGETABLE_PANCAKES, ModItems.VEGETABLE_PANCAKE, Ingredient.of(ForgeTags.MILK), Ingredient.of(ForgeTags.VEGETABLES), Ingredient.of(ForgeTags.SALAD_INGREDIENTS));
        pancakeRecipes(output, ModItems.CHERRY_BLOSSOM_PANCAKES, ModItems.CHERRY_BLOSSOM_PANCAKE, Ingredient.of(ForgeTags.MILK), Ingredient.of(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS));
        pancakeRecipes(output, ModItems.PUMPKIN_PANCAKES, ModItems.PUMPKIN_PANCAKE, Ingredient.of(ModTags.Items.SYRUP), Ingredient.of(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get()));

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
                .unlockedBy(getHasName(ModItems.ROASTED_COFFEE_BEANS.get()), has(ModItems.ROASTED_COFFEE_BEANS.get()))
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
                .unlockedBy(getHasName(ModItems.MILK_COFFEE.get()), has(ModItems.MILK_COFFEE.get()))
                .save(output, getRecipeName(ModItems.MILK_COFFEE.get(), ModItems.HONEY_COFFEE.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HONEY_COFFEE.get(), 1)
                .requires(ModItems.COFFEE.get())
                .requires(ForgeTags.MILK)
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy(getHasName(ModItems.COFFEE.get()), has(ModItems.COFFEE.get()))
                .save(output, getRecipeName(ModItems.COFFEE.get(), ModItems.HONEY_COFFEE.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SYRUP_COFFEE.get(), 1)
                .requires(ModItems.MILK_COFFEE.get())
                .requires(ModTags.Items.SYRUP)
                .unlockedBy(getHasName(ModItems.MILK_COFFEE.get()), has(ModItems.MILK_COFFEE.get()))
                .save(output, getRecipeName(ModItems.MILK_COFFEE.get(), ModItems.SYRUP_COFFEE.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SYRUP_COFFEE.get(), 1)
                .requires(ModItems.COFFEE.get())
                .requires(ForgeTags.MILK)
                .requires(ModTags.Items.SYRUP)
                .unlockedBy(getHasName(ModItems.COFFEE.get()), has(ModItems.COFFEE.get()))
                .save(output, getRecipeName(ModItems.COFFEE.get(), ModItems.SYRUP_COFFEE.get()));

        // Syrup-based recipes
        oneToOne(output, RecipeCategory.MISC, ModItems.SYRUP.get(), Items.SUGAR, 3);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SYRUP_SANDWICH.get(), 1)
                .requires(ForgeTags.BREAD)
                .requires(ModTags.Items.SYRUP)
                .requires(Items.SUGAR)
                .unlockedBy(getHasName(ModItems.SYRUP.get()), has(ModItems.SYRUP.get()))
                .save(output);

        // Feasts
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RICE_ROLL_ROYALE.get())
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
                .save(output);
    }

    private void buildCuttingRecipes(@NotNull Consumer<FinishedRecipe> output) {
        // Cotton
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_COTTON.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.COTTON_SEEDS.get(), 1)
                .addResultWithChance(ModItems.COTTON_BOLL.get(), 0.3F)
                .addResultWithChance(Items.WHITE_DYE, 0.1F)
                .save(output, getCuttingPath(ModItems.WILD_COTTON.get()));

        // Bell pepper
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_BELL_PEPPERS.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.BELL_PEPPER_SEEDS.get(), 1)
                .addResultWithChance(ModItems.BELL_PEPPER_RED.get(), 0.3F)
                .addResultWithChance(Items.RED_DYE, 0.1F)
                .save(output, getCuttingPath(ModItems.WILD_BELL_PEPPERS.get()));

        // Bell pepper slices
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BELL_PEPPER_GREEN.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.BELL_PEPPER_SLICE_GREEN.get(), 1)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS.get(), 0.3F)
                .save(output, getCuttingPath(ModItems.BELL_PEPPER_GREEN.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BELL_PEPPER_YELLOW.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.BELL_PEPPER_SLICE_YELLOW.get(), 1)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS.get(), 0.3F)
                .save(output, getCuttingPath(ModItems.BELL_PEPPER_YELLOW.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BELL_PEPPER_RED.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.BELL_PEPPER_SLICE_RED.get(), 1)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS.get(), 0.3F)
                .save(output, getCuttingPath(ModItems.BELL_PEPPER_RED.get()));

        // Roasted bell pepper slices
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.ROASTED_BELL_PEPPER_GREEN.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.ROASTED_BELL_PEPPER_SLICE_GREEN.get(), 1)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS.get(), 0.3F)
                .save(output, getCuttingPath(ModItems.ROASTED_BELL_PEPPER_GREEN.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.ROASTED_BELL_PEPPER_YELLOW.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.ROASTED_BELL_PEPPER_SLICE_YELLOW.get(), 1)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS.get(), 0.3F)
                .save(output, getCuttingPath(ModItems.ROASTED_BELL_PEPPER_YELLOW.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.ROASTED_BELL_PEPPER_RED.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.ROASTED_BELL_PEPPER_SLICE_RED.get(), 1)
                .addResultWithChance(ModItems.BELL_PEPPER_SEEDS.get(), 0.3F)
                .save(output, getCuttingPath(ModItems.ROASTED_BELL_PEPPER_RED.get()));

        // Coffee
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_COFFEE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.COFFEE_BEANS.get(), 1)
                .addResultWithChance(ModItems.COFFEE_BEANS.get(), 0.3F)
                .addResultWithChance(Items.YELLOW_DYE, 0.1F)
                .save(output, ModItems.WILD_COFFEE.getId());

        // Food
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POTATO), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.POTATO_SLICES.get(), 2)
                .save(output, getCuttingPath(ModItems.POTATO_SLICES.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BAKED_POTATO), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.BAKED_POTATO_SLICES.get(), 2)
                .save(output, getCuttingPath(ModItems.BAKED_POTATO_SLICES.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CALAMARI.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.CALAMARI_SLICE.get(), 2)
                .addResult(Items.BONE_MEAL)
                .save(output, getCuttingPath(ModItems.CALAMARI_SLICE.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.COOKED_CALAMARI.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.COOKED_CALAMARI_SLICE.get(), 2)
                .addResult(Items.BONE_MEAL)
                .save(output, getCuttingPath(ModItems.COOKED_CALAMARI_SLICE.get()));

        // Pie
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CHERRY_BLOSSOM_CHEESECAKE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE.get(), 4)
                .save(output, ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE.getId());
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.SYRUP_CHEESECAKE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.SYRUP_CHEESECAKE_SLICE.get(), 4)
                .save(output, ModItems.SYRUP_CHEESECAKE_SLICE.getId());

        // Salvaging
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ItemTags.WOOL), Ingredient.of(Tags.Items.SHEARS), Items.STRING, 2)
                .save(output, getCuttingPath("wool"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ItemTags.WOOL_CARPETS), Ingredient.of(Tags.Items.SHEARS), Items.STRING, 1)
                .save(output, getCuttingPath("wool_carpet"));
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
                .save(output, getCookingPath(ModItems.COOKING_OIL.get()));

        // Batter
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BATTER.get(), 2, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP, Items.BOWL)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(Tags.Items.EGGS)
                .addIngredient(Items.WHEAT)
                .addIngredient(Items.WHEAT)
                .unlockedByAnyIngredient(Items.MILK_BUCKET, vectorwing.farmersdelight.common.registry.ModItems.MILK_BOTTLE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, getCookingPath(ModItems.BATTER.get()));

        // Syrup
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.SYRUP.get(), 1, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.SYRUP_INGREDIENTS)
                .addIngredient(Items.SUGAR)
                .unlockedByAnyIngredient(Items.APPLE, Items.BEETROOT, Items.SUGAR)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, ModItems.SYRUP.getId());

        // Fried Dough
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_DOUGH.get(), 1, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ForgeTags.DOUGH)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, ModItems.FRIED_DOUGH.getId());

        // Fried Dumplings
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_DUMPLINGS.get(), 2, CookingRecipes.FAST_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.DUMPLINGS.get(), 2)
                .unlockedByAnyIngredient(vectorwing.farmersdelight.common.registry.ModItems.DUMPLINGS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, ModItems.FRIED_DUMPLINGS.getId());

        // Spring Rolls
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.SPRING_ROLLS.get(), 2, CookingRecipes.FAST_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ForgeTags.DOUGH)
                .addIngredient(ForgeTags.SALAD_INGREDIENTS)
                .addIngredient(ModTags.Items.SPRING_ROLL_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, getCookingPath(ModItems.SPRING_ROLLS.get()));

        // Fruit Beignet
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRUIT_BEIGNET.get(), 1, CookingRecipes.FAST_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ForgeTags.DOUGH)
                .addIngredient(ForgeTags.BERRIES)
                .addIngredient(Items.SUGAR)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, getCookingPath(ModItems.FRUIT_BEIGNET.get()));

        // Fried Calamari
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_CALAMARI.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ModItems.BATTER.get())
                .addIngredient(ForgeTags.RAW_FISHES_CALAMARI)
                .addIngredient(ForgeTags.VEGETABLES_TOMATO)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, getCookingPath(ModItems.FRIED_CALAMARI.get()));

        // Fried Chicken
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_CHICKEN.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ModItems.BATTER.get())
                .addIngredient(ForgeTags.RAW_CHICKEN)
                .addIngredient(ForgeTags.VEGETABLES_ONION)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, getCookingPath(ModItems.FRIED_CHICKEN.get()));

        // Fried Mushrooms
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_MUSHROOMS.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(Items.BROWN_MUSHROOM)
                .addIngredient(Items.RED_MUSHROOM)
                .addIngredient(ForgeTags.VEGETABLES_ONION)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, getCookingPath(ModItems.FRIED_MUSHROOMS.get()));

        // Bell Pepper Soup
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BELL_PEPPER_SOUP.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN.get(), ModItems.BELL_PEPPER_YELLOW.get(), ModItems.BELL_PEPPER_RED.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, getCookingPath(ModItems.BELL_PEPPER_SOUP.get()));

        // Stuffed Bell Peppers
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_GREEN.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_GREEN.get())
                .addIngredient(ForgeTags.GRAIN_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, getCookingPath(ModItems.STUFFED_BELL_PEPPER_GREEN.get()));
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_YELLOW.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_YELLOW.get())
                .addIngredient(ForgeTags.GRAIN_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_YELLOW.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, getCookingPath(ModItems.STUFFED_BELL_PEPPER_YELLOW.get()));
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_RED.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_RED.get())
                .addIngredient(ForgeTags.GRAIN_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_RED.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, getCookingPath(ModItems.STUFFED_BELL_PEPPER_RED.get()));

        // Bell Pepper Pasta
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BELL_PEPPER_PASTA.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ForgeTags.PASTA)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN.get(), ModItems.BELL_PEPPER_YELLOW.get(), ModItems.BELL_PEPPER_RED.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, getCookingPath(ModItems.BELL_PEPPER_PASTA.get()));

        // Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(output, ModItems.COFFEE.getId());

        // Milk Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.MILK_COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(output, ModItems.MILK_COFFEE.getId());

        // Chocolate Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.CHOCOLATE_COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(Items.COCOA_BEANS, 2)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(output, ModItems.CHOCOLATE_COFFEE.getId());

        // Honey Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.HONEY_COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(Items.HONEY_BOTTLE, 1)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(output, ModItems.HONEY_COFFEE.getId());

        // Syrup Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.SYRUP_COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModItems.SYRUP.get(), 1)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(output, ModItems.SYRUP_COFFEE.getId());

        // Dark Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.DARK_COFFEE.get(), 1, CookingRecipes.SLOW_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(output, ModItems.DARK_COFFEE.getId());

        // Coffee-Braised Beef
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COFFEE_BRAISED_BEEF.get(), 1, CookingRecipes.SLOW_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ForgeTags.RAW_BEEF)
                .addIngredient(ModTags.Items.COFFEE_FOOD_INGREDIENTS)
                .addIngredient(ForgeTags.VEGETABLES_CARROT)
                .addIngredient(ForgeTags.VEGETABLES_POTATO)
                .unlockedByAnyIngredient(ModItems.COFFEE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output, ModItems.COFFEE_BRAISED_BEEF.getId());
    }

    private void buildFarmersDelightOverrideRecipes(@NotNull Consumer<FinishedRecipe> output) {
        // Fried Rice
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.FRIED_RICE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .addIngredient(CompoundIngredient.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(ModTags.Items.COOKING_OIL)))
                .addIngredient(ForgeTags.VEGETABLES_CARROT)
                .addIngredient(ForgeTags.VEGETABLES_ONION)
                .unlockedByAnyIngredient(vectorwing.farmersdelight.common.registry.ModItems.RICE.get(), Items.EGG, Items.CARROT, vectorwing.farmersdelight.common.registry.ModItems.ONION.get(), ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output);

        // Fried Egg
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.FRIED_EGG.get(), 1, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP)
                .addIngredient(Items.EGG)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(output, getRecipeName(ModItems.COOKING_OIL.get(), vectorwing.farmersdelight.common.registry.ModItems.FRIED_EGG.get()));

        // Baked Cod Stew
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.BAKED_COD_STEW.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ForgeTags.RAW_FISHES_COD)
                .addIngredient(ForgeTags.VEGETABLES_POTATO)
                .addIngredient(ModTags.Items.RAW_AND_COOKED_EGGS)
                .addIngredient(ForgeTags.VEGETABLES_TOMATO)
                .unlockedByAnyIngredient(Items.COD, Items.POTATO, vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get(), Items.EGG)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output);

        // Beef Stew
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.BEEF_STEW.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ForgeTags.RAW_BEEF)
                .addIngredient(ForgeTags.VEGETABLES_CARROT)
                .addIngredient(ForgeTags.VEGETABLES_POTATO)
                .unlockedByAnyIngredient(Items.BEEF, Items.CARROT, Items.POTATO)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output);

        // Mushroom Rice
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.MUSHROOM_RICE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(Items.BROWN_MUSHROOM)
                .addIngredient(Items.RED_MUSHROOM)
                .addIngredient(ForgeTags.CROPS_RICE)
                .addIngredient(CompoundIngredient.of(Ingredient.of(ForgeTags.VEGETABLES_CARROT), Ingredient.of(ForgeTags.VEGETABLES_POTATO)))
                .unlockedByAnyIngredient(Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM, vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output);

        // Vegetable Soup
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.VEGETABLE_SOUP.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ForgeTags.VEGETABLES_CARROT)
                .addIngredient(ForgeTags.VEGETABLES_POTATO)
                .addIngredient(ForgeTags.VEGETABLES_BEETROOT)
                .addIngredient(ForgeTags.SALAD_INGREDIENTS)
                .unlockedByAnyIngredient(Items.CARROT, vectorwing.farmersdelight.common.registry.ModItems.ONION.get(), Items.BEETROOT)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(output);
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

    protected static void simpleSushiRoll(@NotNull Consumer<FinishedRecipe> recipeOutput, @NotNull RegistryObject<Item> ingredient, @NotNull RegistryObject<Item> result) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, result.get(), 2)
                .requires(ingredient.get())
                .requires(ingredient.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get()))
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()), has(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()))
                .save(recipeOutput);
    }

    protected static void pancakeRecipes(@NotNull Consumer<FinishedRecipe> recipeOutput, @NotNull RegistryObject<Item> pancakeBlock, @NotNull RegistryObject<Item> singlePancake, Ingredient topping, Ingredient ingredient) {
        pancakeRecipes(recipeOutput, pancakeBlock, singlePancake, topping, ingredient, ingredient);
    }

    protected static void pancakeRecipes(@NotNull Consumer<FinishedRecipe> recipeOutput, @NotNull RegistryObject<Item> pancakeBlock, @NotNull RegistryObject<Item> singlePancake, Ingredient topping, Ingredient ingredient, Ingredient ingredient2) {
        var batter = ModItems.BATTER;
        var servingItem = Items.BOWL;

        // Crafting a pancake block.
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, pancakeBlock.get(), 1)
                .pattern(" T ")
                .pattern("XMX")
                .pattern("YBY")
                .define('T', topping) // Topping
                .define('X', ingredient) // Main ingredient
                .define('Y', ingredient2) // Optional secondary ingredient
                .define('M', batter.get())
                .define('B', servingItem)
                .unlockedBy(getHasName(batter.get()), has(batter.get()))
                .save(recipeOutput);

        // Cooking a pancake block
        CookingPotRecipeBuilder.cookingPotRecipe(pancakeBlock.get(), 1, CookingRecipes.SLOW_COOKING, CookingRecipes.LARGE_EXP, servingItem)
                .addIngredient(batter.get())
                .addIngredient(topping)
                .addIngredient(ingredient, 2)
                .addIngredient(ingredient2, 2)
                .unlockedByAnyIngredient(batter.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(recipeOutput, getCookingPath(pancakeBlock.get()));

        // Cutting recipe for pancakes to separate them into single pancakes.
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(pancakeBlock.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), singlePancake.get(), PancakeBlock.MAX_SERVINGS)
                .addResult(servingItem)
                .save(recipeOutput, getCuttingPath(pancakeBlock.get()));

        // Split a stack of pancakes into separate pancakes.
        oneToOne(recipeOutput, RecipeCategory.MISC, pancakeBlock.get(), singlePancake.get(), PancakeBlock.MAX_SERVINGS);

        // Combine separate pancakes together into a single stack
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, pancakeBlock.get())
                .requires(singlePancake.get(), PancakeBlock.MAX_SERVINGS)
                .requires(servingItem) // Pancakes are always placed on a bowl
                .unlockedBy(getHasName(singlePancake.get()), has(singlePancake.get()))
                .save(recipeOutput, getRecipeName(singlePancake.get(), pancakeBlock.get()));
    }

    protected static void pieRecipes(@NotNull Consumer<FinishedRecipe> recipeOutput, @NotNull RegistryObject<Item> pieBlock, @NotNull RegistryObject<Item> sliceItem, Ingredient topping) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, pieBlock.get(), 1)
                .pattern("TTT")
                .pattern("MMM")
                .pattern("SCS")
                .define('T', topping)
                .define('M', ForgeTags.MILK)
                .define('S', Items.SUGAR)
                .define('C', vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get())
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get()), has(vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, pieBlock.get(), 1)
                .pattern("##")
                .pattern("##")
                .define('#', sliceItem.get())
                .unlockedBy(getHasName(sliceItem.get()), has(sliceItem.get()))
                .save(recipeOutput, new ResourceLocation(RusticDelight.MOD_ID, getItemName(pieBlock.get()) + "_from_slices"));
    }

    protected static String getRecipeName(ItemLike item, ItemLike result) {
        return RusticDelight.MOD_ID + ":" + getConversionRecipeName(result, item);
    }

    protected static ResourceLocation getCookingPath(Item item) {
        return new ResourceLocation(RusticDelight.MOD_ID, "cooking/" + ForgeRegistries.ITEMS.getKey(item).getPath());
    }

    protected static ResourceLocation getCuttingPath(Item item) {
        return new ResourceLocation(RusticDelight.MOD_ID, "cutting/" + ForgeRegistries.ITEMS.getKey(item).getPath());
    }

    protected static ResourceLocation getCuttingPath(String itemName) {
        return new ResourceLocation(RusticDelight.MOD_ID, "cutting/" + itemName);
    }
}
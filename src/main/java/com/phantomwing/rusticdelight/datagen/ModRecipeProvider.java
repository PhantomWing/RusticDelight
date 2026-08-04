package com.phantomwing.rusticdelight.datagen;

import com.google.gson.JsonObject;
import com.phantomwing.rusticdelight.Configuration;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.custom.PancakeBlock;
import com.phantomwing.rusticdelight.condition.ConfigBooleanCondition;
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
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.PartialNBTIngredient;
import net.minecraftforge.common.crafting.conditions.ICondition;
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

    /**
     * Stands in for NeoForge's {@code RecipeOutput.withConditions(...)}, which Forge 1.20.1 has no
     * equivalent of. Wrapping the output rather than each recipe keeps the builder calls untouched:
     * every recipe sent through the returned consumer gets the conditions attached to its JSON, so
     * the whole family disappears from the datapack when its config toggle is off.
     *
     * <p>The recipe advancement Forge writes alongside it gets the same conditions, so a disabled
     * family leaves nothing behind: {@code ConditionalAdvancement} drops any advancement whose
     * top-level conditions fail, the same array shape used here.
     */
    private static Consumer<FinishedRecipe> withConditions(Consumer<FinishedRecipe> output, ICondition... conditions) {
        return recipe -> output.accept(new FinishedRecipe() {
            @Override
            public void serializeRecipeData(@NotNull JsonObject json) {
                recipe.serializeRecipeData(json);
            }

            @Override
            public @NotNull JsonObject serializeRecipe() {
                JsonObject json = recipe.serializeRecipe();
                json.add("conditions", CraftingHelper.serialize(conditions));
                return json;
            }

            @Override
            public @NotNull ResourceLocation getId() {
                return recipe.getId();
            }

            @Override
            public @NotNull RecipeSerializer<?> getType() {
                return recipe.getType();
            }

            @Override
            public JsonObject serializeAdvancement() {
                JsonObject json = recipe.serializeAdvancement();
                if (json != null) {
                    json.add("conditions", CraftingHelper.serialize(conditions));
                }
                return json;
            }

            @Override
            public ResourceLocation getAdvancementId() {
                return recipe.getAdvancementId();
            }
        });
    }

    /** Shorthand for the common case of gating on a single boolean config option. */
    private static Consumer<FinishedRecipe> gated(Consumer<FinishedRecipe> output, String... settingIds) {
        ICondition[] conditions = new ICondition[settingIds.length];
        for (int i = 0; i < settingIds.length; i++) {
            conditions[i] = new ConfigBooleanCondition(settingIds[i]);
        }
        return withConditions(output, conditions);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> output) {
        buildCraftingRecipes(output);
        buildCuttingRecipes(output);
        buildCookingRecipes(output);
        buildFarmersDelightOverrideRecipes(output);
    }

    private void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> output) {
        Consumer<FinishedRecipe> cottonOutput = gated(output, Configuration.ENABLE_COTTON_ID);
        Consumer<FinishedRecipe> coffeeOutput = gated(output, Configuration.ENABLE_COFFEE_ID);
        Consumer<FinishedRecipe> bellPepperOutput = gated(output, Configuration.ENABLE_BELL_PEPPERS_ID);
        Consumer<FinishedRecipe> calamariOutput = gated(output, Configuration.SQUIDS_DROP_CALAMARI_ID);
        Consumer<FinishedRecipe> cherryBlossomOutput = gated(output, Configuration.ENABLE_CHERRY_BLOSSOM_FOODS_ID);
        Consumer<FinishedRecipe> pancakesOutput = gated(output, Configuration.ENABLE_PANCAKES_ID);
        Consumer<FinishedRecipe> syrupOutput = gated(output, Configuration.ENABLE_SYRUP_FOODS_ID);
        Consumer<FinishedRecipe> potatoSlicesOutput = gated(output, Configuration.ENABLE_POTATO_SLICES_ID);
        // Plain and pumpkin pancakes are topped with Syrup, so they follow both toggles.
        Consumer<FinishedRecipe> pancakesAndSyrupOutput = gated(output,
                Configuration.ENABLE_PANCAKES_ID, Configuration.ENABLE_SYRUP_FOODS_ID);
        Consumer<FinishedRecipe> cherryBlossomPancakesOutput = gated(output,
                Configuration.ENABLE_CHERRY_BLOSSOM_FOODS_ID, Configuration.ENABLE_PANCAKES_ID);
        Consumer<FinishedRecipe> coffeePancakesOutput = gated(output,
                Configuration.ENABLE_COFFEE_ID, Configuration.ENABLE_PANCAKES_ID);
        Consumer<FinishedRecipe> coffeeAndSyrupOutput = gated(output,
                Configuration.ENABLE_COFFEE_ID, Configuration.ENABLE_SYRUP_FOODS_ID);
        Consumer<FinishedRecipe> coffeeAndCherryBlossomOutput = gated(output,
                Configuration.ENABLE_COFFEE_ID, Configuration.ENABLE_CHERRY_BLOSSOM_FOODS_ID);
        // Rice Roll Royale needs a roll from each of the three families.
        Consumer<FinishedRecipe> royaleOutput = gated(output,
                Configuration.ENABLE_BELL_PEPPERS_ID, Configuration.SQUIDS_DROP_CALAMARI_ID,
                Configuration.ENABLE_CHERRY_BLOSSOM_FOODS_ID);

        // Bell pepper
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_GREEN.get(), ModItems.ROASTED_BELL_PEPPER_GREEN.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_YELLOW.get(), ModItems.ROASTED_BELL_PEPPER_YELLOW.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_RED.get(), ModItems.ROASTED_BELL_PEPPER_RED.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_ORANGE.get(), ModItems.ROASTED_BELL_PEPPER_ORANGE.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_WHITE.get(), ModItems.ROASTED_BELL_PEPPER_WHITE.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_PINK.get(), ModItems.ROASTED_BELL_PEPPER_PINK.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_BLUE.get(), ModItems.ROASTED_BELL_PEPPER_BLUE.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_PURPLE.get(), ModItems.ROASTED_BELL_PEPPER_PURPLE.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_BLACK.get(), ModItems.ROASTED_BELL_PEPPER_BLACK.get(), FOOD_COOKING_EXP);

        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_GREEN.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_GREEN.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_YELLOW.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_YELLOW.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_RED.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_RED.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_ORANGE.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_ORANGE.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_WHITE.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_WHITE.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_PINK.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_PINK.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_BLUE.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_BLUE.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_PURPLE.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_PURPLE.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_BLACK.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_BLACK.get(), FOOD_COOKING_EXP);

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
                .save(bellPepperOutput);

        // Calamari
        foodCookingRecipes(calamariOutput, ModItems.CALAMARI.get(), ModItems.COOKED_CALAMARI.get(), FOOD_COOKING_EXP);
        foodCookingRecipes(calamariOutput, ModItems.CALAMARI_SLICE.get(), ModItems.COOKED_CALAMARI_SLICE.get(), FOOD_COOKING_EXP);

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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CALAMARI_ROLL.get(), 2)
                .requires(ModTags.Items.CALAMARI_ROLL_INGREDIENTS)
                .requires(ModTags.Items.CALAMARI_ROLL_INGREDIENTS)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .unlockedBy(getHasName(ModItems.CALAMARI_SLICE.get()), has(ModItems.CALAMARI_SLICE.get()))
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()), has(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()))
                .save(calamariOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_ROLL.get(), 2)
                .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .unlockedBy(getHasName(Items.PINK_PETALS), has(Items.PINK_PETALS))
                .unlockedBy(getHasName(Items.CHERRY_SAPLING), has(Items.CHERRY_SAPLING))
                .unlockedBy(getHasName(Items.CHERRY_LEAVES), has(Items.CHERRY_LEAVES))
                .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()), has(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()))
                .save(cherryBlossomOutput);

        // Potato
        foodCookingRecipes(potatoSlicesOutput, ModItems.POTATO_SLICES.get(), ModItems.BAKED_POTATO_SLICES.get(), FOOD_COOKING_EXP);

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
                // Ungated: SWEET_LIQUIDS also accepts Honey Bottle, so this still works with syrup off.
                .save(output);

        // Cookies
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_COOKIE.get(), 8)
                .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(Items.PINK_PETALS), has(Items.PINK_PETALS))
                .unlockedBy(getHasName(Items.CHERRY_SAPLING), has(Items.CHERRY_SAPLING))
                .unlockedBy(getHasName(Items.CHERRY_LEAVES), has(Items.CHERRY_LEAVES))
                .save(cherryBlossomOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.COFFEE_COOKIE.get(), 8)
                .requires(ModTags.Items.COFFEE_INGREDIENTS)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(ModItems.ROASTED_COFFEE_BEANS.get()), has(ModItems.ROASTED_COFFEE_BEANS.get()))
                .save(coffeeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SYRUP_COOKIE.get(), 8)
                .requires(ModTags.Items.SYRUP)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(ModItems.SYRUP.get()), has(ModItems.SYRUP.get()))
                .save(syrupOutput);

        // Pies
        pieRecipes(syrupOutput, ModItems.SYRUP_CHEESECAKE, ModItems.SYRUP_CHEESECAKE_SLICE, Ingredient.of(ModTags.Items.SYRUP), " T ");
        pieRecipes(cherryBlossomOutput, ModItems.CHERRY_BLOSSOM_CHEESECAKE, ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE, Ingredient.of(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS), "TTT");
        pieRecipes(coffeeOutput, ModItems.COFFEE_CHEESECAKE, ModItems.COFFEE_CHEESECAKE_SLICE, Ingredient.of(ModTags.Items.COFFEE_FOOD_INGREDIENTS), " T ");

        // Pancakes
        pancakeRecipes(pancakesAndSyrupOutput, ModItems.PANCAKES, ModItems.PANCAKE, Ingredient.of(ModTags.Items.SYRUP), Ingredient.of(Items.SUGAR));
        pancakeRecipes(pancakesOutput, ModItems.HONEY_PANCAKES, ModItems.HONEY_PANCAKE, Ingredient.of(Items.HONEY_BOTTLE), Ingredient.of(Items.SWEET_BERRIES), Ingredient.of(Items.SUGAR));
        pancakeRecipes(pancakesOutput, ModItems.CHOCOLATE_PANCAKES, ModItems.CHOCOLATE_PANCAKE, Ingredient.of(ForgeTags.MILK), Ingredient.of(Items.COCOA_BEANS));
        pancakeRecipes(pancakesOutput, ModItems.VEGETABLE_PANCAKES, ModItems.VEGETABLE_PANCAKE, Ingredient.of(ForgeTags.MILK), Ingredient.of(ForgeTags.VEGETABLES), Ingredient.of(ForgeTags.SALAD_INGREDIENTS));
        pancakeRecipes(cherryBlossomPancakesOutput, ModItems.CHERRY_BLOSSOM_PANCAKES, ModItems.CHERRY_BLOSSOM_PANCAKE, Ingredient.of(ForgeTags.MILK), Ingredient.of(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS));
        pancakeRecipes(pancakesAndSyrupOutput, ModItems.PUMPKIN_PANCAKES, ModItems.PUMPKIN_PANCAKE, Ingredient.of(ModTags.Items.SYRUP), Ingredient.of(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get()));
        pancakeRecipes(coffeePancakesOutput, ModItems.COFFEE_PANCAKES, ModItems.COFFEE_PANCAKE, Ingredient.of(ForgeTags.MILK), Ingredient.of(ModTags.Items.COFFEE_INGREDIENTS));

        // Cotton
        oneToOne(cottonOutput, RecipeCategory.MISC, ModItems.COTTON_BOLL.get(), Items.STRING, 1);
        horizontalRecipe(cottonOutput, RecipeCategory.MISC, ModItems.COTTON_BOLL.get(), Items.PAPER, 3);
        twoBytwo(cottonOutput, RecipeCategory.MISC, ModItems.COTTON_BOLL.get(), vectorwing.farmersdelight.common.registry.ModItems.CANVAS.get(), 1);
        storageItemRecipes(cottonOutput, RecipeCategory.MISC, ModItems.COTTON_SEEDS.get(), ModItems.COTTON_SEEDS_BAG.get());
        storageItemRecipes(cottonOutput, RecipeCategory.MISC, ModItems.COTTON_BOLL.get(), ModItems.COTTON_BOLL_CRATE.get());

        // Bell peppers
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_GREEN.get(), Items.GREEN_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_YELLOW.get(), Items.YELLOW_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_RED.get(), Items.RED_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_ORANGE.get(), Items.ORANGE_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_WHITE.get(), Items.WHITE_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_PINK.get(), Items.PINK_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_BLUE.get(), Items.BLUE_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_PURPLE.get(), Items.PURPLE_DYE, 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_BLACK.get(), Items.BLACK_DYE, 1);

        // Bell pepper blocks: only 3x3 slices -> block. The reverse (block -> 9 slices) is cutting-board only.
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_GREEN.get(), ModItems.BELL_PEPPER_GREEN_BLOCK.get());
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_YELLOW.get(), ModItems.BELL_PEPPER_YELLOW_BLOCK.get());
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_RED.get(), ModItems.BELL_PEPPER_RED_BLOCK.get());
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_ORANGE.get(), ModItems.BELL_PEPPER_ORANGE_BLOCK.get());
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_WHITE.get(), ModItems.BELL_PEPPER_WHITE_BLOCK.get());
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_PINK.get(), ModItems.BELL_PEPPER_PINK_BLOCK.get());
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_BLUE.get(), ModItems.BELL_PEPPER_BLUE_BLOCK.get());
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_PURPLE.get(), ModItems.BELL_PEPPER_PURPLE_BLOCK.get());
        compactingRecipe(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_BLACK.get(), ModItems.BELL_PEPPER_BLACK_BLOCK.get());

        // Bell pepper slice -> seeds (1 slice = 1 seed of the matching crop)
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_GREEN.get(), ModItems.BELL_PEPPER_SEEDS.get(), 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_YELLOW.get(), ModItems.BELL_PEPPER_SEEDS.get(), 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_RED.get(), ModItems.BELL_PEPPER_SEEDS.get(), 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_ORANGE.get(), ModItems.PALE_BELL_PEPPER_SEEDS.get(), 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_WHITE.get(), ModItems.PALE_BELL_PEPPER_SEEDS.get(), 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_PINK.get(), ModItems.PALE_BELL_PEPPER_SEEDS.get(), 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_BLUE.get(), ModItems.DARK_BELL_PEPPER_SEEDS.get(), 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_PURPLE.get(), ModItems.DARK_BELL_PEPPER_SEEDS.get(), 1);
        oneToOne(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SLICE_BLACK.get(), ModItems.DARK_BELL_PEPPER_SEEDS.get(), 1);

        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_SEEDS.get(), ModItems.BELL_PEPPER_SEEDS_BAG.get());
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.PALE_BELL_PEPPER_SEEDS.get(), ModItems.PALE_BELL_PEPPER_SEEDS_BAG.get());
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.DARK_BELL_PEPPER_SEEDS.get(), ModItems.DARK_BELL_PEPPER_SEEDS_BAG.get());
        storageItemRecipes(calamariOutput, RecipeCategory.MISC, ModItems.CALAMARI.get(), ModItems.CALAMARI_CRATE.get());
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_GREEN.get(), ModItems.BELL_PEPPER_GREEN_CRATE.get());
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_YELLOW.get(), ModItems.BELL_PEPPER_YELLOW_CRATE.get());
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_RED.get(), ModItems.BELL_PEPPER_RED_CRATE.get());
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_ORANGE.get(), ModItems.BELL_PEPPER_ORANGE_CRATE.get());
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_WHITE.get(), ModItems.BELL_PEPPER_WHITE_CRATE.get());
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_PINK.get(), ModItems.BELL_PEPPER_PINK_CRATE.get());
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_BLUE.get(), ModItems.BELL_PEPPER_BLUE_CRATE.get());
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_PURPLE.get(), ModItems.BELL_PEPPER_PURPLE_CRATE.get());
        storageItemRecipes(bellPepperOutput, RecipeCategory.MISC, ModItems.BELL_PEPPER_BLACK.get(), ModItems.BELL_PEPPER_BLACK_CRATE.get());

        // Coffee
        storageItemRecipes(coffeeOutput, RecipeCategory.MISC, ModItems.COFFEE_BEANS.get(), ModItems.COFFEE_BEANS_BAG.get());
        storageItemRecipes(coffeeOutput, RecipeCategory.MISC, ModItems.ROASTED_COFFEE_BEANS.get(), ModItems.ROASTED_COFFEE_BEANS_BAG.get());

        oneToOne(coffeeOutput, RecipeCategory.MISC, ModItems.COFFEE_BEANS.get(), Items.YELLOW_DYE, 1);
        oneToOne(coffeeOutput, RecipeCategory.MISC, ModItems.ROASTED_COFFEE_BEANS.get(), Items.BROWN_DYE, 1);
        foodCookingRecipes(coffeeOutput, ModItems.COFFEE_BEANS.get(), ModItems.ROASTED_COFFEE_BEANS.get(), FOOD_COOKING_EXP);

        var goldenCoffeeBeansIngredient = Ingredient.of(ModItems.COFFEE_BEANS.get(), ModItems.ROASTED_COFFEE_BEANS.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.GOLDEN_COFFEE_BEANS.get(), 1)
                .pattern("GGG")
                .pattern("GCG")
                .pattern("GGG")
                .define('G', Items.GOLD_NUGGET)
                .define('C', goldenCoffeeBeansIngredient)
                .unlockedBy(getHasName(ModItems.COFFEE_BEANS.get()), has(ModItems.COFFEE_BEANS.get()))
                .unlockedBy(getHasName(ModItems.ROASTED_COFFEE_BEANS.get()), has(ModItems.ROASTED_COFFEE_BEANS.get()))
                .save(coffeeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MILK_COFFEE.get(), 1)
                .requires(ModItems.COFFEE.get())
                .requires(ForgeTags.MILK)
                .unlockedBy(getHasName(ModItems.COFFEE.get()), has(ModItems.COFFEE.get()))
                .save(coffeeOutput, getRecipeName(ModItems.COFFEE.get(), ModItems.MILK_COFFEE.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHOCOLATE_COFFEE.get(), 1)
                .requires(ModItems.MILK_COFFEE.get())
                .requires(Items.COCOA_BEANS)
                .requires(Items.COCOA_BEANS)
                .unlockedBy(getHasName(ModItems.MILK_COFFEE.get()), has(ModItems.MILK_COFFEE.get()))
                .save(coffeeOutput, getRecipeName(ModItems.MILK_COFFEE.get(), ModItems.CHOCOLATE_COFFEE.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHOCOLATE_COFFEE.get(), 1)
                .requires(ModItems.COFFEE.get())
                .requires(ForgeTags.MILK)
                .requires(Items.COCOA_BEANS)
                .requires(Items.COCOA_BEANS)
                .unlockedBy(getHasName(ModItems.COFFEE.get()), has(ModItems.COFFEE.get()))
                .save(coffeeOutput, getRecipeName(ModItems.COFFEE.get(), ModItems.CHOCOLATE_COFFEE.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HONEY_COFFEE.get(), 1)
                .requires(ModItems.MILK_COFFEE.get())
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy(getHasName(ModItems.MILK_COFFEE.get()), has(ModItems.MILK_COFFEE.get()))
                .save(coffeeOutput, getRecipeName(ModItems.MILK_COFFEE.get(), ModItems.HONEY_COFFEE.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HONEY_COFFEE.get(), 1)
                .requires(ModItems.COFFEE.get())
                .requires(ForgeTags.MILK)
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy(getHasName(ModItems.COFFEE.get()), has(ModItems.COFFEE.get()))
                .save(coffeeOutput, getRecipeName(ModItems.COFFEE.get(), ModItems.HONEY_COFFEE.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SYRUP_COFFEE.get(), 1)
                .requires(ModItems.MILK_COFFEE.get())
                .requires(ModTags.Items.SYRUP)
                .unlockedBy(getHasName(ModItems.MILK_COFFEE.get()), has(ModItems.MILK_COFFEE.get()))
                .save(coffeeAndSyrupOutput, getRecipeName(ModItems.MILK_COFFEE.get(), ModItems.SYRUP_COFFEE.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SYRUP_COFFEE.get(), 1)
                .requires(ModItems.COFFEE.get())
                .requires(ForgeTags.MILK)
                .requires(ModTags.Items.SYRUP)
                .unlockedBy(getHasName(ModItems.COFFEE.get()), has(ModItems.COFFEE.get()))
                .save(coffeeAndSyrupOutput, getRecipeName(ModItems.COFFEE.get(), ModItems.SYRUP_COFFEE.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.PUMPKIN_COFFEE.get(), 1)
                .requires(ModItems.MILK_COFFEE.get())
                .requires(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get())
                .unlockedBy(getHasName(ModItems.MILK_COFFEE.get()), has(ModItems.MILK_COFFEE.get()))
                .save(coffeeOutput, getRecipeName(ModItems.MILK_COFFEE.get(), ModItems.PUMPKIN_COFFEE.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.PUMPKIN_COFFEE.get(), 1)
                .requires(ModItems.COFFEE.get())
                .requires(ForgeTags.MILK)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get())
                .unlockedBy(getHasName(ModItems.COFFEE.get()), has(ModItems.COFFEE.get()))
                .save(coffeeOutput, getRecipeName(ModItems.COFFEE.get(), ModItems.PUMPKIN_COFFEE.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_COFFEE.get(), 1)
                .requires(ModItems.MILK_COFFEE.get())
                .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .unlockedBy(getHasName(ModItems.MILK_COFFEE.get()), has(ModItems.MILK_COFFEE.get()))
                .save(coffeeAndCherryBlossomOutput, getRecipeName(ModItems.MILK_COFFEE.get(), ModItems.CHERRY_BLOSSOM_COFFEE.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_COFFEE.get(), 1)
                .requires(ModItems.COFFEE.get())
                .requires(ForgeTags.MILK)
                .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .unlockedBy(getHasName(ModItems.COFFEE.get()), has(ModItems.COFFEE.get()))
                .save(coffeeAndCherryBlossomOutput, getRecipeName(ModItems.COFFEE.get(), ModItems.CHERRY_BLOSSOM_COFFEE.get()));

        // Syrup-based recipes
        oneToOne(syrupOutput, RecipeCategory.MISC, ModItems.SYRUP.get(), Items.SUGAR, 3);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SYRUP_SANDWICH.get(), 1)
                .requires(ForgeTags.BREAD)
                .requires(ModTags.Items.SYRUP)
                .requires(Items.SUGAR)
                .unlockedBy(getHasName(ModItems.SYRUP.get()), has(ModItems.SYRUP.get()))
                .save(syrupOutput);

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
                .save(royaleOutput);

        bellPepperMedleyRecipe(bellPepperOutput, ModItems.BELL_PEPPER_MEDLEY.get(),
                ModItems.STUFFED_BELL_PEPPER_GREEN.get(), ModItems.STUFFED_BELL_PEPPER_YELLOW.get(), ModItems.STUFFED_BELL_PEPPER_RED.get());
        bellPepperMedleyRecipe(bellPepperOutput, ModItems.PALE_BELL_PEPPER_MEDLEY.get(),
                ModItems.STUFFED_BELL_PEPPER_ORANGE.get(), ModItems.STUFFED_BELL_PEPPER_WHITE.get(), ModItems.STUFFED_BELL_PEPPER_PINK.get());
        bellPepperMedleyRecipe(bellPepperOutput, ModItems.DARK_BELL_PEPPER_MEDLEY.get(),
                ModItems.STUFFED_BELL_PEPPER_BLUE.get(), ModItems.STUFFED_BELL_PEPPER_PURPLE.get(), ModItems.STUFFED_BELL_PEPPER_BLACK.get());
    }

    // One stuffed bell pepper of each colour in the variant, plus a bowl.
    private static void bellPepperMedleyRecipe(Consumer<FinishedRecipe> recipeOutput, ItemLike medley, ItemLike first, ItemLike second, ItemLike third) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, medley)
                .requires(first)
                .requires(second)
                .requires(third)
                .requires(Items.BOWL)
                .unlockedBy("has_stuffed_bell_pepper", InventoryChangeTrigger.TriggerInstance.hasItems(
                        first, second, third))
                .save(recipeOutput);
    }

    private void buildCuttingRecipes(@NotNull Consumer<FinishedRecipe> output) {
        Consumer<FinishedRecipe> cottonOutput = gated(output, Configuration.ENABLE_COTTON_ID);
        Consumer<FinishedRecipe> coffeeOutput = gated(output, Configuration.ENABLE_COFFEE_ID);
        Consumer<FinishedRecipe> bellPepperOutput = gated(output, Configuration.ENABLE_BELL_PEPPERS_ID);
        Consumer<FinishedRecipe> calamariOutput = gated(output, Configuration.SQUIDS_DROP_CALAMARI_ID);
        Consumer<FinishedRecipe> cherryBlossomOutput = gated(output, Configuration.ENABLE_CHERRY_BLOSSOM_FOODS_ID);
        Consumer<FinishedRecipe> syrupOutput = gated(output, Configuration.ENABLE_SYRUP_FOODS_ID);
        Consumer<FinishedRecipe> potatoSlicesOutput = gated(output, Configuration.ENABLE_POTATO_SLICES_ID);

        // Cotton
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_COTTON.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.COTTON_SEEDS.get(), 1)
                .addResultWithChance(ModItems.COTTON_BOLL.get(), 0.3F)
                .addResultWithChance(Items.WHITE_DYE, 0.1F)
                .save(cottonOutput, getCuttingPath(ModItems.WILD_COTTON.get()));

        // Bell pepper
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_BELL_PEPPERS.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.BELL_PEPPER_SEEDS.get(), 1)
                .addResultWithChance(ModItems.BELL_PEPPER_RED.get(), 0.3F)
                .addResultWithChance(Items.RED_DYE, 0.1F)
                .save(bellPepperOutput, getCuttingPath(ModItems.WILD_BELL_PEPPERS.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_PALE_BELL_PEPPERS.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.PALE_BELL_PEPPER_SEEDS.get(), 1)
                .addResultWithChance(ModItems.BELL_PEPPER_PINK.get(), 0.3F)
                .addResultWithChance(Items.PINK_DYE, 0.1F)
                .save(bellPepperOutput, getCuttingPath(ModItems.WILD_PALE_BELL_PEPPERS.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_DARK_BELL_PEPPERS.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.DARK_BELL_PEPPER_SEEDS.get(), 1)
                .addResultWithChance(ModItems.BELL_PEPPER_PURPLE.get(), 0.3F)
                .addResultWithChance(Items.PURPLE_DYE, 0.1F)
                .save(bellPepperOutput, getCuttingPath(ModItems.WILD_DARK_BELL_PEPPERS.get()));

        // Bell pepper slices
        bellPepperSlices(bellPepperOutput, ModItems.BELL_PEPPER_GREEN.get(), ModItems.BELL_PEPPER_SLICE_GREEN.get(), ModItems.BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.BELL_PEPPER_YELLOW.get(), ModItems.BELL_PEPPER_SLICE_YELLOW.get(), ModItems.BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.BELL_PEPPER_RED.get(), ModItems.BELL_PEPPER_SLICE_RED.get(), ModItems.BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.BELL_PEPPER_ORANGE.get(), ModItems.BELL_PEPPER_SLICE_ORANGE.get(), ModItems.PALE_BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.BELL_PEPPER_WHITE.get(), ModItems.BELL_PEPPER_SLICE_WHITE.get(), ModItems.PALE_BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.BELL_PEPPER_PINK.get(), ModItems.BELL_PEPPER_SLICE_PINK.get(), ModItems.PALE_BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.BELL_PEPPER_BLUE.get(), ModItems.BELL_PEPPER_SLICE_BLUE.get(), ModItems.DARK_BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.BELL_PEPPER_PURPLE.get(), ModItems.BELL_PEPPER_SLICE_PURPLE.get(), ModItems.DARK_BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.BELL_PEPPER_BLACK.get(), ModItems.BELL_PEPPER_SLICE_BLACK.get(), ModItems.DARK_BELL_PEPPER_SEEDS.get());

        // Roasted bell pepper slices
        bellPepperSlices(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_GREEN.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_GREEN.get(), ModItems.BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_YELLOW.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_YELLOW.get(), ModItems.BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_RED.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_RED.get(), ModItems.BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_ORANGE.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_ORANGE.get(), ModItems.PALE_BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_WHITE.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_WHITE.get(), ModItems.PALE_BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_PINK.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_PINK.get(), ModItems.PALE_BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_BLUE.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_BLUE.get(), ModItems.DARK_BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_PURPLE.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_PURPLE.get(), ModItems.DARK_BELL_PEPPER_SEEDS.get());
        bellPepperSlices(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_BLACK.get(), ModItems.ROASTED_BELL_PEPPER_SLICE_BLACK.get(), ModItems.DARK_BELL_PEPPER_SEEDS.get());

        // Giant bell pepper blocks back into their 9 slices. The forward recipe is 3x3 crafting.
        giantBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_GREEN_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_GREEN.get());
        giantBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_YELLOW_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_YELLOW.get());
        giantBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_RED_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_RED.get());
        giantBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_ORANGE_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_ORANGE.get());
        giantBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_WHITE_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_WHITE.get());
        giantBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_PINK_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_PINK.get());
        giantBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_BLUE_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_BLUE.get());
        giantBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_PURPLE_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_PURPLE.get());
        giantBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_BLACK_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_BLACK.get());

        // Coffee
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_COFFEE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.COFFEE_BEANS.get(), 1)
                .addResultWithChance(ModItems.COFFEE_BEANS.get(), 0.3F)
                .addResultWithChance(Items.YELLOW_DYE, 0.1F)
                .save(coffeeOutput, ModItems.WILD_COFFEE.getId());

        // Food
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POTATO), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.POTATO_SLICES.get(), 2)
                .save(potatoSlicesOutput, getCuttingPath(ModItems.POTATO_SLICES.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BAKED_POTATO), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.BAKED_POTATO_SLICES.get(), 2)
                .save(potatoSlicesOutput, getCuttingPath(ModItems.BAKED_POTATO_SLICES.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CALAMARI.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.CALAMARI_SLICE.get(), 2)
                .addResult(Items.BONE_MEAL)
                .save(calamariOutput, getCuttingPath(ModItems.CALAMARI_SLICE.get()));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.COOKED_CALAMARI.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.COOKED_CALAMARI_SLICE.get(), 2)
                .addResult(Items.BONE_MEAL)
                .save(calamariOutput, getCuttingPath(ModItems.COOKED_CALAMARI_SLICE.get()));

        // Pie
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CHERRY_BLOSSOM_CHEESECAKE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE.get(), 4)
                .save(cherryBlossomOutput, ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE.getId());
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.SYRUP_CHEESECAKE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.SYRUP_CHEESECAKE_SLICE.get(), 4)
                .save(syrupOutput, ModItems.SYRUP_CHEESECAKE_SLICE.getId());
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.COFFEE_CHEESECAKE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.COFFEE_CHEESECAKE_SLICE.get(), 4)
                .save(coffeeOutput, ModItems.COFFEE_CHEESECAKE_SLICE.getId());

        // Salvaging
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ItemTags.WOOL), Ingredient.of(Tags.Items.SHEARS), Items.STRING, 2)
                .save(output, getCuttingPath("wool"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ItemTags.WOOL_CARPETS), Ingredient.of(Tags.Items.SHEARS), Items.STRING, 1)
                .save(output, getCuttingPath("wool_carpet"));
    }

    private void buildCookingRecipes(@NotNull Consumer<FinishedRecipe> output) {
        Consumer<FinishedRecipe> coffeeOutput = gated(output, Configuration.ENABLE_COFFEE_ID);
        Consumer<FinishedRecipe> bellPepperOutput = gated(output, Configuration.ENABLE_BELL_PEPPERS_ID);
        Consumer<FinishedRecipe> calamariOutput = gated(output, Configuration.SQUIDS_DROP_CALAMARI_ID);
        Consumer<FinishedRecipe> syrupOutput = gated(output, Configuration.ENABLE_SYRUP_FOODS_ID);
        // Cooking oil and everything fried with it are gated by the enable_fried_foods toggle.
        Consumer<FinishedRecipe> friedOutput = gated(output, Configuration.ENABLE_FRIED_FOODS_ID);
        // Fried Calamari needs both Cooking Oil and Calamari.
        Consumer<FinishedRecipe> friedAndCalamariOutput = gated(output,
                Configuration.ENABLE_FRIED_FOODS_ID, Configuration.SQUIDS_DROP_CALAMARI_ID);
        Consumer<FinishedRecipe> coffeeAndSyrupOutput = gated(output,
                Configuration.ENABLE_COFFEE_ID, Configuration.ENABLE_SYRUP_FOODS_ID);
        Consumer<FinishedRecipe> coffeeAndCherryBlossomOutput = gated(output,
                Configuration.ENABLE_COFFEE_ID, Configuration.ENABLE_CHERRY_BLOSSOM_FOODS_ID);
        // Batter feeds both fried foods and pancakes, so it only disappears once both are off.
        Consumer<FinishedRecipe> batterOutput = withConditions(output, or(
                new ConfigBooleanCondition(Configuration.ENABLE_FRIED_FOODS_ID),
                new ConfigBooleanCondition(Configuration.ENABLE_PANCAKES_ID)));

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
                .save(friedOutput, getCookingPath(ModItems.COOKING_OIL.get()));

        // Batter
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BATTER.get(), 2, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP, Items.BOWL)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(Tags.Items.EGGS)
                .addIngredient(Items.WHEAT)
                .addIngredient(Items.WHEAT)
                .unlockedByAnyIngredient(Items.MILK_BUCKET, vectorwing.farmersdelight.common.registry.ModItems.MILK_BOTTLE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(batterOutput, getCookingPath(ModItems.BATTER.get()));

        // Syrup
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.SYRUP.get(), 1, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.SYRUP_INGREDIENTS)
                .addIngredient(Items.SUGAR)
                .unlockedByAnyIngredient(Items.APPLE, Items.BEETROOT, Items.SUGAR)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(syrupOutput, ModItems.SYRUP.getId());

        // Fried Dough
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_DOUGH.get(), 1, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ForgeTags.DOUGH)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(friedOutput, ModItems.FRIED_DOUGH.getId());

        // Fried Dumplings
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_DUMPLINGS.get(), 2, CookingRecipes.FAST_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.DUMPLINGS.get(), 2)
                .unlockedByAnyIngredient(vectorwing.farmersdelight.common.registry.ModItems.DUMPLINGS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(friedOutput, ModItems.FRIED_DUMPLINGS.getId());

        // Spring Rolls
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.SPRING_ROLLS.get(), 2, CookingRecipes.FAST_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ForgeTags.DOUGH)
                .addIngredient(ForgeTags.SALAD_INGREDIENTS)
                .addIngredient(ModTags.Items.SPRING_ROLL_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(friedOutput, getCookingPath(ModItems.SPRING_ROLLS.get()));

        // Fruit Beignet
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRUIT_BEIGNET.get(), 1, CookingRecipes.FAST_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ForgeTags.DOUGH)
                .addIngredient(ForgeTags.BERRIES)
                .addIngredient(Items.SUGAR)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(friedOutput, getCookingPath(ModItems.FRUIT_BEIGNET.get()));

        // Fried Calamari
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_CALAMARI.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ModItems.BATTER.get())
                .addIngredient(ForgeTags.RAW_FISHES_CALAMARI)
                .addIngredient(ForgeTags.VEGETABLES_TOMATO)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(friedAndCalamariOutput, getCookingPath(ModItems.FRIED_CALAMARI.get()));

        // Fried Chicken
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_CHICKEN.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ModItems.BATTER.get())
                .addIngredient(ForgeTags.RAW_CHICKEN)
                .addIngredient(ForgeTags.VEGETABLES_ONION)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(friedOutput, getCookingPath(ModItems.FRIED_CHICKEN.get()));

        // Fried Mushrooms
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_MUSHROOMS.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(Items.BROWN_MUSHROOM)
                .addIngredient(Items.RED_MUSHROOM)
                .addIngredient(ForgeTags.VEGETABLES_ONION)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(friedOutput, getCookingPath(ModItems.FRIED_MUSHROOMS.get()));

        // Bell Pepper Soup
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BELL_PEPPER_SOUP.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN.get(), ModItems.BELL_PEPPER_YELLOW.get(), ModItems.BELL_PEPPER_RED.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput, getCookingPath(ModItems.BELL_PEPPER_SOUP.get()));

        // Stuffed Bell Peppers
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_GREEN.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_GREEN.get())
                .addIngredient(ForgeTags.GRAIN_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput, getCookingPath(ModItems.STUFFED_BELL_PEPPER_GREEN.get()));
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_YELLOW.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_YELLOW.get())
                .addIngredient(ForgeTags.GRAIN_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_YELLOW.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput, getCookingPath(ModItems.STUFFED_BELL_PEPPER_YELLOW.get()));
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.STUFFED_BELL_PEPPER_RED.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModItems.BELL_PEPPER_RED.get())
                .addIngredient(ForgeTags.GRAIN_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_RED.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput, getCookingPath(ModItems.STUFFED_BELL_PEPPER_RED.get()));
        stuffedBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_ORANGE.get(), ModItems.STUFFED_BELL_PEPPER_ORANGE.get());
        stuffedBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_WHITE.get(), ModItems.STUFFED_BELL_PEPPER_WHITE.get());
        stuffedBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_PINK.get(), ModItems.STUFFED_BELL_PEPPER_PINK.get());
        stuffedBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_BLUE.get(), ModItems.STUFFED_BELL_PEPPER_BLUE.get());
        stuffedBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_PURPLE.get(), ModItems.STUFFED_BELL_PEPPER_PURPLE.get());
        stuffedBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_BLACK.get(), ModItems.STUFFED_BELL_PEPPER_BLACK.get());

        // Calamari Soup
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.CALAMARI_SOUP.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ForgeTags.RAW_FISHES_CALAMARI)
                .addIngredient(ForgeTags.VEGETABLES_POTATO)
                .addIngredient(ForgeTags.VEGETABLES_ONION)
                .addIngredient(ForgeTags.MILK)
                .unlockedByAnyIngredient(ModItems.CALAMARI.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(calamariOutput, getCookingPath(ModItems.CALAMARI_SOUP.get()));

        // Fried Fish - any raw fish, plus Batter and Cooking Oil.
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FRIED_FISH.get(), 1, CookingRecipes.FAST_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .addIngredient(ModItems.BATTER.get())
                .addIngredient(ForgeTags.RAW_FISHES)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(friedOutput, getCookingPath(ModItems.FRIED_FISH.get()));

        // Pumpkin Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.PUMPKIN_COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get())
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(coffeeOutput, getCookingPath(ModItems.PUMPKIN_COFFEE.get()));

        // Cherry Blossom Coffee - in both the coffee and cherry blossom families.
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.CHERRY_BLOSSOM_COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(coffeeAndCherryBlossomOutput, getCookingPath(ModItems.CHERRY_BLOSSOM_COFFEE.get()));

        // Bell Pepper Pasta
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BELL_PEPPER_PASTA.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ForgeTags.PASTA)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .addIngredient(ForgeTags.VEGETABLES_BELL_PEPPER)
                .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN.get(), ModItems.BELL_PEPPER_YELLOW.get(), ModItems.BELL_PEPPER_RED.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(bellPepperOutput, getCookingPath(ModItems.BELL_PEPPER_PASTA.get()));

        // Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(coffeeOutput, ModItems.COFFEE.getId());

        // Milk Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.MILK_COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ForgeTags.MILK)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(coffeeOutput, ModItems.MILK_COFFEE.getId());

        // Chocolate Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.CHOCOLATE_COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(Items.COCOA_BEANS)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(coffeeOutput, ModItems.CHOCOLATE_COFFEE.getId());

        // Honey Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.HONEY_COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(Items.HONEY_BOTTLE, 1)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(coffeeOutput, ModItems.HONEY_COFFEE.getId());

        // Syrup Coffee
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.SYRUP_COFFEE.get(), 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(ModTags.Items.SYRUP)
                .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .save(coffeeAndSyrupOutput, ModItems.SYRUP_COFFEE.getId());

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
                .save(coffeeOutput, ModItems.DARK_COFFEE.getId());

        // Coffee-Braised Beef
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COFFEE_BRAISED_BEEF.get(), 1, CookingRecipes.SLOW_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
                .addIngredient(ForgeTags.RAW_BEEF)
                .addIngredient(ModTags.Items.COFFEE_FOOD_INGREDIENTS)
                .addIngredient(ForgeTags.VEGETABLES_CARROT)
                .addIngredient(ForgeTags.VEGETABLES_POTATO)
                .unlockedByAnyIngredient(ModItems.COFFEE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(coffeeOutput, ModItems.COFFEE_BRAISED_BEEF.getId());
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

        // Fried Egg (our Cooking Oil variant - gated by the enable_fried_foods toggle).
        // The other overrides below stay ungated: they only add potato slices as an
        // alternative ingredient, so they still work when that family is off.
        Consumer<FinishedRecipe> friedOutput = gated(output, Configuration.ENABLE_FRIED_FOODS_ID);
        CookingPotRecipeBuilder.cookingPotRecipe(vectorwing.farmersdelight.common.registry.ModItems.FRIED_EGG.get(), 1, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP)
                .addIngredient(Items.EGG)
                .addIngredient(ModTags.Items.COOKING_OIL)
                .unlockedByAnyIngredient(ModItems.COOKING_OIL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .save(friedOutput, getRecipeName(ModItems.COOKING_OIL.get(), vectorwing.farmersdelight.common.registry.ModItems.FRIED_EGG.get()));

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

    /** A bell pepper stuffed with rice and one of the filling ingredients. */
    private static void stuffedBellPepper(Consumer<FinishedRecipe> recipeOutput, ItemLike pepper, ItemLike stuffed) {
        CookingPotRecipeBuilder.cookingPotRecipe(stuffed, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
                .addIngredient(pepper)
                .addIngredient(ForgeTags.GRAIN_RICE)
                .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                .unlockedByAnyIngredient(pepper)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput, getCookingPath(stuffed.asItem()));
    }

    /** A whole bell pepper (raw or roasted) cut into 2 slices, occasionally yielding a seed. */
    private static void bellPepperSlices(Consumer<FinishedRecipe> recipeOutput, ItemLike pepper, ItemLike slice, ItemLike seed) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(pepper), Ingredient.of(ForgeTags.TOOLS_KNIVES), slice, 2)
                .addResultWithChance(seed, 0.1F)
                .save(recipeOutput, getCuttingPath(pepper.asItem()));
    }

    /** A giant bell pepper block cut back into its 9 slices. */
    private static void giantBellPepper(Consumer<FinishedRecipe> recipeOutput, ItemLike block, ItemLike slice) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(block), Ingredient.of(ForgeTags.TOOLS_KNIVES), slice, 9)
                .save(recipeOutput, getCuttingPath(block.asItem()));
    }

    /** One-way 3x3 compacting, for blocks whose reverse recipe is cutting-board only. */
    protected static void compactingRecipe(Consumer<FinishedRecipe> recipeOutput, RecipeCategory category, ItemLike item, ItemLike storageItem) {
        ShapedRecipeBuilder.shaped(category, storageItem)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', item)
                .unlockedBy(getHasName(item), has(item))
                .save(recipeOutput, getRecipeName(item, storageItem));
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

    protected static void pieRecipes(@NotNull Consumer<FinishedRecipe> recipeOutput, @NotNull RegistryObject<Item> pieBlock, @NotNull RegistryObject<Item> sliceItem, Ingredient topping, String toppingRow) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, pieBlock.get(), 1)
                .pattern(toppingRow)
                .pattern("SSS")
                .pattern("MCM")
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
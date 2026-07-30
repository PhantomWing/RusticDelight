package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.block.custom.PancakeBlock;
import com.phantomwing.rusticdelight.condition.ConfigBooleanCondition;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tags.CommonTags;
import com.phantomwing.rusticdelight.tags.ModTags;
import com.phantomwing.rusticdelight.util.ItemUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.crafting.CookingPotBookCategory;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public static final int FAST_COOKING = 100;
    public static final int NORMAL_COOKING = 200;
    public static final int SLOW_COOKING = 400;
    public static final float SMALL_EXP = 0.35F;
    public static final float MEDIUM_EXP = 1.0F;
    public static final float LARGE_EXP = 2.0F;

    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    /** A recipe output that only loads while every one of the given boolean config options is on. */
    private RecipeOutput family(RecipeOutput output, String... settingIds) {
        ConfigBooleanCondition[] conditions = new ConfigBooleanCondition[settingIds.length];
        for (int i = 0; i < settingIds.length; i++) {
            conditions[i] = new ConfigBooleanCondition(settingIds[i]);
        }
        return withConditions(output, conditions);
    }

    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider registryLookup, @NotNull RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            final HolderGetter<Item> holderGetter = registryLookup.lookupOrThrow(Registries.ITEM);

            @Override
            public void buildRecipes() {
                buildCraftingRecipes(output);
                buildCuttingRecipes(output);
                buildCookingRecipes(output);
            }

            private void buildCraftingRecipes(@NotNull RecipeOutput output) {
                RecipeOutput bellPepperOutput = family(output, RusticDelightConfig.ENABLE_BELL_PEPPERS_ID);
                RecipeOutput calamariOutput = family(output, RusticDelightConfig.SQUIDS_DROP_CALAMARI_ID);
                RecipeOutput cherryBlossomOutput = family(output, RusticDelightConfig.ENABLE_CHERRY_BLOSSOM_FOODS_ID);
                RecipeOutput cherryBlossomPancakesOutput = family(output,
                        RusticDelightConfig.ENABLE_CHERRY_BLOSSOM_FOODS_ID, RusticDelightConfig.ENABLE_PANCAKES_ID);
                RecipeOutput coffeeAndCherryBlossomOutput = family(output,
                        RusticDelightConfig.ENABLE_COFFEE_ID, RusticDelightConfig.ENABLE_CHERRY_BLOSSOM_FOODS_ID);
                RecipeOutput coffeeAndSyrupOutput = family(output,
                        RusticDelightConfig.ENABLE_COFFEE_ID, RusticDelightConfig.ENABLE_SYRUP_FOODS_ID);
                RecipeOutput coffeeOutput = family(output, RusticDelightConfig.ENABLE_COFFEE_ID);
                RecipeOutput coffeePancakesOutput = family(output,
                        RusticDelightConfig.ENABLE_COFFEE_ID, RusticDelightConfig.ENABLE_PANCAKES_ID);
                RecipeOutput cottonOutput = family(output, RusticDelightConfig.ENABLE_COTTON_ID);
                RecipeOutput pancakesAndSyrupOutput = family(output,
                        RusticDelightConfig.ENABLE_PANCAKES_ID, RusticDelightConfig.ENABLE_SYRUP_FOODS_ID);
                RecipeOutput pancakesOutput = family(output, RusticDelightConfig.ENABLE_PANCAKES_ID);
                RecipeOutput potatoSlicesOutput = family(output, RusticDelightConfig.ENABLE_POTATO_SLICES_ID);
                RecipeOutput royaleOutput = family(output,
                        RusticDelightConfig.ENABLE_BELL_PEPPERS_ID, RusticDelightConfig.SQUIDS_DROP_CALAMARI_ID, RusticDelightConfig.ENABLE_CHERRY_BLOSSOM_FOODS_ID);
                RecipeOutput syrupOutput = family(output, RusticDelightConfig.ENABLE_SYRUP_FOODS_ID);

                // Bell pepper foods
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_GREEN, ModItems.ROASTED_BELL_PEPPER_GREEN, SMALL_EXP);
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_YELLOW, ModItems.ROASTED_BELL_PEPPER_YELLOW, SMALL_EXP);
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_RED, ModItems.ROASTED_BELL_PEPPER_RED, SMALL_EXP);

                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_ORANGE, ModItems.ROASTED_BELL_PEPPER_ORANGE, SMALL_EXP);
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_WHITE, ModItems.ROASTED_BELL_PEPPER_WHITE, SMALL_EXP);
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_PINK, ModItems.ROASTED_BELL_PEPPER_PINK, SMALL_EXP);
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_BLUE, ModItems.ROASTED_BELL_PEPPER_BLUE, SMALL_EXP);
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_PURPLE, ModItems.ROASTED_BELL_PEPPER_PURPLE, SMALL_EXP);
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_BLACK, ModItems.ROASTED_BELL_PEPPER_BLACK, SMALL_EXP);

                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_GREEN, ModItems.ROASTED_BELL_PEPPER_SLICE_GREEN, SMALL_EXP);
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_YELLOW, ModItems.ROASTED_BELL_PEPPER_SLICE_YELLOW, SMALL_EXP);
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_RED, ModItems.ROASTED_BELL_PEPPER_SLICE_RED, SMALL_EXP);
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_ORANGE, ModItems.ROASTED_BELL_PEPPER_SLICE_ORANGE, SMALL_EXP);
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_WHITE, ModItems.ROASTED_BELL_PEPPER_SLICE_WHITE, SMALL_EXP);
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_PINK, ModItems.ROASTED_BELL_PEPPER_SLICE_PINK, SMALL_EXP);
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_BLUE, ModItems.ROASTED_BELL_PEPPER_SLICE_BLUE, SMALL_EXP);
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_PURPLE, ModItems.ROASTED_BELL_PEPPER_SLICE_PURPLE, SMALL_EXP);
                foodCookingRecipes(bellPepperOutput, ModItems.BELL_PEPPER_SLICE_BLACK, ModItems.ROASTED_BELL_PEPPER_SLICE_BLACK, SMALL_EXP);


                shapeless(RecipeCategory.FOOD, ModItems.BELL_PEPPER_SOUP, 1)
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
                foodCookingRecipes(calamariOutput, ModItems.CALAMARI, ModItems.COOKED_CALAMARI, SMALL_EXP);
                foodCookingRecipes(calamariOutput, ModItems.CALAMARI_SLICE, ModItems.COOKED_CALAMARI_SLICE, SMALL_EXP);

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

                shapeless(RecipeCategory.FOOD, ModItems.CALAMARI_ROLL, 2)
                        .requires(ModTags.Items.CALAMARI_ROLL_INGREDIENTS)
                        .requires(ModTags.Items.CALAMARI_ROLL_INGREDIENTS)
                        .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                        .unlockedBy(getHasName(ModItems.CALAMARI_SLICE), has(ModItems.CALAMARI_SLICE))
                        .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()), has(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()))
                        .save(calamariOutput);

                shapeless(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_ROLL, 2)
                        .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                        .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                        .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                        .unlockedBy(getHasName(Items.PINK_PETALS), has(Items.PINK_PETALS))
                        .unlockedBy(getHasName(Items.CHERRY_SAPLING), has(Items.CHERRY_SAPLING))
                        .unlockedBy(getHasName(Items.CHERRY_LEAVES), has(Items.CHERRY_LEAVES))
                        .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()), has(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()))
                        .save(cherryBlossomOutput);

                // Potato
                foodCookingRecipes(potatoSlicesOutput, ModItems.POTATO_SLICES, ModItems.BAKED_POTATO_SLICES, SMALL_EXP);

                // Salads
                shapeless(RecipeCategory.FOOD, ModItems.POTATO_SALAD, 1)
                        .requires(Items.BOWL)
                        .requires(CommonTags.FOODS_POTATO)
                        .requires(CommonTags.FOODS_ONION)
                        .requires(CommonTags.FOODS_MILK)
                        .requires(CommonTags.EGGS)
                        .unlockedBy(getHasName(Items.POTATO), has(Items.POTATO))
                        .unlockedBy(getHasName(ModItems.POTATO_SLICES), has(ModItems.POTATO_SLICES))
                        .save(output);
                shapeless(RecipeCategory.FOOD, ModItems.SWEET_SALAD, 1)
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
                shapeless(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_COOKIE, 8)
                        .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                        .requires(Items.WHEAT)
                        .requires(Items.WHEAT)
                        .unlockedBy(getHasName(Items.PINK_PETALS), has(Items.PINK_PETALS))
                        .unlockedBy(getHasName(Items.CHERRY_SAPLING), has(Items.CHERRY_SAPLING))
                        .unlockedBy(getHasName(Items.CHERRY_LEAVES), has(Items.CHERRY_LEAVES))
                        .save(cherryBlossomOutput);
                shapeless(RecipeCategory.FOOD, ModItems.COFFEE_COOKIE, 8)
                        .requires(ModTags.Items.COFFEE_INGREDIENTS)
                        .requires(Items.WHEAT)
                        .requires(Items.WHEAT)
                        .unlockedBy(getHasName(ModItems.ROASTED_COFFEE_BEANS), has(ModItems.ROASTED_COFFEE_BEANS))
                        .save(coffeeOutput);
                shapeless(RecipeCategory.FOOD, ModItems.SYRUP_COOKIE, 8)
                        .requires(ModTags.Items.SYRUP)
                        .requires(Items.WHEAT)
                        .requires(Items.WHEAT)
                        .unlockedBy(getHasName(ModItems.SYRUP), has(ModItems.SYRUP))
                        .save(syrupOutput);

                // Pies
                pieRecipes(syrupOutput, ModItems.SYRUP_CHEESECAKE, ModItems.SYRUP_CHEESECAKE_SLICE, tagIngredient(ModTags.Items.SYRUP), " T ");
                pieRecipes(cherryBlossomOutput, ModItems.CHERRY_BLOSSOM_CHEESECAKE, ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE, tagIngredient(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS), "TTT");
                pieRecipes(coffeeOutput, ModItems.COFFEE_CHEESECAKE, ModItems.COFFEE_CHEESECAKE_SLICE, tagIngredient(ModTags.Items.COFFEE_FOOD_INGREDIENTS), " T ");

                // Pancakes
                pancakeRecipes(pancakesAndSyrupOutput, ModItems.PANCAKES, ModItems.PANCAKE, tagIngredient(ModTags.Items.SYRUP), Ingredient.of(Items.SUGAR));
                pancakeRecipes(pancakesOutput, ModItems.HONEY_PANCAKES, ModItems.HONEY_PANCAKE, Ingredient.of(Items.HONEY_BOTTLE), Ingredient.of(Items.SWEET_BERRIES), Ingredient.of(Items.SUGAR));
                pancakeRecipes(pancakesOutput, ModItems.CHOCOLATE_PANCAKES, ModItems.CHOCOLATE_PANCAKE, tagIngredient(CommonTags.FOODS_MILK), Ingredient.of(Items.COCOA_BEANS));
                pancakeRecipes(pancakesOutput, ModItems.VEGETABLE_PANCAKES, ModItems.VEGETABLE_PANCAKE, tagIngredient(CommonTags.FOODS_MILK), tagIngredient(ConventionalItemTags.VEGETABLE_FOODS), tagIngredient(CommonTags.FOODS_LEAFY_GREEN));
                pancakeRecipes(cherryBlossomPancakesOutput, ModItems.CHERRY_BLOSSOM_PANCAKES, ModItems.CHERRY_BLOSSOM_PANCAKE, tagIngredient(CommonTags.FOODS_MILK), tagIngredient(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS));
                pancakeRecipes(pancakesAndSyrupOutput, ModItems.PUMPKIN_PANCAKES, ModItems.PUMPKIN_PANCAKE, tagIngredient(ModTags.Items.SYRUP), Ingredient.of(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get()));
                // Milk on top for the crema, roasted beans through the batter.
                pancakeRecipes(coffeePancakesOutput, ModItems.COFFEE_PANCAKES, ModItems.COFFEE_PANCAKE, tagIngredient(CommonTags.FOODS_MILK), tagIngredient(ModTags.Items.COFFEE_INGREDIENTS));

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
                foodCookingRecipes(coffeeOutput, ModItems.COFFEE_BEANS, ModItems.ROASTED_COFFEE_BEANS, SMALL_EXP);

                var goldenCoffeeBeansIngredient = Ingredient.of(ModItems.COFFEE_BEANS, ModItems.ROASTED_COFFEE_BEANS);
                shaped(RecipeCategory.FOOD, ModItems.GOLDEN_COFFEE_BEANS, 1)
                        .pattern("GGG")
                        .pattern("GCG")
                        .pattern("GGG")
                        .define('G', Items.GOLD_NUGGET)
                        .define('C', goldenCoffeeBeansIngredient)
                        .unlockedBy(getHasName(ModItems.COFFEE_BEANS), has(ModItems.COFFEE_BEANS))
                        .unlockedBy(getHasName(ModItems.ROASTED_COFFEE_BEANS), has(ModItems.ROASTED_COFFEE_BEANS))
                        .save(coffeeOutput);

                shapeless(RecipeCategory.FOOD, ModItems.MILK_COFFEE, 1)
                        .requires(ModItems.COFFEE)
                        .requires(CommonTags.FOODS_MILK)
                        .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                        .save(coffeeOutput);

                shapeless(RecipeCategory.FOOD, ModItems.CHOCOLATE_COFFEE, 1)
                        .requires(ModItems.MILK_COFFEE)
                        .requires(Items.COCOA_BEANS)
                        .unlockedBy(getHasName(ModItems.MILK_COFFEE), has(ModItems.MILK_COFFEE))
                        .save(coffeeOutput, getRecipeName(ModItems.MILK_COFFEE, ModItems.CHOCOLATE_COFFEE));
                shapeless(RecipeCategory.FOOD, ModItems.CHOCOLATE_COFFEE, 1)
                        .requires(ModItems.COFFEE)
                        .requires(CommonTags.FOODS_MILK)
                        .requires(Items.COCOA_BEANS)
                        .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                        .save(coffeeOutput, getRecipeName(ModItems.COFFEE, ModItems.CHOCOLATE_COFFEE));

                shapeless(RecipeCategory.FOOD, ModItems.HONEY_COFFEE, 1)
                        .requires(ModItems.MILK_COFFEE)
                        .requires(Items.HONEY_BOTTLE)
                        .unlockedBy(getHasName(ModItems.MILK_COFFEE), has(ModItems.MILK_COFFEE))
                        .save(coffeeOutput, getRecipeName(ModItems.MILK_COFFEE, ModItems.HONEY_COFFEE));
                shapeless(RecipeCategory.FOOD, ModItems.HONEY_COFFEE, 1)
                        .requires(ModItems.COFFEE)
                        .requires(CommonTags.FOODS_MILK)
                        .requires(Items.HONEY_BOTTLE)
                        .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                        .save(coffeeOutput, getRecipeName(ModItems.COFFEE, ModItems.HONEY_COFFEE));

                shapeless(RecipeCategory.FOOD, ModItems.SYRUP_COFFEE, 1)
                        .requires(ModItems.MILK_COFFEE)
                        .requires(ModTags.Items.SYRUP)
                        .unlockedBy(getHasName(ModItems.MILK_COFFEE), has(ModItems.MILK_COFFEE))
                        .save(coffeeAndSyrupOutput, getRecipeName(ModItems.MILK_COFFEE, ModItems.SYRUP_COFFEE));
                shapeless(RecipeCategory.FOOD, ModItems.SYRUP_COFFEE, 1)
                        .requires(ModItems.COFFEE)
                        .requires(CommonTags.FOODS_MILK)
                        .requires(ModTags.Items.SYRUP)
                        .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                        .save(coffeeAndSyrupOutput, getRecipeName(ModItems.COFFEE, ModItems.SYRUP_COFFEE));

                shapeless(RecipeCategory.FOOD, ModItems.PUMPKIN_COFFEE, 1)
                        .requires(ModItems.MILK_COFFEE)
                        .requires(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get())
                        .unlockedBy(getHasName(ModItems.MILK_COFFEE), has(ModItems.MILK_COFFEE))
                        .save(coffeeOutput, getRecipeName(ModItems.MILK_COFFEE, ModItems.PUMPKIN_COFFEE));
                shapeless(RecipeCategory.FOOD, ModItems.PUMPKIN_COFFEE, 1)
                        .requires(ModItems.COFFEE)
                        .requires(CommonTags.FOODS_MILK)
                        .requires(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get())
                        .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                        .save(coffeeOutput, getRecipeName(ModItems.COFFEE, ModItems.PUMPKIN_COFFEE));

                shapeless(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_COFFEE, 1)
                        .requires(ModItems.MILK_COFFEE)
                        .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                        .unlockedBy(getHasName(ModItems.MILK_COFFEE), has(ModItems.MILK_COFFEE))
                        .save(coffeeAndCherryBlossomOutput, getRecipeName(ModItems.MILK_COFFEE, ModItems.CHERRY_BLOSSOM_COFFEE));
                shapeless(RecipeCategory.FOOD, ModItems.CHERRY_BLOSSOM_COFFEE, 1)
                        .requires(ModItems.COFFEE)
                        .requires(CommonTags.FOODS_MILK)
                        .requires(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS)
                        .unlockedBy(getHasName(ModItems.COFFEE), has(ModItems.COFFEE))
                        .save(coffeeAndCherryBlossomOutput, getRecipeName(ModItems.COFFEE, ModItems.CHERRY_BLOSSOM_COFFEE));

                // Syrup-based recipes
                oneToOne(syrupOutput, RecipeCategory.MISC, ModItems.SYRUP, Items.SUGAR, 3);
                shapeless(RecipeCategory.FOOD, ModItems.SYRUP_SANDWICH, 1)
                        .requires(ConventionalItemTags.BREAD_FOODS)
                        .requires(ModTags.Items.SYRUP)
                        .requires(Items.SUGAR)
                        .unlockedBy(getHasName(ModItems.SYRUP), has(ModItems.SYRUP))
                        .save(syrupOutput);

                // Feasts
                shapeless(RecipeCategory.FOOD, ModItems.RICE_ROLL_ROYALE)
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
                        .save(royaleOutput);

                bellPepperMedleyRecipe(bellPepperOutput, ModItems.BELL_PEPPER_MEDLEY,
                        ModItems.STUFFED_BELL_PEPPER_GREEN, ModItems.STUFFED_BELL_PEPPER_YELLOW, ModItems.STUFFED_BELL_PEPPER_RED);
                bellPepperMedleyRecipe(bellPepperOutput, ModItems.PALE_BELL_PEPPER_MEDLEY,
                        ModItems.STUFFED_BELL_PEPPER_ORANGE, ModItems.STUFFED_BELL_PEPPER_WHITE, ModItems.STUFFED_BELL_PEPPER_PINK);
                bellPepperMedleyRecipe(bellPepperOutput, ModItems.DARK_BELL_PEPPER_MEDLEY,
                        ModItems.STUFFED_BELL_PEPPER_BLUE, ModItems.STUFFED_BELL_PEPPER_PURPLE, ModItems.STUFFED_BELL_PEPPER_BLACK);
            }

            // One stuffed bell pepper of each colour in the variant, plus a bowl.
            private void bellPepperMedleyRecipe(RecipeOutput output, Item medley, Item first, Item second, Item third) {
                shapeless(RecipeCategory.FOOD, medley)
                        .requires(first)
                        .requires(second)
                        .requires(third)
                        .requires(Items.BOWL)
                        .unlockedBy("has_stuffed_bell_pepper", InventoryChangeTrigger.TriggerInstance.hasItems(first, second, third))
                        .save(output);
            }

            private void buildCuttingRecipes(@NotNull RecipeOutput output) {
                RecipeOutput bellPepperOutput = family(output, RusticDelightConfig.ENABLE_BELL_PEPPERS_ID);
                RecipeOutput calamariOutput = family(output, RusticDelightConfig.SQUIDS_DROP_CALAMARI_ID);
                RecipeOutput cherryBlossomOutput = family(output, RusticDelightConfig.ENABLE_CHERRY_BLOSSOM_FOODS_ID);
                RecipeOutput coffeeOutput = family(output, RusticDelightConfig.ENABLE_COFFEE_ID);
                RecipeOutput cottonOutput = family(output, RusticDelightConfig.ENABLE_COTTON_ID);
                RecipeOutput potatoSlicesOutput = family(output, RusticDelightConfig.ENABLE_POTATO_SLICES_ID);
                RecipeOutput syrupOutput = family(output, RusticDelightConfig.ENABLE_SYRUP_FOODS_ID);

                // Cotton
                CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_COTTON), tagIngredient(CommonTags.TOOLS_KNIFE), ModItems.COTTON_SEEDS, 1)
                        .addResultWithChance(ModItems.COTTON_BOLL, 0.3F)
                        .addResultWithChance(Items.WHITE_DYE, 0.1F)
                        .build(cottonOutput, ItemUtils.getIdentifier(ModItems.WILD_COTTON));

                // Bell pepper
                CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_BELL_PEPPERS), tagIngredient(CommonTags.TOOLS_KNIFE), ModItems.BELL_PEPPER_SEEDS, 1)
                        .addResultWithChance(ModItems.BELL_PEPPER_RED, 0.3F)
                        .addResultWithChance(Items.RED_DYE, 0.1F)
                        .build(bellPepperOutput, ItemUtils.getIdentifier(ModItems.WILD_BELL_PEPPERS));

                CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_PALE_BELL_PEPPERS), tagIngredient(CommonTags.TOOLS_KNIFE), ModItems.PALE_BELL_PEPPER_SEEDS, 1)
                        .addResultWithChance(ModItems.BELL_PEPPER_PINK, 0.3F)
                        .addResultWithChance(Items.PINK_DYE, 0.1F)
                        .build(bellPepperOutput, ItemUtils.getIdentifier(ModItems.WILD_PALE_BELL_PEPPERS));
                CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_DARK_BELL_PEPPERS), tagIngredient(CommonTags.TOOLS_KNIFE), ModItems.DARK_BELL_PEPPER_SEEDS, 1)
                        .addResultWithChance(ModItems.BELL_PEPPER_PURPLE, 0.3F)
                        .addResultWithChance(Items.PURPLE_DYE, 0.1F)
                        .build(bellPepperOutput, ItemUtils.getIdentifier(ModItems.WILD_DARK_BELL_PEPPERS));

                // Bell pepper slices
                cuttingBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_GREEN, ModItems.BELL_PEPPER_SLICE_GREEN, ModItems.BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_YELLOW, ModItems.BELL_PEPPER_SLICE_YELLOW, ModItems.BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_RED, ModItems.BELL_PEPPER_SLICE_RED, ModItems.BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_ORANGE, ModItems.BELL_PEPPER_SLICE_ORANGE, ModItems.PALE_BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_WHITE, ModItems.BELL_PEPPER_SLICE_WHITE, ModItems.PALE_BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_PINK, ModItems.BELL_PEPPER_SLICE_PINK, ModItems.PALE_BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_BLUE, ModItems.BELL_PEPPER_SLICE_BLUE, ModItems.DARK_BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_PURPLE, ModItems.BELL_PEPPER_SLICE_PURPLE, ModItems.DARK_BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_BLACK, ModItems.BELL_PEPPER_SLICE_BLACK, ModItems.DARK_BELL_PEPPER_SEEDS);

                // Roasted bell pepper slices
                cuttingBellPepper(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_GREEN, ModItems.ROASTED_BELL_PEPPER_SLICE_GREEN, ModItems.BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_YELLOW, ModItems.ROASTED_BELL_PEPPER_SLICE_YELLOW, ModItems.BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_RED, ModItems.ROASTED_BELL_PEPPER_SLICE_RED, ModItems.BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_ORANGE, ModItems.ROASTED_BELL_PEPPER_SLICE_ORANGE, ModItems.PALE_BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_WHITE, ModItems.ROASTED_BELL_PEPPER_SLICE_WHITE, ModItems.PALE_BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_PINK, ModItems.ROASTED_BELL_PEPPER_SLICE_PINK, ModItems.PALE_BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_BLUE, ModItems.ROASTED_BELL_PEPPER_SLICE_BLUE, ModItems.DARK_BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_PURPLE, ModItems.ROASTED_BELL_PEPPER_SLICE_PURPLE, ModItems.DARK_BELL_PEPPER_SEEDS);
                cuttingBellPepper(bellPepperOutput, ModItems.ROASTED_BELL_PEPPER_BLACK, ModItems.ROASTED_BELL_PEPPER_SLICE_BLACK, ModItems.DARK_BELL_PEPPER_SEEDS);

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
                CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.WILD_COFFEE), tagIngredient(CommonTags.TOOLS_KNIFE), ModItems.COFFEE_BEANS, 1)
                        .addResultWithChance(ModItems.COFFEE_BEANS, 0.3F)
                        .addResultWithChance(Items.YELLOW_DYE, 0.1F)
                        .build(coffeeOutput, ItemUtils.getIdentifier(ModItems.WILD_COFFEE));

                // Food
                CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POTATO), tagIngredient(CommonTags.TOOLS_KNIFE), ModItems.POTATO_SLICES, 2)
                        .build(potatoSlicesOutput, ItemUtils.getIdentifier(ModItems.POTATO_SLICES));
                CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BAKED_POTATO), tagIngredient(CommonTags.TOOLS_KNIFE), ModItems.BAKED_POTATO_SLICES, 2)
                        .build(potatoSlicesOutput, ItemUtils.getIdentifier(ModItems.BAKED_POTATO_SLICES));
                CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CALAMARI), tagIngredient(CommonTags.TOOLS_KNIFE), ModItems.CALAMARI_SLICE, 2)
                        .addResult(Items.BONE_MEAL)
                        .build(calamariOutput, ItemUtils.getIdentifier(ModItems.CALAMARI_SLICE));
                CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.COOKED_CALAMARI), tagIngredient(CommonTags.TOOLS_KNIFE), ModItems.COOKED_CALAMARI_SLICE, 2)
                        .addResult(Items.BONE_MEAL)
                        .build(calamariOutput, ItemUtils.getIdentifier(ModItems.COOKED_CALAMARI_SLICE));

                // Pie
                CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CHERRY_BLOSSOM_CHEESECAKE), tagIngredient(CommonTags.TOOLS_KNIFE), ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE, 4)
                        .build(cherryBlossomOutput, ItemUtils.getIdentifier(ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE));
                CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.SYRUP_CHEESECAKE), tagIngredient(CommonTags.TOOLS_KNIFE), ModItems.SYRUP_CHEESECAKE_SLICE, 4)
                        .build(syrupOutput, ItemUtils.getIdentifier(ModItems.SYRUP_CHEESECAKE_SLICE));
                CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.COFFEE_CHEESECAKE), tagIngredient(CommonTags.TOOLS_KNIFE), ModItems.COFFEE_CHEESECAKE_SLICE, 4)
                        .build(coffeeOutput, ItemUtils.getIdentifier(ModItems.COFFEE_CHEESECAKE_SLICE));

                // Salvaging
                CuttingBoardRecipeBuilder.cuttingRecipe(tagIngredient(ItemTags.WOOL), tagIngredient(ConventionalItemTags.SHEAR_TOOLS), Items.STRING, 2)
                        .build(output, Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, "wool"));
                CuttingBoardRecipeBuilder.cuttingRecipe(tagIngredient(ItemTags.WOOL_CARPETS), tagIngredient(ConventionalItemTags.SHEAR_TOOLS), Items.STRING, 1)
                        .build(output, Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, "wool_carpet"));
            }

            private void buildCookingRecipes(@NotNull RecipeOutput output) {
                RecipeOutput bellPepperOutput = family(output, RusticDelightConfig.ENABLE_BELL_PEPPERS_ID);
                RecipeOutput calamariOutput = family(output, RusticDelightConfig.SQUIDS_DROP_CALAMARI_ID);
                RecipeOutput coffeeAndCherryBlossomOutput = family(output,
                        RusticDelightConfig.ENABLE_COFFEE_ID, RusticDelightConfig.ENABLE_CHERRY_BLOSSOM_FOODS_ID);
                RecipeOutput coffeeAndSyrupOutput = family(output,
                        RusticDelightConfig.ENABLE_COFFEE_ID, RusticDelightConfig.ENABLE_SYRUP_FOODS_ID);
                RecipeOutput coffeeOutput = family(output, RusticDelightConfig.ENABLE_COFFEE_ID);
                RecipeOutput friedAndCalamariOutput = family(output,
                        RusticDelightConfig.ENABLE_FRIED_FOODS_ID, RusticDelightConfig.SQUIDS_DROP_CALAMARI_ID);
                RecipeOutput friedOutput = family(output, RusticDelightConfig.ENABLE_FRIED_FOODS_ID);
                RecipeOutput syrupOutput = family(output, RusticDelightConfig.ENABLE_SYRUP_FOODS_ID);

                buildFarmersDelightOverrideRecipes(output, holderGetter);

                // Cooking oil
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.COOKING_OIL, 2, FAST_COOKING, SMALL_EXP, Items.GLASS_BOTTLE)
                        .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                        .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                        .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                        .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                        .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                        .addIngredient(ModTags.Items.COOKING_OIL_INGREDIENTS)
                        .unlockedByAnyIngredient(ModItems.COTTON_SEEDS, Items.PUMPKIN_SEEDS)
                        .setRecipeBookCategory(CookingPotBookCategory.MISC)
                        .build(friedOutput, ItemUtils.getIdentifier(ModItems.COOKING_OIL));

                // Batter
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.BATTER, 2, FAST_COOKING, SMALL_EXP, Items.BOWL)
                        .addIngredient(CommonTags.FOODS_MILK)
                        .addIngredient(CommonTags.EGGS)
                        .addIngredient(Items.WHEAT)
                        .addIngredient(Items.WHEAT)
                        .unlockedByAnyIngredient(Items.MILK_BUCKET, vectorwing.farmersdelight.common.registry.ModItems.MILK_BOTTLE.get())
                        .setRecipeBookCategory(CookingPotBookCategory.MISC)
                        .build(withConditions(output, ResourceConditions.or(
                                new ConfigBooleanCondition(RusticDelightConfig.ENABLE_FRIED_FOODS_ID),
                                new ConfigBooleanCondition(RusticDelightConfig.ENABLE_PANCAKES_ID))),
                                ItemUtils.getIdentifier(ModItems.BATTER));

                // Syrup
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.SYRUP, 1, FAST_COOKING, SMALL_EXP, Items.GLASS_BOTTLE)
                        .addIngredient(ModTags.Items.SYRUP_INGREDIENTS)
                        .addIngredient(Items.SUGAR)
                        .unlockedByAnyIngredient(Items.APPLE, Items.BEETROOT, Items.SUGAR)
                        .setRecipeBookCategory(CookingPotBookCategory.MISC)
                        .build(syrupOutput, ItemUtils.getIdentifier(ModItems.SYRUP));

                // Fried Dough
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.FRIED_DOUGH, 1, FAST_COOKING, SMALL_EXP)
                        .addIngredient(ModTags.Items.COOKING_OIL)
                        .addIngredient(CommonTags.FOODS_DOUGH)
                        .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                        .setRecipeBookCategory(CookingPotBookCategory.MISC)
                        .build(friedOutput, ItemUtils.getIdentifier(ModItems.FRIED_DOUGH));

                // Fried Dumplings
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.FRIED_DUMPLINGS, 2, FAST_COOKING, MEDIUM_EXP)
                        .addIngredient(ModTags.Items.COOKING_OIL)
                        .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.DUMPLINGS.get(), 2)
                        .unlockedByAnyIngredient(vectorwing.farmersdelight.common.registry.ModItems.DUMPLINGS.get())
                        .setRecipeBookCategory(CookingPotBookCategory.MISC)
                        .build(friedOutput, ItemUtils.getIdentifier(ModItems.FRIED_DUMPLINGS));

                // Spring Rolls
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.SPRING_ROLLS, 2, FAST_COOKING, MEDIUM_EXP)
                        .addIngredient(ModTags.Items.COOKING_OIL)
                        .addIngredient(CommonTags.FOODS_DOUGH)
                        .addIngredient(CommonTags.FOODS_LEAFY_GREEN)
                        .addIngredient(ModTags.Items.SPRING_ROLL_INGREDIENTS)
                        .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                        .setRecipeBookCategory(CookingPotBookCategory.MISC)
                        .build(friedOutput, ItemUtils.getIdentifier(ModItems.SPRING_ROLLS));

                // Fruit Beignet
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.FRUIT_BEIGNET, 1, FAST_COOKING, MEDIUM_EXP)
                        .addIngredient(ModTags.Items.COOKING_OIL)
                        .addIngredient(CommonTags.FOODS_DOUGH)
                        .addIngredient(ModTags.Items.FRUITS_AND_BERRIES)
                        .addIngredient(Items.SUGAR)
                        .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                        .setRecipeBookCategory(CookingPotBookCategory.MISC)
                        .build(friedOutput, ItemUtils.getIdentifier(ModItems.FRUIT_BEIGNET));

                // Fried Calamari
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.FRIED_CALAMARI, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                        .addIngredient(ModTags.Items.COOKING_OIL)
                        .addIngredient(ModItems.BATTER)
                        .addIngredient(CommonTags.FOODS_RAW_CALAMARI)
                        .addIngredient(CommonTags.FOODS_TOMATO)
                        .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                        .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                        .build(friedAndCalamariOutput, ItemUtils.getIdentifier(ModItems.FRIED_CALAMARI));

                // Fried Chicken
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.FRIED_CHICKEN, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                        .addIngredient(ModTags.Items.COOKING_OIL)
                        .addIngredient(ModItems.BATTER)
                        .addIngredient(CommonTags.FOODS_RAW_CHICKEN)
                        .addIngredient(CommonTags.FOODS_ONION)
                        .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                        .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                        .build(friedOutput, ItemUtils.getIdentifier(ModItems.FRIED_CHICKEN));

                // Fried Mushrooms
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.FRIED_MUSHROOMS, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                        .addIngredient(ModTags.Items.COOKING_OIL)
                        .addIngredient(Items.BROWN_MUSHROOM)
                        .addIngredient(Items.RED_MUSHROOM)
                        .addIngredient(CommonTags.FOODS_ONION)
                        .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                        .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                        .build(friedOutput, ItemUtils.getIdentifier(ModItems.FRIED_MUSHROOMS));

                // Fried Fish - any raw fish or fish slice, minus pufferfish (the c: tag already excludes it).
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.FRIED_FISH, 1, FAST_COOKING, MEDIUM_EXP)
                        .addIngredient(ModTags.Items.COOKING_OIL)
                        .addIngredient(ModItems.BATTER)
                        .addIngredient(CommonTags.FOODS_SAFE_RAW_FISH)
                        .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                        .setRecipeBookCategory(CookingPotBookCategory.MISC)
                        .build(friedOutput, ItemUtils.getIdentifier(ModItems.FRIED_FISH));

                // Bell Pepper Soup
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.BELL_PEPPER_SOUP, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                        .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                        .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                        .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                        .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN, ModItems.BELL_PEPPER_YELLOW, ModItems.BELL_PEPPER_RED)
                        .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                        .build(bellPepperOutput, ItemUtils.getIdentifier(ModItems.BELL_PEPPER_SOUP));

                // Calamari Soup
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.CALAMARI_SOUP, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                        .addIngredient(CommonTags.FOODS_RAW_CALAMARI)
                        .addIngredient(CommonTags.FOODS_POTATO)
                        .addIngredient(CommonTags.FOODS_ONION)
                        .addIngredient(CommonTags.FOODS_MILK)
                        .unlockedByAnyIngredient(ModItems.CALAMARI)
                        .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                        .build(calamariOutput, ItemUtils.getIdentifier(ModItems.CALAMARI_SOUP));

                // Stuffed Bell Peppers
                stuffedBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_GREEN, ModItems.STUFFED_BELL_PEPPER_GREEN);
                stuffedBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_YELLOW, ModItems.STUFFED_BELL_PEPPER_YELLOW);
                stuffedBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_RED, ModItems.STUFFED_BELL_PEPPER_RED);
                stuffedBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_ORANGE, ModItems.STUFFED_BELL_PEPPER_ORANGE);
                stuffedBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_WHITE, ModItems.STUFFED_BELL_PEPPER_WHITE);
                stuffedBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_PINK, ModItems.STUFFED_BELL_PEPPER_PINK);
                stuffedBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_BLUE, ModItems.STUFFED_BELL_PEPPER_BLUE);
                stuffedBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_PURPLE, ModItems.STUFFED_BELL_PEPPER_PURPLE);
                stuffedBellPepper(bellPepperOutput, ModItems.BELL_PEPPER_BLACK, ModItems.STUFFED_BELL_PEPPER_BLACK);

                // Bell Pepper Pasta
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.BELL_PEPPER_PASTA, 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                        .addIngredient(CommonTags.FOODS_PASTA)
                        .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                        .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                        .addIngredient(CommonTags.FOODS_BELL_PEPPER)
                        .unlockedByAnyIngredient(ModItems.BELL_PEPPER_GREEN, ModItems.BELL_PEPPER_YELLOW, ModItems.BELL_PEPPER_RED)
                        .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                        .build(bellPepperOutput, ItemUtils.getIdentifier(ModItems.BELL_PEPPER_PASTA));

                // Coffee
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.COFFEE, 1, NORMAL_COOKING, MEDIUM_EXP, Items.GLASS_BOTTLE)
                        .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                        .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                        .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                        .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                        .setRecipeBookCategory(CookingPotBookCategory.DRINKS)
                        .build(coffeeOutput,ItemUtils.getIdentifier(ModItems.COFFEE));

                // Milk Coffee
                coffeeDrink(coffeeOutput, ModItems.MILK_COFFEE,
                        null);

                // Chocolate Coffee
                coffeeDrink(coffeeOutput, ModItems.CHOCOLATE_COFFEE,
                        Ingredient.of(Items.COCOA_BEANS));

                // Honey Coffee
                coffeeDrink(coffeeOutput, ModItems.HONEY_COFFEE,
                        Ingredient.of(Items.HONEY_BOTTLE));

                // Syrup Coffee - the tag, so modded syrups work here like they do in the crafted variants.
                coffeeDrink(coffeeAndSyrupOutput, ModItems.SYRUP_COFFEE,
                        tagIngredient(ModTags.Items.SYRUP));

                // Pumpkin Coffee
                coffeeDrink(coffeeOutput, ModItems.PUMPKIN_COFFEE,
                        Ingredient.of(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get()));

                // Cherry Blossom Coffee
                coffeeDrink(coffeeAndCherryBlossomOutput, ModItems.CHERRY_BLOSSOM_COFFEE,
                        tagIngredient(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS));

                // Dark Coffee
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.DARK_COFFEE, 1, SLOW_COOKING, MEDIUM_EXP, Items.GLASS_BOTTLE)
                        .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                        .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                        .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                        .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                        .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                        .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                        .unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                        .setRecipeBookCategory(CookingPotBookCategory.DRINKS)
                        .build(coffeeOutput, ItemUtils.getIdentifier(ModItems.DARK_COFFEE));

                // Coffee-Braised Beef
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, ModItems.COFFEE_BRAISED_BEEF, 1, SLOW_COOKING, MEDIUM_EXP, Items.BOWL)
                        .addIngredient(CommonTags.FOODS_RAW_BEEF)
                        .addIngredient(ModTags.Items.COFFEE_FOOD_INGREDIENTS)
                        .addIngredient(CommonTags.FOODS_CARROT)
                        .addIngredient(CommonTags.FOODS_POTATO)
                        .unlockedByAnyIngredient(ModItems.COFFEE)
                        .setRecipeBookCategory(CookingPotBookCategory.DRINKS)
                        .build(coffeeOutput, ItemUtils.getIdentifier(ModItems.COFFEE_BRAISED_BEEF));
            }

            private void buildFarmersDelightOverrideRecipes(@NotNull RecipeOutput output, HolderGetter<Item> holderGetter) {
                RecipeOutput friedOutput = family(output, RusticDelightConfig.ENABLE_FRIED_FOODS_ID);

                // Fried Rice
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, vectorwing.farmersdelight.common.registry.ModItems.FRIED_RICE.get(), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                        .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                        .addIngredient(ModTags.Items.FRIED_RICE_INGREDIENTS)
                        .addIngredient(CommonTags.FOODS_CARROT)
                        .addIngredient(CommonTags.FOODS_ONION)
                        .unlockedByAnyIngredient(vectorwing.farmersdelight.common.registry.ModItems.RICE.get(), Items.EGG, Items.CARROT, vectorwing.farmersdelight.common.registry.ModItems.ONION.get(), ModItems.COOKING_OIL)
                        .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                        .save(output);

                // Fried Egg
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, vectorwing.farmersdelight.common.registry.ModItems.FRIED_EGG.get(), 1, FAST_COOKING, SMALL_EXP)
                        .addIngredient(Items.EGG)
                        .addIngredient(ModTags.Items.COOKING_OIL)
                        .unlockedByAnyIngredient(ModItems.COOKING_OIL)
                        .setRecipeBookCategory(CookingPotBookCategory.MISC)
                        .save(friedOutput, getRecipeName(ModItems.COOKING_OIL, vectorwing.farmersdelight.common.registry.ModItems.FRIED_EGG.get()));

                // Baked Cod Stew
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, vectorwing.farmersdelight.common.registry.ModItems.BAKED_COD_STEW.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                        .addIngredient(CommonTags.FOODS_RAW_COD)
                        .addIngredient(CommonTags.FOODS_POTATO)
                        .addIngredient(ModTags.Items.RAW_AND_COOKED_EGGS)
                        .addIngredient(CommonTags.FOODS_TOMATO)
                        .unlockedByAnyIngredient(Items.COD, Items.POTATO, vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get(), Items.EGG)
                        .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                        .save(output);

                // Beef Stew
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, vectorwing.farmersdelight.common.registry.ModItems.BEEF_STEW.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                        .addIngredient(CommonTags.FOODS_RAW_BEEF)
                        .addIngredient(CommonTags.FOODS_CARROT)
                        .addIngredient(CommonTags.FOODS_POTATO)
                        .unlockedByAnyIngredient(Items.BEEF, Items.CARROT, Items.POTATO)
                        .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                        .save(output);

                // Mushroom Rice
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, vectorwing.farmersdelight.common.registry.ModItems.MUSHROOM_RICE.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                        .addIngredient(Items.BROWN_MUSHROOM)
                        .addIngredient(Items.RED_MUSHROOM)
                        .addIngredient(CommonTags.CROPS_RICE)
                        .addIngredient(ModTags.Items.MUSHROOM_RICE_INGREDIENTS)
                        .unlockedByAnyIngredient(Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM, vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                        .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                        .save(output);

                // Vegetable Soup
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, vectorwing.farmersdelight.common.registry.ModItems.VEGETABLE_SOUP.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                        .addIngredient(CommonTags.FOODS_CARROT)
                        .addIngredient(CommonTags.FOODS_POTATO)
                        .addIngredient(CommonTags.FOODS_BEETROOT)
                        .addIngredient(CommonTags.FOODS_LEAFY_GREEN)
                        .unlockedByAnyIngredient(Items.CARROT, vectorwing.farmersdelight.common.registry.ModItems.ONION.get(), Items.BEETROOT)
                        .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                        .save(output);
            }

            private void oneToOne(RecipeOutput recipeOutput, RecipeCategory category, ItemLike item, ItemLike result, int count) {
                shapeless(category, result, count)
                        .requires(item)
                        .unlockedBy(getHasName(item), has(item))
                        .save(recipeOutput, getRecipeName(item, result));
            }

            private void horizontalRecipe(RecipeOutput recipeOutput, RecipeCategory category, ItemLike item, ItemLike result, int count) {
                shaped(category, result, count)
                        .pattern("###")
                        .define('#', item)
                        .unlockedBy(getHasName(item), has(item))
                        .save(recipeOutput, getRecipeName(item, result));
            }

            private void twoBytwo(RecipeOutput recipeOutput, RecipeCategory category, ItemLike item, ItemLike result, int count) {
                shaped(category, result, count)
                        .pattern("##")
                        .pattern("##")
                        .define('#', item)
                        .unlockedBy(getHasName(item), has(item))
                        .save(recipeOutput, getRecipeName(item, result));
            }

            private void storageItemRecipes(RecipeOutput recipeOutput, RecipeCategory category, ItemLike item, ItemLike storageItem) {
                // From item to storageItem
                compactingRecipe(recipeOutput, category, item, storageItem);

                // From storageItem to item
                shapeless(category, item, 9)
                        .requires(storageItem)
                        .unlockedBy(getHasName(storageItem), has(storageItem))
                        .save(recipeOutput, getRecipeName(storageItem, item));
            }

            // 1.7.0 rebalance: two slices per pepper, and the seed by-product is rarer.
            private void cuttingBellPepper(RecipeOutput recipeOutput, ItemLike pepper, ItemLike slice, ItemLike seeds) {
                CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(pepper), tagIngredient(CommonTags.TOOLS_KNIFE), slice, 2)
                        .addResultWithChance(seeds, 0.1F)
                        .build(recipeOutput, ItemUtils.getIdentifier(pepper.asItem()));
            }

            // Three roasted coffee bean helpings, milk, and one flavouring on top.
            private void coffeeDrink(RecipeOutput recipeOutput, ItemLike result, Ingredient flavour) {
                CookingPotRecipeBuilder builder = CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, result, 1, NORMAL_COOKING, MEDIUM_EXP, Items.GLASS_BOTTLE)
                        .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                        .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                        .addIngredient(ModTags.Items.COFFEE_INGREDIENTS)
                        .addIngredient(CommonTags.FOODS_MILK);

                if (flavour != null) {
                    builder.addIngredient(flavour);
                }

                builder.unlockedByAnyIngredient(ModItems.ROASTED_COFFEE_BEANS)
                        .setRecipeBookCategory(CookingPotBookCategory.DRINKS)
                        .build(recipeOutput, ItemUtils.getIdentifier(result.asItem()));
            }

            private void stuffedBellPepper(RecipeOutput recipeOutput, ItemLike pepper, ItemLike result) {
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, result, 1, NORMAL_COOKING, MEDIUM_EXP)
                        .addIngredient(pepper)
                        .addIngredient(CommonTags.CROPS_RICE)
                        .addIngredient(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
                        .unlockedByAnyIngredient(pepper)
                        .setRecipeBookCategory(CookingPotBookCategory.MEALS)
                        .build(recipeOutput, ItemUtils.getIdentifier(result.asItem()));
            }

            // Cuts a bell pepper block into 9 slices of the same color.
            private void cuttingBellPepperBlock(RecipeOutput recipeOutput, ItemLike block, ItemLike slice) {
                CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(block), tagIngredient(CommonTags.TOOLS_KNIFE), slice, 9)
                        .build(recipeOutput, ItemUtils.getIdentifier(block.asItem()));
            }

            // 3x3 of item -> storageItem only (no reverse crafting recipe).
            private void compactingRecipe(RecipeOutput recipeOutput, RecipeCategory category, ItemLike item, ItemLike storageItem) {
                shaped(category, storageItem)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .define('#', item)
                        .unlockedBy(getHasName(item), has(item))
                        .save(recipeOutput, getRecipeName(item, storageItem));
            }

            private void foodCookingRecipes(@NotNull RecipeOutput recipeOutput, @NotNull ItemLike material, @NotNull ItemLike result, float experience) {
                foodSmelting(recipeOutput, material, result, experience, 200);
                foodSmoking(recipeOutput, material, result, experience, 100); // Smoking is twice as fast
                foodCampfireCooking(recipeOutput, material, result, experience, 600); // Campfire cooking takes three times longer
            }

            private void foodSmelting(@NotNull RecipeOutput recipeOutput, @NotNull ItemLike material, @NotNull ItemLike result, float experience, int cookingTime) {
                SimpleCookingRecipeBuilder
                        .generic(Ingredient.of(material), RecipeCategory.FOOD, CookingBookCategory.FOOD, result, experience, cookingTime, SmeltingRecipe::new)
                        .unlockedBy(getHasName(material), has(material))
                        .save(recipeOutput);
            }

            private void foodSmoking(@NotNull RecipeOutput recipeOutput, @NotNull ItemLike material, @NotNull ItemLike result, float experience, int cookingTime) {
                SimpleCookingRecipeBuilder
                        .generic(Ingredient.of(material), RecipeCategory.FOOD, CookingBookCategory.FOOD, result, experience, cookingTime, SmokingRecipe::new)
                        .unlockedBy(getHasName(material), has(material))
                        .save(recipeOutput, RusticDelight.MOD_ID + ":" + getItemName(result) + "_from_smoking");
            }

            private void foodCampfireCooking(@NotNull RecipeOutput recipeOutput, @NotNull ItemLike material, @NotNull ItemLike result, float experience, int cookingTime) {
                SimpleCookingRecipeBuilder
                        .generic(Ingredient.of(material), RecipeCategory.FOOD, CookingBookCategory.FOOD, result, experience, cookingTime, CampfireCookingRecipe::new)
                        .unlockedBy(getHasName(material), has(material))
                        .save(recipeOutput, RusticDelight.MOD_ID + ":" + getItemName(result) + "_from_campfire_cooking");
            }

            private void simpleSushiRoll(@NotNull RecipeOutput recipeOutput, @NotNull ItemLike ingredient, @NotNull ItemLike result) {
                shapeless(RecipeCategory.FOOD, result, 2)
                        .requires(ingredient)
                        .requires(ingredient)
                        .requires(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                        .unlockedBy(getHasName(ingredient), has(ingredient))
                        .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()), has(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get()))
                        .save(recipeOutput);
            }

            private void pancakeRecipes(@NotNull RecipeOutput recipeOutput, @NotNull Item pancakeBlock, @NotNull Item singlePancake, Ingredient topping, Ingredient ingredient) {
                pancakeRecipes(recipeOutput, pancakeBlock, singlePancake, topping, ingredient, ingredient);
            }

            private void pancakeRecipes(@NotNull RecipeOutput recipeOutput, @NotNull Item pancakeBlock, @NotNull Item singlePancake, Ingredient topping, Ingredient ingredient, Ingredient ingredient2) {
                var batter = ModItems.BATTER;
                var servingItem = Items.BOWL;

                // Crafting a pancake block.
                shaped(RecipeCategory.FOOD, pancakeBlock, 1)
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
                CookingPotRecipeBuilder.cookingPotRecipe(holderGetter, pancakeBlock, 1, SLOW_COOKING, LARGE_EXP, servingItem)
                        .addIngredient(batter)
                        .addIngredient(topping)
                        .addIngredient(ingredient, 2)
                        .addIngredient(ingredient2, 2)
                        .unlockedByAnyIngredient(batter)
                        .setRecipeBookCategory(CookingPotBookCategory.MISC)
                        // Needs its own id: the shaped recipe above already claims the pancake
                        // block's default id, so both would resolve to rusticdelight:item/<name>.
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE,
                                Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, getItemName(pancakeBlock) + "_from_cooking_pot")));

                // Cutting recipe for pancakes to separate them into single pancakes.
                CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(pancakeBlock), tagIngredient(CommonTags.TOOLS_KNIFE), singlePancake, PancakeBlock.MAX_SERVINGS)
                        .addResult(servingItem)
                        .build(recipeOutput, ItemUtils.getIdentifier(pancakeBlock));

                // Split a stack of pancakes into separate pancakes.
                oneToOne(recipeOutput, RecipeCategory.MISC, pancakeBlock, singlePancake, PancakeBlock.MAX_SERVINGS);

                // Combine separate pancakes together into a single stack
                shapeless(RecipeCategory.FOOD, pancakeBlock)
                        .requires(singlePancake, PancakeBlock.MAX_SERVINGS)
                        .requires(servingItem) // Pancakes are always placed on a bowl
                        .unlockedBy(getHasName(singlePancake), has(singlePancake))
                        .save(recipeOutput, getRecipeName(singlePancake, pancakeBlock));
            }

            /**
             * Layered like Farmer's Delight's Sweet Berry Cheesecake: the topping on top, sugar in the
             * middle, and milk either side of the crust. {@code toppingRow} lets a potent topping take a
             * single centre slot instead of the full row.
             */
            private void pieRecipes(@NotNull RecipeOutput recipeOutput, @NotNull Item pieBlock, @NotNull Item sliceItem, Ingredient topping, String toppingRow) {
                shaped(RecipeCategory.FOOD, pieBlock, 1)
                        .pattern(toppingRow)
                        .pattern("SSS")
                        .pattern("MCM")
                        .define('T', topping)
                        .define('M', CommonTags.FOODS_MILK)
                        .define('S', Items.SUGAR)
                        .define('C', vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get())
                        .unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get()), has(vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get()))
                        .save(recipeOutput);
                shaped(RecipeCategory.FOOD, pieBlock, 1)
                        .pattern("##")
                        .pattern("##")
                        .define('#', sliceItem)
                        .unlockedBy(getHasName(sliceItem), has(sliceItem))
                        .save(recipeOutput, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, getItemName(pieBlock) + "_from_slices")));
            }

            private String getRecipeName(ItemLike item, ItemLike result) {
                return RusticDelight.MOD_ID + ":" + getConversionRecipeName(result, item);
            }

            private Ingredient tagIngredient(TagKey<Item> tag) {
                return Ingredient.of(holderGetter.getOrThrow(tag));
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "Rustic Delight Recipes";
    }


}
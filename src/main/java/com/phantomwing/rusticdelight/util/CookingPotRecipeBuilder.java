package com.phantomwing.rusticdelight.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.AdvancementRequirements.Strategy;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance;
import net.minecraft.advancements.critereon.ItemPredicate.Builder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

@MethodsReturnNonnullByDefault
public class CookingPotRecipeBuilder implements RecipeBuilder {
    private CookingPotRecipeBookTab tab;
    private final NonNullList<Ingredient> ingredients;
    private final Item result;
    private final ItemStack resultStack;
    private final int cookingTime;
    private final float experience;
    private final ItemStack container;
    private final Map<String, Criterion<?>> criteria;

    public CookingPotRecipeBuilder(ItemLike result, int count, int cookingTime, float experience, @Nullable ItemLike container) {
        this(new ItemStack(result, count), cookingTime, experience, container);
    }

    public CookingPotRecipeBuilder(ItemStack resultIn, int cookingTime, float experience, @Nullable ItemLike container) {
        this.ingredients = NonNullList.create();
        this.criteria = new LinkedHashMap<>();
        this.result = resultIn.getItem();
        this.resultStack = resultIn;
        this.cookingTime = cookingTime;
        this.experience = experience;
        this.container = container != null ? new ItemStack(container) : ItemStack.EMPTY;
        this.tab = null;
    }

    public static CookingPotRecipeBuilder cookingPotRecipe(ItemLike mainResult, int count, int cookingTime, float experience) {
        return new CookingPotRecipeBuilder(mainResult, count, cookingTime, experience, (ItemLike)null);
    }

    public static CookingPotRecipeBuilder cookingPotRecipe(ItemLike mainResult, int count, int cookingTime, float experience, ItemLike container) {
        return new CookingPotRecipeBuilder(mainResult, count, cookingTime, experience, container);
    }

    public CookingPotRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return this.addIngredient(Ingredient.of(tagIn));
    }

    public CookingPotRecipeBuilder addIngredient(ItemLike itemIn) {
        return this.addIngredient((ItemLike)itemIn, 1);
    }

    public CookingPotRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(Ingredient.of(new ItemLike[]{itemIn}));
        }

        return this;
    }

    public CookingPotRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return this.addIngredient((Ingredient)ingredientIn, 1);
    }

    public CookingPotRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.ingredients.add(ingredientIn);
        }

        return this;
    }

    public RecipeBuilder group(@org.jetbrains.annotations.Nullable String p_176495_) {
        return this;
    }

    public CookingPotRecipeBuilder setRecipeBookTab(CookingPotRecipeBookTab tab) {
        this.tab = tab;
        return this;
    }

    public Item getResult() {
        return this.result;
    }

    public CookingPotRecipeBuilder unlockedBy(String criterionName, Criterion<?> criterionTrigger) {
        this.criteria.put(criterionName, criterionTrigger);
        return this;
    }

    public CookingPotRecipeBuilder unlockedByItems(String criterionName, ItemLike... items) {
        return this.unlockedBy(criterionName, TriggerInstance.hasItems(items));
    }

    public CookingPotRecipeBuilder unlockedByAnyIngredient(ItemLike... items) {
        this.criteria.put("has_any_ingredient", TriggerInstance.hasItems(new ItemPredicate[]{Builder.item().of(items).build()}));
        return this;
    }

    public void build(RecipeOutput output) {
        ResourceLocation location = BuiltInRegistries.ITEM.getKey(this.result);
        this.save(output, ResourceLocation.fromNamespaceAndPath("farmersdelight", location.getPath()));
    }

    public void build(RecipeOutput outputIn, String save) {
        ResourceLocation resourcelocation = BuiltInRegistries.ITEM.getKey(this.result);
        if (ResourceLocation.parse(save).equals(resourcelocation)) {
            throw new IllegalStateException("Cooking Recipe " + save + " should remove its 'save' argument");
        } else {
            this.save(outputIn, ResourceLocation.parse(save));
        }
    }

    public void save(RecipeOutput output, ResourceLocation id) {
        ResourceLocation recipeId = id.withPrefix("cooking/");
        Advancement.Builder advancementBuilder = output.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId)).rewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(recipeId)).requirements(Strategy.OR);
        Objects.requireNonNull(advancementBuilder);
        this.criteria.forEach(advancementBuilder::addCriterion);
        CookingPotRecipe recipe = new CookingPotRecipe("", this.tab, this.ingredients, this.resultStack, this.container, this.experience, this.cookingTime);
        output.accept(recipeId, recipe, advancementBuilder.build(id.withPrefix("recipes/cooking/")));
    }
}

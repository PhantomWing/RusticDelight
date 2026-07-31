package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tags.CommonTags;
import com.phantomwing.rusticdelight.tags.CompatibilityTags;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.tag.ConventionalTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        addMinecraftTags();
        addCommonTags();
        addCompatibilityTags();
        addModTags();
    }

    private void addModTags() {
        // Mirrors the block tag of the same name.
        itemTag(ModTags.Items.PANCAKES).add(
                ModItems.PANCAKES,
                ModItems.HONEY_PANCAKES,
                ModItems.CHOCOLATE_PANCAKES,
                ModItems.CHERRY_BLOSSOM_PANCAKES,
                ModItems.VEGETABLE_PANCAKES,
                ModItems.PUMPKIN_PANCAKES,
                ModItems.COFFEE_PANCAKES
        );

        // Cooking oil
        itemTag(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .add(ModItems.COTTON_SEEDS, Items.PUMPKIN_SEEDS)
                .addOptionalTag(CommonTags.SEEDS_CANOLA)
                .addOptionalTag(CommonTags.SEEDS_SUNFLOWER);
        addOptionalElement(ModTags.Items.COOKING_OIL_INGREDIENTS, compatItem(CompatibilityTags.FRYCOOKS_DELIGHT, "canola_seeds"));

        itemTag(ModTags.Items.COOKING_OIL)
                .add(ModItems.COOKING_OIL);
        addOptionalElement(ModTags.Items.COOKING_OIL, compatItem(CompatibilityTags.FRYCOOKS_DELIGHT, "canola_oil"));

        // Cherry blossom foods
        itemTag(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS).add(
                Items.PINK_PETALS,
                Items.CHERRY_LEAVES,
                Items.CHERRY_SAPLING
        );

        // Calamari
        itemTag(ModTags.Items.CALAMARI_ROLL_INGREDIENTS)
                .add(ModItems.CALAMARI_SLICE);
        addOptionalElement(ModTags.Items.CALAMARI_ROLL_INGREDIENTS, compatItem(CompatibilityTags.MINERS_DELIGHT, "tentacles"));

        // Coffee
        itemTag(ModTags.Items.COFFEE_INGREDIENTS)
                .add(ModItems.ROASTED_COFFEE_BEANS);
        addOptionalElement(ModTags.Items.COFFEE_INGREDIENTS, compatItem(CompatibilityTags.FARMERS_RESPITE, "coffee_beans"));

        itemTag(ModTags.Items.COFFEE_FOOD_INGREDIENTS)
                .add(ModItems.COFFEE, ModItems.DARK_COFFEE);
        addOptionalElement(ModTags.Items.COFFEE_FOOD_INGREDIENTS, compatItem(CompatibilityTags.FARMERS_RESPITE, "coffee"));
        // Syrup
        itemTag(ModTags.Items.SYRUP_INGREDIENTS).add(
                Items.APPLE
        ).addOptionalTag(ConventionalItemTags.BEETROOT_CROPS);

        itemTag(ModTags.Items.SYRUP).add(
                ModItems.SYRUP
        );
        // Hearth and Harvest's syrup bottle works anywhere our syrup does.
        addOptionalElement(ModTags.Items.SYRUP, compatItem(CompatibilityTags.HEARTH_AND_HARVEST, "syrup_bottle"));

        itemTag(ModTags.Items.SWEET_LIQUIDS).add(
                Items.HONEY_BOTTLE
        ).addTag(ModTags.Items.SYRUP);

        // Eggs
        itemTag(ModTags.Items.RAW_AND_COOKED_EGGS)
            .addOptionalTag(CommonTags.EGGS)
            .addOptionalTag(CommonTags.FOODS_COOKED_EGG);

        // Spring rolls
        itemTag(ModTags.Items.SPRING_ROLL_INGREDIENTS)
            .addOptionalTag(CommonTags.FOODS_SAFE_RAW_FISH)
            .addOptionalTag(CommonTags.FOODS_RAW_PORK)
            .addOptionalTag(CommonTags.FOODS_RAW_CHICKEN)
            .addOptionalTag(CommonTags.FOODS_RAW_BEEF)
            .addOptionalTag(CommonTags.FOODS_RAW_MUTTON)
            .addTag(CommonTags.FOODS_CARROT)
            .addTag(CommonTags.FOODS_POTATO)
            .addTag(CommonTags.FOODS_BEETROOT)
            .addTag(CommonTags.FOODS_BELL_PEPPER)
            .addOptionalTag(CommonTags.EGGS)
            .addOptionalTag(CommonTags.MUSHROOMS);

        // Stuffed bell peppers
        itemTag(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
            .addOptionalTag(CommonTags.FOODS_SAFE_RAW_FISH)
            .addOptionalTag(CommonTags.FOODS_RAW_PORK)
            .addOptionalTag(CommonTags.FOODS_RAW_CHICKEN)
            .addOptionalTag(CommonTags.FOODS_RAW_BEEF)
            .addOptionalTag(CommonTags.FOODS_RAW_MUTTON)
            .addTag(CommonTags.FOODS_CARROT)
            .addTag(CommonTags.FOODS_POTATO)
            .addTag(CommonTags.FOODS_BEETROOT)
            .addOptionalTag(CommonTags.FOODS_TOMATO)
            .addOptionalTag(CommonTags.FOODS_CABBAGE)
            .addOptionalTag(CommonTags.FOODS_ONION)
            .addOptionalTag(CommonTags.EGGS)
            .addOptionalTag(CommonTags.MUSHROOMS)
            .addOptionalTag(CompatibilityTags.BREWIN_AND_CHEWIN_CHEESE_WEDGE);

        // Fried rice
        itemTag(ModTags.Items.FRIED_RICE_INGREDIENTS)
            .addOptionalTag(CommonTags.EGGS)
            .addTag(ModTags.Items.COOKING_OIL);

        // Mushroom rice
        itemTag(ModTags.Items.MUSHROOM_RICE_INGREDIENTS)
            .addTag(CommonTags.FOODS_CARROT)
            .addTag(CommonTags.FOODS_POTATO);
    }

    private void addMinecraftTags() {
        // Villagers
        itemTag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(
            ModItems.COTTON_SEEDS,
            ModItems.BELL_PEPPER_SEEDS,
            ModItems.PALE_BELL_PEPPER_SEEDS,
            ModItems.DARK_BELL_PEPPER_SEEDS,
            ModItems.COFFEE_BEANS
        );
        itemTag(ItemTags.VILLAGER_PICKS_UP).add(
                ModItems.BELL_PEPPER_GREEN,
                ModItems.BELL_PEPPER_YELLOW,
                ModItems.BELL_PEPPER_RED,
                ModItems.BELL_PEPPER_ORANGE,
                ModItems.BELL_PEPPER_WHITE,
                ModItems.BELL_PEPPER_PINK,
                ModItems.BELL_PEPPER_BLUE,
                ModItems.BELL_PEPPER_PURPLE,
                ModItems.BELL_PEPPER_BLACK,
                ModItems.COTTON_BOLL,
                ModItems.BELL_PEPPER_SEEDS,
                ModItems.PALE_BELL_PEPPER_SEEDS,
                ModItems.DARK_BELL_PEPPER_SEEDS,
                ModItems.COTTON_SEEDS,
                ModItems.COFFEE_BEANS
        );

        // ItemTags.SMALL_FLOWERS was removed in 26.2; the data tag still exists, so reference it by key.
        this.itemTag(TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace("small_flowers"))).add(
            ModItems.WILD_COTTON,
            ModItems.WILD_BELL_PEPPERS,
            ModItems.WILD_PALE_BELL_PEPPERS,
            ModItems.WILD_DARK_BELL_PEPPERS,
            ModItems.WILD_COFFEE
        );

        // Animal food
        itemTag(ItemTags.CHICKEN_FOOD).add(
            ModItems.COTTON_SEEDS,
            ModItems.BELL_PEPPER_SEEDS,
            ModItems.PALE_BELL_PEPPER_SEEDS,
            ModItems.DARK_BELL_PEPPER_SEEDS
        );
        itemTag(ItemTags.PARROT_FOOD).add(
            ModItems.COTTON_SEEDS,
            ModItems.BELL_PEPPER_SEEDS,
            ModItems.PALE_BELL_PEPPER_SEEDS,
            ModItems.DARK_BELL_PEPPER_SEEDS
        );
        itemTag(ItemTags.CAT_FOOD).add(
            ModItems.CALAMARI
        );
        itemTag(ItemTags.OCELOT_FOOD).add(
            ModItems.CALAMARI
        );
        itemTag(ItemTags.PIG_FOOD).addTag(
            CommonTags.CROPS_BELL_PEPPER
        );

        // Fish
        itemTag(ItemTags.FISHES).add(
            ModItems.CALAMARI,
            ModItems.COOKED_CALAMARI
        );

        // Piglins
        itemTag(ItemTags.PIGLIN_LOVED).add(
            ModItems.GOLDEN_COFFEE_BEANS
        );
    }

    private void addCommonTags() {
        addStorageBlockTags();
        addSeedTags();
        addCropTags();
        addFoodTags();
    }

    private void addCompatibilityTags() {
        // Create
        itemTag(CompatibilityTags.CREATE_UPRIGHT_ON_BELT).add(
            ModItems.COOKING_OIL,
            ModItems.SYRUP,
            ModItems.COFFEE,
            ModItems.MILK_COFFEE,
            ModItems.CHOCOLATE_COFFEE,
            ModItems.HONEY_COFFEE,
            ModItems.SYRUP_COFFEE,
            ModItems.DARK_COFFEE,
            ModItems.PUMPKIN_COFFEE,
            ModItems.CHERRY_BLOSSOM_COFFEE,
            ModItems.CHERRY_BLOSSOM_CHEESECAKE,
            ModItems.SYRUP_CHEESECAKE,
            ModItems.COFFEE_CHEESECAKE
        );

        // Farmer's Delight
        // Note: ModTags.CABBAGE_ROLL_INGREDIENTS removed in FDR 3.x — cabbage roll recipes use a different mechanism now.
        itemTag(vectorwing.farmersdelight.common.tag.ModTags.Items.WILD_CROPS)
            .add(ModItems.WILD_COTTON)
            .add(ModItems.WILD_BELL_PEPPERS)
            .add(ModItems.WILD_PALE_BELL_PEPPERS)
            .add(ModItems.WILD_DARK_BELL_PEPPERS)
            .add(ModItems.WILD_COFFEE);

        // Serene Seasons
        itemTag(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS).add(
            ModItems.COTTON_SEEDS
        );
        itemTag(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS).add(
            ModItems.COTTON_SEEDS,
            ModItems.BELL_PEPPER_SEEDS,
            ModItems.PALE_BELL_PEPPER_SEEDS,
            ModItems.DARK_BELL_PEPPER_SEEDS,
            ModItems.COFFEE_BEANS
        );
        itemTag(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS).add(
                ModItems.BELL_PEPPER_SEEDS,
            ModItems.PALE_BELL_PEPPER_SEEDS,
            ModItems.DARK_BELL_PEPPER_SEEDS,
                ModItems.COFFEE_BEANS
        );

        // Miner's Delight
        itemTag(CompatibilityTags.MINERS_DELIGHT_TENTACLES).add(
                ModItems.CALAMARI,
                ModItems.CALAMARI_SLICE,
                ModItems.COOKED_CALAMARI,
                ModItems.COOKED_CALAMARI_SLICE
        );

        // Frycook's Delight
        itemTag(CompatibilityTags.FISH_SLICES).add(
                ModItems.CALAMARI_SLICE
        );
        itemTag(CompatibilityTags.HAS_FISH_SLICE).add(
                ModItems.CALAMARI
        );

        // Brewin' and Chewin
        itemTag(CompatibilityTags.BREWIN_AND_CHEWIN_PIZZA_TOPPING).addTag(CommonTags.FOODS_BELL_PEPPER);
    }

    private void addStorageBlockTags() {
        // Storage blocks
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_COTTON_SEEDS).add(
                ModItems.COTTON_SEEDS_BAG
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_COTTON).add(
                ModItems.COTTON_BOLL_CRATE
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_SEEDS).add(
                ModItems.BELL_PEPPER_SEEDS_BAG
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_GREEN).add(
                ModItems.BELL_PEPPER_GREEN_CRATE
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_YELLOW).add(
                ModItems.BELL_PEPPER_YELLOW_CRATE
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_RED).add(
                ModItems.BELL_PEPPER_RED_CRATE
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_PALE_BELL_PEPPER_SEEDS).add(
                ModItems.PALE_BELL_PEPPER_SEEDS_BAG
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_DARK_BELL_PEPPER_SEEDS).add(
                ModItems.DARK_BELL_PEPPER_SEEDS_BAG
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_ORANGE).add(
                ModItems.BELL_PEPPER_ORANGE_CRATE
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_WHITE).add(
                ModItems.BELL_PEPPER_WHITE_CRATE
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_PINK).add(
                ModItems.BELL_PEPPER_PINK_CRATE
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_BLUE).add(
                ModItems.BELL_PEPPER_BLUE_CRATE
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_PURPLE).add(
                ModItems.BELL_PEPPER_PURPLE_CRATE
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_BLACK).add(
                ModItems.BELL_PEPPER_BLACK_CRATE
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_CALAMARI).add(
                ModItems.CALAMARI_CRATE
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_COFFEE_BEANS).add(
                ModItems.COFFEE_BEANS_BAG
        );
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_ROASTED_COFFEE_BEANS).add(
                ModItems.ROASTED_COFFEE_BEANS_BAG
        );

        // Duplicate tags
        itemTag(CommonTags.STORAGE_BLOCKS_ITEM_COFFEE).add(
                ModItems.COFFEE_BEANS_BAG
        );

        // Main storage block tag
        itemTag(ConventionalItemTags.STORAGE_BLOCKS)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_COTTON_SEEDS)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_COTTON)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_SEEDS)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_GREEN)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_YELLOW)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_RED)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_PALE_BELL_PEPPER_SEEDS)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_DARK_BELL_PEPPER_SEEDS)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_ORANGE)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_WHITE)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_PINK)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_BLUE)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_PURPLE)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_BLACK)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_CALAMARI)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_COFFEE_BEANS)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_COFFEE)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_ROASTED_COFFEE_BEANS);
    }

    private void addSeedTags() {
        itemTag(CommonTags.SEEDS_COTTON).add(
                ModItems.COTTON_SEEDS
        );
        itemTag(CommonTags.SEEDS_BELL_PEPPER).add(
                ModItems.BELL_PEPPER_SEEDS,
                ModItems.PALE_BELL_PEPPER_SEEDS,
                ModItems.DARK_BELL_PEPPER_SEEDS
        );
        itemTag(CommonTags.SEEDS_COFFEE_BEANS).add(
                ModItems.COFFEE_BEANS
        );
        itemTag(CommonTags.SEEDS_COFFEE).add(
                ModItems.COFFEE_BEANS
        );

        // Main seeds tag
        itemTag(ConventionalTags.SEEDS)
                .addTag(CommonTags.SEEDS_COTTON)
                .addTag(CommonTags.SEEDS_BELL_PEPPER)
                .addTag(CommonTags.SEEDS_COFFEE_BEANS)
                .addTag(CommonTags.SEEDS_COFFEE);
    }

    private void addCropTags() {
        // Crops
        itemTag(CommonTags.CROPS_COTTON).add(
                ModItems.COTTON_BOLL
        );
        itemTag(CommonTags.CROPS_BELL_PEPPER).add(
                ModItems.BELL_PEPPER_GREEN,
                ModItems.BELL_PEPPER_YELLOW,
                ModItems.BELL_PEPPER_RED,
                ModItems.BELL_PEPPER_ORANGE,
                ModItems.BELL_PEPPER_WHITE,
                ModItems.BELL_PEPPER_PINK,
                ModItems.BELL_PEPPER_BLUE,
                ModItems.BELL_PEPPER_PURPLE,
                ModItems.BELL_PEPPER_BLACK
        );

        // Coffee
        itemTag(CommonTags.CROPS_COFFEE_BEANS).add(
                ModItems.COFFEE_BEANS
        );
        itemTag(CommonTags.CROPS_COFFEE).add(
                ModItems.COFFEE_BEANS
        );

        // Potato
        itemTag(CommonTags.CROPS_POTATO).add(
                Items.POTATO,
                ModItems.POTATO_SLICES
        );

        // Main crops tag
        itemTag(ConventionalItemTags.CROPS)
                .addTag(CommonTags.CROPS_COTTON)
                .addTag(CommonTags.CROPS_BELL_PEPPER)
                .addTag(CommonTags.CROPS_COFFEE_BEANS)
                .addTag(CommonTags.CROPS_COFFEE);
    }

    private void addFoodTags() {
        // Fruits
        itemTag(ModTags.Items.FRUITS_AND_BERRIES)
            .addOptionalTag(ConventionalItemTags.FRUIT_FOODS)
            .addOptionalTag(ConventionalItemTags.BERRY_FOODS);

        // Veggies
        itemTag(CommonTags.FOODS_BELL_PEPPER).addTag(
                CommonTags.CROPS_BELL_PEPPER
        ).add(
                ModItems.BELL_PEPPER_SLICE_GREEN,
                ModItems.BELL_PEPPER_SLICE_YELLOW,
                ModItems.BELL_PEPPER_SLICE_RED,
                ModItems.BELL_PEPPER_SLICE_ORANGE,
                ModItems.BELL_PEPPER_SLICE_WHITE,
                ModItems.BELL_PEPPER_SLICE_PINK,
                ModItems.BELL_PEPPER_SLICE_BLUE,
                ModItems.BELL_PEPPER_SLICE_PURPLE,
                ModItems.BELL_PEPPER_SLICE_BLACK
        );

        itemTag(CommonTags.FOODS_POTATO).addTag(
            CommonTags.CROPS_POTATO
        );

        itemTag(CommonTags.FOODS_CARROT).add(
            Items.CARROT
        );

        itemTag(CommonTags.FOODS_BEETROOT).add(
            Items.BEETROOT
        );

        itemTag(ConventionalItemTags.VEGETABLE_FOODS)
            .add(ModItems.POTATO_SLICES)
            .addTag(CommonTags.FOODS_BELL_PEPPER);

        // Fish
        itemTag(CommonTags.FOODS_RAW_CALAMARI)
                .add(
                        ModItems.CALAMARI,
                        ModItems.CALAMARI_SLICE
                );
        addOptionalElement(CommonTags.FOODS_RAW_CALAMARI, compatItem(CompatibilityTags.CULTURAL_DELIGHTS, "squid"));
        addOptionalElement(CommonTags.FOODS_RAW_CALAMARI, compatItem(CompatibilityTags.CULTURAL_DELIGHTS, "glow_squid"));
        addOptionalElement(CommonTags.FOODS_RAW_CALAMARI, compatItem(CompatibilityTags.CULTURAL_DELIGHTS, "raw_calamari"));
        addOptionalElement(CommonTags.FOODS_RAW_CALAMARI, compatItem(CompatibilityTags.MINERS_DELIGHT, "squid"));
        addOptionalElement(CommonTags.FOODS_RAW_CALAMARI, compatItem(CompatibilityTags.MINERS_DELIGHT, "glow_squid"));
        addOptionalElement(CommonTags.FOODS_RAW_CALAMARI, compatItem(CompatibilityTags.MINERS_DELIGHT, "tentacles"));
        itemTag(CommonTags.FOODS_RAW_SQUID).add(
                ModItems.CALAMARI,
                ModItems.CALAMARI_SLICE
        );

        itemTag(CommonTags.FOODS_COOKED_CALAMARI).add(
                ModItems.COOKED_CALAMARI,
                ModItems.COOKED_CALAMARI_SLICE
        );
        itemTag(CommonTags.FOODS_COOKED_SQUID).add(
                ModItems.COOKED_CALAMARI,
                ModItems.COOKED_CALAMARI_SLICE
        );

        itemTag(ConventionalItemTags.RAW_FISH_FOODS).addTag(
                CommonTags.FOODS_RAW_CALAMARI
        );
        itemTag(ConventionalItemTags.COOKED_FISH_FOODS).addTag(
                CommonTags.FOODS_COOKED_CALAMARI
        );

        // Soups
        itemTag(ConventionalItemTags.SOUP_FOODS)
                .add(ModItems.BELL_PEPPER_SOUP, ModItems.CALAMARI_SOUP);

        // Cookies
        itemTag(ConventionalItemTags.COOKIE_FOODS).add(
                ModItems.CHERRY_BLOSSOM_COOKIE,
                ModItems.COFFEE_COOKIE,
                ModItems.SYRUP_COOKIE
        );
    }

    // 26.2: the tag appender only accepts ResourceKeys; this wrapper restores value-based add(ItemLike...).
    private RegistryTagAppender<Item, ItemLike> itemTag(TagKey<Item> tag) {
        return new RegistryTagAppender<>(builder(tag), item -> item.asItem().builtInRegistryHolder().key());
    }

    private Identifier compatItem(String namespace, String itemName) {
        return Identifier.fromNamespaceAndPath(namespace, itemName);
    }

    // Items can't be instantiated during datagen (intrusive holders are disabled), so optional
    // compat entries go through the ResourceKey-based appender. It writes to the same underlying
    // tag builder as itemTag.
    private void addOptionalElement(TagKey<Item> tagKey, Identifier id) {
        itemTag(tagKey).addOptional(ResourceKey.create(Registries.ITEM, id));
    }

}

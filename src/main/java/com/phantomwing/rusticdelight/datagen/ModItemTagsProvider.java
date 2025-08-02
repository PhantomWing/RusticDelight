package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tags.CommonTags;
import com.phantomwing.rusticdelight.tags.CompatibilityTags;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, RusticDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        copy(vectorwing.farmersdelight.common.tag.ModTags.WILD_CROPS, vectorwing.farmersdelight.common.tag.ModTags.WILD_CROPS_ITEM);
        copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);

        addModTags();
        addMinecraftTags();
        addCommonTags();
        addCompatibilityTags();
    }

    private void addModTags() {
        // Cooking oil
        this.tag(ModTags.Items.COOKING_OIL_INGREDIENTS)
                .add(ModItems.COTTON_SEEDS.get(), Items.PUMPKIN_SEEDS)
                .addOptionalTag(CommonTags.SEEDS_CANOLA)
                .addOptionalTag(CommonTags.SEEDS_SUNFLOWER)
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.FRYCOOKS_DELIGHT, "canola_seeds"));

        this.tag(ModTags.Items.COOKING_OIL).add(
                ModItems.COOKING_OIL.get()
        ).addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.FRYCOOKS_DELIGHT, "canola_oil"));

        // Cherry blossom foods
        this.tag(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS).add(
                Items.PINK_PETALS,
                Items.CHERRY_LEAVES,
                Items.CHERRY_SAPLING
        );

        // Calamari
        this.tag(ModTags.Items.CALAMARI_ROLL_INGREDIENTS)
                .addTag(CommonTags.FOODS_RAW_CALAMARI)
                .remove(ModItems.CALAMARI.get());

        // Coffee
        this.tag(ModTags.Items.COFFEE_INGREDIENTS)
                .addTag(CommonTags.CROPS_COFFEE)
                .addTag(CommonTags.CROPS_COFFEE_BEANS)
                .remove(ModItems.COFFEE_BEANS.get()) // We actually don't want to use our coffee beans here, because we require to roast them first.
                .add(ModItems.ROASTED_COFFEE_BEANS.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.FARMERS_RESPITE, "coffee_beans"));

        this.tag(ModTags.Items.COFFEE_FOOD_INGREDIENTS)
                .add(ModItems.COFFEE.get(), ModItems.DARK_COFFEE.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.FARMERS_RESPITE, "coffee"));

        // Syrup
        this.tag(ModTags.Items.SYRUP_INGREDIENTS).add(
                Items.APPLE
        ).addTag(Tags.Items.CROPS_BEETROOT);

        this.tag(ModTags.Items.SYRUP).add(
                ModItems.SYRUP.get()
        );

        this.tag(ModTags.Items.SWEET_LIQUIDS).add(
                Items.HONEY_BOTTLE
        ).addTag(ModTags.Items.SYRUP);

        // Eggs
        this.tag(ModTags.Items.RAW_AND_COOKED_EGGS)
            .addTag(Tags.Items.EGGS)
            .addTag(CommonTags.FOODS_COOKED_EGG);

        // Spring rolls
        this.tag(ModTags.Items.SPRING_ROLL_INGREDIENTS)
            .addTag(CommonTags.FOODS_SAFE_RAW_FISH)
            .addTag(CommonTags.FOODS_RAW_PORK)
            .addTag(CommonTags.FOODS_RAW_CHICKEN)
            .addTag(CommonTags.FOODS_RAW_BEEF)
            .addTag(CommonTags.FOODS_RAW_MUTTON)
            .addTag(Tags.Items.EGGS)
            .addTag(Tags.Items.MUSHROOMS)
            .addTag(CommonTags.FOODS_CARROT)
            .addTag(CommonTags.FOODS_POTATO)
            .addTag(CommonTags.FOODS_BEETROOT)
            .addTag(CommonTags.FOODS_BELL_PEPPER);

        this.tag(ModTags.Items.STUFFED_BELL_PEPPER_INGREDIENTS)
            .addTag(CommonTags.FOODS_SAFE_RAW_FISH)
            .addTag(CommonTags.FOODS_RAW_PORK)
            .addTag(CommonTags.FOODS_RAW_CHICKEN)
            .addTag(CommonTags.FOODS_RAW_BEEF)
            .addTag(CommonTags.FOODS_RAW_MUTTON)
            .addTag(Tags.Items.EGGS)
            .addTag(Tags.Items.MUSHROOMS)
            .addTag(CommonTags.FOODS_CARROT)
            .addTag(CommonTags.FOODS_POTATO)
            .addTag(CommonTags.FOODS_BEETROOT)
            .addTag(CommonTags.FOODS_TOMATO)
            .addTag(CommonTags.FOODS_CABBAGE)
            .addTag(CommonTags.FOODS_ONION)
            .addOptionalTag(CompatibilityTags.BREWIN_AND_CHEWIN_CHEESE_WEDGE);
    }

    private void addMinecraftTags() {
        // Villagers
        this.tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(
            ModItems.COTTON_SEEDS.get(),
            ModItems.BELL_PEPPER_SEEDS.get(),
            ModItems.COFFEE_BEANS.get()
        );

        // Animal food
        this.tag(ItemTags.CHICKEN_FOOD).add(
            ModItems.COTTON_SEEDS.get(),
            ModItems.BELL_PEPPER_SEEDS.get()
        );
        this.tag(ItemTags.PARROT_FOOD).add(
            ModItems.COTTON_SEEDS.get(),
            ModItems.BELL_PEPPER_SEEDS.get()
        );
        this.tag(ItemTags.CAT_FOOD).add(
            ModItems.CALAMARI.get()
        );
        this.tag(ItemTags.OCELOT_FOOD).add(
            ModItems.CALAMARI.get()
        );
        this.tag(ItemTags.PIG_FOOD).addTag(
            CommonTags.CROPS_BELL_PEPPER
        );

        // Fish
        this.tag(ItemTags.FISHES).add(
            ModItems.CALAMARI.get(),
            ModItems.COOKED_CALAMARI.get()
        );

        // Piglins
        this.tag(ItemTags.PIGLIN_LOVED).add(
            ModItems.GOLDEN_COFFEE_BEANS.get()
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
        this.tag(CompatibilityTags.CREATE_UPRIGHT_ON_BELT).add(
            ModItems.COOKING_OIL.get(),
            ModItems.SYRUP.get(),
            ModItems.COFFEE.get(),
            ModItems.MILK_COFFEE.get(),
            ModItems.CHOCOLATE_COFFEE.get(),
            ModItems.HONEY_COFFEE.get(),
            ModItems.SYRUP_COFFEE.get(),
            ModItems.DARK_COFFEE.get(),
            ModItems.CHERRY_BLOSSOM_CHEESECAKE.get(),
            ModItems.SYRUP_CHEESECAKE.get()
        );

        // Farmer's Delight
        this.tag(vectorwing.farmersdelight.common.tag.ModTags.CABBAGE_ROLL_INGREDIENTS)
            .add(ModItems.POTATO_SLICES.get())
            .addTag(CommonTags.FOODS_BELL_PEPPER);

        // Serene Seasons
        this.tag(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS).add(
            ModItems.COTTON_SEEDS.get()
        );
        this.tag(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS).add(
            ModItems.COTTON_SEEDS.get(),
            ModItems.BELL_PEPPER_SEEDS.get(),
            ModItems.COFFEE_BEANS.get()
        );
        this.tag(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS).add(
                ModItems.BELL_PEPPER_SEEDS.get(),
                ModItems.COFFEE_BEANS.get()
        );

        // Miner's Delight
        this.tag(CompatibilityTags.MINERS_DELIGHT_TENTACLES).add(
                ModItems.CALAMARI.get(),
                ModItems.CALAMARI_SLICE.get(),
                ModItems.COOKED_CALAMARI.get(),
                ModItems.COOKED_CALAMARI_SLICE.get()
        );

        // Frycook's Delight
        this.tag(CompatibilityTags.FISH_SLICES).add(
                ModItems.CALAMARI_SLICE.get()
        );
        this.tag(CompatibilityTags.HAS_FISH_SLICE).add(
                ModItems.CALAMARI.get()
        );

        // Brewin' and Chewin
        this.tag(CompatibilityTags.BREWIN_AND_CHEWIN_PIZZA_TOPPING).addTag(CommonTags.FOODS_BELL_PEPPER);
    }

    private void addStorageBlockTags() {
        // Storage blocks
        this.tag(CommonTags.STORAGE_BLOCKS_ITEM_COTTON_SEEDS).add(
                ModItems.COTTON_SEEDS_BAG.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_ITEM_COTTON).add(
                ModItems.COTTON_BOLL_CRATE.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_SEEDS).add(
                ModItems.BELL_PEPPER_SEEDS_BAG.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_GREEN).add(
                ModItems.BELL_PEPPER_GREEN_CRATE.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_YELLOW).add(
                ModItems.BELL_PEPPER_YELLOW_CRATE.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_RED).add(
                ModItems.BELL_PEPPER_RED_CRATE.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_ITEM_COFFEE_BEANS).add(
                ModItems.COFFEE_BEANS_BAG.get()
        );
        this.tag(CommonTags.STORAGE_BLOCKS_ITEM_ROASTED_COFFEE_BEANS).add(
                ModItems.ROASTED_COFFEE_BEANS_BAG.get()
        );

        // Duplicate tags
        this.tag(CommonTags.STORAGE_BLOCKS_ITEM_COFFEE).add(
                ModItems.COFFEE_BEANS_BAG.get()
        );

        // Main storage block tag
        this.tag(Tags.Items.STORAGE_BLOCKS)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_COTTON_SEEDS)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_COTTON)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_SEEDS)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_GREEN)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_YELLOW)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_BELL_PEPPER_RED)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_COFFEE_BEANS)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_COFFEE)
                .addTag(CommonTags.STORAGE_BLOCKS_ITEM_ROASTED_COFFEE_BEANS);
    }

    private void addSeedTags() {
        this.tag(CommonTags.SEEDS_COTTON).add(
                ModItems.COTTON_SEEDS.get()
        );
        this.tag(CommonTags.SEEDS_BELL_PEPPER).add(
                ModItems.BELL_PEPPER_SEEDS.get()
        );
        this.tag(CommonTags.SEEDS_COFFEE_BEANS).add(
                ModItems.COFFEE_BEANS.get()
        );
        this.tag(CommonTags.SEEDS_COFFEE).add(
                ModItems.COFFEE_BEANS.get()
        );

        // Main seeds tag
        this.tag(Tags.Items.SEEDS)
                .addTag(CommonTags.SEEDS_COTTON)
                .addTag(CommonTags.SEEDS_BELL_PEPPER)
                .addTag(CommonTags.SEEDS_COFFEE_BEANS)
                .addTag(CommonTags.SEEDS_COFFEE);
    }

    private void addCropTags() {
        // Crops
        this.tag(CommonTags.CROPS_COTTON).add(
                ModItems.COTTON_BOLL.get()
        );
        this.tag(CommonTags.CROPS_BELL_PEPPER).add(
                ModItems.BELL_PEPPER_GREEN.get(),
                ModItems.BELL_PEPPER_YELLOW.get(),
                ModItems.BELL_PEPPER_RED.get()
        );

        // Coffee
        this.tag(CommonTags.CROPS_COFFEE_BEANS).add(
                ModItems.COFFEE_BEANS.get()
        );
        this.tag(CommonTags.CROPS_COFFEE).add(
                ModItems.COFFEE_BEANS.get()
        );

        // Main crops tag
        this.tag(Tags.Items.CROPS)
                .addTag(CommonTags.CROPS_COTTON)
                .addTag(CommonTags.CROPS_BELL_PEPPER)
                .addTag(CommonTags.CROPS_COFFEE_BEANS)
                .addTag(CommonTags.CROPS_COFFEE);
    }

    private void addFoodTags() {
        // Fruits
        this.tag(Tags.Items.FOODS_FRUIT).add(
                Items.MELON_SLICE
        ).addTag(Tags.Items.FOODS_BERRY);

        // Veggies
        this.tag(CommonTags.FOODS_BELL_PEPPER).addTag(
                CommonTags.CROPS_BELL_PEPPER
        ).add(
                ModItems.BELL_PEPPER_SLICE_GREEN.get(),
                ModItems.BELL_PEPPER_SLICE_YELLOW.get(),
                ModItems.BELL_PEPPER_SLICE_RED.get()
        );

        this.tag(CommonTags.FOODS_POTATO).add(
            Items.POTATO,
            ModItems.POTATO_SLICES.get()
        );

        this.tag(CommonTags.FOODS_CARROT).add(
            Items.CARROT
        );

        this.tag(CommonTags.FOODS_BEETROOT).add(
            Items.BEETROOT
        );

        this.tag(Tags.Items.FOODS_VEGETABLE)
            .add(ModItems.POTATO_SLICES.get())
            .addTag(CommonTags.FOODS_BELL_PEPPER);

        // Fish
        this.tag(CommonTags.FOODS_RAW_CALAMARI)
                .add(
                        ModItems.CALAMARI.get(),
                        ModItems.CALAMARI_SLICE.get()
                )
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.CULTURAL_DELIGHTS,"squid"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.CULTURAL_DELIGHTS,"glow_squid"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.CULTURAL_DELIGHTS,"raw_calamari"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.MINERS_DELIGHT,"squid"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.MINERS_DELIGHT,"glow_squid"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(CompatibilityTags.MINERS_DELIGHT,"tentacles"));
        this.tag(CommonTags.FOODS_RAW_SQUID).add(
                ModItems.CALAMARI.get(),
                ModItems.CALAMARI_SLICE.get()
        );

        this.tag(CommonTags.FOODS_COOKED_CALAMARI).add(
                ModItems.COOKED_CALAMARI.get(),
                ModItems.COOKED_CALAMARI_SLICE.get()
        );
        this.tag(CommonTags.FOODS_COOKED_SQUID).add(
                ModItems.COOKED_CALAMARI.get(),
                ModItems.COOKED_CALAMARI_SLICE.get()
        );

        this.tag(Tags.Items.FOODS_RAW_FISH).addTag(
                CommonTags.FOODS_RAW_CALAMARI
        );
        this.tag(Tags.Items.FOODS_COOKED_FISH).addTag(
                CommonTags.FOODS_COOKED_CALAMARI
        );

        // Cookies
        this.tag(CommonTags.FOODS_COOKIE).add(
                ModItems.CHERRY_BLOSSOM_COOKIE.get(),
                ModItems.COFFEE_COOKIE.get(),
                ModItems.SYRUP_COOKIE.get()
        );
    }
}

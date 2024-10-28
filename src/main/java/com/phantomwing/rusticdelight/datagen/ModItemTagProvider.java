package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tag.CommonTags;
import com.phantomwing.rusticdelight.tag.CompatibilityTags;
import com.phantomwing.rusticdelight.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        addModTags();
        addMinecraftTags();
        addNeoForgeTags();
        addCommonTags();
        addCompatibilityTags();
    }

    private void addModTags() {
        getOrCreateTagBuilder(ModTags.Items.COOKING_OIL_INGREDIENTS).add(
                ModItems.COTTON_SEEDS
        ).addOptional(Identifier.of(CompatibilityTags.FRYCOOKS_DELIGHT, "canola_seeds"));

        getOrCreateTagBuilder(ModTags.Items.COOKING_OIL).add(
                ModItems.COOKING_OIL
        ).addOptional(Identifier.of(CompatibilityTags.FRYCOOKS_DELIGHT, "canola_oil"));

        getOrCreateTagBuilder(ModTags.Items.CHERRY_BLOSSOM_INGREDIENTS).add(
                Items.PINK_PETALS,
                Items.CHERRY_LEAVES,
                Items.CHERRY_SAPLING
        );

        getOrCreateTagBuilder(ModTags.Items.CALAMARI_ROLL_INGREDIENTS).add(
                ModItems.CALAMARI_SLICE
        ).addOptional(Identifier.of(CompatibilityTags.MINERS_DELIGHT, "tentacles"));
    }

    private void addMinecraftTags() {
        // Villagers
        getOrCreateTagBuilder(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(
                ModItems.COTTON_SEEDS,
                ModItems.BELL_PEPPER_SEEDS,
                ModItems.COFFEE_BEANS
        );

        // Animal food
        getOrCreateTagBuilder(ItemTags.CHICKEN_FOOD).add(
                ModItems.COTTON_SEEDS,
                ModItems.BELL_PEPPER_SEEDS
        );
        getOrCreateTagBuilder(ItemTags.PARROT_FOOD).add(
                ModItems.COTTON_SEEDS,
                ModItems.BELL_PEPPER_SEEDS
        );
        getOrCreateTagBuilder(ItemTags.CAT_FOOD).add(
                ModItems.CALAMARI
        );
        getOrCreateTagBuilder(ItemTags.OCELOT_FOOD).add(
                ModItems.CALAMARI
        );
        getOrCreateTagBuilder(ItemTags.PIG_FOOD).addTag(
                CommonTags.CROPS_BELL_PEPPER
        );

        // Fish
        getOrCreateTagBuilder(ItemTags.FISHES).add(
                ModItems.CALAMARI,
                ModItems.COOKED_CALAMARI
        );

        // Piglins
        getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED).add(
                ModItems.GOLDEN_COFFEE_BEANS
        );
    }

    private void addNeoForgeTags() {
        getOrCreateTagBuilder(CommonTags.CROPS)
                .addTag(CommonTags.CROPS_COTTON)
                .addTag(CommonTags.CROPS_BELL_PEPPER)
                .addTag(CommonTags.CROPS_COFFEE);

        getOrCreateTagBuilder(CommonTags.FOODS_RAW_FISH).addTag(
                CommonTags.FOODS_RAW_CALAMARI
        );
        getOrCreateTagBuilder(CommonTags.FOODS_COOKED_FISH).addTag(
                CommonTags.FOODS_COOKED_CALAMARI
        );
        getOrCreateTagBuilder(CommonTags.SEEDS).add(
                ModItems.COTTON_SEEDS,
                ModItems.BELL_PEPPER_SEEDS,
                ModItems.COFFEE_BEANS
        );
        getOrCreateTagBuilder(CommonTags.FOODS_FRUIT).add(
                Items.MELON_SLICE
        ).addOptionalTag(CommonTags.FOODS_BERRY);
        getOrCreateTagBuilder(CommonTags.FOODS_VEGETABLE).add(
                ModItems.POTATO_SLICES
        ).addTag(CommonTags.FOODS_BELL_PEPPER);
    }

    private void addCommonTags() {
        // Crops
        getOrCreateTagBuilder(CommonTags.CROPS_COTTON).add(
                ModItems.COTTON_BOLL
        );
        getOrCreateTagBuilder(CommonTags.CROPS_BELL_PEPPER).add(
                ModItems.BELL_PEPPER_GREEN,
                ModItems.BELL_PEPPER_YELLOW,
                ModItems.BELL_PEPPER_RED
        );
        getOrCreateTagBuilder(CommonTags.CROPS_COFFEE).add(
                ModItems.COFFEE_BEANS
        );

        // Foods
        getOrCreateTagBuilder(CommonTags.FOODS_WATER).add(
                Items.WATER_BUCKET
                // Water Bottle (is added as an Ingredient at a later stage, because of NBT tags)
        );
        getOrCreateTagBuilder(CommonTags.FOODS_BELL_PEPPER).addTag(
                CommonTags.CROPS_BELL_PEPPER
        );
        getOrCreateTagBuilder(CommonTags.FOODS_RAW_CALAMARI)
                .add(
                        ModItems.CALAMARI,
                        ModItems.CALAMARI_SLICE
                )
                .addOptional(Identifier.of(CompatibilityTags.CULTURAL_DELIGHTS,"squid"))
                .addOptional(Identifier.of(CompatibilityTags.CULTURAL_DELIGHTS,"glow_squid"))
                .addOptional(Identifier.of(CompatibilityTags.CULTURAL_DELIGHTS,"raw_calamari"))
                .addOptional(Identifier.of(CompatibilityTags.MINERS_DELIGHT,"squid"))
                .addOptional(Identifier.of(CompatibilityTags.MINERS_DELIGHT,"glow_squid"))
                .addOptional(Identifier.of(CompatibilityTags.MINERS_DELIGHT,"tentacles"));

        getOrCreateTagBuilder(CommonTags.FOODS_COOKED_CALAMARI).add(
                ModItems.COOKED_CALAMARI,
                ModItems.COOKED_CALAMARI_SLICE
        );
        getOrCreateTagBuilder(CommonTags.FOODS_POTATO).add(
                Items.POTATO,
                ModItems.POTATO_SLICES
        );
        getOrCreateTagBuilder(CommonTags.FOODS_CARROT).add(
                Items.CARROT
        );
    }

    private void addCompatibilityTags() {
        // Create
        getOrCreateTagBuilder(CompatibilityTags.CREATE_UPRIGHT_ON_BELT).add(
                ModItems.COOKING_OIL,
                ModItems.COFFEE,
                ModItems.CHERRY_BLOSSOM_CHEESECAKE
        );

        // Farmer's Delight
        getOrCreateTagBuilder(vectorwing.farmersdelight.common.tag.ModTags.CABBAGE_ROLL_INGREDIENTS).add(
                ModItems.POTATO_SLICES
        );

        // Serene Seasons
        getOrCreateTagBuilder(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS).add(
                ModItems.COTTON_SEEDS
        );
        getOrCreateTagBuilder(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS).add(
                ModItems.COTTON_SEEDS,
                ModItems.BELL_PEPPER_SEEDS,
                ModItems.COFFEE_BEANS
        );
        getOrCreateTagBuilder(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS).add(
                ModItems.BELL_PEPPER_SEEDS,
                ModItems.COFFEE_BEANS
        );

        // Miner's Delight
        getOrCreateTagBuilder(CompatibilityTags.MINERS_DELIGHT_TENTACLES).add(
                ModItems.CALAMARI,
                ModItems.CALAMARI_SLICE,
                ModItems.COOKED_CALAMARI,
                ModItems.COOKED_CALAMARI_SLICE
        );

        // Frycook's Delight
        getOrCreateTagBuilder(CompatibilityTags.FISH_SLICES).add(
                ModItems.CALAMARI_SLICE
        );
        getOrCreateTagBuilder(CompatibilityTags.HAS_FISH_SLICE).add(
                ModItems.CALAMARI
        );
    }
}

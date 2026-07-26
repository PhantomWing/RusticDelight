package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.block.custom.*;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    // Actually add our loot tables.
    @Override
    public void generate() {
        dropCrop(
                ModBlocks.COTTON_CROP, CottonCropBlock.AGE, CottonCropBlock.MAX_AGE,
                ModItems.COTTON_SEEDS, UniformGenerator.between(1.0F, 3.0F),
                ModItems.COTTON_BOLL, UniformGenerator.between(1.0F, 3.0F));
        dropBellPepperCrop(ModBlocks.BELL_PEPPER_CROP);
        dropPaleBellPepperCrop(ModBlocks.PALE_BELL_PEPPER_CROP);
        dropDarkBellPepperCrop(ModBlocks.DARK_BELL_PEPPER_CROP);
        dropCrop(
                ModBlocks.COFFEE_CROP, CoffeeCropBlock.AGE, CoffeeCropBlock.MAX_AGE,
                ModItems.COFFEE_BEANS, UniformGenerator.between(1.0F, 1.0F),
                ModItems.COFFEE_BEANS, UniformGenerator.between(1.0F, 4.0F));

        dropWildCrop(ModBlocks.WILD_COTTON, ModItems.COTTON_SEEDS, ModItems.COTTON_BOLL);
        dropWildCrop(ModBlocks.WILD_BELL_PEPPERS, ModItems.BELL_PEPPER_SEEDS, ModItems.BELL_PEPPER_RED);
        dropWildCrop(ModBlocks.WILD_PALE_BELL_PEPPERS, ModItems.PALE_BELL_PEPPER_SEEDS, ModItems.BELL_PEPPER_PINK);
        dropWildCrop(ModBlocks.WILD_DARK_BELL_PEPPERS, ModItems.DARK_BELL_PEPPER_SEEDS, ModItems.BELL_PEPPER_PURPLE);
        dropWildCrop(ModBlocks.WILD_COFFEE, ModItems.COFFEE_BEANS, ModItems.COFFEE_BEANS);

        dropPottedFlower(ModBlocks.POTTED_WILD_COTTON, ModBlocks.WILD_COTTON);
        dropPottedFlower(ModBlocks.POTTED_WILD_BELL_PEPPERS, ModBlocks.WILD_BELL_PEPPERS);
        dropPottedFlower(ModBlocks.POTTED_WILD_PALE_BELL_PEPPERS, ModBlocks.WILD_PALE_BELL_PEPPERS);
        dropPottedFlower(ModBlocks.POTTED_WILD_DARK_BELL_PEPPERS, ModBlocks.WILD_DARK_BELL_PEPPERS);
        dropPottedFlower(ModBlocks.POTTED_WILD_COFFEE, ModBlocks.WILD_COFFEE);

        dropSelf(ModBlocks.COTTON_SEEDS_BAG);
        dropSelf(ModBlocks.BELL_PEPPER_SEEDS_BAG);
        dropSelf(ModBlocks.PALE_BELL_PEPPER_SEEDS_BAG);
        dropSelf(ModBlocks.DARK_BELL_PEPPER_SEEDS_BAG);
        dropSelf(ModBlocks.COFFEE_BEANS_BAG);
        dropSelf(ModBlocks.ROASTED_COFFEE_BEANS_BAG);

        dropSelf(ModBlocks.COTTON_BOLL_CRATE);
        dropSelf(ModBlocks.BELL_PEPPER_GREEN_CRATE);
        dropSelf(ModBlocks.BELL_PEPPER_YELLOW_CRATE);
        dropSelf(ModBlocks.BELL_PEPPER_RED_CRATE);
        dropSelf(ModBlocks.BELL_PEPPER_ORANGE_CRATE);
        dropSelf(ModBlocks.BELL_PEPPER_WHITE_CRATE);
        dropSelf(ModBlocks.BELL_PEPPER_PINK_CRATE);
        dropSelf(ModBlocks.BELL_PEPPER_BLUE_CRATE);
        dropSelf(ModBlocks.BELL_PEPPER_PURPLE_CRATE);
        dropSelf(ModBlocks.BELL_PEPPER_BLACK_CRATE);
        dropSelf(ModBlocks.CALAMARI_CRATE);

        // Bell pepper blocks drop 1-9 slices of their color (not the block itself).
        dropSlices(ModBlocks.BELL_PEPPER_GREEN_BLOCK, ModItems.BELL_PEPPER_SLICE_GREEN);
        dropSlices(ModBlocks.BELL_PEPPER_YELLOW_BLOCK, ModItems.BELL_PEPPER_SLICE_YELLOW);
        dropSlices(ModBlocks.BELL_PEPPER_RED_BLOCK, ModItems.BELL_PEPPER_SLICE_RED);
        dropSlices(ModBlocks.BELL_PEPPER_ORANGE_BLOCK, ModItems.BELL_PEPPER_SLICE_ORANGE);
        dropSlices(ModBlocks.BELL_PEPPER_WHITE_BLOCK, ModItems.BELL_PEPPER_SLICE_WHITE);
        dropSlices(ModBlocks.BELL_PEPPER_PINK_BLOCK, ModItems.BELL_PEPPER_SLICE_PINK);
        dropSlices(ModBlocks.BELL_PEPPER_BLUE_BLOCK, ModItems.BELL_PEPPER_SLICE_BLUE);
        dropSlices(ModBlocks.BELL_PEPPER_PURPLE_BLOCK, ModItems.BELL_PEPPER_SLICE_PURPLE);
        dropSlices(ModBlocks.BELL_PEPPER_BLACK_BLOCK, ModItems.BELL_PEPPER_SLICE_BLACK);

        dropFoodBlock(ModBlocks.SYRUP_CHEESECAKE, PieBlock.BITES);
        dropFoodBlock(ModBlocks.CHERRY_BLOSSOM_CHEESECAKE, PieBlock.BITES);
        dropFoodBlock(ModBlocks.COFFEE_CHEESECAKE, PieBlock.BITES);

        dropPancakeBlock(ModBlocks.PANCAKES, ModItems.PANCAKE);
        dropPancakeBlock(ModBlocks.HONEY_PANCAKES, ModItems.HONEY_PANCAKE);
        dropPancakeBlock(ModBlocks.CHOCOLATE_PANCAKES, ModItems.CHOCOLATE_PANCAKE);
        dropPancakeBlock(ModBlocks.CHERRY_BLOSSOM_PANCAKES, ModItems.CHERRY_BLOSSOM_PANCAKE);
        dropPancakeBlock(ModBlocks.VEGETABLE_PANCAKES, ModItems.VEGETABLE_PANCAKE);
        dropPancakeBlock(ModBlocks.PUMPKIN_PANCAKES, ModItems.PUMPKIN_PANCAKE);
        dropPancakeBlock(ModBlocks.COFFEE_PANCAKES, ModItems.COFFEE_PANCAKE);

        dropFoodBlock(ModBlocks.RICE_ROLL_ROYALE, RiceRollRoyaleBlock.ROLL_SERVINGS, RiceRollRoyaleBlock.MAX_SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.BELL_PEPPER_MEDLEY, BellPepperMedleyBlock.MEDLEY_SERVINGS, BellPepperMedleyBlock.MAX_SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.PALE_BELL_PEPPER_MEDLEY, BellPepperMedleyBlock.MEDLEY_SERVINGS, BellPepperMedleyBlock.MAX_SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.DARK_BELL_PEPPER_MEDLEY, BellPepperMedleyBlock.MEDLEY_SERVINGS, BellPepperMedleyBlock.MAX_SERVINGS, Items.BOWL);
    }

    // Vanilla's LootTableProvider stamps every table with a random sequence, but Fabric's does not.
    // Set it here so drops roll the same way they do on the NeoForge build.
    @Override
    public void add(Block block, LootTable.Builder builder) {
        super.add(block, builder.setRandomSequence(block.getLootTable().location()));
    }

    private void dropPottedFlower(Block pottedBlock, Block flowerBlock) {
        this.add(pottedBlock, createPotFlowerItemTable(flowerBlock));
    }

    private void dropCrop(Block block, IntegerProperty age, int maxAge, ItemLike seedsItem, NumberProvider seedsCount, ItemLike cropItem, NumberProvider cropCount) {
        this.add(block, blockParam -> createCropDrops(blockParam, age, maxAge, seedsItem, seedsCount, cropItem, cropCount));
    }

    private void dropBellPepperCrop(Block block) {
        this.add(block, this::createBellPepperDrops);
    }

    private void dropPaleBellPepperCrop(Block block) {
        this.add(block, this::createPaleBellPepperDrops);
    }

    private void dropDarkBellPepperCrop(Block block) {
        this.add(block, this::createDarkBellPepperDrops);
    }

    private void dropFoodBlock(Block block, IntegerProperty servings) {
        this.add(block, blockParam -> createFoodBlockDrops(blockParam, servings, 0, null));
    }

    private void dropFoodBlock(Block block, IntegerProperty servings, ItemLike containerItem) {
        this.add(block, blockParam -> createFoodBlockDrops(blockParam, servings, 0, containerItem));
    }

    private void dropFoodBlock(Block block, IntegerProperty servings, int defaultServings, ItemLike containerItem) {
        this.add(block, blockParam -> createFoodBlockDrops(blockParam, servings, defaultServings, containerItem));
    }

    private void dropPancakeBlock(Block block, ItemLike pancakeItem) {
        this.add(block, blockParam -> createPancakeDrops(blockParam, pancakeItem));
    }

    private LootTable.Builder createCropDrops(Block cropBlock, IntegerProperty age, int maxAge, ItemLike seedsItem, NumberProvider seedsCount, ItemLike cropItem, NumberProvider cropCount) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        // Condition that checks if the crop is fully grown.
        LootItemCondition.Builder dropGrownCropCondition = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(cropBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(age, maxAge));

        return this.applyExplosionDecay(
                cropBlock,
                LootTable.lootTable()
                        // When not fully grown, drop the original seed.
                        .withPool(LootPool.lootPool()
                                .when(InvertedLootItemCondition.invert(dropGrownCropCondition))
                                .add(LootItem.lootTableItem(seedsItem))
                        )
                        // When fully grown, drop additional seeds (including a Fortune bonus).
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .add(LootItem.lootTableItem(seedsItem)
                                        .apply(SetItemCountFunction.setCount(seedsCount))
                                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                )
                        )
                        // When fully grown, also drop the full-grown crop items (including a Fortune bonus).
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .add(LootItem.lootTableItem(cropItem)
                                        .apply(SetItemCountFunction.setCount(cropCount))
                                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                )
                        )
        );
    }

    private LootTable.Builder createBellPepperDrops(Block cropBlock) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        // Condition that checks if the crop is fully grown.
        LootItemCondition.Builder dropGrownCropCondition = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(cropBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BellPepperCropBlock.AGE, BellPepperCropBlock.MAX_AGE));

        return this.applyExplosionDecay(
                cropBlock,
                LootTable.lootTable()
                        // When not fully grown, drop the original seed.
                        .withPool(LootPool.lootPool()
                                .when(InvertedLootItemCondition.invert(dropGrownCropCondition))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_SEEDS))
                        )
                        // When fully grown, drop additional seeds (including a Fortune bonus).
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_SEEDS)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                )
                        )
                        // When fully grown, also drop a bell pepper.
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_RED).setWeight(6)) // Red bell peppers are more common
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_GREEN))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_YELLOW))
                        )
                        // When fully grown, potentially drop an additional green bell pepper.
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .when(LootItemRandomChanceCondition.randomChance(0.15f))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_GREEN))
                        )
                        // Finally, when fully grown, potentially drop additional yellow bell pepper.
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .when(LootItemRandomChanceCondition.randomChance(0.15f))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_YELLOW))
                        )
        );
    }

    private LootTable.Builder createPaleBellPepperDrops(Block cropBlock) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        LootItemCondition.Builder dropGrownCropCondition = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(cropBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BellPepperCropBlock.AGE, BellPepperCropBlock.MAX_AGE));

        return this.applyExplosionDecay(
                cropBlock,
                LootTable.lootTable()
                        // When not fully grown, drop the original seed.
                        .withPool(LootPool.lootPool()
                                .when(InvertedLootItemCondition.invert(dropGrownCropCondition))
                                .add(LootItem.lootTableItem(ModItems.PALE_BELL_PEPPER_SEEDS))
                        )
                        // When fully grown, drop additional seeds (including a Fortune bonus).
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .add(LootItem.lootTableItem(ModItems.PALE_BELL_PEPPER_SEEDS)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                )
                        )
                        // When fully grown, drop one bell pepper (orange/white/pink, equal chance).
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_ORANGE))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_WHITE))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_PINK))
                        )
                        // Two independent 15% chances for a bonus bell pepper of a random color.
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .when(LootItemRandomChanceCondition.randomChance(0.15f))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_ORANGE))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_WHITE))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_PINK))
                        )
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .when(LootItemRandomChanceCondition.randomChance(0.15f))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_ORANGE))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_WHITE))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_PINK))
                        )
        );
    }

    private LootTable.Builder createDarkBellPepperDrops(Block cropBlock) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        LootItemCondition.Builder dropGrownCropCondition = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(cropBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BellPepperCropBlock.AGE, BellPepperCropBlock.MAX_AGE));

        return this.applyExplosionDecay(
                cropBlock,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .when(InvertedLootItemCondition.invert(dropGrownCropCondition))
                                .add(LootItem.lootTableItem(ModItems.DARK_BELL_PEPPER_SEEDS))
                        )
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .add(LootItem.lootTableItem(ModItems.DARK_BELL_PEPPER_SEEDS)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                )
                        )
                        // When fully grown, drop one bell pepper (blue/purple/black, equal chance).
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_BLUE))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_PURPLE))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_BLACK))
                        )
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .when(LootItemRandomChanceCondition.randomChance(0.15f))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_BLUE))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_PURPLE))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_BLACK))
                        )
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .when(LootItemRandomChanceCondition.randomChance(0.15f))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_BLUE))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_PURPLE))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_BLACK))
                        )
        );
    }

    private void dropWildCrop(Block block, ItemLike seedsItem, ItemLike cropItem) {
        this.add(block, blockParam -> createWildCropDrops(blockParam, seedsItem, cropItem));
    }

    // Drops 1-9 slices of the matching color (melon-style), never the block itself.
    private void dropSlices(Block block, ItemLike slice) {
        this.add(block, createSingleItemTable(slice, UniformGenerator.between(1.0F, 9.0F)));
    }

    private LootTable.Builder createWildCropDrops(Block block, ItemLike seedsItem, ItemLike cropItem) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return this.applyExplosionDecay(
                block,
                LootTable.lootTable()
                        // When using Silk Touch, drop the actual block.
                        .withPool(LootPool.lootPool()
                                .when(hasShearsOrSilkTouch())
                                .add(LootItem.lootTableItem(block))
                        )
                        // Else, drop the seeds item (including a Fortune bonus).
                        .withPool(LootPool.lootPool()
                                .when(hasShearsOrSilkTouch().invert())
                                .add(LootItem.lootTableItem(seedsItem)
                                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))))
                        )
                        // Additionally, add a random chance to drop the grown crop item.
                        .withPool(LootPool.lootPool()
                                .when(AllOfCondition.allOf(hasShearsOrSilkTouch().invert(), LootItemRandomChanceCondition.randomChance(0.3f)))
                                .add(LootItem.lootTableItem(cropItem))
                        )
        );
    }

    /**
     * A pancake stack holds 1 to {@link PancakeBlock#MAX_TOTAL_SERVINGS} pancakes. Breaking one that
     * is exactly a crafted plate returns the placeable block; any other height returns the loose
     * pancakes plus the bowl, which previously went missing entirely.
     */
    private LootTable.Builder createPancakeDrops(Block block, ItemLike pancakeItem) {
        LootItemCondition.Builder isCraftedPlate = servingsIs(block, 0);

        LootTable.Builder lootTable = LootTable.lootTable()
                // An untouched plate drops the block itself, matching what the recipe produces.
                .withPool(LootPool.lootPool().when(isCraftedPlate).add(LootItem.lootTableItem(block)));

        for (int servings = 1; servings < PancakeBlock.MAX_TOTAL_SERVINGS; servings++) {
            lootTable.withPool(LootPool.lootPool()
                    .when(servingsIs(block, servings))
                    .add(LootItem.lootTableItem(pancakeItem)
                            .apply(SetItemCountFunction.setCount(
                                    ConstantValue.exactly(PancakeBlock.pancakesPresentFor(servings))))));
        }

        // The plate is only left over once the stack is no longer a whole crafted block.
        lootTable.withPool(LootPool.lootPool()
                .when(InvertedLootItemCondition.invert(isCraftedPlate))
                .add(LootItem.lootTableItem(Items.BOWL)));

        return this.applyExplosionDecay(block, lootTable);
    }

    private static LootItemCondition.Builder servingsIs(Block block, int servings) {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties()
                        .hasProperty(PancakeBlock.SERVINGS, servings));
    }

    private LootTable.Builder createFoodBlockDrops(Block block, IntegerProperty servings, int defaultServings, ItemLike containerItem) {
        // Condition that checks if any servings have been taken.
        LootItemCondition.Builder noServingsTaken = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(servings, defaultServings));

        LootTable.Builder lootTable = this.applyExplosionDecay(
                block,
                LootTable.lootTable()
                        // If no servings have been taken yet, drop the block.
                        .withPool(LootPool.lootPool()
                                .when(noServingsTaken)
                                .add(LootItem.lootTableItem(block))
                        )

        );

        if (containerItem != null)
        {
            lootTable.withPool(LootPool.lootPool()
                            .when(InvertedLootItemCondition.invert(noServingsTaken))
                            .add(LootItem.lootTableItem(containerItem)));
        }

        return lootTable;
    }
}

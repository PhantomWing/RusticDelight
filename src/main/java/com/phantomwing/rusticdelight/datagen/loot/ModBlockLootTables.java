package com.phantomwing.rusticdelight.datagen.loot;

import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.block.custom.*;
import com.phantomwing.rusticdelight.item.ModItems;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
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
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    // Actually add our loot tables.
    @Override
    protected void generate() {
        dropCrop(
                ModBlocks.COTTON_CROP.get(), CottonCropBlock.AGE, CottonCropBlock.MAX_AGE,
                ModItems.COTTON_SEEDS.get(), UniformGenerator.between(1.0F, 3.0F),
                ModItems.COTTON_BOLL.get(), UniformGenerator.between(1.0F, 3.0F));
        dropBellPepperCrop(ModBlocks.BELL_PEPPER_CROP.get());
        dropPaleBellPepperCrop(ModBlocks.PALE_BELL_PEPPER_CROP.get());
        dropDarkBellPepperCrop(ModBlocks.DARK_BELL_PEPPER_CROP.get());
        dropCrop(
                ModBlocks.COFFEE_CROP.get(), CoffeeCropBlock.AGE, CoffeeCropBlock.MAX_AGE,
                ModItems.COFFEE_BEANS.get(), UniformGenerator.between(1.0F, 1.0F),
                ModItems.COFFEE_BEANS.get(), UniformGenerator.between(1.0F, 4.0F));

        dropWildCrop(ModBlocks.WILD_COTTON.get(), ModItems.COTTON_SEEDS.get(), ModItems.COTTON_BOLL.get());
        dropWildCrop(ModBlocks.WILD_BELL_PEPPERS.get(), ModItems.BELL_PEPPER_SEEDS.get(), ModItems.BELL_PEPPER_RED.get());
        dropWildCrop(ModBlocks.WILD_PALE_BELL_PEPPERS.get(), ModItems.PALE_BELL_PEPPER_SEEDS.get(), ModItems.BELL_PEPPER_PINK.get());
        dropWildCrop(ModBlocks.WILD_DARK_BELL_PEPPERS.get(), ModItems.DARK_BELL_PEPPER_SEEDS.get(), ModItems.BELL_PEPPER_PURPLE.get());
        dropWildCrop(ModBlocks.WILD_COFFEE.get(), ModItems.COFFEE_BEANS.get(), ModItems.COFFEE_BEANS.get());

        dropPottedFlower(ModBlocks.POTTED_WILD_COTTON.get(), ModBlocks.WILD_COTTON.get());
        dropPottedFlower(ModBlocks.POTTED_WILD_BELL_PEPPERS.get(), ModBlocks.WILD_BELL_PEPPERS.get());
        dropPottedFlower(ModBlocks.POTTED_WILD_PALE_BELL_PEPPERS.get(), ModBlocks.WILD_PALE_BELL_PEPPERS.get());
        dropPottedFlower(ModBlocks.POTTED_WILD_DARK_BELL_PEPPERS.get(), ModBlocks.WILD_DARK_BELL_PEPPERS.get());
        dropPottedFlower(ModBlocks.POTTED_WILD_COFFEE.get(), ModBlocks.WILD_COFFEE.get());

        dropSelf(ModBlocks.COTTON_SEEDS_BAG.get());
        dropSelf(ModBlocks.BELL_PEPPER_SEEDS_BAG.get());
        dropSelf(ModBlocks.PALE_BELL_PEPPER_SEEDS_BAG.get());
        dropSelf(ModBlocks.DARK_BELL_PEPPER_SEEDS_BAG.get());
        dropSelf(ModBlocks.COFFEE_BEANS_BAG.get());
        dropSelf(ModBlocks.ROASTED_COFFEE_BEANS_BAG.get());

        dropSelf(ModBlocks.COTTON_BOLL_CRATE.get());
        dropSelf(ModBlocks.BELL_PEPPER_GREEN_CRATE.get());
        dropSelf(ModBlocks.BELL_PEPPER_YELLOW_CRATE.get());
        dropSelf(ModBlocks.BELL_PEPPER_RED_CRATE.get());
        dropSelf(ModBlocks.BELL_PEPPER_ORANGE_CRATE.get());
        dropSelf(ModBlocks.BELL_PEPPER_WHITE_CRATE.get());
        dropSelf(ModBlocks.BELL_PEPPER_PINK_CRATE.get());
        dropSelf(ModBlocks.BELL_PEPPER_BLUE_CRATE.get());
        dropSelf(ModBlocks.BELL_PEPPER_PURPLE_CRATE.get());
        dropSelf(ModBlocks.BELL_PEPPER_BLACK_CRATE.get());
        dropSelf(ModBlocks.CALAMARI_CRATE.get());

        // Bell pepper blocks drop 1-9 slices of their color (not the block itself).
        dropSlices(ModBlocks.BELL_PEPPER_GREEN_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_GREEN.get());
        dropSlices(ModBlocks.BELL_PEPPER_YELLOW_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_YELLOW.get());
        dropSlices(ModBlocks.BELL_PEPPER_RED_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_RED.get());
        dropSlices(ModBlocks.BELL_PEPPER_ORANGE_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_ORANGE.get());
        dropSlices(ModBlocks.BELL_PEPPER_WHITE_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_WHITE.get());
        dropSlices(ModBlocks.BELL_PEPPER_PINK_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_PINK.get());
        dropSlices(ModBlocks.BELL_PEPPER_BLUE_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_BLUE.get());
        dropSlices(ModBlocks.BELL_PEPPER_PURPLE_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_PURPLE.get());
        dropSlices(ModBlocks.BELL_PEPPER_BLACK_BLOCK.get(), ModItems.BELL_PEPPER_SLICE_BLACK.get());

        dropFoodBlock(ModBlocks.SYRUP_CHEESECAKE.get(), PieBlock.BITES);
        dropFoodBlock(ModBlocks.CHERRY_BLOSSOM_CHEESECAKE.get(), PieBlock.BITES);
        dropFoodBlock(ModBlocks.COFFEE_CHEESECAKE.get(), PieBlock.BITES);

        dropPancakeBlock(ModBlocks.PANCAKES.get(), ModItems.PANCAKE.get());
        dropPancakeBlock(ModBlocks.HONEY_PANCAKES.get(), ModItems.HONEY_PANCAKE.get());
        dropPancakeBlock(ModBlocks.CHOCOLATE_PANCAKES.get(), ModItems.CHOCOLATE_PANCAKE.get());
        dropPancakeBlock(ModBlocks.CHERRY_BLOSSOM_PANCAKES.get(), ModItems.CHERRY_BLOSSOM_PANCAKE.get());
        dropPancakeBlock(ModBlocks.VEGETABLE_PANCAKES.get(), ModItems.VEGETABLE_PANCAKE.get());
        dropPancakeBlock(ModBlocks.PUMPKIN_PANCAKES.get(), ModItems.PUMPKIN_PANCAKE.get());
        dropPancakeBlock(ModBlocks.COFFEE_PANCAKES.get(), ModItems.COFFEE_PANCAKE.get());

        dropFoodBlock(ModBlocks.RICE_ROLL_ROYALE.get(), RiceRollRoyaleBlock.ROLL_SERVINGS, RiceRollRoyaleBlock.MAX_SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.BELL_PEPPER_MEDLEY.get(), BellPepperMedleyBlock.MEDLEY_SERVINGS, BellPepperMedleyBlock.MAX_SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.PALE_BELL_PEPPER_MEDLEY.get(), BellPepperMedleyBlock.MEDLEY_SERVINGS, BellPepperMedleyBlock.MAX_SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.DARK_BELL_PEPPER_MEDLEY.get(), BellPepperMedleyBlock.MEDLEY_SERVINGS, BellPepperMedleyBlock.MAX_SERVINGS, Items.BOWL);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
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

    private void dropFoodBlock(Block block, IntegerProperty servings) {
        this.add(block, blockParam -> createFoodBlockDrops(blockParam, servings, 0, null));
    }

    private void dropFoodBlock(Block block, IntegerProperty servings, ItemLike containerItem) {
        this.add(block, blockParam -> createFoodBlockDrops(blockParam, servings, 0, containerItem));
    }

    private void dropFoodBlock(Block block, IntegerProperty servings, int defaultServings, ItemLike containerItem) {
        this.add(block, blockParam -> createFoodBlockDrops(blockParam, servings, defaultServings, containerItem));
    }

    private LootTable.Builder createCropDrops(Block cropBlock, IntegerProperty age, int maxAge, ItemLike seedsItem, NumberProvider seedsCount, ItemLike cropItem, NumberProvider cropCount) {
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
                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                                )
                        )
                        // When fully grown, also drop the full-grown crop items (including a Fortune bonus).
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .add(LootItem.lootTableItem(cropItem)
                                        .apply(SetItemCountFunction.setCount(cropCount))
                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                                )
                        )
        );
    }

    private LootTable.Builder createBellPepperDrops(Block cropBlock) {
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
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_SEEDS.get()))
                        )
                        // When fully grown, drop additional seeds (including a Fortune bonus).
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_SEEDS.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                                )
                        )
                        // When fully grown, also drop a bell pepper.
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_RED.get()).setWeight(6)) // Red bell peppers are more common
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_GREEN.get()))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_YELLOW.get()))
                        )
                        // When fully grown, potentially drop an additional green bell pepper.
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .when(LootItemRandomChanceCondition.randomChance(0.15f))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_GREEN.get()))
                        )
                        // Finally, when fully grown, potentially drop additional yellow bell pepper.
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .when(LootItemRandomChanceCondition.randomChance(0.15f))
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_YELLOW.get()))
                        )
        );
    }

    private void dropPaleBellPepperCrop(Block block) {
        this.add(block, this::createPaleBellPepperDrops);
    }

    private void dropDarkBellPepperCrop(Block block) {
        this.add(block, this::createDarkBellPepperDrops);
    }

    /** Shared by the pale and dark crops: same shape as the base crop, different seed and colors. */
    private LootTable.Builder createMutatedBellPepperDrops(Block cropBlock, ItemLike seedItem, ItemLike first, ItemLike second, ItemLike third) {
        LootItemCondition.Builder dropGrownCropCondition = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(cropBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BellPepperCropBlock.AGE, BellPepperCropBlock.MAX_AGE));

        return this.applyExplosionDecay(
                cropBlock,
                LootTable.lootTable()
                        // When not fully grown, drop the original seed.
                        .withPool(LootPool.lootPool()
                                .when(InvertedLootItemCondition.invert(dropGrownCropCondition))
                                .add(LootItem.lootTableItem(seedItem))
                        )
                        // When fully grown, drop additional seeds (including a Fortune bonus).
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .add(LootItem.lootTableItem(seedItem)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                                )
                        )
                        // When fully grown, drop one bell pepper of the group (equal chance).
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .add(LootItem.lootTableItem(first))
                                .add(LootItem.lootTableItem(second))
                                .add(LootItem.lootTableItem(third))
                        )
        );
    }

    private LootTable.Builder createPaleBellPepperDrops(Block cropBlock) {
        return createMutatedBellPepperDrops(cropBlock, ModItems.PALE_BELL_PEPPER_SEEDS.get(),
                ModItems.BELL_PEPPER_ORANGE.get(), ModItems.BELL_PEPPER_WHITE.get(), ModItems.BELL_PEPPER_PINK.get());
    }

    private LootTable.Builder createDarkBellPepperDrops(Block cropBlock) {
        return createMutatedBellPepperDrops(cropBlock, ModItems.DARK_BELL_PEPPER_SEEDS.get(),
                ModItems.BELL_PEPPER_BLUE.get(), ModItems.BELL_PEPPER_PURPLE.get(), ModItems.BELL_PEPPER_BLACK.get());
    }

    private void dropSlices(Block block, ItemLike slice) {
        this.add(block, createSingleItemTable(slice, UniformGenerator.between(1.0F, 9.0F)));
    }

    private void dropPancakeBlock(Block block, ItemLike pancakeItem) {
        this.add(block, blockParam -> createPancakeDrops(blockParam, pancakeItem));
    }

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

    private void dropWildCrop(Block block, ItemLike seedsItem, ItemLike cropItem) {
        this.add(block, blockParam -> createWildCropDrops(blockParam, seedsItem, cropItem));
    }

    private LootTable.Builder createWildCropDrops(Block block, ItemLike seedsItem, ItemLike cropItem) {
        return this.applyExplosionDecay(
                block,
                LootTable.lootTable()
                        // When using Silk Touch, drop the actual block.
                        .withPool(LootPool.lootPool()
                                .when(HAS_SHEARS.or(HAS_SILK_TOUCH))
                                .add(LootItem.lootTableItem(block))
                        )
                        // Else, drop the seeds item (including a Fortune bonus).
                        .withPool(LootPool.lootPool()
                                .when((HAS_SHEARS.or(HAS_SILK_TOUCH).invert()))
                                .add(LootItem.lootTableItem(seedsItem)
                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))
                        )
                        // Additionally, add a random chance to drop the grown crop item.
                        .withPool(LootPool.lootPool()
                                .when(AllOfCondition.allOf((HAS_SHEARS.or(HAS_SILK_TOUCH)).invert(), LootItemRandomChanceCondition.randomChance(0.3f)))
                                .add(LootItem.lootTableItem(cropItem))
                        )
        );
    }

    private LootTable.Builder createFoodBlockDrops(Block block, IntegerProperty servings, int defaultServings, ItemLike containerItem) {
        // Condition that checks if any servings have been taken.
        LootItemCondition.Builder noServingsTaken = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(servings, defaultServings));

        // If no servings have been taken yet, drop the block.
        LootTable.Builder lootTable = LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(noServingsTaken)
                        .add(LootItem.lootTableItem(block))
                );

        // Else, drop the container item (if there is any).
        if (containerItem != null) {
            lootTable.withPool(LootPool.lootPool()
                    .when(InvertedLootItemCondition.invert(noServingsTaken))
                    .add(LootItem.lootTableItem(containerItem)));
        }

        return this.applyExplosionDecay(block, lootTable);
    }
}
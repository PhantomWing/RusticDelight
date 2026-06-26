package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.block.custom.*;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
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
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootSubProvider {
    public ModLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
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
        dropCrop(
                ModBlocks.COFFEE_CROP, CoffeeCropBlock.AGE, CoffeeCropBlock.MAX_AGE,
                ModItems.COFFEE_BEANS, UniformGenerator.between(1.0F, 1.0F),
                ModItems.COFFEE_BEANS, UniformGenerator.between(1.0F, 4.0F));

        dropWildCrop(ModBlocks.WILD_COTTON, ModItems.COTTON_SEEDS, ModItems.COTTON_BOLL);
        dropWildCrop(ModBlocks.WILD_BELL_PEPPERS, ModItems.BELL_PEPPER_SEEDS, ModItems.BELL_PEPPER_RED);
        dropWildCrop(ModBlocks.WILD_COFFEE, ModItems.COFFEE_BEANS, ModItems.COFFEE_BEANS);

        dropPottedFlower(ModBlocks.POTTED_WILD_COTTON, ModBlocks.WILD_COTTON);
        dropPottedFlower(ModBlocks.POTTED_WILD_BELL_PEPPERS, ModBlocks.WILD_BELL_PEPPERS);
        dropPottedFlower(ModBlocks.POTTED_WILD_COFFEE, ModBlocks.WILD_COFFEE);

        dropSelf(ModBlocks.COTTON_SEEDS_BAG);
        dropSelf(ModBlocks.BELL_PEPPER_SEEDS_BAG);
        dropSelf(ModBlocks.COFFEE_BEANS_BAG);
        dropSelf(ModBlocks.ROASTED_COFFEE_BEANS_BAG);

        dropSelf(ModBlocks.COTTON_BOLL_CRATE);
        dropSelf(ModBlocks.BELL_PEPPER_GREEN_CRATE);
        dropSelf(ModBlocks.BELL_PEPPER_YELLOW_CRATE);
        dropSelf(ModBlocks.BELL_PEPPER_RED_CRATE);

        dropFoodBlock(ModBlocks.SYRUP_CHEESECAKE, PieBlock.BITES);
        dropFoodBlock(ModBlocks.CHERRY_BLOSSOM_CHEESECAKE, PieBlock.BITES);

        dropFoodBlock(ModBlocks.PANCAKES, PancakeBlock.SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.HONEY_PANCAKES, PancakeBlock.SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.CHOCOLATE_PANCAKES, PancakeBlock.SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.CHERRY_BLOSSOM_PANCAKES, PancakeBlock.SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.VEGETABLE_PANCAKES, PancakeBlock.SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.PUMPKIN_PANCAKES, PancakeBlock.SERVINGS, Items.BOWL);

        dropFoodBlock(ModBlocks.RICE_ROLL_ROYALE, RiceRollRoyaleBlock.ROLL_SERVINGS, RiceRollRoyaleBlock.MAX_SERVINGS, Items.BOWL);
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

    private void dropWildCrop(Block block, ItemLike seedsItem, ItemLike cropItem) {
        this.add(block, blockParam -> createWildCropDrops(blockParam, seedsItem, cropItem));
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
                                .when(doesNotHaveShearsOrSilkTouch())
                                .add(LootItem.lootTableItem(seedsItem)
                                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))))
                        )
                        // Additionally, add a random chance to drop the grown crop item.
                        .withPool(LootPool.lootPool()
                                .when(AllOfCondition.allOf(doesNotHaveShearsOrSilkTouch(), LootItemRandomChanceCondition.randomChance(0.3f)))
                                .add(LootItem.lootTableItem(cropItem))
                        )
        );
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

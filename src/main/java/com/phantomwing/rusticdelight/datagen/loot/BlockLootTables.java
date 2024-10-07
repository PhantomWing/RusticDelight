package com.phantomwing.rusticdelight.datagen.loot;

import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.block.custom.BellPepperCropBlock;
import com.phantomwing.rusticdelight.block.custom.CottonCropBlock;
import com.phantomwing.rusticdelight.block.custom.PancakeBlock;
import com.phantomwing.rusticdelight.item.ModItems;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
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

import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.Set;

public class BlockLootTables extends BlockLootSubProvider {
    public BlockLootTables(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookupProvider);
    }

    // Actually add our loot tables.
    @Override
    protected void generate() {
        dropCrop(
                ModBlocks.COTTON_CROP.get(), CottonCropBlock.AGE, CottonCropBlock.MAX_AGE,
                ModItems.COTTON_SEEDS, UniformGenerator.between(1.0F, 3.0F),
                ModItems.COTTON_BOLL, UniformGenerator.between(1.0F, 3.0F));
        dropBellPepperCrop(ModBlocks.BELL_PEPPER_CROP.get());

        dropWildCrop(ModBlocks.WILD_COTTON.get(), ModItems.COTTON_SEEDS, ModItems.COTTON_BOLL);
        dropWildCrop(ModBlocks.WILD_BELL_PEPPERS.get(), ModItems.BELL_PEPPER_SEEDS, ModItems.BELL_PEPPER_RED);

        dropPottedFlower(ModBlocks.POTTED_WILD_COTTON.get(), ModBlocks.WILD_COTTON.get());
        dropPottedFlower(ModBlocks.POTTED_WILD_BELL_PEPPERS.get(), ModBlocks.WILD_BELL_PEPPERS.get());

        dropSelf(ModBlocks.COTTON_SEEDS_BAG.get());
        dropSelf(ModBlocks.BELL_PEPPER_SEEDS_BAG.get());

        dropSelf(ModBlocks.COTTON_BOLL_CRATE.get());
        dropSelf(ModBlocks.BELL_PEPPER_GREEN_CRATE.get());
        dropSelf(ModBlocks.BELL_PEPPER_YELLOW_CRATE.get());
        dropSelf(ModBlocks.BELL_PEPPER_RED_CRATE.get());

        dropFoodBlock(ModBlocks.CHERRY_BLOSSOM_CHEESECAKE.get(), PieBlock.BITES);
        dropFoodBlock(ModBlocks.HONEY_PANCAKES.get(), PancakeBlock.SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.CHOCOLATE_PANCAKES.get(), PancakeBlock.SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.CHERRY_BLOSSOM_PANCAKES.get(), PancakeBlock.SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.VEGETABLE_PANCAKES.get(), PancakeBlock.SERVINGS, Items.BOWL);
    }

    // The contents of this Iterable are used for validation.
    // We return an Iterable over our block registry's values here.
    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        // The contents of our DeferredRegister.
        return ModBlocks.BLOCKS.getEntries()
                .stream()
                // Cast to Block here, otherwise it will be a ? extends Block and Java will complain.
                .map(e -> (Block) e.value())
                .toList();
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
        this.add(block, blockParam -> createFoodBlockDrops(blockParam, servings));
    }

    private void dropFoodBlock(Block block, IntegerProperty servings, ItemLike containerItem) {
        this.add(block, blockParam -> createFoodBlockDrops(blockParam, servings, containerItem));
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
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_SEEDS.get()))
                        )
                        // When fully grown, drop additional seeds (including a Fortune bonus).
                        .withPool(LootPool.lootPool()
                                .when(dropGrownCropCondition)
                                .add(LootItem.lootTableItem(ModItems.BELL_PEPPER_SEEDS.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
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
                                .when(hasSilkTouch())
                                .add(LootItem.lootTableItem(block))
                        )
                        // Else, drop the seeds item (including a Fortune bonus).
                        .withPool(LootPool.lootPool()
                                .when(doesNotHaveSilkTouch())
                                .add(LootItem.lootTableItem(seedsItem)
                                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))))
                        )
                        // Additionally, add a random chance to drop the grown crop item.
                        .withPool(LootPool.lootPool()
                                .when(AllOfCondition.allOf(doesNotHaveSilkTouch(), LootItemRandomChanceCondition.randomChance(0.3f)))
                                .add(LootItem.lootTableItem(cropItem))
                        )
        );
    }

    private LootTable.Builder createFoodBlockDrops(Block block, IntegerProperty servings) {
        // Condition that checks if any servings have been taken.
        LootItemCondition.Builder noServingsTaken = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(servings, 0));

        return this.applyExplosionDecay(
                block,
                LootTable.lootTable()
                        // If no servings have been taken yet, drop the block.
                        .withPool(LootPool.lootPool()
                                .when(noServingsTaken)
                                .add(LootItem.lootTableItem(block))
                        )
        );
    }

    private LootTable.Builder createFoodBlockDrops(Block block, IntegerProperty servings, ItemLike containerItem) {
        // Condition that checks if any servings have been taken.
        LootItemCondition.Builder noServingsTaken = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(servings, 0));

        return this.applyExplosionDecay(
                block,
                LootTable.lootTable()
                        // If no servings have been taken yet, drop the block.
                        .withPool(LootPool.lootPool()
                                .when(noServingsTaken)
                                .add(LootItem.lootTableItem(block))
                        )
                        // Else, drop the container item.
                        .withPool(LootPool.lootPool()
                                .when(InvertedLootItemCondition.invert(noServingsTaken))
                                .add(LootItem.lootTableItem(containerItem)
                                )
                        )
        );
    }
}

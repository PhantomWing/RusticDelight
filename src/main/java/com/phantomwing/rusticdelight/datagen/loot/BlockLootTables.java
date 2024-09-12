package com.phantomwing.rusticdelight.datagen.loot;

import com.phantomwing.rusticdelight.block.BlockManager;
import com.phantomwing.rusticdelight.block.custom.CottonCropBlock;
import com.phantomwing.rusticdelight.item.ItemManager;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
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

import java.util.Set;

public class BlockLootTables extends BlockLootSubProvider {
    public BlockLootTables(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookupProvider);
    }

    // Actually add our loot tables.
    @Override
    protected void generate() {
        dropCrop(
                BlockManager.COTTON_CROP.get(), CottonCropBlock.AGE, CottonCropBlock.MAX_AGE,
                ItemManager.COTTON_SEEDS.get(), UniformGenerator.between(1.0F, 2.0F),
                ItemManager.COTTON_BOLL.get(), UniformGenerator.between(1.0F, 3.0F));
        dropWildCrop(BlockManager.WILD_COTTON.get(), ItemManager.COTTON_SEEDS.get(), ItemManager.COTTON_BOLL.get());
        dropPottedFlower(BlockManager.POTTED_WILD_COTTON.get(), BlockManager.WILD_COTTON.get());
        dropSelf(BlockManager.COTTON_BOLL_CRATE.get());
        dropSelf(BlockManager.COTTON_SEEDS_BAG.get());
    }

    // The contents of this Iterable are used for validation.
    // We return an Iterable over our block registry's values here.
    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        // The contents of our DeferredRegister.
        return BlockManager.BLOCKS.getEntries()
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
}

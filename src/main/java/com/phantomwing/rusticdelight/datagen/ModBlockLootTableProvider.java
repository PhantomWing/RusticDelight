package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.block.custom.BellPepperCropBlock;
import com.phantomwing.rusticdelight.block.custom.CoffeeCropBlock;
import com.phantomwing.rusticdelight.block.custom.CottonCropBlock;
import com.phantomwing.rusticdelight.block.custom.PancakeBlock;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.state.property.IntProperty;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropCrop(
                ModBlocks.COTTON_CROP, CottonCropBlock.AGE, CottonCropBlock.MAX_AGE,
                ModItems.COTTON_SEEDS, UniformLootNumberProvider.create(1.0F, 3.0F),
                ModItems.COTTON_BOLL, UniformLootNumberProvider.create(1.0F, 3.0F));
        dropBellPepperCrop(ModBlocks.BELL_PEPPER_CROP);
        dropCrop(
                ModBlocks.COFFEE_CROP, CoffeeCropBlock.AGE, CoffeeCropBlock.MAX_AGE,
                ModItems.COFFEE_BEANS, UniformLootNumberProvider.create(1.0F, 1.0F),
                ModItems.COFFEE_BEANS, UniformLootNumberProvider.create(1.0F, 4.0F));

        dropWildCrop(ModBlocks.WILD_COTTON, ModItems.COTTON_SEEDS, ModItems.COTTON_BOLL);
        dropWildCrop(ModBlocks.WILD_BELL_PEPPERS, ModItems.BELL_PEPPER_SEEDS, ModItems.BELL_PEPPER_RED);
        dropWildCrop(ModBlocks.WILD_COFFEE, ModItems.COFFEE_BEANS, ModItems.COFFEE_BEANS);

        addPottedPlantDrops(ModBlocks.POTTED_WILD_COTTON);
        addPottedPlantDrops(ModBlocks.POTTED_WILD_BELL_PEPPERS);
        addPottedPlantDrops(ModBlocks.POTTED_WILD_COFFEE);


        addDrop(ModBlocks.COTTON_SEEDS_BAG);
        addDrop(ModBlocks.BELL_PEPPER_SEEDS_BAG);
        addDrop(ModBlocks.COFFEE_BEANS_BAG);

        addDrop(ModBlocks.COTTON_BOLL_CRATE);
        addDrop(ModBlocks.BELL_PEPPER_GREEN_CRATE);
        addDrop(ModBlocks.BELL_PEPPER_YELLOW_CRATE);
        addDrop(ModBlocks.BELL_PEPPER_RED_CRATE);

        dropFoodBlock(ModBlocks.CHERRY_BLOSSOM_CHEESECAKE, PieBlock.BITES);
        dropFoodBlock(ModBlocks.HONEY_PANCAKES, PancakeBlock.SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.CHOCOLATE_PANCAKES, PancakeBlock.SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.CHERRY_BLOSSOM_PANCAKES, PancakeBlock.SERVINGS, Items.BOWL);
        dropFoodBlock(ModBlocks.VEGETABLE_PANCAKES, PancakeBlock.SERVINGS, Items.BOWL);
    }

    private void dropCrop(Block block, IntProperty age, int maxAge, ItemConvertible seedsItem, LootNumberProvider seedsCount, ItemConvertible cropItem, LootNumberProvider cropCount) {
        addDrop(block, blockParam -> createCropDrops(blockParam, age, maxAge, seedsItem, seedsCount, cropItem, cropCount));
    }

    private void dropBellPepperCrop(Block block) {
        addDrop(block, this::createBellPepperDrops);
    }

    private void dropFoodBlock(Block block, IntProperty servings) {
        addDrop(block, blockParam -> createFoodBlockDrops(blockParam, servings));
    }

    private void dropFoodBlock(Block block, IntProperty servings, ItemConvertible containerItem) {
        addDrop(block, blockParam -> createFoodBlockDrops(blockParam, servings, containerItem));
    }

    private LootTable.Builder createCropDrops(Block cropBlock, IntProperty age, int maxAge, ItemConvertible seedsItem, LootNumberProvider seedsCount, ItemConvertible cropItem, LootNumberProvider cropCount) {
        RegistryEntryLookup<Enchantment> enchantments = this.registryLookup.createRegistryLookup().getOrThrow(RegistryKeys.ENCHANTMENT);

        // Condition that checks if the crop is fully grown.
        LootCondition.Builder dropGrownCropCondition = BlockStatePropertyLootCondition
                .builder(cropBlock)
                .properties(StatePredicate.Builder.create().exactMatch(age, maxAge));

        return this.applyExplosionDecay(
                cropBlock,
                LootTable.builder()
                        // When not fully grown, drop the original seed.
                        .pool(LootPool.builder()
                                .conditionally(InvertedLootCondition.builder(dropGrownCropCondition))
                                .with(ItemEntry.builder(seedsItem))
                        )
                        // When fully grown, drop additional seeds (including a Fortune bonus).
                        .pool(LootPool.builder()
                                .conditionally(dropGrownCropCondition)
                                .with(ItemEntry.builder(seedsItem)
                                        .apply(SetCountLootFunction.builder(seedsCount))
                                        .apply(ApplyBonusLootFunction.uniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                )
                        )
                        // When fully grown, also drop the full-grown crop items (including a Fortune bonus).
                        .pool(LootPool.builder()
                                .conditionally(dropGrownCropCondition)
                                .with(ItemEntry.builder(cropItem)
                                        .apply(SetCountLootFunction.builder(cropCount))
                                        .apply(ApplyBonusLootFunction.uniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                )
                        )
        );
    }

    private LootTable.Builder createBellPepperDrops(Block cropBlock) {
        RegistryEntryLookup<Enchantment> enchantments = this.registryLookup.createRegistryLookup().getOrThrow(RegistryKeys.ENCHANTMENT);

        // Condition that checks if the crop is fully grown.
        LootCondition.Builder dropGrownCropCondition = BlockStatePropertyLootCondition
                .builder(cropBlock)
                .properties(StatePredicate.Builder.create().exactMatch(BellPepperCropBlock.AGE, BellPepperCropBlock.MAX_AGE));

        return this.applyExplosionDecay(
                cropBlock,
                LootTable.builder()
                        // When not fully grown, drop the original seed.
                        .pool(LootPool.builder()
                                .conditionally(InvertedLootCondition.builder(dropGrownCropCondition))
                                .with(ItemEntry.builder(ModItems.BELL_PEPPER_SEEDS))
                        )
                        // When fully grown, drop additional seeds (including a Fortune bonus).
                        .pool(LootPool.builder()
                                .conditionally(dropGrownCropCondition)
                                .with(ItemEntry.builder(ModItems.BELL_PEPPER_SEEDS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                        .apply(ApplyBonusLootFunction.uniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                )
                        )
                        // When fully grown, also drop a bell pepper.
                        .pool(LootPool.builder()
                                .conditionally(dropGrownCropCondition)
                                .with(ItemEntry.builder(ModItems.BELL_PEPPER_RED).weight(6)) // Red bell peppers are more common
                                .with(ItemEntry.builder(ModItems.BELL_PEPPER_GREEN))
                                .with(ItemEntry.builder(ModItems.BELL_PEPPER_YELLOW))
                        )
                        // When fully grown, potentially drop an additional green bell pepper.
                        .pool(LootPool.builder()
                                .conditionally(dropGrownCropCondition)
                                .conditionally(RandomChanceLootCondition.builder(0.15f))
                                .with(ItemEntry.builder(ModItems.BELL_PEPPER_GREEN))
                        )
                        // Finally, when fully grown, potentially drop additional yellow bell pepper.
                        .pool(LootPool.builder()
                                .conditionally(dropGrownCropCondition)
                                .conditionally(RandomChanceLootCondition.builder(0.15f))
                                .with(ItemEntry.builder(ModItems.BELL_PEPPER_YELLOW))
                        )
        );
    }

    private void dropWildCrop(Block block, ItemConvertible seedsItem, ItemConvertible cropItem) {
        addDrop(block, blockParam -> createWildCropDrops(blockParam, seedsItem, cropItem));
    }

    private LootTable.Builder createWildCropDrops(Block block, ItemConvertible seedsItem, ItemConvertible cropItem) {
        RegistryEntryLookup<Enchantment> enchantments = this.registryLookup.createRegistryLookup().getOrThrow(RegistryKeys.ENCHANTMENT);

        return this.applyExplosionDecay(
                block,
                LootTable.builder()
                        // When using Silk Touch, drop the actual block.
                        .pool(LootPool.builder()
                                .conditionally(createWithShearsOrSilkTouchCondition())
                                .with(ItemEntry.builder(block))
                        )
                        // Else, drop the seeds item (including a Fortune bonus).
                        .pool(LootPool.builder()
                                .conditionally(createWithoutShearsOrSilkTouchCondition())
                                .with(ItemEntry.builder(seedsItem)
                                        .apply(ApplyBonusLootFunction.uniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))))
                        )
                        // Additionally, add a random chance to drop the grown crop item.
                        .pool(LootPool.builder()
                                .conditionally(AllOfLootCondition.builder(createWithoutShearsOrSilkTouchCondition(), RandomChanceLootCondition.builder(0.3f)))
                                .with(ItemEntry.builder(cropItem))
                        )
        );
    }

    private LootTable.Builder createFoodBlockDrops(Block block, IntProperty servings) {
        // Condition that checks if any servings have been taken.
        LootCondition.Builder noServingsTaken = BlockStatePropertyLootCondition
                .builder(block)
                .properties(StatePredicate.Builder.create().exactMatch(servings, 0));

        return this.applyExplosionDecay(
                block,
                LootTable.builder()
                        // If no servings have been taken yet, drop the block.
                        .pool(LootPool.builder()
                                .conditionally(noServingsTaken)
                                .with(ItemEntry.builder(block))
                        )
        );
    }

    private LootTable.Builder createFoodBlockDrops(Block block, IntProperty servings, ItemConvertible containerItem) {
        // Condition that checks if any servings have been taken.
        LootCondition.Builder noServingsTaken = BlockStatePropertyLootCondition
                .builder(block)
                .properties(StatePredicate.Builder.create().exactMatch(servings, 0));

        return this.applyExplosionDecay(
                block,
                LootTable.builder()
                        // If no servings have been taken yet, drop the block.
                        .pool(LootPool.builder()
                                .conditionally(noServingsTaken)
                                .with(ItemEntry.builder(block))
                        )
                        // Else, drop the container item.
                        .pool(LootPool.builder()
                                .conditionally(InvertedLootCondition.builder(noServingsTaken))
                                .with(ItemEntry.builder(containerItem))
                        )
        );
    }
}

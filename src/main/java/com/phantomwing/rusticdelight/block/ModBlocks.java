package com.phantomwing.rusticdelight.block;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.custom.*;
import com.phantomwing.rusticdelight.food.FoodValues;
import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import vectorwing.farmersdelight.common.block.PieBlock;

public class ModBlocks {
    // Seed bags
    public static final Block COTTON_SEEDS_BAG = registerBlock("cotton_seeds_bag",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BELL_PEPPER_SEEDS_BAG = registerBlock("bell_pepper_seeds_bag",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block COFFEE_BEANS_BAG = registerBlock("coffee_beans_bag",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block ROASTED_COFFEE_BEANS_BAG = registerBlock("roasted_coffee_beans_bag",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));

    // Crop crates
    public static final Block COTTON_BOLL_CRATE = registerBlock("cotton_boll_crate",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block BELL_PEPPER_GREEN_CRATE = registerBlock("bell_pepper_green_crate",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block BELL_PEPPER_YELLOW_CRATE = registerBlock("bell_pepper_yellow_crate",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block BELL_PEPPER_RED_CRATE = registerBlock("bell_pepper_red_crate",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));

    // Edible blocks
    public static final Block CHERRY_BLOSSOM_CHEESECAKE = registerBlock("cherry_blossom_cheesecake",
            new PieBlock(AbstractBlock.Settings.copy(Blocks.CAKE), () -> ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE));
    public static final Block HONEY_PANCAKES = registerBlock("honey_pancakes",
            new PancakeBlock(FoodValues.HONEY_PANCAKE, AbstractBlock.Settings.copy(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)));
    public static final Block CHOCOLATE_PANCAKES = registerBlock("chocolate_pancakes",
            new PancakeBlock(FoodValues.CHOCOLATE_PANCAKE, AbstractBlock.Settings.copy(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)));
    public static final Block CHERRY_BLOSSOM_PANCAKES = registerBlock("cherry_blossom_pancakes",
            new PancakeBlock(FoodValues.CHERRY_BLOSSOM_PANCAKE, AbstractBlock.Settings.copy(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)));
    public static final Block VEGETABLE_PANCAKES = registerBlock("vegetable_pancakes",
            new PancakeBlock(FoodValues.VEGETABLE_PANCAKE, AbstractBlock.Settings.copy(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)));

    // Wild crops
    public static final Block WILD_COTTON = registerBlock("wild_cotton",
            new ModWildCropBlock(StatusEffects.JUMP_BOOST, 12, AbstractBlock.Settings.copy(Blocks.TALL_GRASS)));
    public static final Block WILD_BELL_PEPPERS = registerBlock("wild_bell_peppers",
            new ModWildCropBlock(StatusEffects.FIRE_RESISTANCE, 10, AbstractBlock.Settings.copy(Blocks.TALL_GRASS)));
    public static final Block WILD_COFFEE = registerBlock("wild_coffee",
            new ModWildCropBlock(StatusEffects.HASTE, 10, AbstractBlock.Settings.copy(Blocks.TALL_GRASS)));

    // Unobtainable blocks
    public static final Block COTTON_CROP = registerBlock("cotton",
            new CottonCropBlock(AbstractBlock.Settings.copy(Blocks.CARROTS).nonOpaque().noCollision()));
    public static final Block BELL_PEPPER_CROP = registerBlock("bell_peppers",
            new BellPepperCropBlock(AbstractBlock.Settings.copy(Blocks.CARROTS).nonOpaque().noCollision()));
    public static final Block COFFEE_CROP = registerBlock("coffee",
            new CoffeeCropBlock(AbstractBlock.Settings.copy(Blocks.CARROTS).nonOpaque().noCollision()));

    public static final Block POTTED_WILD_COTTON = registerBlock("potted_wild_cotton",
            new FlowerPotBlock(ModBlocks.WILD_COTTON, AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque()));
    public static final Block POTTED_WILD_BELL_PEPPERS = registerBlock("potted_wild_bell_peppers",
            new FlowerPotBlock(ModBlocks.WILD_BELL_PEPPERS, AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque()));
    public static final Block POTTED_WILD_COFFEE = registerBlock("potted_wild_coffee",
            new FlowerPotBlock(ModBlocks.WILD_COFFEE, AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(RusticDelight.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        RusticDelight.LOGGER.info("Registering blocks for " + RusticDelight.MOD_ID);
    }
}

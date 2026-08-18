package com.phantomwing.rusticdelight.block;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.custom.*;
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

import java.util.List;

public class ModBlocks {
    // Seed bags
    public static final Block COTTON_SEEDS_BAG = registerBlock("cotton_seeds_bag",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BELL_PEPPER_SEEDS_BAG = registerBlock("bell_pepper_seeds_bag",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block PALE_BELL_PEPPER_SEEDS_BAG = registerBlock("pale_bell_pepper_seeds_bag",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block DARK_BELL_PEPPER_SEEDS_BAG = registerBlock("dark_bell_pepper_seeds_bag",
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
    public static final Block BELL_PEPPER_ORANGE_CRATE = registerBlock("bell_pepper_orange_crate",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block BELL_PEPPER_WHITE_CRATE = registerBlock("bell_pepper_white_crate",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block BELL_PEPPER_PINK_CRATE = registerBlock("bell_pepper_pink_crate",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block BELL_PEPPER_BLUE_CRATE = registerBlock("bell_pepper_blue_crate",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block BELL_PEPPER_PURPLE_CRATE = registerBlock("bell_pepper_purple_crate",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block BELL_PEPPER_BLACK_CRATE = registerBlock("bell_pepper_black_crate",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block CALAMARI_CRATE = registerBlock("calamari_crate",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));

    // Bell pepper blocks (9 slices, reversible)
    public static final Block BELL_PEPPER_GREEN_BLOCK = registerBlock("bell_pepper_green_block",
            new Block(AbstractBlock.Settings.copy(Blocks.MELON)));
    public static final Block BELL_PEPPER_YELLOW_BLOCK = registerBlock("bell_pepper_yellow_block",
            new Block(AbstractBlock.Settings.copy(Blocks.MELON)));
    public static final Block BELL_PEPPER_RED_BLOCK = registerBlock("bell_pepper_red_block",
            new Block(AbstractBlock.Settings.copy(Blocks.MELON)));
    public static final Block BELL_PEPPER_ORANGE_BLOCK = registerBlock("bell_pepper_orange_block",
            new Block(AbstractBlock.Settings.copy(Blocks.MELON)));
    public static final Block BELL_PEPPER_WHITE_BLOCK = registerBlock("bell_pepper_white_block",
            new Block(AbstractBlock.Settings.copy(Blocks.MELON)));
    public static final Block BELL_PEPPER_PINK_BLOCK = registerBlock("bell_pepper_pink_block",
            new Block(AbstractBlock.Settings.copy(Blocks.MELON)));
    public static final Block BELL_PEPPER_BLUE_BLOCK = registerBlock("bell_pepper_blue_block",
            new Block(AbstractBlock.Settings.copy(Blocks.MELON)));
    public static final Block BELL_PEPPER_PURPLE_BLOCK = registerBlock("bell_pepper_purple_block",
            new Block(AbstractBlock.Settings.copy(Blocks.MELON)));
    public static final Block BELL_PEPPER_BLACK_BLOCK = registerBlock("bell_pepper_black_block",
            new Block(AbstractBlock.Settings.copy(Blocks.MELON)));

    // Edible blocks
    public static final Block SYRUP_CHEESECAKE = registerBlock("syrup_cheesecake",
            new PieBlock(AbstractBlock.Settings.copy(Blocks.CAKE), () -> ModItems.SYRUP_CHEESECAKE_SLICE));
    public static final Block CHERRY_BLOSSOM_CHEESECAKE = registerBlock("cherry_blossom_cheesecake",
            new PieBlock(AbstractBlock.Settings.copy(Blocks.CAKE), () -> ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE));
    public static final Block COFFEE_CHEESECAKE = registerBlock("coffee_cheesecake",
            new PieBlock(AbstractBlock.Settings.copy(Blocks.CAKE), () -> ModItems.COFFEE_CHEESECAKE_SLICE));
    public static final Block PANCAKES = registerBlock("pancakes",
            new PancakeBlock(() -> ModItems.PANCAKE, AbstractBlock.Settings.copy(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)));
    public static final Block HONEY_PANCAKES = registerBlock("honey_pancakes",
            new PancakeBlock(() -> ModItems.HONEY_PANCAKE, AbstractBlock.Settings.copy(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)));
    public static final Block CHOCOLATE_PANCAKES = registerBlock("chocolate_pancakes",
            new PancakeBlock(() -> ModItems.CHOCOLATE_PANCAKE, AbstractBlock.Settings.copy(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)));
    public static final Block CHERRY_BLOSSOM_PANCAKES = registerBlock("cherry_blossom_pancakes",
            new PancakeBlock(() -> ModItems.CHERRY_BLOSSOM_PANCAKE, AbstractBlock.Settings.copy(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)));
    public static final Block VEGETABLE_PANCAKES = registerBlock("vegetable_pancakes",
            new PancakeBlock(() -> ModItems.VEGETABLE_PANCAKE, AbstractBlock.Settings.copy(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)));
    public static final Block PUMPKIN_PANCAKES = registerBlock("pumpkin_pancakes",
            new PancakeBlock(() -> ModItems.PUMPKIN_PANCAKE, AbstractBlock.Settings.copy(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)));
    public static final Block COFFEE_PANCAKES = registerBlock("coffee_pancakes",
            new PancakeBlock(() -> ModItems.COFFEE_PANCAKE, AbstractBlock.Settings.copy(Blocks.CAKE).sounds(BlockSoundGroup.WOOD)));

    // Feasts
    public static final Block RICE_ROLL_ROYALE = registerBlock("rice_roll_royale",
            new RiceRollRoyaleBlock(AbstractBlock.Settings.copy(Blocks.CAKE)));
    // Serving lists are ordered [back-left, back-right, front] to match the stage models.
    public static final Block BELL_PEPPER_MEDLEY = registerBlock("bell_pepper_medley",
            new BellPepperMedleyBlock(AbstractBlock.Settings.copy(Blocks.CAKE), List.of(
                    () -> ModItems.STUFFED_BELL_PEPPER_GREEN, () -> ModItems.STUFFED_BELL_PEPPER_YELLOW, () -> ModItems.STUFFED_BELL_PEPPER_RED)));
    public static final Block PALE_BELL_PEPPER_MEDLEY = registerBlock("pale_bell_pepper_medley",
            new BellPepperMedleyBlock(AbstractBlock.Settings.copy(Blocks.CAKE), List.of(
                    () -> ModItems.STUFFED_BELL_PEPPER_ORANGE, () -> ModItems.STUFFED_BELL_PEPPER_WHITE, () -> ModItems.STUFFED_BELL_PEPPER_PINK)));
    public static final Block DARK_BELL_PEPPER_MEDLEY = registerBlock("dark_bell_pepper_medley",
            new BellPepperMedleyBlock(AbstractBlock.Settings.copy(Blocks.CAKE), List.of(
                    () -> ModItems.STUFFED_BELL_PEPPER_BLUE, () -> ModItems.STUFFED_BELL_PEPPER_PURPLE, () -> ModItems.STUFFED_BELL_PEPPER_BLACK)));

    // Wild crops
    public static final Block WILD_COTTON = registerBlock("wild_cotton",
            new ModWildCropBlock(StatusEffects.JUMP_BOOST, 12, AbstractBlock.Settings.copy(Blocks.TALL_GRASS)));
    public static final Block WILD_BELL_PEPPERS = registerBlock("wild_bell_peppers",
            new ModWildCropBlock(StatusEffects.FIRE_RESISTANCE, 10, AbstractBlock.Settings.copy(Blocks.TALL_GRASS)));
    public static final Block WILD_PALE_BELL_PEPPERS = registerBlock("wild_pale_bell_peppers",
            new ModWildCropBlock(StatusEffects.NIGHT_VISION, 10, AbstractBlock.Settings.copy(Blocks.TALL_GRASS)));
    public static final Block WILD_DARK_BELL_PEPPERS = registerBlock("wild_dark_bell_peppers",
            new ModWildCropBlock(StatusEffects.INVISIBILITY, 10, AbstractBlock.Settings.copy(Blocks.TALL_GRASS)));
    public static final Block WILD_COFFEE = registerBlock("wild_coffee",
            new ModWildCropBlock(StatusEffects.SPEED, 10, AbstractBlock.Settings.copy(Blocks.TALL_GRASS)));

    // Unobtainable blocks
    public static final Block COTTON_CROP = registerBlock("cotton",
            new CottonCropBlock(AbstractBlock.Settings.copy(Blocks.CARROTS).nonOpaque().noCollision()));
    public static final Block BELL_PEPPER_CROP = registerBlock("bell_peppers",
            new BellPepperCropBlock(AbstractBlock.Settings.copy(Blocks.CARROTS).nonOpaque().noCollision(), () -> ModItems.BELL_PEPPER_SEEDS));
    public static final Block PALE_BELL_PEPPER_CROP = registerBlock("pale_bell_peppers",
            new BellPepperCropBlock(AbstractBlock.Settings.copy(Blocks.CARROTS).nonOpaque().noCollision(), () -> ModItems.PALE_BELL_PEPPER_SEEDS));
    public static final Block DARK_BELL_PEPPER_CROP = registerBlock("dark_bell_peppers",
            new BellPepperCropBlock(AbstractBlock.Settings.copy(Blocks.CARROTS).nonOpaque().noCollision(), () -> ModItems.DARK_BELL_PEPPER_SEEDS));
    public static final Block COFFEE_CROP = registerBlock("coffee",
            new CoffeeCropBlock(AbstractBlock.Settings.copy(Blocks.CARROTS).nonOpaque().noCollision()));

    public static final Block POTTED_WILD_COTTON = registerBlock("potted_wild_cotton",
            new FlowerPotBlock(ModBlocks.WILD_COTTON, AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque()));
    public static final Block POTTED_WILD_BELL_PEPPERS = registerBlock("potted_wild_bell_peppers",
            new FlowerPotBlock(ModBlocks.WILD_BELL_PEPPERS, AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque()));
    public static final Block POTTED_WILD_PALE_BELL_PEPPERS = registerBlock("potted_wild_pale_bell_peppers",
            new FlowerPotBlock(ModBlocks.WILD_PALE_BELL_PEPPERS, AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque()));
    public static final Block POTTED_WILD_DARK_BELL_PEPPERS = registerBlock("potted_wild_dark_bell_peppers",
            new FlowerPotBlock(ModBlocks.WILD_DARK_BELL_PEPPERS, AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque()));
    public static final Block POTTED_WILD_COFFEE = registerBlock("potted_wild_coffee",
            new FlowerPotBlock(ModBlocks.WILD_COFFEE, AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(RusticDelight.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        RusticDelight.LOGGER.info("Registering blocks for " + RusticDelight.MOD_ID);
    }
}

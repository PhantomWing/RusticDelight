package com.phantomwing.rusticdelight.block;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.custom.*;
import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.List;

public class ModBlocks {

    // Seed bags
    public static final Block COTTON_SEEDS_BAG = registerBlock("cotton_seeds_bag", new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final Block BELL_PEPPER_SEEDS_BAG = registerBlock("bell_pepper_seeds_bag", new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final Block PALE_BELL_PEPPER_SEEDS_BAG = registerBlock("pale_bell_pepper_seeds_bag", new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final Block DARK_BELL_PEPPER_SEEDS_BAG = registerBlock("dark_bell_pepper_seeds_bag", new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final Block COFFEE_BEANS_BAG = registerBlock("coffee_beans_bag", new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final Block ROASTED_COFFEE_BEANS_BAG = registerBlock("roasted_coffee_beans_bag", new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));

    // Crop crates
    public static final Block COTTON_BOLL_CRATE = registerBlock("cotton_boll_crate", new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_GREEN_CRATE = registerBlock("bell_pepper_green_crate", new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_YELLOW_CRATE = registerBlock("bell_pepper_yellow_crate", new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_RED_CRATE = registerBlock("bell_pepper_red_crate", new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_ORANGE_CRATE = registerBlock("bell_pepper_orange_crate", new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_WHITE_CRATE = registerBlock("bell_pepper_white_crate", new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_PINK_CRATE = registerBlock("bell_pepper_pink_crate", new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_BLUE_CRATE = registerBlock("bell_pepper_blue_crate", new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_PURPLE_CRATE = registerBlock("bell_pepper_purple_crate", new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_BLACK_CRATE = registerBlock("bell_pepper_black_crate", new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block CALAMARI_CRATE = registerBlock("calamari_crate", new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Bell pepper blocks (9 slices, reversible)
    public static final Block BELL_PEPPER_GREEN_BLOCK = registerBlock("bell_pepper_green_block", new Block(Block.Properties.ofFullCopy(Blocks.MELON)));
    public static final Block BELL_PEPPER_YELLOW_BLOCK = registerBlock("bell_pepper_yellow_block", new Block(Block.Properties.ofFullCopy(Blocks.MELON)));
    public static final Block BELL_PEPPER_RED_BLOCK = registerBlock("bell_pepper_red_block", new Block(Block.Properties.ofFullCopy(Blocks.MELON)));
    public static final Block BELL_PEPPER_ORANGE_BLOCK = registerBlock("bell_pepper_orange_block", new Block(Block.Properties.ofFullCopy(Blocks.MELON)));
    public static final Block BELL_PEPPER_WHITE_BLOCK = registerBlock("bell_pepper_white_block", new Block(Block.Properties.ofFullCopy(Blocks.MELON)));
    public static final Block BELL_PEPPER_PINK_BLOCK = registerBlock("bell_pepper_pink_block", new Block(Block.Properties.ofFullCopy(Blocks.MELON)));
    public static final Block BELL_PEPPER_BLUE_BLOCK = registerBlock("bell_pepper_blue_block", new Block(Block.Properties.ofFullCopy(Blocks.MELON)));
    public static final Block BELL_PEPPER_PURPLE_BLOCK = registerBlock("bell_pepper_purple_block", new Block(Block.Properties.ofFullCopy(Blocks.MELON)));
    public static final Block BELL_PEPPER_BLACK_BLOCK = registerBlock("bell_pepper_black_block", new Block(Block.Properties.ofFullCopy(Blocks.MELON)));

    // Edible blocks
    public static final Block SYRUP_CHEESECAKE = registerBlock("syrup_cheesecake", new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), () -> ModItems.SYRUP_CHEESECAKE_SLICE));
    public static final Block CHERRY_BLOSSOM_CHEESECAKE = registerBlock("cherry_blossom_cheesecake", new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), () -> ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE));
    public static final Block COFFEE_CHEESECAKE = registerBlock("coffee_cheesecake", new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), () -> ModItems.COFFEE_CHEESECAKE_SLICE));
    public static final Block PANCAKES = registerBlock("pancakes", new PancakeBlock(() -> ModItems.PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final Block HONEY_PANCAKES = registerBlock("honey_pancakes", new PancakeBlock(() -> ModItems.HONEY_PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final Block CHOCOLATE_PANCAKES = registerBlock("chocolate_pancakes", new PancakeBlock(() -> ModItems.CHOCOLATE_PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final Block CHERRY_BLOSSOM_PANCAKES = registerBlock("cherry_blossom_pancakes", new PancakeBlock(() -> ModItems.CHERRY_BLOSSOM_PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final Block VEGETABLE_PANCAKES = registerBlock("vegetable_pancakes", new PancakeBlock(() -> ModItems.VEGETABLE_PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final Block PUMPKIN_PANCAKES = registerBlock("pumpkin_pancakes", new PancakeBlock(() -> ModItems.PUMPKIN_PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final Block COFFEE_PANCAKES = registerBlock("coffee_pancakes", new PancakeBlock(() -> ModItems.COFFEE_PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));

    // Feasts
    public static final Block RICE_ROLL_ROYALE = registerBlock("rice_roll_royale", new RiceRollRoyaleBlock(Block.Properties.ofFullCopy(Blocks.CAKE)));
    // Serving lists are ordered [back-left, back-right, front] to match the stage models.
    public static final Block BELL_PEPPER_MEDLEY = registerBlock("bell_pepper_medley", new BellPepperMedleyBlock(Block.Properties.ofFullCopy(Blocks.CAKE), List.of(
                    () -> ModItems.STUFFED_BELL_PEPPER_GREEN, () -> ModItems.STUFFED_BELL_PEPPER_YELLOW, () -> ModItems.STUFFED_BELL_PEPPER_RED)));
    public static final Block PALE_BELL_PEPPER_MEDLEY = registerBlock("pale_bell_pepper_medley", new BellPepperMedleyBlock(Block.Properties.ofFullCopy(Blocks.CAKE), List.of(
                    () -> ModItems.STUFFED_BELL_PEPPER_ORANGE, () -> ModItems.STUFFED_BELL_PEPPER_WHITE, () -> ModItems.STUFFED_BELL_PEPPER_PINK)));
    public static final Block DARK_BELL_PEPPER_MEDLEY = registerBlock("dark_bell_pepper_medley", new BellPepperMedleyBlock(Block.Properties.ofFullCopy(Blocks.CAKE), List.of(
                    () -> ModItems.STUFFED_BELL_PEPPER_BLUE, () -> ModItems.STUFFED_BELL_PEPPER_PURPLE, () -> ModItems.STUFFED_BELL_PEPPER_BLACK)));

    // Wild crops
    public static final Block WILD_COTTON = registerBlock("wild_cotton", new ModWildCropBlock(MobEffects.WEAVING, 12, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final Block WILD_BELL_PEPPERS = registerBlock("wild_bell_peppers", new ModWildCropBlock(MobEffects.FIRE_RESISTANCE, 10, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final Block WILD_PALE_BELL_PEPPERS = registerBlock("wild_pale_bell_peppers", new ModWildCropBlock(MobEffects.NIGHT_VISION, 10, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final Block WILD_DARK_BELL_PEPPERS = registerBlock("wild_dark_bell_peppers", new ModWildCropBlock(MobEffects.INVISIBILITY, 10, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final Block WILD_COFFEE = registerBlock("wild_coffee", new ModWildCropBlock(MobEffects.MOVEMENT_SPEED, 10, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));

    // Unobtainable blocks
    public static final Block COTTON_CROP = registerBlock("cotton", new CottonCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).noOcclusion().noCollission()));
    public static final Block BELL_PEPPER_CROP = registerBlock("bell_peppers", new BellPepperCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).noOcclusion().noCollission(), () -> ModItems.BELL_PEPPER_SEEDS));
    public static final Block PALE_BELL_PEPPER_CROP = registerBlock("pale_bell_peppers", new BellPepperCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).noOcclusion().noCollission(), () -> ModItems.PALE_BELL_PEPPER_SEEDS));
    public static final Block DARK_BELL_PEPPER_CROP = registerBlock("dark_bell_peppers", new BellPepperCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).noOcclusion().noCollission(), () -> ModItems.DARK_BELL_PEPPER_SEEDS));
    public static final Block COFFEE_CROP = registerBlock("coffee", new CoffeeCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).noOcclusion().noCollission()));

    public static final Block POTTED_WILD_COTTON = registerBlock("potted_wild_cotton", new FlowerPotBlock(ModBlocks.WILD_COTTON, Block.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final Block POTTED_WILD_BELL_PEPPERS = registerBlock("potted_wild_bell_peppers", new FlowerPotBlock(ModBlocks.WILD_BELL_PEPPERS, Block.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final Block POTTED_WILD_PALE_BELL_PEPPERS = registerBlock("potted_wild_pale_bell_peppers", new FlowerPotBlock(ModBlocks.WILD_PALE_BELL_PEPPERS, Block.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final Block POTTED_WILD_DARK_BELL_PEPPERS = registerBlock("potted_wild_dark_bell_peppers", new FlowerPotBlock(ModBlocks.WILD_DARK_BELL_PEPPERS, Block.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final Block POTTED_WILD_COFFEE = registerBlock("potted_wild_coffee", new FlowerPotBlock(ModBlocks.WILD_COFFEE, Block.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion()));

    private static Block registerBlock(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        RusticDelight.LOGGER.info("Registering blocks for " + RusticDelight.MOD_ID);
    }
}

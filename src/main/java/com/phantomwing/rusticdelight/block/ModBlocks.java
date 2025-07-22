package com.phantomwing.rusticdelight.block;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.custom.*;
import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import vectorwing.farmersdelight.common.block.PieBlock;

public class ModBlocks {
    // Seed bags
    public static final Block COTTON_SEEDS_BAG = registerBlock("cotton_seeds_bag",
            new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final Block BELL_PEPPER_SEEDS_BAG = registerBlock("bell_pepper_seeds_bag",
            new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final Block COFFEE_BEANS_BAG = registerBlock("coffee_beans_bag",
            new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final Block ROASTED_COFFEE_BEANS_BAG = registerBlock("roasted_coffee_beans_bag",
            new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));

    // Crop crates
    public static final Block COTTON_BOLL_CRATE = registerBlock("cotton_boll_crate",
            new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_GREEN_CRATE = registerBlock("bell_pepper_green_crate",
            new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_YELLOW_CRATE = registerBlock("bell_pepper_yellow_crate",
            new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_RED_CRATE = registerBlock("bell_pepper_red_crate",
            new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Edible blocks
    public static final Block SYRUP_CHEESECAKE = registerBlock("syrup_cheesecake",
            new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), () -> ModItems.SYRUP_CHEESECAKE_SLICE));
    public static final Block CHERRY_BLOSSOM_CHEESECAKE = registerBlock("cherry_blossom_cheesecake",
            new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), () -> ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE));
    public static final Block PANCAKES = registerBlock("pancakes",
            new PancakeBlock(() -> ModItems.PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final Block HONEY_PANCAKES = registerBlock("honey_pancakes",
            new PancakeBlock(() -> ModItems.HONEY_PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final Block CHOCOLATE_PANCAKES = registerBlock("chocolate_pancakes",
            new PancakeBlock(() -> ModItems.CHOCOLATE_PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final Block CHERRY_BLOSSOM_PANCAKES = registerBlock("cherry_blossom_pancakes",
            new PancakeBlock(() -> ModItems.CHERRY_BLOSSOM_PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final Block VEGETABLE_PANCAKES = registerBlock("vegetable_pancakes",
            new PancakeBlock(() -> ModItems.VEGETABLE_PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final Block PUMPKIN_PANCAKES = registerBlock("pumpkin_pancakes",
            new PancakeBlock(() -> ModItems.PUMPKIN_PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));

    // Feasts
    public static final Block RICE_ROLL_ROYALE = registerBlock("rice_roll_royale",
            new RiceRollRoyaleBlock(Block.Properties.ofFullCopy(Blocks.CAKE)));

    // Wild crops
    public static final Block WILD_COTTON = registerBlock("wild_cotton",
            new ModWildCropBlock(MobEffects.WEAVING, 12, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final Block WILD_BELL_PEPPERS = registerBlock("wild_bell_peppers",
            new ModWildCropBlock(MobEffects.FIRE_RESISTANCE, 10, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final Block WILD_COFFEE = registerBlock("wild_coffee",
            new ModWildCropBlock(MobEffects.MOVEMENT_SPEED, 10, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));

    // Unobtainable blocks
    public static final Block COTTON_CROP = registerBlock("cotton",
            new CottonCropBlock(Block.Properties.ofFullCopy(Blocks.CARROTS).noOcclusion().noCollission()));
    public static final Block BELL_PEPPER_CROP = registerBlock("bell_peppers",
            new BellPepperCropBlock(Block.Properties.ofFullCopy(Blocks.CARROTS).noOcclusion().noCollission()));
    public static final Block COFFEE_CROP = registerBlock("coffee",
            new CoffeeCropBlock(Block.Properties.ofFullCopy(Blocks.CARROTS).noOcclusion().noCollission()));

    public static final Block POTTED_WILD_COTTON = registerBlock("potted_wild_cotton",
            new FlowerPotBlock(ModBlocks.WILD_COTTON, Block.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final Block POTTED_WILD_BELL_PEPPERS = registerBlock("potted_wild_bell_peppers",
            new FlowerPotBlock(ModBlocks.WILD_BELL_PEPPERS, Block.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final Block POTTED_WILD_COFFEE = registerBlock("potted_wild_coffee",
            new FlowerPotBlock(ModBlocks.WILD_COFFEE, Block.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion()));

    private static Block registerBlock(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        RusticDelight.LOGGER.info("Registering blocks for " + RusticDelight.MOD_ID);
    }
}

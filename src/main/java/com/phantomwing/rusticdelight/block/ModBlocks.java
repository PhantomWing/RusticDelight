package com.phantomwing.rusticdelight.block;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.custom.*;
import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.List;
import java.util.function.Function;

public class ModBlocks {
    // Seed bags
    public static final Block COTTON_SEEDS_BAG = registerBlock("cotton_seeds_bag", Blocks.WOOL.white(), Block::new);
    public static final Block BELL_PEPPER_SEEDS_BAG = registerBlock("bell_pepper_seeds_bag", Blocks.WOOL.white(), Block::new);
    public static final Block PALE_BELL_PEPPER_SEEDS_BAG = registerBlock("pale_bell_pepper_seeds_bag", Blocks.WOOL.white(), Block::new);
    public static final Block DARK_BELL_PEPPER_SEEDS_BAG = registerBlock("dark_bell_pepper_seeds_bag", Blocks.WOOL.white(), Block::new);
    public static final Block COFFEE_BEANS_BAG = registerBlock("coffee_beans_bag", Blocks.WOOL.white(), Block::new);
    public static final Block ROASTED_COFFEE_BEANS_BAG = registerBlock("roasted_coffee_beans_bag", Blocks.WOOL.white(), Block::new);

    // Crop crates
    public static final Block COTTON_BOLL_CRATE = registerBlock("cotton_boll_crate", Blocks.OAK_PLANKS,
            props -> new Block(props.strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_GREEN_CRATE = registerBlock("bell_pepper_green_crate", Blocks.OAK_PLANKS,
            props -> new Block(props.strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_YELLOW_CRATE = registerBlock("bell_pepper_yellow_crate", Blocks.OAK_PLANKS,
            props -> new Block(props.strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_RED_CRATE = registerBlock("bell_pepper_red_crate", Blocks.OAK_PLANKS,
            props -> new Block(props.strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_ORANGE_CRATE = registerBlock("bell_pepper_orange_crate", Blocks.OAK_PLANKS,
            props -> new Block(props.strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_WHITE_CRATE = registerBlock("bell_pepper_white_crate", Blocks.OAK_PLANKS,
            props -> new Block(props.strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_PINK_CRATE = registerBlock("bell_pepper_pink_crate", Blocks.OAK_PLANKS,
            props -> new Block(props.strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_BLUE_CRATE = registerBlock("bell_pepper_blue_crate", Blocks.OAK_PLANKS,
            props -> new Block(props.strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_PURPLE_CRATE = registerBlock("bell_pepper_purple_crate", Blocks.OAK_PLANKS,
            props -> new Block(props.strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_BLACK_CRATE = registerBlock("bell_pepper_black_crate", Blocks.OAK_PLANKS,
            props -> new Block(props.strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block CALAMARI_CRATE = registerBlock("calamari_crate", Blocks.OAK_PLANKS,
            props -> new Block(props.strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Giant bell peppers - melon-like blocks, cut back into slices.
    public static final Block BELL_PEPPER_GREEN_BLOCK = registerBlock("bell_pepper_green_block", Blocks.MELON, Block::new);
    public static final Block BELL_PEPPER_YELLOW_BLOCK = registerBlock("bell_pepper_yellow_block", Blocks.MELON, Block::new);
    public static final Block BELL_PEPPER_RED_BLOCK = registerBlock("bell_pepper_red_block", Blocks.MELON, Block::new);
    public static final Block BELL_PEPPER_ORANGE_BLOCK = registerBlock("bell_pepper_orange_block", Blocks.MELON, Block::new);
    public static final Block BELL_PEPPER_WHITE_BLOCK = registerBlock("bell_pepper_white_block", Blocks.MELON, Block::new);
    public static final Block BELL_PEPPER_PINK_BLOCK = registerBlock("bell_pepper_pink_block", Blocks.MELON, Block::new);
    public static final Block BELL_PEPPER_BLUE_BLOCK = registerBlock("bell_pepper_blue_block", Blocks.MELON, Block::new);
    public static final Block BELL_PEPPER_PURPLE_BLOCK = registerBlock("bell_pepper_purple_block", Blocks.MELON, Block::new);
    public static final Block BELL_PEPPER_BLACK_BLOCK = registerBlock("bell_pepper_black_block", Blocks.MELON, Block::new);

    // Edible blocks
    public static final Block SYRUP_CHEESECAKE = registerBlock("syrup_cheesecake", Blocks.CAKE,
            props -> new PieBlock(props, () -> ModItems.SYRUP_CHEESECAKE_SLICE));
    public static final Block CHERRY_BLOSSOM_CHEESECAKE = registerBlock("cherry_blossom_cheesecake", Blocks.CAKE,
            props -> new PieBlock(props, () -> ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE));
    public static final Block COFFEE_CHEESECAKE = registerBlock("coffee_cheesecake", Blocks.CAKE,
            props -> new PieBlock(props, () -> ModItems.COFFEE_CHEESECAKE_SLICE));
    public static final Block PANCAKES = registerBlock("pancakes", Blocks.CAKE,
            props -> new PancakeBlock(() -> ModItems.PANCAKE, props.sound(SoundType.WOOD)));
    public static final Block HONEY_PANCAKES = registerBlock("honey_pancakes", Blocks.CAKE,
            props -> new PancakeBlock(() -> ModItems.HONEY_PANCAKE, props.sound(SoundType.WOOD)));
    public static final Block CHOCOLATE_PANCAKES = registerBlock("chocolate_pancakes", Blocks.CAKE,
            props -> new PancakeBlock(() -> ModItems.CHOCOLATE_PANCAKE, props.sound(SoundType.WOOD)));
    public static final Block CHERRY_BLOSSOM_PANCAKES = registerBlock("cherry_blossom_pancakes", Blocks.CAKE,
            props -> new PancakeBlock(() -> ModItems.CHERRY_BLOSSOM_PANCAKE, props.sound(SoundType.WOOD)));
    public static final Block VEGETABLE_PANCAKES = registerBlock("vegetable_pancakes", Blocks.CAKE,
            props -> new PancakeBlock(() -> ModItems.VEGETABLE_PANCAKE, props.sound(SoundType.WOOD)));
    public static final Block PUMPKIN_PANCAKES = registerBlock("pumpkin_pancakes", Blocks.CAKE,
            props -> new PancakeBlock(() -> ModItems.PUMPKIN_PANCAKE, props.sound(SoundType.WOOD)));
    public static final Block COFFEE_PANCAKES = registerBlock("coffee_pancakes", Blocks.CAKE,
            props -> new PancakeBlock(() -> ModItems.COFFEE_PANCAKE, props.sound(SoundType.WOOD)));

    // Feasts
    public static final Block RICE_ROLL_ROYALE = registerBlock("rice_roll_royale", Blocks.CAKE,
            props -> new RiceRollRoyaleBlock(props.sound(SoundType.WOOD)));
    public static final Block BELL_PEPPER_MEDLEY = registerBlock("bell_pepper_medley", Blocks.CAKE,
            props -> new BellPepperMedleyBlock(props, List.of(
                    () -> ModItems.STUFFED_BELL_PEPPER_GREEN,
                    () -> ModItems.STUFFED_BELL_PEPPER_YELLOW,
                    () -> ModItems.STUFFED_BELL_PEPPER_RED)));
    public static final Block PALE_BELL_PEPPER_MEDLEY = registerBlock("pale_bell_pepper_medley", Blocks.CAKE,
            props -> new BellPepperMedleyBlock(props, List.of(
                    () -> ModItems.STUFFED_BELL_PEPPER_ORANGE,
                    () -> ModItems.STUFFED_BELL_PEPPER_WHITE,
                    () -> ModItems.STUFFED_BELL_PEPPER_PINK)));
    public static final Block DARK_BELL_PEPPER_MEDLEY = registerBlock("dark_bell_pepper_medley", Blocks.CAKE,
            props -> new BellPepperMedleyBlock(props, List.of(
                    () -> ModItems.STUFFED_BELL_PEPPER_BLUE,
                    () -> ModItems.STUFFED_BELL_PEPPER_PURPLE,
                    () -> ModItems.STUFFED_BELL_PEPPER_BLACK)));

    // Wild crops
    public static final Block WILD_COTTON = registerBlock("wild_cotton", Blocks.TALL_GRASS,
            props -> new ModWildCropBlock(MobEffects.WEAVING, 12, props));
    public static final Block WILD_BELL_PEPPERS = registerBlock("wild_bell_peppers", Blocks.TALL_GRASS,
            props -> new ModWildCropBlock(MobEffects.FIRE_RESISTANCE, 10, props));
    public static final Block WILD_PALE_BELL_PEPPERS = registerBlock("wild_pale_bell_peppers", Blocks.TALL_GRASS,
            props -> new ModWildCropBlock(MobEffects.NIGHT_VISION, 10, props));
    public static final Block WILD_DARK_BELL_PEPPERS = registerBlock("wild_dark_bell_peppers", Blocks.TALL_GRASS,
            props -> new ModWildCropBlock(MobEffects.INVISIBILITY, 10, props));
    public static final Block WILD_COFFEE = registerBlock("wild_coffee", Blocks.TALL_GRASS,
            props -> new ModWildCropBlock(MobEffects.SPEED, 10, props));

    // Unobtainable blocks
    public static final Block COTTON_CROP = registerBlock("cotton", Blocks.CARROTS,
            props -> new CottonCropBlock(props.noOcclusion().noCollision()));
    public static final Block BELL_PEPPER_CROP = registerBlock("bell_peppers", Blocks.CARROTS,
            props -> new BellPepperCropBlock(props.noOcclusion().noCollision(), () -> ModItems.BELL_PEPPER_SEEDS));
    public static final Block PALE_BELL_PEPPER_CROP = registerBlock("pale_bell_peppers", Blocks.CARROTS,
            props -> new BellPepperCropBlock(props.noOcclusion().noCollision(), () -> ModItems.PALE_BELL_PEPPER_SEEDS));
    public static final Block DARK_BELL_PEPPER_CROP = registerBlock("dark_bell_peppers", Blocks.CARROTS,
            props -> new BellPepperCropBlock(props.noOcclusion().noCollision(), () -> ModItems.DARK_BELL_PEPPER_SEEDS));
    public static final Block COFFEE_CROP = registerBlock("coffee", Blocks.CARROTS,
            props -> new CoffeeCropBlock(props.noOcclusion().noCollision()));

    public static final Block POTTED_WILD_COTTON = registerBlock("potted_wild_cotton", Blocks.POTTED_ALLIUM,
            props -> new FlowerPotBlock(ModBlocks.WILD_COTTON, props.noOcclusion()));
    public static final Block POTTED_WILD_BELL_PEPPERS = registerBlock("potted_wild_bell_peppers", Blocks.POTTED_ALLIUM,
            props -> new FlowerPotBlock(ModBlocks.WILD_BELL_PEPPERS, props.noOcclusion()));
    public static final Block POTTED_WILD_PALE_BELL_PEPPERS = registerBlock("potted_wild_pale_bell_peppers", Blocks.POTTED_ALLIUM,
            props -> new FlowerPotBlock(ModBlocks.WILD_PALE_BELL_PEPPERS, props.noOcclusion()));
    public static final Block POTTED_WILD_DARK_BELL_PEPPERS = registerBlock("potted_wild_dark_bell_peppers", Blocks.POTTED_ALLIUM,
            props -> new FlowerPotBlock(ModBlocks.WILD_DARK_BELL_PEPPERS, props.noOcclusion()));
    public static final Block POTTED_WILD_COFFEE = registerBlock("potted_wild_coffee", Blocks.POTTED_ALLIUM,
            props -> new FlowerPotBlock(ModBlocks.WILD_COFFEE, props.noOcclusion()));

    private static Block registerBlock(String name, Function<Block.Properties, Block> function) {
        return registerBlock(name, Block.Properties.of(), function);
    }

    private static Block registerBlock(String name, Block copy, Function<Block.Properties, Block> function) {
        return registerBlock(name, Block.Properties.ofFullCopy(copy), function);
    }

    private static Block registerBlock(String name, BlockBehaviour.Properties baseProps, Function<Block.Properties, Block> function) {
        Identifier loc = Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, name);
        return Registry.register(BuiltInRegistries.BLOCK, loc,
                function.apply(baseProps.setId(ResourceKey.create(Registries.BLOCK, loc))));
    }

    public static void registerModBlocks() {
        RusticDelight.LOGGER.info("Registering blocks for " + RusticDelight.MOD_ID);
    }
}

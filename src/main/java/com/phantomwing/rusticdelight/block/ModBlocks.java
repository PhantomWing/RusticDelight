package com.phantomwing.rusticdelight.block;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.custom.BellPepperCropBlock;
import com.phantomwing.rusticdelight.block.custom.CottonCropBlock;
import com.phantomwing.rusticdelight.block.custom.PancakeBlock;
import com.phantomwing.rusticdelight.food.FoodValues;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.block.WildCropBlock;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(RusticDelight.MOD_ID);

    // Seed bags
    public static final DeferredBlock<Block> COTTON_SEEDS_BAG = BLOCKS.register("cotton_seeds_bag",
            () -> new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> BELL_PEPPER_SEEDS_BAG = BLOCKS.register("bell_pepper_seeds_bag",
            () -> new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));

    // Crop crates
    public static final DeferredBlock<Block> COTTON_BOLL_CRATE = BLOCKS.register("cotton_boll_crate",
            () -> new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> BELL_PEPPER_GREEN_CRATE = BLOCKS.register("bell_pepper_green_crate",
            () -> new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> BELL_PEPPER_YELLOW_CRATE = BLOCKS.register("bell_pepper_yellow_crate",
            () -> new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> BELL_PEPPER_RED_CRATE = BLOCKS.register("bell_pepper_red_crate",
            () -> new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Edible blocks
    public static final DeferredBlock<Block> HONEY_PANCAKES = BLOCKS.register("honey_pancakes",
            () -> new PancakeBlock(FoodValues.HONEY_PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> CHOCOLATE_PANCAKES = BLOCKS.register("chocolate_pancakes",
            () -> new PancakeBlock(FoodValues.CHOCOLATE_PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> VEGETABLE_PANCAKES = BLOCKS.register("vegetable_pancakes",
            () -> new PancakeBlock(FoodValues.VEGETABLE_PANCAKE, Block.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));

    // Wild crops
    public static final DeferredBlock<Block> WILD_COTTON = BLOCKS.register("wild_cotton",
            () -> new WildCropBlock(MobEffects.WEAVING, 12, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final DeferredBlock<Block> WILD_BELL_PEPPERS = BLOCKS.register("wild_bell_peppers",
            () -> new WildCropBlock(MobEffects.FIRE_RESISTANCE, 10, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));

    // Unobtainable blocks
    public static final DeferredBlock<Block> COTTON_CROP = BLOCKS.register("cotton",
            () -> new CottonCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).noOcclusion().noCollission()));
    public static final DeferredBlock<Block> BELL_PEPPER_CROP = BLOCKS.register("bell_peppers",
            () -> new BellPepperCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).noOcclusion().noCollission()));
    public static final DeferredBlock<Block> POTTED_WILD_COTTON = BLOCKS.register("potted_wild_cotton",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.WILD_COTTON, Block.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final DeferredBlock<Block> POTTED_WILD_BELL_PEPPERS = BLOCKS.register("potted_wild_bell_peppers",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.WILD_BELL_PEPPERS, Block.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

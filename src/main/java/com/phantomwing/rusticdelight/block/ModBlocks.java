package com.phantomwing.rusticdelight.block;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.custom.BellPepperCropBlock;
import com.phantomwing.rusticdelight.block.custom.CottonCropBlock;
import com.phantomwing.rusticdelight.block.custom.PancakeBlock;
import com.phantomwing.rusticdelight.food.FoodValues;
import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.block.WildCropBlock;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RusticDelight.MOD_ID);

    // Seed bags
    public static final RegistryObject<Block> COTTON_SEEDS_BAG = BLOCKS.register("cotton_seeds_bag",
            () -> new Block(Block.Properties.copy(Blocks.WHITE_WOOL)));
    public static final  RegistryObject<Block> BELL_PEPPER_SEEDS_BAG = BLOCKS.register("bell_pepper_seeds_bag",
            () -> new Block(Block.Properties.copy(Blocks.WHITE_WOOL)));

    // Crop crates
    public static final RegistryObject<Block> COTTON_BOLL_CRATE = BLOCKS.register("cotton_boll_crate",
            () -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BELL_PEPPER_GREEN_CRATE = BLOCKS.register("bell_pepper_green_crate",
            () -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BELL_PEPPER_YELLOW_CRATE = BLOCKS.register("bell_pepper_yellow_crate",
            () -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BELL_PEPPER_RED_CRATE = BLOCKS.register("bell_pepper_red_crate",
            () -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Edible blocks
    public static final RegistryObject<Block> CHERRY_BLOSSOM_CHEESECAKE = BLOCKS.register("cherry_blossom_cheesecake",
            () -> new PieBlock(Block.Properties.copy(Blocks.CAKE), ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE));
    public static final RegistryObject<Block> HONEY_PANCAKES = BLOCKS.register("honey_pancakes",
            () -> new PancakeBlock(FoodValues.HONEY_PANCAKE, Block.Properties.copy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHOCOLATE_PANCAKES = BLOCKS.register("chocolate_pancakes",
            () -> new PancakeBlock(FoodValues.CHOCOLATE_PANCAKE, Block.Properties.copy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHERRY_BLOSSOM_PANCAKES = BLOCKS.register("cherry_blossom_pancakes",
            () -> new PancakeBlock(FoodValues.CHERRY_BLOSSOM_PANCAKE, Block.Properties.copy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> VEGETABLE_PANCAKES = BLOCKS.register("vegetable_pancakes",
            () -> new PancakeBlock(FoodValues.VEGETABLE_PANCAKE, Block.Properties.copy(Blocks.CAKE).sound(SoundType.WOOD)));

    // Wild crops
    public static final RegistryObject<Block> WILD_COTTON = BLOCKS.register("wild_cotton",
            () -> new WildCropBlock(MobEffects.DIG_SPEED, 12, Block.Properties.copy(Blocks.TALL_GRASS)));
    public static final RegistryObject<Block> WILD_BELL_PEPPERS = BLOCKS.register("wild_bell_peppers",
            () -> new WildCropBlock(MobEffects.FIRE_RESISTANCE, 10, Block.Properties.copy(Blocks.TALL_GRASS)));

    // Unobtainable blocks
    public static final RegistryObject<Block> COTTON_CROP = BLOCKS.register("cotton",
            () -> new CottonCropBlock(BlockBehaviour.Properties.copy(Blocks.CARROTS).noOcclusion().noCollission()));
    public static final RegistryObject<Block> BELL_PEPPER_CROP = BLOCKS.register("bell_peppers",
            () -> new BellPepperCropBlock(BlockBehaviour.Properties.copy(Blocks.CARROTS).noOcclusion().noCollission()));
    public static final RegistryObject<Block> POTTED_WILD_COTTON = BLOCKS.register("potted_wild_cotton",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.WILD_COTTON, Block.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final RegistryObject<Block> POTTED_WILD_BELL_PEPPERS = BLOCKS.register("potted_wild_bell_peppers",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.WILD_BELL_PEPPERS, Block.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

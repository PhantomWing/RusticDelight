package com.phantomwing.rusticdelight.block;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.custom.CottonCropBlock;
import com.phantomwing.rusticdelight.item.BlockItems;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.block.WildCropBlock;

import java.util.function.Supplier;

public class BlockManager {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(RusticDelight.MOD_ID);

    // Cotton
    public static final DeferredBlock<Block> COTTON_CROP = BLOCKS.register("cotton",
            () -> new CottonCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).noOcclusion().noCollission()));
    public static final DeferredBlock<Block> POTTED_WILD_COTTON = BLOCKS.register("potted_wild_cotton",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), BlockManager.WILD_COTTON, Block.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static final DeferredBlock<Block> WILD_COTTON = registerSimpleBlock("wild_cotton",
            () -> new WildCropBlock(MobEffects.WEAVING, 12, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final DeferredBlock<Block> COTTON_SEEDS_BAG = registerSimpleBlock("cotton_seeds_bag",
            () -> new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> COTTON_BOLL_CRATE = registerSimpleBlock("cotton_boll_crate",
            () -> new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    private static <T extends Block> DeferredBlock<T> registerSimpleBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerSimpleBlockItem(name, toReturn);

        return toReturn;
    }

    private static <T extends Block> void registerSimpleBlockItem(String name, DeferredBlock<T> block) {
        BlockItems.registerWithTab(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
    }
}

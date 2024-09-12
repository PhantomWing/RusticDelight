package com.phantomwing.rusticdelight.block;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.custom.CottonCropBlock;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.block.WildCropBlock;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(RusticDelight.MOD_ID);

    // Blocks
    public static final DeferredBlock<Block> WILD_COTTON = BLOCKS.register("wild_cotton",
            () -> new WildCropBlock(MobEffects.WEAVING, 12, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final DeferredBlock<Block> COTTON_SEEDS_BAG = BLOCKS.register("cotton_seeds_bag",
            () -> new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> COTTON_BOLL_CRATE = BLOCKS.register("cotton_boll_crate",
            () -> new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Unobtainable blocks
    public static final DeferredBlock<Block> COTTON_CROP = BLOCKS.register("cotton",
            () -> new CottonCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).noOcclusion().noCollission()));
    public static final DeferredBlock<Block> POTTED_WILD_COTTON = BLOCKS.register("potted_wild_cotton",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.WILD_COTTON, Block.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

package com.phantomwing.rusticdelight;

import com.phantomwing.rusticdelight.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

@Environment(EnvType.CLIENT)
public class RusticDelightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Make sure some blocks are rendered correctly as a cutout.
        BlockRenderLayerMap.putBlocks(
                ChunkSectionLayer.CUTOUT,
                ModBlocks.WILD_BELL_PEPPERS,
                ModBlocks.WILD_COTTON,
                ModBlocks.WILD_COFFEE,
                ModBlocks.COTTON_CROP,
                ModBlocks.BELL_PEPPER_CROP,
                ModBlocks.COFFEE_CROP,
                ModBlocks.POTTED_WILD_COTTON,
                ModBlocks.POTTED_WILD_BELL_PEPPERS,
                ModBlocks.POTTED_WILD_COFFEE
        );
    }
}

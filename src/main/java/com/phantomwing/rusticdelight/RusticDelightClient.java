package com.phantomwing.rusticdelight;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class RusticDelightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 26.1: BlockRenderLayerMap was removed from fabric-api but isn't needed — MC 26.1 auto-
        // detects cutout rendering for blocks whose models use `block/cross`, `block/crop`, and
        // `block/flower_pot_cross` parents. All our wild plants and crops inherit from those, so
        // they render correctly without explicit registration.
    }
}

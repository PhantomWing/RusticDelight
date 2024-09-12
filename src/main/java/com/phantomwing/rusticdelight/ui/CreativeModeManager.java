package com.phantomwing.rusticdelight.ui;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.BlockManager;
import com.phantomwing.rusticdelight.item.ItemManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeModeManager {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RusticDelight.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MOD_TAB =
            CREATIVE_MODE_TABS.register(RusticDelight.MOD_ID + "_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(BlockManager.WILD_COTTON.get()))
                    .title(Component.translatable(("creativetab." + RusticDelight.MOD_ID)))
                    .displayItems((parameters, output) -> {
                        // Add items to this tab.
                        ItemManager.CREATIVE_TAB_ITEMS.forEach((item) -> output.accept(item.get()));
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

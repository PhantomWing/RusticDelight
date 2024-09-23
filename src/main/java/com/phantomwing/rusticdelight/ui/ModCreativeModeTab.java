package com.phantomwing.rusticdelight.ui;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RusticDelight.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MOD_TAB =
            CREATIVE_MODE_TABS.register(RusticDelight.MOD_ID + "_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.WILD_COTTON.get()))
                    .title(Component.translatable(("itemGroup." + RusticDelight.MOD_ID)))
                    .displayItems((pParameters, pOutput) -> {
                        // Add items to this tab.
                        ModItems.CREATIVE_TAB_ITEMS.forEach((item) -> pOutput.accept(item.get()));
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
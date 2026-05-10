package com.phantomwing.rusticdelight.itemGroup;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroups {
    public static final CreativeModeTab MOD_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, "item_group"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.WILD_COTTON))
                    .title(Component.translatable("itemGroup." + RusticDelight.MOD_ID))
                    .displayItems((displayContext, entries) -> {
                        // Add items to this tab.
                        ModItems.CREATIVE_TAB_ITEMS.forEach(entries::accept);
                    })
                    .build());

    public static void registerModItemGroups() {
        RusticDelight.LOGGER.info("Registering item group for " + RusticDelight.MOD_ID);
    }
}

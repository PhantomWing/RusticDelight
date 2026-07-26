package com.phantomwing.rusticdelight.itemGroup;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.item.ItemFamily;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroups {
    public static final CreativeModeTab MOD_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "item_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.WILD_COTTON))
                    .title(Component.translatable("itemGroup." + RusticDelight.MOD_ID))
                    .displayItems((displayContext, entries) -> {
                        // Add items to this tab, skipping any whose families aren't all enabled.
                        ModItems.CREATIVE_TAB_ITEMS.forEach((item, families) -> {
                            for (ItemFamily family : families) {
                                if (!family.isEnabled()) {
                                    return;
                                }
                            }
                            entries.accept(item);
                        });
                    })
                    .build());

    public static void registerModItemGroups() {
        RusticDelight.LOGGER.info("Registering item group for " + RusticDelight.MOD_ID);
    }
}

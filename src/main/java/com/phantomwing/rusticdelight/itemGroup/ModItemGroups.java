package com.phantomwing.rusticdelight.itemGroup;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup MOD_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(RusticDelight.MOD_ID, "item_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.WILD_COTTON))
                    .displayName(Text.translatable("itemgroup." + RusticDelight.MOD_ID))
                    .entries((displayContext, entries) -> {
                        // Add items to this tab.
                        ModItems.CREATIVE_TAB_ITEMS.forEach(entries::add);
                    })
                    .build());

    public static void registerModItemGroups() {
        RusticDelight.LOGGER.info("Registering item group for " + RusticDelight.MOD_ID);
    }
}

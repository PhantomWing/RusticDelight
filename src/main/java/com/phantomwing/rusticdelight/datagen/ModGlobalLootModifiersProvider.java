package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.loot.SquidsDropCalamariModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, RusticDelight.MOD_ID);
    }

    @Override
    protected void start() {
        // Entity loot tables
        add("calamari_from_squid", new SquidsDropCalamariModifier(
                new LootItemCondition[] {
                        defaultLootTable("entities/squid"),
                },
                ModItems.CALAMARI.get(),
                1,
                2
        ));
        add("calamari_from_glow_squid", new SquidsDropCalamariModifier(
                new LootItemCondition[] {
                        defaultLootTable("entities/glow_squid"),
                },
                ModItems.CALAMARI.get(),
                1,
                2
        ));
    }

    private LootItemCondition defaultLootTable(String name) {
        return new LootTableIdCondition.Builder(new ResourceLocation(name)).build();
    }
}
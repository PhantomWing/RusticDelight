package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.Configuration;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.condition.ConfigBooleanCondition;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.loot.SquidsDropCalamariModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import vectorwing.farmersdelight.common.loot.modifier.ReplaceItemModifier;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, RusticDelight.MOD_ID);
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

        // Chest loot swaps an existing item rather than adding one, so chests stay vanilla-sized.
        // If the chest never rolled the target item, nothing happens.

        // Cotton takes the place of another crop's seeds wherever villagers or miners store them.
        replaceInChest("cotton_seeds_from_village_savanna", "chests/village/village_savanna_house",
                Items.WHEAT_SEEDS, ModItems.COTTON_SEEDS.get(), 1, Configuration.ENABLE_COTTON_ID);
        replaceInChest("cotton_seeds_from_village_taiga", "chests/village/village_taiga_house",
                Items.PUMPKIN_SEEDS, ModItems.COTTON_SEEDS.get(), 1, Configuration.ENABLE_COTTON_ID);
        replaceInChest("cotton_seeds_from_village_snowy", "chests/village/village_snowy_house",
                Items.BEETROOT_SEEDS, ModItems.COTTON_SEEDS.get(), 1, Configuration.ENABLE_COTTON_ID);
        replaceInChest("cotton_seeds_from_abandoned_mineshaft", "chests/abandoned_mineshaft",
                Items.MELON_SEEDS, ModItems.COTTON_SEEDS.get(), 1, Configuration.ENABLE_COTTON_ID);
        replaceInChest("cotton_seeds_from_shipwreck_supply", "chests/shipwreck_supply",
                Items.WHEAT, ModItems.COTTON_SEEDS.get(), 1, Configuration.ENABLE_COTTON_ID);

        // Jungle temples sit where coffee and wild bell peppers grow, and are full of low-value
        // filler (bone, rotten flesh and bamboo are over half the pool) to convert. Each target is
        // used once so the modifiers can't compete for the same stacks.
        replaceInChest("coffee_beans_from_jungle_temple", "chests/jungle_temple",
                Items.BAMBOO, ModItems.COFFEE_BEANS.get(), 2, Configuration.ENABLE_COFFEE_ID, 0.5F);
        replaceInChest("pale_bell_pepper_seeds_from_jungle_temple", "chests/jungle_temple",
                Items.BONE, ModItems.PALE_BELL_PEPPER_SEEDS.get(), 1, Configuration.ENABLE_BELL_PEPPERS_ID, 0.25F);
        replaceInChest("dark_bell_pepper_seeds_from_jungle_temple", "chests/jungle_temple",
                Items.ROTTEN_FLESH, ModItems.DARK_BELL_PEPPER_SEEDS.get(), 1, Configuration.ENABLE_BELL_PEPPERS_ID, 0.25F);
    }

    // Chest swaps need both the master loot toggle and their own crop family enabled.
    private void replaceInChest(String name, String lootTable, Item removed, Item added, int count, String settingId) {
        add(name, new ReplaceItemModifier(new LootItemCondition[]{defaultLootTable(lootTable)}, removed, added, count),
                new ConfigBooleanCondition(Configuration.GENERATE_RANDOM_LOOT_ID),
                new ConfigBooleanCondition(settingId));
    }

    private void replaceInChest(String name, String lootTable, Item removed, Item added, int count, String settingId, float chance) {
        add(name, new ReplaceItemModifier(new LootItemCondition[]{
                        defaultLootTable(lootTable),
                        LootItemRandomChanceCondition.randomChance(chance).build(),
                }, removed, added, count),
                new ConfigBooleanCondition(Configuration.GENERATE_RANDOM_LOOT_ID),
                new ConfigBooleanCondition(settingId));
    }

    private LootItemCondition defaultLootTable(String name) {
        return new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace(name)).build();
    }
}

package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.item.ItemFamily;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Injects Rustic Delight crops into vanilla village farm plots, mirroring Farmer's Delight.
 * Each crop replaces a distinct vanilla crop (so they spread evenly), with a wheat sprinkle for density.
 */
public class ModVillageStructures {
    public static void registerVillageCrops() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            if (!RusticDelightConfig.getBooleanConfigurationValue(RusticDelightConfig.GENERATE_VILLAGE_FARM_CROPS_ID)) {
                return;
            }

            boolean bellPeppers = ItemFamily.BELL_PEPPER.isEnabled();
            boolean cottonEnabled = ItemFamily.COTTON.isEnabled();
            boolean coffeeEnabled = ItemFamily.COFFEE.isEnabled();

            // Nothing to inject if every crop family is disabled.
            if (!bellPeppers && !cottonEnabled && !coffeeEnabled) {
                return;
            }

            Registry<StructureProcessorList> registry = server.getRegistryManager().get(RegistryKeys.PROCESSOR_LIST);

            BlockState bellPepper = ModBlocks.BELL_PEPPER_CROP.getDefaultState();
            BlockState cotton = ModBlocks.COTTON_CROP.getDefaultState();
            BlockState coffee = ModBlocks.COFFEE_CROP.getDefaultState();

            // Temperate (plains, taiga): carrots -> bell pepper, potatoes -> cotton, plus a wheat sprinkle.
            // Wheat-rule order is swapped between the two lists so the first-match bias cancels out.
            List<StructureProcessorRule> plains = new ArrayList<>();
            List<StructureProcessorRule> taiga = new ArrayList<>();
            if (bellPeppers) {
                plains.add(rule(Blocks.CARROTS, 0.3F, bellPepper));
            }
            if (cottonEnabled) {
                plains.add(rule(Blocks.POTATOES, 0.3F, cotton));
            }
            if (bellPeppers) {
                plains.add(rule(Blocks.WHEAT, 0.12F, bellPepper));
            }
            if (cottonEnabled) {
                plains.add(rule(Blocks.WHEAT, 0.12F, cotton));
            }
            if (bellPeppers) {
                taiga.add(rule(Blocks.CARROTS, 0.3F, bellPepper));
            }
            if (cottonEnabled) {
                taiga.add(rule(Blocks.POTATOES, 0.3F, cotton));
            }
            if (cottonEnabled) {
                taiga.add(rule(Blocks.WHEAT, 0.12F, cotton));
            }
            if (bellPeppers) {
                taiga.add(rule(Blocks.WHEAT, 0.12F, bellPepper));
            }

            // Arid (savanna, desert): also beetroots -> coffee.
            List<StructureProcessorRule> savanna = new ArrayList<>();
            List<StructureProcessorRule> desert = new ArrayList<>();
            if (bellPeppers) {
                savanna.add(rule(Blocks.CARROTS, 0.3F, bellPepper));
            }
            if (cottonEnabled) {
                savanna.add(rule(Blocks.POTATOES, 0.3F, cotton));
            }
            if (coffeeEnabled) {
                savanna.add(rule(Blocks.BEETROOTS, 0.3F, coffee));
            }
            if (bellPeppers) {
                savanna.add(rule(Blocks.WHEAT, 0.1F, bellPepper));
            }
            if (cottonEnabled) {
                savanna.add(rule(Blocks.WHEAT, 0.1F, cotton));
            }
            if (coffeeEnabled) {
                savanna.add(rule(Blocks.WHEAT, 0.1F, coffee));
            }
            if (bellPeppers) {
                desert.add(rule(Blocks.CARROTS, 0.3F, bellPepper));
            }
            if (cottonEnabled) {
                desert.add(rule(Blocks.POTATOES, 0.3F, cotton));
            }
            if (coffeeEnabled) {
                desert.add(rule(Blocks.BEETROOTS, 0.3F, coffee));
            }
            if (coffeeEnabled) {
                desert.add(rule(Blocks.WHEAT, 0.1F, coffee));
            }
            if (cottonEnabled) {
                desert.add(rule(Blocks.WHEAT, 0.1F, cotton));
            }
            if (bellPeppers) {
                desert.add(rule(Blocks.WHEAT, 0.1F, bellPepper));
            }

            addProcessor(registry, new Identifier("farm_plains"), plains);
            addProcessor(registry, new Identifier("farm_taiga"), taiga);
            addProcessor(registry, new Identifier("farm_savanna"), savanna);
            addProcessor(registry, new Identifier("farm_desert"), desert);
        });
    }

    private static StructureProcessorRule rule(Block from, float chance, BlockState to) {
        return new StructureProcessorRule(new RandomBlockMatchRuleTest(from, chance), AlwaysTrueRuleTest.INSTANCE, to);
    }

    private static void addProcessor(Registry<StructureProcessorList> registry, Identifier id, List<StructureProcessorRule> rules) {
        if (rules.isEmpty()) {
            return;
        }
        registry.getOrEmpty(id).ifPresent(processorList -> {
            List<StructureProcessor> newList = new ArrayList<>(processorList.getList());
            newList.add(new RuleStructureProcessor(rules));
            processorList.list = newList;
        });
    }
}

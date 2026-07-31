package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.item.ItemFamily;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.AlwaysTrueTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProcessorRule;
import net.minecraft.world.level.levelgen.structure.templatesystem.RandomBlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

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

            Registry<StructureProcessorList> registry = server.registryAccess().lookupOrThrow(Registries.PROCESSOR_LIST);

            BlockState bellPepper = ModBlocks.BELL_PEPPER_CROP.defaultBlockState();
            BlockState cotton = ModBlocks.COTTON_CROP.defaultBlockState();
            BlockState coffee = ModBlocks.COFFEE_CROP.defaultBlockState();

            // Temperate (plains, taiga): carrots -> bell pepper, potatoes -> cotton, plus a wheat sprinkle.
            // Wheat-rule order is swapped between the two lists so the first-match bias cancels out.
            List<ProcessorRule> plains = new ArrayList<>();
            List<ProcessorRule> taiga = new ArrayList<>();
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
            List<ProcessorRule> savanna = new ArrayList<>();
            List<ProcessorRule> desert = new ArrayList<>();
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

            addProcessor(registry, Identifier.withDefaultNamespace("farm_plains"), plains);
            addProcessor(registry, Identifier.withDefaultNamespace("farm_taiga"), taiga);
            addProcessor(registry, Identifier.withDefaultNamespace("farm_savanna"), savanna);
            addProcessor(registry, Identifier.withDefaultNamespace("farm_desert"), desert);
        });
    }

    private static ProcessorRule rule(Block from, float chance, BlockState to) {
        return new ProcessorRule(new RandomBlockMatchTest(from, chance), AlwaysTrueTest.INSTANCE, to);
    }

    private static void addProcessor(Registry<StructureProcessorList> registry, Identifier id, List<ProcessorRule> rules) {
        if (rules.isEmpty()) {
            return;
        }
        registry.getOptional(id).ifPresent(processorList -> {
            List<StructureProcessor> newList = new ArrayList<>(processorList.list());
            newList.add(new RuleProcessor(rules));
            processorList.list = newList;
        });
    }
}

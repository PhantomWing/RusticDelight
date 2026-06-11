package com.phantomwing.rusticdelight.world;

import com.phantomwing.rusticdelight.Configuration;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.AlwaysTrueTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProcessorRule;
import net.minecraft.world.level.levelgen.structure.templatesystem.RandomBlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Injects Rustic Delight crops into vanilla village farm plots, mirroring Farmer's Delight.
 * Each crop replaces a distinct vanilla crop (so they spread evenly), with a wheat sprinkle for density.
 */
@EventBusSubscriber(modid = RusticDelight.MOD_ID)
public class ModVillageStructures {

    @SubscribeEvent
    public static void addVillageCrops(ServerAboutToStartEvent event) {
        if (!Configuration.getBooleanConfigurationValue(Configuration.GENERATE_VILLAGE_FARM_CROPS_ID)) {
            return;
        }

        Registry<StructureProcessorList> registry = event.getServer().registryAccess().registryOrThrow(Registries.PROCESSOR_LIST);

        BlockState bellPepper = ModBlocks.BELL_PEPPER_CROP.get().defaultBlockState();
        BlockState cotton = ModBlocks.COTTON_CROP.get().defaultBlockState();
        BlockState coffee = ModBlocks.COFFEE_CROP.get().defaultBlockState();

        // Temperate (plains, taiga): carrots -> bell pepper, potatoes -> cotton, plus a wheat sprinkle.
        // Wheat-rule order is swapped between the two lists so the first-match bias cancels out.
        RuleProcessor plains = new RuleProcessor(List.of(
                rule(Blocks.CARROTS, 0.3F, bellPepper),
                rule(Blocks.POTATOES, 0.3F, cotton),
                rule(Blocks.WHEAT, 0.12F, bellPepper),
                rule(Blocks.WHEAT, 0.12F, cotton)
        ));
        RuleProcessor taiga = new RuleProcessor(List.of(
                rule(Blocks.CARROTS, 0.3F, bellPepper),
                rule(Blocks.POTATOES, 0.3F, cotton),
                rule(Blocks.WHEAT, 0.12F, cotton),
                rule(Blocks.WHEAT, 0.12F, bellPepper)
        ));

        // Arid (savanna, desert): also beetroots -> coffee.
        RuleProcessor savanna = new RuleProcessor(List.of(
                rule(Blocks.CARROTS, 0.3F, bellPepper),
                rule(Blocks.POTATOES, 0.3F, cotton),
                rule(Blocks.BEETROOTS, 0.3F, coffee),
                rule(Blocks.WHEAT, 0.1F, bellPepper),
                rule(Blocks.WHEAT, 0.1F, cotton),
                rule(Blocks.WHEAT, 0.1F, coffee)
        ));
        RuleProcessor desert = new RuleProcessor(List.of(
                rule(Blocks.CARROTS, 0.3F, bellPepper),
                rule(Blocks.POTATOES, 0.3F, cotton),
                rule(Blocks.BEETROOTS, 0.3F, coffee),
                rule(Blocks.WHEAT, 0.1F, coffee),
                rule(Blocks.WHEAT, 0.1F, cotton),
                rule(Blocks.WHEAT, 0.1F, bellPepper)
        ));

        addProcessor(registry, ResourceLocation.withDefaultNamespace("farm_plains"), plains);
        addProcessor(registry, ResourceLocation.withDefaultNamespace("farm_taiga"), taiga);
        addProcessor(registry, ResourceLocation.withDefaultNamespace("farm_savanna"), savanna);
        addProcessor(registry, ResourceLocation.withDefaultNamespace("farm_desert"), desert);
    }

    private static ProcessorRule rule(Block from, float chance, BlockState to) {
        return new ProcessorRule(new RandomBlockMatchTest(from, chance), AlwaysTrueTest.INSTANCE, to);
    }

    private static void addProcessor(Registry<StructureProcessorList> registry, ResourceLocation id, StructureProcessor processor) {
        registry.getOptional(id).ifPresent(processorList -> {
            List<StructureProcessor> newList = new ArrayList<>(processorList.list());
            newList.add(processor);
            processorList.list = newList;
        });
    }
}

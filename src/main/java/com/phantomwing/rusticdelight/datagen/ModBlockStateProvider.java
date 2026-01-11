package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.block.custom.*;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.Optional;

public class ModBlockStateProvider {
    public static void registerStatesAndModels(BlockModelGenerators g) {
        createClassicCrop(g, ModBlocks.COTTON_CROP, CottonCropBlock.AGE);
        createCrossCrop(g, ModBlocks.BELL_PEPPER_CROP, BellPepperCropBlock.AGE);
        createCrossCrop(g, ModBlocks.COFFEE_CROP, CoffeeCropBlock.AGE);

        makePottedFlower(g, ModBlocks.POTTED_WILD_COTTON, ModBlocks.WILD_COTTON);
        makePottedFlower(g, ModBlocks.POTTED_WILD_BELL_PEPPERS, ModBlocks.WILD_BELL_PEPPERS);
        makePottedFlower(g, ModBlocks.POTTED_WILD_COFFEE, ModBlocks.WILD_COFFEE);

        canvasBag(g, ModBlocks.COTTON_SEEDS_BAG);
        canvasBag(g, ModBlocks.BELL_PEPPER_SEEDS_BAG);
        canvasBag(g, ModBlocks.COFFEE_BEANS_BAG);
        canvasBag(g, ModBlocks.ROASTED_COFFEE_BEANS_BAG);

        farmersDelightCrate(g, ModBlocks.COTTON_BOLL_CRATE);
        farmersDelightCrate(g, ModBlocks.BELL_PEPPER_GREEN_CRATE);
        farmersDelightCrate(g, ModBlocks.BELL_PEPPER_YELLOW_CRATE);
        farmersDelightCrate(g, ModBlocks.BELL_PEPPER_RED_CRATE);

        pieBlock(g, ModBlocks.SYRUP_CHEESECAKE);
        pieBlock(g, ModBlocks.CHERRY_BLOSSOM_CHEESECAKE);

        pancakeBlock(g, ModBlocks.PANCAKES);
        pancakeBlock(g, ModBlocks.HONEY_PANCAKES);
        pancakeBlock(g, ModBlocks.CHOCOLATE_PANCAKES);
        pancakeBlock(g, ModBlocks.CHERRY_BLOSSOM_PANCAKES);
        pancakeBlock(g, ModBlocks.VEGETABLE_PANCAKES);
        pancakeBlock(g, ModBlocks.PUMPKIN_PANCAKES);

        riceRollBlock(g, ModBlocks.RICE_ROLL_ROYALE);
    }

    private static void createClassicCrop(BlockModelGenerators g, Block cropBlock, Property<Integer> ageProperty) {
        g.createCropBlock(cropBlock, ageProperty, ageProperty.getPossibleValues().stream().mapToInt(Integer::intValue).toArray());
    }

    private static void createCrossCrop(BlockModelGenerators g, Block cropBlock, Property<Integer> ageProperty) {
        int[] ageToVisualStageMapping = ageProperty.getPossibleValues().stream().mapToInt(Integer::intValue).toArray();
        Int2ObjectMap<ResourceLocation> int2ObjectMap = new Int2ObjectOpenHashMap<>();
        ModelTemplate crossModel = new ModelTemplate(Optional.of(blockResourceFD("crop_cross")), Optional.empty(), TextureSlot.CROSS);

        PropertyDispatch propertyDispatch = PropertyDispatch.property(ageProperty).generate((integer) -> {
            int i = ageToVisualStageMapping[integer];
            ResourceLocation resourceLocation = int2ObjectMap.computeIfAbsent(i, (j) -> g.createSuffixedVariant(cropBlock, "_stage" + i, crossModel, TextureMapping::cross));
            return Variant.variant().with(VariantProperties.MODEL, resourceLocation);
        });

        g.createFlatItemModel(cropBlock.asItem());
        g.blockStateOutput.accept(MultiVariantGenerator.multiVariant(cropBlock).with(propertyDispatch));
    }

    private static void makePottedFlower(BlockModelGenerators g, Block pottedBlock, Block block) {
        g.createPlant(block, pottedBlock, BlockModelGenerators.PlantType.NOT_TINTED);
    }

    private static void farmersDelightCrate(BlockModelGenerators g, Block block) {
        String blockName = blockName(block);
        TextureMapping mapping = (new TextureMapping())
                .put(TextureSlot.PARTICLE, blockResource(blockName + "_top"))
                .put(TextureSlot.SIDE, blockResource(blockName + "_side"))
                .put(TextureSlot.BOTTOM, blockResourceFD("crate_bottom"))
                .put(TextureSlot.TOP, blockResource(blockName + "_top"));

        createBlock(g, block, mapping, ModelTemplates.CUBE_BOTTOM_TOP);
    }

    private static void canvasBag(BlockModelGenerators g, Block block) {
        String blockName = blockName(block);
        TextureMapping mapping = (new TextureMapping())
                .put(TextureSlot.PARTICLE, blockResource(blockName + "_top"))
                .put(TextureSlot.DOWN, blockResource(blockName + "_bottom"))
                .put(TextureSlot.UP, blockResource(blockName + "_top"))
                .put(TextureSlot.NORTH, blockResource(blockName + "_side_tied"))
                .put(TextureSlot.SOUTH, blockResource(blockName + "_side_tied"))
                .put(TextureSlot.EAST, blockResource(blockName + "_side"))
                .put(TextureSlot.WEST, blockResource(blockName + "_side"));

        createBlock(g, block, mapping, ModelTemplates.CUBE);
    }

    private static void pieBlock(BlockModelGenerators g, Block block) {
        MultiVariantGenerator generator = MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.properties(PieBlock.FACING, PieBlock.BITES)
                        .generate((direction, bites) -> {
                            String suffix = bites == 0 ? "" : "_slice" + bites;
                            return Variant.variant()
                                    .with(VariantProperties.Y_ROT, dirToRot(direction))
                                    .with(VariantProperties.MODEL, blockResource(blockName(block) + suffix));
                        })
                );
        g.blockStateOutput.accept(generator);
    }

    private static void pancakeBlock(BlockModelGenerators g, Block block) {
        MultiVariantGenerator generator = MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.properties(PancakeBlock.FACING, PancakeBlock.SERVINGS)
                        .generate((direction, servings) -> {
                            String suffix = "_stage" + servings;
                            return Variant.variant()
                                    .with(VariantProperties.Y_ROT, dirToRot(direction))
                                    .with(VariantProperties.MODEL, blockResource(blockName(block) + suffix));
                        })
                );
        g.blockStateOutput.accept(generator);
    }

    private static void riceRollBlock(BlockModelGenerators g, Block block) {
        MultiVariantGenerator generator = MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.properties(RiceRollRoyaleBlock.FACING, RiceRollRoyaleBlock.ROLL_SERVINGS)
                        .generate((direction, servings) -> {
                            int invertedServings = RiceRollRoyaleBlock.MAX_SERVINGS - servings;
                            String suffix = invertedServings == RiceRollRoyaleBlock.MAX_SERVINGS ? "_leftover" : "_stage" + invertedServings;
                            return Variant.variant()
                                    .with(VariantProperties.Y_ROT, dirToRot(direction))
                                    .with(VariantProperties.MODEL, blockResource(blockName(block) + suffix));
                        })
                );
        g.blockStateOutput.accept(generator);
    }

    private static String blockName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    private static ResourceLocation blockResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "block/" + path);
    }

    private static ResourceLocation blockResourceFD(String path) {
        return ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block/" + path);
    }

    private static void createBlock(BlockModelGenerators g, Block block, TextureMapping textureMapping, ModelTemplate modelTemplate) {
        ResourceLocation resourceLocation = modelTemplate.create(block, textureMapping, g.modelOutput);
        MultiVariantGenerator variantGenerator = MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, resourceLocation));

        g.blockStateOutput.accept(variantGenerator);
    }

    private static VariantProperties.Rotation dirToRot(Direction direction) {
        return direction == Direction.NORTH ? VariantProperties.Rotation.R0
                : direction == Direction.EAST ? VariantProperties.Rotation.R90
                : direction == Direction.SOUTH ? VariantProperties.Rotation.R180
                : VariantProperties.Rotation.R270;
    }
}

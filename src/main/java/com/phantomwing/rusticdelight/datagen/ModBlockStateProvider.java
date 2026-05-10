package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.block.custom.*;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.Optional;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

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
        g.registerSimpleFlatItemModel(cropBlock.asItem());

        int[] ageToVisualStageMapping = ageProperty.getPossibleValues().stream().mapToInt(Integer::intValue).toArray();
        Int2ObjectMap<Identifier> int2ObjectMap = new Int2ObjectOpenHashMap<>();
        ModelTemplate crossModel = new ModelTemplate(Optional.of(blockResourceFD("template_crop_cross")), Optional.empty(), TextureSlot.CROSS);

        g.blockStateOutput.accept(MultiVariantGenerator.dispatch(cropBlock).with(PropertyDispatch.initial(ageProperty).generate((integer) -> {
            int i = ageToVisualStageMapping[integer];
            return BlockModelGenerators.plainVariant(int2ObjectMap.computeIfAbsent(i, (ix) -> g.createSuffixedVariant(cropBlock, "_stage" + ix, crossModel, TextureMapping::cross)));
        })));
    }

    private static void makePottedFlower(BlockModelGenerators g, Block pottedBlock, Block block) {
        g.createPlantWithDefaultItem(block, pottedBlock, PlantType.NOT_TINTED);
    }

    private static void farmersDelightCrate(BlockModelGenerators g, Block block) {
        String blockName = blockName(block);
        TextureMapping mapping = (new TextureMapping())
                .put(TextureSlot.PARTICLE, new Material(blockResource(blockName + "_top")))
                .put(TextureSlot.SIDE, new Material(blockResource(blockName + "_side")))
                .put(TextureSlot.BOTTOM, new Material(blockResourceFD("crate_bottom")))
                .put(TextureSlot.TOP, new Material(blockResource(blockName + "_top")));

        createBlock(g, block, mapping, ModelTemplates.CUBE_BOTTOM_TOP);
    }

    private static void canvasBag(BlockModelGenerators g, Block block) {
        String blockName = blockName(block);
        TextureMapping mapping = (new TextureMapping())
                .put(TextureSlot.PARTICLE, new Material(blockResource(blockName + "_top")))
                .put(TextureSlot.DOWN, new Material(blockResource(blockName + "_bottom")))
                .put(TextureSlot.UP, new Material(blockResource(blockName + "_top")))
                .put(TextureSlot.NORTH, new Material(blockResource(blockName + "_side_tied")))
                .put(TextureSlot.SOUTH, new Material(blockResource(blockName + "_side_tied")))
                .put(TextureSlot.EAST, new Material(blockResource(blockName + "_side")))
                .put(TextureSlot.WEST, new Material(blockResource(blockName + "_side")));

        createBlock(g, block, mapping, ModelTemplates.CUBE);
    }

    private static void pieBlock(BlockModelGenerators g, Block block) {
        MultiVariantGenerator generator = MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(PieBlock.FACING, PieBlock.BITES)
                        .generate((direction, bites) -> {
                            String suffix = bites == 0 ? "" : "_slice" + bites;

                            Identifier modelLoc = blockResource(blockName(block) + suffix);
                            MultiVariant variant = plainVariant(modelLoc);
                            VariantMutator rotation = dirToRot(direction);
                            if (rotation != null) {
                                variant = variant.with(rotation);
                            }

                            return variant;
                        })
                );
        g.blockStateOutput.accept(generator);
    }

    private static void pancakeBlock(BlockModelGenerators g, Block block) {
        MultiVariantGenerator generator = MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(PancakeBlock.FACING, PancakeBlock.SERVINGS)
                        .generate((direction, servings) -> {
                            String suffix = "_stage" + servings;

                            Identifier modelLoc = blockResource(blockName(block) + suffix);
                            MultiVariant variant = plainVariant(modelLoc);
                            VariantMutator rotation = dirToRot(direction);
                            if (rotation != null) {
                                variant = variant.with(rotation);
                            }

                            return variant;
                        })
                );
        g.blockStateOutput.accept(generator);
    }

    private static void riceRollBlock(BlockModelGenerators g, Block block) {
        MultiVariantGenerator generator = MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(RiceRollRoyaleBlock.FACING, RiceRollRoyaleBlock.ROLL_SERVINGS)
                        .generate((direction, servings) -> {
                            int invertedServings = RiceRollRoyaleBlock.MAX_SERVINGS - servings;
                            String suffix = invertedServings == RiceRollRoyaleBlock.MAX_SERVINGS ? "_leftover" : "_stage" + invertedServings;

                            Identifier modelLoc = blockResource(blockName(block) + suffix);
                            MultiVariant variant = plainVariant(modelLoc);
                            VariantMutator rotation = dirToRot(direction);
                            if (rotation != null) {
                                variant = variant.with(rotation);
                            }

                            return variant;
                        })
                );
        g.blockStateOutput.accept(generator);
    }

    private static String blockName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    private static Identifier blockResource(String path) {
        return Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, "block/" + path);
    }

    private static Identifier blockResourceFD(String path) {
        return Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "block/" + path);
    }

    private static void createBlock(BlockModelGenerators g, Block block, TextureMapping textureMapping, ModelTemplate modelTemplate) {
        Identifier Identifier = modelTemplate.create(block, textureMapping, g.modelOutput);
        MultiVariantGenerator variantGenerator = MultiVariantGenerator.dispatch(block, plainVariant(Identifier));

        g.blockStateOutput.accept(variantGenerator);
    }

    private static VariantMutator dirToRot(Direction direction) {
        return direction == Direction.NORTH ? null
                : direction == Direction.EAST ? BlockModelGenerators.Y_ROT_90
                : direction == Direction.SOUTH ? BlockModelGenerators.Y_ROT_180
                : BlockModelGenerators.Y_ROT_270;
    }
}

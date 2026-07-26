package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.block.custom.*;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
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
        createCrossCrop(g, ModBlocks.PALE_BELL_PEPPER_CROP, BellPepperCropBlock.AGE);
        createCrossCrop(g, ModBlocks.DARK_BELL_PEPPER_CROP, BellPepperCropBlock.AGE);
        createCrossCrop(g, ModBlocks.COFFEE_CROP, CoffeeCropBlock.AGE);

        makePottedFlower(g, ModBlocks.POTTED_WILD_COTTON, ModBlocks.WILD_COTTON);
        makePottedFlower(g, ModBlocks.POTTED_WILD_BELL_PEPPERS, ModBlocks.WILD_BELL_PEPPERS);
        makePottedFlower(g, ModBlocks.POTTED_WILD_PALE_BELL_PEPPERS, ModBlocks.WILD_PALE_BELL_PEPPERS);
        makePottedFlower(g, ModBlocks.POTTED_WILD_DARK_BELL_PEPPERS, ModBlocks.WILD_DARK_BELL_PEPPERS);
        makePottedFlower(g, ModBlocks.POTTED_WILD_COFFEE, ModBlocks.WILD_COFFEE);

        canvasBag(g, ModBlocks.COTTON_SEEDS_BAG);
        canvasBag(g, ModBlocks.BELL_PEPPER_SEEDS_BAG);
        canvasBag(g, ModBlocks.PALE_BELL_PEPPER_SEEDS_BAG);
        canvasBag(g, ModBlocks.DARK_BELL_PEPPER_SEEDS_BAG);
        canvasBag(g, ModBlocks.COFFEE_BEANS_BAG);
        canvasBag(g, ModBlocks.ROASTED_COFFEE_BEANS_BAG);

        farmersDelightCrate(g, ModBlocks.COTTON_BOLL_CRATE);
        farmersDelightCrate(g, ModBlocks.BELL_PEPPER_GREEN_CRATE);
        farmersDelightCrate(g, ModBlocks.BELL_PEPPER_YELLOW_CRATE);
        farmersDelightCrate(g, ModBlocks.BELL_PEPPER_RED_CRATE);
        farmersDelightCrate(g, ModBlocks.BELL_PEPPER_ORANGE_CRATE);
        farmersDelightCrate(g, ModBlocks.BELL_PEPPER_WHITE_CRATE);
        farmersDelightCrate(g, ModBlocks.BELL_PEPPER_PINK_CRATE);
        farmersDelightCrate(g, ModBlocks.BELL_PEPPER_BLUE_CRATE);
        farmersDelightCrate(g, ModBlocks.BELL_PEPPER_PURPLE_CRATE);
        farmersDelightCrate(g, ModBlocks.BELL_PEPPER_BLACK_CRATE);
        farmersDelightCrate(g, ModBlocks.CALAMARI_CRATE);

        pieBlock(g, ModBlocks.SYRUP_CHEESECAKE);
        pieBlock(g, ModBlocks.CHERRY_BLOSSOM_CHEESECAKE);
        pieBlock(g, ModBlocks.COFFEE_CHEESECAKE);

        pancakeBlock(g, ModBlocks.PANCAKES);
        pancakeBlock(g, ModBlocks.HONEY_PANCAKES);
        pancakeBlock(g, ModBlocks.CHOCOLATE_PANCAKES);
        pancakeBlock(g, ModBlocks.CHERRY_BLOSSOM_PANCAKES);
        pancakeBlock(g, ModBlocks.VEGETABLE_PANCAKES);
        pancakeBlock(g, ModBlocks.PUMPKIN_PANCAKES);
        pancakeBlock(g, ModBlocks.COFFEE_PANCAKES);

        riceRollBlock(g, ModBlocks.RICE_ROLL_ROYALE);
        medleyBlock(g, ModBlocks.BELL_PEPPER_MEDLEY);
        medleyBlock(g, ModBlocks.PALE_BELL_PEPPER_MEDLEY);
        medleyBlock(g, ModBlocks.DARK_BELL_PEPPER_MEDLEY);

        // Giant bell pepper blocks - plain cubes with a matching top texture.
        for (Block giant : new Block[]{ ModBlocks.BELL_PEPPER_GREEN_BLOCK, ModBlocks.BELL_PEPPER_YELLOW_BLOCK,
                ModBlocks.BELL_PEPPER_RED_BLOCK, ModBlocks.BELL_PEPPER_ORANGE_BLOCK, ModBlocks.BELL_PEPPER_WHITE_BLOCK,
                ModBlocks.BELL_PEPPER_PINK_BLOCK, ModBlocks.BELL_PEPPER_BLUE_BLOCK, ModBlocks.BELL_PEPPER_PURPLE_BLOCK,
                ModBlocks.BELL_PEPPER_BLACK_BLOCK }) {
            bellPepperBlock(g, giant);
        }
    }

    private static void createClassicCrop(BlockModelGenerators g, Block cropBlock, Property<Integer> ageProperty) {
        g.createCropBlock(cropBlock, ageProperty, ageProperty.getPossibleValues().stream().mapToInt(Integer::intValue).toArray());
    }

    private static void createCrossCrop(BlockModelGenerators g, Block cropBlock, Property<Integer> ageProperty) {
        int[] ageToVisualStageMapping = ageProperty.getPossibleValues().stream().mapToInt(Integer::intValue).toArray();
        Int2ObjectMap<ResourceLocation> int2ObjectMap = new Int2ObjectOpenHashMap<>();
        ModelTemplate crossModel = new ModelTemplate(Optional.of(farmersDelightResourceBlock("template_crop_cross")), Optional.empty(), TextureSlot.CROSS);

        PropertyDispatch propertyDispatch = PropertyDispatch.property(ageProperty).generate((integer) -> {
            int i = ageToVisualStageMapping[integer];
            ResourceLocation resourceLocation = int2ObjectMap.computeIfAbsent(i, (j) -> g.createSuffixedVariant(cropBlock, "_stage" + i, crossModel, TextureMapping::cross));
            return Variant.variant().with(VariantProperties.MODEL, resourceLocation);
        });

        g.createSimpleFlatItemModel(cropBlock.asItem());
        g.blockStateOutput.accept(MultiVariantGenerator.multiVariant(cropBlock).with(propertyDispatch));
    }

    private static void makePottedFlower(BlockModelGenerators g, Block pottedBlock, Block block) {
        g.createPlant(block, pottedBlock, BlockModelGenerators.TintState.NOT_TINTED);
    }

    private static void farmersDelightCrate(BlockModelGenerators g, Block block) {
        String blockName = blockName(block);
        TextureMapping mapping = (new TextureMapping())
                .put(TextureSlot.PARTICLE, resourceBlock(blockName + "_top"))
                .put(TextureSlot.SIDE, resourceBlock(blockName + "_side"))
                .put(TextureSlot.BOTTOM, farmersDelightResourceBlock("crate_bottom"))
                .put(TextureSlot.TOP, resourceBlock(blockName + "_top"));

        g.createTrivialBlock(block, mapping, ModelTemplates.CUBE_BOTTOM_TOP);
    }

    private static void canvasBag(BlockModelGenerators g, Block block) {
        String blockName = blockName(block);
        TextureMapping mapping = (new TextureMapping())
                .put(TextureSlot.PARTICLE, resourceBlock(blockName + "_top"))
                .put(TextureSlot.DOWN, resourceBlock(blockName + "_bottom"))
                .put(TextureSlot.UP, resourceBlock(blockName + "_top"))
                .put(TextureSlot.NORTH, resourceBlock(blockName + "_side_tied"))
                .put(TextureSlot.SOUTH, resourceBlock(blockName + "_side_tied"))
                .put(TextureSlot.EAST, resourceBlock(blockName + "_side"))
                .put(TextureSlot.WEST, resourceBlock(blockName + "_side"));

        g.createTrivialBlock(block, mapping, ModelTemplates.CUBE);
    }

    private static void pieBlock(BlockModelGenerators g, Block block) {
        MultiVariantGenerator generator = MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.properties(PieBlock.FACING, PieBlock.BITES)
                        .generate((direction, bites) -> {
                            String suffix = bites == 0 ? "" : "_slice" + bites;
                            return Variant.variant()
                                    .with(VariantProperties.Y_ROT, dirToRot(direction))
                                    .with(VariantProperties.MODEL, resourceBlock(blockName(block) + suffix));
                        })
                );
        g.blockStateOutput.accept(generator);
    }

    private static void pancakeBlock(BlockModelGenerators g, Block block) {
        MultiVariantGenerator generator = MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.properties(PancakeBlock.FACING, PancakeBlock.SERVINGS)
                        .generate((direction, servings) -> {
                            String suffix = "_stack_" + PancakeBlock.pancakesPresentFor(servings);
                            return Variant.variant()
                                    .with(VariantProperties.Y_ROT, dirToRot(direction))
                                    .with(VariantProperties.MODEL, resourceBlock(blockName(block) + suffix));
                        })
                );
        g.blockStateOutput.accept(generator);
    }

    private static void riceRollBlock(BlockModelGenerators g, Block block) {
        MultiVariantGenerator generator = MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.properties(RiceRollRoyaleBlock.FACING, RiceRollRoyaleBlock.ROLL_SERVINGS)
                        .generate((direction, servings) -> {
                            int invertedServings = RiceRollRoyaleBlock.MAX_SERVINGS - servings;
                            String suffix = invertedServings == RiceRollRoyaleBlock.MAX_SERVINGS ? "_leftovers" : "_stage" + invertedServings;
                            return Variant.variant()
                                    .with(VariantProperties.Y_ROT, dirToRot(direction))
                                    .with(VariantProperties.MODEL, resourceBlock(blockName(block) + suffix));
                        })
                );
        g.blockStateOutput.accept(generator);
    }

    private static void medleyBlock(BlockModelGenerators g, Block block) {
        MultiVariantGenerator generator = MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.properties(BellPepperMedleyBlock.FACING, BellPepperMedleyBlock.MEDLEY_SERVINGS)
                        .generate((direction, servings) -> {
                            int remaining = BellPepperMedleyBlock.MAX_SERVINGS - servings;
                            String suffix = remaining == BellPepperMedleyBlock.MAX_SERVINGS ? "_leftovers" : "_stage" + remaining;
                            return Variant.variant()
                                    .with(VariantProperties.Y_ROT, dirToRot(direction))
                                    .with(VariantProperties.MODEL, resourceBlock(blockName(block) + suffix));
                        })
                );
        g.blockStateOutput.accept(generator);
    }

    private static void bellPepperBlock(BlockModelGenerators g, Block block) {
        String blockName = blockName(block);
        String tex = blockName.replace("_block", "");
        TextureMapping mapping = (new TextureMapping())
                .put(TextureSlot.PARTICLE, resourceBlock(tex + "_top"))
                .put(TextureSlot.SIDE, resourceBlock(tex + "_side"))
                .put(TextureSlot.BOTTOM, resourceBlock(tex + "_bottom"))
                .put(TextureSlot.TOP, resourceBlock(tex + "_top"));

        g.createTrivialBlock(block, mapping, ModelTemplates.CUBE_BOTTOM_TOP);
    }

    private static String blockName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    private static ResourceLocation resourceBlock(String path) {
        return ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "block/" + path);
    }

    private static ResourceLocation farmersDelightResourceBlock(String path) {
        return ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block/" + path);
    }

    private static VariantProperties.Rotation dirToRot(Direction direction) {
        return direction == Direction.NORTH ? VariantProperties.Rotation.R0
                : direction == Direction.EAST ? VariantProperties.Rotation.R90
                : direction == Direction.SOUTH ? VariantProperties.Rotation.R180
                : VariantProperties.Rotation.R270;
    }
}

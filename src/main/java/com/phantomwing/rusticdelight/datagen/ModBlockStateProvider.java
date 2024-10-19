package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.block.custom.BellPepperCropBlock;
import com.phantomwing.rusticdelight.block.custom.CoffeeCropBlock;
import com.phantomwing.rusticdelight.block.custom.CottonCropBlock;
import com.phantomwing.rusticdelight.block.custom.PancakeBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    private static final int DEFAULT_ANGLE_OFFSET = 180;

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RusticDelight.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        makeCottonCrop((CropBlock) ModBlocks.COTTON_CROP.get(), "cotton_stage", "cotton_stage");
        makeBellPepperCrop((CropBlock) ModBlocks.BELL_PEPPER_CROP.get(), "bell_peppers_stage", "bell_peppers_stage");
        makeCoffeeCrop((CropBlock) ModBlocks.COFFEE_CROP.get(), "coffee_stage", "coffee_stage");

        makeWildCrop(ModBlocks.WILD_COTTON.get());
        makeWildCrop(ModBlocks.WILD_BELL_PEPPERS.get());
        makeWildCrop(ModBlocks.WILD_COFFEE.get());

        makePottedFlower(ModBlocks.POTTED_WILD_COTTON.get(),ModBlocks.WILD_COTTON.get());
        makePottedFlower(ModBlocks.POTTED_WILD_BELL_PEPPERS.get(), ModBlocks.WILD_BELL_PEPPERS.get());
        makePottedFlower(ModBlocks.POTTED_WILD_COFFEE.get(), ModBlocks.WILD_COFFEE.get());

        farmersDelightBag(ModBlocks.COTTON_SEEDS_BAG.get());
        farmersDelightBag(ModBlocks.BELL_PEPPER_SEEDS_BAG.get());
        farmersDelightBag(ModBlocks.COFFEE_BEANS_BAG.get());

        farmersDelightCrate(ModBlocks.COTTON_BOLL_CRATE.get());
        farmersDelightCrate(ModBlocks.BELL_PEPPER_GREEN_CRATE.get());
        farmersDelightCrate(ModBlocks.BELL_PEPPER_YELLOW_CRATE.get());
        farmersDelightCrate(ModBlocks.BELL_PEPPER_RED_CRATE.get());

        pieBlock(ModBlocks.CHERRY_BLOSSOM_CHEESECAKE.get());

        pancakeBlock(ModBlocks.HONEY_PANCAKES.get());
        pancakeBlock(ModBlocks.CHOCOLATE_PANCAKES.get());
        pancakeBlock(ModBlocks.CHERRY_BLOSSOM_PANCAKES.get());
        pancakeBlock(ModBlocks.VEGETABLE_PANCAKES.get());
    }

    private void makeWildCrop(Block block) {
        simpleBlockWithItem(block, models().cross(blockTexture(block).getPath(),
                blockTexture(block)).renderType("cutout"));
    }

    private void makePottedFlower(Block pottedBlock, Block block) {
        simpleBlockWithItem(pottedBlock, models()
                .singleTexture(BuiltInRegistries.BLOCK.getKey(pottedBlock).getPath(),
                        ResourceLocation.withDefaultNamespace("flower_pot_cross"),
                        "plant",
                        blockTexture(block)).renderType("cutout"));
    }

    private void makeCottonCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> cottonStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] cottonStates(BlockState state, CropBlock block, String modelName, String textureName) {
        var ageProperty = state.getValue(((CottonCropBlock) block).getAgeProperty());
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + ageProperty,
                ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "block/" + textureName + ageProperty)).renderType("cutout"));

        return models;
    }

    private void makeBellPepperCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> bellPepperStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] bellPepperStates(BlockState state, CropBlock block, String modelName, String textureName) {
        var ageProperty = state.getValue(((BellPepperCropBlock) block).getAgeProperty());
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + ageProperty,
                ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "block/" + textureName + ageProperty)).renderType("cutout"));

        return models;
    }

    private void makeCoffeeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> coffeeStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] coffeeStates(BlockState state, CropBlock block, String modelName, String textureName) {
        var ageProperty = state.getValue(((CoffeeCropBlock) block).getAgeProperty());
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + ageProperty,
                ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "block/" + textureName + ageProperty)).renderType("cutout"));

        return models;
    }

    private void farmersDelightCrate(Block block) {
        String blockName = blockName(block);
        this.simpleBlock(block,
                models().cubeBottomTop(blockName, resourceBlock(blockName + "_side"), farmersDelightResourceBlock("crate_bottom"), resourceBlock(blockName + "_top")));
    }

    private void farmersDelightBag(Block block) {
        String blockName = blockName(block);
        String riceBag = BuiltInRegistries.BLOCK.getKey(vectorwing.farmersdelight.common.registry.ModBlocks.RICE_BAG.get()).getPath();
        this.simpleBlock(block, models().withExistingParent(blockName, "cube")
                .texture("particle", resourceBlock(blockName + "_top"))
                .texture("down", farmersDelightResourceBlock(riceBag + "_bottom"))
                .texture("up", resourceBlock(blockName + "_top"))
                .texture("north", farmersDelightResourceBlock(riceBag + "_side_tied"))
                .texture("south", farmersDelightResourceBlock(riceBag + "_side_tied"))
                .texture("east", farmersDelightResourceBlock(riceBag + "_side"))
                .texture("west", farmersDelightResourceBlock(riceBag + "_side"))
        );
    }

    private void pieBlock(Block block) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                            int bites = state.getValue(PieBlock.BITES);
                            String suffix = bites == 0 ? "" : "_slice" + bites;
                            return ConfiguredModel.builder()
                                    .modelFile(existingModel(blockName(block) + suffix))
                                    .rotationY(((int) state.getValue(PieBlock.FACING).toYRot() + DEFAULT_ANGLE_OFFSET) % 360)
                                    .build();
                        }
                );
    }

    private void pancakeBlock(Block block) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                            int servings = state.getValue(PancakeBlock.SERVINGS);
                            String suffix = "_stage" + servings;
                            return ConfiguredModel.builder()
                                    .modelFile(existingModel(blockName(block) + suffix))
                                    .rotationY(((int) state.getValue(PancakeBlock.FACING).toYRot() + DEFAULT_ANGLE_OFFSET) % 360)
                                    .build();
                        }
                );
    }

    private String blockName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public ResourceLocation resourceBlock(String path) {
        return ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "block/" + path);
    }

    public ModelFile existingModel(String path) {
        return new ModelFile.ExistingModelFile(resourceBlock(path), models().existingFileHelper);
    }

    public ResourceLocation farmersDelightResourceBlock(String path) {
        return ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block/" + path);
    }
}

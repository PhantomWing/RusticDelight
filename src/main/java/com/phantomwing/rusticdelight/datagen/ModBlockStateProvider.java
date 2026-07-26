package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.block.custom.*;
import com.phantomwing.rusticdelight.tags.CommonTags;
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
        makeBellPepperCrop((CropBlock) ModBlocks.PALE_BELL_PEPPER_CROP.get(), "pale_bell_peppers_stage", "pale_bell_peppers_stage");
        makeBellPepperCrop((CropBlock) ModBlocks.DARK_BELL_PEPPER_CROP.get(), "dark_bell_peppers_stage", "dark_bell_peppers_stage");
        makeCoffeeCrop((CropBlock) ModBlocks.COFFEE_CROP.get(), "coffee_stage", "coffee_stage");

        makeWildCrop(ModBlocks.WILD_COTTON.get());
        makeWildCrop(ModBlocks.WILD_BELL_PEPPERS.get());
        makeWildCrop(ModBlocks.WILD_PALE_BELL_PEPPERS.get());
        makeWildCrop(ModBlocks.WILD_DARK_BELL_PEPPERS.get());
        makeWildCrop(ModBlocks.WILD_COFFEE.get());

        makePottedFlower(ModBlocks.POTTED_WILD_COTTON.get(),ModBlocks.WILD_COTTON.get());
        makePottedFlower(ModBlocks.POTTED_WILD_BELL_PEPPERS.get(), ModBlocks.WILD_BELL_PEPPERS.get());
        makePottedFlower(ModBlocks.POTTED_WILD_PALE_BELL_PEPPERS.get(), ModBlocks.WILD_PALE_BELL_PEPPERS.get());
        makePottedFlower(ModBlocks.POTTED_WILD_DARK_BELL_PEPPERS.get(), ModBlocks.WILD_DARK_BELL_PEPPERS.get());
        makePottedFlower(ModBlocks.POTTED_WILD_COFFEE.get(), ModBlocks.WILD_COFFEE.get());

        canvasBag(ModBlocks.COTTON_SEEDS_BAG.get());
        canvasBag(ModBlocks.BELL_PEPPER_SEEDS_BAG.get());
        canvasBag(ModBlocks.PALE_BELL_PEPPER_SEEDS_BAG.get());
        canvasBag(ModBlocks.DARK_BELL_PEPPER_SEEDS_BAG.get());
        canvasBag(ModBlocks.COFFEE_BEANS_BAG.get());
        canvasBag(ModBlocks.ROASTED_COFFEE_BEANS_BAG.get());

        farmersDelightCrate(ModBlocks.COTTON_BOLL_CRATE.get());
        farmersDelightCrate(ModBlocks.BELL_PEPPER_GREEN_CRATE.get());
        farmersDelightCrate(ModBlocks.BELL_PEPPER_YELLOW_CRATE.get());
        farmersDelightCrate(ModBlocks.BELL_PEPPER_RED_CRATE.get());
        farmersDelightCrate(ModBlocks.BELL_PEPPER_ORANGE_CRATE.get());
        farmersDelightCrate(ModBlocks.BELL_PEPPER_WHITE_CRATE.get());
        farmersDelightCrate(ModBlocks.BELL_PEPPER_PINK_CRATE.get());
        farmersDelightCrate(ModBlocks.BELL_PEPPER_BLUE_CRATE.get());
        farmersDelightCrate(ModBlocks.BELL_PEPPER_PURPLE_CRATE.get());
        farmersDelightCrate(ModBlocks.BELL_PEPPER_BLACK_CRATE.get());
        farmersDelightCrate(ModBlocks.CALAMARI_CRATE.get());

        bellPepperBlock(ModBlocks.BELL_PEPPER_GREEN_BLOCK.get());
        bellPepperBlock(ModBlocks.BELL_PEPPER_YELLOW_BLOCK.get());
        bellPepperBlock(ModBlocks.BELL_PEPPER_RED_BLOCK.get());
        bellPepperBlock(ModBlocks.BELL_PEPPER_ORANGE_BLOCK.get());
        bellPepperBlock(ModBlocks.BELL_PEPPER_WHITE_BLOCK.get());
        bellPepperBlock(ModBlocks.BELL_PEPPER_PINK_BLOCK.get());
        bellPepperBlock(ModBlocks.BELL_PEPPER_BLUE_BLOCK.get());
        bellPepperBlock(ModBlocks.BELL_PEPPER_PURPLE_BLOCK.get());
        bellPepperBlock(ModBlocks.BELL_PEPPER_BLACK_BLOCK.get());

        pieBlock(ModBlocks.SYRUP_CHEESECAKE.get());
        pieBlock(ModBlocks.CHERRY_BLOSSOM_CHEESECAKE.get());
        pieBlock(ModBlocks.COFFEE_CHEESECAKE.get());

        pancakeBlock(ModBlocks.PANCAKES.get());
        pancakeBlock(ModBlocks.HONEY_PANCAKES.get());
        pancakeBlock(ModBlocks.CHOCOLATE_PANCAKES.get());
        pancakeBlock(ModBlocks.CHERRY_BLOSSOM_PANCAKES.get());
        pancakeBlock(ModBlocks.VEGETABLE_PANCAKES.get());
        pancakeBlock(ModBlocks.PUMPKIN_PANCAKES.get());
        pancakeBlock(ModBlocks.COFFEE_PANCAKES.get());

        riceRollBlock(ModBlocks.RICE_ROLL_ROYALE.get());

        medleyBlock(ModBlocks.BELL_PEPPER_MEDLEY.get());
        medleyBlock(ModBlocks.PALE_BELL_PEPPER_MEDLEY.get());
        medleyBlock(ModBlocks.DARK_BELL_PEPPER_MEDLEY.get());
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
        models[0] = new ConfiguredModel(models().singleTexture(modelName + ageProperty, farmersDelightResourceBlock("template_crop_cross"), "cross",
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
        models[0] = new ConfiguredModel(models().singleTexture(modelName + ageProperty, farmersDelightResourceBlock("template_crop_cross"), "cross",
                ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "block/" + textureName + ageProperty)).renderType("cutout"));

        return models;
    }

    private void farmersDelightCrate(Block block) {
        String blockName = blockName(block);
        this.simpleBlock(block,
                models().cubeBottomTop(blockName, resourceBlock(blockName + "_side"), farmersDelightResourceBlock("crate_bottom"), resourceBlock(blockName + "_top")));
    }

    // Solid pepper block: textures are bell_pepper_<color>_{side,bottom,top}, so strip the "_block" suffix.
    private void bellPepperBlock(Block block) {
        String blockName = blockName(block);
        String tex = blockName.replace("_block", "");
        this.simpleBlock(block,
                models().cubeBottomTop(blockName, resourceBlock(tex + "_side"), resourceBlock(tex + "_bottom"), resourceBlock(tex + "_top")));
    }

    private void canvasBag(Block block) {
        String blockName = blockName(block);
        this.simpleBlock(block, models().withExistingParent(blockName, "cube")
                .texture("particle", resourceBlock(blockName + "_top"))
                .texture("down", resourceBlock(blockName + "_bottom"))
                .texture("up", resourceBlock(blockName + "_top"))
                .texture("north", resourceBlock(blockName + "_side_tied"))
                .texture("south", resourceBlock(blockName + "_side_tied"))
                .texture("east", resourceBlock(blockName + "_side"))
                .texture("west", resourceBlock(blockName + "_side"))
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
                            // Models are named for the number of pancakes they show, so the mapping
                            // needs no translation from the packed servings value.
                            String suffix = "_stack_" + PancakeBlock.getPancakesPresent(state);
                            return ConfiguredModel.builder()
                                    .modelFile(existingModel(blockName(block) + suffix))
                                    .rotationY(((int) state.getValue(PancakeBlock.FACING).toYRot() + DEFAULT_ANGLE_OFFSET) % 360)
                                    .build();
                        }
                );
    }

    private void riceRollBlock(Block block) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                            int servings = RiceRollRoyaleBlock.MAX_SERVINGS - state.getValue(RiceRollRoyaleBlock.ROLL_SERVINGS);
                            String suffix = servings == RiceRollRoyaleBlock.MAX_SERVINGS ? "_leftovers" : "_stage" + servings;
                            return ConfiguredModel.builder()
                                    .modelFile(existingModel(blockName(block) + suffix))
                                    .rotationY(((int) state.getValue(RiceRollRoyaleBlock.FACING).toYRot() + DEFAULT_ANGLE_OFFSET) % 360)
                                    .build();
                        }
                );
    }

    private void medleyBlock(Block block) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                            int servings = BellPepperMedleyBlock.MAX_SERVINGS - state.getValue(BellPepperMedleyBlock.MEDLEY_SERVINGS);
                            String suffix = servings == BellPepperMedleyBlock.MAX_SERVINGS ? "_leftovers" : "_stage" + servings;
                            return ConfiguredModel.builder()
                                    .modelFile(existingModel(blockName(block) + suffix))
                                    .rotationY(((int) state.getValue(BellPepperMedleyBlock.FACING).toYRot() + DEFAULT_ANGLE_OFFSET) % 360)
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

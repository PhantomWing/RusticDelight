package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.BlockManager;
import com.phantomwing.rusticdelight.block.custom.CottonCropBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModBlocks;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RusticDelight.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        makeCottonCrop((CropBlock) BlockManager.COTTON_CROP.get(), "cotton_stage", "cotton_stage");
        simpleBlockWithItem(BlockManager.WILD_COTTON.get(), models().cross(blockTexture(BlockManager.WILD_COTTON.get()).getPath(),
                blockTexture(BlockManager.WILD_COTTON.get())).renderType("cutout"));
        simpleBlockWithItem(BlockManager.POTTED_WILD_COTTON.get(), models()
                .singleTexture("potted_wild_cotton",
                        ResourceLocation.withDefaultNamespace("flower_pot_cross"),
                        "plant",
                        blockTexture(BlockManager.WILD_COTTON.get())).renderType("cutout"));
        farmersDelightCrate(BlockManager.COTTON_BOLL_CRATE.get());
        farmersDelightBag(BlockManager.COTTON_SEEDS_BAG.get());
    }

    public void makeCottonCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> cottonStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] cottonStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((CottonCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "block/" + textureName + state.getValue(((CottonCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void farmersDelightCrate(Block block) {
        String blockName = blockName(block);
        this.simpleBlock(block,
                models().cubeBottomTop(blockName, resourceBlock(blockName + "_side"), farmersDelightResourceBlock("crate_bottom"), resourceBlock(blockName + "_top")));
    }

    private void farmersDelightBag(Block block) {
        String blockName = blockName(block);
        String riceBag = BuiltInRegistries.BLOCK.getKey(ModBlocks.RICE_BAG.get()).getPath();
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

    private String blockName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public ResourceLocation resourceBlock(String path) {
        return ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "block/" + path);
    }
    public ResourceLocation farmersDelightResourceBlock(String path) {
        return ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block/" + path);
    }
}

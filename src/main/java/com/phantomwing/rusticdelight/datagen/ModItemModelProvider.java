package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.BlockManager;
import com.phantomwing.rusticdelight.item.ItemManager;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RusticDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ItemManager.COTTON_SEEDS);
        simpleItem(ItemManager.COTTON_BOLL);
        simpleItem(ItemManager.COOKING_OIL);
        simpleItem(ItemManager.POTATO_SLICES);
        simpleItem(ItemManager.BAKED_POTATO_SLICES);
        simpleItem(ItemManager.CALAMARI);
        simpleItem(ItemManager.CALAMARI_SLICE);
        simpleItem(ItemManager.CALAMARI_ROLL);
        simpleItem(ItemManager.COOKED_CALAMARI);
        simpleItem(ItemManager.COOKED_CALAMARI_SLICE);
        simpleBlock2D(BlockManager.WILD_COTTON);
        simpleBlock(BlockManager.COTTON_BOLL_CRATE);
        simpleBlock(BlockManager.COTTON_SEEDS_BAG);
    }

    // A simple item with a model generated from its sprite.
    private void simpleItem(DeferredItem<Item> item) {
        withExistingParent(getItemName(item), ResourceLocation.withDefaultNamespace("item/generated"))
                .texture("layer0", getItemResourceLocation(item));
    }

    // For blocks like stairs/slabs that have multiple models, but need a single model in inventory.
    private void simpleBlock(DeferredBlock<Block> item) {
        this.withExistingParent(RusticDelight.MOD_ID + ":" + getItemName(item), getBlockResourceLocation(item));
    }

    // For blocks that appear as a block in-world but as an item in-hand
    private void simpleBlock2D(DeferredBlock<Block> item) {
        withExistingParent(getItemName(item), ResourceLocation.withDefaultNamespace("item/generated"))
                .texture("layer0", getBlockResourceLocation(item));
    }

    private String getItemName(DeferredItem<Item> item) {
        return item.getId().getPath();
    }

    private String getItemName(DeferredBlock<Block> item) {
        return item.getId().getPath();
    }

    private ResourceLocation getItemResourceLocation(DeferredItem<Item> item) {
        return ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "item/" + getItemName(item));
    }

    private ResourceLocation getBlockResourceLocation(DeferredBlock<Block> item) {
        return ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "block/" + getItemName(item));
    }
}

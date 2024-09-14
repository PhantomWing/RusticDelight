package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.item.ModItems;
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
        // Items
        simpleItem(ModItems.BAKED_POTATO_SLICES);
        simpleItem(ModItems.BATTER);
        simpleItem(ModItems.BELL_PEPPER_GREEN);
        simpleItem(ModItems.BELL_PEPPER_YELLOW);
        simpleItem(ModItems.BELL_PEPPER_RED);
        simpleItem(ModItems.CALAMARI);
        simpleItem(ModItems.CALAMARI_ROLL);
        simpleItem(ModItems.CALAMARI_SLICE);
        simpleItem(ModItems.COOKED_CALAMARI);
        simpleItem(ModItems.COOKED_CALAMARI_SLICE);
        simpleItem(ModItems.COOKING_OIL);
        simpleItem(ModItems.COTTON_BOLL);
        simpleItem(ModItems.COTTON_SEEDS);
        simpleItem(ModItems.FRIED_CALAMARI);
        simpleItem(ModItems.FRIED_CHICKEN);
        simpleItem(ModItems.FRIED_MUSHROOMS);
        simpleItem(ModItems.FRUIT_BEIGNET);
        simpleItem(ModItems.POTATO_SALAD);
        simpleItem(ModItems.POTATO_SLICES);
        simpleItem(ModItems.SPRING_ROLLS);

        // Blocks
        simpleItem(ModItems.HONEY_PANCAKES);
        simpleItem(ModItems.CHOCOLATE_PANCAKES);
        simpleBlock2D(ModBlocks.WILD_COTTON);
        simpleBlock(ModBlocks.COTTON_BOLL_CRATE);
        simpleBlock(ModBlocks.COTTON_SEEDS_BAG);
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

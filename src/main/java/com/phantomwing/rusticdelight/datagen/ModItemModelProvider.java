package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

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
        simpleItem(ModItems.BELL_PEPPER_SEEDS);
        simpleItem(ModItems.BELL_PEPPER_SOUP);
        simpleItem(ModItems.BELL_PEPPER_PASTA);
        simpleItem(ModItems.CALAMARI);
        simpleItem(ModItems.CALAMARI_ROLL);
        simpleItem(ModItems.CALAMARI_SLICE);
        simpleItem(ModItems.CHERRY_BLOSSOM_CHEESECAKE);
        simpleItem(ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE);
        simpleItem(ModItems.CHERRY_BLOSSOM_COOKIE);
        simpleItem(ModItems.CHERRY_BLOSSOM_PANCAKES);
        simpleItem(ModItems.CHERRY_BLOSSOM_ROLL);
        simpleItem(ModItems.CHOCOLATE_PANCAKES);
        simpleItem(ModItems.COOKED_CALAMARI);
        simpleItem(ModItems.COOKED_CALAMARI_SLICE);
        simpleItem(ModItems.COOKING_OIL);
        simpleItem(ModItems.COTTON_BOLL);
        simpleItem(ModItems.COTTON_SEEDS);
        simpleItem(ModItems.FRIED_CALAMARI);
        simpleItem(ModItems.FRIED_CHICKEN);
        simpleItem(ModItems.FRIED_MUSHROOMS);
        simpleItem(ModItems.FRUIT_BEIGNET);
        simpleItem(ModItems.HONEY_PANCAKES);
        simpleItem(ModItems.POTATO_SALAD);
        simpleItem(ModItems.POTATO_SLICES);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_GREEN);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_YELLOW);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_RED);
        simpleItem(ModItems.STUFFED_BELL_PEPPER_GREEN);
        simpleItem(ModItems.STUFFED_BELL_PEPPER_YELLOW);
        simpleItem(ModItems.STUFFED_BELL_PEPPER_RED);
        simpleItem(ModItems.SPRING_ROLLS);
        simpleItem(ModItems.VEGETABLE_PANCAKES);

        // Blocks
        simpleBlock2D(ModBlocks.WILD_COTTON);
        simpleBlock2D(ModBlocks.WILD_BELL_PEPPERS);
        simpleBlock(ModBlocks.COTTON_SEEDS_BAG);
        simpleBlock(ModBlocks.BELL_PEPPER_SEEDS_BAG);
        simpleBlock(ModBlocks.COTTON_BOLL_CRATE);
        simpleBlock(ModBlocks.BELL_PEPPER_GREEN_CRATE);
        simpleBlock(ModBlocks.BELL_PEPPER_YELLOW_CRATE);
        simpleBlock(ModBlocks.BELL_PEPPER_RED_CRATE);
    }

    // A simple item with a model generated from its sprite.
    private void simpleItem(RegistryObject<Item> item) {
        withExistingParent(getItemName(item), new ResourceLocation("item/generated"))
                .texture("layer0", getItemResourceLocation(item));
    }

    // For blocks like stairs/slabs that have multiple models, but need a single model in inventory.
    private void simpleBlock(RegistryObject<Block> item) {
        this.withExistingParent(RusticDelight.MOD_ID + ":" + getBlockItemName(item), getBlockResourceLocation(item));
    }

    // For blocks that appear as a block in-world but as an item in-hand
    private void simpleBlock2D(RegistryObject<Block> item) {
        withExistingParent(getBlockItemName(item), new ResourceLocation("item/generated"))
                .texture("layer0", getBlockResourceLocation(item));
    }

    private String getItemName(RegistryObject<Item> item) {
        return item.getId().getPath();
    }

    private String getBlockItemName(RegistryObject<Block> item) {
        return item.getId().getPath();
    }

    private ResourceLocation getItemResourceLocation(RegistryObject<Item> item) {
        return new ResourceLocation(RusticDelight.MOD_ID, "item/" + getItemName(item));
    }

    private ResourceLocation getBlockResourceLocation(RegistryObject<Block> item) {
        return new ResourceLocation(RusticDelight.MOD_ID, "block/" + getBlockItemName(item));
    }
}
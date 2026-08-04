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
        simpleItem(ModItems.SYRUP);
        simpleItem(ModItems.BELL_PEPPER_GREEN);
        simpleItem(ModItems.BELL_PEPPER_YELLOW);
        simpleItem(ModItems.BELL_PEPPER_RED);
        simpleItem(ModItems.BELL_PEPPER_ROLL_GREEN);
        simpleItem(ModItems.BELL_PEPPER_ROLL_YELLOW);
        simpleItem(ModItems.BELL_PEPPER_ROLL_RED);
        simpleItem(ModItems.BELL_PEPPER_SEEDS);
        simpleItem(ModItems.BELL_PEPPER_SLICE_GREEN);
        simpleItem(ModItems.BELL_PEPPER_SLICE_YELLOW);
        simpleItem(ModItems.BELL_PEPPER_SLICE_RED);
        simpleItem(ModItems.BELL_PEPPER_SOUP);
        simpleItem(ModItems.BELL_PEPPER_PASTA);
        simpleItem(ModItems.COFFEE);
        simpleItem(ModItems.CHOCOLATE_COFFEE);
        simpleItem(ModItems.CALAMARI);
        simpleItem(ModItems.CALAMARI_ROLL);
        simpleItem(ModItems.CALAMARI_SLICE);
        simpleItem(ModItems.CHERRY_BLOSSOM_CHEESECAKE);
        simpleItem(ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE);
        simpleItem(ModItems.CHERRY_BLOSSOM_COOKIE);
        simpleItem(ModItems.CHERRY_BLOSSOM_PANCAKE);
        simpleItem(ModItems.CHERRY_BLOSSOM_PANCAKES);
        simpleItem(ModItems.CHERRY_BLOSSOM_ROLL);
        simpleItem(ModItems.CHOCOLATE_PANCAKE);
        simpleItem(ModItems.CHOCOLATE_PANCAKES);
        simpleItem(ModItems.COFFEE_BEANS);
        simpleItem(ModItems.COFFEE_BRAISED_BEEF);
        simpleItem(ModItems.COOKED_CALAMARI);
        simpleItem(ModItems.COOKED_CALAMARI_SLICE);
        simpleItem(ModItems.COOKING_OIL);
        simpleItem(ModItems.COTTON_BOLL);
        simpleItem(ModItems.COTTON_SEEDS);
        simpleItem(ModItems.FRIED_CALAMARI);
        simpleItem(ModItems.FRIED_CHICKEN);
        simpleItem(ModItems.FRIED_MUSHROOMS);
        simpleItem(ModItems.FRUIT_BEIGNET);
        simpleItem(ModItems.GOLDEN_COFFEE_BEANS);
        simpleItem(ModItems.HONEY_PANCAKE);
        simpleItem(ModItems.HONEY_PANCAKES);
        simpleItem(ModItems.MILK_COFFEE);
        simpleItem(ModItems.POTATO_SALAD);
        simpleItem(ModItems.POTATO_SLICES);
        simpleItem(ModItems.PUMPKIN_PANCAKE);
        simpleItem(ModItems.PUMPKIN_PANCAKES);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_GREEN);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_YELLOW);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_RED);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_SLICE_GREEN);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_SLICE_YELLOW);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_SLICE_RED);
        simpleItem(ModItems.ROASTED_COFFEE_BEANS);
        simpleItem(ModItems.DARK_COFFEE);
        simpleItem(ModItems.HONEY_COFFEE);
        simpleItem(ModItems.STUFFED_BELL_PEPPER_GREEN);
        simpleItem(ModItems.STUFFED_BELL_PEPPER_YELLOW);
        simpleItem(ModItems.STUFFED_BELL_PEPPER_RED);
        simpleItem(ModItems.SPRING_ROLLS);
        simpleItem(ModItems.VEGETABLE_PANCAKE);
        simpleItem(ModItems.VEGETABLE_PANCAKES);
        simpleItem(ModItems.PANCAKE);
        simpleItem(ModItems.PANCAKES);
        simpleItem(ModItems.SYRUP_COFFEE);
        simpleItem(ModItems.SYRUP_COOKIE);
        simpleItem(ModItems.COFFEE_COOKIE);
        simpleItem(ModItems.SYRUP_SANDWICH);
        simpleItem(ModItems.SYRUP_CHEESECAKE);
        simpleItem(ModItems.SYRUP_CHEESECAKE_SLICE);
        simpleItem(ModItems.FRIED_DOUGH);
        simpleItem(ModItems.FRIED_DUMPLINGS);
        simpleItem(ModItems.SWEET_SALAD);
        simpleItem(ModItems.RICE_ROLL_ROYALE);
        simpleItem(ModItems.BELL_PEPPER_ORANGE);
        simpleItem(ModItems.BELL_PEPPER_WHITE);
        simpleItem(ModItems.BELL_PEPPER_PINK);
        simpleItem(ModItems.BELL_PEPPER_BLUE);
        simpleItem(ModItems.BELL_PEPPER_PURPLE);
        simpleItem(ModItems.BELL_PEPPER_BLACK);
        simpleItem(ModItems.BELL_PEPPER_ROLL_ORANGE);
        simpleItem(ModItems.BELL_PEPPER_ROLL_WHITE);
        simpleItem(ModItems.BELL_PEPPER_ROLL_PINK);
        simpleItem(ModItems.BELL_PEPPER_ROLL_BLUE);
        simpleItem(ModItems.BELL_PEPPER_ROLL_PURPLE);
        simpleItem(ModItems.BELL_PEPPER_ROLL_BLACK);
        simpleItem(ModItems.PALE_BELL_PEPPER_SEEDS);
        simpleItem(ModItems.DARK_BELL_PEPPER_SEEDS);
        simpleItem(ModItems.BELL_PEPPER_SLICE_ORANGE);
        simpleItem(ModItems.BELL_PEPPER_SLICE_WHITE);
        simpleItem(ModItems.BELL_PEPPER_SLICE_PINK);
        simpleItem(ModItems.BELL_PEPPER_SLICE_BLUE);
        simpleItem(ModItems.BELL_PEPPER_SLICE_PURPLE);
        simpleItem(ModItems.BELL_PEPPER_SLICE_BLACK);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_ORANGE);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_WHITE);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_PINK);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_BLUE);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_PURPLE);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_BLACK);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_SLICE_ORANGE);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_SLICE_WHITE);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_SLICE_PINK);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_SLICE_BLUE);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_SLICE_PURPLE);
        simpleItem(ModItems.ROASTED_BELL_PEPPER_SLICE_BLACK);
        simpleItem(ModItems.STUFFED_BELL_PEPPER_ORANGE);
        simpleItem(ModItems.STUFFED_BELL_PEPPER_WHITE);
        simpleItem(ModItems.STUFFED_BELL_PEPPER_PINK);
        simpleItem(ModItems.STUFFED_BELL_PEPPER_BLUE);
        simpleItem(ModItems.STUFFED_BELL_PEPPER_PURPLE);
        simpleItem(ModItems.STUFFED_BELL_PEPPER_BLACK);
        simpleItem(ModItems.CALAMARI_SOUP);
        simpleItem(ModItems.COFFEE_PANCAKE);
        simpleItem(ModItems.COFFEE_PANCAKES);
        simpleItem(ModItems.PUMPKIN_COFFEE);
        simpleItem(ModItems.CHERRY_BLOSSOM_COFFEE);
        simpleItem(ModItems.COFFEE_CHEESECAKE);
        simpleItem(ModItems.COFFEE_CHEESECAKE_SLICE);
        simpleItem(ModItems.FRIED_FISH);
        simpleItem(ModItems.BELL_PEPPER_MEDLEY);
        simpleItem(ModItems.PALE_BELL_PEPPER_MEDLEY);
        simpleItem(ModItems.DARK_BELL_PEPPER_MEDLEY);

        // Blocks
        simpleBlock2D(ModBlocks.WILD_COFFEE);
        simpleBlock2D(ModBlocks.WILD_COTTON);
        simpleBlock2D(ModBlocks.WILD_BELL_PEPPERS);
        simpleBlock(ModBlocks.COTTON_SEEDS_BAG);
        simpleBlock(ModBlocks.BELL_PEPPER_SEEDS_BAG);
        simpleBlock(ModBlocks.COFFEE_BEANS_BAG);
        simpleBlock(ModBlocks.ROASTED_COFFEE_BEANS_BAG);
        simpleBlock(ModBlocks.COTTON_BOLL_CRATE);
        simpleBlock(ModBlocks.BELL_PEPPER_GREEN_CRATE);
        simpleBlock(ModBlocks.BELL_PEPPER_YELLOW_CRATE);
        simpleBlock(ModBlocks.BELL_PEPPER_RED_CRATE);
        simpleBlock2D(ModBlocks.WILD_PALE_BELL_PEPPERS);
        simpleBlock2D(ModBlocks.WILD_DARK_BELL_PEPPERS);
        simpleBlock(ModBlocks.PALE_BELL_PEPPER_SEEDS_BAG);
        simpleBlock(ModBlocks.DARK_BELL_PEPPER_SEEDS_BAG);
        simpleBlock(ModBlocks.BELL_PEPPER_ORANGE_CRATE);
        simpleBlock(ModBlocks.BELL_PEPPER_WHITE_CRATE);
        simpleBlock(ModBlocks.BELL_PEPPER_PINK_CRATE);
        simpleBlock(ModBlocks.BELL_PEPPER_BLUE_CRATE);
        simpleBlock(ModBlocks.BELL_PEPPER_PURPLE_CRATE);
        simpleBlock(ModBlocks.BELL_PEPPER_BLACK_CRATE);
        simpleBlock(ModBlocks.CALAMARI_CRATE);
        simpleBlock(ModBlocks.BELL_PEPPER_GREEN_BLOCK);
        simpleBlock(ModBlocks.BELL_PEPPER_YELLOW_BLOCK);
        simpleBlock(ModBlocks.BELL_PEPPER_RED_BLOCK);
        simpleBlock(ModBlocks.BELL_PEPPER_ORANGE_BLOCK);
        simpleBlock(ModBlocks.BELL_PEPPER_WHITE_BLOCK);
        simpleBlock(ModBlocks.BELL_PEPPER_PINK_BLOCK);
        simpleBlock(ModBlocks.BELL_PEPPER_BLUE_BLOCK);
        simpleBlock(ModBlocks.BELL_PEPPER_PURPLE_BLOCK);
        simpleBlock(ModBlocks.BELL_PEPPER_BLACK_BLOCK);
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
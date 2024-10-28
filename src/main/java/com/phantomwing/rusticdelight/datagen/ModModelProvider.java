package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.block.custom.BellPepperCropBlock;
import com.phantomwing.rusticdelight.block.custom.CoffeeCropBlock;
import com.phantomwing.rusticdelight.block.custom.CottonCropBlock;
import com.phantomwing.rusticdelight.item.ModItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        // Crops
        generator.registerCrop(ModBlocks.COTTON_CROP, CottonCropBlock.AGE, 0, 1, 2, 3);
        generator.registerCrop(ModBlocks.BELL_PEPPER_CROP, BellPepperCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6 , 7);
        generator.registerCrop(ModBlocks.COFFEE_CROP, CoffeeCropBlock.AGE, 0, 1, 2, 3, 4, 5);

        // Wild crops
        generator.registerTintableCross(ModBlocks.WILD_COTTON, BlockStateModelGenerator.TintType.NOT_TINTED);
        generator.registerTintableCross(ModBlocks.WILD_BELL_PEPPERS, BlockStateModelGenerator.TintType.NOT_TINTED);
        generator.registerTintableCross(ModBlocks.WILD_COFFEE, BlockStateModelGenerator.TintType.NOT_TINTED);

        // Potted flowers
        generator.registerFlowerPotPlant(ModBlocks.WILD_COTTON, ModBlocks.POTTED_WILD_COTTON, BlockStateModelGenerator.TintType.NOT_TINTED);
        generator.registerFlowerPotPlant(ModBlocks.WILD_BELL_PEPPERS, ModBlocks.POTTED_WILD_BELL_PEPPERS, BlockStateModelGenerator.TintType.NOT_TINTED);
        generator.registerFlowerPotPlant(ModBlocks.WILD_COFFEE, ModBlocks.POTTED_WILD_COFFEE, BlockStateModelGenerator.TintType.NOT_TINTED);

        // Bags
        registerBag(generator, ModBlocks.COTTON_SEEDS_BAG);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // Items
        itemModelGenerator.register(ModItems.BAKED_POTATO_SLICES, Models.GENERATED);
        itemModelGenerator.register(ModItems.BATTER, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_GREEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_YELLOW, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_RED, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_SOUP, Models.GENERATED);
        itemModelGenerator.register(ModItems.BELL_PEPPER_PASTA, Models.GENERATED);
        itemModelGenerator.register(ModItems.COFFEE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHOCOLATE_COFFEE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CALAMARI, Models.GENERATED);
        itemModelGenerator.register(ModItems.CALAMARI_ROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.CALAMARI_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRY_BLOSSOM_CHEESECAKE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRY_BLOSSOM_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRY_BLOSSOM_PANCAKES, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRY_BLOSSOM_ROLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHOCOLATE_PANCAKES, Models.GENERATED);
        itemModelGenerator.register(ModItems.COFFEE_BEANS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COFFEE_BRAISED_BEEF, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_CALAMARI, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_CALAMARI_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKING_OIL, Models.GENERATED);
        itemModelGenerator.register(ModItems.COTTON_BOLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.COTTON_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRIED_CALAMARI, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRIED_CHICKEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRIED_MUSHROOMS, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRUIT_BEIGNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_COFFEE_BEANS, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_PANCAKES, Models.GENERATED);
        itemModelGenerator.register(ModItems.MILK_COFFEE, Models.GENERATED);
        itemModelGenerator.register(ModItems.POTATO_SALAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.POTATO_SLICES, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROASTED_BELL_PEPPER_GREEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROASTED_BELL_PEPPER_YELLOW, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROASTED_BELL_PEPPER_RED, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROASTED_COFFEE_BEANS, Models.GENERATED);
        itemModelGenerator.register(ModItems.DARK_COFFEE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_COFFEE, Models.GENERATED);
        itemModelGenerator.register(ModItems.STUFFED_BELL_PEPPER_GREEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.STUFFED_BELL_PEPPER_YELLOW, Models.GENERATED);
        itemModelGenerator.register(ModItems.STUFFED_BELL_PEPPER_RED, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPRING_ROLLS, Models.GENERATED);
        itemModelGenerator.register(ModItems.VEGETABLE_PANCAKES, Models.GENERATED);

        // Blocks as Items
        itemModelGenerator.register(ModBlocks.WILD_COFFEE.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.WILD_COTTON.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.WILD_BELL_PEPPERS.asItem(), Models.GENERATED);

        // Blocks as Cube
        itemModelGenerator.register(ModBlocks.COTTON_SEEDS_BAG.asItem(), Models.CUBE);
        itemModelGenerator.register(ModBlocks.BELL_PEPPER_SEEDS_BAG.asItem(), Models.CUBE);
        itemModelGenerator.register(ModBlocks.COFFEE_BEANS_BAG.asItem(), Models.CUBE);
        itemModelGenerator.register(ModBlocks.COTTON_BOLL_CRATE.asItem(), Models.CUBE);
        itemModelGenerator.register(ModBlocks.BELL_PEPPER_GREEN_CRATE.asItem(), Models.CUBE);
        itemModelGenerator.register(ModBlocks.BELL_PEPPER_YELLOW_CRATE.asItem(), Models.CUBE);
        itemModelGenerator.register(ModBlocks.BELL_PEPPER_RED_CRATE.asItem(), Models.CUBE);
    }

    public final void registerCrop(BlockStateModelGenerator blockStateModelGenerator, Block crop, Property<Integer> ageProperty, int... ageTextureIndices) {
        if (ageProperty.getValues().size() != ageTextureIndices.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<Identifier> int2ObjectMap = new Int2ObjectOpenHashMap<>();
            BlockStateVariantMap blockStateVariantMap = BlockStateVariantMap.create(ageProperty).register((integer) -> {
                int i = ageTextureIndices[integer];
                Identifier identifier = int2ObjectMap.computeIfAbsent(i, (j) -> blockStateModelGenerator.createSubModel(crop, "_stage" + i, Models.CROP, TextureMap::crop));
                return BlockStateVariant.create().put(VariantSettings.MODEL, identifier);
            });
            blockStateModelGenerator.registerItemModel(crop.asItem());
            blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(crop).coordinate(blockStateVariantMap));
        }
    }

    public final void registerBag(BlockStateModelGenerator generator, Block block) {
        // Define textures.
        TextureMap textureMap = new TextureMap()
                .put(TextureKey.PARTICLE, TextureMap.getSubId(block, "_top"))
                .put(TextureKey.DOWN, TextureMap.getSubId(block, "_bottom"))
                .put(TextureKey.UP, TextureMap.getSubId(block, "_top"))
                .put(TextureKey.NORTH, TextureMap.getSubId(block, "_side_tied"))
                .put(TextureKey.SOUTH, TextureMap.getSubId(block, "_side_tied"))
                .put(TextureKey.EAST, TextureMap.getSubId(block, "_side"))
                .put(TextureKey.WEST, TextureMap.getSubId(block, "_side"));

        // Add block model.
        Identifier identifier = generator.createSubModel(block, "", Models.CUBE, (id) -> getBagTextureMap(block));

        // Add block state.
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, identifier)));
    }

    private final TextureMap getBagTextureMap(Block block) {
        return new TextureMap()
                .put(TextureKey.PARTICLE, TextureMap.getSubId(block, "_top"))
                .put(TextureKey.DOWN, TextureMap.getSubId(block, "_bottom"))
                .put(TextureKey.UP, TextureMap.getSubId(block, "_top"))
                .put(TextureKey.NORTH, TextureMap.getSubId(block, "_side_tied"))
                .put(TextureKey.SOUTH, TextureMap.getSubId(block, "_side_tied"))
                .put(TextureKey.EAST, TextureMap.getSubId(block, "_side"))
                .put(TextureKey.WEST, TextureMap.getSubId(block, "_side"));
    }
}

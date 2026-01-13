package com.phantomwing.rusticdelight;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.mojang.logging.LogUtils;
import com.phantomwing.rusticdelight.block.ModBlocks;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.loot.ModLootModifiers;
import com.phantomwing.rusticdelight.potions.ModBrewingRecipe;
import com.phantomwing.rusticdelight.potions.ModPotions;
import com.phantomwing.rusticdelight.ui.ModCreativeModeTab;
import com.phantomwing.rusticdelight.world.ModPlacementModifiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.npc.Villager;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

@Mod(RusticDelight.MOD_ID)
public class RusticDelight
{
    public static final String MOD_ID = "rusticdelight";
    private static final Logger LOGGER = LogUtils.getLogger();

    public RusticDelight()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configuration.COMMON_CONFIG);

        MinecraftForge.EVENT_BUS.register(this);

        registerManagers(modEventBus);
    }

    // Register all managers to the event bus.
    private void registerManagers(IEventBus eventBus) {
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModLootModifiers.register(eventBus);
        ModCreativeModeTab.register(eventBus);
        ModPlacementModifiers.register(eventBus);
        ModPotions.register(eventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            addFlowerPots();
            registerCompostables();
            registerItemSetAdditions();
            registerPotionRecipes();
        });
    }

    private void addFlowerPots() {
        FlowerPotBlock flowerPotBlock = ((FlowerPotBlock) Blocks.FLOWER_POT);
        flowerPotBlock.addPlant(ModBlocks.WILD_COTTON.getId(), ModBlocks.POTTED_WILD_COTTON);
        flowerPotBlock.addPlant(ModBlocks.WILD_BELL_PEPPERS.getId(), ModBlocks.POTTED_WILD_BELL_PEPPERS);
        flowerPotBlock.addPlant(ModBlocks.WILD_COFFEE.getId(), ModBlocks.POTTED_WILD_COFFEE);
    }

    private void registerCompostables() {
        // 30% chance
        ComposterBlock.COMPOSTABLES.put(ModItems.COTTON_SEEDS.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.BELL_PEPPER_SEEDS.get(), 0.3f);

        // 50% chance
        ComposterBlock.COMPOSTABLES.put(ModItems.COTTON_BOLL.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ModItems.POTATO_SLICES.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ModItems.COFFEE_BEANS.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ModItems.BELL_PEPPER_SLICE_GREEN.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ModItems.BELL_PEPPER_SLICE_YELLOW.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ModItems.BELL_PEPPER_SLICE_RED.get(), 0.5f);

        // 65% chance
        ComposterBlock.COMPOSTABLES.put(ModItems.BELL_PEPPER_GREEN.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.BELL_PEPPER_YELLOW.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.BELL_PEPPER_RED.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.WILD_COTTON.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.WILD_BELL_PEPPERS.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.WILD_COFFEE.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.ROASTED_COFFEE_BEANS.get(), 0.65f);

        // 85% chance
        ComposterBlock.COMPOSTABLES.put(ModItems.COFFEE_COOKIE.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(ModItems.SYRUP_COOKIE.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(ModItems.CHERRY_BLOSSOM_COOKIE.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(ModItems.SYRUP_CHEESECAKE_SLICE.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE.get(), 0.85f);

        // 100% chance
        ComposterBlock.COMPOSTABLES.put(ModItems.SYRUP_CHEESECAKE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ModItems.CHERRY_BLOSSOM_CHEESECAKE.get(), 1f);
    }

    public static void registerItemSetAdditions() {
        Ingredient newChickenFood = Ingredient.of(ModItems.COTTON_SEEDS.get(), ModItems.BELL_PEPPER_SEEDS.get());
        Chicken.FOOD_ITEMS = new CompoundIngredient(Arrays.asList(Chicken.FOOD_ITEMS, newChickenFood))
        {
        };

        Ingredient newPigFood = Ingredient.of(ModItems.BELL_PEPPER_GREEN.get(), ModItems.BELL_PEPPER_YELLOW.get(), ModItems.BELL_PEPPER_RED.get());
        Pig.FOOD_ITEMS = new CompoundIngredient(Arrays.asList(Pig.FOOD_ITEMS, newPigFood))
        {
        };

        Collections.addAll(Parrot.TAME_FOOD, ModItems.COTTON_SEEDS.get(), ModItems.BELL_PEPPER_SEEDS.get());

        Set<Item> newWantedItems = Sets.newHashSet(
                ModItems.BELL_PEPPER_GREEN.get(),
                ModItems.BELL_PEPPER_YELLOW.get(),
                ModItems.BELL_PEPPER_RED.get(),
                ModItems.COTTON_BOLL.get(),
                ModItems.BELL_PEPPER_SEEDS.get(),
                ModItems.COTTON_SEEDS.get(),
                ModItems.COFFEE_BEANS.get());
        newWantedItems.addAll(Villager.WANTED_ITEMS);
        Villager.WANTED_ITEMS = ImmutableSet.copyOf(newWantedItems);
    }

    public static void registerPotionRecipes() {
        // Disable custom potions entirely.
        if (!Configuration.ENABLE_POTIONS.get()) {
            return;
        }

        // Add Potion of Haste recipes. (Only if Coffee feature is enabled)
        if (Configuration.CHANCE_WILD_COFFEE.get() > 0) {
            // Use addRecipe to add brewing recipes for each potion container type (potion, splash potion, lingering potion, tipped arrow)
            BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Potions.AWKWARD, ModItems.GOLDEN_COFFEE_BEANS.get(), ModPotions.HASTE_POTION.get()));
            BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(ModPotions.HASTE_POTION.get(), Items.REDSTONE, ModPotions.LONG_HASTE_POTION.get()));
            BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(ModPotions.HASTE_POTION.get(), Items.GLOWSTONE_DUST, ModPotions.STRONG_HASTE_POTION.get()));
        }
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}

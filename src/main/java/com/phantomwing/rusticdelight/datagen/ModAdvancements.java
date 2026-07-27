package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.RusticDelightConfig;
import com.phantomwing.rusticdelight.condition.ConfigBooleanCondition;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.RecipeCraftedTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancements extends FabricAdvancementProvider {
    private static final Identifier BACKGROUND =
            Identifier.withDefaultNamespace("textures/block/dirt_path_top.png");

    // 1.21.11 resolves item predicates through a HolderGetter rather than raw items.
    private HolderGetter<Item> itemGetter;

    public ModAdvancements(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registries, Consumer<AdvancementHolder> consumer) {
        this.itemGetter = registries.lookupOrThrow(Registries.ITEM);

        // Root is deliberately ungated: every branch hangs off it, and a child whose parent was
        // conditioned away fails to load.
        AdvancementHolder root = save(consumer, Advancement.Builder.advancement()
                        .display(ModItems.WILD_COTTON, title("root"), description("root"),
                                BACKGROUND, AdvancementType.TASK, false, false, false)
                        // No predicate: fires on any inventory change, so the tab appears immediately.
                        // The empty array picks an overload - a bare hasItems() is ambiguous.
                        .addCriterion("any_item", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[0])),
                "main/root");

        cotton(consumer, root);
        bellPepper(consumer, root);
        coffee(consumer, root);

        // Batter feeds both fried foods and pancakes, so it survives while either family is on -
        // the same OR the block and its recipe use.
        ResourceCondition batterEnabled = ResourceConditions.or(
                new ConfigBooleanCondition(RusticDelightConfig.ENABLE_FRIED_FOODS_ID),
                new ConfigBooleanCondition(RusticDelightConfig.ENABLE_PANCAKES_ID));
        AdvancementHolder batter = obtainMatching(consumer, root, "batter", ModItems.BATTER,
                ItemPredicate.Builder.item().of(itemGetter, ModItems.BATTER), batterEnabled);

        // Both hang off Batter, and each repeats its parent's condition: a child whose parent was
        // conditioned away fails to load.
        obtainMatching(consumer, batter, "syrup", ModItems.SYRUP, ItemPredicate.Builder.item().of(itemGetter, ModTags.Items.SYRUP),
                new ConfigBooleanCondition(RusticDelightConfig.ENABLE_SYRUP_FOODS_ID), batterEnabled);
        obtainMatching(consumer, batter, "pancakes", ModItems.PANCAKES, ItemPredicate.Builder.item().of(itemGetter, ModTags.Items.PANCAKES),
                new ConfigBooleanCondition(RusticDelightConfig.ENABLE_PANCAKES_ID), batterEnabled);
    }

    private void cotton(Consumer<AdvancementHolder> consumer, AdvancementHolder root) {
        ConfigBooleanCondition enabled = new ConfigBooleanCondition(RusticDelightConfig.ENABLE_COTTON_ID);

        AdvancementHolder cotton = obtain(consumer, root, "cotton", ModItems.COTTON_BOLL,
                AdvancementType.TASK, enabled, ModItems.COTTON_BOLL);

        // Cooking Oil is made from Cotton Seeds but belongs to the fried foods family, so it needs
        // that toggle as well as its parent's - otherwise it hangs here unobtainable.
        save(consumer, Advancement.Builder.advancement()
                        .parent(cotton)
                        .display(ModItems.COOKING_OIL, title("cooking_oil"), description("cooking_oil"),
                                null, AdvancementType.TASK, true, true, false)
                        .addCriterion("cooking_oil", InventoryChangeTrigger.TriggerInstance.hasItems(
                                ItemPredicate.Builder.item().of(itemGetter, ModItems.COOKING_OIL))),
                "main/cooking_oil", enabled, new ConfigBooleanCondition(RusticDelightConfig.ENABLE_FRIED_FOODS_ID));

        // Keyed off the recipe rather than the item, so any old string doesn't grant it.
        save(consumer, Advancement.Builder.advancement()
                        .parent(cotton)
                        .display(net.minecraft.world.item.Items.STRING, title("string"), description("string"),
                                null, AdvancementType.TASK, true, true, false)
                        .addCriterion("string_from_cotton", RecipeCraftedTrigger.TriggerInstance.craftedItem(
                                ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, "string_from_cotton_boll")))),
                "main/string", enabled);
    }

    private void bellPepper(Consumer<AdvancementHolder> consumer, AdvancementHolder root) {
        ConfigBooleanCondition enabled = new ConfigBooleanCondition(RusticDelightConfig.ENABLE_BELL_PEPPERS_ID);

        AdvancementHolder pepper = obtain(consumer, root, "bell_pepper", ModItems.BELL_PEPPER_RED,
                AdvancementType.TASK, enabled,
                ModItems.BELL_PEPPER_GREEN, ModItems.BELL_PEPPER_YELLOW, ModItems.BELL_PEPPER_RED,
                ModItems.BELL_PEPPER_ORANGE, ModItems.BELL_PEPPER_WHITE, ModItems.BELL_PEPPER_PINK,
                ModItems.BELL_PEPPER_BLUE, ModItems.BELL_PEPPER_PURPLE, ModItems.BELL_PEPPER_BLACK);

        // Needs all nine colours, so it can't be finished without the pale and dark lines.
        obtainAll(consumer, pepper, "all_bell_peppers", ModItems.BELL_PEPPER_PURPLE,
                AdvancementType.GOAL, enabled,
                ModItems.BELL_PEPPER_GREEN, ModItems.BELL_PEPPER_YELLOW, ModItems.BELL_PEPPER_RED,
                ModItems.BELL_PEPPER_ORANGE, ModItems.BELL_PEPPER_WHITE, ModItems.BELL_PEPPER_PINK,
                ModItems.BELL_PEPPER_BLUE, ModItems.BELL_PEPPER_PURPLE, ModItems.BELL_PEPPER_BLACK);

        AdvancementHolder stuffed = obtain(consumer, pepper, "stuffed_bell_pepper", ModItems.STUFFED_BELL_PEPPER_RED,
                AdvancementType.TASK, enabled,
                ModItems.STUFFED_BELL_PEPPER_GREEN, ModItems.STUFFED_BELL_PEPPER_YELLOW, ModItems.STUFFED_BELL_PEPPER_RED,
                ModItems.STUFFED_BELL_PEPPER_ORANGE, ModItems.STUFFED_BELL_PEPPER_WHITE, ModItems.STUFFED_BELL_PEPPER_PINK,
                ModItems.STUFFED_BELL_PEPPER_BLUE, ModItems.STUFFED_BELL_PEPPER_PURPLE, ModItems.STUFFED_BELL_PEPPER_BLACK);

        obtain(consumer, stuffed, "bell_pepper_medley", ModItems.BELL_PEPPER_MEDLEY,
                AdvancementType.GOAL, enabled,
                ModItems.BELL_PEPPER_MEDLEY, ModItems.PALE_BELL_PEPPER_MEDLEY, ModItems.DARK_BELL_PEPPER_MEDLEY);

        // Hangs off the peppers rather than the stuffed ones, since the rolls are made straight from
        // Bell Peppers. It takes a roll from three families though, so it needs all three toggles -
        // which is why this one is written out instead of using the single-condition obtain() helper.
        save(consumer, Advancement.Builder.advancement()
                        .parent(pepper)
                        .display(ModItems.RICE_ROLL_ROYALE, title("rice_roll_royale"), description("rice_roll_royale"),
                                null, AdvancementType.GOAL, true, true, false)
                        .addCriterion("rice_roll_royale", InventoryChangeTrigger.TriggerInstance.hasItems(
                                ItemPredicate.Builder.item().of(itemGetter, ModItems.RICE_ROLL_ROYALE))),
                "main/rice_roll_royale", enabled,
                new ConfigBooleanCondition(RusticDelightConfig.SQUIDS_DROP_CALAMARI_ID),
                new ConfigBooleanCondition(RusticDelightConfig.ENABLE_CHERRY_BLOSSOM_FOODS_ID));
    }

    private void coffee(Consumer<AdvancementHolder> consumer, AdvancementHolder root) {
        ConfigBooleanCondition enabled = new ConfigBooleanCondition(RusticDelightConfig.ENABLE_COFFEE_ID);

        AdvancementHolder beans = obtain(consumer, root, "coffee_beans", ModItems.COFFEE_BEANS,
                AdvancementType.TASK, enabled, ModItems.COFFEE_BEANS);

        AdvancementHolder roasted = obtain(consumer, beans, "roasted_coffee_beans", ModItems.ROASTED_COFFEE_BEANS,
                AdvancementType.TASK, enabled, ModItems.ROASTED_COFFEE_BEANS);

        AdvancementHolder coffee = obtain(consumer, roasted, "coffee", ModItems.COFFEE,
                AdvancementType.TASK, enabled, ModItems.COFFEE);

        obtain(consumer, coffee, "special_coffee", ModItems.HONEY_COFFEE,
                AdvancementType.GOAL, enabled,
                ModItems.MILK_COFFEE, ModItems.CHOCOLATE_COFFEE, ModItems.HONEY_COFFEE,
                ModItems.SYRUP_COFFEE, ModItems.PUMPKIN_COFFEE, ModItems.CHERRY_BLOSSOM_COFFEE,
                ModItems.DARK_COFFEE);

        obtain(consumer, beans, "golden_coffee_beans", ModItems.GOLDEN_COFFEE_BEANS,
                AdvancementType.TASK, enabled, ModItems.GOLDEN_COFFEE_BEANS);
    }

    /**
     * Saves an advancement under {@code rusticdelight:<path>}. Any conditions passed are ANDed -
     * the advancement only loads when all of them hold.
     */
    private AdvancementHolder save(Consumer<AdvancementHolder> consumer, Advancement.Builder builder,
                                   String path, ResourceCondition... conditions) {
        AdvancementHolder holder = builder.build(Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, path));
        (conditions.length == 0 ? consumer : withConditions(consumer, conditions)).accept(holder);
        return holder;
    }

    /**
     * An advancement granted by picking up anything matching {@code match}. Pass a tag predicate so
     * datapacks and add-ons can grant it with their own items, or an item predicate for a one-off.
     */
    private AdvancementHolder obtainMatching(Consumer<AdvancementHolder> consumer, AdvancementHolder parent,
                                             String name, Item icon, ItemPredicate.Builder match,
                                             ResourceCondition... conditions) {
        return save(consumer, Advancement.Builder.advancement()
                        .parent(parent)
                        .display(icon, title(name), description(name), null, AdvancementType.TASK, true, true, false)
                        .addCriterion(name, InventoryChangeTrigger.TriggerInstance.hasItems(match)),
                "main/" + name, conditions);
    }

    /** An advancement granted by picking up any one of {@code items}. */
    private AdvancementHolder obtain(Consumer<AdvancementHolder> consumer, AdvancementHolder parent, String name,
                                     Item icon, AdvancementType type, ConfigBooleanCondition condition, ItemLike... items) {
        Advancement.Builder builder = Advancement.Builder.advancement()
                .parent(parent)
                .display(icon, title(name), description(name), null, type, true, true, false)
                // One predicate matching any of the items. Passing the items straight to hasItems()
                // would make a predicate each, and InventoryChangeTrigger requires all of them to
                // match - i.e. "hold every one at once" rather than "hold any one".
                .addCriterion(name, InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(itemGetter, items)));
        return save(consumer, builder, "main/" + name, condition);
    }

    /**
     * An advancement needing every one of {@code items}. Each gets its own criterion, and the
     * default AND strategy means all of them must be met.
     */
    private AdvancementHolder obtainAll(Consumer<AdvancementHolder> consumer, AdvancementHolder parent, String name,
                                        Item icon, AdvancementType type, ConfigBooleanCondition condition, ItemLike... items) {
        Advancement.Builder builder = Advancement.Builder.advancement()
                .parent(parent)
                .display(icon, title(name), description(name), null, type, true, true, false);
        for (ItemLike item : items) {
            String criterion = BuiltInRegistries.ITEM.getKey(item.asItem()).getPath();
            builder.addCriterion(criterion, InventoryChangeTrigger.TriggerInstance.hasItems(item));
        }
        return save(consumer, builder, "main/" + name, condition);
    }

    private static Component title(String name) {
        return Component.translatable("advancements." + RusticDelight.MOD_ID + "." + name + ".title");
    }

    private static Component description(String name) {
        return Component.translatable("advancements." + RusticDelight.MOD_ID + "." + name + ".description");
    }
}

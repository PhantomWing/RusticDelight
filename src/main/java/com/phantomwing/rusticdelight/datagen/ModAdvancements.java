package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.Configuration;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.condition.ConfigBooleanCondition;
import com.phantomwing.rusticdelight.item.ModItems;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.RecipeCraftedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.concurrent.CompletableFuture;

public class ModAdvancements extends ModAdvancementProvider {
    private static final ResourceLocation BACKGROUND =
            ResourceLocation.withDefaultNamespace("textures/block/dirt_path_top.png");

    public ModAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void generate(HolderLookup.Provider registries) {
        // Root is deliberately ungated: every branch hangs off it, and a child whose parent was
        // conditioned away fails to load.
        AdvancementHolder root = save(Advancement.Builder.advancement()
                        .display(ModItems.WILD_COTTON.get(), title("root"), description("root"),
                                BACKGROUND, AdvancementType.TASK, false, false, false)
                        // No predicate: fires on any inventory change, so the tab appears immediately.
                        // The empty array picks an overload - a bare hasItems() is ambiguous.
                        .addCriterion("any_item", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[0])),
                "main/root");

        cotton(root);
        bellPepper(root);
        coffee(root);

        ConfigBooleanCondition syrupEnabled = new ConfigBooleanCondition(Configuration.ENABLE_SYRUP_FOODS_ID);
        AdvancementHolder syrup = obtainTag(root, "syrup", ModItems.SYRUP.get(), ModTags.Items.SYRUP, syrupEnabled);
        // Carries its parent's condition as well: a child whose parent was conditioned away fails to load.
        obtainTag(syrup, "pancakes", ModItems.PANCAKES.get(), ModTags.Items.PANCAKES,
                new ConfigBooleanCondition(Configuration.ENABLE_PANCAKES_ID), syrupEnabled);
    }

    private void cotton(AdvancementHolder root) {
        ConfigBooleanCondition enabled = new ConfigBooleanCondition(Configuration.ENABLE_COTTON_ID);

        AdvancementHolder cotton = obtain(root, "cotton", ModItems.COTTON_BOLL.get(),
                AdvancementType.TASK, enabled, ModItems.COTTON_BOLL.get());

        // Cooking Oil is made from Cotton Seeds but belongs to the fried foods family, so it needs
        // that toggle as well as its parent's - otherwise it hangs here unobtainable.
        save(Advancement.Builder.advancement()
                        .parent(cotton)
                        .display(ModItems.COOKING_OIL.get(), title("cooking_oil"), description("cooking_oil"),
                                null, AdvancementType.TASK, true, true, false)
                        .addCriterion("cooking_oil", InventoryChangeTrigger.TriggerInstance.hasItems(
                                ItemPredicate.Builder.item().of(ModItems.COOKING_OIL.get()))),
                "main/cooking_oil", enabled, new ConfigBooleanCondition(Configuration.ENABLE_FRIED_FOODS_ID));

        // Keyed off the recipe rather than the item, so any old string doesn't grant it.
        save(Advancement.Builder.advancement()
                        .parent(cotton)
                        .display(net.minecraft.world.item.Items.STRING, title("string"), description("string"),
                                null, AdvancementType.TASK, true, true, false)
                        .addCriterion("string_from_cotton", RecipeCraftedTrigger.TriggerInstance.craftedItem(
                                ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, "string_from_cotton_boll"))),
                "main/string", enabled);
    }

    private void bellPepper(AdvancementHolder root) {
        ConfigBooleanCondition enabled = new ConfigBooleanCondition(Configuration.ENABLE_BELL_PEPPERS_ID);

        AdvancementHolder pepper = obtain(root, "bell_pepper", ModItems.BELL_PEPPER_RED.get(),
                AdvancementType.TASK, enabled,
                ModItems.BELL_PEPPER_GREEN.get(), ModItems.BELL_PEPPER_YELLOW.get(), ModItems.BELL_PEPPER_RED.get(),
                ModItems.BELL_PEPPER_ORANGE.get(), ModItems.BELL_PEPPER_WHITE.get(), ModItems.BELL_PEPPER_PINK.get(),
                ModItems.BELL_PEPPER_BLUE.get(), ModItems.BELL_PEPPER_PURPLE.get(), ModItems.BELL_PEPPER_BLACK.get());

        // Needs all nine colours, so it can't be finished without the pale and dark lines.
        obtainAll(pepper, "all_bell_peppers", ModItems.BELL_PEPPER_PURPLE.get(),
                AdvancementType.GOAL, enabled,
                ModItems.BELL_PEPPER_GREEN.get(), ModItems.BELL_PEPPER_YELLOW.get(), ModItems.BELL_PEPPER_RED.get(),
                ModItems.BELL_PEPPER_ORANGE.get(), ModItems.BELL_PEPPER_WHITE.get(), ModItems.BELL_PEPPER_PINK.get(),
                ModItems.BELL_PEPPER_BLUE.get(), ModItems.BELL_PEPPER_PURPLE.get(), ModItems.BELL_PEPPER_BLACK.get());

        AdvancementHolder stuffed = obtain(pepper, "stuffed_bell_pepper", ModItems.STUFFED_BELL_PEPPER_RED.get(),
                AdvancementType.TASK, enabled,
                ModItems.STUFFED_BELL_PEPPER_GREEN.get(), ModItems.STUFFED_BELL_PEPPER_YELLOW.get(), ModItems.STUFFED_BELL_PEPPER_RED.get(),
                ModItems.STUFFED_BELL_PEPPER_ORANGE.get(), ModItems.STUFFED_BELL_PEPPER_WHITE.get(), ModItems.STUFFED_BELL_PEPPER_PINK.get(),
                ModItems.STUFFED_BELL_PEPPER_BLUE.get(), ModItems.STUFFED_BELL_PEPPER_PURPLE.get(), ModItems.STUFFED_BELL_PEPPER_BLACK.get());

        obtain(stuffed, "bell_pepper_medley", ModItems.BELL_PEPPER_MEDLEY.get(),
                AdvancementType.GOAL, enabled,
                ModItems.BELL_PEPPER_MEDLEY.get(), ModItems.PALE_BELL_PEPPER_MEDLEY.get(), ModItems.DARK_BELL_PEPPER_MEDLEY.get());

        // Hangs off the peppers rather than the stuffed ones, since the rolls are made straight from
        // Bell Peppers. It takes a roll from three families though, so it needs all three toggles -
        // which is why this one is written out instead of using the single-condition obtain() helper.
        save(Advancement.Builder.advancement()
                        .parent(pepper)
                        .display(ModItems.RICE_ROLL_ROYALE.get(), title("rice_roll_royale"), description("rice_roll_royale"),
                                null, AdvancementType.GOAL, true, true, false)
                        .addCriterion("rice_roll_royale", InventoryChangeTrigger.TriggerInstance.hasItems(
                                ItemPredicate.Builder.item().of(ModItems.RICE_ROLL_ROYALE.get()))),
                "main/rice_roll_royale", enabled,
                new ConfigBooleanCondition(Configuration.SQUIDS_DROP_CALAMARI_ID),
                new ConfigBooleanCondition(Configuration.ENABLE_CHERRY_BLOSSOM_FOODS_ID));
    }

    private void coffee(AdvancementHolder root) {
        ConfigBooleanCondition enabled = new ConfigBooleanCondition(Configuration.ENABLE_COFFEE_ID);

        AdvancementHolder beans = obtain(root, "coffee_beans", ModItems.COFFEE_BEANS.get(),
                AdvancementType.TASK, enabled, ModItems.COFFEE_BEANS.get());

        AdvancementHolder roasted = obtain(beans, "roasted_coffee_beans", ModItems.ROASTED_COFFEE_BEANS.get(),
                AdvancementType.TASK, enabled, ModItems.ROASTED_COFFEE_BEANS.get());

        AdvancementHolder coffee = obtain(roasted, "coffee", ModItems.COFFEE.get(),
                AdvancementType.TASK, enabled, ModItems.COFFEE.get());

        obtain(coffee, "special_coffee", ModItems.HONEY_COFFEE.get(),
                AdvancementType.GOAL, enabled,
                ModItems.MILK_COFFEE.get(), ModItems.CHOCOLATE_COFFEE.get(), ModItems.HONEY_COFFEE.get(),
                ModItems.SYRUP_COFFEE.get(), ModItems.PUMPKIN_COFFEE.get(), ModItems.CHERRY_BLOSSOM_COFFEE.get(),
                ModItems.DARK_COFFEE.get());

        obtain(beans, "golden_coffee_beans", ModItems.GOLDEN_COFFEE_BEANS.get(),
                AdvancementType.TASK, enabled, ModItems.GOLDEN_COFFEE_BEANS.get());
    }

    /** An advancement matched on a tag, so datapacks and add-ons can grant it with their own items. */
    private AdvancementHolder obtainTag(AdvancementHolder parent, String name, Item icon, TagKey<Item> tag, ICondition... conditions) {
        return save(Advancement.Builder.advancement()
                        .parent(parent)
                        .display(icon, title(name), description(name), null, AdvancementType.TASK, true, true, false)
                        .addCriterion(name, InventoryChangeTrigger.TriggerInstance.hasItems(
                                ItemPredicate.Builder.item().of(tag))),
                "main/" + name, conditions);
    }

    /** An advancement granted by picking up any one of {@code items}. */
    private AdvancementHolder obtain(AdvancementHolder parent, String name, Item icon,
                                     AdvancementType type, ConfigBooleanCondition condition, ItemLike... items) {
        Advancement.Builder builder = Advancement.Builder.advancement()
                .parent(parent)
                .display(icon, title(name), description(name), null, type, true, true, false)
                // One predicate matching any of the items. Passing the items straight to hasItems()
                // would make a predicate each, and InventoryChangeTrigger requires all of them to
                // match - i.e. "hold every one at once" rather than "hold any one".
                .addCriterion(name, InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(items)));
        return save(builder, "main/" + name, condition);
    }

    /**
     * An advancement needing every one of {@code items}. Each gets its own criterion, and the
     * default AND strategy means all of them must be met.
     */
    private AdvancementHolder obtainAll(AdvancementHolder parent, String name, Item icon,
                                        AdvancementType type, ConfigBooleanCondition condition, ItemLike... items) {
        Advancement.Builder builder = Advancement.Builder.advancement()
                .parent(parent)
                .display(icon, title(name), description(name), null, type, true, true, false);
        for (ItemLike item : items) {
            String criterion = BuiltInRegistries.ITEM.getKey(item.asItem()).getPath();
            builder.addCriterion(criterion, InventoryChangeTrigger.TriggerInstance.hasItems(item));
        }
        return save(builder, "main/" + name, condition);
    }

    private static Component title(String name) {
        return Component.translatable("advancements." + RusticDelight.MOD_ID + "." + name + ".title");
    }

    private static Component description(String name) {
        return Component.translatable("advancements." + RusticDelight.MOD_ID + "." + name + ".description");
    }
}

package com.phantomwing.rusticdelight.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.phantomwing.rusticdelight.Configuration;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.loot.modifier.ReplaceItemModifier;

import java.util.function.Supplier;

/**
 * Farmer's Delight's chest-loot swap, plus the config gates 1.7 puts on it.
 *
 * <p>NeoForge can attach load conditions to a global loot modifier; Forge 1.20.1's
 * {@code LootModifierManager} ignores them entirely, so the toggles are checked at runtime instead.
 * That is what {@link SquidsDropCalamariModifier} already does on this branch, and it has the
 * advantage of reacting to a config change without reloading datapacks.
 */
public class ConfigurableReplaceItemModifier extends ReplaceItemModifier {
    public static final Supplier<Codec<ConfigurableReplaceItemModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("removed").forGetter(m -> m.removed))
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("added").forGetter(m -> m.added))
            .and(Codec.INT.fieldOf("count").forGetter(m -> m.count))
            .and(Codec.STRING.fieldOf("settingId").forGetter(m -> m.settingId))
            .apply(inst, ConfigurableReplaceItemModifier::new)));

    // Kept alongside the ones the superclass stores privately, so the codec has getters.
    private final Item removed;
    private final Item added;
    private final int count;
    /** The crop family toggle this swap belongs to, on top of the master loot toggle. */
    private final String settingId;

    public ConfigurableReplaceItemModifier(LootItemCondition[] conditionsIn, Item removed, Item added, int count, String settingId) {
        super(conditionsIn, removed, added, count);

        this.removed = removed;
        this.added = added;
        this.count = count;
        this.settingId = settingId;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!Configuration.GENERATE_RANDOM_LOOT.get() || !Configuration.getBooleanConfigurationValue(this.settingId)) {
            return generatedLoot;
        }

        return super.doApply(generatedLoot, context);
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}

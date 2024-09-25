package com.phantomwing.rusticdelight.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.phantomwing.rusticdelight.Configuration;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class SquidsDropCalamariModifier extends LootModifier {
    public static final MapCodec<SquidsDropCalamariModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(inst.group(
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(e -> e.item),
                    Codec.INT.fieldOf("minAmount").forGetter(e -> e.minAmount),
                    Codec.INT.fieldOf("maxAmount").forGetter(e -> e.maxAmount)
                    )
            ).apply(inst, SquidsDropCalamariModifier::new)
    );

    private final Item item;
    private final int minAmount;
    private final int maxAmount;

    public SquidsDropCalamariModifier(LootItemCondition[] conditionsIn, Item item, int minAmount, int maxAmount) {
        super(conditionsIn);

        this.item = item;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!Configuration.SQUIDS_DROP_CALAMARI.get()) {
            return generatedLoot;
        }

        for (LootItemCondition condition: this.conditions) {
            if (!condition.test(context))
            {
                return generatedLoot;
            }
        }

        int count = UniformGenerator.between(minAmount, maxAmount).getInt(context);
        if (count > 0) {
            generatedLoot.add(new ItemStack(this.item, count));
        }

        return generatedLoot;
    }

    // Return our codec here.
    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}

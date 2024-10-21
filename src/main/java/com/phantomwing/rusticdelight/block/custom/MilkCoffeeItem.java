package com.phantomwing.rusticdelight.block.custom;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.EffectCures;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.ArrayList;
import java.util.Iterator;

public class MilkCoffeeItem extends DrinkableItem
{
    public MilkCoffeeItem(Properties properties) {
        super(properties, true, true);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        Iterator<MobEffectInstance> itr = consumer.getActiveEffects().iterator();
        ArrayList<Holder<MobEffect>> compatibleEffects = new ArrayList<>();

        while (itr.hasNext()) {
            MobEffectInstance effect = itr.next();
            if (effect.getCures().contains(EffectCures.MILK)) {
                compatibleEffects.add(effect.getEffect());
            }
        }

        if (!compatibleEffects.isEmpty()) {
            MobEffectInstance selectedEffect = consumer.getEffect(compatibleEffects.get(level.random.nextInt(compatibleEffects.size())));
            if (selectedEffect != null && !net.neoforged.neoforge.event.EventHooks.onEffectRemoved(consumer, selectedEffect, EffectCures.MILK)) {
                consumer.removeEffect(selectedEffect.getEffect());
            }
        }
    }
}
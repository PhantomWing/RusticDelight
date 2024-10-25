package com.phantomwing.rusticdelight.item.custom;

import io.github.fabricators_of_create.porting_lib.entity.EffectCures;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.ArrayList;
import java.util.Iterator;

public class MilkCoffeeItem extends DrinkableItem
{
    public MilkCoffeeItem(Item.Settings settings) {
        super(settings, true, true);
    }

    @Override
    public void affectConsumer(ItemStack stack, World level, LivingEntity consumer) {
        Iterator<StatusEffectInstance> itr = consumer.getStatusEffects().iterator();
        ArrayList<RegistryEntry<StatusEffect>> compatibleEffects = new ArrayList<>();

        while (itr.hasNext()) {
            StatusEffectInstance effect = itr.next();
            if (effect.getCures().contains(EffectCures.MILK)) {
                compatibleEffects.add(effect.getEffectType());
            }
        }

        if (!compatibleEffects.isEmpty()) {
            StatusEffectInstance selectedEffect = consumer.getStatusEffect(compatibleEffects.get(level.random.nextInt(compatibleEffects.size())));
            if (selectedEffect != null) {
                consumer.removeStatusEffect(selectedEffect.getEffectType());
            }
        }
    }
}
package com.phantomwing.rusticdelight.item.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.ArrayList;
import java.util.Iterator;

public class ChocolateCoffeeItem extends DrinkableItem
{
    public ChocolateCoffeeItem(Item.Properties properties) {
        super(properties, true, true);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        Iterator<MobEffectInstance> itr = consumer.getActiveEffects().iterator();
        ArrayList<MobEffect> compatibleEffects = new ArrayList<>();

        while (itr.hasNext()) {
            MobEffectInstance effect = itr.next();
            if (effect.getEffect().getCategory().equals(MobEffectCategory.HARMFUL) && effect.isCurativeItem(new ItemStack(Items.MILK_BUCKET))) {
                compatibleEffects.add(effect.getEffect());
            }
        }

        if (!compatibleEffects.isEmpty()) {
            MobEffectInstance selectedEffect = consumer.getEffect(compatibleEffects.get(level.random.nextInt(compatibleEffects.size())));
            if (selectedEffect != null && !net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.entity.living.MobEffectEvent.Remove(consumer, selectedEffect))) {
                consumer.removeEffect(selectedEffect.getEffect());
            }
        }
    }
}
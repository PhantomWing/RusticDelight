package com.phantomwing.rusticdelight.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class CookingOilItem extends DrinkableItem {
    public static final MobEffectInstance EFFECT = new MobEffectInstance(MobEffects.CONFUSION, 200, 1);

    public CookingOilItem(Properties properties) {
        super(properties);
    }

    @Override
    public void affectConsumer(@NotNull ItemStack stack, @NotNull Level level, LivingEntity consumer) {
        consumer.addEffect(new MobEffectInstance(EFFECT));
    }
}

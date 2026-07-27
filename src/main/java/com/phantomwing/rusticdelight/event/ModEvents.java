package com.phantomwing.rusticdelight.event;

import com.phantomwing.rusticdelight.block.custom.PancakeBlock;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class ModEvents {
    public static void registerModEvents() {
        allowPuttingPancakesBack();
    }

    /**
     * Lets a sneaking player put a pancake back on the stack.
     *
     * <p>Vanilla skips a block's own interaction while the player sneaks with a full hand, which is
     * exactly the case this needs, so run {@link PancakeBlock#useItemOn} ourselves before that check.
     */
    private static void allowPuttingPancakesBack() {
        UseBlockCallback.EVENT.register((player, level, hand, hit) -> {
            if (!player.isSecondaryUseActive()) {
                return InteractionResult.PASS;
            }

            BlockState state = level.getBlockState(hit.getBlockPos());
            if (!(state.getBlock() instanceof PancakeBlock pancake)) {
                return InteractionResult.PASS;
            }

            ItemStack heldStack = player.getItemInHand(hand);
            if (!heldStack.is(pancake.servingItem.get())) {
                return InteractionResult.PASS;
            }

            return pancake.useItemOn(heldStack, state, level, hit.getBlockPos(), player, hand, hit);
        });
    }
}

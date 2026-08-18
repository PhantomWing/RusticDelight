package com.phantomwing.rusticdelight.event;

import com.phantomwing.rusticdelight.block.custom.PancakeBlock;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public class ModEvents {
    public static void registerModEvents() {
        allowPuttingPancakesBack();
    }

    /**
     * Lets a sneaking player put a pancake back on the stack.
     *
     * <p>Vanilla skips a block's own interaction while the player sneaks with a full hand, which is
     * exactly the case this needs, so run {@link PancakeBlock#onUse} ourselves before that check.
     */
    private static void allowPuttingPancakesBack() {
        UseBlockCallback.EVENT.register((player, level, hand, hit) -> {
            // This event fires before vanilla's spectator check, so spectators must be filtered here.
            if (player.isSpectator() || !player.shouldCancelInteraction()) {
                return ActionResult.PASS;
            }

            BlockState state = level.getBlockState(hit.getBlockPos());
            if (!(state.getBlock() instanceof PancakeBlock pancake)) {
                return ActionResult.PASS;
            }

            ItemStack heldStack = player.getStackInHand(hand);
            if (!heldStack.isOf(pancake.servingItem.get())) {
                return ActionResult.PASS;
            }

            return pancake.onUse(state, level, hit.getBlockPos(), player, hand, hit);
        });
    }
}

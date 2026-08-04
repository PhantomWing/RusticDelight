package com.phantomwing.rusticdelight.item;

import com.phantomwing.rusticdelight.Configuration;

/**
 * A content family whose entire chain is gated by a single config toggle.
 * Items are tagged with their family at registration; disabling the family hides
 * them from the creative tab and gates their worldgen, village-farm gen, and trades.
 * An item may belong to several families, in which case all of them must be enabled.
 */
public enum ItemFamily {
    COTTON,
    COFFEE,
    BELL_PEPPER,
    CALAMARI,
    POTATO_SLICES,
    FRIED_FOODS,
    CHERRY_BLOSSOM_FOODS,
    PANCAKES,
    SYRUP_FOODS,
    /** Batter is an ingredient for two families rather than content of its own. */
    BATTER;

    public boolean isEnabled() {
        return switch (this) {
            case COTTON -> Configuration.ENABLE_COTTON.get();
            case COFFEE -> Configuration.ENABLE_COFFEE.get();
            case BELL_PEPPER -> Configuration.ENABLE_BELL_PEPPERS.get();
            case CALAMARI -> Configuration.SQUIDS_DROP_CALAMARI.get();
            case POTATO_SLICES -> Configuration.ENABLE_POTATO_SLICES.get();
            case FRIED_FOODS -> Configuration.ENABLE_FRIED_FOODS.get();
            case CHERRY_BLOSSOM_FOODS -> Configuration.ENABLE_CHERRY_BLOSSOM_FOODS.get();
            case PANCAKES -> Configuration.ENABLE_PANCAKES.get();
            case SYRUP_FOODS -> Configuration.ENABLE_SYRUP_FOODS.get();
            // The only OR in here: Batter feeds both fried foods and pancakes, so it survives
            // as long as either one is on, and only disappears once both are off.
            case BATTER -> Configuration.ENABLE_FRIED_FOODS.get() || Configuration.ENABLE_PANCAKES.get();
        };
    }
}

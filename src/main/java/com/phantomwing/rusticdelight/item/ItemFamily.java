package com.phantomwing.rusticdelight.item;

import com.phantomwing.rusticdelight.Configuration;

/**
 * A content family whose entire chain is gated by a single config toggle.
 * Items are tagged with their family at registration; disabling the family hides
 * them from the creative tab and gates their worldgen, village-farm gen, and trades.
 */
public enum ItemFamily {
    COTTON,
    COFFEE,
    BELL_PEPPER,
    CALAMARI,
    POTATO_SLICES,
    FRIED_FOODS;

    public boolean isEnabled() {
        return switch (this) {
            case COTTON -> Configuration.ENABLE_COTTON.get();
            case COFFEE -> Configuration.ENABLE_COFFEE.get();
            case BELL_PEPPER -> Configuration.ENABLE_BELL_PEPPERS.get();
            case CALAMARI -> Configuration.SQUIDS_DROP_CALAMARI.get();
            case POTATO_SLICES -> Configuration.ENABLE_POTATO_SLICES.get();
            case FRIED_FOODS -> Configuration.ENABLE_FRIED_FOODS.get();
        };
    }
}

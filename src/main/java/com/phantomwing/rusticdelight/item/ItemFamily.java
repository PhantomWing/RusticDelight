package com.phantomwing.rusticdelight.item;

import com.phantomwing.rusticdelight.RusticDelightConfig;

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
            case COTTON -> RusticDelightConfig.get().enable_cotton;
            case COFFEE -> RusticDelightConfig.get().enable_coffee;
            case BELL_PEPPER -> RusticDelightConfig.get().enable_bell_peppers;
            case CALAMARI -> RusticDelightConfig.get().squids_drop_calamari;
            case POTATO_SLICES -> RusticDelightConfig.get().enable_potato_slices;
            case FRIED_FOODS -> RusticDelightConfig.get().enable_fried_foods;
            case CHERRY_BLOSSOM_FOODS -> RusticDelightConfig.get().enable_cherry_blossom_foods;
            case PANCAKES -> RusticDelightConfig.get().enable_pancakes;
            case SYRUP_FOODS -> RusticDelightConfig.get().enable_syrup_foods;
            // The only OR in here: Batter feeds both fried foods and pancakes, so it survives
            // as long as either one is on, and only disappears once both are off.
            case BATTER -> RusticDelightConfig.get().enable_fried_foods || RusticDelightConfig.get().enable_pancakes;
        };
    }
}

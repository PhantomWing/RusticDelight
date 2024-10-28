package com.phantomwing.rusticdelight.tag;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import vectorwing.farmersdelight.FarmersDelight;

public class CompatibilityTags {
    public static final String FORGE = "forge";
    public static final String FARMERS_DELIGHT = FarmersDelight.MODID;

    // Create
    public static final String CREATE = "create";
    public static final TagKey<Item> CREATE_UPRIGHT_ON_BELT = externalItemTag(CREATE, "upright_on_belt");

    // Serene Seasons
    public static final String SERENE_SEASONS = "sereneseasons";
    public static final TagKey<Block> SERENE_SEASONS_SPRING_CROPS_BLOCK = externalBlockTag(SERENE_SEASONS, "spring_crops");
    public static final TagKey<Block> SERENE_SEASONS_SUMMER_CROPS_BLOCK = externalBlockTag(SERENE_SEASONS, "summer_crops");
    public static final TagKey<Block> SERENE_SEASONS_AUTUMN_CROPS_BLOCK = externalBlockTag(SERENE_SEASONS, "autumn_crops");
    public static final TagKey<Block> SERENE_SEASONS_WINTER_CROPS_BLOCK = externalBlockTag(SERENE_SEASONS, "winter_crops");
    public static final TagKey<Block> SERENE_SEASONS_UNBREAKABLE_FERTILE_CROPS = externalBlockTag(SERENE_SEASONS, "unbreakable_infertile_crops");
    public static final TagKey<Item> SERENE_SEASONS_AUTUMN_CROPS = externalItemTag(SERENE_SEASONS, "autumn_crops");
    public static final TagKey<Item> SERENE_SEASONS_SPRING_CROPS = externalItemTag(SERENE_SEASONS, "spring_crops");
    public static final TagKey<Item> SERENE_SEASONS_SUMMER_CROPS = externalItemTag(SERENE_SEASONS, "summer_crops");
    public static final TagKey<Item> SERENE_SEASONS_WINTER_CROPS = externalItemTag(SERENE_SEASONS, "winter_crops");

    // Cultural Delights
    public static final String CULTURAL_DELIGHTS = "culturaldelights";

    // Miner's Delight
    public static final String MINERS_DELIGHT = "miners_delight";
    public static final TagKey<Item> MINERS_DELIGHT_TENTACLES = externalItemTag(FORGE, "tentacles");

    // Frycook's Delight
    public static final String FRYCOOKS_DELIGHT = "frycooks_delight";
    public static final TagKey<Item> HAS_FISH_SLICE = externalItemTag(FRYCOOKS_DELIGHT, "has_fish_slice");
    public static final TagKey<Item> FISH_SLICES = externalItemTag(FarmersDelight.MODID, "fish_slices");

    private static TagKey<Item> externalItemTag(String modId, String name) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(modId, name));
    }

    private static TagKey<Block> externalBlockTag(String modId, String name) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(modId, name));
    }
}

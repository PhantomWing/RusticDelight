package com.phantomwing.rusticdelight.datagen;

import com.google.gson.JsonObject;
import com.phantomwing.rusticdelight.RusticDelight;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Generates our advancements, optionally carrying a {@code conditions} array.
 *
 * <p>Vanilla's {@code AdvancementProvider} offers no hook for conditions, so we write the JSON
 * ourselves. Forge patches {@code ServerAdvancementManager} to run every advancement through
 * {@code ConditionalAdvancement.processConditional}, which drops one whose top-level conditions
 * fail, so an entry that is conditioned away simply never loads and is absent from the screen.
 */
public abstract class ModAdvancementProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;
    private final CompletableFuture<HolderLookup.Provider> registries;
    private final List<Entry> entries = new ArrayList<>();

    protected ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        this.pathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "advancements");
        this.registries = registries;
    }

    /** Build advancements here, saving each with {@link #save}. */
    protected abstract void generate(HolderLookup.Provider registries);

    /**
     * Saves an advancement under {@code rusticdelight:<path>}. Any conditions passed are ANDed -
     * the advancement only loads when all of them hold.
     */
    protected Advancement save(Advancement.Builder builder, String path, ICondition... conditions) {
        Advancement advancement = builder.build(new ResourceLocation(RusticDelight.MOD_ID, path));
        entries.add(new Entry(advancement, conditions));
        return advancement;
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput cache) {
        return registries.thenCompose(provider -> {
            entries.clear();
            generate(provider);

            Set<ResourceLocation> seen = new HashSet<>();
            List<CompletableFuture<?>> futures = new ArrayList<>();
            for (Entry entry : entries) {
                if (!seen.add(entry.advancement.getId())) {
                    throw new IllegalStateException("Duplicate advancement " + entry.advancement.getId());
                }
                JsonObject json = entry.advancement.deconstruct().serializeToJson();
                if (entry.conditions.length > 0) {
                    json.add("conditions", CraftingHelper.serialize(entry.conditions));
                }
                futures.add(DataProvider.saveStable(cache, json, pathProvider.json(entry.advancement.getId())));
            }
            return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
        });
    }

    @Override
    public @NotNull String getName() {
        return "Advancements: " + RusticDelight.MOD_ID;
    }

    private record Entry(Advancement advancement, ICondition[] conditions) {}
}

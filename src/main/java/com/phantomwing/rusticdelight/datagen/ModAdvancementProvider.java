package com.phantomwing.rusticdelight.datagen;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.phantomwing.rusticdelight.RusticDelight;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.conditions.ConditionalOps;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.WithConditions;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Generates our advancements, optionally wrapped in {@code neoforge:conditions}.
 *
 * <p>Vanilla's {@code AdvancementProvider} serializes with a plain {@code Advancement.CODEC} and
 * offers no hook for conditions — unlike recipes, where {@code RecipeOutput.withConditions(...)}
 * carries them through. So we write the JSON ourselves using NeoForge's conditional codec.
 * {@code ServerAdvancementManager} reads conditions on any advancement, so an entry whose
 * condition is false simply never loads and is absent from the advancement screen.
 */
public abstract class ModAdvancementProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;
    private final CompletableFuture<HolderLookup.Provider> registries;
    private final List<Entry> entries = new ArrayList<>();

    protected ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        this.pathProvider = output.createRegistryElementsPathProvider(Registries.ADVANCEMENT);
        this.registries = registries;
    }

    /** Build advancements here, saving each with {@link #save}. */
    protected abstract void generate(HolderLookup.Provider registries);

    /**
     * Saves an advancement under {@code rusticdelight:<path>}. Any conditions passed are ANDed —
     * the advancement only loads when all of them hold.
     */
    protected AdvancementHolder save(Advancement.Builder builder, String path, ICondition... conditions) {
        AdvancementHolder holder = builder.build(ResourceLocation.fromNamespaceAndPath(RusticDelight.MOD_ID, path));
        entries.add(new Entry(holder, List.of(conditions)));
        return holder;
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput cache) {
        return registries.thenCompose(provider -> {
            entries.clear();
            generate(provider);

            RegistryOps<JsonElement> ops = provider.createSerializationContext(JsonOps.INSTANCE);
            Codec<Optional<WithConditions<Advancement>>> codec =
                    ConditionalOps.createConditionalCodecWithConditions(Advancement.CODEC);

            Set<ResourceLocation> seen = new HashSet<>();
            List<CompletableFuture<?>> futures = new ArrayList<>();
            for (Entry entry : entries) {
                if (!seen.add(entry.holder.id())) {
                    throw new IllegalStateException("Duplicate advancement " + entry.holder.id());
                }
                JsonElement json = codec
                        .encodeStart(ops, Optional.of(new WithConditions<>(entry.conditions, entry.holder.value())))
                        .getOrThrow(IllegalStateException::new);
                futures.add(DataProvider.saveStable(cache, json, pathProvider.json(entry.holder.id())));
            }
            return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
        });
    }

    @Override
    public @NotNull String getName() {
        return "Advancements: " + RusticDelight.MOD_ID;
    }

    private record Entry(AdvancementHolder holder, List<ICondition> conditions) {}
}

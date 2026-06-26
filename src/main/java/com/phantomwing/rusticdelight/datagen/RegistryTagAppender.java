package com.phantomwing.rusticdelight.datagen;

import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

import java.util.function.Function;

/**
 * Restores value-based {@code add(...)} ergonomics on top of the 26.2 {@link TagAppender}, which only
 * accepts {@link ResourceKey}s (26.2 removed Fabric's value-lookup tag builder). Each value is converted
 * to its registry key via the supplied extractor; the tag-adding methods delegate straight through.
 *
 * @param <T> registry type the tag holds (e.g. Item, Block)
 * @param <V> value type accepted by {@link #add} (e.g. ItemLike, Block)
 */
public final class RegistryTagAppender<T, V> {
    private final TagAppender<T> appender;
    private final Function<V, ResourceKey<T>> keyExtractor;

    public RegistryTagAppender(TagAppender<T> appender, Function<V, ResourceKey<T>> keyExtractor) {
        this.appender = appender;
        this.keyExtractor = keyExtractor;
    }

    @SafeVarargs
    public final RegistryTagAppender<T, V> add(V... values) {
        for (V value : values) {
            appender.add(keyExtractor.apply(value));
        }
        return this;
    }

    public RegistryTagAppender<T, V> addTag(TagKey<T> tag) {
        appender.addTag(tag);
        return this;
    }

    public RegistryTagAppender<T, V> addOptionalTag(TagKey<T> tag) {
        appender.addOptionalTag(tag);
        return this;
    }

    public RegistryTagAppender<T, V> addOptional(ResourceKey<T> key) {
        appender.addOptional(key);
        return this;
    }
}

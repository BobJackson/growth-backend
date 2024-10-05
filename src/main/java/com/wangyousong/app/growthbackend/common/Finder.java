package com.wangyousong.app.growthbackend.common;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

public class Finder<T, R> {
    private final Collection<T> items;
    private final Function<T, R> mapping;

    public Finder(Collection<T> items, Function<T, R> mapping) {
        this.items = items;
        this.mapping = mapping;
    }

    public R findBy(R id) {
        return items.parallelStream()
                .map(mapping)
                .filter(it -> Objects.equals(it, id))
                .findAny()
                .orElseThrow();
    }
}

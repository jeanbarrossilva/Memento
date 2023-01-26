package com.jeanbarrossilva.memento.core.register.utils.list;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

public class ListUtils {
    public static <T> void replace(
        @NotNull List<T> elements,
        @NotNull Predicate<T> predicate,
        @NotNull Replacer<T> replacer
    ) {
        elements.replaceAll(element -> {
           T replacement;
           if (predicate.test(element)) {
               replacement = replacer.replace(element);
           } else {
               replacement = element;
           }
           return replacement;
        });
    }
}

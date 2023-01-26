package com.jeanbarrossilva.memento.core.register.infra;

import com.jeanbarrossilva.memento.core.register.domain.Color;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public interface Editor {
    @NotNull
    CompletableFuture<Void> setTitle(@NotNull String noteID, @NotNull String title);

    @NotNull
    CompletableFuture<Void> setBody(@NotNull String noteID, @NotNull String body);

    @NotNull
    CompletableFuture<Void> setColors(@NotNull String noteID, @NotNull Color color);
}

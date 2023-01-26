package com.jeanbarrossilva.memento.core.register.infra;

import com.jeanbarrossilva.memento.core.register.domain.Color;
import com.jeanbarrossilva.memento.core.register.domain.Note;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public abstract class Register {
    @NotNull
    public final CompletableFuture<String> register(
        @NotNull String title,
        @NotNull String body,
        @NotNull Color color
    ) {
        String id = UUID.randomUUID().toString();
        Note note = new Note(id, title, body, color);
        return onRegister(note).thenApply((result) -> id);
    }

    @NotNull
    public abstract CompletableFuture<Note> getNoteByID(@NotNull String noteID);

    @NotNull
    public abstract CompletableFuture<Void> unregister(@NotNull String noteID);

    @NotNull
    public abstract CompletableFuture<Void> unregisterAll();

    @NotNull
    protected abstract CompletableFuture<Void> onRegister(@NotNull Note note);
}

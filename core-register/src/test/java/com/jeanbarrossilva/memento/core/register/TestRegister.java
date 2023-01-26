package com.jeanbarrossilva.memento.core.register;

import com.jeanbarrossilva.memento.core.register.domain.Note;
import com.jeanbarrossilva.memento.core.register.infra.Register;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class TestRegister extends Register {
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Note> notes = new ArrayList<>();

    public TestRegister() {
    }

    @Override
    @NotNull
    public CompletableFuture<Note> getNoteByID(@NotNull String noteID) {
        return CompletableFuture.supplyAsync(() -> notes
            .stream()
            .filter(note -> note.getId().equals(noteID))
            .findFirst()
            .orElse(null));
    }

    @Override
    @NotNull
    public CompletableFuture<Void> unregister(@NotNull String noteID) {
        return CompletableFuture.runAsync(
            () -> notes.removeIf(note -> note.getId().equals(noteID))
        );
    }

    @Override
    @NotNull
    public CompletableFuture<Void> unregisterAll() {
        return CompletableFuture.runAsync(notes::clear);
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    @Override
    @NotNull
    protected CompletableFuture<Void> onRegister(@NotNull Note note) {
        return CompletableFuture.runAsync(() -> notes.add(note));
    }
}

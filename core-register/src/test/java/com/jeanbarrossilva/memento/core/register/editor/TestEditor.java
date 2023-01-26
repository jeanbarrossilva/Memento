package com.jeanbarrossilva.memento.core.register.editor;

import com.jeanbarrossilva.memento.core.register.TestRegister;
import com.jeanbarrossilva.memento.core.register.domain.Color;
import com.jeanbarrossilva.memento.core.register.infra.Editor;
import com.jeanbarrossilva.memento.core.register.utils.list.ListUtils;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

class TestEditor implements Editor {
    private final TestRegister register;

    TestEditor(TestRegister register) {
        this.register = register;
    }

    @Override
    @NotNull
    public CompletableFuture<Void> setTitle(@NotNull String noteID, @NotNull String title) {
        return CompletableFuture.runAsync(() -> ListUtils.replace(
            register.getNotes(),
            note -> note.getId().equals(noteID),
            note -> note.copy(note.getId(), title, note.getBody(), note.getColor())
        ));
    }

    @Override
    @NotNull
    public CompletableFuture<Void> setBody(@NotNull String noteID, @NotNull String body) {
        return CompletableFuture.runAsync(() -> ListUtils.replace(
            register.getNotes(),
            note -> note.getId().equals(noteID),
            note -> note.copy(note.getId(), note.getTitle(), body, note.getColor())
        ));
    }

    @Override
    @NotNull
    public CompletableFuture<Void> setColors(@NotNull String noteID, @NotNull Color color) {
        return CompletableFuture.runAsync(() -> ListUtils.replace(
            register.getNotes(),
            note -> note.getId().equals(noteID),
            note -> note.copy(note.getId(), note.getTitle(), note.getBody(), color)
        ));
    }
}

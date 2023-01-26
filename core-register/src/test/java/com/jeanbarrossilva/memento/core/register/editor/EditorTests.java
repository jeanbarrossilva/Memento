package com.jeanbarrossilva.memento.core.register.editor;

import static org.assertj.core.api.Assertions.assertThat;

import com.jeanbarrossilva.memento.core.register.TestRegister;
import com.jeanbarrossilva.memento.core.register.domain.Color;
import com.jeanbarrossilva.memento.core.register.infra.Editor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

public class EditorTests {
    @NotNull
    private final TestRegister register = new TestRegister();

    @NotNull
    private final Editor editor = new TestEditor(register);

    @Nullable
    private String noteID;

    @BeforeEach
    void setUp() throws ExecutionException, InterruptedException {
        register
            .register("Title", "Body", Color.BLUE)
            .thenAccept((noteID) -> this.noteID = noteID)
            .get();
    }

    @AfterEach
    void tearDown() throws ExecutionException, InterruptedException {
        assert noteID != null;
        register.unregister(noteID).get();
    }

    @Test
    void setTitle() throws ExecutionException, InterruptedException {
        assert noteID != null;
        editor.setTitle(noteID, "New title").thenCompose(result -> register
            .getNoteByID(noteID)
            .thenAcceptAsync(note -> assertThat(note.getTitle()).isEqualTo("New title")))
            .get();
    }

    @Test
    void setBody() throws ExecutionException, InterruptedException {
        assert noteID != null;
        editor
            .setBody(noteID, "New body")
            .thenCompose(result -> register
                .getNoteByID(noteID)
                .thenAcceptAsync(note -> assertThat(note.getBody()).isEqualTo("New body")))
            .get();
    }
}

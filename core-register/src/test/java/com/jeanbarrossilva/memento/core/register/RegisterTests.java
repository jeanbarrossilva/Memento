package com.jeanbarrossilva.memento.core.register;

import static org.assertj.core.api.Assertions.assertThat;

import com.jeanbarrossilva.memento.core.register.domain.Color;
import com.jeanbarrossilva.memento.core.register.infra.Register;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RegisterTests {
    private final Register register = new TestRegister();

    @Test
    void register() throws ExecutionException, InterruptedException {
        register
            .register("Marcus Aurelius", "'You don't have to turn it into something.'", Color.BLUE)
            .thenCompose(noteID ->
                register.getNoteByID(noteID).thenAccept(note -> assertThat(note).isNotNull())
            )
            .get();
    }

    @Test
    void unregister() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> noteIDFuture =
            register.register("Seneca", "'He who is brave is free.'", Color.BLUE);
        noteIDFuture.thenAccept(register::unregister);
        noteIDFuture
            .thenCompose(
                noteID -> register.getNoteByID(noteID).thenAccept(note -> assertThat(note).isNull())
            )
            .get();
    }
}

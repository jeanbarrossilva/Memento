package com.jeanbarrossilva.memento.core.register.domain;

import org.jetbrains.annotations.NotNull;

public class Note {
    @NotNull
    private final String id;

    @NotNull
    private final String title;

    @NotNull
    private final String body;

    @NotNull
    private final Color color;

    public Note(
        @NotNull String id,
        @NotNull String title,
        @NotNull String body,
        @NotNull Color color
    ) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.color = color;
    }

    @Override
    @NotNull
    public String toString() {
        return "Note(id=" + getID() + ", title=" + title + ", body=" + body + ", color=" + color +
            ")";
    }

    public String getID() {
        return id;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    @NotNull
    public String getBody() {
        return body;
    }

    @NotNull
    public Color getColor() {
        return color;
    }

    public Note copy(String title) {
        return copy(title, body, color);
    }

    public Note copy(String title, String body, Color color) {
        return new Note(id, title, body, color);
    }
}

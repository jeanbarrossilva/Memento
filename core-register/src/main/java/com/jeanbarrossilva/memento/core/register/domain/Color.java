package com.jeanbarrossilva.memento.core.register.domain;

public enum Color {
    BLUE("blue"),
    PURPLE("purple"),
    YELLOW("yellow");

    private final String id;

    Color(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getID();
    }

    public String getID() {
        return id;
    }
}

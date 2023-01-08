package com.jeanbarrossilva.memento.notes.animation.bounce;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;

public class Bouncer {
    private final MutableState<Bounce> bounce =
        SnapshotStateKt.mutableStateOf(Bounce.OUT, SnapshotStateKt.structuralEqualityPolicy());

    Bouncer() {
    }

    public Float getScale() {
        return bounce.getValue().getScale();
    }

    public void bounceIn() {
        boolean isOut = bounce.getValue() == Bounce.OUT;
        if (isOut) {
            bounce.setValue(Bounce.IN);
        }
    }

    public void bounceOut() {
        boolean isIn = bounce.getValue() == Bounce.IN;
        if (isIn) {
            bounce.setValue(Bounce.OUT);
        }
    }
}

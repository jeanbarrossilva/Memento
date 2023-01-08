package com.jeanbarrossilva.memento.notes.animation.bounce

import androidx.annotation.FloatRange

internal enum class Bounce {
    OUT {
        override val scale = 1f
    },
    IN {
        override val scale = .95f
    };

    @get:FloatRange(from = 0.0, to = 1.0)
    abstract val scale: Float
}

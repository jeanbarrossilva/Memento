package com.jeanbarrossilva.memento.feature.notes.animation.bounce // ktlint-disable filename

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
internal fun rememberBouncer(): Bouncer {
    return remember {
        Bouncer()
    }
}

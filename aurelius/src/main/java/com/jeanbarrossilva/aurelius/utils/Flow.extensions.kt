package com.jeanbarrossilva.aurelius.utils // ktlint-disable filename

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

/**
 * Runs the given [block] on the emitted value if it's the first emission.
 *
 * @param block Operation to be run on the first emission.
 **/
internal fun <T> Flow<T>.onFirst(block: (T) -> Unit): Flow<T> {
    var isFirstEmission = true
    return onEach {
        if (isFirstEmission) {
            block(it)
            isFirstEmission = false
        }
    }
}

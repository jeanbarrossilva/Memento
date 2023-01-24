package com.jeanbarrossilva.aurelius.utils // ktlint-disable filename

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize

/** Converts the given [IntSize] into a [DpSize]. **/
fun IntSize.toDpSize(density: Density): DpSize {
    val (widthInDp, heightInDp) = width.toDp(density) to height.toDp(density)
    return DpSize(widthInDp, heightInDp)
}

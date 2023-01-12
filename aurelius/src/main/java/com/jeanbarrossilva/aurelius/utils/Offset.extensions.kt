package com.jeanbarrossilva.aurelius.utils

import androidx.compose.ui.geometry.Offset

/** [Offset] with an infinite [x][Offset.x]-axis. **/
internal val Offset.Companion.Horizontal
    get() = Offset(x = Float.POSITIVE_INFINITY, y = 0f)

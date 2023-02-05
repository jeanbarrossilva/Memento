package com.jeanbarrossilva.aurelius.ui.layout.scaffold.menudrawer

internal sealed interface MenuDrawerWidth {
    val value: Float

    object Unspecified : MenuDrawerWidth {
        override val value = Float.MIN_VALUE
    }

    data class Specified(override val value: Float) : MenuDrawerWidth
}

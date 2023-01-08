package com.jeanbarrossilva.memento.ui.component.scaffold.menudrawer

internal sealed interface MenuDrawerWidth {
    val value: Float

    object Unspecified : MenuDrawerWidth {
        override val value = -1f
    }

    data class Specified(override val value: Float) : MenuDrawerWidth
}

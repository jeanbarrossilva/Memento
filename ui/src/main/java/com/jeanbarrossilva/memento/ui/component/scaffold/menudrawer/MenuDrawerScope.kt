package com.jeanbarrossilva.memento.ui.component.scaffold.menudrawer

interface MenuDrawerScope {
    suspend fun open()

    suspend fun close()
}

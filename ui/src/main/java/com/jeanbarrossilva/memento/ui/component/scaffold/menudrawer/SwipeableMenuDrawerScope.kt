package com.jeanbarrossilva.memento.ui.component.scaffold.menudrawer

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Suppress("SpellCheckingInspection")
internal class SwipeableMenuDrawerScope(private val state: SwipeableState<DrawerValue>) :
    MenuDrawerScope {
    override suspend fun open() {
        state.animateTo(DrawerValue.Open)
    }

    override suspend fun close() {
        state.animateTo(DrawerValue.Closed)
    }
}

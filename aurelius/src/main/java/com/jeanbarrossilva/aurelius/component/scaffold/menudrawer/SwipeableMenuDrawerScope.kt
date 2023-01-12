package com.jeanbarrossilva.aurelius.component.scaffold.menudrawer

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
internal class SwipeableMenuDrawerScope(override val swipeableState: SwipeableState<DrawerValue>) :
    MenuDrawerScope() {
    override suspend fun open() {
        swipeableState.animateTo(DrawerValue.Open)
    }

    override suspend fun close() {
        swipeableState.animateTo(DrawerValue.Closed)
    }
}

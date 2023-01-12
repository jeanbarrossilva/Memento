package com.jeanbarrossilva.aurelius.component.scaffold.menudrawer

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api

abstract class MenuDrawerScope internal constructor() {
    @OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
    internal abstract val swipeableState: SwipeableState<DrawerValue>

    abstract suspend fun open()

    abstract suspend fun close()
}

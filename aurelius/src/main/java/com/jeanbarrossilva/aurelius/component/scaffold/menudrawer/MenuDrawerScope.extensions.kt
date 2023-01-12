package com.jeanbarrossilva.aurelius.component.scaffold.menudrawer // ktlint-disable filename

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.jeanbarrossilva.aurelius.theme.AureliusTheme

/** Creates a [MenuDrawerScope] and [remembers][remember] it. **/
@Composable
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
fun rememberMenuDrawerScope(): MenuDrawerScope {
    val swipeableState =
        rememberSwipeableState(initialValue = DrawerValue.Closed, AureliusTheme.animation.spec())
    return remember { SwipeableMenuDrawerScope(swipeableState) }
}

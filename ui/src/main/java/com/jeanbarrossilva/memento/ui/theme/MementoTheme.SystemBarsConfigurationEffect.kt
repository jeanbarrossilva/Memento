package com.jeanbarrossilva.memento.ui.theme // ktlint-disable filename

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/** Sets up the system bars so that they are cohesive with the theme. **/
@Composable
internal fun SystemBarsConfigurationEffect() {
    val systemUiController = rememberSystemUiController()
    val secondaryContainerColor = MementoTheme.colors.container.secondary

    SideEffect {
        systemUiController.setStatusBarColor(secondaryContainerColor)
    }
}

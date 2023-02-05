package com.jeanbarrossilva.aurelius.theme // ktlint-disable filename

import android.app.Activity
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat

/** Sets up the system bars so that they are cohesive with the theme. **/
@Composable
internal fun SystemBarsConfigurationEffect() {
    val activity = LocalContext.current as? Activity
    val window = activity?.window

    SideEffect {
        window?.let {
            WindowCompat.setDecorFitsSystemWindows(it, true)
            it.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }
}

package com.jeanbarrossilva.aurelius.ui.system.keyboard

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.core.view.ViewCompat

/**
 * [SideEffect] that runs the given [effect] whenever the keyboard visibility changes, providing the
 * callback its current state (that is initially [idle][Keyboard.Idle]).
 *
 * @param effect Callback run whenever the visibility of the keyboard is changed.
 **/
@Composable
fun KeyboardEffect(effect: (Keyboard) -> Unit) {
    val activity = LocalContext.current as? Activity
    val density = LocalDensity.current
    val listener = remember { OnKeyboardChangeListener.createInstance(density, effect) }

    SideEffect {
        effect(Keyboard.Idle.instance)
        activity?.window?.decorView?.let { ViewCompat.setOnApplyWindowInsetsListener(it, listener) }
    }
}

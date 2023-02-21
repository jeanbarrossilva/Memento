package com.jeanbarrossilva.memento.platform.extensions

import android.view.Window
import androidx.core.view.WindowInsetsControllerCompat

/** Compatibility version of [Window.getInsetsController]. **/
val Window.insetsControllerCompat
    get() = WindowInsetsControllerCompat(this, decorView)

package com.jeanbarrossilva.aurelius.utils // ktlint-disable filename

import android.app.Activity
import android.content.Context

inline fun <reified T : Activity> Context.startActivity(vararg args: Pair<String, Any?>) {
    val intent = Intent<T>(this, *args)
    startActivity(intent)
}

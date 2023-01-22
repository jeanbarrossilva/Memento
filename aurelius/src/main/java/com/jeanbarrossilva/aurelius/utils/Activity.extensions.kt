package com.jeanbarrossilva.aurelius.utils // ktlint-disable filename

import android.app.Activity

/**
 * Gets the [Intent][Activity.getIntent] extra with the given [key].
 *
 * @param key Key of the extra.
 **/
inline fun <reified T> Activity.argumentOf(key: String): Lazy<T> {
    return lazy {
        intent?.extras?.get(key) as T
    }
}

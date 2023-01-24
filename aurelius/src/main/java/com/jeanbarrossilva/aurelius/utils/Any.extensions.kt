package com.jeanbarrossilva.aurelius.utils

// ktlint-disable filename

/**
 * Returns the result of the given [transform] if the result of [condition] is `true`; otherwise,
 * returns the receiver.
 *
 * @param condition Returns whether or not the result of [transform] will get returned.
 * @param transform Transformation to be made to the receiver.
 **/
inline fun <T> T.`if`(condition: T.() -> Boolean, transform: T.() -> T): T {
    return `if`(condition(), transform)
}

/**
 * Returns the result of the given [transform] if the [condition] is `true`; otherwise, returns the
 * receiver.
 *
 * @param condition Determines whether or not the result of [transform] will get returned.
 * @param transform Transformation to be made to the receiver.
 **/
inline fun <T> T.`if`(condition: Boolean, transform: T.() -> T): T {
    return if (condition) transform() else this
}

/**
 * Gets the property of the receiver object through reflection whose name equals the given [name].
 *
 * @param name Name of the property to be obtained.
 **/
@Suppress("UNCHECKED_CAST")
internal fun <T> Any.prop(name: String): T {
    return this::class
        .java
        .getDeclaredField(name)
        .apply { isAccessible = true }
        .get(this) as? T
        ?: throw IllegalArgumentException("Could not get ${this::class.simpleName}.$name.")
}

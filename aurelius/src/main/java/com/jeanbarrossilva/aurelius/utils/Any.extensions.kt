package com.jeanbarrossilva.aurelius.utils // ktlint-disable filename

/**
 * Returns the result of the given [transform] if the result of [condition] is `true`; otherwise,
 * returns the receiver.
 *
 * @param condition Returns whether or not the result of [transform] will get returned.
 * @param transform Transformation to be made to the receiver.
 **/
internal inline fun <T> T.`if`(condition: T.() -> Boolean, transform: T.() -> T): T {
    return `if`(condition(), transform)
}

/**
 * Returns the result of the given [transform] if the [condition] is `true`; otherwise, returns the
 * receiver.
 *
 * @param condition Determines whether or not the result of [transform] will get returned.
 * @param transform Transformation to be made to the receiver.
 **/
internal inline fun <T> T.`if`(condition: Boolean, transform: T.() -> T): T {
    return if (condition) transform() else this
}

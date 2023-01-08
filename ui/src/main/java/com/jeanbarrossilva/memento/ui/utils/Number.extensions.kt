package com.jeanbarrossilva.memento.ui.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Returns this ÷ 100 × other's [Dp.value].  **/
internal inline infix fun <reified T : Number> T.percentOf(other: Dp): Dp {
    return percentOf(other.value).toFloat().dp
}

/** Returns this ÷ 100 × [other]. **/
internal inline infix fun <reified T : Number> T.percentOf(other: T): T {
    return (toFloat() / 100f * other.to<Float>()).to()
}

/** Converts the given [Number] into one of type [T]. **/
@Suppress("UNCHECKED_CAST")
internal inline fun <reified T : Number> Number.to(): T {
    return when (T::class) {
        Byte::class -> toByte()
        Double::class -> toDouble()
        Float::class -> toFloat()
        Int::class -> toInt()
        Long::class -> toLong()
        Number::class -> this
        Short::class -> toShort()
        else -> TODO()
    } as T
}

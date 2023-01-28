package com.jeanbarrossilva.memento.core.register.domain

import java.net.URLDecoder
import java.net.URLEncoder

@JvmInline
value class Path private constructor(val value: String) {
    val name: String
        get() = decode(value.replace("$SEPARATOR", ""))
    val isRoot
        get() = this == root

    override fun toString(): String {
        return value
    }

    companion object {
        internal const val SEPARATOR = '/'

        val charset = Charsets.UTF_8
        val root = createInstance("")

        fun createInstance(value: String): Path {
            val encodedValue = URLEncoder.encode(value)
            return Path("/$encodedValue")
        }

        fun decode(value: String): String {
            return URLDecoder.decode(value) ?: value
        }
    }
}

package com.jeanbarrossilva.memento.core.register.domain

import java.net.URLDecoder
import java.net.URLEncoder

@JvmInline
value class Path internal constructor(val value: String) {
    val name
        get() = value.replace("$SEPARATOR", "").let(URLDecoder::decode)
    val isRoot
        get() = this == root

    init {
        assert(String(value.toByteArray(charset), charset) == value) {
            "Path should be UTF-8-encoded."
        }
        assert(value.isNotEmpty()) { "Path cannot be empty." }
        assert(value.startsWith(SEPARATOR)) { "Path $this should start with a '/'." }
        assert(value.filter { it == SEPARATOR }.length == 1) { "Path cannot be nested." }
    }

    override fun toString(): String {
        return value
    }

    companion object {
        internal const val SEPARATOR = '/'

        val charset = Charsets.UTF_8
        val root = Path("$SEPARATOR")

        infix fun to(value: String): Path {
            val encodedSeparator = URLEncoder.encode("$SEPARATOR")
            val encodedValue = URLEncoder.encode(value).replace(encodedSeparator, "$SEPARATOR")
            return Path(encodedValue)
        }
    }
}

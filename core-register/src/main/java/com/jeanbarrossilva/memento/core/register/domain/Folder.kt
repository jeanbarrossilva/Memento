package com.jeanbarrossilva.memento.core.register.domain

import java.net.URLDecoder
import java.net.URLEncoder

abstract class Folder private constructor() {
    open val title: String
        get() = URLDecoder.decode(path)
    open val path: String
        get() = URLEncoder.encode(title)

    val child
        get() = if (SEPARATOR in path) {
            at(path.replaceBefore(SEPARATOR, "").replaceFirst("$SEPARATOR", ""))
        } else {
            null
        }

    companion object {
        private const val SEPARATOR = '/'

        fun titled(title: String): Folder {
            return object : Folder() {
                override val title = title
            }
        }

        fun at(path: String): Folder {
            return object : Folder() {
                override val path = path
            }
        }
    }
}

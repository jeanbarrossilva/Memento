package com.jeanbarrossilva.memento.feature.notes.domain.note

import android.content.Context
import com.jeanbarrossilva.memento.feature.notes.R

data class Folder internal constructor(val path: String, val title: String) {
    companion object {
        internal val sample = Folder(path = "stoicism", title = "Stoicism")

        internal fun getDefault(context: Context): Folder {
            val title = context.getString(R.string.feature_notes)
            return Folder(path = "/", title)
        }
    }
}

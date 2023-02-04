package com.jeanbarrossilva.memento.feature.notes.domain.note

import android.content.Context
import com.jeanbarrossilva.memento.notes.R

internal data class Folder(val path: String, val title: String) {
    companion object {
        val sample = Folder(path = "stoicism", title = "Stoicism")

        fun getDefault(context: Context): Folder {
            val title = context.getString(R.string.feature_notes)
            return Folder(path = "/", title)
        }
    }
}

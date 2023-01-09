package com.jeanbarrossilva.memento.notes.domain.note

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jeanbarrossilva.memento.notes.R

internal sealed interface NoteFolder {
    @Composable
    fun title(): String

    object All : NoteFolder {
        @Composable
        override fun title(): String {
            return stringResource(R.string.feature_notes)
        }
    }

    data class Custom(private val title: String) : NoteFolder {
        @Composable
        override fun title(): String {
            return title
        }

        companion object {
            private val stoicism = Custom(title = "Stoicism")

            val sample = stoicism
            val samples = listOf(stoicism)
        }
    }
}

package com.jeanbarrossilva.memento.feature.editor.ui.focusmode

import android.content.Context
import androidx.compose.foundation.lazy.LazyListState
import com.jeanbarrossilva.memento.feature.editor.domain.Note

internal object FocusModeFactory {
    fun create(
        context: Context,
        lazyListState: LazyListState,
        note: Note
    ): FocusMode {
        return if (note.isEmpty(context)) {
            FocusMode.Default
        } else {
            FocusMode.Scrolling(lazyListState)
        }
    }
}

package com.jeanbarrossilva.memento.feature.editor.ui.focusmode

import androidx.compose.foundation.lazy.LazyListState
import com.jeanbarrossilva.memento.feature.editor.domain.Note

internal object FocusModeFactory {
    fun create(lazyListState: LazyListState, note: Note): FocusMode {
        return if (note.isEmpty) FocusMode.Default else FocusMode.Scrolling(lazyListState)
    }
}

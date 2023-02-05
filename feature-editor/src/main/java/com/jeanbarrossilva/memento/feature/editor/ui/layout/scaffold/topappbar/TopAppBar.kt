package com.jeanbarrossilva.memento.feature.editor.ui.layout.scaffold.topappbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.topappbar.TopAppBar
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.memento.feature.editor.domain.EditorMode
import com.jeanbarrossilva.memento.feature.editor.domain.Note
import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors
import com.jeanbarrossilva.memento.feature.editor.domain.isEditing
import com.jeanbarrossilva.memento.feature.editor.ui.focus.FocusMode
import com.jeanbarrossilva.memento.feature.editor.ui.input.noteheadline.Title
import com.jeanbarrossilva.memento.feature.editor.ui.input.noteheadline.colors.NoteColorsCarousel

@Composable
internal fun TopAppBar(
    editorMode: EditorMode,
    note: Note,
    focusMode: FocusMode,
    isCompact: Boolean,
    onNavigationRequest: () -> Unit,
    onTitleChange: (title: String) -> Unit,
    onDeletionRequest: () -> Unit,
    onColorsChange: (colors: NoteColors) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val isEditing = editorMode.isEditing()

    TopAppBar(
        isCompact,
        navigationButton = {
            NavigationButton(note.colors, isEditing, onClick = onNavigationRequest)
        },
        title = {
            Column(verticalArrangement = Arrangement.spacedBy(AureliusTheme.sizes.spacing.medium)) {
                Title(note, focusMode, isEditing, onTitleChange, Modifier.fillMaxWidth())
                NoteColorsCarousel(
                    editorMode,
                    note,
                    onColorsChange
                )
            }
        },
        modifier,
        containerBrush = SolidColor(note.colors.container.secondary),
        subtitle = {
            Text(
                note.lastEditedAt,
                color = note.colors.content.copy(AureliusTheme.visibility.medium)
            )
        },
        actions = { DeleteAction(isEditing, onClick = onDeletionRequest) },
        content
    )
}

@Composable
private fun TopAppBar(isEditing: Boolean, modifier: Modifier = Modifier) {
    TopAppBar(
        EditorMode.Reading,
        Note.sample,
        FocusMode.Default,
        isCompact = false,
        onNavigationRequest = { },
        onTitleChange = { },
        onDeletionRequest = { },
        onColorsChange = { },
        modifier
    ) {
    }
}

@Composable
@Preview
private fun TopAppBarPreview() {
    AureliusTheme {
        TopAppBar(isEditing = false)
    }
}

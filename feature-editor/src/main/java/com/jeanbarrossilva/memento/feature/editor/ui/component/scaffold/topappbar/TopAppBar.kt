package com.jeanbarrossilva.memento.feature.editor.ui.component.scaffold.topappbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.component.scaffold.topappbar.TopAppBar
import com.jeanbarrossilva.aurelius.theme.AureliusTheme
import com.jeanbarrossilva.memento.feature.editor.domain.Note
import com.jeanbarrossilva.memento.feature.editor.ui.component.NoteTitle
import com.jeanbarrossilva.memento.feature.editor.ui.focusmode.FocusMode

@Composable
internal fun TopAppBar(
    note: Note,
    focusMode: FocusMode,
    isCompact: Boolean,
    isEditing: Boolean,
    onNavigationRequest: () -> Unit,
    onTitleChange: (title: String) -> Unit,
    onDeletionRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    TopAppBar(
        isCompact,
        navigationButton = {
            NavigationButton(note.colors, isEditing, onClick = onNavigationRequest)
        },
        title = { NoteTitle(note, focusMode, isEditing, onTitleChange, Modifier.fillMaxWidth()) },
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
        Note.sample,
        FocusMode.Default,
        isCompact = false,
        isEditing,
        onNavigationRequest = { },
        onTitleChange = { },
        onDeletionRequest = { },
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

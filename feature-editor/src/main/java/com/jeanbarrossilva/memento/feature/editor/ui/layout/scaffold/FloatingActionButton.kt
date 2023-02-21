package com.jeanbarrossilva.memento.feature.editor.ui.layout.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.FloatingActionButton
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.memento.feature.editor.R
import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors

@Composable
internal fun FloatingActionButton(
    colors: NoteColors,
    isEditing: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val vector = if (isEditing) Icons.Rounded.Check else Icons.Rounded.Edit
    val iconContentDescriptionRes =
        if (isEditing) R.string.feature_editor_save else R.string.feature_editor_edit

    FloatingActionButton(
        onClick,
        modifier,
        containerColor = colors.container.secondary,
        contentColor = Color.Black
    ) {
        Icon(vector, contentDescription = stringResource(iconContentDescriptionRes))
    }
}

@Composable
private fun FloatingActionButton(isEditing: Boolean, modifier: Modifier = Modifier) {
    FloatingActionButton(NoteColors.YELLOW, isEditing, onClick = { }, modifier)
}

@Composable
@Preview
private fun EditFloatingActionButtonPreview() {
    AureliusTheme {
        FloatingActionButton(isEditing = false)
    }
}

@Composable
@Preview
private fun SaveFloatingActionButtonPreview() {
    AureliusTheme {
        FloatingActionButton(isEditing = true)
    }
}

package com.jeanbarrossilva.memento.feature.editor.ui.input.noteheadline.colors // ktlint-disable filename

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.memento.feature.editor.R
import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors

internal fun noteHeadlineColorBubbleTagFor(colors: NoteColors): String {
    return "note_headline_colors_bubble_${colors.name.lowercase()}"
}

@Composable
internal fun Bubble(
    colors: NoteColors,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(
        LocalContentColor provides colors.content.copy(AureliusTheme.visibility.low)
    ) {
        Box(
            modifier
                .testTag(noteHeadlineColorBubbleTagFor(colors))
                .semantics { selected = isSelected }
                .requiredSize(42.dp)
                .clip(CircleShape)
                .border(2.dp, LocalContentColor.current, CircleShape)
                .background(colors.container.primary)
                .clickable(onClick = onClick),
            Alignment.Center
        ) {
            if (isSelected) {
                Icon(
                    Icons.Rounded.Check,
                    contentDescription = stringResource(R.string.feature_editor_selected_note_color)
                )
            }
        }
    }
}

@Composable
private fun Bubble(isSelected: Boolean, modifier: Modifier = Modifier) {
    Bubble(NoteColors.YELLOW, isSelected, onClick = { }, modifier)
}

@Composable
@Preview
private fun UnselectedBubblePreview() {
    AureliusTheme {
        Bubble(isSelected = false)
    }
}

@Composable
@Preview
private fun SelectedBubblePreview() {
    AureliusTheme {
        Bubble(isSelected = true)
    }
}

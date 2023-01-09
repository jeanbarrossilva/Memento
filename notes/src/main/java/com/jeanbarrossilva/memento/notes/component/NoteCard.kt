package com.jeanbarrossilva.memento.notes.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.memento.notes.animation.bounce.rememberBouncer
import com.jeanbarrossilva.memento.notes.domain.Selection
import com.jeanbarrossilva.memento.notes.domain.contains
import com.jeanbarrossilva.memento.notes.domain.ifOff
import com.jeanbarrossilva.memento.notes.domain.note.Note
import com.jeanbarrossilva.memento.ui.component.checkbox.Checkbox
import com.jeanbarrossilva.memento.ui.layout.background.Background
import com.jeanbarrossilva.memento.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.memento.ui.theme.MementoTheme

@Composable
internal fun NoteCard(
    note: Note,
    selection: Selection,
    onSelectionToggle: (Selection) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    @Suppress("NAME_SHADOWING")
    val onSelectionToggle = {
        onSelectionToggle(selection.toggle(note))
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(MementoTheme.sizes.spacing.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(
            visible = selection is Selection.On,
            enter = fadeIn(MementoTheme.animation.spec()) +
                slideInHorizontally(MementoTheme.animation.spec()) { -it },
            exit = fadeOut(MementoTheme.animation.spec()) +
                slideOutHorizontally(MementoTheme.animation.spec()) { -it }
        ) {
            Checkbox(note in selection, onToggle = { onSelectionToggle() })
        }

        NoteCard(
            note,
            pointerInputKey = selection,
            onClick = {
                when (selection) {
                    is Selection.On -> onSelectionToggle()
                    is Selection.Off -> onClick()
                }
            },
            onLongClick = {
                selection.ifOff {
                    onSelectionToggle()
                }
            },
            modifier
        )
    }
}

@Composable
internal fun NoteCard(
    note: Note,
    pointerInputKey: Any?,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isHovered by remember { mutableStateOf(false) }
    val shape = MaterialTheme.shapes.large
    val bouncer = rememberBouncer()
    val scale by animateFloatAsState(bouncer.scale, MementoTheme.animation.spec())
    val elevation by animateDpAsState(if (isHovered) 32.dp else 0.dp, MementoTheme.animation.spec())

    LaunchedEffect(Unit) {
        interactionSource.interactions.collect {
            when (it) {
                is HoverInteraction.Enter -> isHovered = true
                is HoverInteraction.Exit -> isHovered = false
            }
        }
    }

    Column(
        modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .hoverable(interactionSource)
            .pointerInput(pointerInputKey) {
                detectTapGestures(
                    onLongPress = {
                        onLongClick()
                        bouncer.bounceOut()
                    },
                    onPress = {
                        bouncer.bounceIn()
                        if (tryAwaitRelease()) {
                            onClick()
                        }
                        bouncer.bounceOut()
                    }
                )
            }
            .shadow(
                elevation,
                shape,
                clip = false,
                ambientColor = note.colors.end,
                spotColor = note.colors.start
            )
            .clip(shape)
            .background(Brush.linearGradient(listOf(note.colors.start, note.colors.end)))
            .padding(MementoTheme.sizes.spacing.huge),
        Arrangement.spacedBy(MementoTheme.sizes.spacing.large)
    ) {
        Text(
            note.title,
            color = Color.Black,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            style = MementoTheme.text.title
        )

        Text(
            note.body,
            color = Color.Black.copy(MementoTheme.colors.text.default.alpha),
            overflow = TextOverflow.Ellipsis,
            maxLines = 7
        )
    }
}

@Composable
private fun NoteCard(selection: Selection, modifier: Modifier = Modifier) {
    NoteCard(Note.sample, selection, onSelectionToggle = { }, onClick = { }, modifier)
}

@Composable
@Preview
private fun NoteCardPreview() {
    MementoTheme {
        NoteCard(Selection.Off, Modifier.fillMaxWidth())
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun UnselectedNoteCardPreview() {
    MementoTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            NoteCard(Selection.On())
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun SelectedNoteCardPreview() {
    MementoTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            NoteCard(Selection.On.sample)
        }
    }
}

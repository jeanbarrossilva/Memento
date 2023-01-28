package com.jeanbarrossilva.memento.feature.editor.ui.component.noteheadline.colors

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.isUnspecified
import com.jeanbarrossilva.aurelius.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.utils.isScrolling
import com.jeanbarrossilva.aurelius.utils.toDpSize
import com.jeanbarrossilva.memento.feature.editor.domain.EditorMode
import com.jeanbarrossilva.memento.feature.editor.domain.Note
import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors
import com.jeanbarrossilva.memento.feature.editor.domain.isEditing

@Composable
internal fun NoteColorsCarousel(
    mode: EditorMode,
    note: Note,
    onColorsChange: (colors: NoteColors) -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val state = rememberLazyListState()
    var height by remember { mutableStateOf(Dp.Unspecified) }
    val shadowBrush = remember(note) {
        Brush.horizontalGradient(listOf(note.colors.container.secondary, Color.Transparent))
    }
    val innerShadowAlpha by animateFloatAsState(if (state.isScrolling) 1f else 0f)

    AnimatedVisibility(
        visible = mode.isEditing() && mode.isColorPickerVisible,
        enter = slideInVertically(AureliusTheme.animation.spec { fast }) { -it },
        exit = slideOutVertically(AureliusTheme.animation.spec { fast }) { -it }
    ) {
        Box(modifier) {
            NoteColorsCarousel(
                note,
                onColorsChange,
                Modifier.onPlaced {
                    if (height.isUnspecified) {
                        height = it.size.toDpSize(density).height
                    }
                },
                state = state
            )

            Box(
                Modifier
                    .height(height)
                    .background(shadowBrush, alpha = innerShadowAlpha)
            )

            Box(
                Modifier
                    .height(height)
                    .background(shadowBrush, alpha = innerShadowAlpha)
                    .align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
internal fun NoteColorsCarousel(
    note: Note,
    onColorsChange: (colors: NoteColors) -> Unit,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState()
) {
    LazyRow(
        modifier,
        state,
        horizontalArrangement = Arrangement.spacedBy(AureliusTheme.sizes.spacing.medium)
    ) {
        items(NoteColors.values()) {
            Bubble(it, isSelected = note.colors == it, onClick = { onColorsChange(it) })
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NoteColorsCarouselPreview() {
    AureliusTheme {
        NoteColorsCarousel(Note.sample, onColorsChange = { })
    }
}

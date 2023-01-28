package com.jeanbarrossilva.memento.feature.editor.ui.component.scaffold.topappbar // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.component.scaffold.topappbar.BackButton
import com.jeanbarrossilva.aurelius.component.scaffold.topappbar.CloseButton
import com.jeanbarrossilva.aurelius.theme.AureliusTheme
import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors
import com.jeanbarrossilva.aurelius.component.scaffold.topappbar.TopAppBarDefaults as AureliusTopAppBarDefaults
import com.jeanbarrossilva.memento.feature.editor.ui.component.scaffold.topappbar.TopAppBarDefaults as EditorTopAppBarDefaults

@Composable
internal fun NavigationButton(
    colors: NoteColors,
    isEditing: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val enterTransition = EditorTopAppBarDefaults.buttonEnterTransition
    val exitTransition = EditorTopAppBarDefaults.buttonExitTransition

    Box(
        modifier.size(AureliusTopAppBarDefaults.navigationButtonSize),
        propagateMinConstraints = true
    ) {
        AnimatedVisibility(visible = isEditing, enter = enterTransition, exit = exitTransition) {
            CloseButton(onClick, tint = colors.content)
        }

        AnimatedVisibility(visible = !isEditing, enter = enterTransition, exit = exitTransition) {
            BackButton(onClick, tint = colors.content)
        }
    }
}

@Composable
private fun NavigationButton(isEditing: Boolean, modifier: Modifier = Modifier) {
    NavigationButton(NoteColors.YELLOW, isEditing, onClick = { }, modifier)
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NavigationButtonPreview() {
    AureliusTheme {
        NavigationButton(isEditing = false)
    }
}

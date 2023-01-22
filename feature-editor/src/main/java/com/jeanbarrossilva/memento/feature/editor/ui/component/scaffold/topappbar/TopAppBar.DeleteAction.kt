package com.jeanbarrossilva.memento.feature.editor.ui.component.scaffold.topappbar // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.component.scaffold.topappbar.DeleteAction
import com.jeanbarrossilva.aurelius.theme.AureliusTheme

@Composable
internal fun DeleteAction(
    isEditing: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = !isEditing,
        modifier,
        TopAppBarDefaults.buttonEnterTransition,
        TopAppBarDefaults.buttonExitTransition
    ) {
        DeleteAction(onClick)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun DeleteActionPreview() {
    AureliusTheme {
        DeleteAction(isEditing = false, onClick = { })
    }
}

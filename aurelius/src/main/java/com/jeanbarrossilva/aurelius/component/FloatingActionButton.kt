package com.jeanbarrossilva.aurelius.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.component.FloatingActionButton as _FloatingActionButton
import com.jeanbarrossilva.aurelius.theme.AureliusTheme

@Composable
fun FloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        isVisible,
        enter = fadeIn(AureliusTheme.animation.spec()) +
            slideInVertically(AureliusTheme.animation.spec { fast }) { it },
        exit = fadeOut(AureliusTheme.animation.spec()) +
            slideOutVertically(AureliusTheme.animation.spec { fast }) { it }
    ) {
        FloatingActionButton(onClick, modifier, content = content)
    }
}

@Composable
internal fun FloatingActionButton(modifier: Modifier = Modifier) {
    _FloatingActionButton(onClick = { }, modifier) {
        Icon(Icons.Rounded.Add, contentDescription = "Add")
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun FloatingActionButtonPreview() {
    AureliusTheme {
        _FloatingActionButton()
    }
}

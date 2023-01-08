package com.jeanbarrossilva.memento.ui.layout.background

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.memento.ui.theme.MementoTheme

@Composable
fun Background(
    modifier: Modifier = Modifier,
    contentSizing: BackgroundContentSizing = BackgroundContentSizing.FILL,
    content: @Composable BoxScope.() -> Unit
) {
    Surface(color = MementoTheme.colors.background) {
        Box(modifier then contentSizing.modifier) {
            CompositionLocalProvider(
                LocalContentColor provides MementoTheme.colors.content.secondary
            ) {
                content()
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun BackgroundPreview() {
    MementoTheme {
        Background {
        }
    }
}

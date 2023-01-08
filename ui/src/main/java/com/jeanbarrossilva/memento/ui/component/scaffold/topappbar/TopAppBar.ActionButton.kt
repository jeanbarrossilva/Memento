package com.jeanbarrossilva.memento.ui.component.scaffold.topappbar // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.memento.ui.layout.background.Background
import com.jeanbarrossilva.memento.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.memento.ui.theme.MementoTheme

@Composable
fun ActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    IconButton(
        onClick,
        modifier
            .clip(MaterialTheme.shapes.extraSmall)
            .background(MementoTheme.colors.container.primary)
            .padding(MementoTheme.sizes.spacing.small)
            .size(20.dp)
    ) {
        CompositionLocalProvider(
            LocalContentColor provides MementoTheme.colors.content.primary,
            content = content
        )
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ActionButtonPreview() {
    MementoTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            ActionButton(onClick = { }) {
                Icon(Icons.Rounded.Add, contentDescription = "Add")
            }
        }
    }
}

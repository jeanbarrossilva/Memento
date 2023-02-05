package com.jeanbarrossilva.aurelius.ui.layout.dialog // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme

@Composable
internal fun Split(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier,
        Arrangement.spacedBy(AureliusTheme.sizes.spacing.large, Alignment.End),
        Alignment.CenterVertically,
        content
    )
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun SplitPreview() {
    AureliusTheme {
        Split {
            Text("🧐")
        }
    }
}

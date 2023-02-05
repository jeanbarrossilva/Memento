package com.jeanbarrossilva.aurelius.ui.layout.dialog // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme

@Composable
internal fun Headline(
    title: @Composable () -> Unit,
    body: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier, Arrangement.spacedBy(AureliusTheme.sizes.spacing.medium)) {
        ProvideTextStyle(AureliusTheme.text.title.small) {
            title()
        }

        body()
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun HeadlinePreview() {
    AureliusTheme {
        Headline(title = { Text("Title") }, body = { Text("Body") })
    }
}

package com.jeanbarrossilva.aurelius.ui.layout.dialog // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme

@Composable
fun NeutralButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    TextButton(onClick, modifier, shape = DialogDefaults.buttonShape) {
        ProvideTextStyle(AureliusTheme.text.body.copy(AureliusTheme.colors.text.highlighted)) {
            content()
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NeutralButtonPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            NeutralButton(onClick = { }) {
                Text(stringResource(android.R.string.cancel))
            }
        }
    }
}

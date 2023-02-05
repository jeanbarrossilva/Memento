package com.jeanbarrossilva.aurelius.ui.input

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme

@Composable
fun Checkbox(
    isChecked: Boolean,
    onToggle: (isChecked: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor by animateColorAsState(
        if (isChecked) {
            AureliusTheme.colors.container.primary
        } else {
            AureliusTheme.colors.container.secondary
        }
    )
    val containerColor by animateColorAsState(
        if (isChecked) backgroundColor else Color.Transparent
    )

    Button(
        onClick = { onToggle(!isChecked) },
        modifier.size(32.dp),
        colors = buttonColors(
            containerColor,
            contentColor = AureliusTheme.colors.content.primary
        ),
        border = BorderStroke(2.dp, backgroundColor),
        contentPadding = PaddingValues(8.dp)
    ) {
        if (isChecked) {
            Icon(
                Icons.Rounded.Check,
                contentDescription = "Check"
            )
        }
    }
}

@Composable
private fun Checkbox(isChecked: Boolean, modifier: Modifier = Modifier) {
    Checkbox(isChecked, onToggle = { }, modifier)
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun SelectedCheckboxPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            Checkbox(isChecked = false)
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun UnselectedCheckboxPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            Checkbox(isChecked = true)
        }
    }
}

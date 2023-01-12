package com.jeanbarrossilva.aurelius.component.scaffold

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.component.FloatingActionButton
import com.jeanbarrossilva.aurelius.layout.background.Background
import com.jeanbarrossilva.aurelius.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.utils.`if`
import com.jeanbarrossilva.aurelius.utils.isNegative
import com.jeanbarrossilva.aurelius.utils.minus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffold(
    modifier: Modifier = Modifier,
    floatingActionButton: @Composable () -> Unit = { },
    content: @Composable (padding: PaddingValues) -> Unit
) {
    androidx.compose.material3.Scaffold(
        modifier,
        floatingActionButton = {
            Box(
                Modifier.offset(
                    y = -AureliusTheme.sizes.margin.navigationBar.calculateBottomPadding()
                )
            ) {
                floatingActionButton()
            }
        }
    ) {
        val padding = it.minus(AureliusTheme.sizes.margin.statusBar).`if`({ isNegative }) { it }
        content(padding)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ScaffoldPreview() {
    AureliusTheme {
        Scaffold(floatingActionButton = { FloatingActionButton() }) {
            Background {
            }
        }
    }
}

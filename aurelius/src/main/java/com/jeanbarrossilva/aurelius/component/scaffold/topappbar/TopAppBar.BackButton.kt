package com.jeanbarrossilva.aurelius.component.scaffold.topappbar // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import com.jeanbarrossilva.aurelius.layout.background.Background
import com.jeanbarrossilva.aurelius.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.aurelius.theme.AureliusTheme
import com.jeanbarrossilva.memento.ui.R

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    val vector = when (LocalLayoutDirection.current) {
        LayoutDirection.Ltr -> Icons.Rounded.ArrowBack
        LayoutDirection.Rtl -> Icons.Rounded.ArrowForward
    }

    IconButton(onClick, modifier) {
        Icon(
            vector,
            contentDescription = stringResource(R.string.top_app_bar_back_button),
            Modifier.size(TopAppBarDefaults.NavigationButtonIconSize),
            tint
        )
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun BackButtonPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            BackButton(onClick = { })
        }
    }
}

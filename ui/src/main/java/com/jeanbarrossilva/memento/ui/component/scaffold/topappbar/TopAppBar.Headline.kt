package com.jeanbarrossilva.memento.ui.component.scaffold.topappbar // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.memento.ui.layout.background.Background
import com.jeanbarrossilva.memento.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.memento.ui.theme.MementoTheme
import com.jeanbarrossilva.memento.ui.utils.animateTextUnitAsState

@Composable
internal fun Headline(
    isCompact: Boolean,
    title: @Composable () -> Unit,
    subtitle: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val compactTitleStyle = MementoTheme.text.title
    val titleSize by animateTextUnitAsState(
        if (isCompact) compactTitleStyle.fontSize else MementoTheme.text.headline.fontSize,
        MementoTheme.animation.spec()
    )
    val titleStyle = if (isCompact) compactTitleStyle else MementoTheme.text.headline
    val animatedSizeTitleStyle = titleStyle.copy(
        MementoTheme.colors.content.secondary,
        fontSize = titleSize
    )
    val titleOffsetY by animateDpAsState(
        if (isCompact) (-8).dp else 0.dp,
        MementoTheme.animation.spec()
    )

    Column(modifier) {
        ProvideTextStyle(animatedSizeTitleStyle) {
            Box(Modifier.offset(titleOffsetY)) {
                title()
            }
        }

        AnimatedVisibility(
            visible = !isCompact,
            enter = fadeIn(MementoTheme.animation.spec()) +
                slideInVertically(MementoTheme.animation.spec()) { -it },
            exit = fadeOut(MementoTheme.animation.spec()) +
                slideOutVertically(MementoTheme.animation.spec()) { -it }
        ) {
            subtitle()
        }
    }
}

@Composable
private fun Headline(modifier: Modifier = Modifier) {
    Headline(
        isCompact = false,
        title = { Text("Title") },
        subtitle = { Text("Subtitle") },
        modifier
    )
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun HeadlinePreview() {
    MementoTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            Headline()
        }
    }
}

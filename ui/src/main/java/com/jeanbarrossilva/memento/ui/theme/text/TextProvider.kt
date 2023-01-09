package com.jeanbarrossilva.memento.ui.theme.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jeanbarrossilva.memento.ui.theme.MementoTheme
import com.jeanbarrossilva.memento.ui.utils.DMSans
import com.jeanbarrossilva.memento.ui.utils.typography.TypographyTokens

/**
 * Provides the [Text] to be used in the [MementoTheme].
 *
 * @param content Content to be able to access the provided value through [LocalText].
 **/
@Composable
internal fun TextProvider(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalText provides Text(
            headline = TypographyTokens.HeadlineSmall.copy(
                MementoTheme.colors.text.highlighted,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.DMSans
            ),
            title = TypographyTokens.TitleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.DMSans
            ),
            body = TypographyTokens.BodyLarge.copy(
                MementoTheme.colors.text.default,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.DMSans,
                letterSpacing = .3.sp,
                lineHeight = 18.sp
            ),
            label = TypographyTokens.BodySmall.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.DMSans
            )
        ),
        content = content
    )
}

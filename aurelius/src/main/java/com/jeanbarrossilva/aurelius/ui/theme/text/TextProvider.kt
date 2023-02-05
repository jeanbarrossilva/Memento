package com.jeanbarrossilva.aurelius.ui.theme.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jeanbarrossilva.aurelius.theme.text.LocalText
import com.jeanbarrossilva.aurelius.theme.text.Text
import com.jeanbarrossilva.aurelius.theme.text.Title
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.utils.DMSans
import com.jeanbarrossilva.aurelius.utils.typography.TypographyTokens

/**
 * Provides the [Text] to be used in the [AureliusTheme].
 *
 * @param content Content to be able to access the provided value through [LocalText].
 **/
@Composable
internal fun TextProvider(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalText provides Text(
            headline = TypographyTokens.HeadlineSmall.copy(
                AureliusTheme.colors.text.highlighted,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.DMSans
            ),
            title = Title(
                large = TypographyTokens.TitleLarge.copy(
                    AureliusTheme.colors.text.highlighted,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.DMSans
                ),
                small = TypographyTokens.BodyLarge.copy(
                    AureliusTheme.colors.text.highlighted,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.DMSans,
                    letterSpacing = .3.sp,
                    lineHeight = 18.sp
                )
            ),
            body = TypographyTokens.BodyLarge.copy(
                AureliusTheme.colors.text.default,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.DMSans,
                letterSpacing = .3.sp,
                lineHeight = 18.sp
            ),
            label = TypographyTokens.BodySmall.copy(
                AureliusTheme.colors.text.highlighted,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.DMSans
            )
        ),
        content = content
    )
}

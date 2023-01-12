package com.jeanbarrossilva.aurelius.theme.animation

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.jeanbarrossilva.aurelius.theme.AureliusTheme
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * Provides the [Animation] to be used in the [AureliusTheme].
 *
 * @param content Content to be able to access the provided value through [LocalAnimation].
 **/
@Composable
internal fun AnimationProvider(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalAnimation provides object : Animation() {
            override val durations = AnimationDurations(
                fast = 64.milliseconds,
                medium = 128.milliseconds,
                slow = 256.milliseconds
            )

            override fun <T> spec(duration: AnimationDurations.() -> Duration):
                FiniteAnimationSpec<T> {
                val durationInMilliseconds = durations.duration().inWholeMilliseconds.toInt()
                return tween(durationInMilliseconds)
            }
        },
        content = content
    )
}

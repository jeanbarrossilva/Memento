package com.jeanbarrossilva.aurelius.theme.visibility

import androidx.annotation.FloatRange

/**
 * Represents content opacity.
 *
 * @param medium Higher than [low].
 * @param low Lower than [medium].
 **/
data class Visibility internal constructor(
    @FloatRange(from = 0.0, to = 1.0) val medium: Float,
    @FloatRange(from = 0.0, to = 1.0) val low: Float
) {
    companion object {
        /** [Visibility] with zeroed values. **/
        internal val Zero = Visibility(medium = 0f, low = 0f)
    }
}

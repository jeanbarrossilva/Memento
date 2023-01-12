package com.jeanbarrossilva.aurelius.theme.visibility

import androidx.annotation.FloatRange

/**
 * Represents content opacity.
 *
 * @param medium Only level for now.
 **/
data class Visibility internal constructor(@FloatRange(from = 0.0, to = 1.0) val medium: Float) {
    companion object {
        /** [Visibility] with zeroed values. **/
        internal val Zero = Visibility(medium = 0f)
    }
}

package com.jeanbarrossilva.aurelius.ui.system.keyboard

import android.view.View
import androidx.compose.ui.unit.Density
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.WindowInsetsCompat
import com.jeanbarrossilva.aurelius.utils.toDp

/** Calls [onKeyboardChange] whenever the state of the software keyboard changes. **/
internal abstract class OnKeyboardChangeListener private constructor() :
    OnApplyWindowInsetsListener {
    /** [Density] through which the height of the keyboard will be calculated. **/
    protected abstract val density: Density

    /**
     * Callback that's run when the state of the keyboard is changed.
     *
     * @param keyboard State to which the keyboard has changed.
     **/
    abstract fun onKeyboardChange(keyboard: Keyboard)

    final override fun onApplyWindowInsets(v: View, insets: WindowInsetsCompat):
        WindowInsetsCompat {
        val typeMask = WindowInsetsCompat.Type.ime()
        val isVisible = insets.isVisible(typeMask)
        val height = insets.getInsets(typeMask).bottom.toDp(density)
        val keyboard = if (isVisible) Keyboard.Open(height) else Keyboard.Closed.instance
        onKeyboardChange(keyboard)
        return insets
    }

    companion object {
        /**
         * Creates an instance of an [OnKeyboardChangeListener].
         *
         * @param density [Density] to be attributed to [OnKeyboardChangeListener.density].
         * @param onKeyboardChange Callback to be called from
         * [OnKeyboardChangeListener.onKeyboardChange].
         **/
        fun createInstance(
            density: Density,
            onKeyboardChange: (Keyboard) -> Unit
        ): OnKeyboardChangeListener {
            return object : OnKeyboardChangeListener() {
                override val density = density

                override fun onKeyboardChange(keyboard: Keyboard) {
                    onKeyboardChange(keyboard)
                }
            }
        }
    }
}

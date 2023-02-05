package com.jeanbarrossilva.aurelius.ui.system.keyboard

import androidx.compose.ui.unit.Dp

/** Holds properties related to the software keyboard and represents its current state. **/
sealed interface Keyboard {
    /**
     * Initial state. Doesn't necessarily mean that the keyboard is closed, but rather that it's in
     * the state in which it was when it began to be listened to.
     **/
    class Idle private constructor() : Keyboard {
        override fun toString(): String {
            return "Idle"
        }

        companion object {
            /** Singleton of [Idle]. **/
            internal val instance = Idle()
        }
    }

    /**
     * State in which the keyboard is visible.
     *
     * @param height Height of the keyboard in [Dp]s.
     **/
    data class Open internal constructor(val height: Dp) : Keyboard

    /**
     * State in which the keyboard has been dismissed either by the system or the user and is no
     * longer visible.
     **/
    class Closed private constructor() : Keyboard {
        override fun toString(): String {
            return "Closed"
        }

        companion object {
            /** Singleton of [Closed]. **/
            internal val instance = Closed()
        }
    }
}

package com.jeanbarrossilva.memento.notes.domain

import com.jeanbarrossilva.memento.notes.domain.Selection.Off
import com.jeanbarrossilva.memento.notes.domain.Selection.Off.toggle
import com.jeanbarrossilva.memento.notes.domain.Selection.On
import com.jeanbarrossilva.memento.notes.domain.note.Note
import com.jeanbarrossilva.memento.notes.utils.toggle

/**
 * Controls the selection of [Note]s, aiming on a good UX.
 *
 * The expected usage is to provide an initial selection that's [off][Off] and to listen to the
 * event that'll turn it [on][On], such as a long press. And then, when the second event is fired
 * off (such as a tap) while the selection is [on][On], it selects or unselects the given
 * [Note] (achievable through [On.toggle], that, in turn, returns the
 * selection that corresponds to the state after that change).
 **/
internal sealed class Selection {
    /**
     * A toggleable state that holds the currently selected [Note]s.
     *
     * @param selected [Note]s that are selected.
     **/
    data class On(val selected: List<Note>) : Selection() {
        constructor() : this(emptyList())

        constructor(note: Note) : this(listOf(note))

        override fun toggle(note: Note): Selection {
            val toggled = selected.toMutableList().apply { toggle(note) }.toList()
            return if (toggled.isEmpty()) Off else On(toggled)
        }

        fun isSelected(note: Note): Boolean {
            return note in selected
        }

        companion object {
            val sample = On(Note.sample)
        }
    }

    /**
     * A static state whose only operation that can be performed is for it to be turned on through a
     * [toggle].
     **/
    object Off : Selection() {
        override fun toggle(note: Note): Selection {
            return On(note)
        }
    }

    /**
     * Selects or unselects the given [note] and then returns a selection according to the
     * result of the operation.
     *
     * @param note [Note] to toggle.
     **/
    abstract fun toggle(note: Note): Selection
}

package com.jeanbarrossilva.memento.feature.editor.domain

import android.content.Context
import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors

internal data class Note(
    val title: String,
    val body: String,
    val colors: NoteColors,
    val lastEditedAt: String
) {
    fun isEmpty(context: Context): Boolean {
        return this == getEmpty(context)
    }

    companion object {
        val sample = Note(
            title = "Marcus Aurelius",
            body = "”The happiness of your life depends upon the quality of your thoughts: " +
                "therefore, guard accordingly, and take care that you entertain no notions " +
                "unsuitable to virtue and reasonable nature.”\n\n”Accept the things to which " +
                "fate binds you, and love the people with whom fate brings you together, but do " +
                "so with all your heart.”\n\n”It is not death that a man should fear, but he " +
                "should fear never beginning to live.”\n\n”You have power over your mind - not " +
                "outside events. Realize this, and you will find strength.”\n\n”Reject your " +
                "sense of injury and the injury itself disappears.”\n\n”Never let the future " +
                "disturb you. You will meet it, if you have to, with the same weapons of reason " +
                "which today arm you against the present.”\n\n”Very little is needed to make a " +
                "happy life; it is all within yourself, in your way of thinking.”\n\n”If it is " +
                "not right do not do it; if it is not true do not say it.”",
            NoteColors.sample,
            lastEditedAt = "Edited yesterday, 23:59"
        )

        fun getEmpty(context: Context): Note {
            return Note(title = "", body = "", NoteColors.getEmpty(context), lastEditedAt = "")
        }
    }
}

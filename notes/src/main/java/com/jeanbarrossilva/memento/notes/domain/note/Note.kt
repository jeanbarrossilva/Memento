package com.jeanbarrossilva.memento.notes.domain.note

import com.jeanbarrossilva.memento.notes.utils.uuid

internal data class Note(
    val id: String,
    val title: String,
    val body: String,
    val colors: NoteColors,
    val lastEditedAt: String
) {
    companion object {
        private val epictetus = Note(
            id = uuid(),
            title = "Epictetus",
            body = "”If anyone tells you that a certain person speaks ill of you, do not make " +
                "excuses about what is said of you but answer, \"He was ignorant of my other " +
                "faults, else he would not have mentioned these alone.”\n\n”Any person capable " +
                "of angering you becomes your master; he can anger you only when you permit...",
            NoteColors.PURPLE,
            lastEditedAt = "Edited Wednesday, 12:00"
        )
        private val marcusAurelius = Note(
            id = uuid(),
            title = "Marcus Aurelius",
            body = "”The happiness of your life depends upon the quality of your thoughts: " +
                "therefore, guard accordingly, and take care that you entertain no notions " +
                "unsuitable to virtue and reasonable nature.”\n\n" + "”Accept the things to " +
                "which fate binds you, and love the people with whom fate brings you together, " +
                "but do so with all your heart.”\n\n”It is not death that a man should fear, but " +
                "he should fear never beginning to live.”\n\n”You have power over your mind - " +
                "not outside events. Realize this, and you will find strength.”\n" +
                "\n”Reject your sense of injury and the injury itself disappears.”\n\n”Never let " +
                "the future disturb you. You will meet it, if you have to, with the same weapons " +
                "of reason which today arm you against the present.”\n\n”Very little is needed " +
                "to make a happy life; it is all within yourself, in your way of thinking.”\n\n" +
                "”If it is not right do not do it; if it is not true do not say it.”",
            NoteColors.YELLOW,
            lastEditedAt = "Edited yesterday, 23:59"
        )
        private val seneca = Note(
            id = uuid(),
            title = "Seneca",
            body = "”If a man knows not to which port he sails, no wind is favorable.”\n\n”We " +
                "suffer more often in imagination than in reality.”\n\n”They lose the day in " +
                "expectation of the night, and the night in fear of the dawn.”",
            NoteColors.BLUE,
            lastEditedAt = "Edited today, 13:26"
        )

        val sample = marcusAurelius
        val samples = listOf(epictetus, marcusAurelius, seneca).let { notes ->
            notes.plus(notes.map { note -> note.copy(id = uuid()) })
        }
    }
}

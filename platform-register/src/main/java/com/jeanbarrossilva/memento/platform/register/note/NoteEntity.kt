package com.jeanbarrossilva.memento.platform.register.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeanbarrossilva.memento.core.register.domain.Color
import com.jeanbarrossilva.memento.core.register.domain.Note

@Entity(tableName = "notes")
internal data class NoteEntity(
    @PrimaryKey val id: String,
    val title: String,
    val body: String,
    @ColumnInfo(name = "color_id") val colorID: String
) {
    fun toNote(): Note {
        val color = Color.values().first { it.id == colorID }
        return Note(id, title, body, color)
    }
}

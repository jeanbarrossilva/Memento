package com.jeanbarrossilva.memento.platform.register.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeanbarrossilva.memento.core.register.domain.Color
import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.domain.Path

@Entity(tableName = "notes")
internal data class NoteEntity(
    @PrimaryKey val id: String,
    @ColumnInfo("path_value") val pathValue: String,
    val title: String,
    val body: String,
    @ColumnInfo("color_id") val colorID: String
) {
    fun toNote(): Note {
        val color = Color.values().first { it.id == colorID }
        val path = Path to pathValue
        return Note(id, path, title, body, color)
    }
}

package com.jeanbarrossilva.memento.platform.register.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeanbarrossilva.memento.core.register.domain.Color
import com.jeanbarrossilva.memento.core.register.domain.Folder
import com.jeanbarrossilva.memento.core.register.domain.Note

@Entity(tableName = "notes")
internal data class NoteEntity(
    @PrimaryKey val id: String,
    @ColumnInfo("folder_path") val folderPath: String?,
    val title: String,
    val body: String,
    @ColumnInfo("color_id") val colorID: String
) {
    fun toNote(): Note {
        val color = Color.values().first { it.id == colorID }
        val folder = folderPath?.let(Folder::at)
        return Note(id, folder, title, body, color)
    }
}

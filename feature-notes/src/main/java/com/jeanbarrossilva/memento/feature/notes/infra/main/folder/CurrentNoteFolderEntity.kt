package com.jeanbarrossilva.memento.feature.notes.infra.main.folder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeanbarrossilva.memento.core.register.domain.Path
import com.jeanbarrossilva.memento.feature.notes.domain.note.NoteFolder

@Entity(tableName = "current_folders")
internal data class CurrentNoteFolderEntity(
    @PrimaryKey @ColumnInfo("path_value") val pathValue: String
) {
    fun toNoteFolder(): NoteFolder {
        val title = Path.decode(pathValue)
        return NoteFolder(pathValue, title)
    }
}

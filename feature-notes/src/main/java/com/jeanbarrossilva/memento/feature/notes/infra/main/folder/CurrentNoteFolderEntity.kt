package com.jeanbarrossilva.memento.feature.notes.infra.main.folder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeanbarrossilva.memento.core.register.domain.Folder
import com.jeanbarrossilva.memento.feature.notes.domain.note.NoteFolder
import com.jeanbarrossilva.memento.feature.notes.utils.toNoteFolder

@Entity(tableName = "current_folders")
internal data class CurrentNoteFolderEntity(
    @PrimaryKey @ColumnInfo("path") val path: String
) {
    fun toNoteFolder(): NoteFolder {
        return Folder.at(path).toNoteFolder()
    }
}

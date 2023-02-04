package com.jeanbarrossilva.memento.feature.notes.infra.main.folder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeanbarrossilva.memento.core.register.domain.Folder
import com.jeanbarrossilva.memento.feature.notes.domain.note.Folder as _Folder

@Entity(tableName = "current_folders")
internal data class CurrentNoteFolderEntity(
    @PrimaryKey @ColumnInfo("path") val path: String
) {
    fun toFolder(): _Folder {
        val title = Folder.at(path).title
        return _Folder(path, title)
    }
}

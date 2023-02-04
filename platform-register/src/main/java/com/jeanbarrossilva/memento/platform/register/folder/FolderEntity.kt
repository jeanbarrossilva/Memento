package com.jeanbarrossilva.memento.platform.register.folder

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

internal data class FolderEntity(
    @PrimaryKey val path: String,
    @ColumnInfo("note_ids") val noteIds: List<String>
)

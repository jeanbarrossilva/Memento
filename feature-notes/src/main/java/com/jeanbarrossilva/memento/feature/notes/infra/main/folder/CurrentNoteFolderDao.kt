package com.jeanbarrossilva.memento.feature.notes.infra.main.folder

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface CurrentNoteFolderDao {
    @Query("SELECT * FROM current_folders")
    suspend fun select(): CurrentNoteFolderEntity?

    @Insert
    suspend fun insert(entity: CurrentNoteFolderEntity)

    @Query("DELETE FROM current_folders WHERE path_value = :pathValue")
    suspend fun delete(pathValue: String)
}

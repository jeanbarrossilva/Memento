package com.jeanbarrossilva.memento.feature.notes.infra.main.folder

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface CurrentNoteFolderDao {
    @Query("SELECT * FROM folders")
    suspend fun select(): NoteFolderEntity?

    @Insert
    suspend fun insert(entity: NoteFolderEntity)

    @Query("DELETE FROM folders WHERE path_value = :pathValue")
    suspend fun delete(pathValue: String)
}

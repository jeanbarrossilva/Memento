package com.jeanbarrossilva.memento.feature.notes.infra.main.folder

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CurrentNoteFolderDao {
    @Query("SELECT * FROM current_folders")
    fun select(): Flow<CurrentNoteFolderEntity?>

    @Insert
    suspend fun insert(entity: CurrentNoteFolderEntity)

    @Query("DELETE FROM current_folders WHERE path_value = :pathValue")
    suspend fun delete(pathValue: String)
}

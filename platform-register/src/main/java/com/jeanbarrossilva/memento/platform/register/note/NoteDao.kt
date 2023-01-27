package com.jeanbarrossilva.memento.platform.register.note

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface NoteDao {
    @Query("SELECT * FROM notes")
    suspend fun selectAll(): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE id = :entityID")
    suspend fun selectByID(entityID: String): NoteEntity?

    @Insert
    suspend fun insert(entity: NoteEntity)

    @Query("UPDATE notes SET title = :title WHERE id = :entityID")
    suspend fun updateTitle(entityID: String, title: String)

    @Query("UPDATE notes SET body = :body WHERE id = :entityID")
    suspend fun updateBody(entityID: String, body: String)

    @Query("UPDATE notes SET color_id = :colorID WHERE id = :entityID")
    suspend fun updateColorID(entityID: String, colorID: String)

    @Query("DELETE FROM notes WHERE id = :entityID")
    suspend fun delete(entityID: String)

    @Query("DELETE FROM notes")
    suspend fun deleteAll()
}

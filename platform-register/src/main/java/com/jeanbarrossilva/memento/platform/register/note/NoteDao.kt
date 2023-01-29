package com.jeanbarrossilva.memento.platform.register.note

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
abstract class NoteDao {
    @Query("SELECT * FROM notes")
    internal abstract suspend fun selectAll(): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE id = :entityID")
    internal abstract suspend fun selectByID(entityID: String): NoteEntity?

    @Insert
    internal abstract suspend fun insert(entity: NoteEntity)

    @Query("UPDATE notes SET title = :title WHERE id = :entityID")
    internal abstract suspend fun updateTitle(entityID: String, title: String)

    @Query("UPDATE notes SET body = :body WHERE id = :entityID")
    internal abstract suspend fun updateBody(entityID: String, body: String)

    @Query("UPDATE notes SET color_id = :colorID WHERE id = :entityID")
    internal abstract suspend fun updateColorID(entityID: String, colorID: String)

    @Query("DELETE FROM notes WHERE id = :entityID")
    internal abstract suspend fun delete(entityID: String)

    @Query("DELETE FROM notes")
    internal abstract suspend fun deleteAll()
}

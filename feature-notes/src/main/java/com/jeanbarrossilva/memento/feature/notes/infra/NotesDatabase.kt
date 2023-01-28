package com.jeanbarrossilva.memento.feature.notes.infra

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jeanbarrossilva.memento.feature.notes.infra.main.folder.CurrentNoteFolderDao
import com.jeanbarrossilva.memento.feature.notes.infra.main.folder.CurrentNoteFolderEntity

@Database(entities = [CurrentNoteFolderEntity::class], version = 1)
internal abstract class NotesDatabase : RoomDatabase() {
    abstract val dao: CurrentNoteFolderDao

    companion object {
        private lateinit var instance: NotesDatabase

        fun getInstance(context: Context): NotesDatabase {
            return if (::instance.isInitialized) {
                instance
            } else {
                instance =
                    Room.databaseBuilder(context, NotesDatabase::class.java, "notes-db").build()
                instance
            }
        }
    }
}

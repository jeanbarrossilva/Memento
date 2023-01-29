package com.jeanbarrossilva.memento.platform.register

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jeanbarrossilva.memento.platform.register.note.NoteDao
import com.jeanbarrossilva.memento.platform.register.note.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class RegisterDatabase internal constructor() : RoomDatabase() {
    abstract val dao: NoteDao

    companion object {
        private lateinit var instance: RegisterDatabase

        internal fun getInstance(context: Context): RegisterDatabase {
            return if (this::instance.isInitialized) {
                instance
            } else {
                instance = createInstance(context)
                instance
            }
        }

        private fun createInstance(context: Context): RegisterDatabase {
            return Room
                .databaseBuilder(context, RegisterDatabase::class.java, "memento-db")
                .build()
        }
    }
}

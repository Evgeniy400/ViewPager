package com.example.viewpager.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.viewpager.model.Note
import java.util.concurrent.Flow


@Dao
interface NoteDao {
    @Query("SELECT * FROM notes WHERE id == :index")
    suspend fun getNoteByIndex(index: Long): Note

    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<Note>

    @Insert
    suspend fun addNote(note: Note)

}
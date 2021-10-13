package com.example.viewpager.model

import com.example.viewpager.database.AppDatabase

class MainModel(
    private val db: AppDatabase
) {
    private suspend fun addNote(note: Note) {
        db.noteDao().addNote(note)
    }

    suspend fun addNote(title: String, text: String) {
        addNote(Note(title, text))
    }

    suspend fun getAllNotes() = db.noteDao().getAll()

}
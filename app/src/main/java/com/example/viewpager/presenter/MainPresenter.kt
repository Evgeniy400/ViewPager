package com.example.viewpager.presenter

import com.example.viewpager.database.AppDatabase
import com.example.viewpager.model.Note
import com.example.viewpager.view.IMainView

class MainPresenter(
    private var view: IMainView,
    private var db: AppDatabase
) {

    private suspend fun addNote(note: Note) {
        db.noteDao().addNote(note)
    }

    suspend fun addNote(title: String, text: String) {
        addNote(Note(title, text))
    }

    suspend fun getAllNotes() = db.noteDao().getAll()


}
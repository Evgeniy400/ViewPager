package com.example.viewpager.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.viewpager.database.AppDatabase
import com.example.viewpager.view.MainActivity
import com.example.viewpager.model.MainModel
import com.example.viewpager.model.Note
import kotlinx.coroutines.launch

class MainPresenter(var view: MainActivity) {
//    private var model =
//        MainModel(arrayListOf(Note("0", "00"), Note("1", "11"), Note("2", "22"), Note("3", "33")))

    private var db = AppDatabase.getDatabase(view)


    suspend fun addNote(note: Note) {
        db.noteDao().addNote(note)
    }


    suspend fun addNote(title: String, text: String) {
        addNote(Note(title, text))
    }

    suspend fun itemCount() = db.noteDao().getAll().size

    suspend fun getNote(index: Int) = db.noteDao().getNoteByIndex(index.toLong())

    suspend fun getAllNotes() = db.noteDao().getAll()

}
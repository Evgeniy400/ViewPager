package com.example.viewpager.presenter

import android.content.Intent
import com.example.viewpager.database.AppDatabase
import com.example.viewpager.model.MainModel
import com.example.viewpager.model.Note
import com.example.viewpager.view.INoteFragment

class NotePresenter(
    private var view: INoteFragment,
    db: AppDatabase
) {
    private val model = MainModel(db)
    fun shareNote(note: Note) {
        view.startShare(note)
    }

    fun initView(note: Note) {
        view.setNote(note)
    }

    fun onInfoBtnClick() {
        view.startDialogAbout()
    }

    suspend fun getNoteByIndex(index: Int) = model.getAllNotes()[index]

    suspend fun addNoteActivityResult(data: Intent?) {
        val title = data?.getStringExtra("Title").toString()
        val text = data?.getStringExtra("Text").toString()
        if (title != "null" && text != "null") {
            model.addNote(
                data?.getStringExtra("Title").toString(),
                data?.getStringExtra("Text").toString()
            )
            view.synchronizeAdapter()
        }
    }

}
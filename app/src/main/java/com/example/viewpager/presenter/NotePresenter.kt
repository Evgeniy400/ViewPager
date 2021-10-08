package com.example.viewpager.presenter

import com.example.viewpager.model.Note
import com.example.viewpager.view.INoteFragment

class NotePresenter(
    private var view: INoteFragment,
) {
    fun shareNote(title: String, text: String) {
        view.startShare(Note(title, text))
    }

}
package com.example.viewpager.view

import com.example.viewpager.model.Note

interface INoteFragment {
    fun startShare(note: Note)
    fun setNote(note: Note)
    fun startDialogAbout()
    fun synchronizeAdapter()
}
package com.example.viewpager.presenter

import android.content.Intent
import com.example.viewpager.database.AppDatabase
import com.example.viewpager.model.MainModel
import com.example.viewpager.model.Note
import com.example.viewpager.view.IMainView

class MainPresenter(
    private var view: IMainView,
    db: AppDatabase
) {
    private val model = MainModel(db)

    suspend fun getAllNotes() = model.getAllNotes()

    fun initAdapter() {
        view.updateAdapter()
    }
}
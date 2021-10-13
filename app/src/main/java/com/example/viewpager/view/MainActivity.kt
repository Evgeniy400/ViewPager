package com.example.viewpager.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.view.get
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager.R
import com.example.viewpager.adapter.NotePagerAdapter
import com.example.viewpager.database.AppDatabase
import com.example.viewpager.fragment.ShowNoteFragment
import com.example.viewpager.model.Note
import com.example.viewpager.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : FragmentActivity(), IMainView {
    private lateinit var presenter: MainPresenter
    private lateinit var viewPager: ViewPager2
    private var adapter = NotePagerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter(this, AppDatabase.getDatabase(this))
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager2)

        lifecycleScope.launch {
            viewPager.let {
                presenter.initAdapter()
                it.adapter = adapter
            }
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        if (viewPager.currentItem == 0)
            super.onBackPressed()
        else
            viewPager.currentItem -= 1
    }

    override fun updateAdapter() {
        lifecycleScope.launch {
            adapter.notesCount = presenter.getAllNotes().size
        }
    }
}
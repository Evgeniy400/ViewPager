package com.example.viewpager.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager.R
import com.example.viewpager.fragment.MyDialogFragment
import com.example.viewpager.fragment.ShowNoteFragment
import com.example.viewpager.model.Note
import com.example.viewpager.presenter.MainPresenter
import kotlinx.coroutines.launch
import java.sql.Time

class MainActivity : FragmentActivity() {
    private lateinit var presenter: MainPresenter
    private lateinit var viewPager: ViewPager2
    private var adapter = Adapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter(this)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager2)

        lifecycleScope.launch{
            viewPager.let {
                adapter.setNotes(presenter.getAllNotes() as ArrayList<Note>)
                it.adapter = adapter
            }
        }



        findViewById<ImageButton>(R.id.imageButtonAbout).setOnClickListener {
            MyDialogFragment().show(supportFragmentManager, null)
        }

        findViewById<ImageButton>(R.id.imageButtonAdd).setOnClickListener {
            startActivityForResult(Intent(this, AddNote::class.java), 1)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        lifecycleScope.launch {
            presenter.addNote(
                data?.getStringExtra("Title").toString(),
                data?.getStringExtra("Text").toString()
            )
            adapter.setNotes(presenter.getAllNotes() as ArrayList<Note>)


        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        if (viewPager.currentItem == 0)
            super.onBackPressed()
        else
            viewPager.currentItem -= 1
    }

    private inner class Adapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        private var notes = ArrayList<Note>()

        fun setNotes(list: ArrayList<Note>){
            notes = list
        }


        override fun getItemCount(): Int = notes.size

        override fun createFragment(position: Int): Fragment {
            val frag = ShowNoteFragment()
            frag.arguments = Bundle().apply {
                putString("Title", notes[position].title)
                putString("Text", notes[position].text)
                putString("Time", notes[position].time)
            }
            return frag
        }

    }
}
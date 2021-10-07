package com.example.viewpager.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fragment.MyDialogFragment
import fragment.ShowNoteFragment
import model.Note
import presenter.MainPresenter

class MainActivity : FragmentActivity() {
    private lateinit var presenter: MainPresenter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter(this)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager2)
        viewPager.adapter = Adapter(this)

        findViewById<ImageButton>(R.id.imageButtonAbout).setOnClickListener{
            MyDialogFragment().show(supportFragmentManager, null)
        }

        findViewById<ImageButton>(R.id.imageButtonAdd).setOnClickListener{
            startActivityForResult(Intent(this, AddNote::class.java), 1)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.addNote(data?.getStringExtra("Title").toString(), data?.getStringExtra("Text").toString())
    }

    private fun startAbout() {
        val about = Intent(this, AboutActivity::class.java)
        startActivity(about)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        if(viewPager.currentItem == 0)
            super.onBackPressed()
        else
            viewPager. currentItem -= 1
    }

    private inner class Adapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = presenter.itemCount()
        override fun createFragment(position: Int): Fragment = ShowNoteFragment(presenter.getNote(position))

    }
}
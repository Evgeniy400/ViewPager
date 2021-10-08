package com.example.viewpager.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.viewpager.fragment.ShowNoteFragment
import com.example.viewpager.model.Note

class NotePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private var notes = ArrayList<Note>()

    fun get() = notes
    fun setNotes(list: ArrayList<Note>) {
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
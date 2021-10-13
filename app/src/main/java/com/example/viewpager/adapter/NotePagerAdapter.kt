package com.example.viewpager.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.viewpager.fragment.ShowNoteFragment

class NotePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    var notesCount = 0

    override fun getItemCount(): Int = notesCount

    override fun createFragment(position: Int): Fragment = ShowNoteFragment()
}
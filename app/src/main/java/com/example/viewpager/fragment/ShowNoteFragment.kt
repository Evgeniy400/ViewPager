package com.example.viewpager.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.viewpager.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.viewpager.model.Note
import java.lang.reflect.Array.getInt

class ShowNoteFragment() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey("Title") && it.containsKey("Time") && it.containsKey("Text") }
            ?.apply {
                view.findViewById<TextView>(R.id.NoteTitle).text = getString("Title")
                view.findViewById<TextView>(R.id.NoteDate).text = getString("Time")
                view.findViewById<TextView>(R.id.NoteText).text = getString("Text")
            }
        activity?.findViewById<FloatingActionButton>(R.id.floatingActionButton)
            ?.setOnClickListener {
                startActivity(Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "${arguments?.getString("Title")}\n${arguments?.getString("Text")}"
                    )
                })

            }

    }
}
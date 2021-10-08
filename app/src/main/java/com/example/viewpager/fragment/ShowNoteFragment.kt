package com.example.viewpager.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.example.viewpager.R
import com.example.viewpager.model.Note
import com.example.viewpager.presenter.NotePresenter
import com.example.viewpager.view.AddNote
import com.example.viewpager.view.INoteFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShowNoteFragment() : Fragment(), INoteFragment {
    private lateinit var presenter: NotePresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = NotePresenter(this)
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
                presenter.shareNote(
                    arguments?.getString("Title") ?: "",
                    arguments?.getString("Text") ?: ""
                )
            }

        activity?.findViewById<AppCompatImageView>(R.id.imageButtonAbout)?.setOnClickListener {
            activity?.supportFragmentManager?.let { it1 -> DialogAboutFragment().show(it1, null) }
        }

        activity?.findViewById<AppCompatImageView>(R.id.imageButtonAdd)?.setOnClickListener {
            startActivityForResult(Intent(activity, AddNote::class.java), 1)
        }

    }

    override fun startShare(note: Note) {
        startActivity(Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT,
                "${note.title}\n${note.text}"
            )
        })
    }
}


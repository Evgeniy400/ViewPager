package com.example.viewpager.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager.R
import com.example.viewpager.adapter.NotePagerAdapter
import com.example.viewpager.database.AppDatabase
import com.example.viewpager.model.Note
import com.example.viewpager.presenter.NotePresenter
import com.example.viewpager.view.AddNoteActivity
import com.example.viewpager.view.INoteFragment
import com.example.viewpager.view.MainActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_show_note.*
import kotlinx.coroutines.launch

class ShowNoteFragment() : Fragment(), INoteFragment {
    private lateinit var presenter: NotePresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = NotePresenter(this, AppDatabase.getDatabase(requireActivity()))
        return inflater.inflate(R.layout.fragment_show_note, container, false)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().lifecycleScope.launch {
            val note =
                presenter.getNoteByIndex(requireActivity().findViewById<ViewPager2>(R.id.viewPager2).currentItem)
            presenter.initView(note)

            activity?.findViewById<FloatingActionButton>(R.id.floatingActionButton)
                ?.setOnClickListener {
                    presenter.shareNote(note)
                }
        }

        activity?.findViewById<AppCompatImageView>(R.id.imageButtonAbout)
            ?.setOnClickListener {
                presenter.onInfoBtnClick()
            }

        activity?.findViewById<AppCompatImageView>(R.id.imageButtonAdd)
            ?.setOnClickListener {
                startActivityForResult(Intent(activity, AddNoteActivity::class.java), 1)
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

    override fun setNote(note: Note) {
        requireActivity().findViewById<TextView>(R.id.NoteTitle).text = note.title
        requireActivity().findViewById<TextView>(R.id.NoteDate).text = note.time
        requireActivity().findViewById<TextView>(R.id.NoteText).text = note.text

    }

    override fun startDialogAbout() {
        activity?.supportFragmentManager?.let { it1 -> DialogAboutFragment().show(it1, null) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        lifecycleScope.launch {
            presenter.addNoteActivityResult(data)
        }
    }

    override fun synchronizeAdapter() {
        (requireActivity() as MainActivity).updateAdapter()
    }
}


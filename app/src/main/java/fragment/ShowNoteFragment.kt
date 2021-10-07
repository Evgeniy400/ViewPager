package fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.viewpager.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import model.Note

class ShowNoteFragment(var note: Note) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_note, container, false)
    }

    override fun onStart() {
        super.onStart()
        activity?.findViewById<FloatingActionButton>(R.id.floatingActionButton)?.setOnClickListener {
            startActivity(Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "${note.title}\n${note.text}")
            })
        }
        activity?.findViewById<TextView>(R.id.NoteTitle)?.text = note.title
        activity?.findViewById<TextView>(R.id.NoteDate)?.text = note.time.toString()
        activity?.findViewById<TextView>(R.id.NoteText)?.text = note.text
    }
}
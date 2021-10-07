package presenter

import com.example.viewpager.view.MainActivity
import model.MainModel
import model.Note
import java.util.*
import kotlin.collections.ArrayList

class MainPresenter(var view: MainActivity) {
    private var model = MainModel(arrayListOf(Note("1", "11"), Note("2", "22"), Note("3", "33")))
//    private lateinit var view: MainActivity

    fun addNote(note:Note) {
        model.notes.add(note)
    }

    fun addNote(title: String, text: String){
        addNote(Note(title, text))
    }

    fun itemCount() = model.notes.size

    fun getNote(index: Int) = model.notes[index]

}
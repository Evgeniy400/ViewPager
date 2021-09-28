package presenter

import com.example.viewpager.view.MainActivity
import model.MainModel
import model.Note

class MainPresenter {
    private var model = emptyList<Note>()
    private lateinit var view: MainActivity

}
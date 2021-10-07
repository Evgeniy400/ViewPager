package com.example.viewpager.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.viewpager.view.AboutActivity
import java.lang.IllegalStateException

class MyDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle("Информация")
                .setMessage("Вы ТОЧНО хотите узнать это?")
                .setNegativeButton("Что-то перехотелось...", null)
                .setPositiveButton("Да.") { dialog, _ ->
                    dialog.cancel()
                    startActivity(
                        Intent(
                            it,
                            AboutActivity::class.java
                        )
                    )

                }
                .create()

        } ?: throw IllegalStateException("Activity не может быть null")
    }
}
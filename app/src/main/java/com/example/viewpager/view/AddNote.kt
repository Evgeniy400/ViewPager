package com.example.viewpager.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.viewpager.R

class AddNote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_note)

        findViewById<Button>(R.id.button).setOnClickListener(){
            var intent = Intent()
            intent.putExtra("Title", findViewById<EditText>(R.id.editTextTitle).text.toString())
            intent.putExtra("Text", findViewById<EditText>(R.id.editTextText).text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
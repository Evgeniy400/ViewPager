package com.example.viewpager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewpager.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShowNote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_note)

        findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener() {


        }
    }


}
package com.example.viewpager.view

import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.viewpager.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageButton>(R.id.imageButton).setOnClickListener(

        ) {
            startAbout()
        }


    }

    private fun startAbout() {
        val about = Intent(this, AboutActivity::class.java)
        startActivity(about)
    }

    private fun viewPager(){
        val vPager = Intent(this, ViewPager::class.java)
        startActivity(vPager)
    }
}
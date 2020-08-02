package com.example.androidassessment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidassessment.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        launchFragment()
    }

    private fun launchFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, ListFragment())
                .commit()
    }
}
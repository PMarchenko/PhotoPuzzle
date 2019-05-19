package com.pmarchenko.photopuzzle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.pmarchenko.photopuzzle.newpuzzle.NewPuzzleActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.fab).setOnClickListener { createNewPuzzle() }
    }

    private fun createNewPuzzle() {
        NewPuzzleActivity.start(this)
    }
}

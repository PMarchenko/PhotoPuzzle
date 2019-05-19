package com.pmarchenko.photopuzzle.newpuzzle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pmarchenko.photopuzzle.R

/**
 * @author Pavel Marchenko (Pavel.Marchenko@datart.com -- DataArt)
 */
class NewPuzzleActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, NewPuzzleActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_puzzle)
        val puzzleField = findViewById<PuzzleFieldView>(R.id.puzzle_field)
        findViewById<View>(R.id.new_row).setOnClickListener { puzzleField.setFieldSize(puzzleField.rowsCount + 1, puzzleField.columnsCount) }
        findViewById<View>(R.id.new_column).setOnClickListener { puzzleField.setFieldSize(puzzleField.rowsCount, puzzleField.columnsCount + 1) }
    }
}
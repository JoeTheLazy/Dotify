package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        assignRandomPlays()
    }

    private fun assignRandomPlays() {
        val playsText = findViewById<TextView>(R.id.plays_text)
        val randomNum = Random.nextInt(0, 10000)
        playsText.text = "$randomNum plays"
    }

    fun changeNameClicked() {

    }

    fun applyClicked() {

    }

    fun prevClicked(view: View) {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    fun playClicked(view: View) {
        val playsText = findViewById<TextView>(R.id.plays_text)
        val numPlaysString = playsText.text.toString().split(" ")[0]
        val numPlays = numPlaysString.toInt() + 1
        playsText.text = "$numPlays plays"
    }

    fun nextClicked(view: View) {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

}

package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        assignRandomPlays()

        prev_button.setOnClickListener {
            showMessage("Skipping to previous track")
        }

        play_button.setOnClickListener {
            playClicked()
        }

        next_button.setOnClickListener {
            showMessage("Skipping to next track")
        }
    }

    // Changes the # of plays text for the current song to some random value between 0-10000.
    private fun assignRandomPlays() {
        val playsText = findViewById<TextView>(R.id.plays_text)
        val randomNum = Random.nextInt(0, 10000)
        playsText.text = "$randomNum plays"
    }

    // Increments the # of plays text for the current song.
    private fun playClicked() {
        val playsText = findViewById<TextView>(R.id.plays_text)
        val numPlaysString = playsText.text.toString().split(" ")[0]
        val numPlays = numPlaysString.toInt() + 1
        playsText.text = "$numPlays plays"
    }

    // Briefly displays at bottom given String message.
    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

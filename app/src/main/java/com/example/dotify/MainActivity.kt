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

        username_button.setOnClickListener {
            changeNameClicked()
        }

        prev_button.setOnClickListener {
            prevClicked()
        }

        play_button.setOnClickListener {
            playClicked()
        }

        next_button.setOnClickListener {
            nextClicked()
        }
    }

    private fun assignRandomPlays() {
        val playsText = findViewById<TextView>(R.id.plays_text)
        val randomNum = Random.nextInt(0, 10000)
        playsText.text = "$randomNum plays"
    }

    private fun changeNameClicked() {
        val changeButton = findViewById<Button>(R.id.username_button)
        val userText = findViewById<TextView>(R.id.username_text)
        val editText = findViewById<EditText>(R.id.username_edit)

        userText.visibility = View.GONE
        editText.visibility = View.VISIBLE

        changeButton.apply {
            text = "Apply"
            setOnClickListener {
                applyClicked(changeButton, userText, editText)
            }
        }
    }

    private fun applyClicked(changeButton: Button, userTextView: TextView, userEditText: EditText) {

        userTextView.visibility = View.VISIBLE
        userEditText.visibility = View.GONE

        val newUserName = userEditText.text.toString()
        userEditText.setText("")

        userTextView.text = newUserName

        changeButton.setOnClickListener {
            changeNameClicked()
        }
    }

    private fun prevClicked() {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    private fun playClicked() {
        val playsText = findViewById<TextView>(R.id.plays_text)
        val numPlaysString = playsText.text.toString().split(" ")[0]
        val numPlays = numPlaysString.toInt() + 1
        playsText.text = "$numPlays plays"
    }

    private fun nextClicked() {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

}

package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_song_list.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var playsText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playsText = findViewById(R.id.plays_text)

        val song = intent.getParcelableExtra<Song>(SONG_KEY)

        album_art.setImageResource(song.largeImageID)
        song_text.text = song.title
        author_text.text = song.artist

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

    companion object {
        const val SONG_KEY = "SONG_KEY"
    }
}

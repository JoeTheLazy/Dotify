package com.example.dotify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class NowPlayingFragment: Fragment() {

    private var playsText = plays_text
    private var song: Song? = null
    private var numPlays = 0

    companion object {
        val TAG: String = NowPlayingFragment::class.java.simpleName
        const val STATE_PLAYS = "numberOfPlays"
        private const val SONG_KEY = "ARG_SONG"

        fun getInstance(song: Song) = NowPlayingFragment().apply {
            arguments = Bundle().apply {
                putParcelable(SONG_KEY, song)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            with(savedInstanceState) {
                numPlays = getInt(STATE_PLAYS)
            }
            playsText.text = "$numPlays plays"
        } else {
            assignRandomPlays()
        }

        arguments?.let { args ->
            val givenSong: Song? = args.getParcelable(SONG_KEY)
            if (givenSong != null) {
                this.song = givenSong
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateSongState(song)

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

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            putInt(STATE_PLAYS, numPlays)
        }
        super.onSaveInstanceState(outState)
    }

    fun updateSong(song: Song) {
        album_art.setImageResource(song.largeImageID)
        song_text.text = song.title
        author_text.text = song.artist
    }

    private fun updateSongState(song: Song?) {
        if (song != null) {
            this.song = song
            updateSong(song)
        }
    }

    // Changes the # of plays text for the current song to some random value between 0-10000.
    private fun assignRandomPlays() {
        val randomNum = Random.nextInt(0, 10000)
        playsText.text = "$randomNum plays"

        numPlays = randomNum
    }

    // Increments the # of plays text for the current song.
    private fun playClicked() {
        numPlays++
        playsText.text = "$numPlays plays"
    }

    // Briefly displays at bottom given String message.
    private fun showMessage(message: String) {
        Toast.makeText(context?.applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}
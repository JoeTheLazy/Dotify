package com.example.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.MainActivity.Companion.SONG_KEY
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListActivity : AppCompatActivity() {

    private lateinit var selectedSongView: TextView
    private lateinit var songList: List<Song>
    private lateinit var songAdapter: SongAdapter
    private var currentSong: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        this.title = "All Songs"

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        selectedSongView = findViewById(R.id.song_text_preview)

        songList = SongDataProvider.getAllSongs()
        songAdapter = SongAdapter(songList)

        rvSongs.adapter = songAdapter

        songAdapter.onSongClickListener = { song: Song ->
            selectSong(song)
        }

        shuffle_button.setOnClickListener {
            shuffleSongs()
        }

        bottom_bar.setOnClickListener {
            if (currentSong != null) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(SONG_KEY, currentSong)

                startActivity(intent)
            }
        }
    }

    private fun shuffleSongs() {
        var newList = songList.shuffled()
        songAdapter.change(newList)
    }

    private fun selectSong(song: Song) {
        selectedSongView.text = "${song.title} - ${song.artist}"
        currentSong = song
    }
}

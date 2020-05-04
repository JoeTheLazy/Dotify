package com.example.dotify

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity_ultimate_main.*

class UltimateMainActivity : AppCompatActivity(), OnSongClickListener {

    private lateinit var songList: List<Song>
    private var currentSong: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ultimate_main)

        songList = SongDataProvider.getAllSongs()

        if (savedInstanceState != null) {
            with(savedInstanceState) {
                currentSong = getParcelable(STATE_SONG)
            }
            onSongClicked(currentSong!!)
        }

        if (supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) == null) {
            val songListFragment = SongListFragment.getInstance(songList)

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, songListFragment, SongListFragment.TAG)
                .addToBackStack(SongListFragment.TAG)
                .commit()
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val hasBackStack = supportFragmentManager.backStackEntryCount > 1

            if (hasBackStack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }

        main_shuffle_button.setOnClickListener {
           selectSong()
        }

        main_bottom_bar.setOnClickListener {
            onBottomBarClicked()
        }
    }

    private fun selectSong() {
        val songListFragment = getListFragment()
        songListFragment?.shuffleList()
    }

    override fun onSupportNavigateUp(): Boolean {
        main_bottom_bar.isVisible = true
        supportFragmentManager.popBackStack()
        return super.onNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            putParcelable(STATE_SONG, currentSong)
        }

        super.onSaveInstanceState(outState)
    }

    override fun onSongClicked(song: Song) {
        main_song_text_preview.text = "${song.title} - ${song.artist}"
        currentSong = song
    }

    private fun onBottomBarClicked() {
        var nowFragment = getNowFragment()

        if (nowFragment == null) {
            if (currentSong != null) {
                main_bottom_bar.isVisible = false
                nowFragment = NowPlayingFragment.getInstance(currentSong!!)
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragContainer, nowFragment, NowPlayingFragment.TAG)
                    .addToBackStack(NowPlayingFragment.TAG)
                    .commit()
            }
        }
    }

    private fun getListFragment() = supportFragmentManager.findFragmentByTag(SongListFragment.TAG) as? SongListFragment
    private fun getNowFragment() = supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) as? NowPlayingFragment

    companion object {
        const val STATE_SONG = "savedSong"
    }

}
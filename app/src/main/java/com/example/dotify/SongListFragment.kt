package com.example.dotify

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.fragment_song_list.*

class SongListFragment: Fragment() {
    private lateinit var songAdapter: SongAdapter
    private var onSongClickListener: OnSongClickListener? = null
    private lateinit var songList: MutableList<Song>

    companion object {
        val TAG: String = SongListFragment::class.java.simpleName
        private const val LIST_KEY = "LIST_KEY"

        fun getInstance(songList: List<Song>): SongListFragment {
            return SongListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(LIST_KEY, ArrayList(songList))
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnSongClickListener) {
            onSongClickListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getParcelableArrayList<Song>(LIST_KEY)?.let { savedSongList ->
            this.songList = savedSongList
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.activity_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songAdapter = SongAdapter(songList)
        frag_rvSongs.adapter = songAdapter

        songAdapter.onSongClickListener = { song ->
            onSongClickListener?.onSongClicked(song)
        }
    }

    fun shuffleSongs() {
        var newList = songList.shuffled()
        songAdapter.change(newList)
    }
}
package com.example.dotify

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song


class SongAdapter(private val initialSongList: List<Song>): RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private var songList: List<Song> = initialSongList
    var onSongClickListener: ((song: Song) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)

        return SongViewHolder(view)
    }

    override fun getItemCount() = songList.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songList[position]
        holder.bind(song, position)
    }

    fun change(newSongs: List<Song>) {
        songList = newSongs
        notifyDataSetChanged()
    }

    inner class SongViewHolder(songView: View): RecyclerView.ViewHolder(songView) {
        private val songImageView = songView.findViewById<ImageView>(R.id.item_song_icon)
        private val songTitleView = songView.findViewById<TextView>(R.id.item_song_title)
        private val songArtistView = songView.findViewById<TextView>(R.id.item_song_artist)
        // Redundant variable here so that bind() has access to songView passed in by inner class.
        // For some reason, bind() can't recognize songView without me doing it this way.
        private val view = songView

        fun bind(song: Song, position: Int) {
            songImageView.setImageResource(song.smallImageID)
            songTitleView.text = song.title
            songArtistView.text = song.artist

            view.setOnClickListener {
                onSongClickListener?.invoke(song)
            }
        }
    }

}


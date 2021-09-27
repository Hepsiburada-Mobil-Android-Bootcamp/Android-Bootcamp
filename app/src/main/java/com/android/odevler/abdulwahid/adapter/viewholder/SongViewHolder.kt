package com.android.odevler.abdulwahid.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.abdulwahid.data.Song

class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvSongName by lazy { itemView.findViewById<TextView>(R.id.tv_item_song_name) }
    private val tvArtistName by lazy { itemView.findViewById<TextView>(R.id.tv_item_artist_name) }
    private val tvDuration by lazy { itemView.findViewById<TextView>(R.id.tv_item_duration) }
    private val tvYear by lazy { itemView.findViewById<TextView>(R.id.tv_item_year) }

    fun bind(song: Song) {
        tvSongName.text = song.name
        tvArtistName.text = song.artist
        tvDuration.text = song.duration.toString()
        tvYear.text = song.year.toString()
    }

}
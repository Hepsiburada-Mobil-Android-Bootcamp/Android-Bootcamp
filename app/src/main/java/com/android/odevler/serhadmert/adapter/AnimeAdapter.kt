package com.android.odevler.serhadmert.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.serhadmert.data.Anime
import java.util.ArrayList

class AnimeAdapter(private val context:Context,private val anime:ArrayList<Anime>) :RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    inner class AnimeViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private val name by lazy { itemView.findViewById<TextView>(R.id.anime_name) }
        private val type by lazy { itemView.findViewById<TextView>(R.id.anime_type) }
        private val malPoint by lazy { itemView.findViewById<TextView>(R.id.anime_malPoint) }
        private val releaseDate by lazy { itemView.findViewById<TextView>(R.id.anime_releaseDate) }

        fun bind(anime: Anime){
            name.text=anime.name
            type.text=anime.type
            malPoint.text= anime.malPoint.toString()
            releaseDate.text= anime.releaseDate

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= AnimeViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_anime,parent,false)
    )

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(anime[position])
    }

    override fun getItemCount()=anime.size

}
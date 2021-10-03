package com.android.odevler.cagrikilic

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.cagrikilic.data.Paper

class PaperListAdapter(private val list:ArrayList<Paper>) : RecyclerView.Adapter<PaperListAdapter.PaperViewHolder>() {

    class PaperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewName by lazy { itemView.findViewById<TextView>(R.id.text_view_name) }
        private val textViewColor by lazy { itemView.findViewById<TextView>(R.id.text_view_color) }
        private val textViewWidthAndHeight by lazy { itemView.findViewById<TextView>(R.id.text_view_width_height)}

        @SuppressLint("SetTextI18n")
        fun bind(paper: Paper){
            textViewName.text = paper.name
            textViewColor.text =paper.color
            textViewWidthAndHeight.text= paper.width.toString() + "x" + paper.height.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaperViewHolder {
       return PaperViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.item_paper,parent,false))
    }

    override fun onBindViewHolder(holder: PaperViewHolder, position: Int) {
      holder.bind(list[position])
    }

    override fun getItemCount(): Int {
      return list.size
    }

}
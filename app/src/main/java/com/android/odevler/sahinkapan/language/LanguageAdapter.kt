package com.android.odevler.sahinkapan.language

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.databinding.ItemLanguageBinding
import com.android.odevler.mehmet_serkan_guzel.data.Language

class LanguageAdapter(context: Context, val languageList:ArrayList<Language>) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    class LanguageViewHolder(private val languageBinding:ItemLanguageBinding):RecyclerView.ViewHolder(languageBinding.root){

        fun bind(language:Language){
            languageBinding.language=language
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageAdapter.LanguageViewHolder {
        return LanguageViewHolder(ItemLanguageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: LanguageAdapter.LanguageViewHolder, position: Int) {
        holder.bind(languageList[position])
    }

    override fun getItemCount(): Int {
        return languageList.size
    }


}
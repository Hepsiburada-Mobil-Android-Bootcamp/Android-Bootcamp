package com.android.odevler.ayse_senses.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.base.BaseAdapter
import com.android.base.viewholder.BaseViewHolder
import com.android.camp.R
import com.android.odevler.ayse_senses.data.model.Note
import com.android.odevler.ayse_senses.data.model.convertLongToTime
import com.google.firebase.firestore.Query

class NoteAdapter(query: Query?) :
    BaseAdapter<NoteAdapter.NoteHolder, Note>(query) {

    inner class NoteHolder(itemView: View) : BaseViewHolder<Note>(itemView) {

        private val title: TextView by lazy { itemView.findViewById(R.id.title_text) }
        private val description: TextView by lazy { itemView.findViewById(R.id.description_text) }
        private val priority: TextView by lazy { itemView.findViewById(R.id.priority_text) }
        private val date: TextView by lazy { itemView.findViewById(R.id.date_text) }

        override fun toBind(note: Note) {
            super.toBind(note)

            title.text = note.title
            description.text = note.description
            priority.text = note.priority.toString()
            date.text = note.convertLongToTime()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        return NoteHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ayse_item_note, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getModelClass() = Note::class.java
}
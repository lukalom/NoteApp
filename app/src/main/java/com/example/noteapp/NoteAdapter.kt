package com.example.noteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val stringSets: List<String>,private val noteOnClickListener: NoteOnClickListener) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView), View.OnLongClickListener {
        private val noteText: TextView = itemView.findViewById(R.id.noteText)
        override fun onLongClick(p0: View?): Boolean {
            noteOnClickListener.onLongClick(adapterPosition)
            return false
        }
        fun bind(){
            itemView.setOnLongClickListener(this)
            val item = stringSets[adapterPosition]
            val textView = noteText
            textView.text = item
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.notes_recycler_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int  = stringSets.size
}

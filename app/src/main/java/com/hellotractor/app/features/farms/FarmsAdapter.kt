package com.hellotractor.app.features.farms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hellotractor.android.notes.R
import com.hellotractor.notes.domain.models.Note

class FarmsAdapter : ListAdapter<Note, FarmsAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_farm, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTitle: TextView = itemView.findViewById(R.id.text_title)
        private val textCategory: TextView = itemView.findViewById(R.id.text_category)
        private val textDate: TextView = itemView.findViewById(R.id.text_date)

        fun bind(note: Note) {
            textTitle.text = note.title
            // Use the note type as the category
            textCategory.text = "Type: ${note.type.name}"
            textDate.text = "Created: ${note.dateCreated}"
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}

package com.example.materialdesignhomework.recyclerviewnotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesignhomework.R
import com.example.materialdesignhomework.databinding.NotesRecyclerItemBinding
import com.example.materialdesignhomework.recyclerviewnotes.model.NoteUiModel
import com.example.materialdesignhomework.recyclerviewnotes.model.SampleListItem

class NotesRecyclerViewAdapter(
    private var onItemClick: ((item: NoteUiModel) -> Unit),
    private var onItemDelete: ((item: NoteUiModel) -> Unit),
) : RecyclerView.Adapter<NotesRecyclerViewAdapter.NotesViewHolder>() {

    var data = emptyList<SampleListItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NotesViewHolder(
            itemView = inflater.inflate(R.layout.notes_recycler_item, parent, false),
            onClickListener = onItemClick, onDeleteListener = onItemDelete

        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val noteUi = data[position] as NoteUiModel
        holder.bind(noteUi)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class NotesViewHolder(
        itemView: View,
        private val onClickListener: (item: NoteUiModel) -> Unit,
        private val onDeleteListener: (item: NoteUiModel) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding = NotesRecyclerItemBinding.bind(itemView)
        fun bind(noteUi: NoteUiModel) {
            binding.notesTextView.text = noteUi.title
            binding.noteDescriptionTextView.text = noteUi.description
            binding.notesTextView.setOnClickListener { onClickListener(noteUi) }
            binding.removeItemImageView.setOnClickListener { onDeleteListener(noteUi) }
        }
    }
}
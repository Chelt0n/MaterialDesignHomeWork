package com.example.materialdesignhomework.recyclerviewnotes

import androidx.recyclerview.widget.DiffUtil
import com.example.materialdesignhomework.recyclerviewnotes.model.NoteUiModel
import com.example.materialdesignhomework.recyclerviewnotes.model.SampleListItem

class SampleDiffUtil(
    private val oldList: List<SampleListItem>,
    private val newList: List<SampleListItem>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return when (oldItem) {
            is NoteUiModel -> newItem is NoteUiModel && oldItem.id == newItem.id
            else -> throw IllegalArgumentException("unknown item type")
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return when (oldItem) {
            is NoteUiModel -> newItem is NoteUiModel && oldItem.title == newItem.title && oldItem.description == newItem.description
            else -> throw IllegalArgumentException("unknown item type")
        }
    }

}
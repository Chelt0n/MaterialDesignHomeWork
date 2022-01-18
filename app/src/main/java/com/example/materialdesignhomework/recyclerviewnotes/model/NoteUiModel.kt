package com.example.materialdesignhomework.recyclerviewnotes.model

import java.io.Serializable

data class NoteUiModel(
    val id: String,
    val title: String,
    val description: String,
) : SampleListItem, Serializable {
    override fun equals(other: Any?): Boolean {
        if (other is NoteUiModel) {
            return hashCode() == other.hashCode()
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
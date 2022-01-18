package com.example.materialdesignhomework.recyclerviewnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesignhomework.recyclerviewnotes.model.NoteUiModel
import com.example.materialdesignhomework.recyclerviewnotes.model.SampleListItem
import java.util.*

class NotesViewModel : ViewModel() {
    private val liveData = MutableLiveData<List<SampleListItem>>(emptyList())

    fun getItems(): LiveData<List<SampleListItem>> {
        return liveData
    }


    fun loadData() {
        val data = arrayListOf<SampleListItem>()
        for (i in 1..5) {
            data.add(
                NoteUiModel(
                    id = UUID.randomUUID().toString(),
                    title = "Заметка $i",
                    description = "Описание ${i}ой заметки"
                )
            )
        }
        liveData.value = data
    }

    fun updateItem(uiModel: NoteUiModel) {
        val newMutableList = requireCurrentList().toMutableList()
        val indexOf = newMutableList.indexOf(uiModel)
        if (indexOf!=-1){
            newMutableList[indexOf] = uiModel
        }
        liveData.value = newMutableList
    }


    fun onItemAdd() {
        val newMutableList = requireCurrentList().toMutableList()
        newMutableList.add(
            NoteUiModel(
                id = UUID.randomUUID().toString(),
                title = "Note",
                description = "NoteDescription"
            )
        )
        liveData.value = newMutableList
    }

    fun onItemMoved(from: Int, to: Int) {
        val newMutableList = requireCurrentList().toMutableList()
        Collections.swap(newMutableList, from, to)

        liveData.value = newMutableList
    }

    fun onItemRemoved(uiModel: NoteUiModel) {
        val newMutableList = requireCurrentList().toMutableList()
        newMutableList.remove(uiModel)
        liveData.value = newMutableList
    }

    fun onItemRemoved(position: Int) {
        val newMutableList = requireCurrentList().toMutableList()
        newMutableList.removeAt(position)
        liveData.value = newMutableList
    }

    private fun requireCurrentList(): List<SampleListItem> {
        return liveData.value ?: throw IllegalStateException("items list is null")
    }


}
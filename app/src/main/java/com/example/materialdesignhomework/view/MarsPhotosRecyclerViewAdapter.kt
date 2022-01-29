package com.example.materialdesignhomework.view


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesignhomework.R
import com.example.materialdesignhomework.model.imageofmars.Photo


class MarsPhotosRecyclerViewAdapter(
    private val onClickMarsPhotoListener: (Photo, Int) -> Unit
) : RecyclerView.Adapter<MarsPhotoViewHolder>() {

    private var listOfPhotos = emptyList<Photo>()

    @SuppressLint("NotifyDataSetChanged")
    fun setListOfPhotos(data: List<Photo>) {
        listOfPhotos = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder {
        return MarsPhotoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        holder.render(listOfPhotos[position], position, onClickMarsPhotoListener)

    }

    override fun getItemCount(): Int {
        return listOfPhotos.size
    }

}
package com.example.materialdesignhomework.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.materialdesignhomework.databinding.CardViewBinding
import com.example.materialdesignhomework.model.imageofmars.Photo


class MarsPhotoViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    private val binding = CardViewBinding.bind(item)
    fun render(nasa: Photo, position: Int, listener: (Photo, Int) -> Unit) {
        binding.imageCard.load(nasa.img_src)
        binding.imageCard.setOnClickListener {
            listener(nasa, position)
        }
    }
}
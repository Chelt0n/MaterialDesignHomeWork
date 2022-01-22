package com.example.materialdesignhomework.view


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.materialdesignhomework.MyOnClick
import com.example.materialdesignhomework.R
import com.example.materialdesignhomework.databinding.CardViewBinding
import com.example.materialdesignhomework.model.imageofmars.Photo


class MarsPhotosRecyclerViewAdapter :
    RecyclerView.Adapter<MarsPhotosRecyclerViewAdapter.MyViewHolder>() {

    private var listOfPhotos = emptyList<Photo>()
    private lateinit var listener: MyOnClick

    fun setOnclickPhoto(onMyClick: MyOnClick) {
        listener = onMyClick
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListOfPhotos(data: List<Photo>) {
        listOfPhotos = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.render(listOfPhotos[position], position)

    }

    override fun getItemCount(): Int {
        return listOfPhotos.size
    }

    inner class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = CardViewBinding.bind(item)
        fun render(nasa: Photo, position: Int) {
            binding.imageCard.load(nasa.img_src)
            binding.imageCard.setOnClickListener {
                listener.click(nasa, position)
            }
        }
    }
}
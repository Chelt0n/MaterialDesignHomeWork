package com.example.materialdesignhomework.lesson6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesignhomework.R
import com.example.materialdesignhomework.databinding.ActivityRecyclerItemEarthBinding
import com.example.materialdesignhomework.databinding.ActivityRecyclerItemMarsBinding

class RecyclerActivityAdapter(
    private var onListItemClick: OnListItemClick,
    private var data: List<Data>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_EARTH) {
            EarthViewHolder(
                inflater.inflate(R.layout.activity_recycler_item_earth, parent, false) as View
            )
        } else {
            MarsViewHolder(
                inflater.inflate(R.layout.activity_recycler_item_mars, parent, false) as View
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_EARTH) {
            holder as EarthViewHolder
            holder.bind(data[position])
        } else {
            holder as MarsViewHolder
            holder.bind(data[position])
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].someDescription.isNullOrBlank()) TYPE_MARS else TYPE_EARTH
    }

    inner class EarthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ActivityRecyclerItemEarthBinding.bind(itemView)
        fun bind(data: Data) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.descriptionTextView.text = data.someDescription
                binding.wikiImageView.setOnClickListener { onListItemClick.onItemClick(data) }
            }
        }
    }

    inner class MarsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ActivityRecyclerItemMarsBinding.bind(itemView)
        fun bind(data: Data) {
            binding.marsImageView.setOnClickListener { onListItemClick.onItemClick(data) }
        }
    }


    companion object {
        private const val TYPE_EARTH = 0
        private const val TYPE_MARS = 1
    }

}



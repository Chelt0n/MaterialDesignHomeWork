package com.example.materialdesignhomework.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.transition.*
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.example.materialdesignhomework.MyOnClick
import com.example.materialdesignhomework.R
import com.example.materialdesignhomework.databinding.MarsRoversPhotosFragmentBinding
import com.example.materialdesignhomework.model.imageofmars.Photo
import com.example.materialdesignhomework.viewmodel.AppStateLatestImageMars
import com.example.materialdesignhomework.viewmodel.LatestImagesMarsViewModel

class MarsPhotosFragment : Fragment(), MyOnClick {
    private lateinit var binding: MarsRoversPhotosFragmentBinding
    private val adapter = MarsPhotosRecyclerViewAdapter()
    private val viewModel: LatestImagesMarsViewModel by lazy {
        ViewModelProvider(this)[LatestImagesMarsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPhotos().observe(this, { appState -> render(appState) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MarsRoversPhotosFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerViewPhotosOfRovers.layoutManager =
                GridLayoutManager(context, 4)
            recyclerViewPhotosOfRovers.adapter = adapter
            adapter.setOnclickPhoto(this@MarsPhotosFragment)
        }
        binding.imageView.setOnClickListener {

            closeEnlargedImageWithAnimation()
        }
    }


    private fun render(appStateLatestImageMars: AppStateLatestImageMars?) {
        when (appStateLatestImageMars) {
            is AppStateLatestImageMars.Loading -> {
                Toast.makeText(context, "Загрузка", Toast.LENGTH_SHORT).show()
            }
            is AppStateLatestImageMars.Success -> {
                adapter.setListOfPhotos(appStateLatestImageMars.serverResponseData.photos)
            }
            is AppStateLatestImageMars.Error -> {
                Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun showEnlargedImageWithAnimation(nasa: Photo) {
        val transition = Fade()
        transition.duration = 1000
        TransitionManager.beginDelayedTransition(binding.marsRoversPhotos, transition)
        binding.imageView.load(nasa.img_src)
        binding.imageView.visibility = View.VISIBLE
    }

    private fun closeEnlargedImageWithAnimation() {
        val transition = Fade()
        transition.duration = 1000
        TransitionManager.beginDelayedTransition(binding.marsRoversPhotos, transition)
        binding.imageView.visibility = View.GONE
    }

    override fun click(nasa: Photo, position: Int) {
        showEnlargedImageWithAnimation(nasa)
    }
}

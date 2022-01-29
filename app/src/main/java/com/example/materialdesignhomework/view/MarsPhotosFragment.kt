package com.example.materialdesignhomework.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.materialdesignhomework.databinding.MarsRoversPhotosFragmentBinding
import com.example.materialdesignhomework.viewmodel.AppStateLatestImageMars
import com.example.materialdesignhomework.viewmodel.LatestImagesMarsViewModel

class MarsPhotosFragment : Fragment() {

    private lateinit var binding: MarsRoversPhotosFragmentBinding
    private val adapter by lazy {
        MarsPhotosRecyclerViewAdapter(
            onClickMarsPhotoListener = { photo, position ->
                Toast.makeText(
                    context,
                    "№$position " + photo.camera.fullName + "\n" + photo.earthDate,
                    Toast.LENGTH_SHORT
                ).show()
            })
    }

    private val viewModel: LatestImagesMarsViewModel by lazy {
        ViewModelProvider(this)[LatestImagesMarsViewModel::class.java]
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
        viewModel.getPhotos().observe(viewLifecycleOwner, { appState -> render(appState) })

        binding.recyclerViewPhotosOfRovers.layoutManager = GridLayoutManager(context, 4)
        binding.recyclerViewPhotosOfRovers.adapter = adapter

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
}
package com.example.materialdesignhomework.view

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialdesignhomework.R
import com.example.materialdesignhomework.viewmodel.AppStateDailyImage
import com.example.materialdesignhomework.viewmodel.DailyImageViewModel


class DailyImageInfoFragment : Fragment() {

    private lateinit var textViewDescription: TextView
    private lateinit var textViewHeader: TextView
    private lateinit var imageInCollapsingToolBar: ImageView
    private lateinit var toolbar: Toolbar

    private val viewModel: DailyImageViewModel by lazy {
        ViewModelProvider(this)[DailyImageViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.daily_image_info_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getImageData().observe(viewLifecycleOwner, { dailyImage -> renderData(dailyImage) })

        textViewHeader = view.findViewById(R.id.text_view_header)
        textViewDescription = view.findViewById(R.id.text_view_description)
        imageInCollapsingToolBar = view.findViewById(R.id.image_collapsing_toolbar)

        toolbar = view.findViewById(R.id.mToolbar)
        (context as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_toolbar, menu)
    }


    private fun renderData(appStateDailyImage: AppStateDailyImage) {
        when (appStateDailyImage) {
            is AppStateDailyImage.Success -> {
                val serverResponseData = appStateDailyImage.serverResponseData
                val url = serverResponseData.url
                imageInCollapsingToolBar.load(url) {
                    lifecycle(this@DailyImageInfoFragment)
                    textViewHeader.text = serverResponseData.title
                    textViewDescription.text = serverResponseData.explanation

                }
            }
            is AppStateDailyImage.Loading -> {
                Toast.makeText(context, "Загрузка", Toast.LENGTH_SHORT).show()
            }
            is AppStateDailyImage.Error -> {
                Toast.makeText(
                    context,
                    appStateDailyImage.error.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}


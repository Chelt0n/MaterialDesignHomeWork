package com.example.materialdesignhomework.view


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialdesignhomework.R
import com.example.materialdesignhomework.viewmodel.AppStateDailyImage
import com.example.materialdesignhomework.viewmodel.DailyImageViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class DailyImageFragment : Fragment() {

    private val viewModel: DailyImageViewModel by lazy {
        ViewModelProvider(this)[DailyImageViewModel::class.java]
    }

    private lateinit var dailyImageView: ImageView
    private lateinit var wikiInputEditText: TextInputEditText
    private lateinit var wikiInputLayout: TextInputLayout
    private lateinit var textViewImageTitle: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getImageData().observe(this, { dailyImage -> renderData(dailyImage) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daily_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        view.setOnClickListener { view.clearFocus() }


        wikiInputLayout.setEndIconOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val url = "https://en.wikipedia.org/wiki/${wikiInputEditText.text.toString()}"
            val uri = Uri.parse(url)
            intent.data = uri
            startActivity(intent)
        }
    }

    private fun initView(view: View) {
        dailyImageView = view.findViewById(R.id.image_view_nasa_image)
        wikiInputEditText = view.findViewById(R.id.input_edit_text_wiki)
        wikiInputLayout = view.findViewById(R.id.input_layout_wiki)
        textViewImageTitle = view.findViewById(R.id.text_view_image_title)

    }


    private fun renderData(appStateDailyImage: AppStateDailyImage) {
        when (appStateDailyImage) {
            is AppStateDailyImage.Success -> {
                val serverResponseData = appStateDailyImage.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
                } else {
                    dailyImageView.load(url) {
                        lifecycle(this@DailyImageFragment)
                    }
                    textViewImageTitle.text = serverResponseData.title
                }
            }
            is AppStateDailyImage.Loading -> {
                Toast.makeText(context, "Загрузка", Toast.LENGTH_SHORT).show()
            }
            is AppStateDailyImage.Error -> {
                Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

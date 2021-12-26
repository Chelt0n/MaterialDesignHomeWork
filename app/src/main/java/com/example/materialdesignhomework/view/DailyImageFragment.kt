package com.example.materialdesignhomework.view


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialdesignhomework.R
import com.example.materialdesignhomework.viewmodel.AppState
import com.example.materialdesignhomework.viewmodel.DailyImageViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class DailyImageFragment : Fragment() {

    private val viewModel: DailyImageViewModel by lazy {
        ViewModelProvider(this)[DailyImageViewModel::class.java]
    }

    private lateinit var dailyImageView: ImageView
    private lateinit var wikiInputEditText: TextInputEditText
    private lateinit var wikiInputLayout: TextInputLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var textViewBottomSheetDescriptionHeader: TextView
    private lateinit var textViewBottomSheetDescriptionText: TextView
    private lateinit var textViewImageTitle: TextView
    private lateinit var buttonShowBottomSheet: Button


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
//        view.setOnClickListener { view.clearFocus() }

        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))


        wikiInputLayout.setEndIconOnClickListener() {
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
        textViewBottomSheetDescriptionHeader =
            view.findViewById(R.id.text_view_bottom_sheet_description_header)
        textViewBottomSheetDescriptionText =
            view.findViewById(R.id.text_view_bottom_sheet_description_text)
        textViewImageTitle = view.findViewById(R.id.text_view_image_title)
        buttonShowBottomSheet = view.findViewById(R.id.button_show_bottom_sheet)

    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        buttonShowBottomSheet.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }


    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val serverResponseData = appState.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
                } else {
                    dailyImageView.load(url) {
                        lifecycle(this@DailyImageFragment)
                    }
                    textViewBottomSheetDescriptionHeader.text = serverResponseData.title
                    textViewBottomSheetDescriptionText.text = serverResponseData.explanation
                    textViewImageTitle.text = serverResponseData.title
                }
            }
            is AppState.Loading -> {
                Toast.makeText(context, "Загрузка", Toast.LENGTH_SHORT).show()
            }
            is AppState.Error -> {
                Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
            }
        }

    }


}

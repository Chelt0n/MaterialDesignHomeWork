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
    private lateinit var inputEditText: TextInputEditText
    private lateinit var inputLayout: TextInputLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var imageTitle: TextView
    private lateinit var btnDescription: Button


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


        inputLayout.setEndIconOnClickListener() {
            val intent = Intent(Intent.ACTION_VIEW)
            val url = "https://en.wikipedia.org/wiki/${inputEditText.text.toString()}"
            val uri = Uri.parse(url)
            intent.data = uri
            startActivity(intent)
        }
    }

    private fun initView(view: View) {
        dailyImageView = view.findViewById(R.id.image_view_nasa_image)
        inputEditText = view.findViewById(R.id.input_edit_text_wiki)
        inputLayout = view.findViewById(R.id.input_layout_wiki)
        title = view.findViewById(R.id.bottom_sheet_description_header)
        description = view.findViewById(R.id.bottom_sheet_description)
        imageTitle = view.findViewById(R.id.imageTitle)
        btnDescription = view.findViewById(R.id.btnDescription)

    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        btnDescription.setOnClickListener {
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
                    title.text = serverResponseData.title
                    description.text = serverResponseData.explanation
                    imageTitle.text = serverResponseData.title
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

    companion object {
        fun newInstance() = DailyImageFragment()
    }
}

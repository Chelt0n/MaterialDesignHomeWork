package com.example.materialdesignhomework.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.materialdesignhomework.R
import com.example.materialdesignhomework.viewmodel.AppState
import com.example.materialdesignhomework.viewmodel.DailyImageViewModel


class DailyImageInfoFragment : Fragment() {

    private lateinit var textViewBottomSheetDescriptionHeader: TextView
    private lateinit var textViewBottomSheetDescriptionText: TextView

    private val viewModel: DailyImageViewModel by lazy {
        ViewModelProvider(this)[DailyImageViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getImageData()
            .observe(viewLifecycleOwner, { dailyImage -> renderData(dailyImage) })

        textViewBottomSheetDescriptionHeader =
            view.findViewById(R.id.text_view_bottom_sheet_description_header)
        textViewBottomSheetDescriptionText =
            view.findViewById(R.id.text_view_bottom_sheet_description_text)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val serverResponseData = appState.serverResponseData
                textViewBottomSheetDescriptionHeader.text = serverResponseData.title
                textViewBottomSheetDescriptionText.text = serverResponseData.explanation
            }
            is AppState.Loading -> {
                Toast.makeText(context, "Загрузка", Toast.LENGTH_SHORT).show()
            }
            is AppState.Error -> {
                Toast.makeText(context, appState.error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

}


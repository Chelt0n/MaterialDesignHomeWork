package com.example.materialdesignhomework.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.materialdesignhomework.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ChipsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chips, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chipGroup = view.findViewById<ChipGroup>(R.id.chipGroup)
        chipGroup.setOnCheckedChangeListener { _, checkedId ->
            val chip = chipGroup.findViewById<Chip>(checkedId)
            val text = "Выбран ${chip.text}"
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
            Log.d("mylog", "Выбран ${chip.text}")
        }

        val chipClose = view.findViewById<Chip>(R.id.chip_close)
        chipClose.setOnCloseIconClickListener {
            chipClose.isVisible = false
            Toast.makeText(context, "Close is Clicked", Toast.LENGTH_SHORT).show()

        }
    }


    companion object {
        fun newInstance() = ChipsFragment()
    }
}
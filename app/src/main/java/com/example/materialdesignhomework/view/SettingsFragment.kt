package com.example.materialdesignhomework.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.IntentCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.materialdesign.FontTypes
import com.example.materialdesign.SharedPref
import com.example.materialdesign.Theme
import com.example.materialdesignhomework.R
import com.example.materialdesignhomework.main.MainActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlin.system.exitProcess

class SettingsFragment : Fragment() {
    lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPref = SharedPref(
            requireActivity().getSharedPreferences(
                SharedPref.SETTINGS,
                Context.MODE_PRIVATE
            )
        )

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val switchTheme = view.findViewById<SwitchMaterial>(R.id.switchTheme)
        val radio1 = view.findViewById<Chip>(R.id.radio1)
        val radio2 = view.findViewById<Chip>(R.id.radio2)
        val radio3 = view.findViewById<Chip>(R.id.radio3)

        radio1.setOnClickListener {
            Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
            sharedPref.setFontType(FontTypes.CURSIVE)
            (requireActivity() as MainActivity).recreate()
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction().replace(
                R.id.container, DailyImageFragment.newInstance()
            ).addToBackStack(null).commit()


        }
        radio2.setOnClickListener {
            Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()
            sharedPref.setFontType(FontTypes.SANS_SERIF_THIN)
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction().replace(
                R.id.container, DailyImageFragment.newInstance()
            ).addToBackStack(null).commit()
            (requireActivity() as MainActivity).recreate()
        }
        radio3.setOnClickListener {
            Toast.makeText(context, "3", Toast.LENGTH_SHORT).show()
            sharedPref.setFontType(FontTypes.CONDENSED)
            (requireActivity() as MainActivity).supportFragmentManager.beginTransaction().replace(
                R.id.container, DailyImageFragment.newInstance()
            ).addToBackStack(null).commit()
            (requireActivity() as MainActivity).recreate()
        }

        switchTheme.isChecked = sharedPref.getCustomTheme() == Theme.NIGHT
        switchTheme.setOnClickListener {
            if (switchTheme.isChecked) {
                sharedPref.setCustomTheme(Theme.NIGHT)
            } else {
                sharedPref.setCustomTheme(Theme.DAY)
            }
            (requireActivity() as MainActivity).setUserTheme()
        }

        val chipClose = view.findViewById<Chip>(R.id.chip_close)
        chipClose.setOnCloseIconClickListener {
            chipClose.isVisible = false
            Toast.makeText(context, "Close is Clicked", Toast.LENGTH_SHORT).show()

        }
    }


    companion object {
        fun newInstance() = SettingsFragment()
    }

}
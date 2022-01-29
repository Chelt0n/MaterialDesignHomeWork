package com.example.materialdesignhomework.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.materialdesignhomework.AppThemeSharedPreferences
import com.example.materialdesignhomework.FontTypes
import com.example.materialdesignhomework.Theme
import com.example.materialdesignhomework.R
import com.example.materialdesignhomework.MainActivity
import com.google.android.material.chip.Chip
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsFragment : Fragment() {
    private lateinit var appThemeSharedPreferences: AppThemeSharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appThemeSharedPreferences = AppThemeSharedPreferences(
            requireActivity().getSharedPreferences(
                AppThemeSharedPreferences.SETTINGS,
                Context.MODE_PRIVATE
            )
        )

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val switchTheme = view.findViewById<SwitchMaterial>(R.id.switch_theme)
        val cursiveChip = view.findViewById<Chip>(R.id.cursive_chip)
        val sansSerifThinChip = view.findViewById<Chip>(R.id.sans_serif_thin_chip)
        val condensedChip = view.findViewById<Chip>(R.id.condensed_chip)

        cursiveChip.setOnClickListener {
            Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
            appThemeSharedPreferences.setFontType(FontTypes.CURSIVE)
            (requireActivity() as MainActivity).recreate()

        }

        sansSerifThinChip.setOnClickListener {
            Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()
            appThemeSharedPreferences.setFontType(FontTypes.SANS_SERIF_THIN)
            (requireActivity() as MainActivity).recreate()
        }

        condensedChip.setOnClickListener {
            Toast.makeText(context, "3", Toast.LENGTH_SHORT).show()
            appThemeSharedPreferences.setFontType(FontTypes.CONDENSED)
            (requireActivity() as MainActivity).recreate()
        }

        switchTheme.isChecked = appThemeSharedPreferences.getCustomTheme() == Theme.NIGHT
        switchTheme.setOnClickListener {
            if (switchTheme.isChecked) {
                appThemeSharedPreferences.setCustomTheme(Theme.NIGHT)
            } else {
                appThemeSharedPreferences.setCustomTheme(Theme.DAY)
            }
            (requireActivity() as MainActivity).setUserTheme()
        }
    }
}
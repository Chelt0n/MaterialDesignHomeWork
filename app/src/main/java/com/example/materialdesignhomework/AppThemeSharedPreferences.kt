package com.example.materialdesignhomework

import android.content.SharedPreferences


private const val THEME_KEY = "THEME_KEY"
private const val FONT_KEY = "FONT_KEY"

class AppThemeSharedPreferences(private val sharedPreferences: SharedPreferences) {
    fun setCustomTheme(theme: Theme) {
        sharedPreferences.edit().putInt(THEME_KEY, theme.ordinal).apply()
    }

    fun getCustomTheme(): Theme {
        val ordinal = sharedPreferences.getInt(THEME_KEY, 0)
        return Theme.values()[ordinal]
    }

    fun setFontType(fontTypes: FontTypes) {
        sharedPreferences.edit().putInt(FONT_KEY, fontTypes.ordinal).apply()
    }

    fun getFontType(): FontTypes {
        val ordinal = sharedPreferences.getInt(FONT_KEY, 0)
        return FontTypes.values()[ordinal]
    }

    companion object {
        const val SETTINGS = "SETTINGS_KEY"
    }

}
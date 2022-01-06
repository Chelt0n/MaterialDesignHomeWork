package com.example.materialdesignhomework


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.materialdesignhomework.AppThemeSharedPreferences.Companion.SETTINGS
import com.example.materialdesignhomework.view.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appThemeSharedPreferences: AppThemeSharedPreferences
    private lateinit var viewPager2: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appThemeSharedPreferences =
            AppThemeSharedPreferences(getSharedPreferences(SETTINGS, MODE_PRIVATE))
        setUserTheme()
        setContentView(R.layout.main_activity)

        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        viewPager2 = findViewById(R.id.view_pager2)

        val adapter = ViewPagerAdapter(this)
        viewPager2.adapter = adapter

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNavigationView.menu.getItem(position).isChecked = true;
            }
        })

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    viewPager2.currentItem = 0

                    true
                }
                R.id.menu_description -> {
                    viewPager2.currentItem = 1
                    true
                }
                R.id.menu_settings -> {
                    viewPager2.currentItem = 2
                    true
                }
                else -> false
            }
        }

    }


    fun setUserTheme() {
        when (appThemeSharedPreferences.getCustomTheme()) {
            Theme.DAY -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Theme.NIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        when (appThemeSharedPreferences.getFontType()) {
            FontTypes.CURSIVE -> setTheme(R.style.cursive)
            FontTypes.SANS_SERIF_THIN -> setTheme(R.style.sans_serif)
            FontTypes.CONDENSED -> setTheme(R.style.condensed)
        }
    }
}


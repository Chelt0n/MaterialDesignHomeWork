package com.example.materialdesignhomework.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager2.widget.ViewPager2
import com.example.materialdesign.FontTypes
import com.example.materialdesign.AppThemeSharedPreferences
import com.example.materialdesign.AppThemeSharedPreferences.Companion.SETTINGS
import com.example.materialdesign.Theme
import com.example.materialdesignhomework.view.SettingsFragment
import com.example.materialdesignhomework.R
import com.example.materialdesignhomework.lesson3.ViewPagerAdapter
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var appThemeSharedPreferences: AppThemeSharedPreferences

    private lateinit var viewPager2:ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appThemeSharedPreferences = AppThemeSharedPreferences(getSharedPreferences(SETTINGS, MODE_PRIVATE))
        setUserTheme()
        setContentView(R.layout.main_activity)
        initBottomBar()
        initBottomBar()

        tabLayout = findViewById(R.id.tab_layout)
        viewPager2 = findViewById(R.id.view_pager2)

        val adapter = ViewPagerAdapter(this)
        viewPager2.adapter = adapter

        tabLayout.addTab(tabLayout.newTab().setText("Картина дня"))
        tabLayout.addTab(tabLayout.newTab().setText("Описание"))
        tabLayout.addTab(tabLayout.newTab().setText("Настройки"))

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
               tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager2.currentItem = tab!!.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onResume() {
        super.onResume()
        setUserTheme()
    }

    private fun initBottomBar() {
        val bottomAppBar: BottomAppBar = findViewById(R.id.bottom_app_bar)
        bottomAppBar.inflateMenu(R.menu.menu_item_bottom_app_bar)
        setSupportActionBar(bottomAppBar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item_bottom_app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> Toast.makeText(this, "Favourite", Toast.LENGTH_SHORT).show()
            R.id.app_bar_settings -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.container,
                    SettingsFragment()
                ).addToBackStack(null).commit()
            }
        }
        return super.onOptionsItemSelected(item)
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

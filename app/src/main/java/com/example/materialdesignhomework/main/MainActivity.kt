package com.example.materialdesignhomework.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.materialdesign.FontTypes
import com.example.materialdesign.SharedPref
import com.example.materialdesign.SharedPref.Companion.SETTINGS
import com.example.materialdesign.Theme
import com.example.materialdesignhomework.view.DailyImageFragment
import com.example.materialdesignhomework.view.BottomNavigationDrawerFragment
import com.example.materialdesignhomework.view.SettingsFragment
import com.example.materialdesignhomework.R
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = SharedPref(getSharedPreferences(SETTINGS, MODE_PRIVATE))
        setContentView(R.layout.main_activity)
        initBottomBar()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DailyImageFragment())
                .commitNow()
        }
    }

    override fun onResume() {
        super.onResume()
        setUserTheme()
        Log.d("mylog", sharedPref.getCustomTheme().toString())
    }

    private fun initBottomBar() {
        val bottomAppBar: BottomAppBar = findViewById(R.id.bottom_app_bar)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        bottomAppBar.inflateMenu(R.menu.menu_item_bottom_app_bar)
        setSupportActionBar(bottomAppBar)
        fab.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DailyImageFragment())
                .commitNow()
        }
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
                    SettingsFragment.newInstance()
                ).addToBackStack(null).commit()
            }
            android.R.id.home -> {

                BottomNavigationDrawerFragment().show(supportFragmentManager, "tag")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setUserTheme() {
        when (sharedPref.getCustomTheme()) {
            Theme.DAY -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Theme.NIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        when (sharedPref.getFontType()) {
            FontTypes.CURSIVE -> setTheme(R.style.cursive)
            FontTypes.SANS_SERIF_THIN -> setTheme(R.style.sans_serif)
            FontTypes.CONDENSED -> setTheme(R.style.condensed)
        }
    }

}

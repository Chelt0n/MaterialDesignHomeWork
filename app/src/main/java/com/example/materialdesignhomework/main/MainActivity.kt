package com.example.materialdesignhomework.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.materialdesignhomework.view.DailyImageFragment
import com.example.materialdesignhomework.view.BottomNavigationDrawerFragment
import com.example.materialdesignhomework.view.ChipsFragment
import com.example.materialdesignhomework.R
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        bottomBar()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DailyImageFragment.newInstance())
                .commitNow()
        }
    }

    private fun bottomBar() {
        val bottomAppBar: BottomAppBar = findViewById(R.id.bottom_app_bar)
        val fab:FloatingActionButton = findViewById(R.id.fab)
        bottomAppBar.inflateMenu(R.menu.menu_item_bottom_app_bar)
        setSupportActionBar(bottomAppBar)
        fab.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DailyImageFragment.newInstance())
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
                    ChipsFragment.newInstance()
                ).addToBackStack(null).commit()
            }
            android.R.id.home -> {

                BottomNavigationDrawerFragment().show(supportFragmentManager, "tag")
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
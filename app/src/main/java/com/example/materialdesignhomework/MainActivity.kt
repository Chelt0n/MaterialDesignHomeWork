package com.example.materialdesignhomework


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesignhomework.view.ViewPagerFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, ViewPagerFragment()).addToBackStack("notes")
                .commit()
        }
    }

}


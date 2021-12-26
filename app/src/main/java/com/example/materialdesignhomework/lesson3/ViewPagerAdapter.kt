package com.example.materialdesignhomework.lesson3

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.materialdesignhomework.view.DailyImageFragment
import com.example.materialdesignhomework.view.SettingsFragment


class ViewPagerAdapter(private val fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DailyImageFragment()
            1 -> SecondFragment()
            2 -> SettingsFragment()
            else -> DailyImageFragment()
        }

    }
}
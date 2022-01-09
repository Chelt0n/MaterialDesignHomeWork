package com.example.materialdesignhomework.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.materialdesignhomework.view.DailyImageFragment
import com.example.materialdesignhomework.view.DailyImageInfoFragment
import com.example.materialdesignhomework.view.SettingsFragment

class ViewPagerAdapter(fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {
    private var items = listOf(DailyImageFragment(),DailyImageInfoFragment(),SettingsFragment())


    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }
}
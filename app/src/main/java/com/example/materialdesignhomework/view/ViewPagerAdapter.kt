package com.example.materialdesignhomework.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    private val items = listOf(DailyImageInfoFragment(),MarsPhotosFragment(),SettingsFragment())

    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }
}
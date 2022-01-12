package com.example.materialdesignhomework.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(private val fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DailyImageInfoFragment()
            1 -> MarsPhotosFragment()
            2 -> MotionFragment()
            else -> DailyImageFragment()
        }

    }
}
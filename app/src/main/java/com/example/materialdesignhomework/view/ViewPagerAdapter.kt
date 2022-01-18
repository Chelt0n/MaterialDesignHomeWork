package com.example.materialdesignhomework.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.materialdesignhomework.recyclerviewnotes.NotesFragment


class ViewPagerAdapter(private val fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {
    private var items =
        listOf(NotesFragment.newInstance(), DailyImageInfoFragment(), MarsPhotosFragment())

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }
}
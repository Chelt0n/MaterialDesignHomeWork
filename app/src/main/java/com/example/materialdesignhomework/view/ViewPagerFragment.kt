package com.example.materialdesignhomework.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.materialdesignhomework.R
import com.example.materialdesignhomework.databinding.FragmentViewPagerBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigationView = view.findViewById(R.id.bottom_navigation_view)
        viewPager2 = view.findViewById(R.id.view_pager2)

        val adapter = ViewPagerAdapter(requireActivity())
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
}
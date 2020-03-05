package com.example.absentexample.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.absentexample.main.fragment.LogAbsentFragment
import com.example.absentexample.main.fragment.ProfileFragment

class MainPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        return if(position == 0) LogAbsentFragment() else ProfileFragment()
    }

    override fun getCount(): Int {
        return 2
    }
}
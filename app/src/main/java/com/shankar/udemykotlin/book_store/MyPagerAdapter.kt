package com.shankar.udemykotlin.book_store

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pageNumber = 3
    override fun getCount(): Int {
        return pageNumber
    }

    override fun getItem(position: Int): Fragment {
        return when(position)
        {
            0 -> FragmentOne()
            1 -> SecondFragment()
            2 -> ThirdFragment()

            else -> FragmentOne()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {

            0 -> "top"
            1 -> "children"
            2 -> "Fiction"

            else -> ""
        }
    }
}
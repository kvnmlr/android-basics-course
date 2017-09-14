package com.example.android.viewpager

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.android.miwok.*

class SimpleFragmentPagerAdapter(mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val tabTitles = arrayOf(
            mContext.getString(R.string.category_numbers),
            mContext.getString(R.string.category_colors),
            mContext.getString(R.string.category_family),
            mContext.getString(R.string.category_phrases))

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> NumbersFragment()
            1 -> ColorsFragment()
            2 -> FamilyFragment()
            else -> PhrasesFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }
}
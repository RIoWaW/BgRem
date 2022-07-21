package com.tuocwizards.bgrem.pages.adapters

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tuocwizards.bgrem.pages.BackgroundTabItem

class ViewPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun getItemCount() = 4

    override fun createFragment(position: Int) = BackgroundTabItem.newInstance(position)
}
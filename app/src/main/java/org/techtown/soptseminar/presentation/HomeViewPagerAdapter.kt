package org.techtown.soptseminar.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    val fragments = mutableListOf<Fragment>()
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int) = fragments[position]
}

// HomeFollowViewPagerAdapter는 Fragment들을 연결시키니까, 생성자에 Fragment 넣기


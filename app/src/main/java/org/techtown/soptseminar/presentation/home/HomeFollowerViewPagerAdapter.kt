package org.techtown.soptseminar.presentation.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.techtown.soptseminar.presentation.camera.CameraFragment
import org.techtown.soptseminar.presentation.home.HomeFragment
import org.techtown.soptseminar.presentation.profile.ProfileFragment

class HomeFollowerViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfileFragment()
            1 -> HomeFragment()
            else -> CameraFragment()
        }
    }
}

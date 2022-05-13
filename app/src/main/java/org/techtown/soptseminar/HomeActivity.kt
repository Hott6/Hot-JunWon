package org.techtown.soptseminar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import org.techtown.soptseminar.adapter.HomeFollowerViewPagerAdapter
import org.techtown.soptseminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var userData: String
    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeFollowerViewPagerAdapter: HomeFollowerViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // signinActivity에서 username에 해당하는 이름 받아오기
        userData = intent.getStringExtra("username").toString()
        initAdapter()
        initBottomNavigation()
    }

    fun initAdapter() {
        val fragments = listOf(ProfileFragment(), HomeFragment(), CameraFragment())
        homeFollowerViewPagerAdapter = HomeFollowerViewPagerAdapter(this)
        homeFollowerViewPagerAdapter.fragments.addAll(fragments)
        binding.vpHome.adapter = homeFollowerViewPagerAdapter
    }

    fun initBottomNavigation() {
        binding.vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bnvHome.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_profile -> {
                    binding.vpHome.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.vpHome.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpHome.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }
}

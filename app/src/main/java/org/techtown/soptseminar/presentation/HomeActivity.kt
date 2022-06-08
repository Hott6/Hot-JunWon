package org.techtown.soptseminar.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import org.techtown.soptseminar.R
import org.techtown.soptseminar.presentation.home.HomeFollowerViewPagerAdapter
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

    private fun initAdapter() {
        binding.vpHome.adapter = HomeFollowerViewPagerAdapter(this)
    }

    private fun initBottomNavigation() {
        binding.vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bnvHome.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_profile -> {
                    binding.vpHome.currentItem = 0
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.vpHome.currentItem = 1
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpHome.currentItem = 2
                    return@setOnItemSelectedListener true
                }
            }
        }
    }
}

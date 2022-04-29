package org.techtown.soptseminar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import org.techtown.soptseminar.databinding.ActivityViewPagerSampleBinding

class ViewPagerSample : AppCompatActivity() {
    private lateinit var binding: ActivityViewPagerSampleBinding
    private lateinit var testViewPagerAdapter: TestViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initBottomNavi()
    }

    private fun initAdapter() {

        val fragmentList = listOf(TestFragment1(), TestFragment2(), TestFragment3())

        testViewPagerAdapter = TestViewPagerAdapter(this)
        testViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpSample.adapter = testViewPagerAdapter
    }

    private fun initBottomNavi() {
        binding.vpSample.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bnvSample.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvSample.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_android -> {
                    binding.vpSample.currentItem = First_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_list -> {
                    binding.vpSample.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpSample.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    companion object {
        const val First_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }
}

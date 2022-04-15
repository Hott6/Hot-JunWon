package org.techtown.soptseminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.soptseminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var position = FOLLOWER_FRAGMENT
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initTransactionEvent()
    }

    fun initTransactionEvent() {

        val fragment1 = FollowerFragment()
        val fragment2 = RepositoryFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragment_main, fragment1).commit()

        binding.btnFollower.setOnClickListener {
            if (position == REPO_FRAGMENT) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment1)
                    .commit()
                position = FOLLOWER_FRAGMENT
            }
        }

        binding.btnRepository.setOnClickListener {
            if (position == FOLLOWER_FRAGMENT) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment2)
                    .commit()
                position = REPO_FRAGMENT
            }
        }
    }

    companion object {
        const val FOLLOWER_FRAGMENT = 1
        const val REPO_FRAGMENT = 2
    }

}
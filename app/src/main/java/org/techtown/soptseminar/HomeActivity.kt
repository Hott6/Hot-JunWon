package org.techtown.soptseminar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_main, followerFragment).commit()

        binding.btnFollower.setOnClickListener {
            if (position == REPO_FRAGMENT) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_main, followerFragment)
                    .commit()
                position = FOLLOWER_FRAGMENT
            }
        }
        binding.btnRepository.setOnClickListener {
            if (position == FOLLOWER_FRAGMENT) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_main, repositoryFragment)
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

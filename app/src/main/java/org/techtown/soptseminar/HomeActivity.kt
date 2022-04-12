package org.techtown.soptseminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.soptseminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initTransactionEvent()
    }
    fun initTransactionEvent(){

        val fragment1 = FollowerFragment()
        val fragment2 =  RepositoryFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragment_main, fragment1).commit()

        binding.btnFollower.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment1).commit()
        }

        binding.btnRepository.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment2).commit()
        }
    }


}
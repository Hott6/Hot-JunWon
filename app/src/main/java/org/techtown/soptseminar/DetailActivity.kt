package org.techtown.soptseminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.soptseminar.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        binding.ivProfile.setImageResource(intent.getIntExtra("gender",-1))
        binding.tvName.text = intent.getStringExtra("name")
        binding.tvDescription.text = intent.getStringExtra("introduce")
        setContentView(binding.root)
    }
}
package org.techtown.soptseminar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.techtown.soptseminar.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        binding.ivProfile.setImageResource(intent.getIntExtra(GENDER, -1))
        binding.tvName.text = intent.getStringExtra(NAME)
        binding.tvDescription.text = intent.getStringExtra(INTRODUCE)
        setContentView(binding.root)
    }

    // 상수화
    companion object {
        const val NAME = "name"
        const val INTRODUCE = "introduce"
        const val GENDER = "gender"
    }
}

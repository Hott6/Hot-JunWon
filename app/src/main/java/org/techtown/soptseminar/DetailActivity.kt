package org.techtown.soptseminar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.circleCrop
import org.techtown.soptseminar.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        with(binding) {
            tvName.text = intent.getStringExtra(NAME)
            tvDescription.text = intent.getStringExtra(INTRODUCE)
        }
        val man =
            "https://user-images.githubusercontent.com/87055456/166401884-4add5a15-42be-41ad-9d72-563f123d27b0.png"
        Glide.with(binding.root)
            .load(intent.getStringExtra(IMAGE) ?: man)
            .circleCrop()
            .into(binding.ivProfile)

        setContentView(binding.root)
    }

    // 상수화
    companion object {
        const val NAME = "name"
        const val INTRODUCE = "introduce"
        const val IMAGE = "gender"
    }
}

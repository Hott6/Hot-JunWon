package org.techtown.soptseminar.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil.setContentView
import org.techtown.soptseminar.databinding.ActivitySettingBinding
import org.techtown.soptseminar.util.SignSharedPreferences

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.layoutLogout.setOnClickListener {
            SignSharedPreferences.clearAll(this)
            val intent = Intent(this, SignInActivity::class.java)
            // 현재 HomeActivity가 백스택에 저장되어 있는 상태, 따라서 기존의 스택을 비워줘야한다.
            finishAffinity()
            startActivity(intent)
        }
    }
}

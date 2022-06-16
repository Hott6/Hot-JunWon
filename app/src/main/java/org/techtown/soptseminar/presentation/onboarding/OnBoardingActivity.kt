package org.techtown.soptseminar.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.techtown.soptseminar.R
import org.techtown.soptseminar.presentation.SignInActivity
import org.techtown.soptseminar.util.SignSharedPreferences

class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboading)

        initOnBoarding()
    }

    private fun initOnBoarding() {
        if (SignSharedPreferences.getAutoMode(this)) {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

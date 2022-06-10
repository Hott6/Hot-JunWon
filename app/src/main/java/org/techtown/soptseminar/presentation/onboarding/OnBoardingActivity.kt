package org.techtown.soptseminar.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        Log.d(TAG, "OnBoardingActivity - initOnBoarding() called")
        if (SignSharedPreferences.getAutoMode(this)) {
            Log.d(TAG, "OnBoardingActivity - initOnBoarding() 자동로그인")
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        Log.d(TAG, "OnBoardingActivity - initOnBoarding() 온보딩화면 넘어가기")
    }

    companion object {
        const val TAG = "로그"
    }
}

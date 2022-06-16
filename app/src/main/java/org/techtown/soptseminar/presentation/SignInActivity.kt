package org.techtown.soptseminar.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.techtown.soptseminar.data.api.ServiceCreator
import org.techtown.soptseminar.data.entity.signin.RequestSignInData
import org.techtown.soptseminar.data.entity.signin.ResponseSignInData
import org.techtown.soptseminar.databinding.ActivitySigninBinding
import org.techtown.soptseminar.util.ResponseWrapper
import org.techtown.soptseminar.util.SignSharedPreferences
import org.techtown.soptseminar.util.enqueueUtil
import org.techtown.soptseminar.util.showToast
import retrofit2.Call

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySigninBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickLoginButton()
        setOnSignUpButton()
        initAutoLogin()
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val id = it.data?.getStringExtra("id") ?: ""
                val pw = it.data?.getStringExtra("pw") ?: ""
                binding.etId.setText(id)
                binding.etPw.setText(pw)
            }
        }

    private fun setOnClickLoginButton() {
        binding.btnLogin.setOnClickListener {
            val id = binding.etId.text.toString()
            val pw = binding.etPw.text.toString()
            loginNetWork(id, pw)
        }
    }

    private fun setOnSignUpButton() {
        val signUpIntent = Intent(this, SignUpActivity::class.java)
        binding.btnSignup.setOnClickListener() {
            resultLauncher.launch(signUpIntent)
        }
    }

    private fun initAutoLogin() {
        val id = SignSharedPreferences.getUserId(this)!!
        val pw = SignSharedPreferences.getUserPassword(this)!!

        if (SignSharedPreferences.getAutoMode(this)) {
            loginNetWork(id, pw)
        }
    }

    private fun loginNetWork(id: String, pw: String) {
        val requestSignIn = RequestSignInData(
            id = id,
            password = pw
        )

        val call: Call<ResponseWrapper<ResponseSignInData>> =
            ServiceCreator.soptService.postSignIn(requestSignIn)

        call.enqueueUtil(
            onSuccess = {
                if (binding.ckbAuto.isChecked) {
                    SignSharedPreferences.setUserId(this, id)
                    SignSharedPreferences.setUserPassWord(this, pw)
                    SignSharedPreferences.setAutoMode(this, true)
                }
                showToast("${it.data?.name}님 반갑습니다!")
                val intent = Intent(this@SignInActivity, HomeActivity::class.java).apply {
                    putExtra("username", id)
                }
                startActivity(intent)
                finish()
            },
            onError = {
                showToast("서버통신실패 실패하셨습니다")
            }
        )
    }

    companion object {
        const val TAG = "로그"
    }
}

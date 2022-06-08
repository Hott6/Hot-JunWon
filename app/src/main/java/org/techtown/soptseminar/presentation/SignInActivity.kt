package org.techtown.soptseminar.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.techtown.soptseminar.databinding.ActivitySigninBinding
import org.techtown.soptseminar.util.showToast
import org.techtown.soptseminar.data.entity.signin.RequestSignInData
import org.techtown.soptseminar.data.entity.signin.ResponseSignInData
import org.techtown.soptseminar.util.ResponseWrapper
import org.techtown.soptseminar.data.api.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySigninBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLogin()
        initSignUpButton()
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

    private fun initLogin() {
        binding.btnLogin.setOnClickListener {
            loginNetWork()
        }
    }

    private fun loginNetWork() {
        val requestSignIn = RequestSignInData(
            id = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )

        val call: Call<ResponseWrapper<ResponseSignInData>> =
            ServiceCreator.soptService.postSignIn(requestSignIn)

        call.enqueue(object : Callback<ResponseWrapper<ResponseSignInData>> {
            override fun onResponse(
                call: Call<ResponseWrapper<ResponseSignInData>>,
                responseData: Response<ResponseWrapper<ResponseSignInData>>
            ) {
                if (responseData.isSuccessful) {
                    val data = responseData.body()?.data
                    showToast("${data?.name}님 반갑습니다!")

                    // 로그인 정보 id ->
                    val intent = Intent(this@SignInActivity, HomeActivity::class.java).apply {
                        putExtra("username", binding.etId.text.toString())
                    }
                    startActivity(intent)
                } else
                    showToast("로그인에 실패하셨습니다")
            }

            override fun onFailure(call: Call<ResponseWrapper<ResponseSignInData>>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }

    private fun initSignUpButton() {
        val signUpIntent = Intent(this, SignUpActivity::class.java)
        binding.btnSignup.setOnClickListener() {
            resultLauncher.launch(signUpIntent)
        }
    }
}

//    private fun initLoginButton() {
//        val homeIntent = Intent(this, HomeActivity::class.java)
//        binding.btnLogin.setOnClickListener() {
//            with(binding) {
//                if (!etId.text.toString().isNullOrBlank() && !etPw.text.toString()
//                    .isNullOrBlank()
//                ) {
//                    Toast.makeText(this@SignInActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
//                    startActivity(homeIntent)
//                } else {
//                    Toast.makeText(this@SignInActivity, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//        }
//    }
//
//    private fun initSignUpButton() {
//        val signUpIntent = Intent(this, SignUpActivity::class.java)
//        binding.btnSignup.setOnClickListener() {
//            startActivity(signUpIntent)
//        }
//    }
//
//    private fun getUserId() {
//        binding.etId.setText(intent.getStringExtra("id"))
//    }
//
//    private fun getUserPassword() {
//        binding.etPw.setText(intent.getStringExtra("pw"))
//    }
// }

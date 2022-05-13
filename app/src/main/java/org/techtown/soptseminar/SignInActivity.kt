package org.techtown.soptseminar

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.techtown.soptseminar.databinding.ActivitySigninBinding
import org.techtown.soptseminar.week4.RequestSignInData
import org.techtown.soptseminar.week4.ResponseSignInData
import org.techtown.soptseminar.week4.ServiceCreator
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

    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val id = it.data?.getStringExtra("id") ?: ""
                val pw = it.data?.getStringExtra("pw") ?: ""
                binding.etId.setText(id)
                binding.etPw.setText(pw)
            }
        }

    fun initLogin() {
        binding.btnLogin.setOnClickListener {
            loginNetWork()
        }
    }

    fun loginNetWork() {
        val requestSignIn = RequestSignInData(
            id = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )

        val call: Call<ResponseSignInData> = ServiceCreator.soptService.postSignIn(requestSignIn)

        call.enqueue(object : Callback<ResponseSignInData> {
            override fun onResponse(
                call: Call<ResponseSignInData>,
                responseData: Response<ResponseSignInData>
            ) {
                if (responseData.isSuccessful) {
                    val data = responseData.body()?.data

                    Toast.makeText(this@SignInActivity, "${data?.name}님 반갑습니다!", Toast.LENGTH_LONG)
                        .show()

                    // 로그인 정보 id ->
                    val intent = Intent(this@SignInActivity, HomeActivity::class.java).apply {
                        putExtra("username", binding.etId.text.toString())
                    }
                    startActivity(intent)
                } else
                    Toast.makeText(this@SignInActivity, "로그인에 실패하셨습니다", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<ResponseSignInData>, t: Throwable) {
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

package org.techtown.soptseminar

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.techtown.soptseminar.databinding.ActivitySignupBinding
import org.techtown.soptseminar.week4.RequestSignUpData
import org.techtown.soptseminar.week4.ResponseSignUpData
import org.techtown.soptseminar.week4.ResponseWrapper
import org.techtown.soptseminar.week4.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clearSignUpButton()
    }

    private fun clearSignUpButton() {
        with(binding) {
            btnSignup.setOnClickListener {
                val id = etId.text.toString()
                val name = etName.text.toString()
                val password = etPw.text.toString()
                if (id.isNullOrBlank() || name.isNullOrBlank() || password.isNullOrBlank()) {
                    Toast.makeText(this@SignUpActivity, "회원가입에 실패하셨습니다", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java).apply {
                        putExtra("id", etId.text.toString())
                        putExtra("pw", etPw.text.toString())
                    }
                    setResult(Activity.RESULT_OK, intent)
                    initNetWork(RequestSignUpData(id, name, password))
                }
            }
        }
    }

    private fun initNetWork(requestSignUp: RequestSignUpData) {
//        val requestSignUp = RequestSignUpData(
//            id = binding.etId.text.toString(),
//            name = binding.etName.text.toString(),
//            password = binding.etPw.text.toString()
//        )
        val call: Call<ResponseWrapper<ResponseSignUpData>> =
            ServiceCreator.soptService.postSignup(requestSignUp)

        call.enqueue(object : Callback<ResponseWrapper<ResponseSignUpData>> {
            override fun onResponse(
                call: Call<ResponseWrapper<ResponseSignUpData>>,
                response: Response<ResponseWrapper<ResponseSignUpData>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data

                    Toast.makeText(
                        this@SignUpActivity,
                        "${data?.id} 회원가입이 완료되었습니다!",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
//                    startActivity(Intent(this@SignUpActivity, HomeActivity::class.java))
                } else
                    Toast.makeText(this@SignUpActivity, "회원가입에 실패하셨습니다", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseWrapper<ResponseSignUpData>>, t: Throwable) {
                Log.e("NetworkText", "error:$t")
            }
        })
    }
}

// fun initsignUpCompleteButton() {
//    binding.btnSignup.setOnClickListener() {
//        with(binding) {
//            if (!etName.text.toString().isNullOrBlank() && !etId.text.toString()
//                    .isNullOrBlank() && !etPw.text.toString().isNullOrBlank()
//            ) {
//                val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
//                intent.putExtra("id", etId.text.toString())
//                intent.putExtra("pw", etPw.text.toString())
//                startActivity(intent)
//                finish()
//            } else {
//                Toast.makeText(this@SignUpActivity, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//    }
// }

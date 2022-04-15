package org.techtown.soptseminar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.techtown.soptseminar.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // signUp 버튼 눌렀을 때

        binding.btnSignup.setOnClickListener() {
            with(binding) {

                if (!etName.text.toString().isNullOrBlank() && !etId.text.toString()
                        .isNullOrBlank() && !etPw.text.toString().isNullOrBlank()
                ) {
                    // finish메서드 : back 버튼과 동일한 기능 수행
                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    intent.putExtra("id", etId.text.toString())
                    intent.putExtra("pw", etPw.text.toString())
                    startActivity(intent)

                    finish() // 호출스택에서 나오기, 다시 SignUpActivity를 호출한 SignInActivity 화면으로 돌아간다.
                } else {
                    Toast.makeText(this@SignUpActivity, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }

    }

}
package org.techtown.soptseminar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.techtown.soptseminar.databinding.ActivitySigninBinding

class SignInActivity : AppCompatActivity() {

    // 클래스파일 이름 바꿀 때, AndroidManifest.xml의 <activity  android: name />  후, 클래스파일의 이름을 rename 해주어야 한다

    // 전역변수로 지연 초기화
    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLoginButton()
        initSignUpButton()
        getUserId()
        getUserPassword()
    }

    private fun initLoginButton() {
        val homeIntent = Intent(this, HomeActivity::class.java)
        binding.btnLogin.setOnClickListener() {
            with(binding) {
                if (!etId.text.toString().isNullOrBlank() && !etPw.text.toString()
                    .isNullOrBlank()
                ) {
                    Toast.makeText(this@SignInActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                    startActivity(homeIntent)
                } else {
                    Toast.makeText(this@SignInActivity, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun initSignUpButton() {
        val signUpIntent = Intent(this, SignUpActivity::class.java)
        binding.btnSignup.setOnClickListener() {
            startActivity(signUpIntent)
        }
    }

    private fun getUserId() {
        binding.etId.setText(intent.getStringExtra("id"))
    }

    private fun getUserPassword() {
        binding.etPw.setText(intent.getStringExtra("pw"))
    }
}

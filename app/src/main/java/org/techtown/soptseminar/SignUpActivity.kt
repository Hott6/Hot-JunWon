package org.techtown.soptseminar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.techtown.soptseminar.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initsignUpCompleteButton()
    }

    fun initsignUpCompleteButton() {
        binding.btnSignup.setOnClickListener() {
            with(binding) {
                if (!etName.text.toString().isNullOrBlank() && !etId.text.toString()
                    .isNullOrBlank() && !etPw.text.toString().isNullOrBlank()
                ) {
                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    intent.putExtra("id", etId.text.toString())
                    intent.putExtra("pw", etPw.text.toString())
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@SignUpActivity, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}

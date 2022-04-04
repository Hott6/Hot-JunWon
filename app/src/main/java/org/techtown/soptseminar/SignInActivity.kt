package org.techtown.soptseminar

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.techtown.soptseminar.databinding.ActivitySigninBinding

class SignInActivity : AppCompatActivity() {

    // 클래스파일 이름 바꿀 때, AndroidManifest.xml의 <activity  android: name />부분의 name을 바꿔준 후, 클래스파일의 이름을 rename해주어야 한다

    // 전역변수로 지연 초기화
    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        // setContentView(R.layout.activity_main) 대신
        super.onCreate(savedInstanceState)

        // LayoutInflater 클래스의 inflate메소드는 xml로 정의된 Layout을 View로 전개할 때 사용하며 정의된 layout xml의 resouce의 id를 함수 파라메터로 전달하면 전개된 View를 리턴한다
        // 자동 생성된 뷰 바인딩 클래스 내부에 inflate 메서드를 활용하여 바인딩 클래스 인스턴스 생성
        binding = ActivitySigninBinding.inflate(layoutInflater)
        // 레이아웃 내부 최상위 위치 뷰 표시
        setContentView(binding.root)


        val intent = Intent(this, HomeActivity::class.java)
        val intent2 = Intent(this, SignUpActivity::class.java)

        // Login
        // binding 변수를 통해 xml 파일 내의 뷰 Id 접근 가능
        binding.btnLogin.setOnClickListener() {
            // 버튼 이벤트 : 버튼 눌렀을 때 로그인 되게 하기?

            val id: String = binding.etId.text.toString() // etId : editview의 id
            val pw: String = binding.etPw.text.toString() // etPw : editview의 id
            if (id != "" && pw != "") {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }else{
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }


        }

        // SignUp
        binding.btnSignup.setOnClickListener(){

            // 회원가입 페이지로 이동!
            startActivity(intent2)
        }
    }
}




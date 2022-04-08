
# Seminar 1

##  SignInActivity
```kotlin
class SignInActivity : AppCompatActivity() {
    // 클래스파일 이름 바꿀 때, AndroidManifest.xml의 <activity  android: name />  후, 클래스파일의 이름을 rename 해주어야 한다

    // 전역변수로 지연 초기화 : 코틀린은 자바와 다르게 원래 선언과 동시에 초기화해주어야하지만, lateinit 키워드를 통해 늦게 초기화할 수 있음.
    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {

    
        super.onCreate(savedInstanceState)

        // 자동 생성된 뷰 바인딩 클래스 내부에 inflate 메서드를 활용하여 바인딩 클래스 인스턴스 생성  
        
        // LayoutInflater 클래스의 inflate메소드는 xml로 정의된 Layout을 View로 전개할 때 사용하며  
        // 정의된 layout xml의 resouce의 id를 함수 파라메터로 전달하여 전개된 View를 리턴한다
        binding = ActivitySigninBinding.inflate(layoutInflater)

        // 레이아웃 내부 최상위 위치 뷰 표시
        setContentView(binding.root)

        // Intent 를 사용하면 앱을 구성하는 화면을 새로 띄우거나 화면 간에 데이터를 전달할 수 있다.
        val homeIntent = Intent(this, HomeActivity::class.java)
        val signUpIntent = Intent(this, SignUpActivity::class.java)


        // SignIn
        // binding 변수를 통해 xml 파일 내의 뷰 Id 접근 가능
         binding.btnLogin.setOnClickListener() {

            with(binding){
            
                if (!etId.text.toString().isNullOrBlank() && !etPw.text.toString().isNullOrBlank()) {
                    // Toast : 잠깐 보여주는 메세지
                    Toast.makeText(this@SignInActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                    startActivity(homeIntent)
                }else{
                    Toast.makeText(this@SignInActivity, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                }
            }
            
        }

        // SignUp
        binding.btnSignup.setOnClickListener(){

            // 회원가입 페이지로 이동!
            startActivity(signUpIntent)
        }

        // intent 속 id, pw 정보 받아서, key = "id"/"pw"에 해당하는 value값에 저장
        binding.etId.setText(intent.getStringExtra("id")) 
        binding.etPw.setText(intent.getStringExtra("pw"))

        }
    }
```
- `with() 메서드` : 객체 이름을 반복하지 않고 사용하고 싶을 때 with함수를 사용하면 편하다.

## SignUpActivity

```kotlin
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // signUp 버튼 눌렀을 때

        binding.btnSignup.setOnClickListener(){
            with(binding) {

                if (!etName.text.toString().isNullOrBlank() && !etId.text.toString().isNullOrBlank() && !etPw.text.toString().isNullOrBlank()) {
                    // finish메서드 : back 버튼과 동일한 기능 수행
                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    intent.putExtra("id", etId.text.toString())
                    intent.putExtra("pw", etPw.text.toString())
                    startActivity(intent)

                    finish() // 호출스택에서 나오기, 다시 SignUpActivity를 호출한 SignInActivity 화면으로 돌아간다.
                } else {
                    Toast.makeText(this@SignUpActivity, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

}
```
- `putExtra` 메서드
    - Intent를 통해 액티비티를 전환할 때에 putExtra를 통해 String이나 Int 등 간단한 데이터를 전달할 수 있다. putExtra(key: String, value: 간단한 데이터) 의 형식을 띤다

## Seminar 1에서 기억해야할 부분
#### 1. xml파일 자동 정렬
`command`+`option`+`L`
#### 2. 스크롤뷰 
스크롤뷰 안에는 View 하나만 넣을 수 있다.
#### 0dp vs match_parent
match_parent : 0dp를 쓰고 제약을 부모에 건거랑 같다고 볼 수 있지
#### 3. res/values/String폴더
String폴더에 text내용을 저장한 후, 사용하면 굉장히 편리하다 :D
```xml
<resources>
    <string name="app_name">SoptSeminar</string>
    <string name="profile">이름 : 이준원\n나이 : 26살\nMBTI : ISFJ\n스크롤뷰 만들자!!\n스크롤뷰 만들자!!\n스크롤뷰 만들자!!\n스크롤뷰 만들자!!\n스크롤뷰 만들자!!\n스크롤뷰 만들자!!\n스크롤뷰 만들자!!\n스크롤뷰 만들자!!\n스크롤뷰 만들자!!\n스크롤뷰 만들자!!\n스크롤뷰 만들자!!\n스크롤뷰 만들자!!</string>
</resources>
```
#### 4. 화면 비율 1:1로 맞추기 
- Ratio
위젯의 치수(dimension) 대한 비율을 이용하여 다른 부분의 치수를 지정할 수도 있다. 비율을 사용하기 위해서는 최소한 하나의 치수(dimension)를 0dp(MATCH_CONSTRAINT)로 지정해야만 한다. 그리고 layout_constraintDimensionRatio를 사용하여 비율을 지정하면 된다. 비율을 입력하는 방식에는 아래와 같이 두 가지의 방식이 있다.
```
- app:layout_constraintDimensionRatio="1:1" (width:height로 표현하는 방법)
- app:layout_constraintDimensionRatio="1.0" (width와 height의 비율을 float값으로 표현하는 방법)
- android:scaleType="fitXY" (사진 공백없이 꽉채우기 !)

```
#### 5. 공백을 구분 
    - if (!(id.equals("")) && !(pw.equals(""))) 와 같음
    - 방법 1 : isNullOrBlank 메서드를 쓰는게 더 좋다
    - 방법 2 : isEmpty 메서드
#### 6. registerForActivityResult

요 방법으로 성장과제 2-1을 하지 않았는데, 코드의 이해가 어렵다..  
좀 더 공부해봐야 할듯!!
```kotlin
    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {result -> if(result.resultCode == Activity.RESULT_OK){
            val data: Intent? = result.data
            val userId = data?.getStringExtra("id")
            val userPw = data?.getStringExtra("pw")
            binding.etId.setText(userId)
            binding.etPw.setText(userPw)
          }
        }
```
## 실행화면
|로그인 -> 자기소개화면|회원가입 -> 로그인 -> 자기소개화면|
|:--:|:--:|
|<img width = "600 " src= "https://user-images.githubusercontent.com/87055456/162427967-7fe63b60-a514-43c6-a59e-150f6656c3f2.gif">|<img width = "600 " src= "https://user-images.githubusercontent.com/87055456/162428001-f9f72df3-b4a6-4449-8132-9cba526decb1.gif"> |

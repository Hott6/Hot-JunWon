### THE-SOPT-30th Android Part
## [ seminar 목차 ]

[⚡️ Seminar 1](#seminar1)

[⚡️ Seminar 2](#seminar2)

[⚡️ Seminar 3](#seminar3)  

[⚡️ Seminar 4](#seminar4)

[⚡️ Seminar 7](#seminar7)  
# seminar1
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

# Seminar2
# 필수과제

# Fragment
프래그먼트는 텍스트 뷰나 버튼처럼 액티비티 화면을 구성하는 뷰이다.  
그러나, 그 자체만으로는 화면에 아무것도 출력되지 않는데, 다른 뷰와 달리 액티비티처럼 동작한다. 
즉, 액티비티에 작성할 수 있는 모든 코드는 프래그먼트에서 사용가능하다.  

프래그먼트는 태블릿처럼 화면이 넓은 기기에서 동작하는 앱을 개발할 수 있도록 제공된다. 한 화면은 하나의 액티비티 클래스에서 작성해야하는데, 화면이 크면
액티비티 클래스에 너무 많은 코드를 작성해야하는 문제점이 있다.   
예를 들어, 한 화면이 너무 커서 왼쪽, 오른쪽 화면의 내용을 2개의 클래스로 분리해서 작성하고 액티비티 클래스에서 이 두 클래스를 조합하려한다. 그런데 두 클래스가 한 화면에 출력되어야하므로 각각의 클래스를 뷰클래스로 만들어야 한다. 근데, `뷰클래스는 액티비티가 아니라서` 액티비티에 작성된 모든 내용을 뷰 클래스에 담을 수 없다.  

그래서, 이때 `프레그먼트`를 사용하는 것이다!!  

- Fragment는 독립적으로 존재할 수 없고, 반드시 Activity나 다른 Fragment에 호스팅되어야 함!  
- Fragment는 자체 레이아웃(xml), 자체 생명주기, 자체 이벤트처리를 가질 수 있다.  
- 여러 Activity에서 하나의 Fragment를 사용해서 UI 표현 가능 -> 재사용성이 뛰어남  
- 런타임 동안 Activity 내에서 Fragment의 추가/삭제/삭제 가능


## 코드단에서 Fragment inflate시키기 : FragmentTransaction
액티비티의 레이아웃 XML에 등록하여 프래그먼트를 출력할 수도 있지만, 유연하지 못하다. 따라서, 코드단에서 inflate하는 것을 권장한다고 한다.  

`FragmentTransaction`는 액티비티에서 프래그먼트를 추가, 교체, 삭제 작업을 수행하기 위해 사용한다.   
- FragmentManager 클래스에 구현되어 있다.(내부 클래스)
- 수행한 transaction의 상태를 백스택(BackStack)에 저장 가능  
- Fragment 전환 애니메이션 설정 가능(예제에서는 버튼클릭)  

코드에서 프그래먼트를 동적으로 제어(제거, 추가 등)을 하려면 `FragmentManager`로 만든 `FragmentTransaction`클래스의 함수를 이용해야 한다.  
- FragmetManager -> FragmentTransaction -> add(fragment객체 추가) -> commit(fragment 객체 화면에 출력)
```kotlin
package org.techtown.soptseminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.soptseminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var position = FOLLOWER_FRAGMENT
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initTransactionEvent()
    }

    // 프래그먼트 동적 제어 함수
    fun initTransactionEvent() {

        val fragment1 = FollowerFragment()
        val fragment2 = RepositoryFragment()

        // FragmentTransaction의 객체의 add()함수를 이용해 프래그먼트 객체를 화면에 출력하는 코드

        supportFragmentManager.beginTransaction().add(R.id.fragment_main, fragment1).commit()

        // 버튼을 누르면 프래그먼트 교체
        binding.btnFollower.setOnClickListener {
            if (position == REPO_FRAGMENT) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment1)
                    .commit()
                position = FOLLOWER_FRAGMENT
            }
        }
        binding.btnRepository.setOnClickListener {
            if (position == FOLLOWER_FRAGMENT) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment2)
                    .commit()
                position = REPO_FRAGMENT
            }
        }
    }

    companion object {
        const val FOLLOWER_FRAGMENT = 1
        const val REPO_FRAGMENT = 2
    }

}
```
다음 코드를 하나하나 뜯어서 분석해보자 :D  
```kotlin
supportFragmentManager.beginTransaction().add(R.id.fragment_main, fragment1).commit()  


// 1. 액티비티와 연관있는 프레그먼트와 interact하기 위해 FragmentManager객체 반환
val fragmentManager: FragmentManager = supportFragmentManager

// 2. FragmentTransaction객체 생성
 val transaction : FragmentTransaction = fragmentManager.beginTransaction()
 
// 3.dd()메서드를 통해 프레그먼트 추가 
// R.id.fragment_main : 프래그먼트가 출력될 뷰의 id값
transaction.add(R.id.fragment_main, fragment1)

// 4. commit() 메서드를 호출하는 순간 화면에 적용된다.
transaction.commit()
```
- 코드에서 동적으로 프래그먼트 제어하는 함수
```
add(int containerViewId, Fragment fragment) : 새로운 프레그먼트를 화면에 추가, 첫 번째 매개변수가 프래그먼트가 출력될 뷰의 id값

replace(int containerViewID, Fragment fragmnet): 추가된 프래그먼트로 대체한다

remove(Fragment fragment) : 추가된 프래그먼트를 제거한다

commit(): 화면에 적용한다.
```
## Fragmnet 내 ViewBinding
이미 죽은 프레그먼트뷰를 binding변수가 계속 참조할 수 있다.  
따라서, Activity내 뷰바인딩하는 것처럼 하면 안된다!  

```kotlin
class FollowerFragment : Fragment() {

    private var _binding: FragmentFollwerBinding? = null
    // 코틀린의 property를 활용 : field로만 구성된 자바와 다르게 코틀린은 field+getter + setter로 구성된 property활용 가능
    private val binding get() = _binding!! // binding 변수의 getter 정의
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollwerBinding.inflate(inflater, container, false)
        initAdapter()
        return binding.root
    }

    // onDestroyView()에서 binding객체 참조 해제
   override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
```
## RecyclerView

- ViewHolder 패턴이 강제되기 때문에 스크롤 시 마다 View를 Create하는 게 아닌 재활용할 수 있다.  
- ItemDecoration, ItemAnimator를 활용하여 보다 유연한 뷰 커스텀 가능

리사이클러 뷰는 목록을 만드는데 `RecyclerView`클래스만으로는 화면에 아무것도 출력되지 않는다.  
따라서, 다음과 같은 구성요소를 반드시 이용해야 한다!!  
```
(정말 간단한 설명)
1. ViewHolder : 항목에 필요한 뷰 객체를 가진다.
2. Adapter : 항목을 구성한다.
3. LayoutManager : 항목을 배치한다.

(옵션) ItemDecoration : 항목을 꾸민다.
```
## 1. item Layout 파일 만들기
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@drawable/layout_background"
    android:padding="10dp">


    <ImageView
        android:id="@+id/iv_profile"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:src="@drawable/man"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintDimensionRatio="1:1"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintWidth_percent="0.25" />

    <TextView
        android:id="@+id/tv_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="20dp"
        android:text="이름"
        android:textColor="@color/black"
        android:textSize="20sp"
        app:layout_constraintStart_toEndOf="@id/iv_profile"
        app:layout_constraintTop_toTopOf="@id/iv_profile" />

    <TextView
        android:id="@+id/tv_introduce"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="자기소개 텍스트입니다."
        android:textColor="@color/black"
        android:textSize="14sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="@id/tv_name"
        app:layout_constraintTop_toBottomOf="@id/tv_name" />
    <ImageView
        android:id="@+id/iv_draghandle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginEnd="10dp"
        android:src="@drawable/drag_handle"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        />
</androidx.constraintlayout.widget.ConstraintLayout>
```
- 실행화면
<img src="https://user-images.githubusercontent.com/87055456/164721800-c5bab675-a5e4-42cb-8954-8460573fef10.png">

## 2. Item data class 만들기
- Data class : 코틀린에서 제공하는 데이터만을 다루는 클래스  
```kotlin
data class RepositoryData(val title: String, val description: String)
```
data 들은 `ViewHolder`가 가지고 있는 `View(Item Layout)`에 담아줘야 한다.  
따라서, 이번에는 ViewHolder를 만들어보자!
## 3. UI 요소가 들고 있는 ViewHolder 만들기

- ViewHolder
- RecyclerView의 재활용되는 `Item Layout(View)`를 붙잡고 관리  
- Adapyer에서 전달받은 데이터를 `Item Layout`에 `bind`해주는 역할
- 한 번 `ViewHolder`가 만들어지면 계속해서 재활용된다!! -> 효율적  

Adapter 클래스 안의 내부 클래스로 구현할 것이므로, 다음 챕터에서 구현하겠다 :D
## 4. RecyclerView Adapter 만들기
- Adapter는 크게 2가지 기능을 한다.
    - 1. `ViewHolder`를 생성하고 `뷰(ItemLayout)`을 `ViewHolder`에 넘겨준다.  
    - 2. 리스트로 보여줄 `Data`의 정보를 각 `ViewHolder` 에 전달해준다.  
### Adapter 준비
리사이클러 뷰를 위한 `Adapter`는 `RecyclerView.Adapter`를 상속받아 작성한다. < >안에 해당 Adapter가 데이터를 전할 `ViewHolder `작성  

- 다음 코드는 필수적으로 구현해주어야할 메서드와 `Adapter`클래스의 기본 구조다.
```kotlin
// RecyclerView.Adapter<>를 상속받는다.
class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>(){

    
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
    

}
```
- getItemCount() : `항목 개수를 판단`하려고 자동으로 호출된다.  
- onCreateViewHolder() : 항목의 뷰를 가지는 `뷰 홀더를 준비`하려고 자동 호출된다.  
- onBindViewHolder() : 뷰 홀더의 `뷰에 데이터를 출력`하려고 자동으로 호출된다. 
이제 메서드 하나하나 구현 해보자!  

### 1)  ViewHolder

View Binding기법을 이용하여 XML파일에 해당하는 바인딩 객체를 가지고온다.  
```kotlin
class RepositoryViewHolder(private val binding: ItemRepositorySampleListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: RepositoryData) {
            binding.tvTitle.text = data.title
            binding.tvDescription.text = data.description
        }
    }
```
- Binding 객체를 생성자로 가지는 ViewHolder 클래스 생성
- RecyclerView.ViewHolder를 상속
- 클래스 ViewHolder는 생성자로 View를 요구하므로, binding.root를 넘겨준다.
- 이때, root뷰는 재활용되는 Item Layout의 root뷰이다.
```kotlin
fun onBind(data : UserData){
    binding.tvName.text = data.name
    binding.tvIntroduce.text = data.introduce
}
```
- onBind 메서드 : ViewHolder가 가진 View에 Adapter로부터 전달받은 `데이터`를 붙여주는 함수  
- `OnBindViewHolder` 메서드를 호출 시 실행되는 메서드이다!!

### 2) getItemCount() 메서드
- RecyclerView로 보여줄 전체 데이터의 개수 반환  

getItemCount() 메서드는 자동 호출되어 항목의 개수를 판단한다.  
이 함수가 반환 숫자만큼 `onBinderHolder`메서드가 호출되어 항목을 만든다!!  만약 이 함수가 0을 반환하면 화면에 아무것도 출력되지 않는다.
```kotlin
val repositoryList = mutableListOf<RepositoryData>()
    
override fun getItemCount(): Int {
        return repositoryList.size
    }
```

### 3) onCreateViewHolder
- ViewHolder를 생성  
- ItemLayout의 Binding 객체를 만들어 ViewHolder의 생성자로 넘겨주는 메서드  
- onCreatViewHolder() 함수에서 반환한 ViewHolder 객체는 자동으로 onBindViewHolder() 함수의 매개변수로 전달된다.
```kotlin
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding =
            ItemRepositorySampleListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return RepositoryViewHolder(binding)
    }
```
위의 코드를 하나하나 뜯어보자!  
- Binding객체를 생성할 때  
    - LayoutInflater
            ``` 
            LayoutInflater : RecyclerView에서 뷰를 만들어줄 때 사용
            Activity, Fragment와 달리 RecyclerView에서는 ViewHolder에서 뷰를 만들 때 LayoutInflater를 사용한다.
            ```
        
    - .from()
        ```
        뷰를 만들어주는 LayoutInflater에는 resource에도 접근하기 위해 Context가 필요하다.  
        .from()메서드 : Context로부터 접근할 layout에 대한 정보를 가져오는 메서드
        ```
    - Parent : 뷰그룹
- 반환값 : `MyViewHolder(binding)`  
    ```
    이렇게 생성된 View 객체를 ViewHolder에 참조할 뷰 객체를 넘겨준다.  
    이는 자동으로 FolloweViewHolder -> onBindViewHolder() 함수의 첫 번째 매개변수로 전달된다.
    ```

### 4) onBindViewHolder 메서드

- onBindViewHolder() : 재활용 되는 뷰(ViewHolder)가 호출하여 실행되는 함수  
- `ViewHolder`와 `postion`의 데이터를 결합시킨다!! 
```kotlin
override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.onBind(repositoryList[position])
    }
```

# 5. RecyclerView를 layout상에 배치하기

- itemCount : Preview상에서 몇개의 아이템을 띄워줄지 결정  
- listitem : Preview상에 어떤 listLayout을 띄울지 결정  
두 속성 모두 `tools`속성이라 앱에 반영되지 않는다. 오로지 Editor에서만 반영  

- spanCount(그리드): 한줄에 몇 개의 아이템을 배치할지 결정 
- (중요)layoutManager : item의 배치 규칙을 관리하는 클래스  
```
LinearLayoutManager : 선형(수직/수평)으로 item을 보여줄 때 사용

GridLayoutManager : 격자식으로 item을 보여줄 때 사용 (코드 or xml에서 가로로 몇 칸 보여줄 지 설정 가능)

StaggeredGridLayoutManager : 높이가 불규칙한 형태의 격자식 item 리스트를 보여줄 때 사용
```

- 다음은 리스트를 그리드형식으로(GridLayoutManager) xml상에서 선언
```xml
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".RepositoryFragment">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rv_repository"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
        app:spanCount="2"
        tools:itemCount="6"
        tools:listitem="@layout/item_repository_sample_list" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
코드단에서도 레이아웃 매니저 설정가능!
```kotlin
// 그리드
binding.rvFollower.layoutManager = GridLayoutManager(this,2) // 2는 spanCount

// 리니어
binding.rvMyfollower.layoutManager = LinearLayoutManager(this)
```

# 6. 코드단에 연결!

```kotlin
package org.techtown.soptseminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.techtown.soptseminar.databinding.FragmentRepositoryBinding

class RepositoryFragment : Fragment() {
    private lateinit var repositoryAdapter: RepositoryAdapter
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding!!
    // onCreateView 메서드는 반드시 있어야 한다.
    // 이 함수는 자동 호출되며, 반환한 View객체가 화면에 출력된다.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        initAdapter()
        return binding.root
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }

    private fun initAdapter() {
        repositoryAdapter = RepositoryAdapter() // 1. Adapter 초기화
        with(binding) {
            rvRepository.adapter = repositoryAdapter // 2. RecyclerView와 Adapter 연동!!
            rvRepository.addItemDecoration(ItemDecoration(resources.getDimensionPixelOffset(R.dimen.margin_15), 2))
            // resources : 옛날이름 getResources 메서드
            // getDimesionPixelOffset메서드 : getDimension메서드와 비슷, (부동소수점을 버림 -> int형)
        }

        // 3. Adapter에 List로 보여줄 데이터 넣어준다.
        repositoryAdapter.repositoryList.addAll(
            listOf(
                RepositoryData("today_junelog", "파이썬 + 자료구조/알고리즘 레포지토리"),
                RepositoryData("Android_Bymurjune", "자바 + 코틀린 + 안스 레포지토리"),
                RepositoryData("Hott6/Hot-JunWon", "30기 Sopt 안드로이드 과제 레포지토리"),
                RepositoryData(" murjune.github.io ", "기술블로그"),
                RepositoryData(
                    "OpportunityPod/hackerrank-interview-preparation-kit",
                    "Hacker-Rank 인터뷰 키트 스터디 과제/발표 레포지토리"
                ),

                )
        )
        // 4. Adapter에 전체 리스트의 데이터가 갱신되었다고 알려주는 메서드
        repositoryAdapter.notifyDataSetChanged()
    }
}
```
- notifyDataSetChanged()  
```
리사이클러 뷰에 항목이 출력된 후 동적으로 새로운 항목을 추가하거나 화면에 보이는 항목을 제거해야하는 경우가 있다.  
이 작업은 항목을 구성하는 데이터에 새로운 데이터를 추가하거나 제거한 후 어댑터의 `notifyDataSetChanged()`함수를 호출해주면 된다!
```

# 성장과제
## 1. ItemView 터치시 DetailActivity로 이동
- FollowerAdapter() 클래스파일
```kotlin
class FollowerAdapter() :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(),
    ItemTouchHelperCallback.OnItemMoveListener {

    // 클릭 리스너 선언
    private lateinit var itemClickListener: ItemClickListener

    val followerList =
        mutableListOf<FollowerData>()

    override fun getItemCount(): Int {
        return followerList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {

        val binding =
            ItemFollowerSampleListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return FollowerViewHolder(binding)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {

        // itemView에 클릭리스너 세팅
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }

        holder.onBind(followerList[position])
    }

    class FollowerViewHolder(
        val binding: ItemFollowerSampleListBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: FollowerData) {
            binding.ivProfile.setImageResource(data.gender)
            binding.tvName.text = data.name
            binding.tvIntroduce.text = data.introduce
        }
    }

    // 성장 과제 1
    // https://sunpil.tistory.com/181
    // 클릭 인터페이스 정의
    interface ItemClickListener {
        fun onClick(view: View, postion: Int)
    }

    // 클릭리스너 등록 메서드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}

```
- Adapter 클래스 내부에 `ItemClickListener 인터페이스` 정의
- Adapter 클래스에 멤버변수 `itemClickListener: ItemClickListener` 선언
- itemClickListener를 초기화하는 역할을 하는 `setItemClickListener()`메서드를 구현  
- `onBindViewHolder`메서드에서 뷰홀더안에 있는 `itemView`에 클릭리스너를 세팅해놓았다.  

- FollowerFragment 클래스파일
```kotlin
private fun initAdapter() {
    ...

followerAdapter.setItemClickListener(object : FollowerAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                val gender = followerAdapter.followerList[position].gender
                val name = followerAdapter.followerList[position].name
                val introduce = followerAdapter.followerList[position].introduce

                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("gender", gender)
                    putExtra("name", name)
                    putExtra("introduce", introduce)
                }
                startActivity(intent)
            }
        })
}
```
- `initAdapter()메서드` 내에 followerAdapter.setItemClickListener()의 매개변수로 `FollowerAdapter.ItemClickListener`를 구현한 익명의 클래스를 넣어준다.  
- 익명의 클래스내에 onClick메서드내에 position에 해당하는 뷰를 클릭시 DetailActivit로 `Intent`할 수 있도록 한다.  
- 이때, position에 위치하는 뷰홀더의 정보를 Intent에 담는다.  

- 
```kotlin
class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        binding.ivProfile.setImageResource(intent.getIntExtra("gender",-1))
        binding.tvName.text = intent.getStringExtra("name")
        binding.tvDescription.text = intent.getStringExtra("introduce")
        setContentView(binding.root)
    }
}
```
- `setImageResource` 메서드를 통해 image정보를 불러온다. (gradle을 사용하면 더 좋은 성능을 낼 수 있다고 한다...)  
- `getStringExtra` 메서드를 사용하여 나머지 정보도 얻어와 세팅한다.  

## 2. ItemDecoration을 활용하여 리스트 간 간격 또는 구분선 주기

- ItemDecoration클래스 파일
```kotlin
//  생성자에서 margin값과 spanCount 값을 초기화 해준다.
class ItemDecoration(private val margin: Int, private val spanCount : Int) : RecyclerView.ItemDecoration() {

    // getItemOffsets 개별 항목을 꾸밀 때 호출
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        // FollowerRecyclerView인 경우 (Linear)
        if (spanCount == 1){
            outRect.set(margin,margin,margin,margin) // left, top, right, bottom
        }
        // RepositoryRecyclerView(Grid)
        else{
            outRect.top = margin
            outRect.bottom = margin/2
            if(position % spanCount == 0){
                outRect.left = margin
                outRect.right = margin/2
            }else{
                outRect.left = margin/2
                outRect.right = margin
            }
        }
    }
}
```
- ItemDecoration을 상속받아 커스텀 클래스를 만들었다.  
- ItemDecoration은 다음과 같은 3가지 함수를 제공ㅎ해준다
    - onDraw() : 항목이 배치되기 전에 호출  
    - onDrawOver() : 항목이 모두 배치된 후 호출  
    - getItemOffsets() : 개별 항목을 꾸밀 때 호출
  
- FollowerFragment클래스 파일
```kotlin
private fun initAdapter() {

    ...

    with(binding) {
                rvFollower.adapter = followerAdapter
                rvFollower.addItemDecoration(
                    ItemDecoration(
                        resources.getDimensionPixelOffset(R.dimen.margin_15),
                        1
                    )
                )
}
```
이렇게 만든 아이템 데코레이션 객체를 리사이클러 뷰에 적용할 때 addItemDecoration()를 사용한다.  
#### getDimension() vs getDimensionPixelSize() vs getDimensionPixelOffset
- `getDimension()`현재 디스플레이 메트릭으로 조정된 차원 값인 부동 소수점 숫자를 반환합니다.(Float)
- `getDimensionPixelSize()` 정수를 반환합니다. (반올림,Int) 
두 메서드(함수)의 차이는 dp를 px로 변환하면서 소수점까지 반환하느냐, 반올림해서 정수로 반환하느냐이다.  
추가로 `getDimensionPixelOffset`메서드는 float -> int로 변환하여 반환한다. (버림, Int형)

## 도전 과제 3 - 2
## notifyDataSetChanged
지금까지는 `notifyDataSetChanged`메서드를 사용해서 data set이 changed 됐다는 것을 알렸다.  
> RecyclerView에 표현할 데이터를 업데이트하기 위해 사용하였다.  

그러나, `notifyDataSetChanged`메서드에는 모든 항목을 통째로 날린 후, 다시 그리는 비효율적인 방법을 사용한다.  
## 해결책 1. 적절한 notify~ 메서드를 사용한다.
```kotlin
override fun onItemMoved(fromPos: Int, toPos: Int) {
        Collections.swap(followerList, fromPos, toPos)
        notifyItemMoved(fromPos, toPos) # position에서 아이템이 삭제되었다고 알리는 메서드
    }

    override fun onItemSwiped(pos: Int) {
        followerList.removeAt(pos)
        notifyItemRemoved(pos) # fromPosition에서 toPosition으로 아이템이 이동
    }
```
하지만, 모든 상황에 따라 notify~메서드를 쓰는 것은 비효율적이라 할 수 있다.

## 해결책 2. DiffUtil
이에 변경된 데이터에 한해서만 adapter를 변경할수있도록 고안한것이 `diffutil 클래스`이다.  
- DiffUtil.Callback()를 상속
```
diffUtil.Callback()은 추상 클래스이며 4가지 추상메서드와 1개의 비추상 메서드로 이루어져있다
```
- 추상 메서드(오버로딩 해주어야 함)
- `getOldListSize()`: 이전 목록의 개수를 반환.
- `getNewListSize()`: 새로운 목록의 개수를 반환.
- `areItemsTheSame(int oldItemPosition, int newItemPosition)`: 두 객체가 같은 항목인지 여부를 결정
- `areContentsTheSame(int oldItemPosition, int newItemPosition)`: 두 항목의 데이터가 같은지 여부를 결정  

다음과 같이 MyDiffUtilCallback클래스를 구현해주었다.
```kotlin
class MyDiffUtilCallback(private val oldItemList: List<Any>, private val newItemList: List<Any>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItemList.size

    override fun getNewListSize(): Int = newItemList.size

    // 두 리스트의 id가 같은지 확인, 여기서는 Name으로 대체
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItemList[oldItemPosition]
        val newItem = newItemList[newItemPosition]
        // is는 자바에서 istanceOf , 형변환도 안해줘도 됨
        return if (oldItem is FollowerData && newItem is FollowerData) {
            oldItem.name == newItem.name
        } else {
            false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemList[oldItemPosition] == newItemList[newItemPosition]
```
### areItemsTheSame() vs areContentsTheSame() 메서드  

- areItemsTheSame()  
 각각의 리스트에서 꺼내온 두 객체가 같은 아이템인지 확인하는 작업
 만일 아이템에 유일하게 식별할 수 있는(DB로 치면 Primary Key와 같은 기능을 하는) ID가 있다면, areItemsTheSame() 메서드 내에서 두 객체의 ID를 비교해주면 된다.  
 원래 `id`같은걸 많이하는데 예시에서는 id값이 없어서 세미나 과제에서는 `followerName`로 대체해줬다.
 
 - areContentsTheSame()  
 두 아이템의 ID를 비교하는 것이 아닌 실제 내용물까지도 정확히 일치하는지를 확인하는 메서드다.  
 
 ```
 areItemsTheSame에서는 ID에 해당하는 필드만 비교하고, areContentsTheSame에서는 두 객체 전체를 비교해야 한다는 것을 구분해서 기억하자 :D
 ```
 - 따라서, itemList를 갱신할 때 다음 replaceItemList()메서드를 사용해서 리스트를 갱신한 뒤 View를 업데이트해줄 수 있다.
 ```kotlin
fun replaceItemList(newItemList: List<FollowerData>?) {
        newItemList?.let {
            val diffCallback = MyDiffUtilCallback(followerList, it)
            val diffResult = DiffUtil.calculateDiff(diffCallback)

            followerList.run {
                clear()
                addAll(it)
                diffResult.dispatchUpdatesTo(this@FollowerAdapter)
            }
        }
    }
```

## 실행화면
|필수과제|아이템 클릭시 Detail화면으로 이동| Drag & Swipe|  
|:--:|:--:|:--:|
|<img width = "400 " src= "https://user-images.githubusercontent.com/87055456/164716588-12fd025f-0f6a-4312-bab1-9905b2261443.gif">|<img width = "400 " src= "https://user-images.githubusercontent.com/87055456/164717101-8a13453d-92fb-49be-b338-a233f2e4729e.gif"> |<img width = "400 " src= "https://user-images.githubusercontent.com/87055456/164717574-7ceaf940-9fa5-444c-b221-713bf880d7c9.gif"> |

# Seminar3

## 필수 과제

### FontFamily - 폰트 추가 (noto sans kr)
```xml
<font-family xmlns:android="http://schemas.android.com/apk/res/android">
    <font
        android:font="@font/noto_sans_kr_regular"
        android:fontWeight="400"/>
    <font
        android:font="@font/noto_sans_kr_bold"
        android:fontWeight="700"/>
    <font
        android:font="@font/noto_sans_kr_medium"
        android:fontWeight="500"/>
</font-family>
```
- font-family를 만들어서 layout 파일에서 font를 설정할 때 편리하게 해준다. 
- fontWeight : 같은 폰트 내의 다른 스타일을 구별할 수 있다.  
- `includeFontPadding` : 폰트를 추가하면 자동으로 여백이 생기는 현상이 발생하기에 디자인을 적용하는데 불편함이 있다. 해당 속성을 `false`로 설정하면 여백을 없앨 수 있다.(res/values/themes)
```xml
<item name="android:includeFontPadding">false</item>
```
### Selector - 버튼 클릭시 색 바꾸기

- drawable파일에 `selected`여부로 false일 때와 true일 때의 색상을 구분해준다.  
- `selector-button`이라는 layout파일을 만들어 다른 background파일과 구분해주었다.
```xml
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/background_button_rectangle_unclicked" android:state_selected="false" />
    <item android:drawable="@drawable/background_button_rectangle_clicked" android:state_selected="true"/>
</selector>
```
### Glide 활용하기
- 2주차까지는 `setImageResource`메서드를 활용하여 이미지를 불러왔다면 이번 3주차에서는 `Glide` 사용하여 uri로 사진 불러와주었고, 사진을 원형으로 만들어 주었다.  
- ViewHolder의 onBind메서드 안에 Glide를 이용해 url에서 이미지를 불러와 원형으로 그리도록 하였다.
```kotlin
Glide.with(binding.root)
    .load(data.gender)
    .circleCrop()
    .into(binding.ivProfile)
```
### TapLayout

```kotlin
package org.techtown.soptseminar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import org.techtown.soptseminar.adapter.HomeViewPagerAdapter
import org.techtown.soptseminar.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    ...

    private fun initAdapter() {
        val fragmentList = listOf(FollowerTapFragment(), FollowingTapFragment())

        homeViewPagerAdapter = HomeViewPagerAdapter(this)
        homeViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpHomefragment.adapter = homeViewPagerAdapter
    }

    private fun initTabLayout() {
        val tabLabel = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.homeTablayout, binding.vpHomefragment) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }

    ...
}
```
# 성장과제
- NestedScrollableHost.kt를 만들어준다.(feat. [Google.github](https://github.com/android/views-widgets-samples/blob/master/ViewPager2/app/src/main/java/androidx/viewpager2/integration/testapp/NestedScrollableHost.kt))  
- 태그를 이용해 ViewPager2를 감싸준다.

```xml
<org.techtown.soptseminar.NestedScrollableHost
    android:layout_width="match_parent"
    android:layout_height="0dp"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/home_tablayout">

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/vp_homefragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/background_gray" />
</org.techtown.soptseminar.NestedScrollableHost>
```
# 도전과제

```kotlin
package org.techtown.soptseminar

class CameraFragment : Fragment() {
    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding ?: error("바인딩에 NULL 값이 들어갔어요!!")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        changeProfileImage()
        return binding.root
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                navigateGallery()
            } else {
                Toast.makeText(
                    requireContext(),
                    "갤러리 접근 권한이 없습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private val galleryLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                val imageUri = it.data?.data
                Glide.with(this)
                    .load(imageUri)
                    .into(binding.ivAttached)
            } else if (it.resultCode == RESULT_CANCELED) {
                Toast.makeText(requireContext(), "사진 선택이 취소되었습니다", Toast.LENGTH_SHORT).show()
            }
        }

    private fun navigateGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        galleryLauncher.launch(intent)
    }

    private fun changeProfileImage() {
        binding.btnAttachImg.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    navigateGallery()
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```

### 1) AndroidManifest.xml에 사진을 읽고 쓸 수 있도록 permission을 다음과 같이 추가한다.
```xml
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```
### 2) changeProfileImage
- changeProfileImage()를 실행하여, 접근권한을 받은 상태이면 `navigateGallery()`로 이동
- 접근권한을 받지 못했으면, `requestPermissionLauncher.launch(Manifest.permission.  READ_EXTERNAL_STORAGE)`을 통해 접근 권한 받기  
```kotlin
private fun changeProfileImage() {
        binding.btnAttachImg.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    navigateGallery()
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    }
```
### 3) navigateGallery
```kotlin
private fun navigateGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        galleryLauncher.launch(intent)
    }
```
- 갤러리 목록 화면은 intent를 통해 실행하는데 액션문자열을 `Intent.ACTION_PICK`로 한다.  

### 4) galleryLauncher
```kotlin
private val galleryLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                val imageUri = it.data?.data
                Glide.with(this)
                    .load(imageUri)
                    .into(binding.ivAttached)
            } else if (it.resultCode == RESULT_CANCELED) {
                Toast.makeText(requireContext(), "사진 선택이 취소되었습니다", Toast.LENGTH_SHORT).show()
            }
        }
```
### 5) requestPermissionLauncher
```kotlin
private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                navigateGallery()
            } else {
                Toast.makeText(
                    requireContext(),
                    "갤러리 접근 권한이 없습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
```
## 실행화면
<img width = "250" height ="400" src ="https://user-images.githubusercontent.com/87055456/167545932-1acf446a-42a9-4689-be0d-a2449db21783.gif">


# Seminar4
## 필수과제
## 1. 로그인/회원가입 API 연동
### PostMan Test 및 실행화면
- 회원가입
<img src ="https://user-images.githubusercontent.com/87055456/168290676-f9125f9b-179e-4912-923f-cd6cf5b384a1.jpg" >  

<img width ="500" src="https://user-images.githubusercontent.com/87055456/168292808-e37325e8-ef21-4462-b657-d7ef2bc24459.gif" >
- 로그인
<img src ="https://user-images.githubusercontent.com/87055456/168291703-ff1f66bf-46ee-4fc3-b84a-ce96819ca7c9.png">  

<img width ="500" src="https://user-images.githubusercontent.com/87055456/168293078-e0624cfa-ad24-41c9-bc12-be1187025d58.gif" >

## retrofit 인터페이스
- http메서드, 헤더, URI 등을 정의하는 곳이다.  
- 서버에 어떠한 요청을 보내고(request), 요청을 받는지(respond) 정의하는 부분이다.  
- 명세서를 보면서 필요한 부분을 넣어주면 된다~  
```kotlin
interface SoptService {
    @POST("auth/signup")
    fun postSignup(
        @Body body: RequestSignUpData
    ): Call<ResponseSignUpData>

    @POST("auth/signin")
    fun postSignIn(
        @Body body: RequestSignInData
    ): Call<ResponseSignInData>
}
```  
## retrofit 구현체
- reptrofit 인터페이스를 구현한 객체를 생성해주는 곳이다.  
- retrofit 객체를 싱글톤으로 만들기 위해 `object`를 사용했다.
```kotlin
object ServiceCreator {
    private const val BASE_URL = "http://13.124.62.236/"
    private const val BASE_URL_GITHUB = "https://api.github.com/"

    private val soptRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val githubRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_GITHUB)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val soptService: SoptService = soptRetrofit.create(SoptService::class.java)
    val githubApiService: GithubApiService = githubRetrofit.create(GithubApiService::class.java)
}
```    
```kotlin
private val soptRetrofit: Retrofit  = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
```
- Retrofit.Builder() : Retrofit 빌더 객체생성  
- baseUrl : 빌더 객체의 baseUrl을 호출하고 서버의 메인 URL을 전달한다.  
- addConverterFactory: gson컨버터를 연동해준다.  
- build: Retrofit 객체 반환
## Request/Respond 객체 코드
- RequestSignInData
```kotlin
data class RequestSignInData(
    @SerializedName("email") val id: String,
    val password: String
)
```
- ResponseSignInData
```kotlin
data class ResponseSignInData(
    val status: Int,
    val message: String,
    val data: Data
) {
    data class Data(
        val name: String,
        val email: String
    )
}
```
- RequestSignUpData
```kotlin
data class RequestSignUpData(
    @SerializedName("email")
    val id: String,
    val name: String,
    val password: String
)
```
- ResponseSignUpData
```kotlin
data class ResponseSignUpData(
    val status: Int,
    val message: String,
    val data: Data
) {
    data class Data(
        val id: Int
    )
}
```
## 성장과제 2-1
## ResponseUserInfoData
```kotlin
data class ResponseUserInfoData(
    @SerializedName("avatar_url")
    val image: String,
    @SerializedName("login")
    val name: String,
    @SerializedName("bio")
    val introduce: String
)
```
- @SerializedName 어노테이션으로 다른 이름으로 바꿔줬다
## GithubApiService
```kotlin
interface GithubApiService {

    @GET("users/{user_name}/repos")
    fun getRepoInfo(
        @Path("user_name") userName: String
    ): Call<List<ResponseRepoInfoData>>

    @GET("users/{user_name}/followers")
    fun getFollowingInfo(
        @Path("user_name") userName: String
    ): Call<List<ResponseUserInfoData>>
}
```
- githubApi에서 받아올 retrofit 인터페이스를 하나 더 만들어줍니다.  
- 이때, 요청할 필요없이 받아오기만 하면 되니 @GET 어노테이션을 붙어줍니다.  
- getFollowingInfo()의 인자에 `@Path("user_name") userName: String`으로 받아
 로그인하는 사람의 github정보를 받아오도록 합니다.
## SignInActivity
```kotlin
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
```
- 1주차 성장과제에서 `registForActivityResult`메서드를 사용 안했는데, 이번에 사용해봤슴니다~  
- responseData.isSuccessful이 true값을 얻어준다면, HomeActivity화면으로 넘어가게 해줍니다.  
- 이때, `putExtra()`메서드를 통해 인텐트객체에 username을 담아 줍니다.  

## HomeActivity
```kotlin
class HomeActivity : AppCompatActivity() {

    lateinit var userData: String // username
    ...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // signinActivity에서 username에 해당하는 이름 받아오기
        userData = intent.getStringExtra("username").toString()
        initAdapter()
        initBottomNavigation()
    }

    ...
```
- HomeActivity에 userData라는 변수를 선언해준후, getStringExtra()메서드를 이용해 'username'을 받아 초기화시켜줍니다.

## FollowerFragment
```kotlin
class FollowerFragment : Fragment() {
    private lateinit var homeActivity: HomeActivity
    private lateinit var followerAdapter: FollowerAdapter
    private var _binding: FragmentFollwerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 된다
        homeActivity = activity as HomeActivity
        // DataBinding 적용
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_follwer, container, false)
        // userData가 null값일 수도 있기 때문에 null처리
        if (!homeActivity.userData.isNullOrBlank()) {
            followerAdapter = FollowerAdapter()
            binding.rvFollower.adapter = followerAdapter
            Log.d("UserData:", homeActivity.userData)
            initUserInfoNetwork(homeActivity.userData)
        }
        return binding.root
    }

    private fun initUserInfoNetwork(userData: String) {
        val call: Call<List<ResponseUserInfoData>> =
            ServiceCreator.githubApiService.getFollowingInfo(userData)

        call.enqueue(object : Callback<List<ResponseUserInfoData>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<ResponseUserInfoData>>,
                response: Response<List<ResponseUserInfoData>>
            ) {
                if (response.isSuccessful) {
                    // let을 통한 널처리
                    response.body()?.let { data ->
                        Log.d("tracking?", data.toString())
                        data.forEach {
                            followerAdapter.followerList.add(
                                FollowerData(
                                    image = it.image,
                                    name = it.name,
                                    introduce = it.introduce ?: "NULL",
                                )
                            )
                        }
                    }
                    followerAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(requireContext(), "깃허브 팔로워 조회 실패", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<ResponseUserInfoData>>, t: Throwable) {
                Log.e("NetworkTest", "error:$t") // 오류처리 코드
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```
- it : ResponseUserInfoData  
- data : List<ResponseUserInfoData> , it이 두개가 나오기 때문에 둘 중 하나는 이름을 정해주어야 한다.  
- ResponseUserInfoData의 정보를 FollowerData()생성자에 넣어준 후, 객체를 followerData에 담아준다.  
- `introduce = it.introduce ?: "NULL"` introduce가 없는 사람도 있을 수 있으니 엘비스 연산자로 널처리~  
## 성장과제 2 : Wrapper 클래스

- 모든 Response data class에서 계속 공통되는 부분이 있다.  
- 이 공통되는 부분을 Wrapper(지네릭)클래스로 만들고, 타입변수 data를 만들어준다.
```kotlin
data class ResponseWrapper<T>(
    val status: Int,
    val message: String,
    val data: T?
)
```
- ResponseSignInDat와 ResponseSignUpData에서 공통되는 부분을 지워준다.  
```kotlin
data class ResponseSignUpData(
    val id: Int
)

data class ResponseSignInData(
    val name: String,
    val email: String
)
```
- 타입 T에 기존의 ResponseSignInData와 ResponseSignUpData를 넣어준다.  
- 이해가 잘안가면 T를 다음과 같이 ResponseSignInData로 치환해주면 된다.
```kotlin
data class ResponseWrapper<ResponseSignInData>(
    val status: Int,
    val message: String,
    val data: ResponseSignInData
)
```
- SignInActivity와 SignUpActivity에서도 Call<Response..Data> 부분을 Call<ResponseWrapper<Response..Data>>로 바꿔주면 된다.  

## 실행화면  
- 정말 어이없게도, 제 'murjune'아이디의 비밀번호를 까먹어서.. 윤정님의 깃허브아이디로 로그인하는 화면을 보여드리겠슴니다..
<img width ="500" src="https://user-images.githubusercontent.com/87055456/168293336-d4e041c1-e5ac-4652-83bd-1461681c869b.gif" >
    
# Seminar7
## 필수과제 1-1 : 자동 로그인 구현
- SignSharedPreferences
```kotlin
object SignSharedPreferences {
    private const val GITHUB_SIGN_IN = "my_github_sign_in"
    private const val ID = "id"
    private const val AUTO_MODE = "auto_mode"
    private const val PW = "pw"

    fun setUserId(context: Context, input: String) {
        context.getSharedPreferences(GITHUB_SIGN_IN, Context.MODE_PRIVATE).edit().apply {
            putString(ID, input)
            apply()
        }
    }

    fun setAutoMode(context: Context, boolean: Boolean) {
        context.getSharedPreferences(GITHUB_SIGN_IN, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(AUTO_MODE, boolean)
            .apply()
    }

    fun getAutoMode(context: Context): Boolean {
        return context.getSharedPreferences(GITHUB_SIGN_IN, Context.MODE_PRIVATE)
            .getBoolean(AUTO_MODE, false)
    }

    fun getUserId(context: Context) =
        context.getSharedPreferences(GITHUB_SIGN_IN, Context.MODE_PRIVATE)
            .getString(ID, "")

    fun setUserPassWord(context: Context, input: String) {
        context.getSharedPreferences(GITHUB_SIGN_IN, Context.MODE_PRIVATE)
            .edit()
            .putString(PW, input)
            .apply()
    }

    fun getUserPassword(context: Context) =
        context.getSharedPreferences(GITHUB_SIGN_IN, Context.MODE_PRIVATE)
            .getString(PW, "")

    fun clearAll(context: Context) {
        context.getSharedPreferences(GITHUB_SIGN_IN, Context.MODE_PRIVATE)
            .edit()
            .clear()
            .apply()
    }
}
```
- 앱 전역에서 호출 가능하도록 싱글톤으로 구현  
- GITHUB_SIGN_IN: 저장공간에 접근하기 위한 key값
- AutoMode : 자동로그인이 활성화되었는지 위한 key값
- ID: 사용자의 id가 저장되었는지 확인하기 위한 key값 
- Password: 사용자의 password을 저장하기 위해 key값  
- getter와 setter를 구현해주었다.
## SignInActivity
```kotlin
class SignInActivity : AppCompatActivity() {
    ...
    
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
}
```
- AutoMode값이 true일 때  
    - 저장된 id값과 password값을 loginNetWork의 인자에 보내준다.  
- AutoMode값이 false일 때
    - 자동로그인box가 check되어있는 경우  
        - 1. editText에 있는 id값과 password을 loginNetWork의 인자에 보내준다.  
        - 2.  id값과 password을 저장해주고, id값을 putExtra로 담아준 후, homeActivity로 intent한다.
    - 자동로그인box가 check되어있지 않은 경우  
        - 1. id값을 putExtra로 담아준 후,homeActivity로 intent한다.

## 필수과제 1-2 : 자동 로그인 해제 구현  
- SettingActivity
```kotlin
class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutLogout.setOnClickListener {
            SignSharedPreferences.clearAll(this)
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
```
- SignSharedPreferences.clear()메서드를 통해 로컬에 저장된 모든 값을 지워준 후, 다시 로그인화면으로 간다.  
## 성장과제 : 온보딩화면 만들기
- activity_onboarding
```kotlin
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".presentation.onboarding.OnBoardingActivity">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/cl_title"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/white"
        android:elevation="10dp"
        app:layout_constraintTop_toTopOf="parent">

        <TextView
            android:id="@+id/tv_onboarding"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:fontFamily="@font/noto_sans_kr_bold"
            android:paddingVertical="20dp"
            android:text="@string/onboarding"
            android:textAlignment="center"
            android:textSize="20sp"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
    </androidx.constraintlayout.widget.ConstraintLayout>
        <androidx.fragment.app.FragmentContainerView
            android:id="@+id/container_onboarding"
            android:name="androidx.navigation.fragment.NavHostFragment"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            app:defaultNavHost="true"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/cl_title"
            app:navGraph="@navigation/nav_onboarding" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
- 1. FragmentContainerView에 NavHostFragment 지정
- 2. `app:defaultNavHost="true"`: 백버튼 로직을 가로챌 수 있게 해주는 속성
- 3. `app:navGraph="@navigation/nav_onboarding"`: nav그래프를 가져온다. 

- OnBoardingActivity
```kotlin
class OnBoardingActivity : AppCompatActivity() {
    
    ...
    private fun initOnBoarding() {
        if (SignSharedPreferences.getAutoMode(this)) {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    ...
}
```
- `SignSharedPreferences.getAutoMode(this)`값이 true이면 바로, 로그인 화면으로 가도록한다~  

## 추가로 공부한 것!!
### apply() vs commit()
```java
 public abstract boolean commit ()   //  API 1

public abstract void apply ()  // API 9
```  
- commit는 저장여부를 boolean타입으로 반환
- apply는 동일하지만, 반환값이 없다.

단순하게 생각해보면 commit()을 쓰는게 더 좋을 것 같다. 그러나, commit()이 존재하는데 왜 API 9에서 apply()라는 메서드가 나왔을까??  
그 이유는 `동기/비동기`에 있다.
```
- 공식문서
commit(), which writes its preferences out to persistent storage synchronously,
apply() commits its changes to the in-memory SharedPreferences immediately
but starts an asynchronous commit to disk and you won't be notified of any failures  

As SharedPreferences instances are singletons within a process,
it's safe to replace any instance of commit() with apply() 
if you were already ignoring the return value.
```  
- commit(): 호출 시 UI쓰레드는 `block`되고, 파일 저장이 완료된 후에야 UI쓰레드가 다시 작동된다.(동기적)
- apply() : 호출되자 마자 return된다. 백그라운드에서 실행되기 때문에 UI쓰레드가 `block`되지 않음.(비동기적)

apply()는 결과값을 알 수 없지만, 굳이 결과값을 알고 싶지 않으면 apply()를 써주는게 더 안전하다:D  
### ShapeableImageView
- material ShapeableImageView
```xml
<com.google.android.material.imageview.ShapeableImageView
        android:id="@+id/iv_github"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginTop="50dp"
        android:padding="2dp"
        android:src="@drawable/img_github"
        app:layout_constraintDimensionRatio="1"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintWidth_percent="0.2"
        app:shapeAppearanceOverlay="@style/CornerSize50Percent"
        app:strokeColor="@color/sopt_main_purple"
        app:strokeWidth="3dp" />
```  
ShapeableImageView를 사용하여 쉽게 circle을 만들었다~    
<img src="https://user-images.githubusercontent.com/87055456/173077602-afbcd51c-9ec0-4528-989c-41275094e9f2.png">  

## 실행화면
|자동로그인	| 자동로그인 해제 |	온보딩 |
|:--:|:--:|:--:|
|<img width= 600 src="https://user-images.githubusercontent.com/87055456/173079751-b8542b85-5913-4e51-a288-bfcf4c86d9fd.gif">   | <img width= 600 src="https://user-images.githubusercontent.com/87055456/173080078-6b5c43b0-8254-4e6a-a5f6-1edf5e9e791d.gif">|<img width= 600 src="https://user-images.githubusercontent.com/87055456/173079484-6b9fd17a-cac5-4890-8dbf-d9a755e9e9cf.gif">|

갑자기.. 제 에뮬 or 안스에 문제가 생긴것 같습니다. GLide로 image뷰 넣어줄 때 image가 에뮬에 안떠요.. 다른 분들은 제 코드로 돌렸을 때 image잘 뜬다고 하는데 거참..

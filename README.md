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

# Seminar 2
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




# Seminar 2
# 필수과제


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

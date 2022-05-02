package org.techtown.soptseminar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import org.techtown.soptseminar.adapter.FollowerAdapter
import org.techtown.soptseminar.data.FollowerData
import org.techtown.soptseminar.databinding.FragmentFollwerBinding

class FollowerFragment : Fragment() {

    private lateinit var followerAdapter: FollowerAdapter
    private var _binding: FragmentFollwerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollwerBinding.inflate(inflater, container, false)
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter {
            val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra(GENDER, it.gender)
                putExtra(NAME, it.name)
                putExtra(INTRODUCE, it.introduce)
            }
            startActivity(intent)
        }
        followerAdapter.followerList.addAll(
            listOf(
                FollowerData(R.drawable.man, "이준원", "안드로이드 YB"),
                FollowerData(R.drawable.woman, "김수빈", "안드로이드 OB"),
                FollowerData(R.drawable.man, "권용민", "안드로이드 OB"),
                FollowerData(R.drawable.woman, "최유리", "안드로이드 YB"),
                FollowerData(R.drawable.woman, "최윤정", "안드로이드 YB"),
            )
        )

        with(binding) {
            with(rvFollower) {
                adapter = followerAdapter
                addItemDecoration(
                    ItemDecoration(
                        // xml은 dp, 코틀린 파일은 px을 쓰기 때문에 getDimensionPixelOffset을 써줘서 dp -> px로 바꿔줘버린다.
                        resources.getDimensionPixelOffset(R.dimen.margin_15),
                        1
                    )
                )
            }
            // myAdapter객체는 클래스 OnItemMoveListener 구현했으므로, OnItemMoveListener의 자손이다.
            val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(followerAdapter))
            itemTouchHelper.attachToRecyclerView(rvFollower)
            // ItemTouchHelper(ItemTouchHelperCallback(myAdapter)).attachToRecyclerView((rvMyfollower))
            // 생성자 ItemTouchHelper(CallBack callback)
            // val callBack = ItemTouchHelperCallback(myAdapter) 커스텀 CallBack
            // val itemTouchHelper = ItemTouchHelper(callBack)
            followerAdapter.startDrag(object : FollowerAdapter.OnStartDragListener {
                override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                    itemTouchHelper.startDrag(viewHolder)
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val NAME = "name"
        const val INTRODUCE = "introduce"
        const val GENDER = "gender"
    }
}

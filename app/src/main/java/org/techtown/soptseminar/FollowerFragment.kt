package org.techtown.soptseminar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import org.techtown.soptseminar.databinding.FragmentFollwerBinding

class FollowerFragment : Fragment() {

    private lateinit var followerAdapter: FollowerAdapter
    private var _binding: FragmentFollwerBinding? = null
    private val binding get() = _binding!!
    // onCreateView 메서드는 반드시 있어야 한다.
    // 이 함수는 자동 호출되며, 반환한 View객체가 화면에 출력된다.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollwerBinding.inflate(inflater, container, false)
        initAdapter()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter()
        with(binding) {
            rvFollower.adapter = followerAdapter
            rvFollower.addItemDecoration(
                ItemDecoration(
                    resources.getDimensionPixelOffset(R.dimen.margin_15),
                    1
                )
            )
            // myAdapter객체는 클래스 OnItemMoveListener 구현했으므로, OnItemMoveListener의 자손이다.
            val itemTouchHelper : ItemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(followerAdapter))
            itemTouchHelper.attachToRecyclerView(rvFollower)

            //ItemTouchHelper(ItemTouchHelperCallback(myAdapter)).attachToRecyclerView((rvMyfollower))

            // 생성자 ItemTouchHelper(CallBack callback)
            // val callBack = ItemTouchHelperCallback(myAdapter) 커스텀 CallBack
            // val itemTouchHelper = ItemTouchHelper(callBack)

            followerAdapter.startDrag(object : FollowerAdapter.OnStartDragListener {
                override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                    itemTouchHelper.startDrag(viewHolder)
                }
            })
        }
//        binding.rvFollower.layoutManager = GridLayoutManager(this,2)
        followerAdapter.followerList.addAll(
            listOf(
                FollowerData(R.drawable.man, "이준원", "안드로이드 YB"),
                FollowerData(R.drawable.woman, "김수빈", "안드로이드 OB"),
                FollowerData(R.drawable.man, "권용민", "안드로이드 OB"),
                FollowerData(R.drawable.woman, "최유리", "안드로이드 YB"),
                FollowerData(R.drawable.woman, "최윤정", "안드로이드 YB"),
                )
        )

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
}
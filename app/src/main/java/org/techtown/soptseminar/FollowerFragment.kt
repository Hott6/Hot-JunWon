package org.techtown.soptseminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import org.techtown.soptseminar.databinding.FragmentFollwerBinding

class FollowerFragment : Fragment() {

    private lateinit var followerAdapter : FollowerAdapter
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

    private fun initAdapter(){

        followerAdapter = FollowerAdapter()

        binding.rvFollower.adapter = followerAdapter
        // 코드단에서도 레이아웃 매니저 설정가능!
//        binding.rvFollower.layoutManager = GridLayoutManager(this,2)

        followerAdapter.followerList.addAll(

            listOf(
                FollowerData("이준원", "안드로이드 파트원"),
                FollowerData("김수빈", "안드로이드 31기 파트장"),
                FollowerData("권용민", "안드로이드 알통몬"),
                FollowerData("최유리", "유림이는 반병.."),
                FollowerData("최윤정", "좋아~~"),

            )
        )
    }
}
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initAdapter() {
        repositoryAdapter = RepositoryAdapter()
        with(binding) {

            rvRepository.adapter = repositoryAdapter
            rvRepository.addItemDecoration(MyDecoration(this@RepositoryFragment))
        }

        repositoryAdapter.repositoryList.addAll(

            listOf(
                RepositoryData("알고리즘 레포", "집에가고싶ㄷ"),
                RepositoryData("안드로이드 레포", "뭐먹지"),
                RepositoryData("ios 레포", "치킨먹고시푸다"),
                RepositoryData("배고파", "치킨은 bbq.."),
                RepositoryData("졸려", "황금올리부.."),

                )
        )
    }
}
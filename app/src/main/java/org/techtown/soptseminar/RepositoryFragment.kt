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
            rvRepository.addItemDecoration(ItemDecoration(resources.getDimensionPixelOffset(R.dimen.margin_15), 2))
            // resources : 옛날이름 getResources 메서드
            // getDimesionPixelOffset메서드 : getDimension메서드와 비슷, (부동소수점을 버림 -> int형)
        }

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
    }
}
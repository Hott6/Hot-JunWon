package org.techtown.soptseminar.presentation.profile.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.soptseminar.R
import org.techtown.soptseminar.data.entity.RepositoryData
import org.techtown.soptseminar.databinding.FragmentRepositoryBinding
import org.techtown.soptseminar.presentation.profile.ItemDecoration

class RepositoryFragment : Fragment() {
    private lateinit var repositoryAdapter: RepositoryAdapter
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        initAdapter()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        repositoryAdapter = RepositoryAdapter()
        with(binding) {
            rvRepository.adapter = repositoryAdapter
            rvRepository.addItemDecoration(
                ItemDecoration(
                    resources.getDimensionPixelOffset(R.dimen.margin_15),
                    2
                )
            )
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

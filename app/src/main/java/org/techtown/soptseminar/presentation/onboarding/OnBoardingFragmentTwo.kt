package org.techtown.soptseminar.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.techtown.soptseminar.R
import org.techtown.soptseminar.databinding.FragmentOnboardingTwoBinding

class OnBoardingFragmentTwo : Fragment() {
    private var _binding: FragmentOnboardingTwoBinding? = null
    private val binding get() = _binding ?: error("null값 들어감")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardingTwoBinding.inflate(inflater, container, false)
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragmentTwo_to_onBoardingFragmentThree)
        }
        return binding.root
    }
}

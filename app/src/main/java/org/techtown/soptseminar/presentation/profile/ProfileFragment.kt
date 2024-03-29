package org.techtown.soptseminar.presentation.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.bumptech.glide.Glide
import org.techtown.soptseminar.R
import org.techtown.soptseminar.databinding.FragmentProfileBinding
import org.techtown.soptseminar.presentation.SettingActivity
import org.techtown.soptseminar.presentation.profile.follower.FollowerFragment
import org.techtown.soptseminar.presentation.profile.repo.RepositoryFragment
import org.techtown.soptseminar.util.SignSharedPreferences

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding ?: error("바인딩에 NULL 값이 들어갔어요!!")
    private var position = FOLLOWER
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        initTransactionEvent()
        changeToFollowerFragment()
        changeToRepositoryFragment()
        setOnclickSetting()
        initImage()
        return binding.root
    }

    private fun setOnclickSetting() {
        binding.ivSetting.setOnClickListener {
            val intent = Intent(requireContext(), SettingActivity::class.java)
            startActivity(intent)
            if (!SignSharedPreferences.getAutoMode(requireContext())) {
                activity?.finish()
            }
        }
    }

    private fun initTransactionEvent() {
//        childFragmentManager.beginTransaction().add().commit()
        binding.btnFollower.isSelected = true
        childFragmentManager.commit {
            setReorderingAllowed(true)
            add<FollowerFragment>(R.id.fragment_main)
        }
    }

    private fun changeToFollowerFragment() {
        binding.btnFollower.setOnClickListener {
            if (position == REPOSITORY) {
                binding.btnFollower.isSelected = true
                binding.btnRepository.isSelected = false
                childFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<FollowerFragment>(R.id.fragment_main)
                    position = FOLLOWER
                }
            }
        }
    }

    private fun changeToRepositoryFragment() {
        binding.btnRepository.setOnClickListener {
            if (position == FOLLOWER) {
                binding.btnFollower.isSelected = false
                binding.btnRepository.isSelected = true
                childFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<RepositoryFragment>(R.id.fragment_main)
                    position = REPOSITORY
                }
            }
        }
    }

    private fun initImage() {
        Glide.with(this)
            .load(R.drawable.image1)
            .circleCrop()
            .into(binding.ivProfile)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FOLLOWER = 0
        const val REPOSITORY = 1
    }
}

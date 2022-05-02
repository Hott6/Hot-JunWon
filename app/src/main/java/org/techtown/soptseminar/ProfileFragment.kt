package org.techtown.soptseminar

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.bumptech.glide.Glide
import org.techtown.soptseminar.databinding.FragmentProfileBinding

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
        initImage()
        return binding.root
    }

    private fun initTransactionEvent() {
        childFragmentManager.commit {
            setReorderingAllowed(true)
            add<FollowerFragment>(R.id.fragment_main)
        }
    }

    private fun changeToFollowerFragment() {
        binding.btnFollower.setOnClickListener {
            if (position == REPOSITORY) {
                childFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<FollowerFragment>(R.id.fragment_main)
                }
            }
        }
    }

    private fun changeToRepositoryFragment() {
        binding.btnRepository.setOnClickListener {
            if (position == FOLLOWER) {
                childFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<RepositoryFragment>(R.id.fragment_main)
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
        val FOLLOWER_FRAGMENT: String = FollowerFragment::class.java.simpleName
        val REPOSITORY_FRAGMENT: String = RepositoryFragment::class.java.simpleName
    }
}

/*
transaction
    .replace(R.id.fragment_main, followerFragment)
    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    .commit()
*/

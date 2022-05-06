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
        val man = "https://user-images.githubusercontent.com/87055456/166401884-4add5a15-42be-41ad-9d72-563f123d27b0.png"
        val woman = "https://user-images.githubusercontent.com/87055456/166401904-764a8011-c297-4611-a1a5-e3a1f0be063b.png"
        followerAdapter.followerList.addAll(
            listOf(
                FollowerData(man, "이준원", "안드로이드 YB"),
                FollowerData(woman, "김수빈", "안드로이드 OB"),
                FollowerData(man, "권용민", "안드로이드 OB"),
                FollowerData(woman, "최유리", "안드로이드 YB"),
                FollowerData(man, "최윤정", "안드로이드 YB"),
            )
        )

        with(binding) {
            with(rvFollower) {
                adapter = followerAdapter
                addItemDecoration(
                    ItemDecoration(
                        resources.getDimensionPixelOffset(R.dimen.margin_15),
                        1
                    )
                )
            }
            val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(followerAdapter))
            itemTouchHelper.attachToRecyclerView(rvFollower)
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

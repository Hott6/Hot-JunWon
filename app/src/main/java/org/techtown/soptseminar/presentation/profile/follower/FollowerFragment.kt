package org.techtown.soptseminar.presentation.profile.follower

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import org.techtown.soptseminar.R
import org.techtown.soptseminar.data.api.ServiceCreator
import org.techtown.soptseminar.data.entity.FollowerData
import org.techtown.soptseminar.data.entity.follower.ResponseUserInfoData
import org.techtown.soptseminar.databinding.FragmentFollwerBinding
import org.techtown.soptseminar.presentation.DetailActivity
import org.techtown.soptseminar.presentation.DetailActivity.Companion.IMAGE
import org.techtown.soptseminar.presentation.DetailActivity.Companion.INTRODUCE
import org.techtown.soptseminar.presentation.DetailActivity.Companion.NAME
import org.techtown.soptseminar.presentation.HomeActivity
import org.techtown.soptseminar.presentation.profile.ItemDecoration
import org.techtown.soptseminar.util.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerFragment : Fragment() {
    private lateinit var followerAdapter: FollowerAdapter
    var responseDataSet = mutableListOf<FollowerData>()
    private var _binding: FragmentFollwerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "FollowerFragment - onCreateView() called")
        val userData = (requireActivity() as? HomeActivity)?.userData
        Log.d(TAG, "FollowerFragment - onCreateView() - $userData")
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_follwer, container, false)
        if (!userData.isNullOrBlank()) {
            initUserInfoNetwork(userData)
        }
        return binding.root
    }

    private fun initUserInfoNetwork(userData: String) {
        Log.d(TAG, "FollowerFragment - initUserInfoNetwork() called")
        val call: Call<List<ResponseUserInfoData>> =
            ServiceCreator.githubApiService.getFollowingInfo(userData)

        call.enqueue(object : Callback<List<ResponseUserInfoData>> {
            override fun onResponse(
                call: Call<List<ResponseUserInfoData>>,
                response: Response<List<ResponseUserInfoData>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { dataList ->
                        Log.d("tracking?", dataList.toString())
                        dataList.forEach {
                            responseDataSet.add(
                                FollowerData(
                                    image = it.image,
                                    name = it.name,
                                    introduce = it.introduce ?: "NULL",
                                )
                            )
                        }
                    }
                    initAdapter()
                } else {
                    requireContext().showToast("깃허브 팔로워 조회 실패")
                }
            }

            override fun onFailure(call: Call<List<ResponseUserInfoData>>, t: Throwable) {
                Log.e("NetworkTest", "error:$t") // 오류처리 코드
            }
        })
    }

    private fun initAdapter() {
        // 고차함수로 넘겨보자
//        followerAdapter = FollowerAdapter() { data: FollowerData -> itemClick(data) }
        Log.d(TAG, "FollowerFragment - initAdapter() called")
        followerAdapter = FollowerAdapter { onItemClick(it) }
        Log.d(TAG, "FollowerFragment - initAdapter() - $followerAdapter")
        binding.rvFollower.adapter = followerAdapter.apply { submitList(responseDataSet.toMutableList()) }
        Log.d(TAG, "FollowerFragment - initAdapter() - ${followerAdapter.currentList}")
        initItemDecorarion()
        initItemTouch()
    }

    private fun onItemClick(data: FollowerData) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(IMAGE, data.image)
            putExtra(NAME, data.name)
            putExtra(INTRODUCE, data.introduce)
        }
        startActivity(intent)
    }

    // TODO ItemDecoration에서 getItemOffsets말고 다른 메서드로 구현해보기
    private fun initItemDecorarion() {
        binding.rvFollower.addItemDecoration(
            ItemDecoration(
                resources.getDimensionPixelOffset(R.dimen.margin_15),
                1
            )
        )
    }

    private fun initItemTouch() {
        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(followerAdapter))
        itemTouchHelper.attachToRecyclerView(binding.rvFollower)
        followerAdapter.startDrag(object : FollowerAdapter.OnStartDragListener {
            override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                itemTouchHelper.startDrag(viewHolder)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        const val TAG = "로그"
    }
}
// private fun initAdapter() {
//        followerAdapter = FollowerAdapter {
//            val intent = Intent(requireContext(), DetailActivity::class.java).apply {
//                putExtra(GENDER, it.gender)
//                putExtra(NAME, it.name)
//                putExtra(INTRODUCE, it.introduce)
//            }
//            startActivity(intent)
//        }
//        val man =
//            "https://user-images.githubusercontent.com/87055456/166401884-4add5a15-42be-41ad-9d72-563f123d27b0.png"
//        val woman =
//            "https://user-images.githubusercontent.com/87055456/166401904-764a8011-c297-4611-a1a5-e3a1f0be063b.png"
//        followerAdapter.followerList.addAll(
//            listOf(
//                FollowerData(man, "이준원", "안드로이드 YB"),
//                FollowerData(woman, "김수빈", "안드로이드 OB"),
//                FollowerData(man, "권용민", "안드로이드 OB"),
//                FollowerData(woman, "최유리", "안드로이드 YB"),
//                FollowerData(man, "최윤정", "안드로이드 YB"),
//            )
//        )
//
//        with(binding) {
//            with(rvFollower) {
//                adapter = followerAdapter
//                addItemDecoration(
//                    ItemDecoration(
//                        resources.getDimensionPixelOffset(R.dimen.margin_15),
//                        1
//                    )
//                )
//            }
//            val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(followerAdapter))
//            itemTouchHelper.attachToRecyclerView(rvFollower)
//            followerAdapter.startDrag(object : FollowerAdapter.OnStartDragListener {
//                override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
//                    itemTouchHelper.startDrag(viewHolder)
//                }
//            })
//        }
//    }

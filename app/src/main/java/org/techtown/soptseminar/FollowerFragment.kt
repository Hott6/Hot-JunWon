package org.techtown.soptseminar

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.techtown.soptseminar.adapter.FollowerAdapter
import org.techtown.soptseminar.data.FollowerData
import org.techtown.soptseminar.databinding.FragmentFollwerBinding
import org.techtown.soptseminar.week4.ResponseUserInfoData
import org.techtown.soptseminar.week4.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerFragment : Fragment() {
    private lateinit var homeActivity: HomeActivity
    private lateinit var followerAdapter: FollowerAdapter
    val followerData = mutableListOf<FollowerData>()
    private var _binding: FragmentFollwerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 된다
        homeActivity = activity as HomeActivity
        // DataBinding 적용??
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_follwer, container, false)
        if (!homeActivity.userData.isNullOrBlank()) {
            followerAdapter = FollowerAdapter()
            followerAdapter.followerList = followerData
            binding.rvFollower.adapter = followerAdapter
            Log.d("UserData:", homeActivity.userData)
            initUserInfoNetwork(homeActivity.userData)
        }
        return binding.root
    }

    private fun initUserInfoNetwork(userData: String) {
        val call: Call<List<ResponseUserInfoData>> =
            ServiceCreator.githubApiService.getFollowingInfo(userData)

        call.enqueue(object : Callback<List<ResponseUserInfoData>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<ResponseUserInfoData>>,
                response: Response<List<ResponseUserInfoData>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        Log.d("tracking?", data.toString())
                        data.forEach {
                            followerData.add(
                                FollowerData(
                                    image = it.image,
                                    name = it.name,
                                    introduce = it.introduce ?: "NULL",
                                )
                            )
                        }
                    }
                    followerAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(requireContext(), "깃허브 팔로워 조회 실패", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<ResponseUserInfoData>>, t: Throwable) {
                Log.e("NetworkTest", "error:$t") // 오류처리 코드
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

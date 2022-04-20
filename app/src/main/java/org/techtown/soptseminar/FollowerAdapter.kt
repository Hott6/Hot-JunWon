package org.techtown.soptseminar

import android.content.Context
import android.content.Intent
import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.techtown.soptseminar.databinding.ItemFollowerSampleListBinding
import kotlin.contracts.contract

class FollowerAdapter() :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {

    // 클릭 리스너 선언
    private lateinit var itemClickListener: ItemClickListener

    val followerList =
        mutableListOf<FollowerData>()

    override fun getItemCount(): Int {
        return followerList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {

        val binding =
            ItemFollowerSampleListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    class FollowerViewHolder(
        private val binding: ItemFollowerSampleListBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: FollowerData) {
            binding.ivProfile.setImageResource(data.gender)
            binding.tvName.text = data.name
            binding.tvIntroduce.text = data.introduce
        }
    }

    // 성장 과제 1
    // https://sunpil.tistory.com/181
    // 클릭 인터페이스 정의
    interface ItemClickListener {
        fun onClick(view: View, postion: Int)
    }

    // 클릭리스너 등록 메서드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}

package org.techtown.soptseminar

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.techtown.soptseminar.databinding.ItemFollowerSampleListBinding
import java.util.*
import kotlin.contracts.contract

class FollowerAdapter() :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(),
    ItemTouchHelperCallback.OnItemMoveListener {

    // 드래그 리스너 선언
    private lateinit var dragListener: OnStartDragListener

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

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])

        // 위 아래로 이동 가능한 draghandler
        holder.binding.ivDraghandle.setOnTouchListener { view: View, event: MotionEvent ->
            // Action_DOWN : 화면을 손가락으로 누른 순간의 이벤트
            if (event.action == MotionEvent.ACTION_DOWN) {
                dragListener.onStartDrag(holder)
            }
            return@setOnTouchListener false
        }

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    class FollowerViewHolder(
        val binding: ItemFollowerSampleListBinding,
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

    // 드래그 인터페이스
    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }

    fun startDrag(listener: OnStartDragListener) {
        this.dragListener = listener
    }

    override fun onItemMoved(fromPos: Int, toPos: Int) {
        Collections.swap(followerList, fromPos, toPos)
        notifyItemMoved(fromPos, toPos)
    }

    override fun onItemSwiped(pos: Int) {
        followerList.removeAt(pos)
        notifyItemRemoved(pos)
    }
}

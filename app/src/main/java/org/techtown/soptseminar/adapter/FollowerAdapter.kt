package org.techtown.soptseminar.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.techtown.soptseminar.ItemTouchHelperCallback
import org.techtown.soptseminar.MyDiffUtilCallback
import org.techtown.soptseminar.data.FollowerData
import org.techtown.soptseminar.databinding.ItemFollowerSampleListBinding
import java.util.*

class FollowerAdapter(private val itemClick: (FollowerData) -> (Unit)) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(),
    ItemTouchHelperCallback.OnItemMoveListener {
    // 드래그 리스너 선언
    private lateinit var dragListener: OnStartDragListener

    val followerList =
        mutableListOf<FollowerData>()

    override fun getItemCount(): Int = followerList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {

        val binding =
            ItemFollowerSampleListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return FollowerViewHolder(binding, itemClick)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.binding.ivDraghandle.setOnTouchListener { view: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                dragListener.onStartDrag(holder)
            }
            return@setOnTouchListener false
        }
        holder.onBind(followerList[position])
    }

    // diffUtill 부분
    fun replaceItemList(newItemList: List<FollowerData>?) {
        newItemList?.let {
            val diffCallback = MyDiffUtilCallback(followerList, it)
            val diffResult = DiffUtil.calculateDiff(diffCallback)

            followerList.run {
                clear()
                addAll(it)
                diffResult.dispatchUpdatesTo(this@FollowerAdapter)
            }
        }
    }

    class FollowerViewHolder(
        val binding: ItemFollowerSampleListBinding,
        private val itemClick: (FollowerData) -> (Unit)
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: FollowerData) {
            binding.ivProfile.setImageResource(data.gender)
            binding.tvName.text = data.name
            binding.tvIntroduce.text = data.introduce
            binding.root.setOnClickListener {
                itemClick(data)
            }
        }
    }

    // 성장 과제 3
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

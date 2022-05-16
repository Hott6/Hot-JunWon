package org.techtown.soptseminar.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.soptseminar.ItemTouchHelperCallback
import org.techtown.soptseminar.data.FollowerData
import org.techtown.soptseminar.databinding.ItemFollowerSampleListBinding
import java.util.*

class FollowerAdapter(private val itemClick: ((FollowerData) -> (Unit))? = null) :
    ListAdapter<FollowerData, RecyclerView.ViewHolder>(follwerDiffUtil),
    ItemTouchHelperCallback.OnItemMoveListener {
    // 드래그 리스너 선언
    private lateinit var dragListener: OnStartDragListener

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
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FollowerViewHolder) {
            holder.binding.ivDraghandle.setOnTouchListener { _: View, event: MotionEvent ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    dragListener.onStartDrag(holder)
                }
                return@setOnTouchListener false
            }
            holder.onBind(getItem(position))
        }
    }

    class FollowerViewHolder(
        val binding: ItemFollowerSampleListBinding,
        private val itemClick: ((FollowerData) -> (Unit))? = null
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: FollowerData) {
            Log.d("onBind:", data.toString())
            binding.follower = data
            Glide.with(binding.root)
                .load(data.image)
                .circleCrop()
                .into(binding.ivProfile)
            binding.root.setOnClickListener {
                itemClick?.invoke(data)
            }
        }
    }

    // 드래그 인터페이스
    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }

    fun startDrag(listener: OnStartDragListener) {
        this.dragListener = listener
    }

    override fun onItemMoved(fromPos: Int, toPos: Int) {
        val newList = currentList.toMutableList()
        Collections.swap(newList, fromPos, toPos)
        submitList(newList)
    }

    override fun onItemSwiped(pos: Int) {
        val newList = currentList.toMutableList()
        newList.removeAt(pos)
        submitList(newList)
    }

    companion object {
        private val follwerDiffUtil = object : DiffUtil.ItemCallback<FollowerData>() {
            override fun areItemsTheSame(oldItem: FollowerData, newItem: FollowerData): Boolean {
                return oldItem.name === newItem.name
            }

            override fun areContentsTheSame(oldItem: FollowerData, newItem: FollowerData): Boolean {
                return oldItem == newItem
            }
        }
    }
}

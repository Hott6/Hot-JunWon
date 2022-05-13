package org.techtown.soptseminar.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.soptseminar.ItemTouchHelperCallback
import org.techtown.soptseminar.MyDiffUtilCallback
import org.techtown.soptseminar.data.FollowerData
import org.techtown.soptseminar.databinding.ItemFollowerSampleListBinding
import java.util.*

class FollowerAdapter(private val itemClick: ((FollowerData) -> (Unit))? = null) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(),
    ItemTouchHelperCallback.OnItemMoveListener {
    // 드래그 리스너 선언
    private lateinit var dragListener: OnStartDragListener

    var followerList =
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
        private val itemClick: ((FollowerData) -> (Unit))? = null
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: FollowerData) {
            Log.d("onBind:", data.toString())
            // databinding~ 코드가 줄어듬
            binding.follower = data
//            binding.tvName.text = data.name
//            binding.tvIntroduce.text = data.introduce
            Glide.with(binding.root)
                .load(data.image)
                .circleCrop()
                .into(binding.ivProfile)
            binding.root.setOnClickListener {
                itemClick?.invoke(data)
                // invoke() : 함수실행해주는 함수, null or data 뱉어줌
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
        Collections.swap(followerList, fromPos, toPos)
        notifyItemMoved(fromPos, toPos)
    }

    override fun onItemSwiped(pos: Int) {
        followerList.removeAt(pos)
        notifyItemRemoved(pos)
    }
}

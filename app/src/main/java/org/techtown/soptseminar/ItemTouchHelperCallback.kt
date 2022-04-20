package org.techtown.soptseminar

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(private val itemMoveListener: OnItemMoveListener) :
    ItemTouchHelper.Callback() { // ItemTouchHelper.Callback()는 추상클래스


    // getMovementFlags() : 이벤트의 방향을 설정, 어느 방향으로 움직 일지에 따라 flag를 정의
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        // Kotlin에서 if문은 statement가 아닌 Expression이다.
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)

    }

    //  from -> to 이동
    // onMove() : 어느 위치에서 어느 위치로 변경하는지 이벤트를 받음
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        itemMoveListener.onItemMoved(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    // 삭제
    // onSwiped(): Swipe가 될 때 이벤트
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        itemMoveListener.onItemSwiped(viewHolder.adapterPosition)
    }

    // 길게 누를 수 있는 메서드
    override fun isLongPressDragEnabled(): Boolean = false

    interface OnItemMoveListener {
        fun onItemMoved(fromPos: Int, toPos: Int)
        fun onItemSwiped(pos: Int)
    }
}

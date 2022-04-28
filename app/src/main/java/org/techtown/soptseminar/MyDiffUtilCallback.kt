package org.techtown.soptseminar

import androidx.recyclerview.widget.DiffUtil

class MyDiffUtilCallback(private val oldItemList: List<Any>, private val newItemList: List<Any>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItemList.size

    override fun getNewListSize(): Int = newItemList.size

    // 두 리스트의 id가 같은지 확인, 여기서는 Name으로 대체
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItemList[oldItemPosition]
        val newItem = newItemList[newItemPosition]
        // is는 자바에서 istanceOf , 형변환도 안해줘도 됨
        return if (oldItem is FollowerData && newItem is FollowerData) {
            oldItem.name == newItem.name
        } else {
            false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemList[oldItemPosition] == newItemList[newItemPosition]
}

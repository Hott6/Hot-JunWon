package org.techtown.soptseminar.presentation.profile

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecoration(private val margin: Int, private val spanCount: Int) :
    RecyclerView.ItemDecoration() {
    // getItemOffsets 개별 항목을 꾸밀 때 호출
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        // FollowerRecyclerView
        if (spanCount == 1) {
            outRect.set(margin, margin, margin, margin) // left, top, right, bottom
        }
        // RepositoryRecyclerView
        else {
            outRect.top = margin
            outRect.bottom = margin / 2
            if (position % spanCount == 0) {
                outRect.left = margin
                outRect.right = margin / 2
            } else {
                outRect.left = margin / 2
                outRect.right = margin
            }
        }
    }
}

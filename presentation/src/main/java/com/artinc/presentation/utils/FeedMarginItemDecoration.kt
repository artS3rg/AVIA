package com.artinc.presentation.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FeedMarginItemDecoration(
    private val startMargin: Int,
    private val endMargin: Int,
    private val defaultMargin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return
        val itemCount = parent.adapter?.itemCount ?: 0

        outRect.left = defaultMargin
        outRect.right = defaultMargin

        // Устанавливаем отступы для первого элемента
        if (position == 0) {
            outRect.left = startMargin
        }

        // Устанавливаем отступы для последнего элемента
        if (position == itemCount - 1) {
            outRect.right = endMargin
        }
    }
}

package com.artinc.avia.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.artinc.avia.R

class TicketsMarginItemDecoration(
    private val topMargin: Int,
    private val bottomMargin: Int,
    private val defaultMargin: Int,
    private val additionalTopMargin: Int
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

        // Получаем видимость ticket_badge из ViewHolder
        val ticketBadge = view.findViewById<View>(R.id.ticket_badge)
        val isBadgeVisible = ticketBadge?.visibility == View.VISIBLE

        // Устанавливаем отступы
        outRect.top = if (isBadgeVisible) additionalTopMargin else defaultMargin
        outRect.bottom = defaultMargin

        // Устанавливаем отступы для первого элемента
        if (position == 0) {
            outRect.top = if (isBadgeVisible) topMargin else topMargin-defaultMargin
        }

        // Устанавливаем отступы для последнего элемента
        if (position == itemCount - 1) {
            outRect.bottom = bottomMargin
        }
    }
}

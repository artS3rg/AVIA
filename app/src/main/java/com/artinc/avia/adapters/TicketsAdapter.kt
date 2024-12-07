package com.artinc.avia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.artinc.avia.R
import com.artinc.core.models.TicketItem
import java.text.NumberFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class TicketsAdapter(private var tickets: List<com.artinc.core.models.TicketItem>) : RecyclerView.Adapter<TicketsAdapter.TicketsViewHolder>() {
    class TicketsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemLayout: LinearLayout = itemView.findViewById(R.id.ticket_layout)
        val itemBadge: CardView = itemView.findViewById(R.id.ticket_badge)
        val itemBadgeText: TextView = itemView.findViewById(R.id.ticket_badge_text)
        val itemPrice: TextView = itemView.findViewById(R.id.ticket_price)
        val itemFirstTime: TextView = itemView.findViewById(R.id.first_time)
        val itemFirstAir: TextView = itemView.findViewById(R.id.first_aero)
        val itemSecondTime: TextView = itemView.findViewById(R.id.second_time)
        val itemSecondAir: TextView = itemView.findViewById(R.id.second_aero)
        val itemNavTime: TextView = itemView.findViewById(R.id.nav_time)
        val itemHasTransfer: TextView = itemView.findViewById(R.id.has_transfer)
        val resources = itemView.resources
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket, parent, false)
        return TicketsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        val item = tickets[position]

        val params = holder.itemLayout.layoutParams as ViewGroup.MarginLayoutParams

        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        val departureDateTime = LocalDateTime.parse(item.departure.date)
        val arrivalDateTime = LocalDateTime.parse(item.arrival.date)

        // Расчет продолжительности
        val flightDuration = Duration.between(departureDateTime, arrivalDateTime)

        // Часы и минуты
        val hours = flightDuration.toHours()
        val minutes = flightDuration.toMinutes() % 60
        val decimalDuration = hours + minutes / 60.0
        val formattedDuration = String.format("%.1f", decimalDuration)

        holder.itemPrice.text = "${formatNumberWithSpaces(item.price.value)} ₽"
        holder.itemFirstTime.text = departureDateTime.format(timeFormatter)
        holder.itemFirstAir.text = item.arrival.airport
        holder.itemSecondTime.text = arrivalDateTime.format(timeFormatter)
        holder.itemSecondAir.text = item.departure.airport
        holder.itemNavTime.text = "${formattedDuration}ч в пути"
        holder.itemHasTransfer.text = if (item.has_transfer) "" else " / Без пересадок"

        when(item.badge) {
            null -> {
                holder.itemBadge.visibility = View.INVISIBLE
                params.topMargin = (16 * holder.resources.displayMetrics.density).toInt()
            }
            else -> {
                holder.itemBadge.visibility = View.VISIBLE
                holder.itemBadgeText.text = item.badge
                params.topMargin = (21 * holder.resources.displayMetrics.density).toInt()
            }
        }

        holder.itemLayout.layoutParams = params
    }

    override fun getItemCount(): Int = tickets.size

    fun updateItems(newItems: List<com.artinc.core.models.TicketItem>) {
        tickets = newItems
        notifyDataSetChanged()
    }

    private fun formatNumberWithSpaces(number: Int): String {
        val numberFormat = NumberFormat.getInstance(Locale("ru", "RU"))
        return numberFormat.format(number)
    }
}
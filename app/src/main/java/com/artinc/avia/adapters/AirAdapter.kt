package com.artinc.avia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.artinc.avia.R
import com.artinc.core.models.AirItem
import java.text.NumberFormat
import java.util.Locale

class AirAdapter(private var airs: List<AirItem>) : RecyclerView.Adapter<AirAdapter.AirViewHolder>() {
    class AirViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: CardView = itemView.findViewById(R.id.air_img)
        val itemTitle: TextView = itemView.findViewById(R.id.air_title)
        val itemTimes: TextView = itemView.findViewById(R.id.air_times)
        val itemPrice: TextView = itemView.findViewById(R.id.air_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_air, parent, false)
        return AirViewHolder(view)
    }

    override fun onBindViewHolder(holder: AirViewHolder, position: Int) {
        val imageMap: Map<Int, Int> = mapOf(
            1 to R.color.red,
            2 to R.color.blue,
            3 to R.color.white
        )

        val item = airs[position]
        holder.itemTitle.text = item.title
        holder.itemTimes.text = item.time_range.joinToString(separator = " ")
        holder.itemPrice.text = "${formatNumberWithSpaces(item.price.value)} ₽"
        imageMap[position + 1]?.let { colorResId ->
            val color = ContextCompat.getColor(holder.itemView.context, colorResId)
            holder.itemImage.setCardBackgroundColor(color) // Установить реальный цвет
        }
    }

    override fun getItemCount(): Int = airs.size

    fun updateItems(newItems: List<AirItem>) {
        airs = newItems
        notifyDataSetChanged()
    }

    private fun formatNumberWithSpaces(number: Int): String {
        val numberFormat = NumberFormat.getInstance(Locale("ru", "RU"))
        return numberFormat.format(number)
    }
}
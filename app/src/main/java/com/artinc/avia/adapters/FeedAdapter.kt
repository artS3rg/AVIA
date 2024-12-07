package com.artinc.avia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artinc.avia.R
import com.artinc.core.models.FeedItem
import java.text.NumberFormat
import java.util.Locale

class FeedAdapter(private var feeds: List<com.artinc.core.models.FeedItem>) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
        val itemTitle: TextView = itemView.findViewById(R.id.itemTitle)
        val itemCity: TextView = itemView.findViewById(R.id.itemCity)
        val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val imageMap: Map<Int, Int> = mapOf(
            1 to R.drawable.first_img,
            2 to R.drawable.second_img,
            3 to R.drawable.third_img
        )

        val item = feeds[position]
        holder.itemTitle.text = item.title
        holder.itemCity.text = item.town
        holder.itemPrice.text = "от ${formatNumberWithSpaces(item.price.value)} ₽"
        imageMap[item.id]?.let { holder.itemImage.setImageResource(it) }
    }

    override fun getItemCount(): Int = feeds.size

    fun updateItems(newItems: List<com.artinc.core.models.FeedItem>) {
        feeds = newItems
        notifyDataSetChanged()
    }

    private fun formatNumberWithSpaces(number: Int): String {
        val numberFormat = NumberFormat.getInstance(Locale("ru", "RU"))
        return numberFormat.format(number)
    }
}
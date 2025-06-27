package com.example.nguyentronghieu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.nguyentronghieu.R

class RestaurantAdapter(private val restaurantList: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    // Lớp này giữ các tham chiếu đến các View trong mỗi item
    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imgRestaurant)
        val nameTextView: TextView = itemView.findViewById(R.id.tvRestaurantName)
        val cuisineTextView: TextView = itemView.findViewById(R.id.tvRestaurantCuisine)
        val ratingTextView: TextView = itemView.findViewById(R.id.tvRestaurantRating)
    }

    // Tạo ViewHolder mới (được gọi bởi layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    // Gán dữ liệu vào ViewHolder (được gọi bởi layout manager)
    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        holder.imageView.setImageResource(restaurant.imageResId)
        holder.nameTextView.text = restaurant.name
        holder.cuisineTextView.text = restaurant.cuisine
        holder.ratingTextView.text = restaurant.rating.toString()

        // Thêm sự kiện click cho mỗi item
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Bạn đã chọn ${restaurant.name}", Toast.LENGTH_SHORT).show()
        }
    }

    // Trả về số lượng item trong danh sách
    override fun getItemCount() = restaurantList.size
}
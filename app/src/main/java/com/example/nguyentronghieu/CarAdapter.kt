package com.example.nguyentronghieu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.nguyentronghieu.R

class CarAdapter(private val carList: List<Car>) :
    RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imgCar)
        val nameTextView: TextView = itemView.findViewById(R.id.tvCarName)
        val descriptionTextView: TextView = itemView.findViewById(R.id.tvCarDescription)
        val priceTextView: TextView = itemView.findViewById(R.id.tvCarPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]
        holder.imageView.setImageResource(car.imageResId)
        holder.nameTextView.text = car.name
        holder.descriptionTextView.text = car.description
        holder.priceTextView.text = car.price

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Xem chi tiết xe ${car.name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = carList.size
}
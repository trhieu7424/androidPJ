package com.example.nguyentronghieu

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load

class CarAdapter(private val carList: List<Car>) :
    RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.ivCarImage)
        val name: TextView = itemView.findViewById(R.id.tvCarName)
        val description: TextView = itemView.findViewById(R.id.tvCarDescription)
        val price: TextView = itemView.findViewById(R.id.tvCarPrice)
        val btnAddToCart: Button = itemView.findViewById(R.id.btnAddToCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]

        holder.image.load(car.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_car)
            error(R.drawable.ic_car)
        }


        holder.name.text = car.name
        holder.description.text = car.description
        holder.price.text = car.price

        holder.btnAddToCart.setOnClickListener {
            val product = Product(car.name, car.description, car.price, car.imageUrl, "car")
            CartManager.addItem(product)
            Toast.makeText(holder.itemView.context, "${car.name} đã được thêm vào giỏ", Toast.LENGTH_SHORT).show()
        }


        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val product = Product(car.name, car.description, car.price, car.imageUrl, "car")
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("EXTRA_PRODUCT", product)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = carList.size
}
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

class MotorbikeAdapter(private val motorbikeList: List<Motorbike>) :
    RecyclerView.Adapter<MotorbikeAdapter.MotorbikeViewHolder>() {

    class MotorbikeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.ivMotorbikeImage)
        val name: TextView = itemView.findViewById(R.id.tvMotorbikeName)
        val description: TextView = itemView.findViewById(R.id.tvMotorbikeDescription)
        val price: TextView = itemView.findViewById(R.id.tvMotorbikePrice)
        val btnAddToCart: Button = itemView.findViewById(R.id.btnAddToCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotorbikeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_motorbike, parent, false)
        return MotorbikeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MotorbikeViewHolder, position: Int) {
        val motorbike = motorbikeList[position]


        holder.image.load(motorbike.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_bike)
            error(R.drawable.ic_bike)
        }

        holder.name.text = motorbike.name
        holder.description.text = motorbike.description
        holder.price.text = motorbike.price


        holder.btnAddToCart.setOnClickListener {

            val product = Product(motorbike.name, motorbike.description, motorbike.price, motorbike.imageUrl, "motorbike")
            CartManager.addItem(product)
            Toast.makeText(holder.itemView.context, "${motorbike.name} đã được thêm vào giỏ", Toast.LENGTH_SHORT).show()
        }


        holder.itemView.setOnClickListener {
            val context = holder.itemView.context

            val product = Product(motorbike.name, motorbike.description, motorbike.price, motorbike.imageUrl, "motorbike")
            val intent = Intent(context, ProductDetailActivity::class.java)

            intent.putExtra("EXTRA_PRODUCT", product)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = motorbikeList.size
}
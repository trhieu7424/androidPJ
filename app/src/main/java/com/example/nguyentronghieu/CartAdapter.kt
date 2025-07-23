package com.example.nguyentronghieu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class CartAdapter(private val cartItems: List<CartItem>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.ivCartItemImage)
        val name: TextView = itemView.findViewById(R.id.tvCartItemName)
        val price: TextView = itemView.findViewById(R.id.tvCartItemPrice)
        val quantity: TextView = itemView.findViewById(R.id.tvCartItemQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        holder.name.text = item.product.name
        holder.price.text = item.product.price
        holder.quantity.text = "x ${item.quantity}"
        holder.image.load(item.product.imageUrl) {
            placeholder(R.drawable.ic_bike)
        }
    }

    override fun getItemCount() = cartItems.size
}
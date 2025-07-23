package com.example.nguyentronghieu

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class SearchAdapter(private var productList: List<Product>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.ivCarImage)
        val name: TextView = itemView.findViewById(R.id.tvCarName)
        val price: TextView = itemView.findViewById(R.id.tvCarPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val product = productList[position]
        holder.name.text = product.name
        holder.price.text = product.price
        holder.image.load(product.imageUrl) {
            val placeholder = if (product.type == "car") R.drawable.ic_car else R.drawable.ic_bike
            placeholder(placeholder)
            error(placeholder)
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("EXTRA_PRODUCT", product)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = productList.size


    fun filterList(filteredList: List<Product>) {
        productList = filteredList
        notifyDataSetChanged()
    }
}
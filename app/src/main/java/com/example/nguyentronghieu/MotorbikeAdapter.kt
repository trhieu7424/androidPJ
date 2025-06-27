
package com.example.nguyentronghieu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MotorbikeAdapter(private val motorbikeList: List<Motorbike>) :
    RecyclerView.Adapter<MotorbikeAdapter.MotorbikeViewHolder>() {

    class MotorbikeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.ivMotorbikeImage)
        val name: TextView = itemView.findViewById(R.id.tvMotorbikeName)
        val description: TextView = itemView.findViewById(R.id.tvMotorbikeDescription)
        val price: TextView = itemView.findViewById(R.id.tvMotorbikePrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotorbikeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_motorbike, parent, false)
        return MotorbikeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MotorbikeViewHolder, position: Int) {
        val motorbike = motorbikeList[position]
        holder.image.setImageResource(motorbike.imageResId)
        holder.name.text = motorbike.name
        holder.description.text = motorbike.description
        holder.price.text = motorbike.price
    }

    override fun getItemCount(): Int {
        return motorbikeList.size
    }
}
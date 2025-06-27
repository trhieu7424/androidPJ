package com.example.nguyentronghieu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        val rvRestaurants = findViewById<RecyclerView>(R.id.rvRestaurants)


        val restaurantList = listOf(
            Restaurant("Cơm Tấm Phúc Lộc Thọ", "Cơm, Đồ ăn Việt", 4.7, R.drawable.food_comtam),
            Restaurant("The Coffee House", "Cà phê, Trà sữa, Bánh", 4.5, R.drawable.food_coffeehouse),
            Restaurant("Bún Bò Huế Đông Ba", "Bún, Đồ ăn Việt", 4.6, R.drawable.food_bunbo),
            Restaurant("Gà Rán Popeyes", "Gà rán, Thức ăn nhanh", 4.4, R.drawable.food_popeyes)
        )



        val adapter = RestaurantAdapter(restaurantList)


        rvRestaurants.layoutManager = LinearLayoutManager(this)
        rvRestaurants.adapter = adapter
    }
}
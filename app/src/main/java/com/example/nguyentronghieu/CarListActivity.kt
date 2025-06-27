package com.example.nguyentronghieu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class CarListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_list)

        val rvCars = findViewById<RecyclerView>(R.id.rvCars)

        val carList = listOf(
            Car("Vinfast VF8", "Xe điện thông minh, công nghệ vượt trội", "1.2 tỷ", R.drawable.car_vinfast_vf8),
            Car("Toyota Vios", "Bền bỉ, tiết kiệm nhiên liệu", "592 triệu", R.drawable.car_toyota_vios),

            Car("Hyundai Accent", "Thiết kế trẻ trung, nhiều tiện nghi", "542 triệu", R.drawable.car_huyndai_accent),

            Car("Ford Ranger", "Vua bán tải, mạnh mẽ và đa dụng", "965 triệu", R.drawable.car_ford_ranger)
        )

        val adapter = CarAdapter(carList)
        rvCars.adapter = adapter
    }
}
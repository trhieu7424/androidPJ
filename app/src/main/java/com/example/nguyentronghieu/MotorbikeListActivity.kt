package com.example.nguyentronghieu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MotorbikeListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motorbike_list)

        val rvMotorbikes = findViewById<RecyclerView>(R.id.rvMotorbikes)

        val motorbikeList = listOf(
            Motorbike("Honda Wave Alpha", "Xe số quốc dân, bền bỉ vượt thời gian", "18.9 triệu", R.drawable.bike_wave_alpha),
            Motorbike("Honda Vision", "Xe tay ga thời trang, tiết kiệm xăng", "35.2 triệu", R.drawable.bike_vision),
            Motorbike("Yamaha Exciter 155", "Ông vua côn tay, mạnh mẽ và thể thao", "52.5 triệu", R.drawable.bike_exciter),
            Motorbike("Honda SH 125i", "Đẳng cấp và sang trọng", "81.7 triệu", R.drawable.bike_sh_125)
        )

        val adapter = MotorbikeAdapter(motorbikeList)
        rvMotorbikes.adapter = adapter
    }
}
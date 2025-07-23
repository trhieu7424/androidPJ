package com.example.nguyentronghieu

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CarListActivity : AppCompatActivity() {

    private lateinit var carAdapter: CarAdapter
    private val carList = mutableListOf<Car>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_list)

        val rvCars = findViewById<RecyclerView>(R.id.rvCars)

        carAdapter = CarAdapter(carList)
        rvCars.adapter = carAdapter

        fetchCarsFromFirestore()
    }

    private fun fetchCarsFromFirestore() {
        val db = Firebase.firestore
        db.collection("cars")
            .get()
            .addOnSuccessListener { documents ->
                carList.clear()
                for (document in documents) {

                    val car = document.toObject(Car::class.java)
                    carList.add(car)
                }
                carAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Lỗi tải dữ liệu: ${exception.message}", Toast.LENGTH_LONG).show()
            }
    }
}
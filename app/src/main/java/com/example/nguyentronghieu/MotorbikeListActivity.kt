package com.example.nguyentronghieu

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MotorbikeListActivity : AppCompatActivity() {

    private lateinit var motorbikeAdapter: MotorbikeAdapter
    private val motorbikeList = mutableListOf<Motorbike>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motorbike_list)

        val rvMotorbikes = findViewById<RecyclerView>(R.id.rvMotorbikes)


        motorbikeAdapter = MotorbikeAdapter(motorbikeList)
        rvMotorbikes.adapter = motorbikeAdapter


        fetchMotorbikesFromFirestore()
    }

    private fun fetchMotorbikesFromFirestore() {
        val db = Firebase.firestore
        db.collection("motorbikes")
            .get()
            .addOnSuccessListener { documents ->
                motorbikeList.clear()
                for (document in documents) {

                    val motorbike = document.toObject(Motorbike::class.java)
                    motorbikeList.add(motorbike)
                }
                motorbikeAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Lỗi tải dữ liệu: ${exception.message}", Toast.LENGTH_LONG).show()
            }
    }
}
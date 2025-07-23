package com.example.nguyentronghieu

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SearchActivity : AppCompatActivity() {

    private val allProducts = mutableListOf<Product>()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var rvSearchResults: RecyclerView
    private lateinit var tvNoResults: TextView
    private lateinit var etSearchInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.title = "Tìm kiếm"

        rvSearchResults = findViewById(R.id.rvSearchResults)
        tvNoResults = findViewById(R.id.tvNoResults)
        etSearchInput = findViewById(R.id.etSearchInput)


        searchAdapter = SearchAdapter(emptyList())
        rvSearchResults.adapter = searchAdapter


        fetchAllProducts()


        etSearchInput.addTextChangedListener { text ->
            filterProducts(text.toString())
        }
    }

    private fun fetchAllProducts() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val db = Firebase.firestore

                val carsTask = db.collection("cars").get().await()
                val motorbikesTask = db.collection("motorbikes").get().await()

                val fetchedList = mutableListOf<Product>()

                for (document in carsTask) {
                    val car = document.toObject(Car::class.java)
                    fetchedList.add(Product(car.name, car.description, car.price, car.imageUrl, "car"))
                }
                for (document in motorbikesTask) {
                    val motorbike = document.toObject(Motorbike::class.java)
                    fetchedList.add(Product(motorbike.name, motorbike.description, motorbike.price, motorbike.imageUrl, "motorbike"))
                }


                withContext(Dispatchers.Main) {
                    allProducts.clear()
                    allProducts.addAll(fetchedList)

                    searchAdapter.filterList(allProducts)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SearchActivity, "Lỗi tải dữ liệu: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun filterProducts(query: String) {
        val filteredList = if (query.isEmpty()) {
            allProducts
        } else {
            allProducts.filter {

                it.name.contains(query, ignoreCase = true)
            }
        }


        searchAdapter.filterList(filteredList)


        if (filteredList.isEmpty() && query.isNotEmpty()) {
            tvNoResults.visibility = View.VISIBLE
            rvSearchResults.visibility = View.GONE
        } else {
            tvNoResults.visibility = View.GONE
            rvSearchResults.visibility = View.VISIBLE
        }
    }
}
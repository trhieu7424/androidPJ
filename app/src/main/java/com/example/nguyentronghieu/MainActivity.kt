package com.example.nguyentronghieu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


data class FeaturedProduct(
    val name: String,
    val price: String,
    val imageUrl: String,
    val description: String,
    val type: String
)

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var featuredProductAdapter: FeaturedProductAdapter
    private val featuredProductList = mutableListOf<FeaturedProduct>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        setupRecyclerView()
        setupClickListeners()
        setupBottomNavigation()
        fetchFeaturedProducts()
    }


    private fun setupRecyclerView() {
        val rvProducts = findViewById<RecyclerView>(R.id.rv_featured_products)
        rvProducts.layoutManager = LinearLayoutManager(this)
        featuredProductAdapter = FeaturedProductAdapter(featuredProductList)
        rvProducts.adapter = featuredProductAdapter
    }


    private fun fetchFeaturedProducts() {
        val db = Firebase.firestore


        db.collection("cars").limit(2).get()
            .addOnSuccessListener { carDocuments ->
                for (document in carDocuments) {
                    val car = document.toObject(Car::class.java)
                    featuredProductList.add(FeaturedProduct(car.name, car.price, car.imageUrl, car.description, "car"))
                }

                fetchFeaturedMotorbikes()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Lỗi tải dữ liệu xe hơi", Toast.LENGTH_SHORT).show()
            }
    }


    private fun fetchFeaturedMotorbikes() {
        val db = Firebase.firestore


        db.collection("motorbikes").limit(2).get()
            .addOnSuccessListener { motorbikeDocuments ->
                for (document in motorbikeDocuments) {
                    val motorbike = document.toObject(Motorbike::class.java)
                    featuredProductList.add(FeaturedProduct(motorbike.name, motorbike.price, motorbike.imageUrl, motorbike.description, "motorbike"))
                }


                featuredProductList.shuffle()
                featuredProductAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Lỗi tải dữ liệu xe máy", Toast.LENGTH_SHORT).show()
            }
    }


    private fun setupClickListeners() {
        val searchBar = findViewById<LinearLayout>(R.id.search_bar)
        searchBar.setOnClickListener {

            startActivity(Intent(this, SearchActivity::class.java))
        }

        val servicesGrid = findViewById<GridLayout>(R.id.services_grid)
        servicesGrid.children.forEachIndexed { index, view ->
            view.setOnClickListener {
                when (index) {
                    0 -> startActivity(Intent(this, CarListActivity::class.java))
                    1 -> startActivity(Intent(this, MotorbikeListActivity::class.java))
                    2 -> startActivity(Intent(this, CartActivity::class.java))
                }
            }
        }
    }


    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_logout -> {
                    auth.signOut()
                    Toast.makeText(this, "Đã đăng xuất", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    false
                }
                else -> false
            }
        }
        bottomNav.selectedItemId = R.id.nav_home
    }


    inner class FeaturedProductAdapter(private val productList: List<FeaturedProduct>) :
        RecyclerView.Adapter<FeaturedProductAdapter.ProductViewHolder>() {

        inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val productImage: ImageView = itemView.findViewById(R.id.ivProductImage)
            val productName: TextView = itemView.findViewById(R.id.tvProductName)
            val productPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_featured_product, parent, false)
            return ProductViewHolder(view)
        }

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            val featuredProduct = productList[position]


            holder.productImage.load(featuredProduct.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_food)
                error(R.drawable.ic_food)
            }
            holder.productName.text = featuredProduct.name
            holder.productPrice.text = featuredProduct.price


            holder.itemView.setOnClickListener {

                val productToSend = Product(
                    name = featuredProduct.name,
                    description = featuredProduct.description,
                    price = featuredProduct.price,
                    imageUrl = featuredProduct.imageUrl,
                    type = featuredProduct.type
                )

                val context = holder.itemView.context
                val intent = Intent(context, ProductDetailActivity::class.java)
                intent.putExtra("EXTRA_PRODUCT", productToSend)
                context.startActivity(intent)
            }
        }

        override fun getItemCount() = productList.size
    }
}
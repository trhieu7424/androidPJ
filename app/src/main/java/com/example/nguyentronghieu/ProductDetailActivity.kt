package com.example.nguyentronghieu

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import coil.load

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)


        val ivImage = findViewById<ImageView>(R.id.ivProductDetailImage)
        val tvName = findViewById<TextView>(R.id.tvProductDetailName)
        val tvPrice = findViewById<TextView>(R.id.tvProductDetailPrice)
        val tvDescription = findViewById<TextView>(R.id.tvProductDetailDescription)

        val btnAddToCart = findViewById<Button>(R.id.btnDetailAddToCart)


        val product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA_PRODUCT", Product::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Product>("EXTRA_PRODUCT")
        }


        if (product != null) {

            tvName.text = product.name
            tvPrice.text = product.price
            tvDescription.text = product.description
            ivImage.load(product.imageUrl) {
                val placeholderIcon = if (product.type == "car") R.drawable.ic_car else R.drawable.ic_bike
                placeholder(placeholderIcon)
                error(placeholderIcon)
            }
            supportActionBar?.title = product.name


            btnAddToCart.setOnClickListener {

                CartManager.addItem(product)

                Toast.makeText(this, "${product.name} đã được thêm vào giỏ", Toast.LENGTH_SHORT).show()
            }

        } else {

            Toast.makeText(this, "Không thể tải thông tin sản phẩm.", Toast.LENGTH_SHORT).show()
            finish()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
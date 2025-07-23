package com.example.nguyentronghieu

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        supportActionBar?.title = "Giỏ hàng của bạn"

        val rvCart = findViewById<RecyclerView>(R.id.rvCart)
        val tvEmptyCart = findViewById<TextView>(R.id.tvEmptyCart)
        val btnCheckout = findViewById<Button>(R.id.btnCheckout)

        val cartItems = CartManager.items

        if (cartItems.isEmpty()) {
            rvCart.visibility = View.GONE
            btnCheckout.visibility = View.GONE
            tvEmptyCart.visibility = View.VISIBLE
        } else {
            rvCart.visibility = View.VISIBLE
            btnCheckout.visibility = View.VISIBLE
            tvEmptyCart.visibility = View.GONE

            rvCart.layoutManager = LinearLayoutManager(this)
            rvCart.adapter = CartAdapter(cartItems)
        }

        btnCheckout.setOnClickListener {
            Toast.makeText(this, "Thanh toán thành công!", Toast.LENGTH_LONG).show()

            CartManager.clearCart()

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            finish()
        }
    }
}
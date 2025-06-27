package com.example.nguyentronghieu

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.children
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupClickListeners()
        setupBottomNavigation()
    }

    private fun setupClickListeners() {
        val searchBar = findViewById<LinearLayout>(R.id.search_bar)
        val servicesGrid = findViewById<GridLayout>(R.id.services_grid)
        val adCard = findViewById<CardView>(R.id.ad_card)

        searchBar.setOnClickListener {
            Toast.makeText(this, "Chức năng tìm kiếm sẽ được phát triển!", Toast.LENGTH_SHORT).show()
        }

        adCard.setOnClickListener {
            val adUrl = "https://www.grab.com"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(adUrl))
            startActivity(intent)
        }


        servicesGrid.children.forEachIndexed { index, view ->
            view.setOnClickListener {
                when (index) {

                    0 -> {
                        val intent = Intent(this, CarListActivity::class.java)
                        startActivity(intent)
                    }


                    1 -> {
                        val intent = Intent(this, MotorbikeListActivity::class.java)
                        startActivity(intent)
                    }


                    2 -> {

                         val intent = Intent(this, FoodActivity::class.java)
                         startActivity(intent)
                // Toast.makeText(this, "Mở màn hình Đồ ăn!", Toast.LENGTH_SHORT).show()
                    }


                    3 -> {
                        val intent = Intent(this, ServiceDetailActivity::class.java)
                        intent.putExtra("SERVICE_NAME", "Giao hàng")
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    true
                }
                R.id.nav_activity -> {
                    Toast.makeText(this, "Mở màn hình Hoạt động!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_payment -> {
                    Toast.makeText(this, "Mở màn hình Thanh toán!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_account -> {
                    Toast.makeText(this, "Mở màn hình Tài khoản!", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        bottomNav.selectedItemId = R.id.nav_home
    }
}
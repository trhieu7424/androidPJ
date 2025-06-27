
package com.example.nguyentronghieu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ServiceDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_detail)

        val tvServiceName = findViewById<TextView>(R.id.tvServiceName)


        val serviceName = intent.getStringExtra("SERVICE_NAME")


        tvServiceName.text = serviceName
    }
}
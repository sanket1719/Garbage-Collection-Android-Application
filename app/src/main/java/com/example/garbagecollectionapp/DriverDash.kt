package com.example.garbagecollectionapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class DriverDash : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_dash)


        val adddriver = findViewById<ImageView>(R.id.adddriver)

        adddriver.setOnClickListener {
            val intent = Intent(applicationContext, ShowRequest::class.java)
            startActivity(intent)

        }

        val showcom = findViewById<ImageView>(R.id.showcom)

        showcom.setOnClickListener {
            val intent = Intent(applicationContext, sendlocation::class.java)
            startActivity(intent)

        }

        val Showfeed = findViewById<ImageView>(R.id.showfeedback)

        Showfeed.setOnClickListener {
            val intent = Intent(applicationContext, Classification::class.java)
            startActivity(intent)

        }
        val logout = findViewById<ImageView>(R.id.logout)

        logout.setOnClickListener {
            val intent = Intent(applicationContext, Home::class.java)
            startActivity(intent)

        }

    }
}
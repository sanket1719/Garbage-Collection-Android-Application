package com.example.garbagecollectionapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class AdminDash : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dash)

        val adddriver = findViewById<ImageView>(R.id.adddriver)

        adddriver.setOnClickListener {
            val intent = Intent(applicationContext, Adddriver::class.java)
            startActivity(intent)

        }

        val showcom = findViewById<ImageView>(R.id.showcom)

        showcom.setOnClickListener {
            val intent = Intent(applicationContext, ShowCompaint::class.java)
            startActivity(intent)

        }

        val Showfeed = findViewById<ImageView>(R.id.showfeedback)

        Showfeed.setOnClickListener {
            val intent = Intent(applicationContext, ShowFeedback::class.java)
            startActivity(intent)

        }

        val tips = findViewById<ImageView>(R.id.tips)

        tips.setOnClickListener {
            val intent = Intent(applicationContext, Home::class.java)
            startActivity(intent)

        }

    }
}
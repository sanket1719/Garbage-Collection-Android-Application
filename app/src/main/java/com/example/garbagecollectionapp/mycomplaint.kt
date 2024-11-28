package com.example.garbagecollectionapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class mycomplaint : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mycomplaint)

        val adddriver = findViewById<ImageView>(R.id.adddriver)

        adddriver.setOnClickListener {
            val intent = Intent(applicationContext, ShowUserStatus::class.java)
            startActivity(intent)

        }

        val showcom = findViewById<ImageView>(R.id.showcom)

        showcom.setOnClickListener {
            val intent = Intent(applicationContext, ShowUserComplaint::class.java)
            startActivity(intent)

        }

    }
}
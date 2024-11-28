package com.example.garbagecollectionapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserDash : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_dash)


        val adddriver = findViewById<ImageView>(R.id.adddriver)

        adddriver.setOnClickListener {
            val intent = Intent(applicationContext, ShowDriver::class.java)
            startActivity(intent)

        }

        val showcom = findViewById<ImageView>(R.id.showcom)

        showcom.setOnClickListener {
            val intent = Intent(applicationContext, mycomplaint::class.java)
            startActivity(intent)

        }

        val Showfeed = findViewById<ImageView>(R.id.showfeedback)

        Showfeed.setOnClickListener {
            val intent = Intent(applicationContext, UploadFeedback::class.java)
            startActivity(intent)

        }

        val addrequest = findViewById<ImageView>(R.id.addrequest)

        addrequest.setOnClickListener {
            val intent = Intent(applicationContext, Usenotificaation::class.java)
            startActivity(intent)

        }

        val tips = findViewById<ImageView>(R.id.tips)

        tips.setOnClickListener {
            val intent = Intent(applicationContext, Addtips::class.java)
            startActivity(intent)

        }

        val location = findViewById<ImageView>(R.id.location)

        location.setOnClickListener {
            val intent = Intent(applicationContext, ShowLocation::class.java)
            startActivity(intent)

        }
    }
}
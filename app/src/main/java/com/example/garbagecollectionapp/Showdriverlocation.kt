package com.example.garbagecollectionapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Showdriverlocation : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showdriverlocation)



        val txt = findViewById<TextView>(R.id.textView4)


        val btn = findViewById<Button>(R.id.btnlocation)

        btn.setOnClickListener {
            val prefs = getSharedPreferences("MY", MODE_PRIVATE)
            val address = prefs.getString("add", "")

            Toast.makeText(applicationContext,address.toString(),Toast.LENGTH_LONG).show()

          txt.setText(address)
        }



    }
}
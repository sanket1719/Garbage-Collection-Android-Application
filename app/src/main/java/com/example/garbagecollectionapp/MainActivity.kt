package com.example.garbagecollectionapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.garbagecollectionapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var auth : FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val edemail = binding.username
        val edpassword = binding.password

        val btnlogin = binding.loginButton






        auth = FirebaseAuth.getInstance()

        //login
        btnlogin.setOnClickListener {

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edemail.text.toString()).matches()) {
                edemail.setError("Enter Email Id")
                return@setOnClickListener
            } else if (edpassword.text.isEmpty()) {
                edpassword.setError("Enter Password")
                return@setOnClickListener
            }



            if (edemail.text.toString() == "admin@gmail.com" && edpassword.text.toString() == "1234") {
                val intent = Intent(applicationContext, AdminDash::class.java)
                startActivity(intent)

            }


        }
    }
}
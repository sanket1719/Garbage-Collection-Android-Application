package com.example.garbagecollectionapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.garbagecollectionapp.databinding.ActivityDriverloginBinding
import com.example.garbagecollectionapp.databinding.ActivityUserloginBinding
import com.google.firebase.auth.FirebaseAuth

class Userlogin : AppCompatActivity() {

    private lateinit var binding: ActivityUserloginBinding
    lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserloginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val edemail = binding.username
        val edpassword = binding.password

        val btnlogin = binding.loginButton
        val btnregister = binding.signupText
        val btnforgot = binding.forgot






        btnforgot.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.dailog_forgot,null)
            val username = view.findViewById<EditText>(R.id.ed_forgot)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                forgotpassword(username)
            })
            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->  })
            builder.show()
        }


        btnregister.setOnClickListener {
            val intent = Intent(applicationContext,Userregister::class.java)
            startActivity(intent)

        }

        auth = FirebaseAuth.getInstance()

        //login
        btnlogin.setOnClickListener {

            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(edemail.text.toString()).matches()) {
                edemail.setError("Enter Email Id")
                return@setOnClickListener
            }
            else if (edpassword.text.isEmpty()){
                edpassword.setError("Enter Password")
                return@setOnClickListener
            }



            auth.signInWithEmailAndPassword(edemail.text.toString(),edpassword.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(applicationContext,"successfully Login", Toast.LENGTH_LONG).show()
                        val intent = Intent(applicationContext,UserDash::class.java)
                        startActivity(intent)


                    }
                    else
                    {
                        Toast.makeText(applicationContext,"Failed to login", Toast.LENGTH_LONG).show()
                    }
                }
        }
        fun validateInputs(email: String, password: String): Boolean {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edemail.error = "Enter a valid email address"
                return false
            }
            if (password.isEmpty()) {
                edpassword.error = "Enter a password"
                return false
            }
            return true
        }

// Inside onCreate
        btnlogin.setOnClickListener {
            val email = edemail.text.toString().trim()
            val password = edpassword.text.toString().trim()

            if (validateInputs(email, password)) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(applicationContext, "Successfully logged in", Toast.LENGTH_SHORT).show()
                            val intent = Intent(applicationContext, UserDash::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(applicationContext, "Login failed. Please try again.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }


    }

    private fun forgotpassword(username: EditText) {

        auth.sendPasswordResetEmail(username!!.text.toString())
            .addOnCompleteListener {
                if(it.isSuccessful)
                {
                    Toast.makeText(applicationContext,"Email Sent", Toast.LENGTH_LONG).show()
                }
            }
    }
}
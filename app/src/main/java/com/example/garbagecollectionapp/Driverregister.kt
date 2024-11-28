package com.example.garbagecollectionapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.garbagecollectionapp.databinding.ActivityDriverregisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern

class Driverregister : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener {

    lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityDriverregisterBinding

    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    var shouldValidateOnFocusChange = false // Flag to control validation on focus change

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDriverregisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Driver")

        binding.loginButton.setOnClickListener {
            shouldValidateOnFocusChange = true // Enable validation on button click
            val name = binding.Name.editText?.text.toString().trim()
            val address = binding.address.editText?.text.toString().trim()
            val number = binding.number.editText?.text.toString().trim()
            val email = binding.username.editText?.text.toString().trim()
            val password = binding.password.editText?.text.toString().trim()

            if (validateInputs(name, address, number, email, password)) {
                createUserWithEmailAndPassword(name, address, email, password)
            } else {
                Toast.makeText(applicationContext, "Please fix errors in the form", Toast.LENGTH_LONG).show()
            }
        }

        // Set onFocusChangeListener for all input fields
        binding.Name.editText?.onFocusChangeListener = this
        binding.address.editText?.onFocusChangeListener = this
        binding.number.editText?.onFocusChangeListener = this
        binding.username.editText?.onFocusChangeListener = this
        binding.password.editText?.onFocusChangeListener = this
    }

    override fun onClick(view: View?) {
        // Handle click events if needed
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (!shouldValidateOnFocusChange) return // Skip validation if flag is false

        if (!hasFocus) {
            when (view?.id) {
                R.id.Name -> validateName()
                R.id.address -> validateAddress()
                R.id.number -> validateNumber()
                R.id.username -> validateEmail()
                R.id.password -> validatePassword()
            }
        } else {
            // Clear error message when the field gains focus
            when (view?.id) {
                R.id.Name -> binding.Name.error = null
                R.id.address -> binding.address.error = null
                R.id.number -> binding.number.error = null
                R.id.username -> binding.username.error = null
                R.id.password -> binding.password.error = null
            }
        }
    }

    private fun validateInputs(name: String, address: String, number: String, email: String, password: String): Boolean {
        return validateName() && validateAddress() && validateNumber() && validateEmail() && validatePassword()
    }

    private fun validateName(): Boolean {
        val name = binding.Name.editText?.text.toString().trim()
        return if (name.isEmpty()) {
            binding.Name.error = "Name is required"
            false
        } else {
            binding.Name.error = null
            true
        }
    }

    private fun validateAddress(): Boolean {
        val address = binding.address.editText?.text.toString().trim()
        return if (address.isEmpty()) {
            binding.address.error = "Address is required"
            false
        } else {
            binding.address.error = null
            true
        }
    }

    private fun validateNumber(): Boolean {
        val number = binding.number.editText?.text.toString().trim()
        return if (number.isEmpty() || number.length != 10) {
            binding.number.error = "Enter a valid 10-digit number"
            false
        } else {
            binding.number.error = null
            true
        }
    }

    private fun validateEmail(): Boolean {
        val email = binding.username.editText?.text.toString().trim()
        return if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.username.error = "Enter a valid email address"
            false
        } else {
            binding.username.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.password.editText?.text.toString().trim()

        // Regex pattern to match the required password format
        val pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@$!%*?&]{8,}\$")

        return if (!pattern.matcher(password).matches()) {
            binding.password.error = "Password must contain at least one (A-Z),(a-z),(0-9), one special character, and be at least 8 characters long"
            false
        } else {
            binding.password.error = null
            true
        }
    }

    private fun createUserWithEmailAndPassword(name: String, address: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    val currentUserdb = databaseReference?.child(currentUser?.uid!!)
                    currentUserdb?.child("name")?.setValue(name)
                    currentUserdb?.child("address")?.setValue(address)

                    Toast.makeText(applicationContext, "Registration Successful", Toast.LENGTH_LONG).show()
                    saveUserData(name, address)
                } else {
                    val errorMessage = task.exception?.message ?: "Registration Failed"
                    Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun saveUserData(name: String, address: String) {
        val sharedPreferences = getSharedPreferences("My", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.putString("address", address)
        editor.apply()
    }
}

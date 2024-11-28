package com.example.garbagecollectionapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.telephony.SmsManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference

class driverdetails : AppCompatActivity() {

    var name:String?=null
    var material:String?=null
    var manifacute:String?=null
    var origin:String?=null
    var weight:String?=null
    var rating:String?=null
    var demi:String?=null
    var price:String?=null

    var ref: DatabaseReference? = null
    var username:String?=null
    var usermobile:String?=null
    var useremail:String?=null
    var useraddress:String?=null
    var url:String?=null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driverdetails)


        val txtproname = findViewById<TextView>(R.id.txtname)
        val txtmaterial = findViewById<TextView>(R.id.txtmaterial)
        val txtaddress = findViewById<TextView>(R.id.txtmanifacture)

        val txtarea = findViewById<TextView>(R.id.txtorigin)
        val txtwight= findViewById<TextView>(R.id.txtweigth)

        val image = findViewById<ImageView>(R.id.image1)
        val mSharedPreference = PreferenceManager.getDefaultSharedPreferences(baseContext)
        val value = mSharedPreference.getString("user-email", "DEFAULT")

        val bundle = intent.extras


        name = bundle?.getString("proname")
        material = bundle?.getString("material")
        manifacute=bundle?.getString("manifacture")
        origin = bundle?.getString("origin")
        weight = bundle?.getString("weight")

        url = bundle?.getString("url")


        Glide.with(this@driverdetails).load(url).into(image)


        txtproname.setText("Driver Name:" +name)
        txtmaterial.setText("Address: "+material)
        txtaddress.setText("Mobile No: "+manifacute)
        txtarea.setText("Area: "+origin)
        txtwight.setText("Experiance: "+weight)



//        btnsend.setOnClickListener {
//            val smsManager = SmsManager.getDefault() as SmsManager
//            smsManager.sendTextMessage("+91$manifacute",null,"new Request",null,null)
//        }



    }
}
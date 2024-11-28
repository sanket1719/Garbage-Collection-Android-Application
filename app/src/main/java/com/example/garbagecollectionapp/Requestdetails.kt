package com.example.garbagecollectionapp

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.telephony.SmsManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference

class Requestdetails : AppCompatActivity() {

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
        setContentView(R.layout.activity_requestdetails)

        val txtproname = findViewById<TextView>(R.id.txtname)
        val txtmaterial = findViewById<TextView>(R.id.txtmaterial)
        val txtaddress = findViewById<TextView>(R.id.txtmanifacture)

        val txtarea = findViewById<TextView>(R.id.txtorigin)
        val txtwight= findViewById<TextView>(R.id.txtweigth)

        val image = findViewById<ImageView>(R.id.image1)
        val mSharedPreference = PreferenceManager.getDefaultSharedPreferences(baseContext)
        val value = mSharedPreference.getString("user-email", "DEFAULT")

        val bundle = intent.extras

        val btnsend = findViewById<Button>(R.id.btnsend)



        val btnotp = findViewById<Button>(R.id.btnotp)

        btnotp.setOnClickListener {
            val intent = Intent(applicationContext,phoneauth::class.java)
            startActivity(intent)

        }

        name = bundle?.getString("proname")
        material = bundle?.getString("material")
        manifacute=bundle?.getString("manifacture")
        origin = bundle?.getString("origin")
        weight = bundle?.getString("weight")

        url = bundle?.getString("url")


        Glide.with(this@Requestdetails).load(url).into(image)


        txtproname.setText("User Name:" +name)
        txtmaterial.setText("Address: "+material)
        txtaddress.setText("Mobile No: "+manifacute)
        txtarea.setText("Area: "+origin)
        txtwight.setText("Garbage Type: "+weight)



        btnsend.setOnClickListener {

            try {
                val uri = Uri.parse("https://www.google.co.in/maps/dir/"+"/"+ material)

                val intent = Intent(Intent.ACTION_VIEW,uri)
                intent.setPackage("com.google.android.apps.maps")
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }catch (e: ActivityNotFoundException)
            {
                val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
                val intent = Intent(Intent.ACTION_VIEW,uri)
                intent.setPackage("com.google.android.apps.maps")
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }

        }


    }
}
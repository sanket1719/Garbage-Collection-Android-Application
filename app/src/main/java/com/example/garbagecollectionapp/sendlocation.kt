package com.example.garbagecollectionapp

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.FirebaseDatabase
import java.io.IOException
import java.util.Locale

class sendlocation : AppCompatActivity() {


    var fusedLocationProviderClient: FusedLocationProviderClient? = null

    var lat: String? = null
    var log: String? = null
    var address: String? = null
    var counter = 1
    var area: String? = null


    var name:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sendlocation)


        val pref = getSharedPreferences("My", MODE_PRIVATE)
        name = pref.getString("name", "")
        Toast.makeText(applicationContext,name.toString(), Toast.LENGTH_LONG).show()

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        val i = Intent(applicationContext,DriverDash::class.java)
        when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> getlocation()
            KeyEvent.KEYCODE_VOLUME_DOWN -> startActivity(i)

        }
        return true
    }

    private fun getlocation() {
        //Toast.makeText(applicationContext,"location", Toast.LENGTH_LONG).show()
        val i = Intent(applicationContext,DriverDash::class.java)
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }

        fusedLocationProviderClient!!.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val geocoder = Geocoder(this@sendlocation, Locale.getDefault())
                try {
                    val addresses =
                        geocoder.getFromLocation(location.latitude, location.longitude, 1)


                    lat = addresses?.get(0)!!.latitude.toString()
                    log = addresses[0]!!.longitude.toString()
                    address = addresses.get(0).getAddressLine(0)
                    area = addresses.get(0).locality
                    Toast.makeText(applicationContext, address.toString(), Toast.LENGTH_LONG).show()


                    savedata(name,address)


                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        startActivity(i)
    }

    private fun savedata(name: String?, address: String?) {


        val data = FirebaseDatabase.getInstance().getReference().child("location")
        val service = Mylocation(name,address)


        data.push().setValue(service)

        Toast.makeText(applicationContext,"Uploaded", Toast.LENGTH_LONG).show()

    }


}
package com.example.garbagecollectionapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class AddStatus : AppCompatActivity() {

    var name:String?=null
    var number:String?=null
    var address:String?=null
    var des:String?=null
    var weight:String?=null
    var rating:String?=null
    var demi:String?=null
    var price:String?=null
    var edname: EditText?=null
    var edprofile: EditText?=null
    var edcriteria: EditText?=null
    var edtime: EditText?=null
    var edhrname: EditText?=null
    var edcontact: EditText?=null
    var edaddress: EditText?=null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_status)


        edname = findViewById<EditText>(R.id.edname)
        edprofile = findViewById<EditText>(R.id.edstatus)
        edcriteria = findViewById<EditText>(R.id.eddate)



        val bundle = intent.extras

        name = bundle?.getString("name")
        number = bundle?.getString("number")
        address =bundle?.getString("address")
        des = bundle?.getString("des")





    }

    fun UploadData(view: View?) {
        val name = edname!!.text.toString()
        val status = edprofile!!.text.toString()
        val date = edcriteria!!.text.toString()


        val data = FirebaseDatabase.getInstance().reference.child("status")
        val service = Status(name,status,date,des)


        data.push().setValue(service)

        Toast.makeText(applicationContext,"Uploaded", Toast.LENGTH_LONG).show()



    }
}
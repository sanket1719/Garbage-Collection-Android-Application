package com.example.garbagecollectionapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class ShowDriver : AppCompatActivity() {

    private var database: FirebaseDatabase? = null
    private var adapter: DriverAdapter? = null
    private var driverList: ArrayList<Driver>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_driver)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        driverList = ArrayList()

        val pref = getSharedPreferences("Mypre", MODE_PRIVATE)
        val address = pref.getString("address", "")
        Toast.makeText(applicationContext, address.toString(), Toast.LENGTH_LONG).show()

        database = FirebaseDatabase.getInstance()
        val driverRef = database?.getReference("Driver")
        val query: Query = driverRef?.orderByChild("Address")?.equalTo(address) ?: return

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                driverList?.clear()
                for (data in dataSnapshot.children) {
                    val driver: Driver? = data.getValue(Driver::class.java)
                    driver?.let { driverList?.add(it) }
                }
                adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(applicationContext, "Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })

        adapter = DriverAdapter(driverList ?: ArrayList(), applicationContext)
        recyclerView.adapter = adapter
    }
}

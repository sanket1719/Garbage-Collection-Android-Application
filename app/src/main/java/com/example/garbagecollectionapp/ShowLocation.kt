package com.example.garbagecollectionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// For ShowLocation activity
class ShowLocation : AppCompatActivity() {
    // Variables
    var ref: DatabaseReference? = null
    var list: ArrayList<Mylocation>? = null
    private var listener: LocationAdapter.RecyclerViewClickListener? = null
    var recyclerView: RecyclerView? = null
    var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_location)

        // Initialize views and variables
        ref = FirebaseDatabase.getInstance().reference.child("location")
        recyclerView = findViewById(R.id.recyclerview)
        searchView = findViewById(R.id.searchview)
    }

    override fun onStart() {
        super.onStart()

        // Fetch data from Firebase
        if (ref != null) {
            ref!!.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        list = ArrayList()
                        for (ds in snapshot.children) {
                            list!!.add(ds.getValue(Mylocation::class.java)!!)
                        }
                        val adapter = LocationAdapter(list, listener)
                        recyclerView!!.adapter = adapter
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ShowLocation, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        // Set up search functionality
        if (searchView != null) {
            searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    search(newText)
                    return true
                }
            })
        }
    }

    // Perform search
    private fun search(query: String) {
        try {
            val filteredList = ArrayList<Mylocation?>()
            for (location in list!!) {
                if (location!!.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(location)
                }
            }
            val adapter = LocationAdapter(filteredList, listener)
            recyclerView!!.adapter = adapter
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

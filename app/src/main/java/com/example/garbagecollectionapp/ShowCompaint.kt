package com.example.garbagecollectionapp

import android.annotation.SuppressLint
import android.content.Intent
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

class ShowCompaint : AppCompatActivity() {

    var ref: DatabaseReference? = null
    var list: ArrayList<Complaint>? = null
    private var listener: ComplaintAdapter.RecyclerViewClickListener? = null

    var recyclerView: RecyclerView? = null

    var searchView: SearchView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_compaint)

        ref = FirebaseDatabase.getInstance().reference.child("complaint")
        recyclerView = findViewById(R.id.recyclerview)
        searchView = findViewById(R.id.searchview)
    }


    override fun onStart() {
        super.onStart()

        if (ref != null) {
            ref!!.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        list = ArrayList()
                        for (ds in snapshot.children) {
                            list!!.add(ds.getValue(Complaint::class.java)!!)
                        }
                        setOnClickListner()
                        val adapter = ComplaintAdapter(list, listener)
                        recyclerView!!.adapter = adapter
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ShowCompaint, "error", Toast.LENGTH_SHORT).show()
                }
            })
        }


        if (searchView != null) {
            searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(s: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(s: String): Boolean {
                    search(s)
                    return true
                }
            })
        }
    }

    private fun setOnClickListner() {
        listener = ComplaintAdapter.RecyclerViewClickListener { v, position ->
//            Toast.makeText(applicationContext,"Driver",Toast.LENGTH_LONG).show()
            val intent = Intent(applicationContext, AddStatus::class.java)
            intent.putExtra("name", list!![position].name)
            intent.putExtra("address",list!![position].address)
            intent.putExtra("number",list!![position].number)
            intent.putExtra("des",list!![position].des)
            startActivity(intent)
        }
    }

    private fun search(s: String) {

        try{
            val mylist = ArrayList<Complaint?>()
            for (`object` in list!!) {
                if (`object`!!.getAddress().toLowerCase().contains(s.toLowerCase())) {
                    mylist.add(`object`)
                }
            }
            val adapter = ComplaintAdapter(mylist,listener)
            recyclerView!!.adapter = adapter
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}
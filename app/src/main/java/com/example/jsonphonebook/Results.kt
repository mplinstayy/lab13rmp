package com.example.jsonphonebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.jsonphonebook.data.DATABASE_NAME
import com.example.jsonphonebook.data.PhoneDAO
import com.example.jsonphonebook.data.PhoneDatabase
import com.example.jsonphonebook.data.model.Names
import com.example.jsonphonebook.data.model.Phone
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Results : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private var index: Int = -1
    private lateinit var db: PhoneDatabase
    private lateinit var phoneDAO: PhoneDAO
    private  var names: MutableList<Names> = mutableListOf()
    private  var phone: MutableList<Phone> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        db = Room.databaseBuilder(this, PhoneDatabase::class.java, DATABASE_NAME).build()
        phoneDAO = db.phoneDAO()

        getPhoneBook()
        updateInfo()
    }

    private fun updateInfo(){
        rv = findViewById(R.id.rvContacts)
        val adapter = ContactRVAdapter(this, names, phone)
        val rvListener = object : ContactRVAdapter.ItemClickListener{
            override fun onItemClick(view: View?, position: Int) {
                val intent = Intent(this@Results, AddContacts::class.java)
                intent.putExtra("index", position)
                index = position
                startActivity(intent)
            }
        }
        adapter.setOnClickListener(rvListener)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume(){
        super.onResume()
        if (index != -1){
            getPhoneBook()
            updateInfo()
            rv.adapter?.notifyItemChanged(index)
        }
    }

    private fun getPhoneBook() {
        names.clear()
        phone.clear()
        val contacts = phoneDAO.getAllNames()
        val numbers = phoneDAO.getAllPhones()
        contacts.observe(this, androidx.lifecycle.Observer {
            it.forEach {
                names.add(Names(it.id, it.title))
                updateInfo()
            }
        })
        numbers.observe(this, androidx.lifecycle.Observer {
            it.forEach {
                phone.add(Phone(it.id, it.nameId, it.phone, it.description))
                updateInfo()
            }
        })
    }
}

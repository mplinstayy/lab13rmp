package com.example.jsonphonebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.room.Room
import com.example.jsonphonebook.data.DATABASE_NAME
import com.example.jsonphonebook.data.PhoneDAO
import com.example.jsonphonebook.data.PhoneDatabase
import com.example.jsonphonebook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var btnAdd: Button
    private lateinit var btnShowAll: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.buttonAddContact)
        btnShowAll = findViewById(R.id.buttonShowContacts)

        btnAdd.setOnClickListener {
            val intent = Intent(this, AddContacts::class.java)
            startActivity(intent)
        }
        btnShowAll.setOnClickListener {
            val intent = Intent(this, Results::class.java)
            startActivity(intent)
        }
    }
}
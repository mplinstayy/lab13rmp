package com.example.jsonphonebook

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import androidx.room.Room
import com.example.jsonphonebook.data.DATABASE_NAME
import com.example.jsonphonebook.data.PhoneDAO
import com.example.jsonphonebook.data.PhoneDatabase
import com.example.jsonphonebook.data.model.Names
import com.example.jsonphonebook.data.model.Phone
import com.example.jsonphonebook.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.concurrent.Executors

class AddContacts : AppCompatActivity() {

    private lateinit var editName:EditText
    private lateinit var editNumber:EditText
    private lateinit var btnSaveContact:Button
    private lateinit var tv:TextView
    private lateinit var btnDelete: Button
    private var index: Int = -1
    private lateinit var db: PhoneDatabase
    private lateinit var phoneDAO: PhoneDAO

    private var id: Int = 0

    private  var names: MutableList<Names> = mutableListOf()
    private  var phones: MutableList<Phone> = mutableListOf()

    private  var namesList: MutableList<Names> = mutableListOf()
    private  var phonesList: MutableList<Phone> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contacts)

        btnSaveContact = findViewById(R.id.buttonAdd)
        editName = findViewById(R.id.editTextTextPersonName)
        editNumber = findViewById(R.id.editTextNumber)
        tv = findViewById(R.id.textView2)
        btnDelete = findViewById(R.id.delete_button)


        db = Room.databaseBuilder(this, PhoneDatabase::class.java, DATABASE_NAME).build()
        phoneDAO = db.phoneDAO()

        index = intent.getIntExtra("index", -1)

        if (index != -1){
            tv.text = "Изменить контакт"
            btnSaveContact.text = "Изменить"
            GetDataBase()

            val contacts = phoneDAO.getName(0)
            val numbers = phoneDAO.getPhone(0)

            contacts.observe(this, androidx.lifecycle.Observer{
                it.forEach { it ->
                   //names.add(Names(it.id, it.title))

                   editName.setText(it.title)
                   id = it.id
                }
            })
            numbers.observe(this, androidx.lifecycle.Observer{
                it.forEach { it ->
                    //phones.add(Phone(it.id, it.nameId, it.phone, it.description))

                    editNumber.setText(it.phone)
                    id = it.id
                }
            })
        }


        btnSaveContact.setOnClickListener {
            if (index == -1){
                if (editName.text.isNotEmpty() && editNumber.text.isNotEmpty()){
                    GetDataBase()
                    val name = editName.text.toString()
                    val phone = editNumber.text.toString()
                    val executor = Executors.newSingleThreadExecutor()
                    executor.execute{
                        phoneDAO.addName(Names(0, name))
                        phoneDAO.addPhone(Phone(0,0, phone))
                    }
                }else{
                    Toast.makeText(this, "Оба поля должны быть заполнены!", Toast.LENGTH_SHORT).show()
                }
            }else if (index != -1){
                GetDataBase()
                val name = editName.text.toString()
                val phone = editNumber.text.toString()

                val executor = Executors.newSingleThreadExecutor()
                executor.execute{
                    phoneDAO.saveName(Names(id, name))
                    phoneDAO.savePhone(Phone(id, id, phone))
                }
            }
            editName.setText("")
            editNumber.setText("")
        }


        btnDelete.setOnClickListener {
            val name = editName.text.toString()
            val phone = editNumber.text.toString()
            val executor = Executors.newSingleThreadExecutor()
            executor.execute{
                phoneDAO.killName(Names(id, name))
                phoneDAO.killPhone(Phone(id,id, phone))
            }
            GetDataBase()
        }
    }


    fun GetDataBase()
    {
        db = Room.databaseBuilder(this, PhoneDatabase::class.java, DATABASE_NAME).build()
        phoneDAO = db.phoneDAO()
        names.clear()
        phones.clear()
        val namess = phoneDAO.getAllNames()
        val phoness = phoneDAO.getAllPhones()
        namess.observe(this){
            namesList.addAll(it)
        }
        phoness.observe(this){
            phonesList.addAll(it)
        }
    }


}
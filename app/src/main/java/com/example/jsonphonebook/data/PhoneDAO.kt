package com.example.jsonphonebook.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jsonphonebook.data.model.Names
import com.example.jsonphonebook.data.model.Phone

@Dao
interface PhoneDAO{

    @Query("SELECT * FROM $NAME_TABLE")
    fun getAllNames(): LiveData<List<Names>>

    @Query("SELECT * FROM $NAME_TABLE WHERE _id=:id")
    fun getName(id: Int): LiveData<List<Names>>

    @Insert
    fun addName(names: Names)
    @Update
    fun saveName(names: Names)
    @Delete
    fun killName(names: Names)




    @Query("SELECT * FROM $PHONE_TABLE")
    fun getAllPhones(): LiveData<List<Phone>>

    @Query("SELECT * FROM $PHONE_TABLE WHERE _id=:id")
    fun getPhone(id: Int): LiveData<List<Phone>>

    @Insert
    fun addPhone(phone: Phone)
    @Update
    fun savePhone(phone: Phone)
    @Delete
    fun killPhone(phone: Phone)
}
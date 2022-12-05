package com.example.jsonphonebook.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jsonphonebook.data.model.Names
import com.example.jsonphonebook.data.model.Phone

@Database(entities = [Names::class, Phone::class], version = 1)
abstract class PhoneDatabase: RoomDatabase() {
    abstract fun phoneDAO(): PhoneDAO
}
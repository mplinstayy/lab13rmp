package com.example.jsonphonebook.data.model

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jsonphonebook.data.PHONE_TABLE

@Entity(tableName = PHONE_TABLE)
data class Phone (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = BaseColumns._ID)
    val id: Int,
    var nameId: Int,
    var phone: String,
    var description: String = ""
)
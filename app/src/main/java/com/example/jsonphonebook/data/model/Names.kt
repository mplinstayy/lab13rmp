package com.example.jsonphonebook.data.model

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jsonphonebook.data.NAME_TABLE

@Entity(tableName = NAME_TABLE)
data class Names (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = BaseColumns._ID)
    val id:Int,
    @ColumnInfo(index = true)
    var title:String
)
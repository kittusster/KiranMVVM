package com.linoop.kiranmvvm.repository.data.localdatabse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.linoop.kiranmvvm.models.UserTable

@Database(entities = [UserTable::class], version = 1, exportSchema = true)
abstract class MyDatabase : RoomDatabase(){
    abstract fun getDao(): MyDao
}
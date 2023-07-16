package com.linoop.kiranmvvm.repository.data.localdatabse

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linoop.kiranmvvm.Constants.USER_TABLE
import com.linoop.kiranmvvm.models.UserTable

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(userModel: UserTable): Long

    @Query("SELECT * FROM $USER_TABLE WHERE user_id == :userId")
    suspend fun getUserById(userId: Long): UserTable

    @Query("SELECT * FROM $USER_TABLE")
    suspend fun getUsers(): List<UserTable>

    @Query("SELECT user_id FROM $USER_TABLE WHERE email == :email AND password == :password")
    suspend fun getUserIds(email: String, password: String): List<Int>

    @Query("SELECT user_id FROM $USER_TABLE WHERE email == :email AND password == :password")
    suspend fun checkLogin(email:String, password: String) : List<Int>
}
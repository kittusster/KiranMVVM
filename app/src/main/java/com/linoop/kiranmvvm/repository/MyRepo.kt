package com.linoop.kiranmvvm.repository

import com.linoop.kiranmvvm.models.UserTable
import com.linoop.kiranmvvm.repository.data.localdatabse.MyDao
import com.linoop.kiranmvvm.repository.data.networkapi.MyWebApi
import kotlinx.coroutines.delay
import javax.inject.Inject

class MyRepo @Inject constructor(private val myDao: MyDao, private val myWebApi: MyWebApi) {

    suspend fun createUser(userTable: UserTable) {
        delay(3000)
        myDao.createUser(userTable)
    }

    suspend fun checkLogin(email: String, password: String): Boolean {
        delay(3000)
        return myDao.checkLogin(email, password).isNotEmpty()
    }

    suspend fun createUser(userTable: UserTable) {
        //delay(3000) // testing
        myWebApi.createUser(userTable.name,userTable.password, userTable.email )
    }

    suspend fun checkLogin(email: String, password: String): Boolean {
        //delay(3000)
        myWebApi.checkLogin(email, password).body()?.let {
            return it.status
        }
        return false
    }
}
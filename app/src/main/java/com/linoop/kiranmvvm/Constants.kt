package com.linoop.kiranmvvm

import androidx.lifecycle.MutableLiveData
import com.linoop.kiranmvvm.models.UserTable
import com.linoop.kiranmvvm.utils.DatabaseResult

typealias SignupModel = DatabaseResult<UserTable>
object Constants {
     const val TAG = "KiranMVVMLogs"

     const val MY_DATABASE_NAME = "my_database"
     const val USER_TABLE = "UserTable"

     const val BASE_URL = "http://192.168.0.103:8000/api/"
     const val TOKEN = "token"

     const val MY_PREF = "my_pref"
}
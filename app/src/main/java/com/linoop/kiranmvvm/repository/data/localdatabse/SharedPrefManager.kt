package com.linoop.kiranmvvm.repository.data.localdatabse

import android.content.SharedPreferences
import androidx.core.content.edit
import com.linoop.kiranmvvm.Constants.TOKEN
import javax.inject.Inject

class SharedPrefManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun setToken(token: String) = sharedPreferences.edit { putString(TOKEN, token) }

    fun getToken() = sharedPreferences.getString(TOKEN, "")

}
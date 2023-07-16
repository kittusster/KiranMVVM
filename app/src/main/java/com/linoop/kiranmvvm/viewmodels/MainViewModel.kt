package com.linoop.kiranmvvm.viewmodels

import SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linoop.kiranmvvm.SignupModel
import com.linoop.kiranmvvm.models.UserTable
import com.linoop.kiranmvvm.repository.MyRepo
import com.linoop.kiranmvvm.utils.DatabaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val myRepo: MyRepo) : ViewModel() {

    private val _signupResponse = MutableLiveData<SignupModel>()
    val signupResponse: LiveData<SignupModel> get() = _signupResponse

    private val _loginResponse = SingleLiveEvent<DatabaseResult<String>>()
    val loginResponse: SingleLiveEvent<DatabaseResult<String>> get() = _loginResponse

    fun signup(userTable: UserTable) = viewModelScope.launch {
        if (userTable.name.isBlank() || userTable.email.isBlank() || userTable.password.isBlank()) {
            _signupResponse.postValue(DatabaseResult.Error("Invalid inputs"))
        } else {
            _signupResponse.postValue(DatabaseResult.Loading())
            myRepo.createUser(userTable)
            _signupResponse.postValue(
                DatabaseResult.Success(
                    userTable,
                    "User created successfully"
                )
            )
        }
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        if (email.isBlank() || password.isBlank()) {
            _loginResponse.postValue(DatabaseResult.Error("Invalid inputs"))
        } else {
            _loginResponse.postValue(DatabaseResult.Loading())
            if (myRepo.checkLogin(email, password)) {
                _loginResponse.postValue(
                    DatabaseResult.Success("Username $email password $password", "Login successful")
                )
            } else _loginResponse.postValue(
                DatabaseResult.Error( "Invalid Credential", "Username $email password $password",)
            )
        }
    }
}
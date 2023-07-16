package com.linoop.kiranmvvm.models

data class LoginResponse(
    val email: String,
    val id: Int,
    val message: String,
    val status: Boolean,
    val token: String
)
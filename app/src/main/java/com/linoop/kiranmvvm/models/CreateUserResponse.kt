package com.linoop.kiranmvvm.models

data class CreateUserResponse(
    val message: String,
    val status: Boolean,
    val token: String
)
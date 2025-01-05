package com.example.backofficeproject

data class Auth(
    val id: String,
    val nama: String,
    val email: String,
    val password: String,
    val divisi: String,
    val role: String = "user"
){
    constructor() : this("", "", "", "", "")
}
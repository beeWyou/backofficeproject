package com.example.backofficeproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue

class Login : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView

    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi Firebase
        database = FirebaseDatabase.getInstance()
        myRef = database.reference

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Mencari pengguna dengan email yang dimasukkan
                myRef.child("users").orderByChild("email").equalTo(email)
                    .get()
                    .addOnSuccessListener { snapshot ->
                        if (snapshot.exists()) {
                            // Jika email ditemukan, ambil data pengguna
                            val user = snapshot.children.first().getValue(User::class.java)
                            if (user?.password == password) {
                                // Jika password cocok, periksa role pengguna
                                if (user.role == "admin") {
                                    // Jika admin, redirect ke DashboardAdmin
                                    startActivity(Intent(this, DashboardAdmin::class.java))
                                } else {
                                    // Jika user biasa, redirect ke Menu
                                    startActivity(Intent(this, Menu::class.java))
                                }
                                finish()  // Agar tidak bisa kembali ke halaman login setelah berhasil login
                            } else {
                                Toast.makeText(this, "Password Salah", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this, "Akun tidak ditemukan", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Gagal memverifikasi akun", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Harap isi email dan password", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegister.setOnClickListener {
            // Pindah ke halaman Register
            startActivity(Intent(this@Login, Register::class.java))
        }
    }
}

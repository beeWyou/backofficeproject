package com.example.backofficeproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var usersRef: DatabaseReference

    private val TAG = "Login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi FirebaseAuth dan Database
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        usersRef = database.getReference("users")

        // Inisialisasi komponen UI
        val emailEditText = findViewById<EditText>(R.id.etEmail)
        val passwordEditText = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val registerTextView = findViewById<TextView>(R.id.tvRegister)

        // Klik tombol login
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Validasi input
            if (email.isEmpty()) {
                emailEditText.error = "Email harus diisi"
                emailEditText.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                passwordEditText.error = "Password harus diisi"
                passwordEditText.requestFocus()
                return@setOnClickListener
            }

            // Melakukan login menggunakan Firebase Authentication
            signInWithEmailAndPassword(email, password)
        }

        // Klik teks untuk menuju halaman registrasi
        registerTextView.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login berhasil
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // Jika gagal, tampilkan pesan kesalahan
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // Mengambil data pengguna setelah berhasil login
            fetchUserData(user.uid)
        } else {
            // Jika gagal login, tampilkan toast pesan kesalahan
            Toast.makeText(this, "Login failed.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchUserData(userId: String) {
        // Mengambil data pengguna dari Realtime Database berdasarkan UID
        usersRef.child(userId).get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    // Jika data ditemukan, ambil role dan name
                    val role = snapshot.child("role").value.toString()
                    val name = snapshot.child("name").value.toString()

                    // Log data pengguna
                    Log.d(TAG, "User role: $role, name: $name")

                    // Menampilkan data pengguna sesuai dengan role
                    navigateBasedOnRole(role, name)
                } else {
                    Toast.makeText(this, "Data pengguna tidak valid", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                // Menangani kesalahan jika terjadi saat mengambil data
                Log.e(TAG, "Error fetching user data: ${exception.message}")
                Toast.makeText(this, "Gagal memuat data pengguna: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun navigateBasedOnRole(role: String, name: String) {
        // Arahkan pengguna ke halaman berbeda berdasarkan role mereka
        Log.d(TAG, "Navigating based on role: $role")

        when (role) {
            "Admin" -> {
                Toast.makeText(this, "Selamat datang Admin, $name", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DashboardAdmin::class.java))
            }
            "User" -> {
                Toast.makeText(this, "Selamat datang, $name", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Menu::class.java))
            }
            else -> {
                // Menangani error jika role tidak valid
                Toast.makeText(this, "Role tidak valid", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        finish()  // Menutup activity setelah pengalihan
    }
}

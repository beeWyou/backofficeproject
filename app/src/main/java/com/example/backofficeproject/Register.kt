package com.example.backofficeproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.widget.ArrayAdapter

class Register : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var roleSpinner: Spinner  // Spinner untuk memilih role

    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inisialisasi Firebase
        database = FirebaseDatabase.getInstance()
        myRef = database.reference

        // Menginisialisasi view
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnRegister = findViewById(R.id.btnRegister)
        roleSpinner = findViewById(R.id.roleSpinner)  // Menambahkan Spinner

        // Membuat adapter untuk Spinner
        val roleAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.role_array,
            android.R.layout.simple_spinner_item
        )
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        roleSpinner.adapter = roleAdapter

        // Menangani klik tombol Register
        btnRegister.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val role = roleSpinner.selectedItem.toString() // Mendapatkan role yang dipilih

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && role != "Pilih Role") {
                // Membuat objek user baru
                val userId = myRef.push().key  // Menghasilkan ID unik untuk pengguna

                if (userId != null) {
                    // Membuat objek User dengan data yang diambil dari EditText dan Spinner
                    val user = User(name, email, password, role)

                    // Menyimpan data pengguna ke Firebase
                    myRef.child("users").child(userId).setValue(user)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show()

                                // Redirect ke MainActivity setelah berhasil register
                                val intent = Intent(this@Register, MainActivity::class.java)
                                startActivity(intent)

                                // Menutup Register Activity agar tidak bisa kembali ke halaman Register
                                finish()
                            } else {
                                Toast.makeText(this, "Pendaftaran Gagal", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            } else {
                Toast.makeText(this, "Harap isi semua kolom dan pilih role", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

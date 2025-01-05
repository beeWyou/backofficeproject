package com.example.backofficeproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter  // Pastikan mengimpor ArrayAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var roleSpinner: Spinner
    private lateinit var btnRegister: Button

    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inisialisasi Firebase
        database = FirebaseDatabase.getInstance()
        myRef = database.reference

        // Inisialisasi komponen
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        roleSpinner = findViewById(R.id.roleSpinner)
        btnRegister = findViewById(R.id.btnRegister)

        // Menghubungkan spinner dengan data dari strings.xml
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.role_array, // Array yang didefinisikan di strings.xml
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        roleSpinner.adapter = adapter

        btnRegister.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val selectedRole = roleSpinner.selectedItem.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val userId = myRef.push().key

                if (userId != null) {
                    val user = User(name, email, password, selectedRole) // Menyertakan role

                    myRef.child("users").child(userId).setValue(user)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show()

                                // Intent untuk berpindah ke MainActivity
                                val intent = Intent(this@Register, MainActivity::class.java)
                                startActivity(intent)
                                finish() // Menutup Register Activity
                            } else {
                                Toast.makeText(this, "Pendaftaran Gagal", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            } else {
                Toast.makeText(this, "Harap isi semua kolom", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

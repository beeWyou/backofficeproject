package com.example.backofficeproject

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DashboardAdmin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboardadmin)

        // Mendapatkan referensi ke elemen UI
        val backIcon: ImageView = findViewById(R.id.back_icon)
        val doctorName: TextView = findViewById(R.id.doctor_name)
        val schedule: TextView = findViewById(R.id.schedule)

        // Menambahkan event listener untuk tombol kembali
        backIcon.setOnClickListener {
            Toast.makeText(this, "Back button clicked", Toast.LENGTH_SHORT).show()
            finish() // Menutup activity saat tombol back diklik
        }

        // Menambahkan listener untuk elemen lainnya jika diperlukan
        doctorName.setOnClickListener {
            Toast.makeText(this, "Doctor name clicked", Toast.LENGTH_SHORT).show()
        }

        schedule.setOnClickListener {
            Toast.makeText(this, "Schedule clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
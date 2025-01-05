package com.example.backofficeproject

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LaporanPenilaianAdmin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.penilaian_admin)

        val btnInputPen: Button = findViewById(R.id.btnInputPen)

        btnInputPen.setOnClickListener {
            Toast.makeText(this, "Input Penilaian clicked", Toast.LENGTH_SHORT).show()
        }
    }
}

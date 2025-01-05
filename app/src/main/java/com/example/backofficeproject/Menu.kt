package com.example.backofficeproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu) // Menghubungkan layout

        // Tombol "Riwayat Penilaian & Laporan Evaluasi"
        findViewById<Button>(R.id.btn_evaluation_report).setOnClickListener {
            Toast.makeText(this, "Riwayat Penilaian Dipilih", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, PenilaianAdapter::class.java)
            startActivity(intent)
        }

        // Tombol "Laporan Kinerja"
        findViewById<Button>(R.id.btn_performance_report).setOnClickListener {
            Toast.makeText(this, "Laporan Kinerja Dipilih", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LaporanKinerjaUser::class.java)
            startActivity(intent)
        }

        // Tombol "Laporan Absensi"
        findViewById<Button>(R.id.btn_absence_report).setOnClickListener {
            Toast.makeText(this, "Laporan Absensi Dipilih", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LaporanAbsensi::class.java)
            startActivity(intent)
        }
    }
}
package com.example.backofficeproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
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

        // Tombol Logout
        findViewById<ImageView>(R.id.logout_icon).setOnClickListener {
            onLogoutClicked(it)
        }
    }

    private fun onLogoutClicked(view: View) {
        // Menghapus status login di SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()  // Menghapus semua data yang ada di SharedPreferences
        editor.apply()

        // Menampilkan Toast sebagai konfirmasi
        Toast.makeText(this, "Anda telah keluar", Toast.LENGTH_SHORT).show()

        // Navigasi kembali ke halaman login
        val intent = Intent(this@Menu, MainActivity::class.java)
        startActivity(intent)
        finish()  // Menutup Menu agar tidak bisa kembali ke halaman ini setelah logout
    }
}

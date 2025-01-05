package com.example.backofficeproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DashboardAdmin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboardadmin)

        // Menambahkan event klik pada Input Penilaian
        val inputPenilaian: TextView = findViewById(R.id.input_penilaian)
        inputPenilaian.setOnClickListener {
            // Arahkan ke halaman Input Penilaian
            val intent = Intent(this@DashboardAdmin, InputPenilaian::class.java)
            startActivity(intent)
        }

        // Menambahkan event klik pada Riwayat Penilaian
        val riwayatPenilaian: TextView = findViewById(R.id.riwayat_penilaian)
        riwayatPenilaian.setOnClickListener {
            // Arahkan ke halaman Riwayat Penilaian
            val intent = Intent(this@DashboardAdmin, PenilaianList::class.java)
            startActivity(intent)
        }
    }

    // Logika untuk Card Evaluasi 1
    fun onCardEvaluasi1Clicked(view: View) {
        // Tampilkan toast saat Card Evaluasi 1 diklik
        Toast.makeText(this, "Input Penilaian dipilih", Toast.LENGTH_SHORT).show()

        // Tambahkan logika untuk navigasi, jika diperlukan
        val intent = Intent(this@DashboardAdmin, InputPenilaian::class.java)
        startActivity(intent)
    }

    // Logika untuk Card Evaluasi 2
    fun onCardEvaluasi2Clicked(view: View) {
        // Tampilkan toast saat Card Evaluasi 2 diklik
        Toast.makeText(this, "Riwayat Penilaian dipilih", Toast.LENGTH_SHORT).show()

        // Tambahkan logika untuk navigasi, jika diperlukan
        val intent = Intent(this@DashboardAdmin, PenilaianList::class.java)
        startActivity(intent)
    }

    // Fungsi untuk handle logout
    fun onLogoutClicked(view: View) {
        // Menghapus status login di SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()  // Menghapus semua data yang ada di SharedPreferences
        editor.apply()

        // Menampilkan Toast sebagai konfirmasi
        Toast.makeText(this, "Anda telah keluar", Toast.LENGTH_SHORT).show()

        // Navigasi kembali ke halaman login
        val intent = Intent(this@DashboardAdmin, MainActivity::class.java)
        startActivity(intent)
        finish()  // Menutup DashboardAdmin agar tidak bisa kembali ke halaman ini setelah logout
    }
}

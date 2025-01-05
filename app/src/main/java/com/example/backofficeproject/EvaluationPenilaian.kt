package com.example.backofficeproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EvaluationPenilaian : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_penilaian)  // Pastikan layout ini sesuai dengan tampilan yang Anda inginkan

        val namaDokter = intent.getStringExtra("NAMA_DOKTER")
        val evaluasi = intent.getStringExtra("EVALUASI")
        val saran = intent.getStringExtra("SARAN")

        // Menghubungkan dengan ID yang sesuai dari layout
        val namaTextView: TextView = findViewById(R.id.textNo)
        val evaluasiTextView: TextView = findViewById(R.id.textTanggal)
        val saranTextView: TextView = findViewById(R.id.buttonLihat)

        // Menampilkan data pada TextViews
        namaTextView.text = namaDokter
        evaluasiTextView.text = evaluasi
        saranTextView.text = saran

        // Menangani tombol "Lihat"
        val lihatButton: Button = findViewById(R.id.buttonLihat)
        lihatButton.setOnClickListener {
            // Mengarahkan ke halaman Menu Activity
            val intent = Intent(this@EvaluationPenilaian, EvaluationActivity::class.java)
            startActivity(intent)
            finish()  // Optional: Tutup Activity EvaluationPenilaian agar tidak bisa kembali
        }
    }
}

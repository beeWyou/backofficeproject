package com.example.backofficeproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EvaluationPenilaian : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_penilaian)  // Change to appropriate layout

        val namaDokter = intent.getStringExtra("NAMA_DOKTER")
        val evaluasi = intent.getStringExtra("EVALUASI")
        val saran = intent.getStringExtra("SARAN")

        // Correct IDs based on the layout you choose for EvaluationPenilaian
        val namaTextView: TextView = findViewById(R.id.textNo)
        val evaluasiTextView: TextView = findViewById(R.id.textTanggal)
        val saranTextView: TextView = findViewById(R.id.buttonLihat)

        // Set the data to the respective TextViews
        namaTextView.text = namaDokter
        evaluasiTextView.text = evaluasi
        saranTextView.text = saran
    }
}
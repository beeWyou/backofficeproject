package com.example.backofficeproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EvaluationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluation)

        // Mendapatkan data dari Intent
        val division = intent.getStringExtra("division")
        val date = intent.getStringExtra("date")
        val ratingAbsensi = intent.getFloatExtra("ratingAbsensi", 0f)
        val ratingProfessional = intent.getFloatExtra("ratingProfessional", 0f)
        val ratingService = intent.getFloatExtra("ratingService", 0f)
        val evaluation = intent.getStringExtra("evaluation")

        // Mengisi data ke dalam TextView
        val divisionTextView = findViewById<TextView>(R.id.profileSpecialty) // Menggunakan ID yang sesuai
        val dateTextView = findViewById<TextView>(R.id.profileName) // Pastikan sesuai dengan layout yang ada
        val ratingAbsensiTextView = findViewById<TextView>(R.id.ratingAbsensi) // Sesuai dengan layout yang sudah diubah
        val ratingProfessionalTextView = findViewById<TextView>(R.id.ratingProfessional) // Pastikan ini sesuai
        val ratingServiceTextView = findViewById<TextView>(R.id.ratingService) // Pastikan ini sesuai
        val evaluationTextView = findViewById<TextView>(R.id.evaluation)

        // Mengisi data
        divisionTextView.text = division
        dateTextView.text = date
        ratingAbsensiTextView.text = "Rating Absensi: $ratingAbsensi"
        ratingProfessionalTextView.text = "Rating Professional: $ratingProfessional"
        ratingServiceTextView.text = "Rating Service: $ratingService"
        evaluationTextView.text = evaluation
    }
}

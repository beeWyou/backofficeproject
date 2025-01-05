package com.example.backofficeproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.content.Intent
import com.google.firebase.database.*

class EvaluationActivity : AppCompatActivity() {
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluation)

        // Mendapatkan ID dari Intent
        val assessmentId = intent.getStringExtra("assessmentId")

        // Referensi ke Firebase
        db = FirebaseDatabase.getInstance().reference

        // Inisialisasi TextView
        val divisionTextView = findViewById<TextView>(R.id.profileSpecialty)
        val dateTextView = findViewById<TextView>(R.id.profileName)
        val ratingAbsensiTextView = findViewById<TextView>(R.id.ratingAbsensi)
        val ratingProfessionalTextView = findViewById<TextView>(R.id.ratingProfessional)
        val ratingServiceTextView = findViewById<TextView>(R.id.ratingService)
        val evaluationTextView = findViewById<TextView>(R.id.evaluation)
        val backArrow: ImageView = findViewById(R.id.backArrow)
        backArrow.setOnClickListener {
            // Intent untuk kembali ke halaman penilaian adapter
            val intent = Intent(this, PenilaianAdapter::class.java)
            startActivity(intent) // Memulai aktivitas penilaian adapter
            finish() // Menutup activity ini sehingga tidak dapat kembali ke halaman ini
        }
        // Ambil data dari Firebase berdasarkan ID
        if (assessmentId != null) {
            // Menambahkan listener untuk mengambil data
            db.child("penilaian").child(assessmentId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        // Mengambil data dari Firebase
                        val division = snapshot.child("division").value.toString()
                        val date = snapshot.child("date").value.toString()
                        val ratingAbsensi = snapshot.child("ratingAbsensi").value.toString().toFloat()
                        val ratingProfessional = snapshot.child("ratingProfessional").value.toString().toFloat()
                        val ratingService = snapshot.child("ratingService").value.toString().toFloat()
                        val evaluation = snapshot.child("evaluation").value.toString()

                        // Set data ke TextView
                        divisionTextView.text = division
                        dateTextView.text = date
                        ratingAbsensiTextView.text = "Rating Absensi: $ratingAbsensi"
                        ratingProfessionalTextView.text = "Rating Professional: $ratingProfessional"
                        ratingServiceTextView.text = "Rating Service: $ratingService"
                        evaluationTextView.text = evaluation
                    } else {
                        // Jika data tidak ditemukan, tampilkan pesan
                        divisionTextView.text = "Data tidak ditemukan"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Menangani error jika gagal mengambil data dari Firebase
                    divisionTextView.text = "Terjadi kesalahan saat mengambil data"
                }
            })
        } else {
            // Jika assessmentId tidak ada, tampilkan pesan kesalahan
            divisionTextView.text = "Assessment ID tidak ditemukan"
        }
    }
}

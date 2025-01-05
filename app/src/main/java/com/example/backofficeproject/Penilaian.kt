package com.example.backofficeproject

data class Penilaian(
    val id: String = "",
    val nama: String = "Tidak ada nama",
    val evaluasi: String = "Belum ada evaluasi",
    val saran: String = "Belum ada saran",
    val division: String = "",
    val date: String = "",
    val ratingAbsensi: Float = 0f,
    val ratingProfessional: Float = 0f,
    val ratingService: Float = 0f,
    val evaluation: String = ""
)

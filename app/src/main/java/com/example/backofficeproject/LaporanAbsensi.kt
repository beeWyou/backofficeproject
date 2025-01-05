package com.example.backofficeproject

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.utils.ColorTemplate

class LaporanAbsensi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_absensi)

        // Menyambungkan elemen chart dan mengatur data
        val dailyChart = findViewById<LineChart>(R.id.chart_daily)
        setupBarChart(dailyChart, getDailyData(), "Laporan Harian")

        val monthlyChart = findViewById<LineChart>(R.id.chart_monthly)
        setupBarChart(monthlyChart, getMonthlyData(), "Laporan Bulanan")

        val yearlyChart = findViewById<LineChart>(R.id.chart_yearly)
        setupBarChart(yearlyChart, getYearlyData(), "Laporan Tahunan")

        // Tombol Kembali
        val backArrow: ImageView = findViewById(R.id.backarrow)
        backArrow.setOnClickListener {
            // Navigasi ke halaman menu
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
            finish() // Menutup activity ini setelah navigasi
        }
    }

    private fun setupBarChart(chart: LineChart, dataEntries: List<Entry>, label: String) {
        val dataSet = LineDataSet(dataEntries, label)
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        val data = LineData(dataSet)

        chart.data = data
        chart.description.isEnabled = false
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.animateY(1000)
        chart.invalidate() // refresh chart
    }

    private fun getDailyData(): List<Entry> {
        return listOf(
            Entry(0f, 4f), // Senin
            Entry(1f, 5f), // Selasa
            Entry(2f, 6f), // Rabu
            Entry(3f, 7f), // Kamis
            Entry(4f, 8f), // Jumat
            Entry(5f, 3f), // Sabtu
            Entry(6f, 0f)  // Minggu
        )
    }

    private fun getMonthlyData(): List<Entry> {
        return listOf(
            Entry(0f, 20f), // Januari
            Entry(1f, 18f), // Februari
            Entry(2f, 22f), // Maret
            Entry(3f, 24f)  // April (tambahkan jika perlu)
        )
    }

    private fun getYearlyData(): List<Entry> {
        return listOf(
            Entry(0f, 200f), // 2023
            Entry(1f, 210f), // 2024
            Entry(2f, 190f)  // 2025
        )
    }
}

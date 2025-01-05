package com.example.backofficeproject

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.Entry

class LaporanKinerjaUser : AppCompatActivity() {
    private var lineChartIndividu: LineChart? = null
    private var lineChartDivisi: LineChart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.laporan_kinerjauser)

        // Grafik Kinerja Individu
        lineChartIndividu = findViewById(R.id.lineChartIndividu)
        setDataForChart(
            chart = lineChartIndividu,
            dataValues = getDummyDataIndividu(),
            color = "#F8B400" // Warna kuning
        )

        // Grafik Kinerja Divisi
        lineChartDivisi = findViewById(R.id.lineChartDivisi)
        setDataForChart(
            chart = lineChartDivisi,
            dataValues = getDummyDataDivisi(),
            color = "#00B4D8" // Warna biru
        )

        // Tombol Kembali
        val backArrow: ImageView = findViewById(R.id.backarrow)
        backArrow.setOnClickListener {
            // Navigasi ke halaman menu
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
            finish() // Menutup activity ini setelah navigasi
        }
    }

    private fun setDataForChart(chart: LineChart?, dataValues: List<Entry>, color: String) {
        val lineDataSet = LineDataSet(dataValues, "Kinerja")
        lineDataSet.color = Color.parseColor(color)
        lineDataSet.setCircleColor(Color.parseColor(color))
        lineDataSet.setDrawValues(false) // Sembunyikan angka pada grafik
        lineDataSet.lineWidth = 2f
        lineDataSet.circleRadius = 4f

        val lineData = LineData(lineDataSet)
        chart?.data = lineData

        // Styling Chart
        chart?.xAxis?.apply {
            position = XAxis.XAxisPosition.BOTTOM
            granularity = 1f
        }
        chart?.axisLeft?.granularity = 1f
        chart?.axisRight?.isEnabled = false // Nonaktifkan sumbu Y kanan
        chart?.description?.isEnabled = false // Nonaktifkan deskripsi bawaan
        chart?.invalidate() // Refresh grafik
    }

    // Data dummy untuk grafik individu
    private fun getDummyDataIndividu(): List<Entry> {
        return listOf(
            Entry(2018f, 10f),
            Entry(2019f, 15f),
            Entry(2020f, 12f),
            Entry(2021f, 18f),
            Entry(2022f, 20f),
            Entry(2023f, 25f)
        )
    }

    // Data dummy untuk grafik divisi
    private fun getDummyDataDivisi(): List<Entry> {
        return listOf(
            Entry(2018f, 5f),
            Entry(2019f, 10f),
            Entry(2020f, 8f),
            Entry(2021f, 15f),
            Entry(2022f, 18f),
            Entry(2023f, 22f)
        )
    }
}

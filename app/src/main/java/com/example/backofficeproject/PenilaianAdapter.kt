package com.example.backofficeproject

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PenilaianAdapter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penilaian)

        // Data
        val penilaianData = mapOf(
            "2023" to listOf(Penilaian("12/12/23")),
            "2024" to listOf(Penilaian("12/12/24"))
        )

        val container: LinearLayout = findViewById(R.id.penilaianContainer)
        val yearDropdown: Spinner = findViewById(R.id.yearDropdown)
        val backArrow: ImageView = findViewById(R.id.backArrow)
        backArrow.setOnClickListener {
            // Intent untuk kembali ke halaman penilaian adapter
            val intent = Intent(this, Menu::class.java)
            startActivity(intent) // Memulai aktivitas penilaian adapter
            finish() // Menutup activity ini sehingga tidak dapat kembali ke halaman ini
        }
        // Dropdown
        val years = penilaianData.keys.toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        yearDropdown.adapter = adapter

        // Listener Dropdown
        yearDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedYear = years[position]
                val penilaianList = penilaianData[selectedYear] ?: emptyList()
                container.removeAllViews()

                // Populate data
                for ((index, penilaian) in penilaianList.withIndex()) {
                    val itemLayout = layoutInflater.inflate(R.layout.item_penilaian, container, false)

                    val noTextView: TextView = itemLayout.findViewById(R.id.textNo)
                    val textTanggal: TextView = itemLayout.findViewById(R.id.textTanggal)
                    val lihatButton: Button = itemLayout.findViewById(R.id.buttonLihat)

                    noTextView.text = (index + 1).toString()
                    textTanggal.text = penilaian.tanggal

                    lihatButton.setOnClickListener {
                        // Mengarahkan ke EvaluationActivity
                        val intent = Intent(this@PenilaianAdapter, EvaluationActivity::class.java)
                        intent.putExtra("TANGGAL", penilaian.tanggal)
                        startActivity(intent)
                    }

                    container.addView(itemLayout)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                container.removeAllViews()
            }
        }
    }

    data class Penilaian(val tanggal: String)
}

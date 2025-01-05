package com.example.backofficeproject

import android.content.Intent // Menambahkan import untuk Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.util.*

class PenilaianList : AppCompatActivity() {
    private lateinit var evaluationAdapter: ArrayAdapter<String>
    private var evaluations = mutableListOf<String>()
    private var filteredEvaluations = mutableListOf<String>()
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penilaian_list)

        // Initialize Firebase Realtime Database reference
        db = FirebaseDatabase.getInstance().reference.child("penilaian")

        // Initialize views
        val yearSpinner: Spinner = findViewById(R.id.spinnerYear)
        val evaluationListView: ListView = findViewById(R.id.evaluationListView)

        // Create array of years from 2010 to current year
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val years = (2010..currentYear).map { it.toString() }.toTypedArray()

        // Setup year spinner
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        yearSpinner.adapter = yearAdapter
        val buttonCreate: Button = findViewById(R.id.buttonCreate)
        buttonCreate.setOnClickListener {
            // Create an Intent to navigate to InputPenilaian activity
            val intent = Intent(this, InputPenilaian::class.java)
            startActivity(intent)
        }

        // Filter evaluations based on year selection
        yearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filterEvaluations(years[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Setup ListView adapter for evaluations
        evaluationAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredEvaluations)
        evaluationListView.adapter = evaluationAdapter

        // CRUD Operations - long click to delete or short click to edit
        evaluationListView.setOnItemClickListener { _, _, position, _ ->
            // Implement edit functionality here
            Toast.makeText(this, "Edit: ${filteredEvaluations[position]}", Toast.LENGTH_SHORT).show()
        }

        evaluationListView.setOnItemLongClickListener { _, _, position, _ ->
            // Implement delete functionality here
            filteredEvaluations.removeAt(position)
            evaluationAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
            true
        }

        // Get data from Firebase and display it
        getEvaluationsFromFirebase(years)
    }

    // Get data from Firebase and display it in the ListView
    private fun getEvaluationsFromFirebase(years: Array<String>) {
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                evaluations.clear()
                for (dataSnapshot in snapshot.children) {
                    val division = dataSnapshot.child("division").getValue(String::class.java)
                    val date = dataSnapshot.child("date").getValue(String::class.java)
                    val ratingAbsensi = dataSnapshot.child("ratingAbsensi").getValue(Float::class.java)
                    val ratingProfessional = dataSnapshot.child("ratingProfessional").getValue(Float::class.java)
                    val ratingService = dataSnapshot.child("ratingService").getValue(Float::class.java)
                    val evaluation = dataSnapshot.child("evaluation").getValue(String::class.java)

                    // Format data to be displayed in the list
                    if (division != null && date != null && evaluation != null) {
                        val evaluationText = "$division - $evaluation ($date)"
                        evaluations.add(evaluationText)
                    }
                }

                // After fetching data from Firebase, update the ListView
                // Call filterEvaluations only after data is fetched
                filterEvaluations(years[years.size - 1]) // Default to the latest year initially
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@PenilaianList, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Filter the evaluations based on the selected year
    private fun filterEvaluations(year: String) {
        filteredEvaluations.clear()
        for (evaluation in evaluations) {
            if (evaluation.contains(year)) {
                filteredEvaluations.add(evaluation)
            }
        }
        evaluationAdapter.notifyDataSetChanged()
    }
}

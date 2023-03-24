package com.example.carsmodelsgorilla

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carsmodelsgorilla.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ArrayAdapter.createFromResource(
            this, R.array.brand_spinner_entries, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.brandsSpinner.adapter = adapter
        }

        binding.brandsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(applicationContext, "You selected: $selectedItem", Toast.LENGTH_SHORT).show()
                updateModelsOptions(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    fun updateModelsOptions(position: Int) {

        val modelsEntries = when (position) {
            0 -> R.array.models_toyota_spinner_entries
            1 -> R.array.models_honda_spinner_entries
            2 -> R.array.models_bmw_spinner_entries
            else -> R.array.models_toyota_spinner_entries
        }

        fun initModelsSpinner(modelsEntries: Int) {
            ArrayAdapter.createFromResource(
                this, modelsEntries, android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.modelsSpinner.adapter = adapter
            }
        }
        initModelsSpinner(modelsEntries)
    }

}
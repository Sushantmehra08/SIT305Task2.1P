package com.example.task21p

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val units = arrayOf("Inch", "Foot", "Yard", "Mile")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        sourceUnitSpinner.adapter = adapter
        destinationUnitSpinner.adapter = adapter

        convertButton.text = getString(R.string.convert)

        convertButton.setOnClickListener {
            val inputValue = valueInput.text.toString().toDouble()
            val sourceUnit = sourceUnitSpinner.selectedItem.toString()
            val destinationUnit = destinationUnitSpinner.selectedItem.toString()

            val convertedValue = convertValue(inputValue, sourceUnit, destinationUnit)

            convertedValueText.text = convertedValue.toString()
        }

        valueInput.setImportantForAutofill(View.IMPORTANT_FOR_AUTOFILL_YES)
        valueInput.setAutofillHints(View.AUTOFILL_HINT_NUMERIC)
    }

    private fun convertValue(value: Double, sourceUnit: String, destinationUnit: String): Double {
        return when (sourceUnit) {
            "Inch" -> when (destinationUnit) {
                "Inch" -> value
                "Foot" -> value / 12
                "Yard" -> value / 36
                "Mile" -> value / 63360
                else -> 0.0
            }
            "Foot" -> when (destinationUnit) {
                "Inch" -> value * 12
                "Foot" -> value
                "Yard" -> value / 3
                "Mile" -> value / 5280
                else -> 0.0
            }
            "Yard" -> when (destinationUnit) {
                "Inch" -> value * 36
                "Foot" -> value * 3
                "Yard" -> value
                "Mile" -> value / 1760
                else -> 0.0
            }
            "Mile" -> when (destinationUnit) {
                "Inch" -> value * 63360
                "Foot" -> value * 5280
                "Yard" -> value * 1760
                "Mile" -> value
                else -> 0.0
            }
            else -> 0.0
        }
    }
}
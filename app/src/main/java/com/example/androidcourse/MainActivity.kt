package com.example.androidcourse

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var colorNumber: String
    private lateinit var priceNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        colorNumber = getString(R.string.blueFlowerColor)
        priceNumber = getString(R.string.minPriceValue)
    }


    fun onColorChange(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            if (!view.isChecked) return

            // Check which radio button was clicked
            colorNumber = when (view.getId()) {
                R.id.blueFlowerRadioButton -> getString(R.string.blueFlowerColor)
                R.id.redFlowerRadioButton -> getString(R.string.redFlowerColor)
                else -> getString(R.string.yellowFlowerColor)
            }
        }
    }

    fun onPriceChange(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            if (!view.isChecked) return

            // Check which radio button was clicked
            priceNumber = when (view.getId()) {
                R.id.minPriceRadioButton -> getString(R.string.minPriceValue)
                R.id.middlePriceRadioButton -> getString(R.string.middlePriceValue)
                else -> getString(R.string.maxPriceValue)
            }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onOkButtonClick(view: View) {
        binding.textView.text = getString(
            R.string.order_result,
            colorNumber,
            binding.personNameTextField.text,
            priceNumber
        )
    }

}
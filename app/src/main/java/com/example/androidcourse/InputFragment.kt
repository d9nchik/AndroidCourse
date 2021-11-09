package com.example.androidcourse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.androidcourse.databinding.FragmentInputBinding

class InputFragment : Fragment() {
    private lateinit var colorNumber: String
    private lateinit var priceNumber: String
    private lateinit var binding: FragmentInputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputBinding.inflate(layoutInflater, container, false)

        binding.blueFlowerRadioButton.setOnClickListener { view -> onColorChange(view) }
        binding.redFlowerRadioButton.setOnClickListener { view -> onColorChange(view) }
        binding.yellowRadioButton.setOnClickListener { view -> onColorChange(view) }

        binding.minPriceRadioButton.setOnClickListener { view -> onPriceChange(view) }
        binding.maxPriceValueRadioButton.setOnClickListener { view -> onPriceChange(view) }
        binding.middlePriceRadioButton.setOnClickListener { view -> onPriceChange(view) }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        colorNumber = getString(R.string.blueFlowerColor)
        priceNumber = getString(R.string.minPriceValue)
    }

    private fun onColorChange(view: View) {
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

    private fun onPriceChange(view: View) {
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

    fun getOrderDetails(): String? {
        if (binding.personNameTextField.text.isNotEmpty()) {
            return getString(
                R.string.order_result,
                colorNumber,
                binding.personNameTextField.text,
                priceNumber
            )
        }
        return null

    }
}
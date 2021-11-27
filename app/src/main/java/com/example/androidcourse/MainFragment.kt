package com.example.androidcourse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.androidcourse.databinding.FragmentMainBinding

class MainFragment : Fragment(), LifecycleOwner {
    private lateinit var colorNumber: String
    private lateinit var priceNumber: String
    private lateinit var binding: FragmentMainBinding
    private var orderDb: OrderDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)
        binding.recordsButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_showRecords)
        }

        binding.blueFlowerRadioButton.setOnClickListener { view -> onColorChange(view) }
        binding.redFlowerRadioButton.setOnClickListener { view -> onColorChange(view) }
        binding.yellowRadioButton.setOnClickListener { view -> onColorChange(view) }

        binding.minPriceRadioButton.setOnClickListener { view -> onPriceChange(view) }
        binding.maxPriceValueRadioButton.setOnClickListener { view -> onPriceChange(view) }
        binding.middlePriceRadioButton.setOnClickListener { view -> onPriceChange(view) }
        binding.okButton.setOnClickListener { onButtonClick() }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderDb = context?.let {
            Room.databaseBuilder(
                it,
                OrderRoomDatabase::class.java, "order_database"
            ).build()
        }?.orderDao()

        colorNumber = getString(R.string.blueFlowerColor)
        priceNumber = getString(R.string.minPriceValue)

    }

    private fun onButtonClick() {
        if (binding.personNameTextField.text.isEmpty()) {
            Toast.makeText(activity, R.string.notAllFieldsFilledPopup, Toast.LENGTH_LONG).show()
            return
        }

        Thread {
            orderDb?.insertOrder(
                Order(
                    binding.personNameTextField.text.toString(),
                    colorNumber,
                    priceNumber
                )
            )
        }.start()

        binding.textView.text = getString(
            R.string.order_result,
            colorNumber,
            binding.personNameTextField.text,
            priceNumber
        )
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
}

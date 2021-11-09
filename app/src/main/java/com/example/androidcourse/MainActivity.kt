package com.example.androidcourse

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ShowOrder.InputDataListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onButtonClick() {
        val inputFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_input_view) as InputFragment
        val orderDetails = inputFragment.getOrderDetails()
        if (orderDetails.isNullOrEmpty()) {
            Toast.makeText(this, R.string.notAllFieldsFilledPopup, Toast.LENGTH_LONG).show()
            return
        }
        val showOrderFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_output_view) as ShowOrder
        showOrderFragment.setMessage(orderDetails)
    }
}
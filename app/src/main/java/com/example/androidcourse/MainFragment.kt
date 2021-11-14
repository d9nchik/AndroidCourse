package com.example.androidcourse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import com.example.androidcourse.databinding.FragmentMainBinding

class MainFragment : Fragment(), LifecycleOwner {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)
        binding.recordsButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_showRecords)
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        childFragmentManager.setFragmentResultListener(
            "requestKey",
            this
        ) { _, _ -> onButtonClick() }

    }

    private fun onButtonClick() {
        val inputFragment =
            childFragmentManager.findFragmentById(R.id.fragment_input_view) as InputFragment
        val orderDetails = inputFragment.getOrderDetails()
        if (orderDetails.isNullOrEmpty()) {
            Toast.makeText(activity, R.string.notAllFieldsFilledPopup, Toast.LENGTH_LONG).show()
            return
        }
        val showOrderFragment =
            childFragmentManager.findFragmentById(R.id.fragment_output_view) as ShowOrder
        showOrderFragment.setMessage(orderDetails)
    }


}

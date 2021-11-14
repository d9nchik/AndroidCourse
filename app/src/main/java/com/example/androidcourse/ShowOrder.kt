package com.example.androidcourse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.androidcourse.databinding.FragmentShowOrderBinding


class ShowOrder : Fragment() {
    private lateinit var binding: FragmentShowOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowOrderBinding.inflate(layoutInflater, container, false)
        binding.okButton.setOnClickListener { v -> onOkButtonClick(v) }
        return binding.root
    }


    @Suppress("UNUSED_PARAMETER")
    fun onOkButtonClick(view: View) {
        setFragmentResult("requestKey", bundleOf())
    }

    fun setMessage(text: String) {
        binding.textView.text = text
    }
}
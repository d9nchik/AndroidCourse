package com.example.androidcourse

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidcourse.databinding.FragmentShowOrderBinding


class ShowOrder : Fragment() {
    private lateinit var binding: FragmentShowOrderBinding
    private var inputActivityCallback: InputDataListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowOrderBinding.inflate(layoutInflater, container, false)
        binding.okButton.setOnClickListener { v -> onOkButtonClick(v) }
        return binding.root
    }

    interface InputDataListener {
        fun onButtonClick()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            inputActivityCallback = context as InputDataListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                        + " must implement InputDataListener"
            )
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onOkButtonClick(view: View) {
        inputActivityCallback?.onButtonClick()
    }

    fun setMessage(text: String) {
        binding.textView.text = text
    }
}
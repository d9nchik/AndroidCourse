package com.example.androidcourse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.androidcourse.databinding.FragmentShowRecordsBinding

class ShowRecords : Fragment() {
    private var orderDb: OrderDao? = null

    private lateinit var binding: FragmentShowRecordsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentShowRecordsBinding.inflate(layoutInflater)
        val tableLayout = binding.tableLayoutRecords

        orderDb = context?.let {
            Room.databaseBuilder(
                it,
                OrderRoomDatabase::class.java, "order_database"
            ).build().orderDao()
        }
        orderDb?.getAllOrders()?.observe(viewLifecycleOwner, { products ->
            run {
                tableLayout.removeAllViews()

                for (product in products) {
                    val rowLayout = TableRow(context)
                    tableLayout.addView(rowLayout)
                    val ownerName = product.orderOwner
                    val textViewOwnerName = TextView(context)
                    textViewOwnerName.text = ownerName
                    rowLayout.addView(textViewOwnerName)

                    val colorTextView = TextView(context)
                    colorTextView.text = product.color
                    rowLayout.addView(colorTextView)
                    val priceTextView = TextView(context)
                    priceTextView.text = product.price
                    rowLayout.addView(priceTextView)
                }
            }

        })

        binding.button.setOnClickListener { _ -> removeAllOrders() }


        return binding.root
    }

    private fun removeAllOrders() {
        Thread {
            orderDb?.removeAllOrders()
        }.start()
    }
}

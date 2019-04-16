package com.demo.ecommerceapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.ecommerceapp.R
import kotlinx.android.synthetic.main.address_list_item.view.*

class AddressRVAdapter(private val items: List<String>, val context: Context) :
    RecyclerView.Adapter<AddressRVAdapter.AddressViewGolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewGolder {
        return AddressViewGolder(LayoutInflater.from(context).inflate(R.layout.address_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: AddressViewGolder, position: Int) {
//        holder.productName.text = "I phone X"
//        holder.mainLayout.setOnClickListener {
//            Toast.makeText(context,"Clicked "+position, Toast.LENGTH_LONG).show()
//        }
    }


    class AddressViewGolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAddressType = view.tvAddressType
        val tvAddress = view.tvAddress
    }
}
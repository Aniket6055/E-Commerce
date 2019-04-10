package com.demo.ecommerceapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.demo.ecommerceapp.R
import kotlinx.android.synthetic.main.best_in_mobile_item.view.*

class CommonRVAdapter(private val items: List<String>, val context: Context) :
    RecyclerView.Adapter<CommonRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.best_in_mobile_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.text = "I phone X"
        holder.mainLayout.setOnClickListener {
            Toast.makeText(context,"Clicked "+position,Toast.LENGTH_LONG).show()
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productName = view.productName
        val productImage = view.productImage
        val mainLayout = view.mainLayout
    }
}
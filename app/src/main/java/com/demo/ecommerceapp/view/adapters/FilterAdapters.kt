package com.demo.ecommerceapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.ecommerceapp.R
import kotlinx.android.synthetic.main.category_item.view.*

class FilterAdapter(val context: Context, private val items: List<String>, val type: Int) :
    RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {

    lateinit var filterClickEvents: FilterClickEvents

    override fun onBindViewHolder(holder: FilterAdapter.FilterViewHolder, position: Int) {
        holder.filterCategory.text = items[position]
        holder.filterCategory.setOnClickListener {
            if (type == 1) {
                filterClickEvents.categoryItemClicked(position)
            } else {
                filterClickEvents.filterItemClicked(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return if (type == 1) {
            FilterViewHolder(LayoutInflater.from(context).inflate(R.layout.category_item, parent, false))
        } else {
            FilterViewHolder(LayoutInflater.from(context).inflate(R.layout.filter_item, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class FilterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val filterCategory = view.filterCategory!!
    }


    interface FilterClickEvents {
        fun categoryItemClicked(postion: Int)
        fun filterItemClicked(position: Int)
    }
}
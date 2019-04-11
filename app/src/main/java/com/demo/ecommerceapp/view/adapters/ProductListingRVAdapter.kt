package com.demo.ecommerceapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.demo.ecommerceapp.R
import com.demo.ecommerceapp.view.customviews.TagView
import kotlinx.android.synthetic.main.product_list_item.view.*


class ProductListingRVAdapter(private val items: List<String>, val context: Context) :
    RecyclerView.Adapter<ProductListingRVAdapter.ViewHolder>() {
    lateinit var lablaeAdapter: TagView.Adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.listingProductName.text = "I phone X"
        holder.listingProductPrice.text = "₹ 94000"
        holder.listingProductOffer.text = "10% off"
        holder.listingProductRating.text = "4.5"
        holder.listingProductRatingCOunt.text = "(10 reviews)"
        holder.listProductImage.setImageResource(R.drawable.phone)
        var listOfSpecs = mutableListOf<String>()
        listOfSpecs.addAll(
            listOf(
                "AAA",
                "BBB",
                "CCC",
                "DDD",
                "AAA",
                "BBB",
                "CCC",
                "DDD",
                "AAA",
                "BBB",
                "CCC",
                "DDD"
            )
        )
        lablaeAdapter = TagView.Adapter(listOfSpecs)
        lablaeAdapter.datas=listOfSpecs
        holder.listingProductSpecs.setAdapter(lablaeAdapter)
        holder.listingItemMainLayout.setOnClickListener {
            Toast.makeText(context, "Clicked " + position, Toast.LENGTH_LONG).show()
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listingProductName = view.listingProductName
        val listProductImage = view.listProductImage
        val listingProductPrice = view.listingProductPrice
        val listingProductOffer = view.listingProductOffer
        val listingProductRating = view.listingProductRating
        val listingProductRatingCOunt = view.listingProductRatingCount
        val listingItemMainLayout = view.listingItemMainLayout
        val listingProductSpecs = view.listingProductspecs
    }
}
package com.demo.ecommerceapp.view.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import com.demo.ecommerceapp.R
import android.widget.Toast
import android.widget.ImageView
import android.widget.LinearLayout


class BannerPagerAdapter(var mContext: Context, images: IntArray) : PagerAdapter() {

    var images: IntArray? = images

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun getCount(): Int {
        return images!!.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.banner_item, container, false)
        val imageView = itemView.findViewById(R.id.bannerImage) as ImageView
        imageView.setImageResource(images!![position])

        container.addView(itemView)

        //listening to image click
        imageView.setOnClickListener {
            Toast.makeText(
                mContext,
                "you clicked image " + (position + 1),
                Toast.LENGTH_LONG
            ).show()
        }
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}
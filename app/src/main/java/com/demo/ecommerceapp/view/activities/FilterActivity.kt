package com.demo.ecommerceapp.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.ecommerceapp.R
import com.demo.ecommerceapp.view.adapters.FilterAdapter
import kotlinx.android.synthetic.main.activity_filter.*


class FilterActivity : AppCompatActivity(), FilterAdapter.FilterClickEvents {

    companion object {
        fun getIntent(mContext: Context): Intent {
            return Intent(mContext, FilterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val layoutManager = LinearLayoutManager(this)
        categoryRv.layoutManager = layoutManager
        categoryRv.addItemDecoration(DividerItemDecoration(categoryRv.context, DividerItemDecoration.VERTICAL))
        val adapter = FilterAdapter(this, listOf("Brand", "Price", "Offers", "Type", "Other"), 1)
        adapter.filterClickEvents = this
        categoryRv.adapter = adapter
        categoryRv.isNestedScrollingEnabled = true

        val layoutManager1 = LinearLayoutManager(this)
        filterRv.layoutManager = layoutManager1
        filterRv.addItemDecoration(DividerItemDecoration(categoryRv.context, DividerItemDecoration.VERTICAL))
        filterRv.adapter = FilterAdapter(
            this,
            listOf(
                "AAA",
                "BBB",
                "CCC",
                "DDD",
                "EEE",
                "AAA",
                "BBB",
                "CCC",
                "DDD",
                "EEE",
                "AAA",
                "BBB",
                "CCC",
                "DDD",
                "EEE",
                "AAA",
                "BBB",
                "CCC",
                "DDD",
                "EEE"
            ),
            2
        )
        filterRv.isNestedScrollingEnabled = true
    }


    override fun categoryItemClicked(postion: Int) {

    }

    override fun filterItemClicked(position: Int) {

    }
}

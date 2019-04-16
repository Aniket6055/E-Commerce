package com.demo.ecommerceapp.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.ecommerceapp.R
import com.demo.ecommerceapp.view.adapters.AddressRVAdapter
import kotlinx.android.synthetic.main.activity_address.*

class AddressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)


        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = false
        rvAddressList.layoutManager = layoutManager
        rvAddressList.adapter = AddressRVAdapter(listOf(""), this)
        rvAddressList.isNestedScrollingEnabled = true
    }
}

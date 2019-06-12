package com.demo.ecommerceapp.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.ecommerceapp.R

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
    }

    companion object {
        fun getIntent(mContext: Context): Intent {
            return Intent(mContext, CartActivity::class.java)
        }
    }
}

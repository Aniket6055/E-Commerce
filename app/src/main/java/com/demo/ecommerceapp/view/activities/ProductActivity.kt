package com.demo.ecommerceapp.view.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.ecommerceapp.R
import com.demo.ecommerceapp.view.adapters.CommonRVAdapter
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.content_product.*


class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        toolbar.contentInsetStartWithNavigation = 0
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar_layout.title = "Title"
                    isShow = true
                } else if (isShow) {
                    toolbar_layout.title =
                        " "//carefull there should a space between double quote otherwise it wont work
                    isShow = false
                }
            }
        })

        setSimilarProducts()
        loadWebView()
    }

    private fun loadWebView() {
        specsWebView.settings.javaScriptEnabled = true
        specsWebView.webViewClient = WebViewController()
        specsWebView.loadUrl("http://www.google.com")
    }

    private fun setSimilarProducts() {
        val rvTrendMobile = findViewById<RecyclerView>(com.demo.ecommerceapp.R.id.rvSimilarProducts)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        layoutManager.reverseLayout = false
        rvTrendMobile.layoutManager = layoutManager
        rvTrendMobile.adapter = CommonRVAdapter(getProducts(), this)
        rvTrendMobile.isNestedScrollingEnabled = true

    }

    private fun getProducts(): List<String> {
        return listOf("AA", "BB", "CC", "DD", "EE")
    }


    inner class WebViewController : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)

        val menuItem = menu.findItem(R.id.action_cart)

        val actionView = MenuItemCompat.getActionView(menuItem)
        textCartItemCount = actionView.findViewById(R.id.cart_badge) as TextView

        setupBadge()

        actionView.setOnClickListener { onOptionsItemSelected(menuItem) }
        return true
    }

    var textCartItemCount: TextView? = null
    var mCartItemCount = 10

    private fun setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount === 0) {
                if (textCartItemCount!!.visibility !== View.GONE) {
                    textCartItemCount!!.visibility = View.GONE
                }
            } else {
                textCartItemCount!!.text = Math.min(mCartItemCount, 99).toString()
                if (textCartItemCount!!.visibility !== View.VISIBLE) {
                    textCartItemCount!!.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_cart -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
}

package com.demo.ecommerceapp.view.activities

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.demo.ecommerceapp.view.adapters.ExpandableListAdapter
import com.demo.ecommerceapp.view.fragments.HomeFragment
import android.widget.TextView
import com.demo.ecommerceapp.R
import androidx.core.view.MenuItemCompat
import com.demo.ecommerceapp.view.fragments.ListingFragment


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var expandableListAdapter: ExpandableListAdapter? = null
    var headerList: List<String> = ArrayList()
    var childList: HashMap<String, List<String>> = HashMap()
    var textCartItemCount: TextView? = null
    var mCartItemCount = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        populateCategories()
        populateExpandableList()
        addHomeFragment()
    }

    private fun addHomeFragment() {
        var homeFragment = HomeFragment()
        var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainContent, homeFragment)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    fun populateCategories(): Unit {
        headerList = listOf("A", "B", "C", "D")
        childList.put("A", headerList)
        childList.put("B", headerList)
        childList.put("C", headerList)
        childList.put("D", headerList)
    }


    fun populateExpandableList() {
        expandableListAdapter = ExpandableListAdapter(this, headerList, childList)
        expandableListView.setAdapter(expandableListAdapter)

        expandableListView.setOnChildClickListener(ExpandableListView.OnChildClickListener { parent, view, groupPosition, childPosition, id ->
            Boolean
            Toast.makeText(this, childList[headerList[groupPosition]]!![childPosition], Toast.LENGTH_LONG)
                .show()
            return@OnChildClickListener false
        })
    }

}

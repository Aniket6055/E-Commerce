package com.demo.ecommerceapp.view.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ExpandableListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.FragmentTransaction
import com.demo.ecommerceapp.R
import com.demo.ecommerceapp.view.adapters.ExpandableListAdapter
import com.demo.ecommerceapp.view.fragments.HomeFragment
import com.demo.ecommerceapp.view.fragments.ListingFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*


class HomeActivity : AppCompatActivity(), HomeFragment.ClickEvents {

    var expandableListAdapter: ExpandableListAdapter? = null
    var headerList: List<String> = ArrayList()
    var childList: HashMap<String, List<String>> = HashMap()
    var textCartItemCount: TextView? = null
    var mCartItemCount = 10
    lateinit var filterMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        populateCategories()
        populateExpandableList()
        addHomeFragment()
    }

    private fun addHomeFragment() {
        var homeFragment = HomeFragment()
        homeFragment.clickEvents = this
        var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainContent, homeFragment)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if (supportFragmentManager.backStackEntryCount > 0) {
                filterMenuItem.isVisible = false
                supportFragmentManager.popBackStack()

            } else
                super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)

        val menuItem = menu.findItem(R.id.action_cart)
        filterMenuItem = menu.findItem(R.id.action_filter)

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
         when (item.itemId) {
            R.id.action_cart -> startActivity(CartActivity.getIntent(this))
            R.id.action_filter -> startActivity(FilterActivity.getIntent(this))

             else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun populateCategories(): Unit {
        headerList = listOf("A", "B", "C", "D")
        childList.put("A", headerList)
        childList.put("B", headerList)
        childList.put("C", headerList)
        childList.put("D", headerList)
    }


    private fun populateExpandableList() {
        expandableListAdapter = ExpandableListAdapter(this, headerList, childList)
        expandableListView.setAdapter(expandableListAdapter)

        expandableListView.setOnChildClickListener(ExpandableListView.OnChildClickListener { parent, view, groupPosition, childPosition, id ->
            Boolean
            Toast.makeText(this, childList[headerList[groupPosition]]!![childPosition], Toast.LENGTH_LONG)
                .show()
            return@OnChildClickListener false
        })
    }

    override fun onClick(type: Int) {
        var listingFragment = ListingFragment()
        supportFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(R.id.mainContent, listingFragment)
            .addToBackStack(null)
            .commit()
        filterMenuItem.isVisible = true
    }
}

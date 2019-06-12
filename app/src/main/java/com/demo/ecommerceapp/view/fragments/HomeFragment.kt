package com.demo.ecommerceapp.view.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.demo.ecommerceapp.R
import com.demo.ecommerceapp.view.adapters.BannerPagerAdapter
import com.demo.ecommerceapp.view.adapters.CommonRVAdapter
import com.demo.ecommerceapp.view.customviews.textview.LatoSemiboldTextView
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class HomeFragment : Fragment() {

    var images = intArrayOf(R.drawable.banner_test, R.mipmap.ic_launcher, R.drawable.banner_test, R.mipmap.ic_launcher)
    lateinit var bannerAdapter: BannerPagerAdapter
    lateinit var vpBanner: ViewPager
    lateinit var dotsIndicator: DotsIndicator
    var NUM_PAGES: Int = 0
    lateinit var clickEvents: ClickEvents

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        vpBanner = view.findViewById(R.id.vpBanner) as ViewPager
        dotsIndicator = view.findViewById(R.id.dotIndicator) as DotsIndicator
        NUM_PAGES = images.size + 1
        bannerAdapter = BannerPagerAdapter(activity!!.baseContext, images)
        vpBanner.adapter = bannerAdapter
        dotsIndicator.setViewPager(vpBanner)
        val trendingMobileSeeMore = view.findViewById<LatoSemiboldTextView>(R.id.trendingMobileSeeMore)
        trendingMobileSeeMore.setOnClickListener {
            clickEvents.onClick(1)
        }
        val trendingElectronicsSeeMore = view.findViewById<LatoSemiboldTextView>(R.id.trendingElectronicsSeeMore)
        trendingElectronicsSeeMore.setOnClickListener {
            clickEvents.onClick(2)
        }
        val bestOffersSeeMore = view.findViewById<LatoSemiboldTextView>(R.id.bestOffersSeeMore)
        bestOffersSeeMore.setOnClickListener {
            clickEvents.onClick(3)
        }
        setUpTrendingPhones(view)
        setupTrendingElectronics(view)
        setupBestOffers(view)
        startAutoScrollForViewPager()
        return view
    }

    private fun setupBestOffers(view: View) {

        val rvBestOffers = view.findViewById<RecyclerView>(R.id.rvBestOffers)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true)
        layoutManager.reverseLayout = false
        rvBestOffers.layoutManager = layoutManager
        rvBestOffers.adapter = CommonRVAdapter(getProducts(), activity!!.baseContext)
        rvBestOffers.isNestedScrollingEnabled = true
    }

    private fun setupTrendingElectronics(view: View) {
        val rvTrendElectronics = view.findViewById<RecyclerView>(R.id.rvTrendElectronics)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true)
        layoutManager.reverseLayout = false
        rvTrendElectronics.layoutManager = layoutManager
        rvTrendElectronics.adapter = CommonRVAdapter(getProducts(), activity!!.baseContext)
        rvTrendElectronics.isNestedScrollingEnabled = true

    }

    private fun setUpTrendingPhones(view: View) {
        val rvTrendMobile = view.findViewById<RecyclerView>(R.id.rvTrendMobile)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true)
        layoutManager.reverseLayout = false
        rvTrendMobile.layoutManager = layoutManager
        rvTrendMobile.adapter = CommonRVAdapter(getProducts(), activity!!.baseContext)
        rvTrendMobile.isNestedScrollingEnabled = true

    }

    private fun getProducts(): List<String> {
        return listOf("AA", "BB", "CC", "DD", "EE")
    }

    var currentPage = 0
    var timer: Timer? = null
    val DELAY_MS: Long = 500//delay in milliseconds before task is to be executed
    val PERIOD_MS: Long = 3000 // time in milliseconds between successive task executions.
    private fun startAutoScrollForViewPager() {

        /*After setting the adapter use the timer */
        val handler = Handler()
        val Update = Runnable {
            if (currentPage === NUM_PAGES - 1) {
                currentPage = 0
            }
            vpBanner.setCurrentItem(currentPage++, true)
        }

        timer = Timer() // This will create a new Thread
        timer!!.schedule(object : TimerTask() { // task to be scheduled
            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)
    }


    interface ClickEvents {
        fun onClick(type: Int)
    }
}

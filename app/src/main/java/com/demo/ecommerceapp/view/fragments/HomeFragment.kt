package com.demo.ecommerceapp.view.fragments

import android.content.Context
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
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
//    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    var images = intArrayOf(R.drawable.banner_test, R.mipmap.ic_launcher, R.drawable.banner_test, R.mipmap.ic_launcher)
    lateinit var bannerAdapter: BannerPagerAdapter
    lateinit var vpBanner: ViewPager
    lateinit var dotsIndicator: DotsIndicator
    var NUM_PAGES: Int = 0

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


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
//        listener = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

package com.example.absentexample.main

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.viewpager.widget.ViewPager
import com.example.absentexample.R
import com.example.absentexample.main.adapter.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.view.WindowManager
import android.os.Build
import com.androidnetworking.AndroidNetworking


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transparantStatusBar()
        setupPager()
    }


    fun setupPager(){
        var pagerAdapter = MainPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        view_pager_main.adapter = pagerAdapter
        blueText()

        txt_log_absent.setOnClickListener {
            view_pager_main.setCurrentItem(0, true)
            blueText()
        }

        txt_profile.setOnClickListener {
            view_pager_main.setCurrentItem(1, true)
            blueText()
        }

        view_pager_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                blueText()
            }

        })
    }

    fun blueText(){
        if(view_pager_main.currentItem == 0){
            txt_profile.setTextColor(Color.parseColor("#C2C2C2"))
            txt_log_absent.setTextColor(Color.parseColor("#0057ED"))
        }else {
            txt_log_absent.setTextColor(Color.parseColor("#C2C2C2"))
            txt_profile.setTextColor(Color.parseColor("#0057ED"))
        }
    }

    fun transparantStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }



}

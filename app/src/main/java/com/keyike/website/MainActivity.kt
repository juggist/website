package com.keyike.website

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gyf.immersionbar.ImmersionBar
import com.keyike.website.pay.Pay
import com.keyike.website.pay.PayType
import com.keyike.website.ui.ui.fragment.*
import com.keyike.website.webview.MyWebView
import com.tencent.smtt.sdk.WebView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    lateinit var fragments: MutableList<Fragment>

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun create() {
        mainBottom.enableAnimation(false)
        mainBottom.enableItemShiftingMode(false)
        mainBottom.enableShiftingMode(false)
        fragments = mutableListOf(
            BabyFragment(),
            ChannelFragment(),
            GameFragment(),
            SchoolFragment(),
            ShopFragment()
        )
        mainViewpager.apply {
            isUserInputEnabled = false
            offscreenPageLimit = 5
            adapter =
                object : FragmentStateAdapter(supportFragmentManager, this@MainActivity.lifecycle) {
                    override fun createFragment(position: Int): Fragment {
                        return fragments[position]
                    }

                    override fun getItemCount() = 5
                }
            setCurrentItem(0, false)
        }
        mainBottom.apply {
            enableAnimation(false)
            enableShiftingMode(false)
            enableItemShiftingMode(false)

            setTextSize(12f)
            setOnNavigationItemSelectedListener {

                when (it.itemId) {
                    R.id.menu_map -> {

                        mainViewpager.setCurrentItem(0, false)
                    }
                    R.id.menu_discover -> {

                        mainViewpager.setCurrentItem(1, false)
                    }
                    R.id.menu_car -> {

                        mainViewpager.setCurrentItem(2, false)
                    }
                    R.id.menu_shop -> {

                        mainViewpager.setCurrentItem(3, false)
                    }
                    R.id.menu_mine -> {

                        mainViewpager.setCurrentItem(4, false)
                    }
                }
                true
            }

            currentItem = 0

            //去除长按吐司
            bottomNavigationItemViews.forEach {
                it.setOnLongClickListener { true }
            }
        }
//        myWebView.loadUrl("http://www.txtx365.com")
    }


    override fun onBackPressed() {
//        if (myWebView.canGoBack()) {
//            myWebView.goBack()
//        } else {
//            super.onBackPressed()
//        }
    }
}

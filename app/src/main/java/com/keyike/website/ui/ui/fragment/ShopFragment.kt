package com.keyike.website.ui.ui.fragment

import com.keyike.website.BaseFragment
import com.keyike.website.R
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopFragment : BaseFragment() {
    override fun getLayoutId(): Int  = R.layout.fragment_shop
    override fun initData() {
        myWeb.loadUrl("https://weidian.com/?userid=1232872316&wfr=wx_wxh5&ifr=shopdetail&share_relation=7d84be9e861a92a1_167715218_1")
    }
}
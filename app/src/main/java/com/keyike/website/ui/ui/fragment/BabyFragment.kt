package com.keyike.website.ui.ui.fragment

import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginLeft
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keyike.website.BaseFragment
import com.keyike.website.R
import com.keyike.website.telNums
import com.keyike.website.telTypes
import kotlinx.android.synthetic.main.component_connect_us.view.*
import kotlinx.android.synthetic.main.fragment_baby.*

class BabyFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_baby
    override fun initData() {
        rv.layoutManager = GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
        rv.adapter =
            ChannelAdapter(listOf("1", "2", "3", "4", "5", "6", "7"), activity!!, TYPE_BABY)
        item1Change(0)
        item2Change(0)
        initItem1Listener()
        initItem2Listener()
    }

    fun initItem1Listener() {
        tv_startBaby.setOnClickListener {
            channelClick()
            item1Change(0)
        }
        tv_uBaby.setOnClickListener {
            channelClick()
            item1Change(1)
        }
        tv_jiandangModel.setOnClickListener {
            priceClick()
            item1Change(2)
        }
        tv_qianyueModel.setOnClickListener {
            priceClick()
            item1Change(3)
        }
    }
    fun channelClick(){
        v_left.visibility = View.VISIBLE
        v_right.visibility = View.VISIBLE
        img_content.visibility = View.GONE
        rv.layoutManager = GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
        rv.adapter =
            ChannelAdapter(listOf("1", "2", "3", "4", "5", "6", "7"), activity!!, TYPE_BABY)

    }
    fun priceClick(){
        v_left.visibility = View.GONE
        v_right.visibility = View.GONE
        img_content.visibility = View.VISIBLE
        rv.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rv.adapter = CommonAdapter(activity!!)
    }
    fun item1Change(index:Int){
        tv_startBaby.setTextColor(resources.getColor(R.color.colorWhite))
        tv_uBaby.setTextColor(resources.getColor(R.color.colorWhite))
        tv_jiandangModel.setTextColor(resources.getColor(R.color.colorWhite))
        tv_qianyueModel.setTextColor(resources.getColor(R.color.colorWhite))
        if(index == 0){
            tv_startBaby.setTextColor(resources.getColor(R.color.colorItemClick))
        }else if(index == 1){
            tv_uBaby.setTextColor(resources.getColor(R.color.colorItemClick))
        }else if(index == 2){
            tv_jiandangModel.setTextColor(resources.getColor(R.color.colorItemClick))
            img_content.setImageDrawable(resources.getDrawable(R.mipmap.tit_jd))
        }else if(index == 3){
            tv_qianyueModel.setTextColor(resources.getColor(R.color.colorItemClick))
            img_content.setImageDrawable(resources.getDrawable(R.mipmap.tit_qy))
        }
    }
    fun initItem2Listener() {
        tv_level1.setOnClickListener {
            item2Change(0)
        }
        tv_level2.setOnClickListener {
            item2Change(1)
        }
        tv_level3.setOnClickListener {
            item2Change(2)
        }
    }
    fun item2Change(index:Int){
        tv_level1.setTextColor(resources.getColor(R.color.colorBlack))
        tv_level2.setTextColor(resources.getColor(R.color.colorBlack))
        tv_level3.setTextColor(resources.getColor(R.color.colorBlack))

        if(index == 0){
            tv_level1.setTextColor(resources.getColor(R.color.colorItemClick))
        }else if(index == 1){
            tv_level2.setTextColor(resources.getColor(R.color.colorItemClick))
        }else if(index == 2) {
            tv_level3.setTextColor(resources.getColor(R.color.colorItemClick))
        }
    }
}
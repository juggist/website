package com.keyike.website.ui.ui.fragment

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keyike.website.BaseFragment
import com.keyike.website.R

import kotlinx.android.synthetic.main.fragment_channel.*
import kotlinx.android.synthetic.main.fragment_channel.rv
import kotlinx.android.synthetic.main.fragment_channel.tv_level1
import kotlinx.android.synthetic.main.fragment_channel.tv_level2
import kotlinx.android.synthetic.main.fragment_channel.tv_level3

class ChannelFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_channel
    override fun initData() {
        rv.layoutManager = LinearLayoutManager(activity).apply {
            this.orientation = LinearLayoutManager.VERTICAL
        }
        rv.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.top = if (parent.getChildPosition(view) == 0) 0 else 76
            }
        })
        rv.adapter = PriceAdapter(listOf("1", "2", "3", "4", "5", "6", "7"), activity!!,
            TYPE_CHANNEL)
        initListener()
        itemChange(0)
    }
    fun initListener(){
        tv_level1.setOnClickListener {
            itemChange(0)
        }
        tv_level2.setOnClickListener {
            itemChange(1)
        }
        tv_level3.setOnClickListener {
            itemChange(2)
        }
        tv_level4.setOnClickListener {
            itemChange(3)
        }
        tv_level5.setOnClickListener {
            itemChange(4)
        }
    }
    fun itemChange(index : Int){
        tv_level1.setTextColor(resources.getColor(R.color.colorBlack))
        tv_level2.setTextColor(resources.getColor(R.color.colorBlack))
        tv_level3.setTextColor(resources.getColor(R.color.colorBlack))
        tv_level4.setTextColor(resources.getColor(R.color.colorBlack))
        tv_level5.setTextColor(resources.getColor(R.color.colorBlack))

        if(index == 0){
            tv_level1.setTextColor(resources.getColor(R.color.colorItemClick))
        }else if(index == 1){
            tv_level2.setTextColor(resources.getColor(R.color.colorItemClick))
        }else if(index == 2) {
            tv_level3.setTextColor(resources.getColor(R.color.colorItemClick))
        }else if(index == 3) {
            tv_level4.setTextColor(resources.getColor(R.color.colorItemClick))
        }else if(index == 4) {
            tv_level5.setTextColor(resources.getColor(R.color.colorItemClick))
        }
    }
}
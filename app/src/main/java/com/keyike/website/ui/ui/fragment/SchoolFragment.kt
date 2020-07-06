package com.keyike.website.ui.ui.fragment

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keyike.website.BaseFragment
import com.keyike.website.R
import kotlinx.android.synthetic.main.fragment_channel.*
import kotlinx.android.synthetic.main.fragment_school.*
import kotlinx.android.synthetic.main.fragment_school.rv
import kotlinx.android.synthetic.main.fragment_school.tv_level1
import kotlinx.android.synthetic.main.fragment_school.tv_level2
import kotlinx.android.synthetic.main.fragment_school.tv_level3
import kotlinx.android.synthetic.main.fragment_school.tv_level4

class SchoolFragment : BaseFragment() {
    override fun getLayoutId(): Int  = R.layout.fragment_school
    override fun initData() {
        rv.layoutManager = GridLayoutManager(activity,3, RecyclerView.VERTICAL,false)
        rv.adapter = ChannelAdapter(listOf("1","2","3","4","5","6","7"),activity!!, TYPE_SCHOOL)
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
    }
    fun itemChange(index : Int){
        tv_level1.setTextColor(resources.getColor(R.color.colorBlack))
        tv_level2.setTextColor(resources.getColor(R.color.colorBlack))
        tv_level3.setTextColor(resources.getColor(R.color.colorBlack))
        tv_level4.setTextColor(resources.getColor(R.color.colorBlack))
        if(index == 0){
            tv_level1.setTextColor(resources.getColor(R.color.colorItemClick))
        }else if(index == 1){
            tv_level2.setTextColor(resources.getColor(R.color.colorItemClick))
        }else if(index == 2) {
            tv_level3.setTextColor(resources.getColor(R.color.colorItemClick))
        }else if(index == 3) {
            tv_level4.setTextColor(resources.getColor(R.color.colorItemClick))
        }
    }
}
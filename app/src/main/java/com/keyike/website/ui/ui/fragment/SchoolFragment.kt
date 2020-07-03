package com.keyike.website.ui.ui.fragment

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keyike.website.BaseFragment
import com.keyike.website.R
import kotlinx.android.synthetic.main.fragment_school.*

class SchoolFragment : BaseFragment() {
    override fun getLayoutId(): Int  = R.layout.fragment_school
    override fun initData() {
        rv.layoutManager = GridLayoutManager(activity,3, RecyclerView.VERTICAL,false)
        rv.adapter = ChannelAdapter(listOf("1","2","3","4","5","6","7"),activity!!, TYPE_SCHOOL)
    }
}
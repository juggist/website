package com.keyike.website.ui.ui.fragment

import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keyike.website.BaseFragment
import com.keyike.website.R
import com.keyike.website.telNums
import com.keyike.website.telTypes
import kotlinx.android.synthetic.main.component_connect_us.view.*
import kotlinx.android.synthetic.main.fragment_baby.*

class BabyFragment : BaseFragment() {

    override fun getLayoutId(): Int  = R.layout.fragment_baby
    override fun initData() {
        rv.layoutManager = GridLayoutManager(activity,3,RecyclerView.VERTICAL,false)
        rv.adapter = ChannelAdapter(listOf("1","2","3","4","5","6","7"),activity!!, TYPE_BABY)

//        loadConncetUs()

    }

    private fun loadConncetUs(){
        val textStr1 = telTypes[0] + telNums[0]
        val sp1 = SpannableString(textStr1)
        val spanClick1 = MyClickableSpan(0)
        sp1.setSpan(spanClick1,6,textStr1.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        rv.tv_tel_1?.apply{
            movementMethod = LinkMovementMethod.getInstance()
            text = textStr1
        }

        rv.tv_tel_2.text = telTypes[1] + telNums[1]
        rv.tv_tel_3.text = telTypes[2] + telNums[3]
        rv.tv_tel_4.text = telTypes[3] + telNums[3]
        rv.tv_tel_5.text = telTypes[4] + telNums[4]
        rv.tv_tel_6.text = telTypes[5] + telNums[5]
        rv.tv_tel_7.text = telTypes[6] + telNums[6]
    }
}
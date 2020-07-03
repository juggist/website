package com.keyike.website

import android.os.Bundle
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
val telTypes = listOf<String>("咨询电话","免费试镜","报名咨询","经纪合作","机构合作","品牌合作","商务合作")
val telNums = listOf<String>("021-6587","免费试镜","报名咨询","经纪合作","机构合作","品牌合作","商务合作")
abstract class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(getLayoutId(),container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }
    abstract fun getLayoutId():Int
    abstract fun initData()


    class MyClickableSpan(val type:Int):ClickableSpan(){
        override fun updateDrawState(ds: TextPaint) {
            ds.isUnderlineText = true  //默认下划线，改为false无下划线
        }

        override fun onClick(p0: View) {
            when(type){

            }
        }

    }
}
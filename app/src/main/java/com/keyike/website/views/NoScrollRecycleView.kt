package com.keyike.website.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView


class NoScrollRecycleView(context: Context):RecyclerView(context) {
    constructor(context: Context?, attrs: AttributeSet?):this(context!!)
    constructor(context: Context?, attrs: AttributeSet?,defStyle: Int):this(context,attrs)

    //通过重新dispatchTouchEvent方法来禁止滑动
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return if (ev.action == MotionEvent.ACTION_MOVE) {
            true //禁止Gridview进行滑动
        } else super.dispatchTouchEvent(ev)
    }
}
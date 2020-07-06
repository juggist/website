package com.keyike.website.ui.ui.fragment

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keyike.website.BaseFragment
import com.keyike.website.R
import kotlinx.android.synthetic.main.fragment_game.*


class GameFragment : BaseFragment() {
    override fun getLayoutId(): Int  = R.layout.fragment_game
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
        rv.adapter = PriceAdapter(listOf("1", "2", "3", "4", "5", "6", "7"), activity!!, TYPE_GAME)
    }


}
package com.keyike.website

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_test

    override fun create() {
        btn_add.setOnClickListener {
            setOrder("1","lufeisong","wx")
        }
        btn_add2.setOnClickListener {
            setOrder("2","lufeisong","wx")
        }
        btn_add3.setOnClickListener {
            setOrder("3","lufeisong","wx")
        }
        btn_post.setOnClickListener {

        }
        btn_delete.setOnClickListener {
            removeOrder("1","lufeisong","wx")
        }
        btn_delete2.setOnClickListener {
            removeOrder("2","lufeisong","wx")
        }
        btn_delete3.setOnClickListener {
            removeOrder("3","lufeisong","wx")
        }
        btn_get.setOnClickListener {
            Log.d("测试","get = ${getOrders().toString()}")
        }
    }
}

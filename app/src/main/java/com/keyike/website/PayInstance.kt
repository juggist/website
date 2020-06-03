package com.keyike.website

import android.app.Activity
import com.keyike.website.pay.Pay
import com.keyike.website.pay.PayFactory
import com.keyike.website.pay.PayType

/**
 * @author juggist
 * @date 2020/5/12 17:35
 */
object PayInstance {
    private var pay: Pay? = null
    fun initPay(activity: Activity, payType: PayType, result: Pay.Result?, exception: Pay.Exception?) {
        pay = PayFactory(activity, payType, result, exception)
    }

    fun payStart(){
        pay?.invokePayClient() ?: throw Exception("请实例化支付方式")
    }

    fun paySuccess(){
        pay?.result?.paySuccess()
    }

    fun payFail(msg:String){
        pay?.result?.payFail(msg)
    }

    fun payCancel(){
        pay?.result?.payCancel()
    }
}
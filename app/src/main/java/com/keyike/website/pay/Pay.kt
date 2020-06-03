package com.keyike.website.pay

import android.app.Activity

/**
 * @author juggist
 * @date 2020/5/12 16:23
 */
interface Pay {
    val activity: Activity
    var result: Result?
    var exception: Exception?

    /*
    统一处理,创建订单
     */
    fun createOrder() {

    }

    /*
    唤起支付客户端
     */
    fun invokePayClient()
    /*
    添加监听事件
     */
    fun addCallback(result: Result,exception: Exception){
        addResult(result)
        addException(exception)
    }
    fun addResult(result: Result){
        this.result = result
    }
    fun addException(exception: Exception){
        this.exception = exception
    }
    /*
    移除监听事件
     */
    fun removeCallback() {
        removeResult()
        removeException()
    }

    fun removeResult() {
        result = null
    }

    fun removeException() {
        exception = null
    }

    /*
    支付结果
     */
    interface Result {
        fun paySuccess()
        fun payFail(msg: String)
        fun payCancel()
    }

    /*
    异常处理
     */
    interface Exception {
        fun unInstallClient()
        fun unSupportPay()
    }
}
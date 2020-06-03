package com.keyike.website.pay

import android.app.Activity
import android.os.Handler
import android.os.Message
import android.util.Log
import com.alipay.sdk.app.PayTask
import com.keyike.website.ALI_PAY_LOCAL_ERROR

/**
 * @author juggist
 * @date 2020/5/12 16:31
 */
class AliPay(
    override val activity: Activity,
    override var result: Pay.Result?,
    override var exception: Pay.Exception?
) : Pay {
    private val handler_what_result = 1
    private val myHandler = object :Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                handler_what_result -> {
                    val payResult = AliPayResult(msg.obj as Map<String, String>)
                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    Log.i("Pay","payResult = ${payResult.toString()}")
                    val resultStatus = payResult.resultStatus
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    when(resultStatus){
                        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                        "9000" -> result?.paySuccess()
                        //用户中途取消
                        "6001" -> result?.payCancel()
                        else -> result?.payFail(payResult.result)
                    }
                }
            }
        }
    }

    override fun invokePayClient() {
        val runnable = Runnable {
            val payTask = PayTask(activity)
            val result: Map<String, String> = payTask.payV2("", true)
            val message = Message().apply {
                what = handler_what_result
                obj = result
            }
            myHandler.sendMessage(message)
        }
        val thread = Thread(runnable)
        thread.start()
    }

}
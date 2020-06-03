package com.keyike.website.pay

import android.app.Activity

/**
 * @author juggist
 * @date 2020/5/12 16:28
 * 单例工厂
 */
object PayFactory {
    //重载运算符invoke
    operator fun invoke(activity:Activity,payType: PayType,result:Pay.Result?,exception: Pay.Exception?): Pay {
        return when (payType) {
            PayType.WEXIN -> WeChatPay(activity,result,exception)
            PayType.ALIPAY -> AliPay(activity,result,exception)
        }
    }
}
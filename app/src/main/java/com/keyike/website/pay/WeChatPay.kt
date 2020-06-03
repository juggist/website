package com.keyike.website.pay

import android.app.Activity
import com.keyike.website.MyApplication
import com.keyike.website.WEXIN_APPID
import com.keyike.website.wechat.WeChatBase
import com.tencent.mm.opensdk.constants.Build
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI

/**
 * @author juggist
 * @date 2020/5/12 16:30
 */
class WeChatPay(
    override val activity: Activity,
    override var result: Pay.Result?,
    override var exception: Pay.Exception?
) : Pay, WeChatBase {
    override val wexinApi: IWXAPI = MyApplication.wexinApi
    override fun invokePayClient() {
        wexinApi.registerApp(WEXIN_APPID)
        if (!clientInstall()) {
            exception?.unInstallClient()
            return
        }
        if (!supportPay()) {
            exception?.unSupportPay()
            return
        }
        val request = PayReq().apply {
            appId = "data.getAppId()"
            partnerId = "data.getPartnerId()"
            prepayId = "data.getPrepayId()"
            packageValue = "Sign=WXPay"
            nonceStr = "data.getNonceStr()"
            timeStamp = "data.timestamp"
            sign = "data.getPaySign()"
        }
        wexinApi.sendReq(request)
    }



    /*
    是否支持支付功能
     */
    private fun supportPay(): Boolean {
        return wexinApi.wxAppSupportAPI >= Build.PAY_SUPPORTED_SDK_INT
    }

}
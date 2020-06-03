package com.keyike.website.wxapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.keyike.website.MyApplication
import com.keyike.website.PayInstance
import com.keyike.website.WEXIN_PAY_LOCAL_ERROR
import com.keyike.website.pay.WeChatPay
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler

/**
 * @author juggist
 * @date 2020/5/12 16:34
 */
class WXPayEntryActivity : AppCompatActivity(), IWXAPIEventHandler {
    private lateinit var wexinApi: IWXAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wexinApi = MyApplication.wexinApi
        wexinApi.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        wexinApi.handleIntent(intent, this)
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    override fun onResp(baseResp: BaseResp?) {
        Log.i("Pay", "baseResp 02=  ${baseResp?.type} ; ${baseResp?.errCode} ; ${baseResp?.errStr}")
        when (baseResp?.type) {
            ConstantsAPI.COMMAND_PAY_BY_WX -> when (baseResp.errCode) {
                BaseResp.ErrCode.ERR_OK -> PayInstance.paySuccess()
                BaseResp.ErrCode.ERR_USER_CANCEL -> PayInstance.payCancel()
                else -> PayInstance.payFail(baseResp.errStr ?: WEXIN_PAY_LOCAL_ERROR)
            }

        }
        finish()
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    override fun onReq(baseReq: BaseReq?) {
        Log.i("Pay", "baseReq 02=  ${baseReq?.type} ")
    }

}
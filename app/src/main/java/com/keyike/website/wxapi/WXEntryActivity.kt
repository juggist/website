package com.keyike.website.wxapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.keyike.website.MyApplication
import com.keyike.website.WeChatInstance
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler

/**
 * @author juggist
 * @date 2020/5/15 09:18
 */
class WXEntryActivity :AppCompatActivity(), IWXAPIEventHandler{
    private lateinit var wexinApi: IWXAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wexinApi = MyApplication.wexinApi
        wexinApi.handleIntent(intent, this)
    }

    override fun onResp(baseResp: BaseResp?) {
        Log.i("Pay","baseResp 01 =  ${baseResp?.type} ; ${baseResp?.errCode} ; ${baseResp?.errStr}")
        baseResp?.run {
            when(type){
                ConstantsAPI.COMMAND_SENDAUTH -> {
                    when(errCode){
                        BaseResp.ErrCode.ERR_OK ->{
                            val loginResp = baseResp as SendAuth.Resp
                            WeChatInstance.notifyLoginSuccess(loginResp.code)
                            Log.i("Login","ERR_OK : ${loginResp.toString()}")
                        }
//                        BaseResp.ErrCode.ERR_AUTH_DENIED -> WeChatInstance.notifyLoginFail()
//                        BaseResp.ErrCode.ERR_USER_CANCEL -> WeChatInstance.notifyLoginFail()
                        else -> {WeChatInstance.notifyLoginFail()}
                    }
                }
                ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX -> {

                }
                else ->{}
            }
        }
        finish()
    }

    override fun onReq(baseReq: BaseReq?) {
        Log.i("Pay","baseReq 01=  ${baseReq?.type} ")
    }
}
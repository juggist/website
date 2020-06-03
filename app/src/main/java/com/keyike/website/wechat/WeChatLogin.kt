package com.keyike.website.wechat

import com.keyike.website.MyApplication
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPI

/**
 * @author juggist
 * @date 2020/5/15 11:49
 */
class WeChatLogin : WeChatBase{
    override val wexinApi: IWXAPI = MyApplication.wexinApi
    fun login() {
        if(!clientInstall()){
            return
        }
        val req = SendAuth.Req().apply {
            scope = "snsapi_userinfo"
            state = "keyike"
        }
        wexinApi.sendReq(req)
    }
}
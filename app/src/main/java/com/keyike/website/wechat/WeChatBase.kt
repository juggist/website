package com.keyike.website.wechat

import com.keyike.website.MyApplication
import com.tencent.mm.opensdk.openapi.IWXAPI

/**
 * @author juggist
 * @date 2020/5/15 12:11
 */
interface WeChatBase {
    val wexinApi: IWXAPI
    /*
   是否安装了微信客户端
    */
    fun clientInstall(): Boolean {
        return wexinApi.isWXAppInstalled
    }
}
package com.keyike.website.wechat

import com.keyike.website.MyApplication
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.modelmsg.WXTextObject
import com.tencent.mm.opensdk.openapi.IWXAPI

/**
 * @author juggist
 * @date 2020/5/15 11:49
 */
class WeChatShare : WeChatBase{
    override val wexinApi: IWXAPI = MyApplication.wexinApi
    fun shareText(){
        val textObj = WXTextObject()
        textObj.text = "标题"
        val msg = WXMediaMessage().apply {
            mediaObject = textObj
            description = "内容"
        }
        val req = SendMessageToWX.Req().apply {
            transaction = "测试"
            message = msg
            scene = WXSceneSession//聊天
        }
        wexinApi.sendReq(req)
    }
}
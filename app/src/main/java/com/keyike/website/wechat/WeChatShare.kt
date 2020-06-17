package com.keyike.website.wechat

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import com.keyike.website.MyApplication
import com.keyike.website.R
import com.tencent.mm.opensdk.modelmsg.*
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession
import com.tencent.mm.opensdk.openapi.IWXAPI
import java.io.ByteArrayOutputStream

/**
 * @author juggist
 * @date 2020/5/15 11:49
 */
class WeChatShare : WeChatBase {
    override val wexinApi: IWXAPI = MyApplication.wexinApi
    fun shareText() {
        val textObj = WXTextObject()
        textObj.text = "text"
        val msg = WXMediaMessage().apply {
            mediaObject = textObj
            description = "description"
            title = "title"
            messageExt = "messageExt"
        }
        val req = SendMessageToWX.Req().apply {
            transaction = "transaction"
            message = msg
            scene = WXSceneSession//聊天
        }
        wexinApi.sendReq(req)
    }

    fun shareImg(context: Context) {
        val bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.logo)
        val obj = WXImageObject(bitmap)
        val msg = WXMediaMessage()
        msg.mediaObject = obj
        msg.title = "title"
        msg.description = "description"
        msg.messageExt = "messageExt"
        val thumbBitmp = Bitmap.createScaledBitmap(bitmap, 150, 150, true)
        bitmap.recycle()
        msg.thumbData = bmpToByteArray(thumbBitmp, true)
        val req = SendMessageToWX.Req().apply {
            transaction = "transaction"
            message = msg
            scene = WXSceneSession
        }
        wexinApi.sendReq(req)
    }
    fun shareWebPage(context: Context){
        val webPage = WXWebpageObject()
        webPage.webpageUrl = "www.baidu.com"
        val msg = WXMediaMessage(webPage)
        msg.title = "title"
        msg.description = "description"
        val bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.logo)
        msg.thumbData = bmpToByteArray(bitmap, true)
        val req = SendMessageToWX.Req().apply {
            transaction = "transaction"
            message = msg
            scene = WXSceneSession
        }
        wexinApi.sendReq(req)
    }
    fun bmpToByteArray(bmp: Bitmap, needRecycle: Boolean): ByteArray? {
        val output = ByteArrayOutputStream()
        bmp.compress(CompressFormat.PNG, 100, output)
        if (needRecycle) {
            bmp.recycle()
        }
        val result = output.toByteArray()
        try {
            output.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

}
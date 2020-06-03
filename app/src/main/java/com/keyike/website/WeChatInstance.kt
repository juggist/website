package com.keyike.website

import com.keyike.website.wechat.WeChatLogin
import com.keyike.website.wechat.WeChatShare

/**
 * @author juggist
 * @date 2020/5/15 12:18
 */
object WeChatInstance {
    private val weChatLogin : WeChatLogin = WeChatLogin()
    private val weChatShare : WeChatShare = WeChatShare()

    fun login(){
        weChatLogin.login()
    }
    fun shareText(){
        weChatShare.shareText()
    }
}
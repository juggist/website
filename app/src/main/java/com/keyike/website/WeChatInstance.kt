package com.keyike.website

import android.content.Context
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
    fun shareImg(context: Context){
        weChatShare.shareImg(context)
    }
    fun shareWebPage(context: Context){
        weChatShare.shareWebPage(context)
    }
    private var loginCallbacks:MutableList<WeChatLogin.Callback> = listOf<WeChatLogin.Callback>().toMutableList()

    fun addLoginCallback(callback:WeChatLogin.Callback){
        if(!loginCallbacks.contains(callback)){
            loginCallbacks.add(callback)
        }
    }
    fun removeLoginCallback(callback:WeChatLogin.Callback){
        if(loginCallbacks.contains(callback)){
            loginCallbacks.remove(callback)
        }
    }
    fun notifyLoginSuccess(code:String){
        loginCallbacks.forEach {
            it.loginSuccess(code)
        }
    }
    fun notifyLoginFail(){
        loginCallbacks.forEach {
            it.loginFail()
        }
    }
}
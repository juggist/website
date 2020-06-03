package com.keyike.website

import android.app.Application
import android.util.Log
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory

/**
 * @author juggist
 * @date 2020/5/12 16:45
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        wexinApi = WXAPIFactory.createWXAPI(applicationContext, WEXIN_APPID, false)
    }
    companion object{
        lateinit var wexinApi: IWXAPI
    }
}
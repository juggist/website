package com.keyike.website

import android.app.Application
import android.util.Log
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import org.xutils.x

/**
 * @author juggist
 * @date 2020/5/12 16:45
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        x.Ext.init(this);
        wexinApi = WXAPIFactory.createWXAPI(applicationContext, WEXIN_APPID, false)
    }
    companion object{
        lateinit var wexinApi: IWXAPI
        var token :String? = null
        var user_id :String? = null
    }
}
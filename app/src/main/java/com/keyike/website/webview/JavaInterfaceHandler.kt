package com.keyike.website.webview

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface

/**
 * @author juggist
 * @date 2020/5/13 09:16
 */
class JavaInterfaceHandler(val context: Context) {
    @JavascriptInterface
    fun jsCallNative(result: String){
        Log.d("JavaInterfaceHandler",result)

    }
}
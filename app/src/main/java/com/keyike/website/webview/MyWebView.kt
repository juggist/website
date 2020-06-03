package com.keyike.website.webview

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.keyike.website.JAVA_INTERFACE_NAME
import com.keyike.website.R
import com.tencent.smtt.export.external.interfaces.*
import com.tencent.smtt.sdk.*


/**
 * @author juggist
 * @date 2020/5/12 21:48
 */
class MyWebView : WebView,WebViewLifeCycle {
    private lateinit var mContext:Context
    private var mWebViewLoadListener:WebViewLoadListener? = null
    private val mWebViewClient = object :WebViewClient(){
        override fun onPageStarted(webView: WebView?, url: String?, bitmap: Bitmap?) {
            super.onPageStarted(webView, url, bitmap)
            mWebViewLoadListener?.onPageStarted(webView,url,bitmap)
        }

        override fun onPageFinished(webView: WebView?, url: String?) {
            super.onPageFinished(webView, url)
            mWebViewLoadListener?.onPageFinished(webView,url)
        }

        override fun onReceivedError(webView: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
            super.onReceivedError(webView, errorCode, description, failingUrl)
            mWebViewLoadListener?.onReceivedError(errorCode)
        }


        override fun onReceivedHttpError(
            webView: WebView?,
            request: WebResourceRequest?,
            response: WebResourceResponse?
        ) {
            super.onReceivedHttpError(webView, request, response)
            mWebViewLoadListener?.onReceivedError(response?.statusCode ?: 0)
        }

        override fun onReceivedSslError(webView: WebView?, handler: SslErrorHandler?, error: SslError?) {
//            super.onReceivedSslError(p0, p1, p2)
            handler?.proceed()

        }
    }
    private val myWebChromeClient = object : WebChromeClient(){
        override fun onReceivedTitle(webView: WebView?, title: String?) {
            super.onReceivedTitle(webView, title)
            mWebViewLoadListener?.onReceivedTitle(webView,title)
        }

        override fun onJsAlert(webView: WebView?, url: String?, message: String?, jsResult: JsResult?): Boolean {
            AlertDialog.Builder(mContext).apply {
                setTitle("Alert")
                setMessage(message)
                setPositiveButton(
                    android.R.string.ok
                ) { _, _ -> jsResult?.confirm() }
                setCancelable(false)
            }.create().show()
            return true
        }

        override fun onShowFileChooser(
            p0: WebView?,
            p1: ValueCallback<Array<Uri>>?,
            p2: FileChooserParams?
        ): Boolean {
            return super.onShowFileChooser(p0, p1, p2)
        }
    }
    constructor(p0: Context?, p1: Boolean) : super(p0, p1){
        loadParams(p0)
    }
    constructor(p0: Context?) : super(p0){
        loadParams(p0)
    }
    constructor(p0: Context?, p1: AttributeSet?) : super(p0, p1){
        loadParams(p0)
    }
    constructor(p0: Context?, p1: AttributeSet?, p2: Int) : super(p0, p1, p2){
        loadParams(p0)
    }
    constructor(p0: Context?, p1: AttributeSet?, p2: Int, p3: Boolean) : super(p0, p1, p2, p3){
        loadParams(p0)
    }
    constructor(
        p0: Context?,
        p1: AttributeSet?,
        p2: Int,
        p3: MutableMap<String, Any>?,
        p4: Boolean
    ) : super(p0, p1, p2, p3, p4){
        loadParams(p0)
    }

    override fun onPaused() {
        onPause()
    }

    override fun onResumed() {
        onResume()
    }
    private fun loadParams(context :Context?){
        mContext = context ?: getContext()
        setBackgroundResource(android.R.color.transparent)
        background.alpha = 0

        val settings = settings
        settings.setAllowFileAccessFromFileURLs(false)
        settings.setAllowUniversalAccessFromFileURLs(false)
        settings.javaScriptEnabled = true
        settings.builtInZoomControls = true
        settings.displayZoomControls = false
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
        settings.savePassword = false
        settings.setGeolocationEnabled(true)
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.domStorageEnabled = true
        settings.databaseEnabled  = true

        webChromeClient = myWebChromeClient
        webViewClient = mWebViewClient

        addJavascriptInterface(JavaInterfaceHandler(mContext),JAVA_INTERFACE_NAME)

        settings.userAgentString = getUserAgent(settings)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        if (null != x5WebViewExtension) {
            x5WebViewExtension.setScrollBarFadingEnabled(false)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setWebContentsDebuggingEnabled(true)
        }

        if (context is FragmentActivity) {
            context.lifecycle.addObserver(this)
        }
    }

    private fun getUserAgent(webSetting:WebSettings):String{
        val userAgent = webSetting.userAgentString
        val sb:StringBuffer = StringBuffer()
        for(c in userAgent){
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", c.toInt()))
            }else{
                sb.append(c)
            }
        }
        return sb.toString()
    }
    /*
    native向js发起请求
    methodParams:请求参数，一般为 方法Name+入参
     */
    fun nativeCallJs(methodParams: String){
        val urlParams = "javascript:$methodParams"
        Log.d("MyWebView","nativeCallJs urlParams : $urlParams")
        loadUrl(urlParams)
    }
    // 解决 某些room 定制化严重导致的 webview内核线程无法自动回收的问题
    fun recycle(){
        removeAllViews()
        tag = null
        clearHistory()
        destroy()
    }
    //注册监听事件
    fun setWebViewLoadListener(webViewLoadListener:WebViewLoadListener){
        mWebViewLoadListener = webViewLoadListener
    }

    /*
    webview状态监听
     */
    interface WebViewLoadListener{
        fun onPageStarted(
            view: WebView?,
            url: String?,
            favicon: Bitmap?
        )

        fun onPageFinished(
            view: WebView?,
            url: String?
        )

        fun onProgressChanged(view: WebView?, progress: Int)

        fun onReceivedTitle(
            view: WebView?,
            mtTitle: String?
        )

        fun onReceivedError(code: Int)
    }
}
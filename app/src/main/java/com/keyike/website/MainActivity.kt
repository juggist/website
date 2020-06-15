package com.keyike.website

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keyike.website.pay.Pay
import com.keyike.website.pay.PayType
import com.keyike.website.webview.MyWebView
import com.tencent.smtt.sdk.WebView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    val TAG = "webSite"
    var startTime = 0L
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        initListener()
//        myWebView.loadUrl("http://www.txtx365.com")
//
//        PayInstance.initPay(this, PayType.WEXIN, object : Pay.Result {
//            override fun paySuccess() {
//                Log.d("Pay", "succsss")
//            }
//
//            override fun payFail(msg: String) {
//                Log.d("Pay", "fail : $msg")
//            }
//
//            override fun payCancel() {
//                Log.d("Pay", "cancel")
//            }
//        }, object : Pay.Exception {
//            override fun unInstallClient() {
//                Log.d("Pay", "unInstallClient")
//            }
//
//            override fun unSupportPay() {
//                Log.d("Pay", "unSupportPay")
//            }
//        })
//    }

    override fun getLayoutId(): Int  = R.layout.activity_main

    override fun create() {
        myWebView.loadUrl("http://www.txtx365.com")
    }

//    private fun initListener() {
//        btn.setOnClickListener { myWebView.nativeCallJs("nativeCallJs('android call js')") }
//        btn2.setOnClickListener { WeChatInstance.login() }
//        btn3.setOnClickListener { WeChatInstance.shareText() }
//        btn.visibility = View.GONE
//        btn2.visibility = View.GONE
//        btn3.visibility = View.GONE
//        myWebView.setWebViewLoadListener(object : MyWebView.WebViewLoadListener {
//            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//                Log.d(TAG, "onPageStarted ")
//                startTime = System.currentTimeMillis()
//            }
//
//            override fun onPageFinished(view: WebView?, url: String?) {
//                Log.d(TAG, "onPageFinished ; 时间差" + (System.currentTimeMillis() - startTime))
//            }
//
//            override fun onProgressChanged(view: WebView?, progress: Int) {
//                Log.d(TAG, "onProgressChanged")
//            }
//
//            override fun onReceivedTitle(view: WebView?, mtTitle: String?) {
//                Log.d(TAG, "onReceivedTitle")
//            }
//
//            override fun onReceivedError(code: Int) {
//                Log.d(TAG, "onReceivedError")
//            }
//        })
//    }

    override fun onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}

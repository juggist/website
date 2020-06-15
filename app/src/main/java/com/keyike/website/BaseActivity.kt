package com.keyike.website

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar
import com.keyike.website.bean.WrapData
import com.keyike.website.http.BaseCallBack
import org.xutils.HttpManager
import org.xutils.common.Callback
import org.xutils.http.RequestParams
import org.xutils.x

abstract class BaseActivity : AppCompatActivity() {
    val http : HttpManager = x.http()
    val BASE_URL = "http://api.txtx365.com/index.php?/api/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(getLayoutId())
        ImmersionBar.with(this).statusBarColor(R.color.colorWhite).statusBarDarkFont(true).init()
        create()
    }
    abstract fun getLayoutId():Int
    abstract fun create()
    protected inline fun<reified T> post(url:String,params:HashMap<String,String>,callback:DataCallback<T>){
       Thread(Runnable {
           val params = convertRequestParams(url,null,params)
           http.post(params,object : Callback.CommonCallback<String>{
               override fun onFinished() {

               }

               override fun onSuccess(result: String?) {
                   Log.i("keyikeApp","onSuccess : $result")
                   val bean = Gson().fromJson(result,T::class.java)
                   callback.dataSuccess(bean)
               }

               override fun onCancelled(cex: Callback.CancelledException?) {
               }

               override fun onError(ex: Throwable?, isOnCallback: Boolean) {
                   Log.i("keyikeApp","onError : ${ex.toString()}")
                   callback.dataFail(ex.toString())
               }

           })
       }).start()
    }

    protected inline fun<reified T> get(url:String,params:HashMap<String,String>,callback:DataCallback<T>){
        Thread(Runnable {
            val params = convertRequestParams(url,params,null)
            http.get(params,object : Callback.CommonCallback<String>{
                override fun onFinished() {

                }

                override fun onSuccess(result: String?) {
                    Log.i("keyikeApp","onSuccess : $result")
                    val bean = Gson().fromJson(result,T::class.java)
                    callback.dataSuccess(bean)
                }

                override fun onCancelled(cex: Callback.CancelledException?) {
                }

                override fun onError(ex: Throwable?, isOnCallback: Boolean) {
                    Log.i("keyikeApp","onError : ${ex.toString()}")
                    callback.dataFail(ex.toString())
                }

            })
        }).start()
    }
    protected fun setUserSpData(userId:String){
        val sp = this.getSharedPreferences("keyike", Context.MODE_PRIVATE)
        sp.edit().putString("userId",userId).commit()
    }
    protected fun getUserSpData():String?{
        val sp = this.getSharedPreferences("keyike", Context.MODE_PRIVATE)
        val token = sp.getString("userId","")
        return token
    }
    protected fun clearUserSpData(){
        val sp = this.getSharedPreferences("keyike", Context.MODE_PRIVATE)
        sp.edit().clear()
    }
    public fun convertRequestParams(url: String, queryParams: Map<String, String>?, formParams: Map<String, String>?): RequestParams {
        val params = RequestParams(url)
        params.connectTimeout = 30000
        var keys: Set<*>
        var e: Iterator<*>
        var key: Map.Entry<String?, String?>
        if (queryParams != null) {
            keys = queryParams.entries
            e = keys.iterator()
            while (e.hasNext()) {
                key = e.next()
                params.addQueryStringParameter(key.key, key.value)
            }
        }
        if (formParams != null) {
            keys = formParams.entries
            e = keys.iterator()
            while (e.hasNext()) {
                key = e.next() as Map.Entry<String?, String?>
                params.addBodyParameter(key.key, key.value)
            }
        }
        return params
    }
}
interface DataCallback<T>{
    fun dataSuccess(result: T)
    fun dataFail(msg:String)
}
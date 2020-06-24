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
import com.keyike.website.bean.OrderBeans
import com.keyike.website.bean.WrapData
import com.keyike.website.http.BaseCallBack
import org.xutils.HttpManager
import org.xutils.common.Callback
import org.xutils.http.RequestParams
import org.xutils.x
const val SP_NAME = "keyike"
const val SP_ORDERS = "orders"
const val SP_USER = "userId"
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
           Log.d("keyikeApp","url = $url ; params  = ${params.toJSONString()}")
           http.post(params,object : Callback.CommonCallback<String>{
               override fun onFinished() {

               }

               override fun onSuccess(result: String?) {

                   val bean = Gson().fromJson(result,T::class.java)
                   Log.i("keyikeApp","onSuccess : ${Gson().toJson(bean)}")
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
                    val bean = Gson().fromJson(result,T::class.java)
                    Log.i("keyikeApp","onSuccess : ${Gson().toJson(bean)}")
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
        val sp = this.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        sp.edit().putString(SP_USER,userId).commit()
    }
    protected fun getUserSpData():String?{
        val sp = this.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        val token = sp.getString(SP_USER,"")
        return token
    }
    protected fun clearUserSpData(){
        val sp = this.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        sp.edit().putString(SP_USER,"").commit()
    }
    protected fun setOrder(order:String,userId:String,payType:String){
        val sp = this.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        val ordersStr = sp.getString(SP_ORDERS, "")
        var orders = Gson().fromJson(ordersStr, OrderBeans::class.java)
        var exist = false
        orders?.data?.let {
            it.forEach { item ->
                if(item.order == order && item.payType == payType &&item.userId == userId){
                    exist = true
                    return@let
                }
            }
        }
        if(!exist){
            if(orders == null){
                orders = OrderBeans(mutableListOf())
            }
            if(orders.data == null){
                orders.data = mutableListOf()
            }
            orders.data!!.add(OrderBeans.OrderBean(order,userId,payType))
            sp.edit().putString(SP_ORDERS,Gson().toJson(orders)).commit()
        }
    }
    protected fun removeOrder(order:String,userId:String,payType:String){
        val sp = this.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        val ordersStr = sp.getString(SP_ORDERS, "")
        var orders = Gson().fromJson(ordersStr, OrderBeans::class.java)
        var index = -1
        orders?.data?.let {
            for (_index in it.indices){
                if(it[_index].order == order && it[_index].payType == payType &&it[_index].userId == userId){
                    index = _index
                    return@let
                }
            }
        }
        if(index > -1){
            orders.data!!.removeAt(index)
            sp.edit().putString(SP_ORDERS,Gson().toJson(orders)).commit()
        }
    }
    protected fun getOrders():OrderBeans?{
        val sp = this.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        val ordersStr = sp.getString(SP_ORDERS, "")
        return Gson().fromJson(ordersStr, OrderBeans::class.java)
    }
    fun convertRequestParams(url: String, queryParams: Map<String, String>?, formParams: Map<String, String>?): RequestParams {
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
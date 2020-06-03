package com.keyike.website.http

import org.xutils.HttpManager
import org.xutils.common.Callback
import org.xutils.http.RequestParams
import org.xutils.x

/**
 * @author juggist
 * @date 2020/5/13 13:22
 */
class HttpClientAPi : BaseApi {
    val http : HttpManager = x.http()
    override fun <T> post(url: String, queryParams: Map<String, String>, formParams: Map<String, String>, callback: BaseCallBack<T>) {
        val params = convertRequestParams(url,queryParams,formParams)
        http.post(params,object : Callback.CommonCallback<T>{
            override fun onFinished() {
                TODO("Not yet implemented")
            }

            override fun onSuccess(result: T?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(cex: Callback.CancelledException?) {
                TODO("Not yet implemented")
            }

            override fun onError(ex: Throwable?, isOnCallback: Boolean) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun <T> get(url: String, queryParams: Map<String, String>, formParams: Map<String, String>, callback: BaseCallBack<T>) {
        val params = convertRequestParams(url,queryParams,formParams)
        http.get(params,object : Callback.CommonCallback<T>{
            override fun onFinished() {
                TODO("Not yet implemented")
            }

            override fun onSuccess(result: T?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(cex: Callback.CancelledException?) {
                TODO("Not yet implemented")
            }

            override fun onError(ex: Throwable?, isOnCallback: Boolean) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun convertRequestParams(url: String, queryParams: Map<String, String>, formParams: Map<String, String>): RequestParams{
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
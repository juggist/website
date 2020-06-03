package com.keyike.website.http

/**
 * @author juggist
 * @date 2020/5/13 13:18
 */
interface BaseApi {
    fun <T> post(url:String,queryParams:Map<String,String>,formParams :Map<String,String>,callback:BaseCallBack<T>)

    fun <T> get(url:String,queryParams:Map<String,String>,formParams :Map<String,String>,callback:BaseCallBack<T>)
}
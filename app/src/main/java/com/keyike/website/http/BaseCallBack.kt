package com.keyike.website.http

/**
 * @author juggist
 * @date 2020/5/13 13:19
 */
interface BaseCallBack<T> {
    fun success(t:T)
    fun fail(msg:String)
}
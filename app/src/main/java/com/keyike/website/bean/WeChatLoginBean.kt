package com.keyike.website.bean

import java.io.Serializable

data class WeChatLoginBean(val openid : String,val nickname : String,val sex : Int,val province : String,val city : String,val country : String,val headimgurl : String,val privilege : MutableList<String>,val unionid : String):Serializable
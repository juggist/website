package com.keyike.website.bean

data class WeChatTokenBean(val access_token:String,val expires_in:Int,val refresh_token:String,val openid : String,val scope:String)
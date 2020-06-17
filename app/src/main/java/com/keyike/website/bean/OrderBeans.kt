package com.keyike.website.bean

data class OrderBeans(var data:MutableList<OrderBean>?){
    data class OrderBean(val order:String,val userId:String,val payType:String){
        override fun toString(): String {
            return "OrderBean(order='$order', userId='$userId', payType='$payType')"
        }
    }

}

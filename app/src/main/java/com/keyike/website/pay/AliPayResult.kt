package com.keyike.website.pay

/**
 * @author juggist
 * @date 2020/5/12 20:13
 */
class AliPayResult {
    var resultStatus: String = ""
    var result: String = ""
    var memo: String = ""

    constructor(resultMap: Map<String, String>) {
        resultMap.keys.forEach {
            when (it) {
                "resultStatus" -> resultStatus = resultMap["resultStatus"] ?: ""
                "result" -> result = resultMap["result"] ?: ""
                "memo" -> memo = resultMap["memo"] ?: ""
            }
        }
    }

    override fun toString(): String {
        return "AliPayResult(resultStatus='$resultStatus', result='$result', memo='$memo')"
    }

}
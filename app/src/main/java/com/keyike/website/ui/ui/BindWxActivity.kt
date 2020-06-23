package com.keyike.website.ui.ui

import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import com.keyike.website.*
import com.keyike.website.bean.LoginBean
import com.keyike.website.bean.WeChatLoginBean
import kotlinx.android.synthetic.main.activity_bind_wx.*
import kotlinx.android.synthetic.main.activity_bind_wx.btn_register
import kotlinx.android.synthetic.main.activity_bind_wx.et_phone
import kotlinx.android.synthetic.main.activity_login.*

class BindWxActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_bind_wx

    override fun create() {
        val data: WeChatLoginBean = intent.extras.get("data") as WeChatLoginBean
        btn_register.setOnClickListener {
            if (!Utils.isMobileNO(et_phone.text.toString())) {
                Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val androidId = Settings.System.getString(
                contentResolver,
                Settings.System.ANDROID_ID
            )
            post(
                "${BASE_URL}weixinRegister",
                hashMapOf(
                    "token" to MyApplication.token!!,
                    "device_code" to androidId,
                    "mobile" to et_phone.text.toString(),
                    "open_id" to data.openid,
                    "nickname" to data.nickname,
                    "sex" to data.sex.toString(),
                    "province" to data.province,
                    "city" to data.city,
                    "country" to data.country,
                    "headimgurl" to data.headimgurl,
                    "privilege" to data.privilege.toString(),
                    "unionid" to data.unionid
                ),
                object : DataCallback<LoginBean> {
                    override fun dataSuccess(result: LoginBean) {
                        setUserSpData(result.user_id)
                        MyApplication.user_id = result.user_id
                        startActivity(Intent(this@BindWxActivity, MainActivity::class.java))
                        this@BindWxActivity.finish()
                        Toast.makeText(this@BindWxActivity,"绑定微信成功",Toast.LENGTH_LONG).show()
                    }

                    override fun dataFail(msg: String) {
                        Toast.makeText(this@BindWxActivity,"绑定微信失败，请重试",Toast.LENGTH_LONG).show()
                    }

                })
        }
    }
}
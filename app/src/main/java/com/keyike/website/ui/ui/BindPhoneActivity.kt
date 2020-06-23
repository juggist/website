package com.keyike.website.ui.ui

import android.content.Intent
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.keyike.website.*
import com.keyike.website.bean.LoginBean
import com.keyike.website.bean.SmsCodeBean
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class BindPhoneActivity:BaseActivity() {
    var timer: Timer? = null
    var max_time = 0
    lateinit var androidId :String
    override fun getLayoutId(): Int = R.layout.activity_bind_phone

    override fun create() {
        btn_agree.isSelected = false
        initListener()
        androidId = Settings.System.getString(
            contentResolver,
            Settings.System.ANDROID_ID
        )
    }
    private fun initListener() {
        btn_agree.setOnClickListener {
            btn_agree.isSelected = !btn_agree.isSelected
        }
        btn_register.setOnClickListener {
            if (TextUtils.isEmpty(et_phone.text) ||  TextUtils.isEmpty(et_code.text)) {
                Toast.makeText(this, "请填写完整信息", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (!Utils.isMobileNO(et_phone.text.toString())) {
                Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (!btn_agree.isSelected) {
                Toast.makeText(this, "请勾选协议", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            post(
                "${BASE_URL}verifyMobile",
                hashMapOf<String, String>(
                    "token" to MyApplication.token!!,
                    "mobile" to et_phone.text.toString(),
                    "verify_code" to et_code.text.toString(),
                    "device_code" to androidId,
                    "user_id" to MyApplication.user_id!!
                ),
                object : DataCallback<LoginBean> {
                    override fun dataSuccess(result: LoginBean) {
                        if (result.code == 1) {
                            Toast.makeText(this@BindPhoneActivity, "修改手机号成功", Toast.LENGTH_LONG).show()
                            this@BindPhoneActivity.finish()
                        } else {
                            Toast.makeText(this@BindPhoneActivity, "修改手机号失败:${result.message}", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun dataFail(msg: String) {
                        Toast.makeText(this@BindPhoneActivity, "修改手机号失败:$msg", Toast.LENGTH_LONG).show()
                    }

                })
        }
        tv_timer.setOnClickListener {
            if (!Utils.isMobileNO(et_phone.text.toString())) {
                Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (max_time > 0) {
                return@setOnClickListener
            }
            max_time = 60
            timer = Timer()
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    max_time--
                    if (max_time > 0) {
                        tv_timer.text = max_time.toString()
                    } else {
                        tv_timer.text = "发送验证码"
                        timer?.cancel()
                        max_time = 0
                    }
                }
            }, 1000, 1000)
            post(
                "${BASE_URL}registerVerify",
                hashMapOf<String, String>(
                    "token" to MyApplication.token!!,
                    "mobile" to et_phone.text.toString(),
                    "device_code" to androidId
                ),
                object :
                    DataCallback<SmsCodeBean> {
                    override fun dataSuccess(result: SmsCodeBean) {
                        if (result.code == 1) {
                            Toast.makeText(this@BindPhoneActivity, "发送验证码成功", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            Toast.makeText(this@BindPhoneActivity, "发送验证码失败:${result.message}", Toast.LENGTH_LONG)
                                .show()
                            tv_timer.text = "发送验证码"
                            timer?.cancel()
                            max_time = 0
                        }

                    }

                    override fun dataFail(msg: String) {
                        Toast.makeText(this@BindPhoneActivity, "发送验证码失败:$msg", Toast.LENGTH_LONG).show()
                        tv_timer.text = "发送验证码"
                        timer?.cancel()
                        max_time = 0
                    }

                })
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
        timer = null
    }
}
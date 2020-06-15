package com.keyike.website.ui.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.gyf.immersionbar.ImmersionBar
import com.keyike.website.*
import com.keyike.website.bean.*
import com.keyike.website.wechat.WeChatLogin
import kotlinx.android.synthetic.main.activity_login.*
import pub.devrel.easypermissions.EasyPermissions
import java.util.*


/**
 * @author juggist
 * @date 2020/5/21 22:19
 */
class LoginActivity : BaseActivity(),WeChatLogin.Callback {
    var timer: Timer? = null
    var max_time = 0
    var loginType = 0
    override fun getLayoutId(): Int = R.layout.activity_login

    override fun create() {
        WeChatInstance.addLoginCallback(this)
        initListener()
    }

    private fun initListener() {
        tv_login_psw.setOnClickListener {
            tv_login_psw.setTextColor(resources.getColor(R.color.colorBlue))
            tv_login_sms.setTextColor(resources.getColor(R.color.colorBlack))
            et_psw.visibility = View.VISIBLE
            rl_sms.visibility = View.INVISIBLE
            loginType = 0
        }
        tv_login_sms.setOnClickListener {
            tv_login_psw.setTextColor(resources.getColor(R.color.colorBlack))
            tv_login_sms.setTextColor(resources.getColor(R.color.colorBlue))
            et_psw.visibility = View.INVISIBLE
            rl_sms.visibility = View.VISIBLE
            loginType = 1
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
            }, 1000,1000)
            post("${BASE_URL}registerVerify", hashMapOf<String,String>("token" to MyApplication.token!!,"mobile" to et_phone.text.toString()),object :DataCallback<SmsCodeBean>{
                override fun dataSuccess(result: SmsCodeBean) {
                    if(result.code == 1){
                        Toast.makeText(this@LoginActivity, "发送验证码成功", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@LoginActivity, "发送验证码失败", Toast.LENGTH_LONG).show()
                        tv_timer.text = "发送验证码"
                        timer?.cancel()
                        max_time = 0
                    }

                }

                override fun dataFail(msg: String) {
                    Toast.makeText(this@LoginActivity, "发送验证码失败", Toast.LENGTH_LONG).show()
                    tv_timer.text = "发送验证码"
                    timer?.cancel()
                    max_time = 0
                }

            })
        }
        btn_login.setOnClickListener {
            if (loginType == 0) {
                if (TextUtils.isEmpty(et_phone.text.toString()) || TextUtils.isEmpty(et_psw.text.toString())) {
                    Toast.makeText(this, "请填写完整信息", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                if (!Utils.isMobileNO(et_phone.text.toString())) {
                    Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                post(
                    "${BASE_URL}verifyLogin",
                    hashMapOf(
                        "token" to MyApplication.token!!,
                        "mobile" to et_phone.text.toString(),
                        "passwd" to et_psw.text.toString()
                    ),
                    object : DataCallback<LoginBean> {
                        override fun dataSuccess(result: LoginBean) {
                            if (result.code == 1) {
                                Log.i("keyikeApp", "verifyLogin : $result")
                                setUserSpData(result.user_id)
                                MyApplication.user_id = result.user_id
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                this@LoginActivity.finish()
                            } else {
                                Toast.makeText(this@LoginActivity, "登录失败", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun dataFail(msg: String) {
                            Toast.makeText(this@LoginActivity, "登录失败", Toast.LENGTH_LONG).show()
                        }

                    })
            } else if (loginType == 1) {
                if (TextUtils.isEmpty(et_phone.text.toString()) || TextUtils.isEmpty(et_code.text.toString())) {
                    Toast.makeText(this, "请填写完整信息", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                if (!Utils.isMobileNO(et_phone.text.toString())) {
                    Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                post(
                    "${BASE_URL}verifyMobile",
                    hashMapOf(
                        "token" to MyApplication.token!!,
                        "mobile" to et_phone.text.toString(),
                        "verify_code" to et_code.text.toString()
                    ),
                    object : DataCallback<LoginBean> {
                        override fun dataSuccess(result: LoginBean) {
                            if (result.code == 1) {
                                Log.i("keyikeApp", "verifyLogin : $result")
                                setUserSpData(result.user_id)
                                MyApplication.user_id = result.user_id
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                this@LoginActivity.finish()
                            } else {
                                Toast.makeText(this@LoginActivity, "登录失败", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun dataFail(msg: String) {
                            Toast.makeText(this@LoginActivity, "登录失败", Toast.LENGTH_LONG).show()
                        }

                    })
            }
        }
        btn_register.setOnClickListener {
            Log.d("website", "login")
            startActivity(Intent(this,RegisterAvtivity::class.java))
        }

        iv_remember.setOnClickListener {
            Log.d("website", "login")

        }
        tv_forget.setOnClickListener {
            Log.d("website", "login")

        }
        ll_wechat.setOnClickListener {
            WeChatInstance.login()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
        timer = null
        WeChatInstance.removeLoginCallback(this)
    }

    override fun loginSuccess(code: String) {
        get("https://api.weixin.qq.com/sns/oauth2/access_token", hashMapOf<String,String>("appid" to WEXIN_APPID,"secret" to WEXIN_SECRET,"code" to code,"grant_type" to "authorization_code"),object :DataCallback<WeChatTokenBean>{
            override fun dataSuccess(result: WeChatTokenBean) {
                get("https://api.weixin.qq.com/sns/userinfo?", hashMapOf<String,String>("access_token" to result.access_token,"openid" to result.openid),object :DataCallback<WeChatLoginBean>{
                    override fun dataSuccess(result: WeChatLoginBean) {

                    }

                    override fun dataFail(msg: String) {
                        Toast.makeText(this@LoginActivity,"微信登录失败",Toast.LENGTH_LONG).show()
                    }

                })
            }

            override fun dataFail(msg: String) {
               Toast.makeText(this@LoginActivity,"微信登录失败",Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun loginFail() {
        Toast.makeText(this,"微信登录失败 ",Toast.LENGTH_LONG).show()
    }
}
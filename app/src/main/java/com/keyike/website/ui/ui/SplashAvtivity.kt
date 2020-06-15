package com.keyike.website.ui.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.keyike.website.*
import com.keyike.website.bean.WrapData
import kotlinx.android.synthetic.main.activity_splash.*
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest


class SplashAvtivity : BaseActivity(), EasyPermissions.PermissionCallbacks {
    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun create() {

//        EasyPermissions.requestPermissions(this,"需要打开权限\n请去设置里打开",0, Manifest.permission.READ_PHONE_STATE)
        getToken()
        iv_bg.setOnClickListener {
            getToken()
        }
    }

    /*
    获取token
     */
    private fun getToken(){
        val androidId = Settings.System.getString(contentResolver, Settings.System.ANDROID_ID)
        Log.d("keyikeApp", "deviceId = $androidId")
        post("${BASE_URL}getToken", hashMapOf("device_code" to androidId), object : DataCallback<WrapData> {
                override fun dataSuccess(result: WrapData) {
                    if(result.code == 1){
                        MyApplication.token = result.token
                        val userId = getUserSpData()
                        if (TextUtils.isEmpty(userId)) {
                            toLoginActivity()
                        } else {
                            toMainActivity()
                        }
                        this@SplashAvtivity.finish()
                    }else{
                        Toast.makeText(this@SplashAvtivity,"获取服务器信息失败，请点击屏幕重试",Toast.LENGTH_LONG).show()
                    }
                }

                override fun dataFail(msg: String) {
                    Toast.makeText(this@SplashAvtivity,"获取服务器信息失败，请点击屏幕重试",Toast.LENGTH_LONG).show()
                }
            })
    }
    fun toMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun toLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }







    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        this.finish()
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}
package com.tw.sessiondemo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import com.tw.sessiondemo.SessionVar.ISLOGIN
import com.tw.sessiondemo.SessionVar.KEY_PHONE

@RequiresApi(Build.VERSION_CODES.Q)
object SessionVar {
    const val ISLOGIN="islogin"
    const val KEY_USER_ID = "user_id"
    const val KEY_PHONE = "key_phone"
}

private const val PREF_NAME = "draft_session"

class SessionManager(context: Context) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences = appContext.getSharedPreferences(PREF_NAME, 0)   //0 for private mode

    private val editor: SharedPreferences.Editor = preference.edit()


    fun createLoginStatus(isLogin: Boolean) {
        editor.putBoolean(ISLOGIN, isLogin).apply()
    }

    fun getLoginStatus(): HashMap<String, Boolean> {
        val isLogin = HashMap<String, Boolean>()
        isLogin.put(ISLOGIN, preference.getBoolean(ISLOGIN, false))
        return isLogin
    }

    fun setPhone(phone: String) {
        editor.putString(KEY_PHONE, phone).apply()
    }

    fun getPhone(): HashMap<String, String> {
        val phone = HashMap<String, String>()
        phone.put(KEY_PHONE, preference.getString(KEY_PHONE, null).toString())
        return phone
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    fun logoutUser() {
        editor.clear()
        editor.apply()

        val intent = Intent(appContext, LoginScreen::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        appContext.startActivity(intent)
    }


}

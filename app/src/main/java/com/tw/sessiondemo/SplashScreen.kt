package com.tw.sessiondemo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@RequiresApi(Build.VERSION_CODES.Q)
class SplashScreen : AppCompatActivity() {

    //splash screen display and wait till 3500 seconds
    private val SPLASH_TIME_OUT : Int= 3500
    var TAG: String? = this.javaClass.simpleName
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        sessionManager= SessionManager(this)

        navigate()

    }


    private fun navigate() {
        //taking login status in boolean variable
        val loginStatus = sessionManager.getLoginStatus()[SessionVar.ISLOGIN]

        if (loginStatus==true){

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, SPLASH_TIME_OUT.toLong())

        }else{

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@SplashScreen, LoginScreen::class.java)
                startActivity(intent)
                finish()
            }, SPLASH_TIME_OUT.toLong())

        }
    }

}
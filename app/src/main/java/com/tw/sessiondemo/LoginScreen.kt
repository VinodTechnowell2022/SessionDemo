package com.tw.sessiondemo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tw.sessiondemo.databinding.LoginScreenBinding
import java.util.Random

@RequiresApi(Build.VERSION_CODES.Q)
class LoginScreen : AppCompatActivity() {

    var TAG: String? = this.javaClass.simpleName
    lateinit var sessionManager: SessionManager
    lateinit var mbinding: LoginScreenBinding

    //taking two variable for captcha
    var var1: Int = 0
    var var2: Int= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = LoginScreenBinding.inflate(layoutInflater)
        setContentView(mbinding.root)
        sessionManager= SessionManager(this)

        val version = String.format(resources.getString(R.string.app_version), BuildConfig.VERSION_NAME +" v")
        mbinding.bottomLayout.tvVersion.text = version
        mbinding.toolbar.title = resources.getString(R.string._login)

        generateCaptcha()

        mbinding.flRefresh.setOnClickListener {
            generateCaptcha()
        }

        mbinding.btnLogin.setOnClickListener {
            val phone = mbinding.etPhone.text.toString()

            if (phone.isEmpty()){
                Toast.makeText(this@LoginScreen, "Please enter phone number", Toast.LENGTH_SHORT).show()
            }else{

                val captcha = mbinding.etCaptcha.text.toString()

                if (captcha.toInt()==(var1+var2)){
                    sessionManager.createLoginStatus(true)
                    sessionManager.setPhone(phone)
                    val intent = Intent(this@LoginScreen, MainActivity::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this@LoginScreen, "Wrong Captcha entered", Toast.LENGTH_SHORT).show()
                }


            }
        }

    }

    private fun generateCaptcha() {

        // create instance of Random class
        val rand = Random()

        // Generate random integers in range 0 to 11
        var1 = rand.nextInt(11)
        var2 = rand.nextInt(11)

        mbinding.tvCaptcha.text = "$var1  +  $var2  = "

    }

}
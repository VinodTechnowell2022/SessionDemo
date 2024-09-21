package com.tw.sessiondemo

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tw.sessiondemo.databinding.ActivityMainBinding
import com.tw.sessiondemo.databinding.LoginScreenBinding

@RequiresApi(Build.VERSION_CODES.Q)
class MainActivity : AppCompatActivity() {

    var TAG: String? = this.javaClass.simpleName
    lateinit var sessionManager: SessionManager
    lateinit var mbinding: ActivityMainBinding


    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mbinding.root)
        sessionManager= SessionManager(this)


        mbinding.ivLogout.setOnClickListener {
            confirmationDialog()
        }

    }

    private fun confirmationDialog() {

        val builder: AlertDialog.Builder = AlertDialog.Builder( this )
        builder.setTitle(resources.getString(R.string._exit_alert))
        builder.setMessage(resources.getString(R.string._sure_exit_alert))
        builder.setCancelable(true)

        builder.setPositiveButton(resources.getString(R.string._yes)) { dialog, which ->
            dialog.dismiss()
            sessionManager.createLoginStatus(false)
            val intent = Intent(this@MainActivity, LoginScreen::class.java)
            startActivity(intent)
            finish()
        }

        builder.setNegativeButton(resources.getString(R.string._no)) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }



    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        //super.onBackPressed()
        if (doubleBackToExitPressedOnce) {
            this.finish()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, resources.getString(R.string.click_again_to_exit), Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}
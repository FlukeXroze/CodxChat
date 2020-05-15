package com.trap9.codxchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_ac.*
import kotlinx.android.synthetic.main.activity_create_ac.btn_createac
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_forgotpassword.setOnClickListener{
            var intent = Intent(this,ForgotpasswordActivity::class.java)
            startActivity(intent)
        }

        btn_createac.setOnClickListener{
            var intent = Intent (this,CreateAcActivity::class.java)
            startActivity(intent)
        }
    }
}

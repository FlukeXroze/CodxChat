package com.trap9.codxchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgotpassword.*
import kotlinx.android.synthetic.main.activity_main.*

class ForgotpasswordActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpassword)

        btn_sendchangpassword.setOnClickListener {
            var email = edt_forgotpassword.text.toString().trim()

            if (!TextUtils.isEmpty(email)) {
                mAuth!!.sendPasswordResetEmail(email)
                    .addOnCompleteListener {task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Please check your email", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG).show()
                        }
                }
            } else {
                Toast.makeText(this, "Please put your email to reset", Toast.LENGTH_LONG).show()
            }
        }
    }
}

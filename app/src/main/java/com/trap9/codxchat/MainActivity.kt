package com.trap9.codxchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_ac.*
import kotlinx.android.synthetic.main.activity_create_ac.btn_createac
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        var email = edt_email_login.text.toString().trim()
        var password = edt_password_login.text.toString().trim()

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
                        var intent = Intent(this, DashboardActivity::class.java)
                        intent.putExtra("userId", mAuth!!.currentUser!!.uid)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_LONG).show()
                    }
                }
        } else {
            Toast.makeText(this, "Please put your email and password", Toast.LENGTH_LONG).show()
        }

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

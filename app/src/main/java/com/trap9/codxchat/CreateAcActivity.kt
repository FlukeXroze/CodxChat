package com.trap9.codxchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_ac.*

class CreateAcActivity : AppCompatActivity() {

    var mAuth:FirebaseAuth? = null
    var mDatabase:FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ac)

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()

        btn_createac.setOnClickListener{
            var display_name = edt_createac_username.text.toString().trim()
            var email = edt_createac_email.text.toString().trim()
            var password = edt_createac_password.text.toString().trim()

            if(TextUtils.isEmpty(display_name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                mAuth!!.createUserWithEmailAndPassword(email,password).addOnCompleteListener { 
                    task: Task<AuthResult> ->

                    if(task.isSuccessful) {
                        var user = mAuth!!.currentUser
                        var userId = user!!.uid

                        var userRef = mDatabase!!.reference.child("Users").child(userId)

                        var userObject = HashMap<String,String>()
                        userObject.put("display_name",display_name)
                        userObject.put("status","FeelGood")
                        userObject.put("image","default")
                        userObject.put("thumb_image","default")

                        userRef.setValue(userObject).addOnCompleteListener {
                            task: Task<Void> ->

                            if(task.isSuccessful){
                                Toast.makeText(this,"Create Successful",Toast.LENGTH_LONG).show()
                            }else
                                Toast.makeText(this,"Create Unsuccessful",Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}


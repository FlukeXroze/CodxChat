package com.trap9.codxchat.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.trap9.codxchat.R
import kotlinx.android.synthetic.main.activity_your_profile.*

class YourProfileActivity : AppCompatActivity() {

    var mAuth:FirebaseAuth? = null
    var mDatabase:FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_profile)

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()

        var userId = mAuth!!.currentUser!!.uid

        var userRef = mDatabase!!.reference.child("Users").child(userId)

        userRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                finish()
            }

            override fun onDataChange(dataSnapshot : DataSnapshot) {
                var display_name = dataSnapshot.child("display_name").value.toString()
                var status = dataSnapshot.child("status").value.toString()
                var image = dataSnapshot.child("image").value.toString()

                tv_display_name_profile.text = display_name
                tv_status_profile.text = status

                if (image != null)
                    Picasso.get().load(image).placeholder(R.drawable.ic_person).into(iv_image_profile)

            }

        })
    }

}

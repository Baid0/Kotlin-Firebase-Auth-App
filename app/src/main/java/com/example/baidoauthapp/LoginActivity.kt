package com.example.baidoauthapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        login_button.setOnClickListener(){
            val email = email_login.text.toString()
            val password = password_login.text.toString()
            val intent = Intent(this,ProfileActivity::class.java)

            Log.d("Login","Attempt Login with email/pw: $email/***")

            if (email.isEmpty() && password.isEmpty()){
                Toast.makeText(this,"E-Mail And Password Is Not Defined",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.isEmpty()){
                Toast.makeText(this,"E-Mail Is Not Defined",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isEmpty()){
                Toast.makeText(this,"Password Is Not Defined",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)



                .addOnCompleteListener() {
                    if (it.isSuccessful) {
                        Toast.makeText(this,"Logged In Succesfully",Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                        return@addOnCompleteListener



                    }

                }
                .addOnFailureListener(){
                    Toast.makeText(this,"E-Mail Or Password is Incorrect",Toast.LENGTH_SHORT).show()
                    return@addOnFailureListener
            }
        }

        back_to_register.setOnClickListener(){
            finish()
        }
    }
}

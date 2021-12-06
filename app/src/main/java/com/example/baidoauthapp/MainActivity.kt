package com.example.baidoauthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        register_button_register.setOnClickListener() {
            val email = email_edittext_register.text.toString()
            val password = password_edittext_register.text.toString()
            val recheck = password_recheck.text.toString()
            val intent = Intent(this,LoginActivity::class.java)


            if (email.isEmpty() && password.isEmpty()){
                Toast.makeText(this, "Please enter Your Email And Password",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter Your Email",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (password.isEmpty()){
                Toast.makeText(this,"Please Enter Your Password",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 9 ){
                Toast.makeText(this, "Your Password Must Be longer than 8 characters",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!password.equals(recheck)) {
                Toast.makeText(this, "Your Passwords Do Not Match",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)

            if (!password.matches(passwordMatcher)) {
                Toast.makeText(this, "You Have To Use Numbers And Symbols in your password",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }





            Log.d("MainActivity", "Email is:" + email)
            Log.d("MainActivity", "Password is:  $password")

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(){
                    if (!it.isSuccessful) return@addOnCompleteListener

                    if (it.isSuccessful){
                        Toast.makeText(this,"You Registered Succesfully",Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                        return@addOnCompleteListener
                    }
                    //else if successful

                    Log.d("Main","succesfully created user with uid: ${it.result?.user?.uid}")

                }
                .addOnFailureListener(){
                    Toast.makeText(this,"Your E-Mail Is Incorrect Or It is Used",Toast.LENGTH_SHORT).show()
                    return@addOnFailureListener
                }

        }

        login_textbutton.setOnClickListener(){
            Log.d("MainActivity", "show log in")

            // login start
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.firebaseproject

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    private lateinit var edittextEmail:EditText
    private lateinit var edittextPassword:EditText
    private lateinit var buttonLogin:Button
    private lateinit var buttonRegister:Button
    private lateinit var buttonResetPassword:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_login)
        init()
    }
    private fun init(){
        edittextEmail=findViewById(R.id.UserEmailLoginEditText)
        edittextPassword=findViewById(R.id.UserPasswordLoginEditText)
        buttonLogin=findViewById(R.id.LoginButton)
        buttonRegister=findViewById(R.id.RegistrationLoginButton)
        buttonResetPassword=findViewById(R.id.ResetPasswordLoginButton)

        listeners()
    }
    private fun listeners(){

        buttonLogin.setOnClickListener {
            var email=edittextEmail.text.toString()
            var password=edittextPassword.text.toString()
            if(email.isEmpty()||password.isEmpty()){
                Toast.makeText(this,"empty email or password",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{
                    task->if(task.isSuccessful){
                        goToProfile()
                }else{
                    Toast.makeText(this,"try again",Toast.LENGTH_LONG).show()
                }
                }

        }
        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        buttonResetPassword.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

    }
    private fun goToProfile(){
        startActivity(Intent(this,ProfileActivity::class.java))
        finish()
    }
}
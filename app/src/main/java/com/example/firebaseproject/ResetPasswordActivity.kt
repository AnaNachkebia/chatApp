package com.example.firebaseproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var edittextResetPasswordUserEmail:EditText
    private lateinit var buttonResetPassword:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_password_reset)
        init()
    }
    private fun init(){
        edittextResetPasswordUserEmail=findViewById(R.id.UserEmailResetPasswordEditText)
        buttonResetPassword=findViewById(R.id.ResetPasswordButton)
        listeners()
    }
    private fun listeners(){
        buttonResetPassword.setOnClickListener {
            var email=edittextResetPasswordUserEmail.text.toString()
            if(email.isEmpty()){
                Toast.makeText(this, "empty email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
                task->if(task.isSuccessful){
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
            }

        }
    }
}
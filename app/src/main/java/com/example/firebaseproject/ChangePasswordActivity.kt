package com.example.firebaseproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var changePasswordPassword:TextView
    private lateinit var changePasswordButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_password_change)
        init()
    }
    private fun init(){
        changePasswordPassword=findViewById(R.id.PasswordChangePasswordEditText)
        changePasswordButton=findViewById(R.id.ChangePasswordButton)
        listeners()
    }
    private fun listeners(){
        changePasswordButton.setOnClickListener {
            var password=changePasswordPassword.text.toString()
            if(password.isEmpty()||password.length<7){
                Toast.makeText(this, "try other", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().currentUser?.updatePassword(password)?.addOnCompleteListener {
                task->if(task.isSuccessful){
                Toast.makeText(this, "this password updated", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,LoginActivity::class.java))
            }else{
                Toast.makeText(this, "try again", Toast.LENGTH_SHORT).show()
            }

            }
        }
    }
}
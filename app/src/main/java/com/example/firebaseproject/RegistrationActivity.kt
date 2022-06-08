package com.example.firebaseproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {
    private lateinit var edittextUserNameRegistration:EditText
    private lateinit var edittextUserEmailRegistration:EditText
    private lateinit var edittextPasswordRegistration:EditText
    private lateinit var buttonRegistration:Button
    private lateinit var textviewAlreadyRegistered:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_registration)
        init()
    }
    private fun init(){
        edittextUserNameRegistration=findViewById(R.id.UserNameRegistrationEditText)
        edittextUserEmailRegistration=findViewById(R.id.UserEmailRegistrationEditText)
        edittextPasswordRegistration=findViewById(R.id.PasswordRegistrationEditText)
        buttonRegistration=findViewById(R.id.RegistrationButton)
        textviewAlreadyRegistered=findViewById(R.id.AlreadyRegisteredTextview)
        listeners()
    }
    private fun listeners(){
        buttonRegistration.setOnClickListener {
            var email=edittextUserEmailRegistration.text.toString()
            var password=edittextPasswordRegistration.text.toString()
            val name=edittextUserNameRegistration.text.toString()
            if(email.isEmpty()||password.isEmpty()){
                Toast.makeText(this,"email or password is empty",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    task->if(task.isSuccessful){
                        addUserToDatabase(name,email,FirebaseAuth.getInstance().currentUser?.uid!!)
                        goToProfile()
                }else{
                    Toast.makeText(this,"try again",Toast.LENGTH_LONG).show()
                }
                }
        }
        textviewAlreadyRegistered.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
    private fun goToProfile(){
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
    private fun addUserToDatabase(name:String,email:String,uid:String){

        val database=Firebase.database
        val myRef=database.getReference()
        myRef.child("user").child(uid).setValue(User(name,email,uid))
    }
}